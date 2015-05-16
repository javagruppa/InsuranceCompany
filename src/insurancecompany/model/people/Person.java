package insurancecompany.model.people;
import insurancecompany.misc.DateUtility;
import insurancecompany.model.properties.Address;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.text.SimpleDateFormat;
/**
 * Abstract class Person. This is the super class of all person objects
 * registered in this program.
 * 
 * 
 *@author Carl
 */
public abstract class Person implements Serializable {
    /** SerialVersionUID used to identify this class for object IO */
    private static final long serialVersionUID = 1L;
    
    /**the personal number of the person*/
    private String personalNumber;
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
    /**The date this person is registered.*/
    private Calendar registeredDate;
    /** Wheter this person is active. */
    private boolean active;
    
    
    /**
     * Constructs a person-object with specified parameters
     * @param firstName the first name of the person
     * @param lastName the last name of the person
     * @param personalNumber the persons personal number
     * @param email the email address of the person
     * @param address the address of the person, as an object
     * @param phone the phone number of the person
     */
    public Person(String firstName, String lastName, String personalNumber, String email, Address address, String phone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.personalNumber = personalNumber;
        this.email = email;
        this.address = address;
        this.phone = phone;
        registeredDate = Calendar.getInstance();
        active = true;
    }
    
    /**
     * Returns the Id of the person object
     * @return the persons Id
     */
    public abstract int getId();
    
    /**
     * Returns the full name of this person
     * @return the first name and the last name
     */
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
    public String getPersonalNumber(){
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
    @Override
    public String toString() {
        // Creates a StringBuilder which will be returned at the end of the 
        // method.
        StringBuilder result = new StringBuilder();
        // Appends the fields with appropriate sentences.
        result.append("Navn: ").append(firstName).append(" ").append(lastName);
        result.append("\nPersonnummer: ").append(personalNumber);
        result.append("\nAdresse: ").append(address.toString());
        if (email != null) {
            result.append("\nE-post adresse: ").append(email);
        }
        result.append("\nTelefonnummer: ").append(phone);
        result.append("\nRegistrert dato: ").append(DateUtility.NORWEGIAN_DATE_FORMAT.format(registeredDate.getTime()));
        result.append("\nAktiv: ").append(active ? "Ja" : "Nei");
        // Returns the string.
        return result.toString();
    }

    /**
     * returns the adress object of this person
     * @return the address
     */
    public Address getAddress() {
        return address;
    }

    /**
     * returns te phone number of this person
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }
    
    /**
     * Indicates whether an object is equal to this person. The result 
     * is true if and only if the argument is not null and is a Person 
     * object that contains the same personal number as this object.
     * 
     * @param obj the object to compare with
     * @return true if the objects are the same; false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if(obj != null && obj instanceof Person) {
            Person other = (Person) obj;
            return getPersonalNumber().equals(other.getPersonalNumber());
        } else {
            return false;
        }   
    }
    
    /**
     * Returns a hash code value for this customer. This method is supported for 
     * the benefit of hash tables such as those provided by HashMap.
     * @return the hash code
     */
    @Override 
    public int hashCode() {
        final int prime = 31;
        int result = 17;
        result = prime * result + getPersonalNumber().hashCode();
        return result;
    }

    /**
     * @return the registeredDate
     */
    public Calendar getRegisteredDate() {
        return registeredDate;
    }

    /**
     * Returns whether or not the person is active
     * 
     * @return the boolean active
     */
    public boolean isActive() {
        return active;
    }

    /**
     * Sets whether or not this person is active
     * 
     * @param active the active to set
     */
    public void setActive(boolean active) {
        this.active = active;
    }
}
