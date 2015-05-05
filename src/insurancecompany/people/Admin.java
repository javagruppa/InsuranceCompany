/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package insurancecompany.people;
import insurancecompany.misc.Address;
/**
 *
 * @author Carl
 */
public class Admin extends Employee {

    /**
     * Constructs an Admin-object with the specified parameters
     * @param firstname first name of person
     * @param lastname last name of person
     * @param personalNumber personal number of person
     * @param email e-mail address of the person
     * @param address address of the person
     * @param phone  phone number for the person
     */
    public Admin(String firstname, String lastname, long personalNumber, String email, Address address, String phone) {
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