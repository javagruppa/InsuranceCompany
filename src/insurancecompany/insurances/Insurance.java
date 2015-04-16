/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package insurancecompany.insurances;

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
    /** The date this insurance was created. */
    private Date date;
    /** The insurance amount of this insurance. */
    private int insuranceAmount;
    /** The yearly insurance premium of this insurance. */
    private int insurancePremium;
    /** List containing all insurance conditions of this insurance. */
    private List<String> insuranceConditions;
    
    /**
     * Empty constructor.
     */
    public Insurance() {
        this.active = false;
        this.date = new Date();
        this.insuranceConditions = new ArrayList<>();
    }
    
    /**
     * Adds an insurance condition to the list insuranceConditions.
     * @param insuranceCondition insurance condition to be appended to this list
     * @return true if this list changed as a result of the call
     */
    public boolean addInsuranceCondition(String insuranceCondition) {
        return insuranceConditions.add(insuranceCondition);
    }
    
    /**
     * Deletes an insurance condition from the list insuranceConditions.
     * @param insuranceCondition insurance condition to be removed from this 
     * list
     * @return true if this list changed as a result of the call
     */
    public boolean deleteInsuranceCondition(String insuranceCondition) {
        return insuranceConditions.removeIf(i -> i.equals(insuranceCondition));
    }
    
    /**
     * Sets an activity status to the insurance.
     * @param active activity status to be set to this insurance
     */
    public void setActive(boolean active) {
        this.active = active;
    }
    
    /**
     * Sets an insurance amount to the insurance.
     * @param insuranceAmount insurance amount to be set to this insurance
     */
    public void setInsuranceAmount(int insuranceAmount) {
        this.insuranceAmount = insuranceAmount;
    }
    
    /**
     * Sets an insurance premium to the insurance.
     * @param insurancePremium insurance premium to be set to this insurance
     */
    public void setInsurancePremium(int insurancePremium) {
        this.insurancePremium = insurancePremium;
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
            out.append("\nForsikringen er aktiv.");
        } else {
            out.append("\nForsikringen er inaktiv.");
        }
        out.append("\nDato: ").append(dateFormat.format(date));
        out.append("\nForsikringsbeløp: ").append(insuranceAmount);
        out.append("\nÅrlig forsikringspremie: ").append(insurancePremium);
        out.append("Forsikringsbetingelser: ");
        insuranceConditions.forEach(i -> out.append("\n").append(i));
        // Returns the string.
        return out.toString();
    }
}