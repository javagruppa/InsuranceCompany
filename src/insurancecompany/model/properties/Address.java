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
    
    /**
     * Optional constructor which leaves out the city field. Is used in
     * CarClaimFormView.
     * @param street
     * @param zipCode 
     */
    public Address(String street, int zipCode) {
        this.street = street;
        this.zipCode = zipCode;
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
    
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Adresse:\n");
        if (street != null) {
            sb.append("Gate: ").append(street).append("\n");
        }
        if (zipCode != 0) {
            sb.append("Postnummer: ").append(zipCode).append("\n");
        }
        if (city != null) {
            sb.append("By: ").append(city).append("\n");
        }
        return sb.toString();
    }
    
}
