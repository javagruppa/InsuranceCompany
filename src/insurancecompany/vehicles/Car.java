/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package insurancecompany.vehicles;

/**
 *
 * @author Carl
 */
public class Car extends Vehicle {
    private int engineEffect;
    private int regYear;
    private String make;
    private String model;

    public Car(int regNumber, int effect, int year, String make, String model){
        super(regNumber);
        this.engineEffect = effect;
        this.regYear = year;
        this.make = make;
        this.model = model;
    }
    
    public String getMake(){
        return make;
    }
    
    public String getModel(){
        return model;
    }
    
    public int getEngineEffect(){
        return engineEffect;
    }
    
    public int getRegYear(){
        return regYear;
    }
}
