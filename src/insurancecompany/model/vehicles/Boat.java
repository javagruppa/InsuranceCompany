package insurancecompany.model.vehicles;

import java.io.Serializable;

/**
 * The class boat. Sub class of vehicle representing a boat that is insured.
 * 
 * @author Carl
 * @author Sindre
 * 
 * @since 16.05.2015
 */
public class Boat extends Vehicle implements Serializable {
    /** SerialVersionUID used to identify this class for object IO */
    private static final long serialVersionUID = 1L;
    /** The effect of the engine of this boat. */
    private int engineEffect;
    /** The type of the engine of this boat. */
    private String engineType;
    /** The length of this boat in feet. */
    private int length;
    /** The value of this boat. */
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
        super(alarm, brand, model, personalNumber, 
                registrationNumber, registrationYear);
        this.length = length;
        this.engineEffect = engineEffect;
        this.engineType = engineType;
        this.value = value;
    }
    
    /** @return The value of tis boat. */
    public int getValue(){
        return value;
    }
    
    /** @return The length of this boat. */
    public int getLength() {
        return length;
    }
    
    /** @return The engine effect of this boat. */
    public int getEngineEffect() {
        return engineEffect;
    }
    
    /** @return The engine type of this boat. */
    public String getEngineType() {
        return engineType;
    }
    
    /**
     * Creates and returns a string representation of this vehicle
     * 
     * @return A string representation of this vehicle.
     */
    @Override
    public String toString() {
        // Creates a StringBuilder which will be returned at the end of the 
        // method.
        StringBuilder result = new StringBuilder();
        // Appends the fields with appropriate sentences.
        result.append(super.toString());
        result.append("\nHestekrefter: ").append(engineEffect);
        result.append("\nMotortype: ").append(engineType);
        result.append("\nVerdi: ").append(value);
        // Returns the string.
        return result.toString();
    }
}