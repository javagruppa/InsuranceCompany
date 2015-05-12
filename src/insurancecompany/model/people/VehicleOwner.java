/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package insurancecompany.model.people;

import insurancecompany.model.properties.Address;
import insurancecompany.model.vehicles.Vehicle;
import java.io.Serializable;

/**
 *
 * @author Sindre
 */
public class VehicleOwner extends Person implements Serializable {
    private static final long serialVersionUID = 1L;
        
    public VehicleOwner(String firstname, String lastname, String personalNumber, String email, Address address, String phone) {
        super(firstname, lastname, personalNumber, email, address, phone);
    }
    
    /**
     * Returns a string representation of this VehicleOwner. The string
     * representation consists of each field with a short description separated
     * by a new line.
     * @return a string representation of this vehicle owner
     */
    @Override
    public String toString() {
        // Creates a StringBuilder which will be returned at the end of the 
        // method.
        StringBuilder result = new StringBuilder();
        // Appends the fields with appropriate sentences.
        result.append("EIER AV KJØRETØY");
        result.append("\n").append(super.toString());
        // Returns the string.
        return result.toString();
    }

    /**
     * Returns the  ID, set to 0.
     * @return 0
     */
    @Override
    public int getId() {
        return 0;
    }
        
}