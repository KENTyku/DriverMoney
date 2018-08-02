/*
 * Use and copying for commercial purposes
 * only with the author's permission
 */
package ru.kentyku.drivermoney;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Класс описывающий логику работы приложения
 *
 * @author kentyku
 */
public class Logic {

    private String command = "";//выбранная команда
    private Scanner sc = new Scanner(System.in);//сканер для ввода команды    
    UseDataBaze udb = new UseDataBaze();//обработчик БД
    
    //консольный выбор команд
    void selectCommand() throws ClassNotFoundException, SQLException, InterruptedException {
        //подключение БД и проверка ее корректности.
        udb.initDB();
        //консольный выбор команд
        while (true) {
            System.out.println("Выберите требуемое действие(add,del,sortToMin,sortToMax,show,showLimit,copy,load,exit)");
            Thread.sleep(5000);
            command = sc.nextLine();

            //команда добавления нового водителя в программу
            if (command.equals("add")) {
                add();
            }

            //команда удаления нового водителя из программы
            if (command.equals("del")) {
                delete();
            }
            //команда сортировки данных о водителях по месячной зарплате по убыванию
            if (command.equals("sortToMin")) {
                sortToMin();
            }
            //команда сортировки данных о водителях по месячной зарплате по возрастанию
            if (command.equals("sortToMax")) {
                sortToMax();
            }
            //команда вывода данных о всех водителях (по имени и месячной зарплате)
            if (command.equals("show")) {
                show();
            }
            //команда вывода данных о N первых водителях 
            if (command.equals("showLimit")) {
                showLimit();
            }
            //команда копирования списка водителей в файл 
            if (command.equals("copy")) {
                saveList();
            }

            //команда загрузки списка водителей из файла 
            if (command.equals("load")) {
                loadList();
            }

            //Выход из программы
            if (command.equals("exit")) {
                System.exit(0);
            }
        }

    }

    //добавление водителя в БД
    void add() throws SQLException, InterruptedException {
        //инициализация
        while (true) {
            String command = null;
            System.out.println("Выберите стратегию оплаты добавляемого водителя (everymonth, everyhour)");
            command = sc.nextLine();
            if (command.equals("everymonth")) {
                //инициализация
                int typedriver = 0;
                System.out.println("Введите имя водителя:");
                String name = sc.nextLine();
                System.out.println("Введите размер помесячной оплаты:");
                int cost = sc.nextInt();
                //создаем водителя с заданными свойствами    
                Driver dm = new DriverMonth(name, typedriver, cost);
                dm.calculateMonthMoney();
                udb.addToBase(dm);//добавляем в БД
                break;
            }
            if (command.equals("everyhour")) {
                int typedriver = 1;
                System.out.println("Введите имя водителя:");
                String name = sc.nextLine();
                System.out.println("Введите размер почасовой оплаты:");
                int cost = sc.nextInt();
                Driver dh = new DriverHour(name, typedriver, cost);
                dh.calculateMonthMoney();
                udb.addToBase(dh);
                break;
            } else {
                System.out.println("Неверная команда");
                Thread.sleep(1000);
            }
        }
    }

    //удаление водителя из БД
    void delete() throws SQLException {
        System.out.println("Введите имя удаляемого водителя:");
        String name = sc.nextLine();
        Driver dr = new Driver();
        dr.setName(name);
        udb.removeDriver(dr);
    }

    void loadList() throws SQLException {
        //Сохраняем список водителей из БД программы в файл.
        String lineText; //для чтения из буффера        
        System.out.println("Укажите имя файла для загрузки данных в программу(в формате .csv)");
        String filename = sc.nextLine();
        FileReader freader;
        try {
            freader = new FileReader(filename);
            BufferedReader in = new BufferedReader(freader);
            int count = 0;
            //смотрим первую строку файла на соответствие эталону
            while ((lineText = in.readLine()) != null) {
                String[] arraysubstrings = lineText.split(";");
                if (count == 0) {
                    if (!verifyLoadedFile(arraysubstrings)) {//проверка полей файла
                        break;
                    }
                } else {//остальные строки кладем в базу 

                    Driver dr = new Driver();
                    dr.setName(arraysubstrings[1]);
                    dr.setTypedriver(Integer.parseInt(arraysubstrings[2]));
                    dr.setCost(Double.parseDouble(arraysubstrings[3]));
                    dr.setMonthMoney(Double.parseDouble(arraysubstrings[4]));
                    udb.addToBase(dr);
                }
                count = 1;
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            System.out.println("Ошибка загрузки файла");
        }
    }

    void saveList() throws SQLException {
        //Сохраняем список водителей из БД программы в файл.
        ArrayList<String> list = udb.readList();
        System.out.println("Введите имя файла для сохранения(без расширения)");
        String filename = sc.nextLine();
        try {
            FileWriter fwriter = new FileWriter(filename + ".csv", false);
            for (String item : list) {
                fwriter.write(item);
            }
            fwriter.flush();
            fwriter.close();
        } catch (IOException ex) {
            System.out.println("Ошибка записи!\n" + ex.getMessage());
        }
    }

    void sortToMin() throws SQLException {
        //сортировать водителей по убыванию среднемесячного заработка
        udb.sortByMonthMoneyToMin();
    }

    void sortToMax() throws SQLException {
        //сортировать водителей по возрастанию среднемесячного заработка
        udb.sortByMonthMoneyToMax();
    }

    void show() throws SQLException {
        //выводить на экран ФИО и среднемесячный заработок для всех элементов списка
        udb.showNameMonthMoneyList();
    }

    void showLimit() throws SQLException {
        System.out.println("Введите максимальное количество отображаемых строк");
        int n = sc.nextInt();
        //выводить на экран N первых элемента из списка
        udb.showLimitRows(n);
    }
//проверка загруженного файла

    boolean verifyLoadedFile(String[] line) {
        boolean ok = true;
        //эталонная структура заголовков файла базы
        List nameColumsIdeal = new ArrayList<String>();
        nameColumsIdeal.add("number");
        nameColumsIdeal.add("name");
        nameColumsIdeal.add("typedriver");
        nameColumsIdeal.add("cost");
        nameColumsIdeal.add("monthmoney");
       //сравниваем с эталоном
        if (line.length == nameColumsIdeal.size()) {
            for (int i = 1; i < line.length; i++) {
                if (!(line[i].equals(nameColumsIdeal.get(i)))) {
                    ok = false;
                }
            }
        } else {
            ok = false;
        }
        if (!ok) {
            System.out.println("Структура загружаемого файла данных не совместима, используйте другой файл данных");
        }
        return ok;
    }
}
