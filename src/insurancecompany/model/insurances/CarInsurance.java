/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package insurancecompany.model.insurances;

import insurancecompany.model.vehicles.Car;
import insurancecompany.misc.DateCalculations;
import insurancecompany.model.coverages.CarInsuranceCoverage;

import java.util.Calendar;

/**
 *
 * @author Sindre
 */
public class CarInsurance extends Insurance {
    /** The bonus of this insurance. Used to calculate the yearly insurance 
     * premium. */
    private int bonus;
    /** The car this insurance is for. */
    private Car car;
    /** The coverage of this insurance. */
    private CarInsuranceCoverage coverage;
    /** Whether the car this insurance is for has a garage or not. */
    private boolean hasGarage;
    /** The maximum driving length for this insurance. */
    private int maxLength;
    /** Whether a person under 25 years is allowed to drive the car this 
     * insurance is for. */
    private boolean youngDriver;
    
    /** The date of when the bonus of this insurance was last updated. */
    private Calendar lastBonusUpdate;
    /** The number of consecutive years the bonus has stayed at 70 */
    private int yearsOnSeventy;
    /** The number of consecutive years the bonus has stayed at 75 */
    private int yearsOnSeventyFive;
    /** The calculated cost for the maximum length on this insurance */
    private int maxlengthCost;
    /** The calculated saving due to the excess on this insurance */
    private int excessSaving;
    
    /**
     * Constructs a new car insurance with the specified car, coverage, 
     * customerId, excess, hasAlarm, hasGarage, maxLengh and youngDriver. Active 
     * is set to true. Bonus is set to 0. Date is set to the current date. 
     * InsuranceId is automatically set to nextInsuranceId.
     * 
     * @param car the car this insurance is for
     * @param coverage the coverage of this insurance
     * @param customerId the id of the customer who owns this insurance
     * @param excess the excess of this insurance
     * @param hasAlarm whether the car this insurance is for has an alarm or not
     * @param hasGarage whether the car this insurance is for has a garage or 
     * not
     * @param maxLength the maximum driving length for this insurance
     * @param youngDriver whether a person under 25 years is allowed to drive 
     * the car this insurance is for
     */
    public CarInsurance(Car car, CarInsuranceCoverage coverage, int customerId, 
            int excess, boolean hasAlarm, boolean hasGarage, int maxLength, 
            boolean youngDriver) {
        super(customerId, excess);
        this.bonus = 0;
        this.car = car;
        this.coverage = coverage;
        this.hasGarage = hasGarage;
        this.maxLength = maxLength;
        this.youngDriver = youngDriver;
        lastBonusUpdate = DateCalculations.dateToCalendar(getDate());
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
        result.append("BILFORSIKRING");
        result.append("\n").append(super.toString());
        result.append("\nType: ").append(coverage.toString());
        result.append("\nGarasje: ").append(hasGarage ? "Ja" : "Nei");
        result.append("\nKan personer under 25 kjøre bilen: ").
                append(youngDriver ? "Ja" : "Nei");
        result.append("\nMaksimum kjørelengde: ").append(maxLength);
        result.append("\nBonus: ").append(bonus).append("%");
        // Returns the string.
        return result.toString();
    }
    
    /**
     * Calculates the price for the maximum number of kilometers included in
     * this insurance.
     */
    public void maxlengthCost(){
	maxlengthCost = maxLength / 10;
    }

    /**
     * Calculates the price drop of this insurance due the chosen excess.
     */
    public void excessCalculator(){
	int excess = getExcess();

	if(excess == 0){
	excessSaving = -2000;
        }

	else if(excess > 0 && excess <= 5000){
	excessSaving = excess / 5;
        }

	else if(excess > 5000 && excess <= 10000){
	excessSaving = excess / 4;
        }

	else if(excess > 10000){
	excessSaving = excess / 3;
        }
    }

    /**
     * Calculates the total premium to this insurance, using different
     * parameters including whether the customer fullfills certain aspects:
     * youngdriver, garage, alarm.
     */
    public void premiumMultiplicators(){
	double youngDriverMultiplicator = 1.0;
	double garageMultiplicator = 1.0;
	double alarmMultiplicator = 1.0;

	if (youngDriver){
	youngDriverMultiplicator = 1.2;
        }

	if (hasAlarm){
	alarmMultiplicator = 0.8;
        }

	if (hasGarage){
	garageMultiplicator = 0.8;
        }

	double allMultiplicators = garageMultiplicator + alarmMultiplicator +
                youngDriverMultiplicator;
	double totalMultiplicator = allMultiplicators / 3;

	int typeCost = coverage.getPricing();	
	double basicPremium = maxlengthCost + typeCost - excessSaving; 
	premium = basicPremium * totalMultiplicator;

}
    
    
    /**
     * Drops the bonus in case of an accident.
     */
    public void dropBonus() {
        // Bonus drops to a minimum of -180 points.
        if (bonus >= -180 || bonus <= -150) {
            bonus = -180;
        // When at 0 or below, bonus drops by 40 points.
        } else if (bonus >= -140 || bonus <= 0) {
            bonus -= 40;
        // When between 10 and 70, bonus drops by 30 points.
        } else if (bonus >= 10 || bonus <= 70) {
            bonus -= 30;
        // When reaching 75, bonus only drops by 15 points the 5 first years.
        } else if (bonus == 75 && yearsOnSeventyFive <= 5) {
            bonus -= 15;
        // With a bonus at 75 for 5 consecutive years bonus will remain constant with no drops.
        } else if (bonus == 75 && yearsOnSeventyFive >= 6) {
            bonus -= 0;
        }
        lastBonusUpdate = Calendar.getInstance();
    }
    
    /**
     * Increases the bonus from one year to the next.
     */
    private void yearlyBonusIncrease() {
        // Increases the bonus by 10 points a year up to a maximum of 70 points.
        if (bonus <= 60 ) {
            bonus += 10;
            yearsOnSeventy = 0;
            yearsOnSeventyFive = 0;
        // Once bonus has reached 70 points, we start counting consecutive years on seventy. This affects the bonus drops
        } else if (bonus == 70 && yearsOnSeventy < 5) {
            yearsOnSeventyFive = 0;
            yearsOnSeventy++;
        // When 5 consecutive years with 70 as a bonus, bonus goes to 75, and we start counting consecutive years at this stage aswell.
        } else if (bonus == 70 && yearsOnSeventy == 5) {
            bonus = 75;
            yearsOnSeventyFive++;
        }
        // Set the last bonus increase to 1 year later. If 1.5 years has passed the last update will correctly stay at 0.5 years ago.
        lastBonusUpdate.add(Calendar.YEAR, 1);
    }
    
    /**
     * Increases the bonus depending on how many years has past since the last bonus increase.
     */
    public void increaseBonus() {
        int years = DateCalculations.getDifferenceInYears(lastBonusUpdate, Calendar.getInstance());
        for (int i = 0; i < years; i++) {
            yearlyBonusIncrease();
        }
    }
    
    /**
     * Returns the boolean representing whether the insurance covers young drivers.
     * @return 
     */
    public boolean getYoungDriver() {
        return youngDriver;
    }
    
    
    /**
     * Returns the bonus of this car insurance.
     * @return 
     */
    public int getBonus() {
        return bonus;
    }
    
    /**
     * Sets the bonus of this car insurance.
     * @param bonus 
     */
    public void setBonus(int bonus) {
        this.bonus = bonus;
    }
    
    /**
     * Sets the max length for the car to this car insurance.
     * @param maxLength 
     */
    public void setMaxLength(int maxLength) {
        this.maxLength = maxLength;
    }
    
    /**
     * Returns the max length for the car to this car insurance.
     * @return 
     */
    public int getMaxLength() {
        return maxLength;
    }
    
    /**
     * Sets a true or false to the has garage option of this insurance.
     * @param hasGarage 
     */
    public void setHasGarage(boolean hasGarage) {
        this.hasGarage = hasGarage;
    }
    
    /**
     * Returns a true or false to the has garage option of this insurance.
     * @return 
     */
    public boolean getHasGarage() {
        return hasGarage;
    }
    
    /**
     * Sets the car of this car insurance.
     * @param car 
     */
    public void setCar(Car car) {
        this.car = car;
    }
    
    /**
     * Returns the car of this car insurance.
     * @return 
     */
    public Car getCar() {
        return car;
    }
}