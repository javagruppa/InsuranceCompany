package insurancecompany.model.people;
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
     * Constructs a CaseWorker object with the  specified parameters.
     * 
     * @param firstname the first name of this case worker
     * @param lastname the last name of this case worker
     * @param personalNumber the personal number of this case worker
     * @param email the email address of this case worker
     * @param address the address of this case worker
     * @param phone the phone number of this case worker
     */
    public CaseWorker(String firstname, String lastname, String personalNumber, String email, Address address, String phone) {
        super(firstname, lastname, personalNumber, email, address, phone);
    }

    /**
     * Returns a string representation of this CaseWorker. The string
     * representation consists of each field with a short description separated
     * by a new line.
     * @return a string representation of this caseworker
     */
    @Override
    public String toString(){
        return super.toString();
    }
            
}