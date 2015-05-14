package insurancecompany.model.insurances;

import insurancecompany.misc.coverages.BoatInsuranceCoverage;
import insurancecompany.model.vehicles.Boat;
import java.io.Serializable;

/**
 * Class BoatInsurance. This is the insurance for boats.
 * 
 * @author Carl
 * @author Sindre
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
    public BoatInsurance(Boat boat, int customerId, 
            BoatInsuranceCoverage coverage, int excess) {
        super(customerId, excess);
        this.boat = boat;
        this.coverage = coverage;
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
        return "Båtforsikring";
    }
    
    // CALCULATE PREMIUM METHODS
    
    /**
     * Generates and returns the basic cost of this insurance, based on the 
     * value of the boat this insurance is for.
     * 
     * @return The basic cost of this insurance.
     */
    public int basicCost() {
	// The basic cost of this insurance, initialized at 0:
        int basic = 0;
        // The value of the boat this insurance is for:
        int value = boat.getValue();
        // Sets the basic cost based on the price range the boats value is 
        // within:
	if (value <= 25000) {
            basic = 1000;
	} else if (value > 25000 && value <= 50000) {
            basic = 1500;
	} else if (value > 50000 && value <= 100000) {
            basic = 2000;
	} else if (value > 100000 && value <= 250000) {
            basic = 5000;
	} else if (value > 250000 && value <= 750000) {
            basic = 7000;
	} else if (value > 750000 && value <= 1500000) {
            basic = 10000;
	} else if (value > 1500000 && value <= 3000000) {
            basic = 12500;
	} else if (value > 3000000) {
            basic = 25000;
	}
        // Returns the basic cost of this insurance:
        return basic;
    }

    /**
     * Calculates and returns the extra costs of this insurance due to the
     * engine effect of the boat insured.
     * 
     * @return The extra engine cost of this insurance.
     */
    public int effectCost() {
        // The extra cost based on the effect of the boat. Initialized at 0:
        int effectC = 0;
	// The engine effect of the boat this insurance is for:
        int effect = boat.getEngineEffect();
	// Calculates the extra cost of this insurance based on what range
        // the engine effect of the boat insured is within:
        if (effect <= 50) {
            effectC = 250;
	} else if (effect > 50 && effect <= 100) {
            effectC = 500;
	} else if (effect > 100 && effect <= 250) {
            effectC = 1500;
	} else if (effect > 250) {
            effectC = 3000;
	}
        // Returns the extra engine cost of this insurace:
        return effectC;
}

    /**
     * Calculates and returns the drop in price for this insurance based on how
     * high the excess is set. The higher the excess, the more money is saved.
     * 
     * @return The drop in price based on the excess of this insurance.
     */
    public int excessDrop() {
        // The price drop of this insurance based on the excess. Initialized 
        // at 0:
        int excessSaving = 0;
	// The excess of this insurance:
        int excess = getExcess();
        // Calculates the price drop of the insurance based on the excess.
        // If excess is set at 0, the price will rise instead of dropping:
	if (excess == 0) {
            excessSaving = -2000;
	} else if (excess > 0 && excess <= 5000) {
            excessSaving = excess / 5;
	} else if (excess > 5000 && excess <= 10000) {
            excessSaving = excess / 4;
	} else if (excess > 10000) {
            excessSaving = excess / 3;
	}
        // Returns the price drop:
        return excessSaving;
}

    /**
     * Calculates and sets the total premium of this insurance, based on the the
     * coverage of the insurance, whether the boat insured has an alarm, how
     * high the excess is set, and the engine effect.
     */
    public void totalPremium() {
	// Total price for this insurance:
        int totalP;
        // Calculated premium of the insurance after all savings are calculated:
        double newPremium;
        // The premium to be set for this insurance, as an integer value:
        int setPremium;
        // Whether the boat insured has an alarm or not:
        boolean alarm = boat.getAlarm();
        // Calculates total price for this insurance:
	totalP = basicCost() + effectCost() - excessDrop() 
                + coverage.getPricing();
	// Drops the price of the insurance if the boat insured has an alarm:
        if (alarm) {
            newPremium = totalP * 0.85;
	} else {
            newPremium = totalP;
	}
        // Changes the new premium from a double value to an integer value:
        setPremium = (int) newPremium;
	// Sets the premium of this insurance to the calculated price:
        setPremium(setPremium); 
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
