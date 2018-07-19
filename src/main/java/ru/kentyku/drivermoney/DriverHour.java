/*
 * Use and copying for commercial purposes
 * only with the author's permission
 */
package ru.kentyku.drivermoney;

/**
 *
 * @author kentyku
 */
public class DriverHour extends Driver {

    Integer HourMoney;

    Double calculatemonthMoney() {
        HourMoney = 1;
        MonthMoney = 20.8 * 8 * HourMoney;
        return MonthMoney;
    }
}
