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
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kentyku
 */
public class UseDataBaze {

    static final String DRIVER_NAME = "org.sqlite.JDBC";
    static String nameDB = "myDB.db";
    Statement stmt;
    PreparedStatement pstmt;
    Connection connection;
    ResultSet rs;

    //подключение к БД
    void connect() {
        try {
            Class.forName(DRIVER_NAME);
            try {
                connection = DriverManager.getConnection("jdbc:sqlite:" + nameDB);
            } catch (SQLException ex) {
                Logger.getLogger(UseDataBaze.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                stmt = connection.createStatement();
            } catch (SQLException ex) {
                Logger.getLogger(UseDataBaze.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UseDataBaze.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //отключение от БД
    void disconnect() {
        try {
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(UseDataBaze.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //первоначальная настройка БД
    void initDB() throws ClassNotFoundException, SQLException {
        connect();
        verifyDB();
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

    void verifyDB() throws SQLException {
        List nameColums = new ArrayList<String>();
        
        List nameColumsIdeal = new ArrayList<String>();
        nameColumsIdeal.add("number");
        nameColumsIdeal.add("name");
        nameColumsIdeal.add("typedriver");
        nameColumsIdeal.add("cost");
        
        rs=stmt.executeQuery("pragma table_info(drivers);");
//        rs=stmt.executeQuery("SELECT name FROM drivers ;");
        while (rs.next()) {
            nameColums.add(rs.getString(2));
            System.out.println(rs.getString(2));
        }
        if (nameColums.size()==nameColumsIdeal.size()){
            for ( int i=0;i<nameColums.size();i++){
                if (nameColums.get(i)!=nameColumsIdeal.get(i)) {
                    System.out.println("Структура файла данных не совместима, используйте другой файл данных");
                }                
            }
        }
            
        else{
            System.out.println("Структура файла данных не совместима, используйте другой файл данных");
            
        }
    }
}
