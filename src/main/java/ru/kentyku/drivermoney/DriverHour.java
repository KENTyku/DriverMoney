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

   
     DriverHour(String name,double cost){
     this.Name=name;
     this.cost=cost;
    }

    Double calculateMonthMoney() {        
        this.monthMoney=20.8 * 8 * this.cost;
        return this.monthMoney;
    }
}
