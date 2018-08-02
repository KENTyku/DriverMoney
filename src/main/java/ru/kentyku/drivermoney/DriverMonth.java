/*
 * Use and copying for commercial purposes
 * only with the author's permission
 */
package ru.kentyku.drivermoney;

/**
 *
 * @author kentyku
 */
public class DriverMonth extends Driver {

    DriverMonth(String name, int typedriver, double cost) {
        this.name = name;
        this.cost = cost;
        this.typedriver = typedriver;
    }

    DriverMonth(String name) {
        this.name = name;
    }

    Double calculateMonthMoney() {
        this.monthMoney = this.cost;
        return this.monthMoney;
    }
}
