/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package insurancecompany.model.people;
import insurancecompany.model.properties.Address;
/**
 *
 * @author Carl
 */
public class ServiceWorker extends Employee {

    public ServiceWorker(String firstname, String lastname, String personalNumber, String email, Address address, String phone) {
        super(firstname, lastname, personalNumber, email, address, phone);
    }

    public String toString(){
        String s = "";
        return s;
    }
            
}