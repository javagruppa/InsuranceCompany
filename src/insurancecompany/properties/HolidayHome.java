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
public class HolidayHome extends Property {
    /** Whether the holiday home is a rental or not. */
    private boolean rental;
    
    /**
     * Constructor initializing the address and rental of this property.
     * @param address the address of this property
     * @param rental whether the holiday home is a rental or not
     */
    public HolidayHome(Address address, boolean rental) {
        super(address);
        this.rental = rental;
    }
}