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
public class Boat extends Vehicle{
    private String engineType;
    private int footLength;
    
    public Boat(String brand, int engineEffect, String engineType, 
            int footLength, String model, String personalNumber, 
            String regNumber, int regYear){
        super(brand, engineEffect, model, personalNumber, regNumber, regYear);
        this.footLength = footLength;
        this.engineType = engineType;
    }   
}