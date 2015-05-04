
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
    private long personalNumber;
    /**the first name of the person*/
    private String firstName;
    /**the last name of the person*/
    private String lastName;
    /**an object containing the persons address*/
    private Address address;
    /**the e-mail address for the person*/
    private String email;
    /**the phone number of the person*/
    private String phone;
    
    
    /**
     * Constructs a person-object with specified parameters
     * @param firstname the first name of the person
     * @param lastname the last name of the person
     * @param personalNumber the persons personal number
     * @param email the email address of the person
     * @param address the address of the person, as an object
     * @param phone the phone number of the person
     */
    public Person(String firstName, String lastName, long personalNumber, String email, Address address, String phone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.personalNumber = personalNumber;
        this.email = email;
        this.address = address;
        this.phone = phone;
        
    }
    
    public String getName(){
        return firstName + " " + lastName;
    }
    
    /**
     * Returns the persons first name
     * @return the first name as a string
     */
    public String getFirstName(){
        return firstName;
    }
    /**
     * Returns the persons last name
     * @return the last name as a string
     */
    public String getLastName(){
        return lastName;
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
        return getAddress().toString();
    }
    
    /**
     * Returns a string representation of the person.
     * All fields included, as well as the toString from the per address object.
     * 
     * @return a string representation of this person
     */
    public String toString() {
        String s = firstName + "\n" + lastName + "\n" + "Fødselsnr: " + personalNumber +
                "/nEpost-adresse: " + email + "\n" + getAddress().toString();
        return s;
    }

    /**
     * @return the address
     */
    public Address getAddress() {
        return address;
    }

    /**
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }
}
