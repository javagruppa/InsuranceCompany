package insurancecompany.model.insurances;

import insurancecompany.misc.enums.InsuranceType;
import insurancecompany.misc.enums.coverages.HolidayHomeInsuranceCoverage;
import insurancecompany.misc.enums.hometypes.HolidayHomeType;
import insurancecompany.model.properties.Property;
import java.io.Serializable;
import java.util.Calendar;

/**
 * This class represents a holiday home insurance object. It contains 
 * information about the insurance and methods to manipulate and get the 
 * information.
 * 
 * @author Sindre
 * @author Carl
 * 
 * @since 17.05.2015
 */
public class HolidayHomeInsurance extends Insurance 
        implements Serializable {
    
    /** SerialVersionUID used to identify this class for object IO. */
    private static final long serialVersionUID = 1L;
    /** The coverage of this insurance. */
    private HolidayHomeInsuranceCoverage coverage;
    /** The property this insurance is for. */
    private Property property;
    /** Whether or not this insurance covers rentals. */
    private boolean rental;
    /** The type of home this insurance is for. */
    private HolidayHomeType type;
    
    /**
     * Constructs a new holiday home insurance with the specified parameters. 
     * Active is set to true. Date is set to the current date. InsuranceId is 
     * automatically set to nextInsuranceId.
     * 
     * @param coverage The coverage of this insurance.
     * @param customerId The id of the customer who owns this insurance.
     * @param excess The excess of this insurance.
     * @param property The property this insurance is for.
     * @param rental Whether or not this insurance covers rentals.
     * @param type The type of home this insurance is for.
     */
    public HolidayHomeInsurance(HolidayHomeInsuranceCoverage coverage, 
            int customerId, int excess, Property property, boolean rental, 
            HolidayHomeType type) {
        super(customerId, excess);
        this.coverage = coverage;
        this.property = property;
        this.rental = rental;
        this.type = type;
        calculatePremium();
    }
    
    /** @return The coverage of this insurance. */
    @Override
    public HolidayHomeInsuranceCoverage getCoverage() {
        return coverage;
    }
    
    /** @return The type of insurance in form of a String. */
    @Override
    public String getType() {
        return InsuranceType.HOLIDAY_HOME_INSURANCE.toString();
    }
    
    /** @return The property this insurance is for. */
    public Property getProperty() {
        return property;
    }
    
    /**
     * Calculates and sets the premium for this insurance based on if the house 
     * is to be rented out, the type of the insured home, the building material 
     * of the home and the year the home was built.
     */
    @Override
    public final void calculatePremium() {
        double rentalExtra = rental ? 1.15 : 1;
        // Multiplicator for the homes building material
	double MaterialMultiplicator = property.getMaterialMultiplicator();
        // Multiplicator for the homes building year
        double yearMultiplicator = buildingYearMultiplicator();
        // Price for what type of home is insured
	int typePrice = type.getPricing();
        // Base price. Price after excess drop (incl price for plus or basic)
	int baseprice = typePrice + coverage.getPricing() - excessDrop();
        // Total price. Base price including extra if the home is a rental
	double totalPrice = baseprice * rentalExtra;
        // Material price. Total price multiplied by material multiplicator
	double materialPrice = totalPrice * MaterialMultiplicator;
        // Final price. Material price multiplied by year multiplicator
        double finalprice = materialPrice * yearMultiplicator;
        // Converts the final price to an int value
        int setPremium = (int)finalprice;
        // Sets this insurances premium to the final price
	setPremium(setPremium);
        calculateMonthlyPremium();
    }
    
    /**
     * Calculates the drop in price for this insurance based on the excess.
     * 
     * @return The drop in price due to excess.
     */
    private int excessDrop() {
	int excess = getExcess();
	int result = 0;
	if (excess == 0){
            result = -1000;
	} else if (excess > 0) {
            result = excess / 6;
	}
	return result;
    }
    
    /**
     * Sets and returns the price multiplicator for what year the home was
     * built.
     * 
     * @return The multiplicator as a double.
     */
    private double buildingYearMultiplicator() {
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