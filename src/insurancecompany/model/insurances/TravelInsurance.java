package insurancecompany.model.insurances;

import insurancecompany.misc.coverages.TravelInsuranceCoverage;
import java.io.Serializable;

/**
 *
 * @author Sindre
 * @author Carl
 */
public class TravelInsurance extends Insurance implements Serializable {
    
    /** SerialVersionUID used to identify this class for object IO. */
    private static final long serialVersionUID = 1L;
    /** The coverage of this insurance. */
    private TravelInsuranceCoverage coverage;
    
    /**
     * Constructs a new travel insurance with the specified parameters. Active 
     * is set to true. Date is set to the current date. InsuranceId is 
     * automatically set to nextInsuranceId.
     * 
     * @param coverage The coverage of this insurance.
     * @param customerId The id of the customer who owns this insurance.
     * @param excess The excess of this insurance.
     */
    public TravelInsurance(TravelInsuranceCoverage coverage, int customerId, 
            int excess) {
        super(customerId, excess);
        this.coverage = coverage;
    }
    
    // GET METHODS
    
    /** @return The coverage of this insurance. */
    @Override
    public TravelInsuranceCoverage getCoverage() {
        return coverage;
    }
    
    /** @return The type of insurance in form of a String. */
    @Override
    public String getName() {
        return "Reiseforsikring";
    }
    
    // CALCULATE PREMIUM METHODS

    /**
     * Calculates and sets the premium of this insurance, based on the basic 
     * price and the excess drop.
     */
    public void calculatePremium(){
	int newPremium = coverage.getPricing() - excessDrop();
	setPremium(newPremium);
    }
    
    /**
     * Calculates and returns the price drop of this insurance based
     * on the set excess.
     * 
     * @return The drop in price due to excess.
     */
    private int excessDrop() {
	int excess = getExcess();
	int result = 0;
	if (excess == 0) {
            result = -2000;
	} else if (excess > 0 && excess <= 1000) {
            result = excess / 10;
	} else if (excess > 1000 && excess <= 2000) {
            result = excess / 9;
	} else if (excess > 2000 && excess <= 3500) {
            result = excess / 8;
	} else if (excess > 3500 && excess <= 5000) {
            result = excess / 7;
	}
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
        // method.
        StringBuilder result = new StringBuilder();
        // Appends the fields with appropriate sentences.
        result.append("REISEFORSIKRING");
        result.append("\n").append(super.toString());
        result.append("\nDekning: ").append(coverage);
        // Returns the string.
        return result.toString();
    }
}
