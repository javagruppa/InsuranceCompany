package insurancecompany.model.people;

import insurancecompany.misc.EmployeeType;
import insurancecompany.model.properties.Address;
import java.io.Serializable;

/**
 * This class represents an object representing an admin.
 * This is an administrating type of employee with more rights than the
 * caseworker and the serviceworker.
 * 
 * @author Carl
 */
public class Admin extends Employee implements Serializable {
    /** SerialVersionUID used to identify this class for object IO */
    private static final long serialVersionUID = 1L;

    /**
     * Constructs an Admin object with the specified parameters.
     * 
     * @param firstname this Admins first name
     * @param lastname this Admins last name
     * @param personalNumber this Admins personal number
     * @param email this Admins email address
     * @param address this Admins address
     * @param phone this Admins phone number
     */
    public Admin(String firstname, String lastname, String personalNumber, 
            String email, Address address, String phone) {
        super(firstname, lastname, personalNumber, email, address, phone);
    }
    
    /** @return The type of employee in form of a String. */
    @Override
    public String getType() {
        return EmployeeType.ADMIN.toString();
    }
}