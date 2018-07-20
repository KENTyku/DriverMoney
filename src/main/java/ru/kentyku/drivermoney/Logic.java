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
            //сканер для ввода команды
            Scanner sc = new Scanner(System.in);
            command = sc.nextLine();

            //добавление нового водителя
            if (command.equals("/add")) {
                System.out.println("Выберите стратегию оплаты добавляемого водителя");
                command = sc.nextLine();
                if (command.equals("everymonth")) {
                    DriverMonth dm = new DriverMonth();
                    ReadingToBaze rtb = new ReadingToBaze();
                    rtb.connect();
                    System.out.println("m");
                }
                if (command.equals("everyhour")) {
                    DriverHour dh = new DriverHour();
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
