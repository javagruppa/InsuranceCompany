/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package insurancecompany.model.properties;

import java.io.Serializable;

/**
 *
 * @author Carl
 */
public class Address implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String street; // Includes street name, number and letter.
    private int zipCode;
    private String city;
    
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
            String s = "\n" + street + "\n" + zipCode + " " + city;
            return s;
    }
    
}
