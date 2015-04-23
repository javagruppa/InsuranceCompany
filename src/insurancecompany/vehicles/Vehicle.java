/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package insurancecompany.vehicles;

/**
 *
 * @author André
 */
public class Vehicle {
    private int personalNumber;
    private int regNumber;
    private String make;
    private String model;
    private int regYear;
    
    public Vehicle(int regNumber, String make, String model, int regYear){
        this.regNumber = regNumber;
        this.make = make;
        this.model = model;
        this.regYear = regYear;
    }
    
    public int getRegNumber(){
        return regNumber;
    }
    
    public void setRegNumber(int newNumber){
        this.regNumber = newNumber;
    }
    
    public void setPersonalNumber(int personalNumber) {
        this.personalNumber = personalNumber;
    }
    
    public String toString(){
        String s = "";
        return s;
    }
}
