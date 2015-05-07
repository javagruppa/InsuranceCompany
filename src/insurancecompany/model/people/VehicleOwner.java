/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package insurancecompany.model.people;

import insurancecompany.model.properties.Address;
import insurancecompany.model.vehicles.Vehicle;

/**
 *
 * @author Sindre
 */
public class VehicleOwner extends Person {   
        
    public VehicleOwner(String firstname, String lastname, long personalNumber, String email, Address address, String phone) {
        super(firstname, lastname, personalNumber, email, address, phone);
    }
    
    public String toString() {
        String text = "";
        return text;
    }
        
}