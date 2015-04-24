/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package insurancecompany.properties;

import insurancecompany.misc.Address;

/**
 *
 * @author Sindre
 */
public class Home extends Property {
    /**
     * Constructs a new home with the specified address. Area and year are 
     * set to 0. Material and type are set to null.
     * 
     * @param address the address of this property
     */
    public Home(Address address) {
        super(address);
    }
}