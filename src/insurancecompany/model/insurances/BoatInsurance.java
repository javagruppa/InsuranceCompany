/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package insurancecompany.model.insurances;

import insurancecompany.misc.coverages.BoatInsuranceCoverage;
import insurancecompany.model.vehicles.Boat;
import java.io.Serializable;

/**
 *
 * @author Sindre
 */
public class BoatInsurance extends Insurance implements Serializable {
    private static final long serialVersionUID = 42L;
    
    /** The boat this insurance is for. */
    private Boat boat;
    /** The coverage of this insurance. */
    private BoatInsuranceCoverage coverage;
    
    /**
     * Constructs a new boat insurance with the specified boat, coverage, 
     * customerId, excess and hasAlarm. Active is set to true. Date is set to 
     * the current date. InsuranceId is automatically set to nextInsuranceId.
     * 
     * @param boat the boat this insurance is for
     * @param coverage the coverage of this insurance
     * @param customerId the id of the customer who owns this insurance
     * @param excess the excess of this insurance
     * @param hasAlarm whether the car this insurance is for has an alarm 
     * or not
     */
    public BoatInsurance(Boat boat, int customerId, 
            BoatInsuranceCoverage coverage, int excess, boolean hasAlarm) {
        super(customerId, excess);
        this.boat = boat;
        this.coverage = coverage;
    }
    
    /**
     * Generates the basic cost of this insurance, based on the value of the
     * boat this insurance is for.
     * Returns the basic cost as an int value
     * @return the basic cost of this insurance
     */
    public int basicCost(){
	// The basic cost of this insurance, initialized at 0
        int basic = 0;
        // The value of the boat this insurance is for.
        int worth = boat.getValue();
        
        // Sets the basic cost based on the price range the boats value
        // is within
	if (worth <= 25000){
	basic = 1000;
	}
	else if (worth > 25000 && worth <= 50000){
	basic = 1500;
	}
	else if (worth > 50000 && worth <= 100000){
	basic = 2000;
	}
	else if (worth > 100000 && worth <= 250000){
	basic = 5000;
	}
	else if (worth > 250000 && worth <= 750000){
	basic = 7000;
	}
	else if (worth > 750000 && worth <= 1500000){
	basic = 10000;
	}
	else if (worth > 1500000 && worth <= 3000000){
	basic = 12500;
	}
	else if (worth > 3000000){
	basic = 25000;
	}
        // Returns the set basic cost of this insurance
        return basic;
    }

    /**
     * Calculates the extra costs of this insurance due to the engine effect of
     * the boat this insurance is for. Returns this as an int value.
     * @return the extra engine cost
     */
    public int effectCost(){
	// The engine effect of the boat this insurance is for.
        int effect = boat.getEngineEffect();
        // The extra cost based on the effect of the boat. Initialized at 0
        int effectC = 0;

	// Calculates the effect cost of this insurance based on what range
        // the engine effect of the boat insured is within.
        if (effect <= 50){
	effectC = 250;
	}
	else if (effect > 50 && effect <= 100){
	effectC = 500;
	}
	else if (effect > 100 && effect <= 250){
	effectC = 1500;
	}
	else if (effect > 250){
	effectC = 3000;
	}
        // Returns the set effect cost
        return effectC;
}

    /**
     * Calculates the drop in price for this insurance based on how high the
     * excess is set. The higher the excess, the more money is saved. Returns
     * the price drop as an int value.
     * @return the drop in price based on the excess on this insurance
     */
    public int excessDrop(){
	// The excess set for this insurance
        int excess = getExcess();
        // The price drop of this insurance based on the set excess.
        // Initialized at 0
        int excessSaving = 0;

        // Calculates the price drop of the insurance based on the set excess.
        // If excess is set at 0, the price will rise instead of dropping.
	if(excess == 0){
	excessSaving = -2000;
	}
	else if(excess > 0 && excess <= 5000){
	excessSaving = excess / 5;
	}
	else if(excess > 5000 && excess <= 10000){
	excessSaving = excess / 4;
	}
	else if(excess > 10000){
	excessSaving = excess / 3;
	}
        // Returns the set price drop
        return excessSaving;
}

    /**
     * Calculates and sets the total premium of this insurance, based on the the
     * coverage of the insurance, whether the boat insured has an alarm, how
     * high the excess is set, and the engine effect.
     */
    public void totalPremium(){
	// Total price for this insurance
        int totalP;
        // Calculated premium of the insurance after all savings are calculated
        double newPremium;
        // The premium to be set for this insurance, as an integer value.
        int setPremium;
        // Whether the boat insured has an alarm or not.
        boolean hasAlarm = boat.getAlarm();
        
        // Calculates total price for this insurance
	totalP = basicCost() + effectCost() - excessDrop()
                + coverage.getPricing();

	// Drops the price of the insurance if the boat insured has an alarm.
        if (hasAlarm){
	newPremium = totalP * 0.85;
	}
	else{
	newPremium = totalP;
	}
        
        // Changes the new premium from a double value to an integer value
        setPremium = (int)newPremium;
	// Sets the premium of this insurance to the calculated price
        setPremium(setPremium); 
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
        result.append("BÅTFORSIKRING");
        result.append("\n").append(super.toString());
        result.append("\nType: ").append(coverage.toString());
        // Returns the string.
        return result.toString();
    }
}
