package insurancecompany.model.properties;

    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import insurancecompany.model.properties.Address;
import insurancecompany.model.properties.Property;
import insurancecompany.model.properties.PropertyMaterial;
import java.io.Serializable;

/**
 *
 * @author Sindre
 */
public class HolidayHome extends Property implements Serializable {
    private static final long serialVersionUID = 1L;
    
    
    
    
    /**
     * Constructs a new holiday home with the specified address. Area and year 
     * are set to 0. Material and type are set to null.
     * 
     * @param address the address of this property
     */
    public HolidayHome(Address address, PropertyMaterial material, int area,
            int year, boolean rental) {
        super(address, material, area, year, rental);
        
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
        // Returns the string.
        return result.toString();
    }
}
