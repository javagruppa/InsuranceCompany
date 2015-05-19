package insurancecompany.model.people;

import insurancecompany.model.properties.Address;
import java.io.Serializable;

/**
 * This is a person object that owns a vehicle, but is not a customer of the 
 * company. Used for owners of vehicles that are part of the same accident as a 
 * customer in claims.
 * 
 * @author Sindre
 */
public class VehicleOwner extends Person implements Serializable {
    /** SerialVersionUID used to identify this class for object IO */
    private static final long serialVersionUID = 1L;
    
    /**
     * Constructs a VehicleOwner with the specified parameters.
     * 
     * @param firstname This vehicle owners first name.
     * @param lastname This vehicle owners last name.
     * @param personalNumber This vehicle owners personal number.
     * @param email This vehicle owners email address.
     * @param address This vehicle owners address.
     * @param phone This vehicle owners phone number.
     */
    public VehicleOwner(String firstname, String lastname, 
            String personalNumber, String email, Address address, 
            String phone) {
        super(firstname, lastname, personalNumber, email, address, phone);
    }
    
    /** 
     * @return As the vehicle owner does not have an ID and this method has to 
     * be overrided this method returns 0. It's an abstract method in Person
     * because it is then possible to get the ID of a customer or employee 
     * without
     */
    @Override
    public int getId() {
        return 0;
    }
}