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
    
    
    DriverMonth(String name,double cost){
     this.Name=name;
     this.cost=cost;
    }

    Double calculateMonthMoney() {
        this.monthMoney=this.cost;
        return this.monthMoney;
    }
}
