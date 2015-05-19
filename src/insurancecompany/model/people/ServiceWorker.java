package insurancecompany.model.people;

import insurancecompany.misc.EmployeeType;
import insurancecompany.model.properties.Address;
import java.io.Serializable;

/**
 * ServiceWorker class. This is an employee of the insurance company.
 * The service worker registers and views customers, insurances and claims.
 * 
 * @author Carl
 * @author Sindre
 */
public class ServiceWorker extends Employee implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * Constructs a ServiceWorker with the specified parameters.
     * 
     * @param firstname The first name of this service worker.
     * @param lastname The last name of this service worker.
     * @param personalNumber The personal number of this service worker.
     * @param email The email address of this service worker.
     * @param address The address of this service worker.
     * @param phone The phone number of this service worker.
     */
    public ServiceWorker(String firstname, String lastname, 
            String personalNumber, String email, Address address, 
            String phone) {
        super(firstname, lastname, personalNumber, email, address, phone);
    }
    
    /** @return The type of employee in form of a String. */
    public String getType() {
        return EmployeeType.SERVICE_WORKER.toString();
    }  
}