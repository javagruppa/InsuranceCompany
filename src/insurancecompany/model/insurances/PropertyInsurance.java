/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package insurancecompany.model.insurances;

import java.io.Serializable;
/**
 *
 * @author Sindre
 */
public abstract class PropertyInsurance extends Insurance implements Serializable {
    private static final long serialVersionUID = 1L;
    
    /** The content insurance amount of this insurance. */
    private int contentAmount;
    
    /**
     * Constructs a new property insurance with the specified 
     * contentAmount, customerId and excess. Active is set to true. Date is set 
     * to the current date. InsuranceId is automatically set to nextInsuranceId.
     * 
     * @param customerId the id of the customer who owns this insurance
     * @param excess the excess of this insurance
     */
    public PropertyInsurance(int customerId, int excess) {
        super(customerId, excess);
        
    }
    
   
    
    /**
     * Returns the content insurance amount of this insurance.
     * 
     * @return the content insurance amount of this insurance
     */
    public int getContentAmount() {
        return contentAmount;
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
        // Appends the fields with appropriate sentences.
        result.append(super.toString());
        result.append("\nForsikringsbeløp for innbo: ").append(contentAmount);
        // Returns the string.
        return result.toString();
    }
}