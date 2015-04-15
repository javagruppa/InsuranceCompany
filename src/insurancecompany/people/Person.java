
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
public class Person {
    protected int personalNumber;
    protected String firstname;
    protected String lastname;
    protected Address address;
    protected String email;
    protected int phone;
    
    
    
    public Person(String firstname, String lastname, int personalNumber, String email, Address address, int phone) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.personalNumber = personalNumber;
        this.email = email;
        this.address = address;
        this.phone = phone;
        
    }
    
    public String getFirstname(){
        return firstname;
    }
    
    public String getLastname(){
        return lastname;
    }
    
    public int getPersonalNumber(){
        return personalNumber;
    }
    
    public String getEmail(){
        return email;
    }
    
    public String getAddressString(){
        return address.toString();
    }
    
    public String toString() {
        String s = firstname + "\n" + lastname + "\n" + "Fødselsnr: " + personalNumber +
                "/nEpost-adresse: " + email + "\n" + address.toString();
        return s;
    }
}
