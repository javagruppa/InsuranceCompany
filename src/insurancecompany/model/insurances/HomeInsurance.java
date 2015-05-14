package insurancecompany.model.insurances;

import insurancecompany.model.properties.Property;
import insurancecompany.misc.hometypes.HomeType;
import insurancecompany.misc.coverages.HomeInsuranceCoverage;
import java.io.Serializable;   
import java.util.Calendar;

/**
 *
 * @author Sindre
 * @author Carl
 */
public class HomeInsurance extends PropertyInsurance implements Serializable {
    
    /** SerialVersionUID used to identify this class for object IO. */
    private static final long serialVersionUID = 1L;
    /** The coverage of this insurance. */
    private HomeInsuranceCoverage coverage;
    /** The property this insurance is for. */
    private Property property;
    /** Whether or not this insurance covers rentals. */
    private boolean rental;
    /** The type of home this insurance is for. */
    private HomeType type;
    
    /**
     * Constructs a new home insurance with the specified parameters. Active is 
     * set to true. Date is set to the current date. InsuranceId is 
     * automatically set to nextInsuranceId.
     * 
     * @param coverage The coverage of this insurance.
     * @param customerId The id of the customer who owns this insurance.
     * @param excess The excess of this insurance.
     * @param property The property this insurance is for.
     * @param rental Whether or not this insurance covers rentals.
     * @param type The type of home this insurance is for.
     */
    public HomeInsurance(HomeInsuranceCoverage coverage, int customerId, 
            int excess, Property property, boolean rental, HomeType type) {
        super(customerId, excess);
        this.coverage = coverage;
        this.property = property;
        this.rental = rental;
        this.type = type;
    }
    
    // GET METHODS
    
    /** @return The coverage of this insurance. */
    @Override
    public HomeInsuranceCoverage getCoverage() {
        return coverage;
    }
    
    /** @return The type of insurance in form of a String. */
    @Override
    public String getName() {
        return "Husforsikring";
    }
    
    // CALCULATE PREMIUM METHODS
    
    /**
     * Calculates and sets the premium for this insurance based on if the house 
     * is to be rented out, the type of the insured property, the building 
     * material of the property and the year the property was built.
     */
    public void calculatePremium(){
        double rentalExtra = rental ? 1.15 : 1;
        // Multiplicator for the homes building material
	double MaterialMultiplicator = property.getMaterialMultiplicator();
        // Multiplicator for the homes building year
        double yearMultiplicator = buildingYearMultiplicator();
        // Price for what type of property is insured
	int typePrice = type.getPricing();
        // Base price. Price after excess drop (incl price for plus or basic)
	int baseprice = typePrice + coverage.getPricing() - excessDrop();
        // Total price. Base price including extra if the property is a rental
	double totalPrice = baseprice * rentalExtra;
        // Material price. Total price multiplied by material multiplicator
	double materialPrice = totalPrice * MaterialMultiplicator;
        // Final price. Material price multiplied by year multiplicator
        double finalprice = materialPrice * yearMultiplicator;
        // Converts the final price to an int value
        int setPremium = (int)finalprice;
        // Sets this insurances premium to the final price
	setPremium(setPremium);
    }
    
    /**
     * Calculates the drop in price for this insurance based on the excess.
     * 
     * @return The drop in price due to excess.
     */
    private int excessDrop() {
	int excess = getExcess();
	int result = 0;
	if (excess == 0) {
            result = -1000;
	} else if (excess > 0) {
            result = excess / 6;
	}
	return result;
    }
    
    /**
     * Sets and returns the price multiplicator for what year the property was
     * built.
     * 
     * @return The multiplicator as a double.
     */
    private double buildingYearMultiplicator(){
        double result = 1;
        int year = property.getYear();
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        if (currentYear - year <= 10) {
            result = 0.85;
        } else if (currentYear - year > 10 && currentYear - year <= 20) {
            result = 0.95;
        } else if (currentYear - year > 20 && currentYear - year <= 30) {
            result = 1.05;
        } else if (currentYear - year > 30 && currentYear - year <= 45) {
            result = 1.1;
        } else if (currentYear - year > 45 && currentYear - year <= 55) {
            result = 1.19;
        } else if (currentYear - year > 55 && currentYear - year <= 80) {
            result = 1.25;
        } else if (currentYear - year > 80 && currentYear - year <= 100) {
            result = 1.5;
        } else if (currentYear - year > 100) {
            result = 1.9;
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
        result.append("HUSFORSIKRING");
        result.append("\n").append(super.toString());
        result.append("\nDekning: ").append(coverage.toString());
        result.append("\nUtleiedekning: ").append(rental ? "Ja" : "Nei");
        result.append("\nEiendomstype: ").append(type.toString());
        // Returns the string.
        return result.toString();
    }
}