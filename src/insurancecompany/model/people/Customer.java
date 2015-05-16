package insurancecompany.model.people;
import insurancecompany.model.properties.Address;
import insurancecompany.model.vehicles.Vehicle;
import java.util.ArrayList;
import java.io.*;
import java.util.List;
/**
 * Customer class. This is an insurance customer of the company.
 * 
 * @author Carl
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
    
    /** passwords */
    private String passwordSalt; // to be used in future updates
    private String passwordHash; // to be used in future updates
    
    /** this customers Id */
    private final int customerId;
    /** this customers Vehicles */
    private List<Vehicle> vehicles;
    
    /**
     * Constructs a customer with the specified parameters
     * @param firstname this customers first name
     * @param lastname this customers last name
     * @param personalNumber this customers personal number
     * @param email this customers email address
     * @param address this customers address
     * @param phone this customers phone number
     */
    public Customer(String firstname, String lastname, String personalNumber, String email, Address address, String phone) {
        super(firstname, lastname, personalNumber, email, address, phone);
        customerId = nextCustomerId++;
    }
    
    /**
     * Adds a vehicle to the customer.
     * @param vehicle 
     */
    public void addVehicle(Vehicle vehicle) {
        vehicles.add(vehicle);
    }
    
    /**
     * Returns the customerId for this customer
     * 
     * @return the customer ID
     */
    @Override
    public int getId(){
        return customerId;
    }
    
    /**
     * Returns a string representation of this Customer. The string
     * representation consists of each field with a short description separated
     * by a new line.
     * 
     * @return a string representation of this customer
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
     * @throws IOException 
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

    /**
     * Returns whether or not this customer is a total customer (3 or more
     * different insurances)
     * 
     * @return the boolean totalCustomer
     */
    public boolean isTotalCustomer() {
        return totalCustomer;
    }

    /**
     * Sets whether or not this customer is a total customer
     * @param totalCustomer the totalCustomer to set
     */
    public void setTotalCustomer(boolean totalCustomer) {
        this.totalCustomer = totalCustomer;
    }
}
