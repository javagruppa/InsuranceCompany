package insurancecompany.model.insurances;

import insurancecompany.misc.InsuranceType;
import insurancecompany.misc.coverages.BoatInsuranceCoverage;
import insurancecompany.model.vehicles.Boat;
import java.io.Serializable;

/**
 * Class BoatInsurance. This is the insurance for boats.
 * 
 * @author Sindre
 * @author Carl
 */
public class BoatInsurance extends Insurance implements Serializable {
    
    /** SerialVersionUID used to identify this class for object IO. */
    private static final long serialVersionUID = 1L;
    /** The boat this insurance is for. */
    private Boat boat;
    /** The coverage of this insurance. */
    private BoatInsuranceCoverage coverage;
    
    /**
     * Constructs a new boat insurance with the specified parameters.
     * Active is set to true. Date is set to the current date.
     * InsuranceId is automatically set to nextInsuranceId.
     * 
     * @param boat The boat this insurance is for.
     * @param coverage The coverage of this insurance.
     * @param customerId The id of the customer who owns this insurance.
     * @param excess The excess of this insurance.
     */
    public BoatInsurance(Boat boat, BoatInsuranceCoverage coverage, 
            int customerId, int excess) {
        super(customerId, excess);
        this.boat = boat;
        this.coverage = coverage;
        calculatePremium();
    }
    
    // GET METHODS
    
    /** @return The coverage of this insurance. */
    @Override
    public BoatInsuranceCoverage getCoverage() {
        return coverage;
    }
    
    /** @return The type of insurance in form of a String. */
    @Override
    public String getName() {
        return InsuranceType.BOAT_INSURANCE.toString();
    }
    
    /** @return The boat this insurance is for. */
    public Boat getBoat() {
        return boat;
    }
    
    // CALCULATE PREMIUM METHODS

    /**
     * Calculates and sets the total premium of this insurance, based on the 
     * value, coverage, excess, engine effect and whether the boat insured
     * has an alarm.
     */
    public void calculatePremium() {
	// Calculates the total price for this insurance:
	double totalPrice = valueCost() + effectCost() - excessDrop() 
                + coverage.getPricing();
	// Drops the price of the insurance if the boat insured has an alarm:
        if (boat.getAlarm()) {
            totalPrice = totalPrice * 0.85;
	}
        // Sets the premium of this insurance to the calculated price:
        setPremium((int) totalPrice); 
        calculateMonthlyPremium();
    }
    
    /**
     * Generates and returns the value cost of this insurance, based on the 
     * value of the boat this insurance is for.
     * 
     * @return The value cost of this insurance.
     */
    private int valueCost() {
	// The basic cost of this insurance:
        int result = 0;
        // The value of the boat this insurance is for:
        int value = boat.getValue();
        // Sets the basic cost based on the price range the boats value is 
        // within:
	if (value <= 25000) {
            result = 1000;
	} else if (value > 25000 && value <= 50000) {
            result = 1500;
	} else if (value > 50000 && value <= 100000) {
            result = 2000;
	} else if (value > 100000 && value <= 250000) {
            result = 5000;
	} else if (value > 250000 && value <= 750000) {
            result = 7000;
	} else if (value > 750000 && value <= 1500000) {
            result = 10000;
	} else if (value > 1500000 && value <= 3000000) {
            result = 12500;
	} else if (value > 3000000) {
            result = 25000;
	}
        // Returns the value cost of this insurance:
        return result;
    }

    /**
     * Calculates and returns the engine effect cost of this insurance due to 
     * the engine effect of the boat insured.
     * 
     * @return The engine effect cost of this insurance.
     */
    private int effectCost() {
        // The extra cost based on the effect of the boat:
        int result = 0;
	// The engine effect of the boat this insurance is for:
        int effect = boat.getEngineEffect();
	// Calculates the extra cost of this insurance based on what range
        // the engine effect of the boat insured is within:
        if (effect <= 50) {
            result = 250;
	} else if (effect > 50 && effect <= 100) {
            result = 500;
	} else if (effect > 100 && effect <= 250) {
            result = 1500;
	} else if (effect > 250) {
            result = 3000;
	}
        // Returns the engine effect cost of this insurace:
        return result;
}

    /**
     * Calculates and returns the drop in price for this insurance based on how
     * high the excess is set. The higher the excess, the more money is saved.
     * 
     * @return The drop in price based on the excess of this insurance.
     */
    private int excessDrop() {
        // The price drop of this insurance based on the excess:
        int result = 0;
	// The excess of this insurance:
        int excess = getExcess();
        // Calculates the price drop of the insurance based on the excess.
        // If excess is set at 0, the price will rise instead of dropping:
	if (excess == 0) {
            result = -2000;
	} else if (excess > 0 && excess <= 5000) {
            result = excess / 5;
	} else if (excess > 5000 && excess <= 10000) {
            result = excess / 4;
	} else if (excess > 10000) {
            result = excess / 3;
	}
        // Returns the price drop:
        return result;
    }
    
    // TO STRING METHOD
    
    /**
     * Returns a string representation of this insurance. The string
     * representation consists of each field with a short description separated
     * by a new line.
     * 
     * @return A string representation of this insurance.
     */
    @Override
    public String toString() {
        // Creates a StringBuilder which will be returned at the end of the 
        // method:
        StringBuilder result = new StringBuilder();
        // Appends the fields with appropriate sentences:
        result.append("BÅTFORSIKRING");
        result.append("\n").append(super.toString());
        result.append("\nDekning: ").append(coverage.toString());
        // Returns the string:
        return result.toString();
    }
}