/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package insurancecompany.model.insurances;

import insurancecompany.model.properties.HolidayHome;
import insurancecompany.misc.hometypes.HolidayHomeType;
import insurancecompany.misc.coverages.HolidayHomeInsuranceCoverage;
import java.io.Serializable;
import java.util.Calendar;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
/**
 *
 * @author Sindre
 */
public class HolidayHomeInsurance extends PropertyInsurance implements Serializable {
    private static final long serialVersionUID = 1L;
    
    /** The holiday home this insurance is for. */
    private HolidayHome holidayHome;
    /** The type of house this insurance is for. */
    private HolidayHomeType holidayhometype;
    /** the coverage of this insurance */
    private HolidayHomeInsuranceCoverage coverage;
    
    /**
     *  * Constructs a new holiday home insurance with the specified contentAmount, 
     * customerId, excess, holidayhometype, holidayhomecoverage and holidayHome. 
     * Active is set to true. Date is set to the current date. InsuranceId is 
     * automatically set to nextInsuranceId.
     * 
     * @param type the type of holidayhouse this insurance is for
     * @param customerId the customer that has this insurance
     * @param excess the excess of this insurance
     * @param holidayHome  the holidayhome that is insured
     * @param coverage the coverage of this insurance
     */
    public HolidayHomeInsurance(HolidayHomeType type, int customerId,
            int excess, HolidayHome holidayHome,
            HolidayHomeInsuranceCoverage coverage) {
        super(customerId, excess);
        this.holidayHome = holidayHome;
        this.holidayhometype = type;
        this.coverage = coverage;
    }
    
    /**
     * Returns the type of this insurance in form of a String.
     * @return 
     */
    @Override
    public String getName() {
        return "Fritidsboligforsikring";
    }
    
    /**
     * Returns the type of this insurance in form of a StringProperty.
     * @return 
     */
    @Override
    public StringProperty getPropertyName() {
        return new SimpleStringProperty("Fritidsboligforsikring");
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

	if (holidayHome.getRental()){
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
        int year = holidayHome.getYear();
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
	double MaterialMultiplicator = holidayHome.getMaterialMultiplicator();
        // Multiplicator for the homes building year
        double yearMultiplicator = buildingYearMultiplicator();
        // Price for what type of home is insured
	int typePrice = holidayhometype.getPricing();
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
     * Returns the holiday home this insurance is for.
     * 
     * @return the holiday home this insurance is for
     */
    public HolidayHome getHolidayHome() {
        return holidayHome;
    }
}