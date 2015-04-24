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
    /** Whether this insurance is active or not. */
    private boolean active;
    /** The id of the customer who owns this insurance. */
    private int customerId;
    /** The date this insurance was created. */
    private Date date;
    /** The excess of this insurance. */
    private int excess;
    /** The yearly insurance premium of this insurance. */
    private int premium;
    
    /**
     * Constructs a new insurance with the specified customerId and excess. 
     * Active is set to true. Date is set to the current date.
     * 
     * @param customerId the id of the customer who owns this insurance
     * @param excess the excess of this insurance
     */
    public Insurance(int customerId, int excess) {
        this.active = true;
        this.customerId = customerId;
        this.date = new Date();
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