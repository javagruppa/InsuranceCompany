/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package insurancecompany.model.bills;

import java.util.Calendar;

/**
 *
 * @author André
 */
public class Bill {
    
    /** Constant representing the number of days until each due date from issue, as well as days to dunningDate */
    public static final int FIXED_DUE_DAYS = 14;
    /** Date of when the bill is issued. */
    private Calendar isuedDate;
    /** Date of when the bill is due to be payed.*/
    private Calendar dueDate;
    /** Date of when the dunning is due to payed.*/
    private Calendar dunningDate;
    /** Date of when the payment was recieved.*/
    private Calendar payedDate;
    /** Sum of the fee for this bill.*/
    private int fee; // NOR: Gebyr
    /** Total sum of the dunning fee for this bill.*/
    private int dunningFee; // NOR: Purregebyr
    /** Boolean representing whether the bill is payed or not.*/
    private boolean payed;
    
    public Bill(int fee) {
        this.fee = fee;
        isuedDate = Calendar.getInstance();
        dueDate = Calendar.getInstance();
        dueDate.add(Calendar.DAY_OF_YEAR, FIXED_DUE_DAYS);
        dunningFee = 0;
        payed = false;
    }
    
    public void increaseDunningFee(int sum) {
        setDunningFee(getDunningFee() + sum);
    }
    
    public void payBill() {
        setPayedDate(Calendar.getInstance());
        setPayed(true);
    }

    /**
     * @return the isuedDate
     */
    public Calendar getIsuedDate() {
        return isuedDate;
    }

    /**
     * @return the dueDate
     */
    public Calendar getDueDate() {
        return dueDate;
    }

    /**
     * @return the dunningDate
     */
    public Calendar getDunningDate() {
        return dunningDate;
    }

    /**
     * @param dunningDate the dunningDate to set
     */
    public void setDunningDate(Calendar dunningDate) {
        this.dunningDate = dunningDate;
    }

    /**
     * @return the payedDate
     */
    public Calendar getPayedDate() {
        return payedDate;
    }

    /**
     * @param payedDate the payedDate to set
     */
    public void setPayedDate(Calendar payedDate) {
        this.payedDate = payedDate;
    }

    /**
     * @return the fee
     */
    public int getFee() {
        return fee;
    }

    /**
     * @param fee the fee to set
     */
    public void setFee(int fee) {
        this.fee = fee;
    }

    /**
     * @return the dunningFee
     */
    public int getDunningFee() {
        return dunningFee;
    }

    /**
     * @param dunningFee the dunningFee to set
     */
    public void setDunningFee(int dunningFee) {
        this.dunningFee = dunningFee;
    }

    /**
     * @return the payed
     */
    public boolean isPayed() {
        return payed;
    }

    /**
     * @param payed the payed to set
     */
    public void setPayed(boolean payed) {
        this.payed = payed;
    }
    
}
