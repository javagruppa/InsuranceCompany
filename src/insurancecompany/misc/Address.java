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
    private String street; // Includes street name, number and letter.
    private int zipCode;
    private String city;
    
    public Address(String street, int zipCode, String city) {
        this.street = street;
        this.zipCode = zipCode;
        this.city = city;
    }
    
    public void setStreet(String street) {
        this.street = street;
    }
    
    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }
    
    public void setCity(String city) {
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
