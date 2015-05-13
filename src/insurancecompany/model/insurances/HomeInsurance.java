/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package insurancecompany.model.insurances;

import insurancecompany.model.properties.Property;
import insurancecompany.misc.hometypes.HomeType;
import insurancecompany.misc.coverages.HomeInsuranceCoverage;
import java.io.Serializable;   
import java.util.Calendar;

/**
 *
 * @author Sindre
 */
public class HomeInsurance extends PropertyInsurance implements Serializable {
    /** SerialVersionUID used to identify this class for object IO */
    private static final long serialVersionUID = 1L;
    
    /** The home this insurance is for. */
    private Property home;
    /** The type of home this insurance is for */
    private HomeType hometype;
    /** The coverage of this insurance */
    private HomeInsuranceCoverage coverage;
    /** Whether or not the insurance covers rental property */
    private boolean rental;
    
    /**
     * Constructs a new home insurance with the specified customerId, excess
     * and home. Active is set to true. Date is set to the current date.
     * InsuranceId is automatically set to nextInsuranceId.
     * 
     * @param customerId the id of the customer who owns this insurance
     * @param excess the excess of this insurance
     * @param home the holiday home this insurance is for
     * @param hometype the type of home this insurance is for
     * @param coverage the coverage of this insurance
     */
    public HomeInsurance(int customerId, int excess, Property home, 
            HomeType hometype, HomeInsuranceCoverage coverage, boolean rental) {
        super(customerId, excess);
        this.home = home;
        this.hometype = hometype;
        this.coverage = coverage;
        this.rental = rental;
    }
    
    /**
     * Returns the type of this insurance in form of a String.
     * @return 
     */
    @Override
    public String getName() {
        return "Husforsikring";
    }
    
    /**
     * Returns whether or not this insurance covers rentals
     * @return rental
     */
    public boolean getRental() {
        return rental;
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
        result.append("HUSFORSIKRING");
        result.append("\n").append(super.toString());
        result.append("\nDekning: ").append(coverage.toString());
        result.append("\nUtleiedekning: ").
                append(rental ? "Ja" : "Nei");
        // Returns the string.
        return result.toString();
    }
    
    /**
     * Calculates the drop in price for this insurance based on the
     * applied excess
     * @return the drop value as an integer
     */
    private int excessDrop(){
	int excess = getExcess();
	int drop = 0;

	if (excess == 0){
            drop = -1000;
	}
	else if (excess > 0){
            drop = excess / 6;
	}

	return drop;
    }
    
    /**
     * Creates a multiplier for the extra cost of this insurance if the insured
     * house is to be rented out.
     * @return the multiplicator for extra pricing as a double
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
    private double buildingYearMultiplicator(){
        double multiplicator = 1;
        int year = home.getYear();
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        if (currentYear - year <= 10){
            multiplicator = 0.85;
        }
        else if (currentYear - year > 10 && currentYear - year <= 20){
            multiplicator = 0.95;
        }
        else if (currentYear - year > 20 && currentYear - year <= 30){
            multiplicator = 1.05;
        }
        else if (currentYear - year > 30 && currentYear - year <= 45){
            multiplicator = 1.1;
        }
        else if (currentYear - year > 45 && currentYear - year <= 55){
            multiplicator = 1.19;
        }
        else if (currentYear - year > 55 && currentYear - year <= 80){
            multiplicator = 1.25;
        }
        else if (currentYear - year > 80 && currentYear - year <= 100){
            multiplicator = 1.5;
        }
        else if (currentYear - year > 100){
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
     * 
     */
    public void insuranceprice(){
        // Multiplicator for the homes building material
	double MaterialMultiplicator = home.getMaterialMultiplicator();
        // Multiplicator for the homes building year
        double yearMultiplicator = buildingYearMultiplicator();
        // Price for what type of home is insured
	int typePrice = hometype.getPricing();
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
    
    /**
     * Sets the rental status of this insurance to the received parameter.
     * Also changes the premium of this insurance based on the new information.
     * @param rental whether or not this insurance covers rentals
     */
    public void setRental(boolean rental) {
        this.rental = rental;
        insuranceprice();
    }
    
    
    
    /**
     * Returns the home this insurance is for.
     * 
     * @return the home this insurance is for
     */
    public Property getHome() {
        return home;
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