/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package insurancecompany.model.insurances;

import insurancecompany.misc.coverages.HolidayHomeInsuranceCoverage;
import insurancecompany.misc.hometypes.HolidayHomeType;
import insurancecompany.model.properties.Property;
import java.io.Serializable;
import java.util.Calendar;

/**
 *
 * @author Sindre
 */
public class HolidayHomeInsurance extends PropertyInsurance 
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
     * Constructs a new holiday home insurance with the specified coverage, 
     * customerId, excess, property, rental and type. Active is set to true. 
     * Date is set to the current date. InsuranceId is automatically set to 
     * nextInsuranceId.
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
    }
    
    // GET METHODS
    
    /** @return The coverage of this insurance. */
    @Override
    public Object getCoverage() {
        return coverage;
    }
    
    /** @return The type of insurance in form of a String. */
    @Override
    public String getName() {
        return "Fritidsboligforsikring";
    }
    
    // CALCULATE PREMIUM METHODS
    
    /**
     * Calculates the drop in price for this insurance based on the
     * applied excess
     * 
     * @return The drop value as an integer.
     */
    private int excessDrop() {
	int excess = getExcess();
	int drop = 0;
	if (excess == 0){
            drop = -1000;
	} else if (excess > 0) {
            drop = excess / 6;
	}
	return drop;
    }
    
    /**
     * Creates a multiplier for the extra cost of this insurance if the insured
     * house is to be rented out.
     * 
     * @return The multiplicator for extra pricing as a double.
     */
    private double rentalExtra(){
	double rentalExtra = 1;
	if (rental){
            rentalExtra = 1.15;
	}
	return rentalExtra;
    }
    
    /**
     * Sets and returns the price multiplicator for what year the home was
     * built.
     * @return the multiplicator as a double 
     */
    private double buildingYearMultiplicator() {
        double multiplicator = 1;
        int year = property.getYear();
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        if (currentYear - year <= 10) {
            multiplicator = 0.85;
        } else if (currentYear - year > 10 && currentYear - year <= 20) {
            multiplicator = 0.95;
        } else if (currentYear - year > 20 && currentYear - year <= 30) {
            multiplicator = 1.05;
        } else if (currentYear - year > 30 && currentYear - year <= 45) {
            multiplicator = 1.1;
        } else if (currentYear - year > 45 && currentYear - year <= 55) {
            multiplicator = 1.19;
        } else if (currentYear - year > 55 && currentYear - year <= 80) {
            multiplicator = 1.25;
        } else if (currentYear - year > 80 && currentYear - year <= 100) {
            multiplicator = 1.5;
        } else if (currentYear - year > 100) {
            multiplicator = 1.9;
        }
        return multiplicator;
    }
    
    /**
     * Calculates and sets the premium for this insurance based on:
     * If the house is to be rented out
     * The type of the insured home
     * The building material of the home
     * The year the home was built
     */
    public void insuranceprice(){
        // Multiplicator for the homes building material
	double MaterialMultiplicator = property.getMaterialMultiplicator();
        // Multiplicator for the homes building year
        double yearMultiplicator = buildingYearMultiplicator();
        // Price for what type of home is insured
	int typePrice = type.getPricing();
        // Base price. Price after excess drop (incl price for plus or basic)
	int baseprice = typePrice + coverage.getPricing() - excessDrop();
        // Total price. Base price including extra if the home is a rental
	double totalPrice = baseprice * rentalExtra();
        // Material price. Total price multiplied by material multiplicator
	double materialPrice = totalPrice * MaterialMultiplicator;
        // Final price. Material price multiplied by year multiplicator
        double finalprice = materialPrice * yearMultiplicator;
        // Converts the final price to an int value
        int setPremium = (int)finalprice;
        // Sets this insurances premium to the final price
	setPremium(setPremium);
    }
    
    // TO STRING METHOD
    
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
        result.append("HUSFORSIKRING");
        result.append("\n").append(super.toString());
        result.append("\nDekning: ").append(coverage.toString());
        result.append("\nUtleiedekning: ").
                append(rental ? "Ja" : "Nei");
        // Returns the string.
        return result.toString();
    }
}