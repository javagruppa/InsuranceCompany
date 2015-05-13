package insurancecompany.model.people;
import insurancecompany.model.properties.Address;
import java.io.Serializable;
/**
 * person object Admin.
 * This is an administrating type of employee with more rights than the
 * caseworker and the serviceworker.
 * 
 * @author Carl
 */
public class Admin extends Employee implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * Constructs an Admin-object with the specified parameters
     * 
     * @param firstname this Admins first name
     * @param lastname this Admins last name
     * @param personalNumber this Admins personal number
     * @param email this Admins email address
     * @param address this Admins address
     * @param phone this Admins phone number
     */
    public Admin(String firstname, String lastname, String personalNumber, String email, Address address, String phone) {
        super(firstname, lastname, personalNumber, email, address, phone);
    }

    /**
    * Returns a string representation of this Admin. The string
     * representation consists of each field with a short description separated
     * by a new line.
     * @return a string representation of this admin
     */
    @Override
    public String toString(){
        return super.toString();
    }
            
}