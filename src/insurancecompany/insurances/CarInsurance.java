/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package insurancecompany.insurances;

import insurancecompany.vehicles.Car;

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
    
    /**
     * Constructs a new vehicle insurance with the specified customerId and 
     * excess. Active is set to true. Date is set to the current date.
     * 
     * @param car the car this insurance is for
     * @param customerId the id of the customer who owns this insurance
     * @param excess the excess of this insurance
     * @param hasAlarm whether the car this insurance is for has an alarm or not
     * @param hasGarage whether the car this insurance is for has a garage or 
     * not
     * @param maxLength the maximum driving length for this insurance
     * @param youngDriver whether a person under 25 years is allowed to drive 
     * the car this insurance is for
     */
    public CarInsurance(Car car, int customerId, int excess, boolean hasAlarm,
            boolean hasGarage, int maxLength, boolean youngDriver) {
        super(customerId, excess, hasAlarm);
        this.bonus = 0;
        this.car = car;
        this.hasGarage = hasGarage;
        this.maxLength = maxLength;
        this.youngDriver = youngDriver;
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
        result.append("\nKan personer under 25 kjøre bilen: ").append(youngDriver 
                ? "Ja" : "Nei");
        result.append("\nMaksimum kjørelengde: ").append(maxLength);
        result.append("\nBonus: ").append(bonus).append("%");
        // Returns the string.
        return result.toString();
    }
    
    /**
     * Updates the bonus of this insurance.
     */
    public void updateBonus() {
        //
    }
}