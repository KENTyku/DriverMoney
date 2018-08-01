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
        if (!verifyDB()) {
            stmt.executeUpdate("CREATE TABLE drivers ("
                    + "number INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL UNIQUE,"
                    + "name TEXT NOT NULL,"
                    + "typedriver INTEGER NOT NULL DEFAULT NULL,"
                    + "cost INTEGER NOT NULL DEFAULT NULL,"
                    + "monthmoney DOUBLE);");
        }
        disconnect();
    }

    // добавляет водителя в список БД
    void addToBase(Driver driver) throws SQLException {
        connect();
        pstmt = connection.prepareStatement("INSERT INTO drivers "
                + "(name,typedriver,cost,monthmoney) VALUES(?,?,?,?);");
        pstmt.setString(1, driver.getName());
        pstmt.setInt(2, driver.getTypedriver());
        pstmt.setDouble(3, driver.getCost());
        pstmt.setDouble(4, driver.getMonthMoney());
        //выполняем запрос
        pstmt.executeUpdate();
        disconnect();

    }

    void removeDriver(Driver driver) throws SQLException {
        // удаляем водителя из списка БД
        connect();
        pstmt = connection.prepareStatement("DELETE FROM drivers "
                + "WHERE name=?;");
        pstmt.setString(1, driver.getName());
        //выполняем запрос
        pstmt.executeUpdate();
        disconnect();
    }

    void SortByMonthMoneyToMin() throws SQLException {
        //показать весь список, отсортированный по убыванию месячной зарплаты
        ArrayList<String> list = new ArrayList<String>();
        connect();
        rs = stmt.executeQuery("SELECT * FROM drivers ORDER BY monthmoney DESC,name ASC;");
        while (rs.next()) {//выводим на экран   
            System.out.println(rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3) + " " + rs.getString(4) + " " + rs.getString(5));
        }
        disconnect();
    }

    void SortByMonthMoneyToMax() throws SQLException {
        //показать весь список, отсортированный по возрастанию месячной зарплаты
        ArrayList<String> list = new ArrayList<String>();
        connect();
        rs = stmt.executeQuery("SELECT * FROM drivers ORDER BY monthmoney ASC,name ASC;");
        while (rs.next()) {//выводим на экран 
            System.out.println(rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3) + " " + rs.getString(4) + " " + rs.getString(5));
        }
        disconnect();
    }

    void showNameMonthMoneyList() throws SQLException {
        //показать список имен и месячных заработных плат
        ArrayList<String> list = new ArrayList<String>();
        connect();
        rs = stmt.executeQuery("SELECT name,monthmoney FROM drivers ORDER BY monthmoney ASC,name ASC;");
        while (rs.next()) {//выводим на экран 
            System.out.println(rs.getString(1) + " " + rs.getString(2));
        }
        disconnect();
    }

    ArrayList<String> readList() throws SQLException {
        //считать весь список в ArrayList
        ArrayList<String> list = new ArrayList<String>();
        //получаем из базы заголовки таблицы и формируем заголовки для сохраняемого файла
        ArrayList<String> nameColums = new ArrayList<String>();
        connect();
        rs = stmt.executeQuery("pragma table_info(drivers);");
        while (rs.next()) {
            nameColums.add(rs.getString(2));
        }
        String str = "";
        for (String item : nameColums) {
            str = str.concat(item + ";");
        }
        str = str.substring(0, str.length() - 1) + "\r\n";
        list.add(str);
        //формируем остальные данные
        rs = stmt.executeQuery("SELECT * FROM drivers ORDER BY monthmoney DESC;");
        while (rs.next()) {
            list.add(rs.getString(1) + ";" + rs.getString(2) + ";" + rs.getString(3) + ";" + rs.getString(4) + ";" + rs.getString(5) + "\r\n");
        }
        disconnect();
        return list;
    }

    void showLimitRows(int n) throws SQLException {
        //показать первые N строк списка
        ArrayList<String> list = new ArrayList<String>();
        connect();
        pstmt = connection.prepareStatement("SELECT * FROM drivers ORDER BY monthmoney ASC,name ASC limit 0,?;");
        pstmt.setInt(1, n);
        rs = pstmt.executeQuery();
        while (rs.next()) {//выводим на экран 
            System.out.println(rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3) + " " + rs.getString(4) + " " + rs.getString(5));
        }
        disconnect();
    }

    //проверка файла БД
    boolean verifyDB() {
        boolean ok = true;
        List nameColums = new ArrayList<String>();
        //эталонная структура файла базы
        List nameColumsIdeal = new ArrayList<String>();
        nameColumsIdeal.add("number");
        nameColumsIdeal.add("name");
        nameColumsIdeal.add("typedriver");
        nameColumsIdeal.add("cost");
        nameColumsIdeal.add("monthmoney");
        try {
            rs = stmt.executeQuery("pragma table_info(drivers);");
            while (rs.next()) {
                nameColums.add(rs.getString(2));
            }

            if (nameColums.size() == nameColumsIdeal.size()) {
                for (int i = 0; i < nameColums.size(); i++) {
                    if (!(nameColums.get(i).equals(nameColumsIdeal.get(i)))) {
                        ok = false;
                    }
                }
            } else {
                ok = false;
            }

            if (!ok) {
                System.out.println("Структура файла данных не совместима, используйте другой файл данных");
            }

        } catch (SQLException ex) {
            ok = false;
        }
        return ok;
    }

    void loadData(String[] line) {
        Driver dr = new Driver();
        dr.setName(line[1]);
        dr.setTypedriver(2);
        dr.setCost(Double.parseDouble(line[3]));
        dr.setMonthMoney(Double.parseDouble(line[4]));

    }
}
