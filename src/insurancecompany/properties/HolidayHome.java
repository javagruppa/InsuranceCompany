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
     * Constructs a new holiday home with the specified address. Area and year 
     * are set to 0. Material and type are set to null.
     * 
     * @param address the address of this property
     */
    public HolidayHome(Address address) {
        super(address);
        this.rental = false;
    }
    
    /**
     * Returns whether the holiday home is a rental or not.
     * 
     * @return whether the holiday home is a rental or not
     */
    public boolean getRental() {
        return rental;
    }
    
    /**
     * Sets a rental status to this holidat home.
     * 
     * @param rental whether the holiday home is a rental or not
     */
    public void setRental(boolean rental) {
        this.rental = rental;
    }
    
    /**
     * Returns a string representation of this property. The string
     * representation consists of each field with a short description separated
     * by a new line.
     * 
     * @return a string representation of this property
     */
    @Override
    public String toString() {
        // Creates a StringBuilder which will be returned at the end of the 
        // method.
        StringBuilder result = new StringBuilder();
        // Appends the fields with appropriate sentences.
        result.append(super.toString());
        result.append("\nUtleie: ").append(rental ? "Ja" : "Nei" );
        // Returns the string.
        return result.toString();
    }
}