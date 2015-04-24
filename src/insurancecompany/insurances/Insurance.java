/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package insurancecompany.insurances;

import java.text.DateFormat;
import java.util.Date;

/**
 *
 * @author Sindre
 */
public abstract class Insurance {
    /** The insurance id of the insurance created next */
    private static int nextInsuranceId = 1000000;
    /** Whether this insurance is active or not. */
    private boolean active;
    /** The id of the customer who owns this insurance. */
    private int customerId;
    /** The date this insurance was created. */
    private Date date;
    /** The excess of this insurance. */
    private int excess;
    /** Unique insurance id representing this insurance. */
    private int insuranceId;
    /** The yearly insurance premium of this insurance. */
    private int premium;
    
    /**
     * Constructs a new insurance with the specified customerId and excess. 
     * Active is set to true. Date is set to the current date. InsuranceId is 
     * automatically set to nextInsuranceId.
     * 
     * @param customerId the id of the customer who owns this insurance
     * @param excess the excess of this insurance
     */
    public Insurance(int customerId, int excess) {
        this.active = true;
        this.customerId = customerId;
        this.date = new Date();
        this.insuranceId = nextInsuranceId++;
    }
    
    /**
     * Indicates whether some other insurance is equal to this one. The result 
     * is true if and only if the argument is not null and is an Insurance 
     * object that contains the same insuranceId value as this object.
     * 
     * @param obj the object ot compare with
     * @return true if the objects are the same; false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if(obj != null && obj instanceof Insurance) {
            Insurance other = (Insurance) obj;
            return insuranceId == other.getInsuranceId();
        } else {
            return false;
        }   
    }
    
    /**
     * Returns whether this insurance is active or not.
     * 
     * @return whether this insurance is active or not
     */
    public boolean getActive() {
        return active;
    }
    
    /**
     * Returns the customer id of this insurance.
     * 
     * @return the id of the customer who owns this insurance
     */
    public int getCustomerId() {
        return customerId;
    }
    
    /**
     * Returns the excess of this insurance.
     * 
     * @return the excess of this insurance
     */
    public int getExcess() {
        return excess;
    }
    
    /**
     * Returns the unique insurance id representing this insurance.
     * 
     * @return the unique insurance id representing this insurance
     */
    public int getInsuranceId() {
        return insuranceId;
    }
    
    /**
     * Returns the yearly insurance premium of this insurance.
     * 
     * @return the yearly insurance premium of this insurance
     */
    public int getPremium() {
        return premium;
    }
    
    /**
     * Sets an activity status to this insurance.
     * 
     * @param active the activity status to be set to this insurance
     */
    public void setActive(boolean active) {
        this.active = active;
    }
    
    /**
     * Sets a yearly insurance premium to this insurance.
     * 
     * @param premium the yearly insurance premium to be set to this insurance
     */
    public void setPremium(int premium) {
        this.premium = premium;
    }
    
    /**
     * Returns a string representation of this insurance. The string
     * representation consists of each field with a short description separated
     * by a new line.
     * 
     * @return a string representation of this insurance
     */
    @Override
    public String toString() {
        // Creates a StringBuilder which will be returned at the end of the 
        // method.
        StringBuilder result = new StringBuilder();
        // Creates a DateFormat to format the field date.
        DateFormat dateFormat = DateFormat.getInstance();
        // Appends the fields with appropriate sentences.
        result.append("Forsikringen er ").append(active ? "aktiv" : "inaktiv");
        result.append("\n\nDato: ").append(dateFormat.format(date));
        result.append("\nÅrlig forsikringspremie: ").append(premium);
        result.append("\nEgenandel: ").append(excess);
        // Returns the string.
        return result.toString();
    }
}