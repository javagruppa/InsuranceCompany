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
    private String brand;
    private int engineEffect;
    private String model;
    private String personalNumber;
    private String regNumber;
    private int regYear;
    
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