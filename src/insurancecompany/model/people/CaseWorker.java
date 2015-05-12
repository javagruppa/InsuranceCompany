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
public class CaseWorker extends Employee implements Serializable {
    private static final long serialVersionUID = 1L;

    public CaseWorker(String firstname, String lastname, String personalNumber, String email, Address address, String phone) {
        super(firstname, lastname, personalNumber, email, address, phone);
    }

    /**
     * Returns a string representation of this CaseWorker. The string
     * representation consists of each field with a short description separated
     * by a new line.
     * @return a string representation of this caseworker
     */
    @Override
    public String toString(){
        return super.toString();
    }
            
}