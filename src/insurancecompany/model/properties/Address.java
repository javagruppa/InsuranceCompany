package insurancecompany.model.properties;

import java.io.Serializable;

/**
 * This class represents an address object. It contains information about
 * the address and methods to get the information.
 * 
 * @author Carl
 * 
 * @since 15.05.2015
 */
public class Address implements Serializable {
    private static final long serialVersionUID = 1L;
    
    /** The street of the address. Includes street name, number and letter. */
    private String street;
    /** The zip code of the address. */
    private int zipCode;
    /** The city of the address. */
    private String city;
    
    /**
     * Creates an address with the specified parameters.
     * 
     * @param street The street of the address. Includes street name, number 
     * and letter.
     * @param zipCode The zip code of the address.
     * @param city The city of the address.
     */
    public Address(String street, int zipCode, String city) {
        this.street = street;
        this.zipCode = zipCode;
        this.city = city;
    }
    
    /**
     * Optional constructor which leaves out the city field. Is used in
     * CarClaimFormView.
     * 
     * @param street The street of the address. Includes street name, number 
     * and letter.
     * @param zipCode The zip code of the address.
     */
    public Address(String street, int zipCode) {
        this.street = street;
        this.zipCode = zipCode;
    }

    /** 
     * @return The street of the address. Includes street name, number and
     * letter.
     */
    public String getStreet() {
        return street;
    }
    
    /** @return The zip code of the address. */
    public int getZipCode() {
        return zipCode;
    }
    
    /** @return The city of the address. */
    public String getCity() {
        return city;
    }
    
    /**
     * Returns a string representation of this address. The string
     * representation consists of each field with a short description separated
     * by a new line.
     * 
     * @return A string representation of this property.
     */
    @Override
    public String toString(){
        StringBuilder result = new StringBuilder();
        result.append("Gate: ").append(street);
        result.append("\nPostnummer: ").append(zipCode);
        result.append("\nPoststed: ").append(city);
        return result.toString();
    }
}