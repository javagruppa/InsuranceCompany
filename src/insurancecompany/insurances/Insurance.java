/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package insurancecompany.insurances;

import insurancecompany.people.Customer;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Sindre
 */
public abstract class Insurance {
    /** Whether this insurance is active or not. */
    private boolean active;
    /** The customer who owns this insurance. */
    private Customer customer;
    /** The date this insurance was created. */
    private Date date;
    /** The insurance amount of this insurance. */
    private int amount;
    /** The yearly insurance premium of this insurance. */
    private int premium;
    /** List containing all insurance conditions of this insurance. */
    private List<String> conditions;
    
    /**
     * Constructor initializing active, customer, date and conditions of this 
     * insurance.
     * @param customer customer who owns this insurance
     */
    public Insurance(Customer customer) {
        this.active = false;
        this.customer = customer;
        this.date = new Date();
        this.conditions = new ArrayList<>();
    }
    
    /**
     * Adds an insurance condition to the list conditions.
     * @param condition insurance condition to be appended to this list
     * @return true if this list changed as a result of the call
     */
    public boolean addCondition(String condition) {
        return conditions.add(condition);
    }
    
    /**
     * Deletes an insurance condition from the list conditions.
     * @param condition insurance condition to be removed from this list
     * @return true if this list changed as a result of the call
     */
    public boolean deleteCondition(String condition) {
        return conditions.removeIf(i -> i.equals(condition));
    }
    
    /**
     * Returns the customer of this insurance.
     * @return customer who owns this insurance
     */
    public Customer getCustomer() {
        return customer;
    }
    
    /**
     * Returns the insurance premium of this insurance.
     * @return insurance premium of this insurance
     */
    public int getPremium() {
        return premium;
    }
    
    /**
     * Sets an activity status to this insurance.
     * @param active activity status to be set to this insurance
     */
    public void setActive(boolean active) {
        this.active = active;
    }
    
    /**
     * Sets an insurance amount to this insurance.
     * @param amount insurance amount to be set to this insurance
     */
    public void setAmount(int amount) {
        this.amount = amount;
    }
    
    /**
     * Sets an insurance premium to this insurance.
     * @param premium insurance premium to be set to this insurance
     */
    public void setPremium(int premium) {
        this.premium = premium;
    }
    
    /**
     * Returns a string representation of this insurance. The string
     * representation consists of each field with a short description separated
     * by a new line.
     * @return a string representation of this insurance
     */
    @Override
    public String toString() {
        // Creates a StringBuilder which will be returned at the end of the 
        // method.
        StringBuilder out = new StringBuilder();
        // Creates a DateFormat to format the field date.
        DateFormat dateFormat = DateFormat.getInstance();
        // Appends the fields with appropriate sentences.
        if(active) {
            out.append("Forsikringen er aktiv.");
        } else {
            out.append("Forsikringen er inaktiv.");
        }
        out.append("\nDato: ").append(dateFormat.format(date));
        out.append("\nForsikringsbeløp: ").append(amount);
        out.append("\nÅrlig forsikringspremie: ").append(premium);
        out.append("\nForsikringsbetingelser: ");
        conditions.forEach(i -> out.append("\n").append(i));
        // Returns the string.
        return out.toString();
    }
}