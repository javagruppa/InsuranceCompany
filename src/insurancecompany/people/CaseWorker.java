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
public class CaseWorker extends Employee {

    public CaseWorker(String firstname, String lastname, long personalNumber, String email, Address address, int phone) {
        super(firstname, lastname, personalNumber, email, address, phone);
    }

    public String toString(){
        String s = "";
        return s;
    }
            
}