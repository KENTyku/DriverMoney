/*
 * Use and copying for commercial purposes
 * only with the author's permission
 */
package ru.kentyku.drivermoney;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author kentyku
 */
public class ReadingToBaze {
    
    static final String DRIVER_NAME = "org.sqlite.JDBC";
    static String nameDB = "sqlite.db";
    Statement stmt;
    PreparedStatement pstmt;
    Connection connection;
    ResultSet rs;

    void connect() throws ClassNotFoundException, SQLException {
         Class.forName(DRIVER_NAME);
            connection = DriverManager.getConnection("jdbc:sqlite:" + nameDB);
            stmt = connection.createStatement();
    //подключение к БД
    }

    void disconnect() {
    //отключение от БД
    }

    void addDriver() {
        // добавляет водителя в список БД
    }

    void removeDriver() {
        // удаляем водителя из списка БД
    }

    void showList() {
        //показать весь список
    }

    void showListLimited() {
        //показать N перыйх элементов списка
    }

    void sortListByMonthMoneyTop() {
        //сортировка списка по убыванию
    }

    void sortListByMonthMoneyBottom() {
        //сортировка списка по возрастанию
    }
}
