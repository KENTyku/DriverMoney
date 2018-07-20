/*
 * Use and copying for commercial purposes
 * only with the author's permission
 */
package ru.kentyku.drivermoney;

import java.sql.SQLException;

/**
 *
 * @author kentyku
 */
public class DriverMoney {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Logic l=new Logic();
        l.selectcommand();        
    }

}
