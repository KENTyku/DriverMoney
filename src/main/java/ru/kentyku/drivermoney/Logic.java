/*
 * Use and copying for commercial purposes
 * only with the author's permission
 */
package ru.kentyku.drivermoney;

import java.sql.SQLException;
import java.util.Scanner;

/**
 * Класс описывающий логику работы приложения
 *
 * @author kentyku
 */
public class Logic {

    String typedriver;

    //консольный выбор команд
    void selectcommand() throws ClassNotFoundException, SQLException {
        String command = "";
        while (true) {
            System.out.println("Выберите требуемое действие");
            Scanner sc = new Scanner(System.in);//сканер для ввода команды
            command = sc.nextLine();

            //команда добавления нового водителя в программу
            if (command.equals("/add")) {
                System.out.println("Выберите стратегию оплаты добавляемого водителя");
                command = sc.nextLine();
                if (command.equals("everymonth")) {
                    System.out.println("Введите имя водителя:");
                    String name = sc.nextLine();
                    System.out.println("Введите размер помесячной оплаты:");
                    int cost = sc.nextInt();
                    DriverMonth dm = new DriverMonth(name, cost);

                    UseDataBaze udb = new UseDataBaze();
                    udb.initDB();
                    System.out.println("m");
                }
                if (command.equals("everyhour")) {
                    DriverHour dh = new DriverHour("sd",3);
                    System.out.println("h");
                }
            }

            //Выход из программы
            if (command.equals("/exit")) {
                System.exit(0);
            }
//            break;//заглушка против зацикливания
        }
        //сканер для ввода команды

    }

    void ReadingToBaze() {
        //Создание класса обработчика соединений с БД. Содержит все методы для работы с базой
    }

    void checkFile() {
        //проверка корректности загружаемого файла
    }

    void loadingList() {
        //Загружаем список водителей из файла в БД программы.
        checkFile();
    }

    void SaveList() {
        //Сохраняем список водителей из БД программы в файл.
    }

}
