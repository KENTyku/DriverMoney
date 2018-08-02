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

   
     DriverHour(String name,int typedriver,double cost){
     this.name=name;
     this.cost=cost;
     this.typedriver=typedriver;
    }
     
     DriverHour(String name) {
        this.name = name;
    }

    Double calculateMonthMoney() {        
        this.monthMoney=20.8 * 8 * this.cost;
        return this.monthMoney;
    }
}
