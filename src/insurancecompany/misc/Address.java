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
    protected String street;
    protected int zipcode;
    protected String city;
    
    public Address(String street, int zipcode, String city) {
        this.street = street;
        this.zipcode = zipcode;
        this.city = city;
    }
    
    public toString(){
            String s = "" + street + "\n" + zipcode + " " + city;
            return s;
}
