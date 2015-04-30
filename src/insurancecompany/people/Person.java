
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
public abstract class Person {
    /**the person number of the person*/
    protected long personalNumber;
    /**the first name of the person*/
    protected String firstname;
    /**the last name of the person*/
    protected String lastname;
    /**an object containing the persons address*/
    protected Address address;
    /**the e-mail address for the person*/
    protected String email;
    /**the phone number of the person*/
    protected int phone;
    
    
    /**
     * Constructs a person-object with specified parameters
     * @param firstname the first name of the person
     * @param lastname the last name of the person
     * @param personalNumber the persons personal number
     * @param email the email address of the person
     * @param address the address of the person, as an object
     * @param phone the phone number of the person
     */
    public Person(String firstname, String lastname, int personalNumber, String email, Address address, int phone) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.personalNumber = personalNumber;
        this.email = email;
        this.address = address;
        this.phone = phone;
        
    }
    
    /**
     * Returns the persons first name
     * @return the first name as a string
     */
    public String getFirstname(){
        return firstname;
    }
    /**
     * Returns the persons last name
     * @return the last name as a string
     */
    public String getLastname(){
        return lastname;
    }
    
    /**
     * Returns the personal number of the person.
     * @return the personal number as an integer
     */
    public long getPersonalNumber(){
        return personalNumber;
    }
    
    /**
     * Returns the persons email address
     * @return the email address as a string
     */
    public String getEmail(){
        return email;
    }
    
    /**
     * Return the toString of the address object for this person
     * @return the address-objects toString for the specific person
     */
    public String getAddressString(){
        return address.toString();
    }
    
    /**
     * Returns a string representation of the person.
     * All fields included, as well as the toString from the per address object.
     * 
     * @return a string representation of this person
     */
    public String toString() {
        String s = firstname + "\n" + lastname + "\n" + "Fødselsnr: " + personalNumber +
                "/nEpost-adresse: " + email + "\n" + address.toString();
        return s;
    }
}
