package insurancecompany.model.people;

import insurancecompany.misc.DateUtility;
import insurancecompany.model.properties.Address;
import java.io.Serializable;
import java.util.Calendar;

/**
 * Abstract class Person. This is the super class of all the person objects.
 * 
 * @author Carl
 * @author Sindre
 */
public abstract class Person implements Serializable {
    
    /** SerialVersionUID used to identify this class for object IO. */
    private static final long serialVersionUID = 1L;
    /** The personal number of the person. */
    private String personalNumber;
    /** The first name of the person. */
    private String firstName;
    /** The last name of the person. */
    private String lastName;
    /** An object containing the persons address. */
    private Address address;
    /** The e-mail address for the person. */
    private String email;
    /** The phone number of the person. */
    private String phone;
    /** The date this person is registered. */
    private Calendar registeredDate;
    /** Whether this person is active. */
    private boolean active;
    
    /**
     * Constructs a person object with specified parameters.
     * 
     * @param firstName The first name of the person.
     * @param lastName The last name of the person.
     * @param personalNumber The persons personal number.
     * @param email The email address of the person.
     * @param address The address of the person, as an object.
     * @param phone The phone number of the person.
     */
    public Person(String firstName, String lastName, String personalNumber, 
            String email, Address address, String phone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.personalNumber = personalNumber;
        this.email = email;
        this.address = address;
        this.phone = phone;
        this.active = true;
        registeredDate = Calendar.getInstance();
    }
    
    /** @return The persons customer ID. */
    public abstract int getId();
    
    /** @return The first name and the last name seperated with a space. */
    public String getName(){
        return firstName + " " + lastName;
    }
    
    /** @return The first name as a string. */
    public String getFirstName(){
        return firstName;
    }
    /** @return The last name as a string. */
    public String getLastName(){
        return lastName;
    }
    
    /** @return The personal number as an integer. */
    public String getPersonalNumber(){
        return personalNumber;
    }
    
    /** @return The email address as a string. */
    public String getEmail(){
        return email;
    }
    
    /** @return The toString of the address of the person. */
    public String getAddressString(){
        return getAddress().toString();
    }
    
    /**
     * Returns a string representation of the person.
     * All fields included, as well as the toString from the per address object.
     * 
     * @return A string representation of this person.
     */
    @Override
    public String toString() {
        // Creates a StringBuilder which will be returned at the end of the 
        // method.
        StringBuilder result = new StringBuilder();
        // Appends the fields with appropriate sentences.
        result.append("Navn: ").append(firstName).append(" ").append(lastName);
        result.append("\nPersonnummer: ").append(personalNumber);
        if (email != null) {
            result.append("\nE-post adresse: ").append(email);
        }
        result.append("\nTelefonnummer: ").append(phone);
        result.append("\nRegistrert dato: ").append(DateUtility
                .NORWEGIAN_DATE_FORMAT.format(registeredDate.getTime()));
        result.append("\nAktiv: ").append(active ? "Ja" : "Nei");
        result.append("\n\n").append(address.toString());
        // Returns the string.
        return result.toString();
    }

    /** @return The address of the person. */
    public Address getAddress() {
        return address;
    }

    /** @return The phone number of the person. */
    public String getPhone() {
        return phone;
    }
    
    /**
     * Indicates whether an object is equal to this person. The result 
     * is true if and only if the argument is not null and is a Person 
     * object that contains the same personal number as this object.
     * 
     * @param obj the object to compare with
     * 
     * @return True if the objects are the same; false otherwise.
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
     * 
     * @return The hash code.
     */
    @Override 
    public int hashCode() {
        final int prime = 31;
        int result = 17;
        result = prime * result + getPersonalNumber().hashCode();
        return result;
    }

    /** @return The date the person was registrated. */
    public Calendar getRegisteredDate() {
        return registeredDate;
    }

    /** @return True if the person is active. */
    public boolean isActive() {
        return active;
    }

    /** @param active True if the person is to be set as active. */
    public void setActive(boolean active) {
        this.active = active;
    }
}
