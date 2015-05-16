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
    public static final double FIXED_DUNNING_CHARGE = 20.00;
    private static int nextBillingId = 1000000;
    private static String billIdFileName = "src/insurancecompany/resources/nextidnumbers/billId.dta";
    
    
    /** Unique billing id used to identify a specific bill.*/
    private int billId;
    /** Customer id used to identify the customer this bill belongs to.*/
    private int customerId;
    /** Insurance id used to identify the insurance that paid out this bill.*/
    private int insuranceId;
    /** Date of when the bill is issued. */
    private Calendar isuedDate;
    /** Date of when the bill is due to be paid.*/
    private Calendar dueDate;
    /** Date of when the dunning is due to paid.*/
    private Calendar dunningDate;
    /** Date of when the payment was received.*/
    private Calendar payedDate;
    /** Sum of the fee for this bill.*/
    private double fee; // NOR: Gebyr
    /** Total sum of the dunning charge for this bill.*/
    private double dunningCharge; // NOR: Purregebyr
    /** Boolean representing whether the bill is paid or not.*/
    private boolean paid;
    
    public Bill(double fee, int customerId, int insuranceId) {
        billId = nextBillingId++;
        this.fee = fee;
        this.customerId = customerId;
        this.insuranceId = insuranceId;
        isuedDate = Calendar.getInstance();
        dueDate = Calendar.getInstance();
        dueDate.add(Calendar.DAY_OF_YEAR, FIXED_DUE_DAYS);
        dunningCharge = 0;
        paid = false;
    }
    
    public void increaseDunningFee(double sum) {
        setDunningCharge(getDunningCharge() + sum);
    }
    
    public void payBill() {
        setPayedDate(Calendar.getInstance());
        setPaid(true);
    }
    
    public static void saveNextIdToFile() throws IOException {
        try (DataOutputStream dos = new DataOutputStream(
                new BufferedOutputStream(
                        new FileOutputStream(billIdFileName)))) {
            dos.writeInt(nextBillingId);
        }
    }
    
    public static void readNextIdFromFile() throws IOException {
        try (DataInputStream dis = new DataInputStream(
                new BufferedInputStream(
                        new FileInputStream(billIdFileName)))) {
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
    public double getFee() {
        return fee;
    }

    /**
     * @param fee the fee to set
     */
    public void setFee(double fee) {
        this.fee = fee;
    }

    /**
     * @return the dunningCharge
     */
    public double getDunningCharge() {
        return dunningCharge;
    }

    /**
     * @param dunningCharge the dunningCharge to set
     */
    public void setDunningCharge(double dunningCharge) {
        this.dunningCharge = dunningCharge;
    }

    /**
     * @return the paid
     */
    public boolean isPaid() {
        return paid;
    }

    /**
     * @param paid the paid to set
     */
    public void setPaid(boolean paid) {
        this.paid = paid;
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

    /**
     * @return the insuranceId
     */
    public int getInsuranceId() {
        return insuranceId;
    }
    
}
