package insurancecompany.model.people;
import insurancecompany.misc.EmployeeType;
import insurancecompany.model.properties.Address;
import java.io.Serializable;
/**
 * ServiceWorker class. This is an employee of the insurance company.
 * The service worker registers and views customers, insurances and claims.
 * 
 * @author Carl
 */
public class ServiceWorker extends Employee implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * Constructs a ServiceWorker with the specified parameters
     * 
     * @param firstname the first name of this service worker
     * @param lastname the last name of this service worker
     * @param personalNumber the personal number of this service worker 
     * @param email the email address of this service worker
     * @param address the address of this service worker
     * @param phone the phone number of this service worker
     */
    public ServiceWorker(String firstname, String lastname, String personalNumber, String email, Address address, String phone) {
        super(firstname, lastname, personalNumber, email, address, phone);
    }
    
    public String getType() {
        return EmployeeType.SERVICE_WORKER.toString();
    }

    /**
     * Returns a string representation of this ServieWorker. The string
     * representation consists of each field with a short description separated
     * by a new line.
     * @return a string representation of this serviceworker
     */
    @Override
    public String toString(){
        return super.toString();
    }
            
}