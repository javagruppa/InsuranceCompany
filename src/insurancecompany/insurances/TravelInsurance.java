/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package insurancecompany.insurances;

import insurancecompany.people.Customer;

/**
 *
 * @author Sindre
 */
public class TravelInsurance extends Insurance {
    /** The insurance area of this insurance. */
    private String insuranceArea;
    /** The insurance sum of this insurance. */
    private int insuranceSum;
    
    /**
     * Constructor.
     * @param customer the customer who owns this insurance
     * @param insuranceArea the insurance area of this insurance
     * @param insuranceSum the insurance sum of this insurance
     */
    public TravelInsurance(Customer customer, String insuranceArea, 
            int insuranceSum) {
        super(customer);
        this.insuranceArea = insuranceArea;
        this.insuranceSum = insuranceSum;
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
        // Appends the fields with appropriate sentences.
        out.append("REISEFORSIKRING");
        out.append("\n").append(super.toString());
        out.append("\nForsikringsområde: ").append(insuranceArea);
        out.append("\nForsikringssum: ").append(insuranceSum);
        // Returns the string.
        return out.toString();
    }
}