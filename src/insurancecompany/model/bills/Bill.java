/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package insurancecompany.model.bills;

import java.io.*;
import java.util.Calendar;

/**
 *
 * @author André
 */
public class Bill implements Serializable {
    private static final long serialVersionUID = 1L;
    
    /** Constant representing the number of days until each due date from issue, as well as days to dunningDate */
    public static final int FIXED_DUE_DAYS = 14;
    private static int nextBillingId = 1000000;
    private static String billingIdFileName = "insurancecompany/resources/nextidnumbers/billingId.dta";
    
    
    /** Unique billing id used to identify a specific bill.*/
    private int billId;
    /** Customer id used to identify the customer this bill belongs to.*/
    private int customerId;
    /** Insurance id used to identify the insurance that payed out this bill.*/
    private int insuranceId;
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
    /** Total sum of the dunning charge for this bill.*/
    private int dunningCharge; // NOR: Purregebyr
    /** Boolean representing whether the bill is payed or not.*/
    private boolean payed;
    
    public Bill(int fee, int customerId, int insuranceId) {
        billId = nextBillingId++;
        this.fee = fee;
        this.customerId = customerId;
        this.insuranceId = insuranceId;
        isuedDate = Calendar.getInstance();
        dueDate = Calendar.getInstance();
        dueDate.add(Calendar.DAY_OF_YEAR, FIXED_DUE_DAYS);
        dunningCharge = 0;
        payed = false;
    }
    
    public void increaseDunningFee(int sum) {
        setDunningCharge(getDunningCharge() + sum);
    }
    
    public void payBill() {
        setPayedDate(Calendar.getInstance());
        setPayed(true);
    }
    
    public static void saveNextIdToFile() throws IOException {
        try (DataOutputStream dos = new DataOutputStream(
                new BufferedOutputStream(
                        new FileOutputStream(billingIdFileName)))) {
            dos.writeInt(nextBillingId);
        }
    }
    
    public static void readNextIdFromFile() throws IOException {
        try (DataInputStream dis = new DataInputStream(
                new BufferedInputStream(
                        new FileInputStream(billingIdFileName)))) {
                nextBillingId = dis.readInt();
        }
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
     * @return the dunningCharge
     */
    public int getDunningCharge() {
        return dunningCharge;
    }

    /**
     * @param dunningCharge the dunningCharge to set
     */
    public void setDunningCharge(int dunningCharge) {
        this.dunningCharge = dunningCharge;
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

    /**
     * @return the billId
     */
    public int getBillId() {
        return billId;
    }

    /**
     * @return the customerId
     */
    public int getCustomerId() {
        return customerId;
    }
    
}
