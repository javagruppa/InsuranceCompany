/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package insurancecompany.insurances;

import insurancecompany.vehicles.Boat;

/**
 *
 * @author Sindre
 */
public class BoatInsurance extends VehicleInsurance {
    /** The boat this insurance is for. */
    private Boat boat;
    
    /**
     * Constructor initializing active, date and insuranceConditions and boat 
     * of this insurance.
     * @param boat the boat this insurance is for
     */
    public BoatInsurance(Boat boat) {
        super();
        this.boat = boat;
    }
    
    /**
     * Returns a string representation of this insurance. The string
     * representation consists of each field with a short description separated
     * by a new line.
     * @return a string representation of this insurance
     */
    @Override
    public String toString() {
        // Creates a StringBuilder which will be returned at the end of the 
        // method.
        StringBuilder out = new StringBuilder();
        // Appends the fields with appropriate sentences.
        out.append("BÅTFORSIKRING");
        out.append("\n").append(super.toString());
        out.append("\n").append(boat.toString());
        // Returns the string.
        return out.toString();
    }
}