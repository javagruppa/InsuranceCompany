package insurancecompany.model.people;

import insurancecompany.model.properties.Address;
import insurancecompany.model.vehicles.Vehicle;
import java.io.Serializable;

/**
 * Class VehicleOwner. This is a person object that owns a vehicle, but is not
 * a customer of the company. Used for owners of vehicles that are part of the
 * same accident as a customer. For claims.
 * 
 * @author Sindre
 */
public class VehicleOwner extends Person implements Serializable {
    /** SerialVersionUID used to identify this class for object IO */
    private static final long serialVersionUID = 1L;
    
    /**
     * Constructs a VehicleOwner with the specified parameters
     * 
     * @param firstname this vehicle owners first name
     * @param lastname this vehicle owners last name
     * @param personalNumber this vehicle owners personal number
     * @param email this vehicle owners email address
     * @param address this vehicle owners address
     * @param phone this vehicle owners phone number
     */
    public VehicleOwner(String firstname, String lastname, String personalNumber, String email, Address address, String phone) {
        super(firstname, lastname, personalNumber, email, address, phone);
    }
    
    /**
     * Returns a string representation of this VehicleOwner. The string
     * representation consists of each field with a short description separated
     * by a new line.
     * @return a string representation of this vehicle owner
     */
    @Override
    public String toString() {
        // Creates a StringBuilder which will be returned at the end of the 
        // method.
        StringBuilder result = new StringBuilder();
        // Appends the fields with appropriate sentences.
        result.append("EIER AV KJØRETØY");
        result.append("\n").append(super.toString());
        // Returns the string.
        return result.toString();
    }

    /**
     * Returns the  ID, set to 0.
     * @return 0
     */
    @Override
    public int getId() {
        return 0;
    }
        
}