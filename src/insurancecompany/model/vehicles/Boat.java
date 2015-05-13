package insurancecompany.model.vehicles;

import java.io.Serializable;

/**
 * The class boat. Sub class of vehicle representing a boat that is insured
 * @author Carl
 * @author Sindre
 */
public class Boat extends Vehicle implements Serializable {
    /** SerialVersionUID used to identify this class for object IO */
    private static final long serialVersionUID = 1L;
    /** The type of the engine of this boat. */
    private String engineType;
    /** The length of this boat in feet. */
    private int length;
    /** The value of this boat */
    private int value;
    
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
     * @param registrationNumber the registration number of this boat
     * @param registrationYear the registration year of this boat
     * @param value the value of this boat
     */
    public Boat(boolean alarm, String brand, int engineEffect, 
            String engineType, int length, String model, String personalNumber, 
            String registrationNumber, int registrationYear, int value){
        super(alarm, brand, engineEffect, model, personalNumber, 
                registrationNumber, registrationYear);
        this.length = length;
        this.engineType = engineType;
        this.value = value;
    }
    
    /**
     * Returns the value of this boat
     * @return the value
     */
    public int getValue(){
        return value;
    }
    
    /**
     * Returns the length of this boat
     * @return the length
     */
    public int getLength() {
        return length;
    }
    
    /**
     * Returns the engine type of this boat
     * @return the engine type
     */
    public String getEngineType() {
        return engineType;
    }
    
    /**
     * Returns a string representation of this boat
     * @return a string representation of this boat
     */
    @Override
    public String toString() {
        return "Båt:\n" + super.toString();
    }
            
}