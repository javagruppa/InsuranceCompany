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
     * Finds and returns the basic price of this insurance
     * @return the basic price
     */
    private int basicPrice(){
	int basicP = coverage.getPricing();
	return basicP;
    }   

    /**
     * Calculates and returns the price drop of this insurance based
     * on the set excess
     * @return the drop in price due to excess
     */
    private int excessDrop(){
	int excess = getExcess();
	int drop = 0;

	if (excess == 0){
		drop = -2000;
	}
	else if (excess > 0 && excess <= 1000){
		drop = excess / 10;
	}
	else if (excess > 1000 && excess <= 2000){
		drop = excess / 9;
	}
	else if (excess > 2000 && excess <= 3500){
		drop = excess / 8;
	}
	else if (excess > 3500 && excess <= 5000){
		drop = excess / 7;
	}
	
	return drop;
    }

    /**
     * Calculates and sets the premium of this insurance, based on:
     * The basic price
     * The excess drop
     */
    public void premium(){
	int newPremium = basicPrice() - excessDrop();
	setPremium(newPremium);
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
