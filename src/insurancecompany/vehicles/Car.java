/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package insurancecompany.vehicles;

/**
 *
 * @author Carl
 * @author Sindre
 */
public class Car extends Vehicle {
    public Car(String brand, int engineEffect, String model, 
            String personalNumber, String regNumber, int regYear){
        super(brand, engineEffect, model, personalNumber, regNumber, regYear);
    }
}