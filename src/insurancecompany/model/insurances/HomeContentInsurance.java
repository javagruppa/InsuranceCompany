/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package insurancecompany.model.insurances;

import insurancecompany.model.properties.Home;
import insurancecompany.misc.hometypes.HomeType;
import insurancecompany.misc.coverages.HomeInsuranceCoverage;
import java.io.Serializable;


/**
 *
 * @author Carl
 */
public class HomeContentInsurance extends PropertyInsurance implements Serializable {
    private static final long serialVersionUID = 1L;
    
    /** the home this insurance is for */
    private Home home;
    /** the hometype this insurance is for */
    private HomeType hometype;
    /** the coverage of this insurance */
    private HomeInsuranceCoverage coverage;
    /** The value this insurance covers */
    private int amount;
    
    /**
     * Constructs a new home contents insurance with the specified customerId,
     * excess, home, hometype and coverage. Active is set to true.
     * Date is set to the current date.
     * InsuranceId is automatically set to nextInsuranceId.
     * @param customerId the ID of the customer who owns this insurance
     * @param excess the excess of this insurance
     * @param home the home this insurance is for
     * @param hometype the hometype this insurance is for 
     * @param coverage the coverage of this insurance
     * @param amount the value this insurance covers
     */
    public HomeContentInsurance(int customerId, int excess, Home home,
            HomeType hometype, HomeInsuranceCoverage coverage, int amount){
        super(customerId, excess);
        this.home = home;
        this.hometype = hometype;
        this.coverage = coverage;
        this.amount = amount;
    }
    
    /**
     * Finds and returns the basic price of this insurance, based on whether
     * this is a Basic or a Plus insurance type
     * @return the basic price
     */
    private int basicPrice(){
	int basicP = coverage.getPricing();
	return basicP;
    }

    /**
     * Calculates and returns the content pricing of this insurance, based
     * on the value insured.
     * @return the content price of this insurance
     */
    private double contentPrice(){
	double cPrice = 0;
	
	if (amount <= 150000){
		cPrice = amount * 0.004;
	}
	else if (amount > 150000 && amount <= 300000){
		cPrice = amount * 0.0055;
	}
	else if (amount > 300000 && amount <= 500000){
		cPrice = amount * 0.006;
	}
	else if (amount > 500000 && amount <= 1500000){
		cPrice = 3500;
	}
	else if (amount > 1500000){
		double extra = amount * 0.001;
		cPrice = extra + 3500;
	}

	return cPrice;
    }

    /**
     * Calculates and returns the drop in price for this insurance based on
     * the chosen excess
     * @return the drop in price due to excess
     */
    private int excessDrop(){
	int excess = getExcess();
	int drop = 0;

	if (excess == 0){
		drop = -2000;
	}
	else if (excess > 0 && excess <= 1000){
		drop = excess / 10;
	}
	else if (excess > 1000 && excess <= 2000){
		drop = excess / 9;
	}
	else if (excess > 2000 && excess <= 3500){
		drop = excess / 8;
	}
	else if (excess > 3500 && excess <= 5000){
		drop = excess / 7;
	}
	else if (excess > 5000 && excess <= 10000){
		drop = 800;
	}
	else if (excess > 10000 && excess <= 20000){
		drop = 1000;
	}
	return drop;
    }

    /**
     * Calculates the premium of this insurance, and then sets the premium
     */
    public void premium(){
	int newPremium = basicPrice() + (int)contentPrice() - excessDrop();
	setPremium(newPremium);
    }
    
    /**
     * Returns the type of this insurance in form of a String.
     * @return 
     */
    @Override
    public String getName() {
        return "Innboforsikring";
    }
    
     /**
     * Returns the coverage of this insurance.
     * @return 
     */
    @Override
    public Object getCoverage() {
        return coverage;
    }    
    public Home getHome(){
        return home;
    }
}
