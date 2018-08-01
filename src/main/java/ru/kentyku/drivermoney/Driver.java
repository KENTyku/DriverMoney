/*
 * Use and copying for commercial purposes
 * only with the author's permission
 */
package ru.kentyku.drivermoney;

/**
 *
 * @author kentyku
 */
public class Driver {

    protected String Name;//имя
    protected Double cost;//ставка
    protected int typedriver;
    protected Double monthMoney;//месячная ЗП


    Double calculateMonthMoney() {
        return getCost();

    }

    /**
     * @return the Name
     */
    public String getName() {
        return Name;
    }

    /**
     * @param Name the Name to set
     */
    public void setName(String Name) {
        this.Name = Name;
    }

    /**
     * @return the cost
     */
    public Double getCost() {
        return cost;
    }

    /**
     * @param cost the cost to set
     */
    public void setCost(Double cost) {
        this.cost = cost;
    }

    /**
     * @return the monthMoney
     */
    public Double getMonthMoney() {
        return monthMoney;
    }

    /**
     * @param monthMoney the monthMoney to set
     */
    public void setMonthMoney(Double monthMoney) {
        this.monthMoney = monthMoney;
    }

    /**
     * @return the typedriver
     */
    public int getTypedriver() {
        return typedriver;
    }

    /**
     * @param typedriver the typedriver to set
     */
    public void setTypedriver(int typedriver) {
        this.typedriver = typedriver;
    }
}
