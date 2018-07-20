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
import java.util.logging.Level;
import java.util.logging.Logger;

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
    
    

    //подключение к БД
    void connect()  {
        try {
            Class.forName(DRIVER_NAME);
            try {
                connection = DriverManager.getConnection("jdbc:sqlite:" + nameDB);
            } catch (SQLException ex) {
                Logger.getLogger(ReadingToBaze.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                stmt = connection.createStatement();
            } catch (SQLException ex) {
                Logger.getLogger(ReadingToBaze.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ReadingToBaze.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //отключение от БД
    void disconnect() {
        try {
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(ReadingToBaze.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    //первоначальная настройка БД
    void initDB() throws ClassNotFoundException, SQLException{
        connect();
        disconnect();        
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
