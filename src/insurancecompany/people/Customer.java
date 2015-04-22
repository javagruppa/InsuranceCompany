/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package insurancecompany.people;
import insurancecompany.misc.Address;
import insurancecompany.vehicles.Vehicle;
import java.util.ArrayList;
/**
 *
 * @author Carl
 */
public class Customer extends Person {
    private int customerId;
    public static int nextNumber = 100;
    private ArrayList<Vehicle> vehicles;
    
    public Customer(String firstname, String lastname, int personalNumber, String email, Address address, int phone) {
        super(firstname, lastname, personalNumber, email, address, phone);
        customerId = nextNumber++;
    }
    
    public int getCustomerId(){
        return customerId;
    }
    
    public String getName(){
        return firstname + " " + lastname;
    }
    
    public String getEmail(){
        return email;
    }
    
    public int getPersonalNumber(){
        return personalNumber;
    }
    
    public String getAddress(){
        return address.toString();
    }
    
    public void setAddress(Address newAddress){
        this.address = newAddress;
    }
    
    public void setEmail(String newMail){
        this.email = newMail;
    }
    
    
    public String toString(){
        String s = "";
        return s;
    }
    
}
