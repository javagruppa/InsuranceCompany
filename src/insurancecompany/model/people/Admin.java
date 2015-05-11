/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package insurancecompany.model.people;
import insurancecompany.model.properties.Address;
import java.io.Serializable;
/**
 *
 * @author Carl
 */
public class Admin extends Employee implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * Constructs an Admin-object with the specified parameters
     * @param firstname first name of person
     * @param lastname last name of person
     * @param personalNumber personal number of person
     * @param email e-mail address of the person
     * @param address address of the person
     * @param phone  phone number for the person
     */
    public Admin(String firstname, String lastname, String personalNumber, String email, Address address, String phone) {
        super(firstname, lastname, personalNumber, email, address, phone);
    }

    /**
     * Returns a string representation for the Admin
     * @return a string of the admin
     */
    public String toString(){
        String s = "String tekst for Admin toString ikke lagt inn";
        return s;
    }
            
}