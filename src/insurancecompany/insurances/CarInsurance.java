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
    /** The car this insurance is for. */
    private Car car;
    /** The price per kilometer of this insurance. Used to calculate the yearly
     * insurance premium. */
    private int pricePerKilometer;
    /** The bonus of this insurance. Used to calculate the yearly insurance 
     * premium. */
    private int bonus;
    
    /**
     * Constructor initializing car of this insurance.
     * @param car the car this insurance is for
     */
    public CarInsurance(Car car) {
        super();
        this.car = car;
        this.bonus = 0;
    }
    
    /**
     * Sets an price per kilometer to this insurance.
     * @param pricePerKilometer price per kilometer to be set to this insurance
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
        out.append(super.toString());
        out.append(car.toString());
        out.append("Pris pr. kilometer: ").append(pricePerKilometer);
        out.append("Bonus: ").append(bonus + "%");
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