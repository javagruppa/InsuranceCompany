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
    
    public String toString() {
        String text = "";
        return text;
    }

    @Override
    public int getId() {
        return 0;
    }
        
}