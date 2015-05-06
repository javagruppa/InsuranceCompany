/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package insurancecompany.vehicles;

/**
 *
 * @author André
 * @author Sindre
 */
public abstract class Vehicle {
    /** The brand of this vehicle. */
    private String brand;
    /** The effect of the engine of this vehicle. */
    private int engineEffect;
    /** The model of this vehicle. */
    private String model;
    /** The personal number of the owner of this vehicle. */
    private String personalNumber;
    /** The registration number of this vehicle. */
    private String regNumber;
    /** The registration year of this vehicle. */
    private int regYear;
    
    /**
     * Constructs a new vehicle of the specified brand and model, and with the
     * specified engine effect, registration number and registration year. The
     * owner of this vehicle has the specified personal number.
     * 
     * @param brand the brand of this vehicle
     * @param engineEffect the effect of the engine of this vehicle
     * @param model the model of this vehicle
     * @param personalNumber the personal number of the owner of this vehicle
     * @param regNumber the registration number of this vehicle
     * @param regYear the registration year of this vehicle
     */
    public Vehicle(String brand, int engineEffect, String model, 
            String personalNumber, String regNumber, int regYear){
        this.brand = brand;
        this.engineEffect = engineEffect;
        this.model = model;
        this.personalNumber = personalNumber;
        this.regNumber = regNumber;
        this.regYear = regYear;
    }
}