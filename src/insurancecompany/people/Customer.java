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
public class Customer extends Person {
    private int customerID;
    private Vehicle = null;
    public static int nextNumber = 100;
    
    public Customer(String firstname, String lastname, int personalNumber, String email, Address address, int phone) {
        super(firstname, lastname, personalNumber, email, address, phone);
        customerID = nextNumber++;
    }
    
    public int getCustomerID(){
        return customerID;
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
    
    public void setAddress(Adress newAddress){
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
