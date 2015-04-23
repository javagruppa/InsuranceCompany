/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package insurancecompany.insurances;

import insurancecompany.people.Customer;
import insurancecompany.vehicles.Car;

/**
 *
 * @author Sindre
 */
public class CarInsurance extends VehicleInsurance {
    /** Whether the car this insurance is for has an alarm or not. */
    private boolean hasAlarm;
    /** Whether the car this insurance is for has a garage or not. */
    private boolean hasGarage;
    /** Whether a person under 25 years is allowed to drive the car this 
     * insurance is for. */
    private boolean youngDriverPermission;
    /** The car this insurance is for. */
    private Car car;
    /** The bonus of this insurance. Used to calculate the yearly insurance 
     * premium. */
    private int bonus;
    /** The price per kilometer of this insurance. Used to calculate the yearly
     * insurance premium. */
    private int pricePerKilometer;
    /** The maximum driving length for this insurance. */
    private int maxDrivingLength;
    
    /**
     * Constructor.
     * @param customer the customer who owns this insurance
     * @param car the car this insurance is for
     */
    public CarInsurance(Customer customer, Car car) {
        super(customer);
        this.hasAlarm = false;
        this.hasGarage = false;
        this.youngDriverPermission = false;
        this.car = car;
        this.bonus = 0;
    }
    
    /**
     * Sets a price per kilometer to this insurance.
     * @param pricePerKilometer the price per kilometer to be set to this 
     * insurance
     */
    public void setPricePerKilometer(int pricePerKilometer) {
        this.pricePerKilometer = pricePerKilometer;
    }
    
    /**
     * Returns a string representation of this insurance. The string
     * representation consists of each field with a short description separated
     * by a new line.
     * @return a string representation of this insurance
     */
    @Override
    public String toString() {
        // Creates a StringBuilder which will be returned at the end of the 
        // method.
        StringBuilder out = new StringBuilder();
        // Appends the fields with appropriate sentences.
        out.append("BILFORSIKRING");
        out.append("\n").append(super.toString());
        out.append("\nPris pr. kilometer: ").append(pricePerKilometer);
        out.append("\n").append(car.toString());
        out.append("\nMaksimum kjørelengde: ").append(maxDrivingLength);
        if(hasAlarm) {
            out.append("\nBilen har alarm.");
        } else {
            out.append("\nBilen har ikke alarm.");
        }
        if(hasGarage) {
            out.append("\nBilen har garasje.");
        } else {
            out.append("\nBilen har ikke garasje.");
        }
        if(youngDriverPermission) {
            out.append("\nPersoner under 25 år kan kjøre bilen.");
        } else {
            out.append("\nPersoner under 25 år kan ikke kjøre bilen.");
        }
        out.append("\nBonus: ").append(bonus).append("%");
        // Returns the string.
        return out.toString();
    }
    
    /**
     * Updates the bonus of this insurance.
     */
    public void updateBonus() {
        //
    }
}