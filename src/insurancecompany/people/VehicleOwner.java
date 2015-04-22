/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package insurancecompany.people;
import insurancecompany.misc.Address;
import insurancecompany.vehicles.Vehicle;
/**
 *
 * @author Sindre
 */
public class VehicleOwner extends Person {
        private Vehiclelist vehiclelist;
        
        public VehicleOwner(String firstname, String lastname, int personalNumber, String email, Address address, int phone) {
        super(firstname, lastname, personalNumber, email, address, phone)
}
