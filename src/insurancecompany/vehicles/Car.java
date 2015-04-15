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
  

    public Car(int regNumber, int effect, int year, String make, String model){
        super(regNumber, make, model, year);
        this.engineEffect = effect;
    }
    
    public int getEngineEffect(){
        return engineEffect;
    }
    
}
