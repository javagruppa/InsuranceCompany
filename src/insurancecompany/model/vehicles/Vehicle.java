/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package insurancecompany.model.vehicles;

/**
 *
 * @author André
 * @author Sindre
 */
public abstract class Vehicle {
    /** Whether this vehicle has an alarm or not. */
    private boolean alarm;
    /** The brand of this vehicle. */
    private String brand;
    /** The effect of the engine of this vehicle. */
    private int engineEffect;
    /** The model of this vehicle. */
    private String model;
    /** The personal number of the owner of this vehicle. */
    private String personalNumber;
    /** The registration number of this vehicle. */
    private String registrationNumber;
    /** The registration year of this vehicle. */
    private int registrationYear;
    
    /**
     * Constructs a new vehicle of the specified brand and model, and with the
     * specified engine effect, registration number and registration year. The
     * owner of this vehicle has the specified personal number.
     * 
     * @param alarm whether this vehicle has an alarm or not
     * @param brand the brand of this vehicle
     * @param engineEffect the effect of the engine of this vehicle
     * @param model the model of this vehicle
     * @param personalNumber the personal number of the owner of this vehicle
     * @param registrationNumber the registration number of this vehicle
     * @param registrationYear the registration year of this vehicle
     */
    public Vehicle(boolean alarm, String brand, int engineEffect, String model, 
            String personalNumber, String registrationNumber, 
            int registrationYear){
        this.alarm = alarm;
        this.brand = brand;
        this.engineEffect = engineEffect;
        this.model = model;
        this.personalNumber = personalNumber;
        this.registrationNumber = registrationNumber;
        this.registrationYear = registrationYear;
    }

    /**
     * @return the engineEffect
     */
    public int getEngineEffect() {
        return engineEffect;
    }

    /**
     * @return the personalNumber
     */
    public String getPersonalNumber() {
        return personalNumber;
    }

    /**
     * @return the registrationNumber
     */
    public String getRegistrationNumber() {
        return registrationNumber;
    }
}