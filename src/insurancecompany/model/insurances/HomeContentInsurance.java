package insurancecompany.model.insurances;

import insurancecompany.misc.coverages.HomeContentInsuranceCoverage;
import insurancecompany.misc.hometypes.HomeType;
import insurancecompany.model.properties.Property;
import java.io.Serializable;

/**
 *
 * @author Sindre
 * @author Carl
 */
public class HomeContentInsurance extends Insurance 
        implements Serializable {
    
    /** SerialVersionUID used to identify this class for object IO. */
    private static final long serialVersionUID = 1L;
    /** The insurance amount of this insurance. */
    private int amount;
    /** The coverage of this insurance. */
    private HomeContentInsuranceCoverage coverage;
    /** The property this insurance is for. */
    private Property property;
    /** The type of home this insurance is for. */
    private HomeType type;
    
    /**
     * Constructs a new home content insurance with the specified parameters. 
     * Active is set to true. Date is set to the current date. InsuranceId is 
     * automatically set to nextInsuranceId.
     * 
     * @param amount The insurance amount of this insurance.
     * @param coverage The coverage of this insurance.
     * @param customerId The id of the customer who owns this insurance.
     * @param excess The excess of this insurance.
     * @param property The property this insurance is for.
     * @param type The type of home this insurance is for.
     */
    public HomeContentInsurance(int amount, 
            HomeContentInsuranceCoverage coverage, int customerId, int excess, 
            Property property, HomeType type){
        super(customerId, excess);
        this.amount = amount;
        this.coverage = coverage;
        this.property = property;
        this.type = type;
    }
    
    // GET METHODS
    
    /** @return The coverage of this insurance. */
    @Override
    public HomeContentInsuranceCoverage getCoverage() {
        return coverage;
    }
    
    /** @return The type of insurance in form of a String. */
    @Override
    public String getName() {
        return "Innboforsikring";
    }
    
    /** @return The property this insurance is for. */
    public Property getProperty() {
        return property;
    }
    
    // CALCULATE PREMIUM METHODS

    /**
     * Calculates the premium of this insurance, and then sets the premium.
     */
    public void calculatePremium() {
	int newPremium = coverage.getPricing() + (int)amountCost() - 
                excessDrop();
	setPremium(newPremium);
    }
    
    /**
     * Calculates and returns the amount cost of this insurance, based on the 
     * value.
     * 
     * @return The amount cost of this insurance.
     */
    private double amountCost() {
	double result = 0;
	if (amount <= 150000) {
            result = amount * 0.004;
	} else if (amount > 150000 && amount <= 300000) {
            result = amount * 0.0055;
	} else if (amount > 300000 && amount <= 500000) {
            result = amount * 0.006;
	} else if (amount > 500000 && amount <= 1500000) {
            result = 3500;
	} else if (amount > 1500000) {
            double extra = amount * 0.001;
            result = extra + 3500;
	}
	return result;
    }

    /**
     * Calculates and returns the drop in price for this insurance based on
     * the chosen excess
     * 
     * @return The drop in price due to excess.
     */
    private int excessDrop() {
	int excess = getExcess();
	int drop = 0;
	if (excess == 0) {
            drop = -2000;
	} else if (excess > 0 && excess <= 1000) {
            drop = excess / 10;
	} else if (excess > 1000 && excess <= 2000) {
            drop = excess / 9;
	} else if (excess > 2000 && excess <= 3500) {
            drop = excess / 8;
	} else if (excess > 3500 && excess <= 5000) {
            drop = excess / 7;
	} else if (excess > 5000 && excess <= 10000) {
            drop = 800;
	} else if (excess > 10000 && excess <= 20000) {
            drop = 1000;
	}
	return drop;
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
        // method.
        StringBuilder result = new StringBuilder();
        // Appends the fields with appropriate sentences.
        result.append("INNBOFORSIKRING HJEM");
        result.append("\n").append(super.toString());
        result.append("\nDekning: ").append(coverage.toString());
        result.append("\nForsikringssum: ").append(amount);
        result.append("\nEiendomstype: ").append(type.toString());
        
        // Returns the string.
        return result.toString();
    }
}