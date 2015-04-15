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
public class Employee extends Person {
    private int employeeNumber;
    
    public Employee(String firstname, String lastname, int personalNumber, String email, Address address, int employeeNumber, int phone) {
        super(firstname, lastname, personalNumber, email, address, phone);
        this.employeeNumber = employeeNumber;
    }
    
    public String toString(){
        String s = "";
        return s;
    }
    
    public void setEmployeeNumber(int employeeNumber){
        this.employeeNumber = employeeNumber;
    }
    
}
