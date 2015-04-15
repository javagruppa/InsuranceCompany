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
public class Boat extends Vehicle{
    private int footLength;
    private String engineType;
    private int engineEffect;
    
    public Boat(int regNumber, int effect, int length, String engineType, int year, String make, String model){
        super(regNumber, make, model, year);
        this.footLength = length;
        this.engineType = engineType;
        this.engineEffect = effect;
    }
    
}
