/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package insurancecompany.insurances;

import insurancecompany.vehicles.Car;
import insurancecompany.misc.DateCalculations;

import java.util.Calendar;

/**
 *
 * @author Sindre
 */
public class CarInsurance extends VehicleInsurance {
    /** The bonus of this insurance. Used to calculate the yearly insurance 
     * premium. */
    private int bonus;
    /** The car this insurance is for. */
    private Car car;
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
    public CarInsurance(Car car, String coverage, int customerId, int excess, 
            boolean hasAlarm, boolean hasGarage, int maxLength, 
            boolean youngDriver) {
        super(coverage, customerId, excess, hasAlarm);
        this.bonus = 0;
        this.car = car;
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
        result.append("\nGarasje: ").append(hasGarage ? "Ja" : "Nei");
        result.append("\nKan personer under 25 kjøre bilen: ").
                append(youngDriver ? "Ja" : "Nei");
        result.append("\nMaksimum kjørelengde: ").append(maxLength);
        result.append("\nBonus: ").append(bonus).append("%");
        // Returns the string.
        return result.toString();
    }
    
    
    /**
     * Drops the bonus in case of an accident.
     */
    public void dropBonus() {       
        if (bonus >= -180 || bonus <= 0) {
            bonus -= 40;
        } else if (bonus >= 10 || bonus <= 70) {
            bonus -= 30;
        } else if (bonus == 75 && yearsOnSeventyFive <= 5) {
            bonus -= 15;
        } else if (bonus == 75 && yearsOnSeventyFive >= 6) {
            bonus -= 0;
        }
        lastBonusUpdate = Calendar.getInstance();
    }
    
    /**
     * Increases the bonus from one year to the next.
     */
    private void yearlyBonusIncrease() {
        if (bonus <= 60 ) {
            bonus += 10;
            yearsOnSeventy = 0;
            yearsOnSeventyFive = 0;
        } else if (bonus == 70 && yearsOnSeventy < 5) {
            yearsOnSeventy++;
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
     * Sets the boolean representing whether the insurance covers young drivers.
     * @param youngDriver 
     */
    public void setYoungDriver(boolean youngDriver) {
        this.youngDriver = youngDriver;
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