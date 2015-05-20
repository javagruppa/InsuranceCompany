package insurancecompany.model.people;

import insurancecompany.model.properties.Address;
import java.io.*;

/**
 * This is an insurance customer of the company.
 * 
 * @author Carl
 * @author Sindre
 * 
 * @since 15.05.2015
 */
public class Customer extends Person implements Serializable {
    /** SerialVersionUID used to identify this class for object IO */
    private static final long serialVersionUID = 1L;
    /** The next customer ID */
    private static int nextCustomerId = 1000000;
    /** The file path of the file the customer IDs are saved to */
    private static String customerIdFileName = "src/insurancecompany/resources/nextidnumbers/customerId.dta";
    /** 10% off for total customers (3 or more different insurance types */
    public static final double TOTAL_CUSTOMER_DISCOUNT = 0.1;
    /** whether or not this customer is a total customer */
    private boolean totalCustomer;
    /** Can be used for passwords in future upgrades. */
    private String passwordSalt;
    private String passwordHash;
    /** The ID of this customer. */
    private final int customerId;
    
    /**
     * Constructs a customer with the specified parameters.
     * 
     * @param firstname This customers first name.
     * @param lastname This customers last name.
     * @param personalNumber This customers personal number.
     * @param email This customers email address.
     * @param address This customers address.
     * @param phone This customers phone number.
     */
    public Customer(String firstname, String lastname, String personalNumber, 
            String email, Address address, String phone) {
        super(firstname, lastname, personalNumber, email, address, phone);
        customerId = nextCustomerId++;
    }
    
    /** @return The ID of this customer. */
    @Override
    public int getId(){
        return customerId;
    }
    
    /**
     * Returns a string representation of this Customer. The string
     * representation consists of each field with a short description separated
     * by a new line.
     * 
     * @return A string representation of this customer.
     */
    @Override
    public String toString() {
        // Creates a StringBuilder which will be returned at the end of the 
        // method.
        StringBuilder result = new StringBuilder();
        // Appends the fields with appropriate sentences.
        result.append("Kundenummer: ").append(customerId);
        result.append("\n").append(super.toString());
        // Returns the string.
        return result.toString();
    }
    
    /**
     * Saves the new customer ID to file
     * 
     * @throws IOException exception that is thrown
     */
    public static void saveNextIdToFile() throws IOException {
        try (DataOutputStream dos = new DataOutputStream(
                new BufferedOutputStream(
                        new FileOutputStream(customerIdFileName)))) {
            dos.writeInt(nextCustomerId);
        }
    }
    
    /**
     * Reads next customer ID from file
     * 
     * @throws IOException 
     */
    public static void readNextIdFromFile() throws IOException {
        try (DataInputStream dis = new DataInputStream(
                new BufferedInputStream(
                        new FileInputStream(customerIdFileName)))) {
            nextCustomerId = dis.readInt();
        }
    }    

    /** @return True if the customer is a total customer. */
    public boolean isTotalCustomer() {
        return totalCustomer;
    }

    /**
     * @param totalCustomer True if the customer is to be set as total customer.
     */
    public void setTotalCustomer(boolean totalCustomer) {
        this.totalCustomer = totalCustomer;
    }
}
