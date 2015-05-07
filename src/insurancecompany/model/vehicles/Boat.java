/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package insurancecompany.model.vehicles;

/**
 *
 * @author Carl
 * @author Sindre
 */
public class Boat extends Vehicle{
    /** The type of the engine of this boat. */
    private String engineType;
    /** The length of this boat in feet. */
    private int length;
    
    /**
     * Constructs a new boat of the specified brand and model, and with the
     * specified engine effect, registration number, registration year, engine 
     * type and length. The owner of this boat has the specified personal 
     * number.
     * 
     * @param alarm whether this vehicle has an alarm or not
     * @param brand the brand of this boat
     * @param engineEffect the effect of the engine of this boat
     * @param engineType the type of the engine of this boat
     * @param length the length of this boat in feet
     * @param model the model of this boat
     * @param personalNumber the personal number of the owner of this boat
     * @param regNumber the registration number of this boat
     * @param regYear the registration year of this boat
     */
    public Boat(boolean alarm, String brand, int engineEffect, 
            String engineType, int length, String model, String personalNumber, 
            String regNumber, int regYear){
        super(alarm, brand, engineEffect, model, personalNumber, regNumber, 
                regYear);
        this.length = length;
        this.engineType = engineType;
    }   
}