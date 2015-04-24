/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package insurancecompany.insurances;

/**
 *
 * @author Sindre
 */
public abstract class VehicleInsurance extends Insurance {
    /** Whether the vehicle this insurance is for has an alarm or not. */
    private boolean hasAlarm;
    
    /**
     * Constructs a new vehicle insurance with the specified customerId and 
     * excess. Active is set to true. Date is set to the current date.
     * 
     * @param customerId the id of the customer who owns this insurance
     * @param excess the excess of this insurance
     * @param hasAlarm whether the vehicle this insurance is for has an alarm 
     * or not
     */
    public VehicleInsurance(int customerId, int excess, boolean hasAlarm) {
        super(customerId, excess);
        this.hasAlarm = hasAlarm;
    }
    
    /**
     * Returns a string representation of this insurance. The string
     * representation consists of each field with a short description separated
     * by a new line.
     * 
     * @return a string representation of this insurance
     */
    @Override
    public String toString() {
        // Creates a StringBuilder which will be returned at the end of the 
        // method.
        StringBuilder result = new StringBuilder();
        // Appends the fields with appropriate sentences.
        result.append(super.toString());
        result.append("\nAlarm: ").append(hasAlarm ? "Ja" : "Nei");
        // Returns the string.
        return result.toString();
    }
}