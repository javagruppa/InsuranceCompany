package insurancecompany.model.people;

import insurancecompany.misc.EmployeeType;
import insurancecompany.model.properties.Address;
import java.io.Serializable;

/**
 * CaseWorker class. This is an employee of the insurance company.
 * The case worker processes claims and decides the outcome of these.
 * 
 * @author Carl
 */
public class CaseWorker extends Employee implements Serializable {
    /** SerialVersionUID used to identify this class for object IO */
    private static final long serialVersionUID = 1L;

    /**
     * Constructs a CaseWorker object with the specified parameters.
     * 
     * @param firstname The first name of this case worker.
     * @param lastname The last name of this case worker.
     * @param personalNumber The personal number of this case worker.
     * @param email The email address of this case worker.
     * @param address The address of this case worker.
     * @param phone The phone number of this case worker.
     */
    public CaseWorker(String firstname, String lastname, String personalNumber, 
            String email, Address address, String phone) {
        super(firstname, lastname, personalNumber, email, address, phone);
    }
    
    /** @return The type of employee in form of a String. */
    @Override
    public String getType() {
        return EmployeeType.CASE_WORKER.toString();
    }      
}