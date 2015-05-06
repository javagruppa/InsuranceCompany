/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package insurancecompany.misc;

/**
 *
 * @author Carl
 */
public class Address {
    // Fields are final as they are used in the overriden equals method, and specifically to identify Property objects
    private final String street; // Includes street name, number and letter.
    private final int zipCode;
    private final String city;
    
    public Address(String street, int zipCode, String city) {
        this.street = street;
        this.zipCode = zipCode;
        this.city = city;
    }
     
    public String getStreet() {
        return street;
    }
    
    public int getZipCode() {
        return zipCode;
    }
    
    public String getCity() {
        return city;
    }
    
    public String toString(){
            String s = "" + street + "\n" + zipCode + " " + city;
            return s;
    }
    
        /**
     * Indicates whether some other address is equal to this one. The result 
     * is true if and only if the argument is not null and is an Address 
     * object that contains the same insuranceId value as this object.
     * 
     * @param obj the object to compare with
     * @return true if the objects are the same; false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if(obj != null && obj instanceof Address) {
            Address other = (Address) obj;
            return (getStreet() == other.getStreet()) 
                    && (getZipCode() == other.getZipCode())
                    && (getCity() == other.getCity());
        } else {
            return false;
        }   
    }
}
