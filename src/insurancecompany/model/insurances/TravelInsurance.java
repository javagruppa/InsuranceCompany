/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package insurancecompany.model.insurances;

import insurancecompany.misc.coverages.TravelInsuranceCoverage;
import java.io.Serializable;

/**
 *
 * @author Sindre
 */
public class TravelInsurance extends Insurance implements Serializable {
    private static final long serialVersionUID = 1L;
    
    /** The coverage of this insurance. */
    private TravelInsuranceCoverage coverage;
    /** The insurance area of this insurance. */
    private String insuranceArea;
    
    /**
     * Constructs a new travel insurance with the specified customerId, 
     * coverage, excess and insuranceArea. Active is set to true. Date is set 
     * to the current date. InsuranceId is automatically set to nextInsuranceId.
     * 
     * @param customerId the id of the customer who owns this insurance
     * @param coverage the coverage of this insurance
     * @param excess the excess of this insurance
     * @param insuranceArea the insurance area of this insurance
     */
    public TravelInsurance(int customerId, TravelInsuranceCoverage coverage,
            int excess, String insuranceArea) {
        super(customerId, excess);
        this.coverage = coverage;
        this.insuranceArea = insuranceArea;
    }
    
    /**
     * Returns the type of this insurance in form of a String.
     * @return 
     */
    public String getName() {
        return "Reiseforsikring";
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
        result.append("REISEFORSIKRING");
        result.append("\n").append(super.toString());
        result.append("\nType: ").append(coverage);
        result.append("\nForsikringsområde: ").append(insuranceArea);
        // Returns the string.
        return result.toString();
    }
    
    /**
     * Returns the coverage of this insurance.
     * @return 
     */
    @Override
    public Object getCoverage() {
        return coverage;
    }
}