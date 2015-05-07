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
public class Car extends Vehicle {
    /**
     * Constructs a new car of the specified brand and model, and with the
     * specified engine effect, registration number and registration year. The
     * owner of this car has the specified personal number.
     * 
     * @param brand the brand of this car
     * @param engineEffect the effect of the engine of this car
     * @param model the model of this car
     * @param personalNumber the personal number of the owner of this car
     * @param regNumber the registration number of this car
     * @param regYear the registration year of this car
     */
    public Car(String brand, int engineEffect, String model, 
            String personalNumber, String regNumber, int regYear){
        super(brand, engineEffect, model, personalNumber, regNumber, regYear);
    }
}