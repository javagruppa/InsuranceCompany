package insurancecompany.model.people;

import insurancecompany.model.properties.Address;
import java.io.*;

/**
 * Employee class. This is an employee of the insurance company. This is a
 * super class of Admin, CaseWorker and ServiceWorker, and the employee is
 * either of these.
 * 
 * @author Carl
 */
public abstract class Employee extends Person implements Serializable {
    /** SerialVersionUID used to identify this class for object IO */
    private static final long serialVersionUID = 1L;
    /** The next employee ID */
    private static int nextEmployeeId = 1000000;
    /** The file path of the file the customer IDs are saved to */
    private static String employeeIdFileName = "src/insurancecompany/resources/nextidnumbers/employeeId.dta";
    
    /** passwords */
    private String passwordSalt; // to be used in future upgrades
    private String passwordHash; // to be used in future upgrades
    
    /** The employee ID */
    private final int employeeId;
    
    public abstract String getType();
    
    /**
     * Constructs an employee with the specified parameters
     * 
     * @param firstname the first name of this employee
     * @param lastname the last name of this employee
     * @param personalNumber the personal number of this employee
     * @param email the email address of this employee
     * @param address the address of this employee
     * @param phone the phone number of this employee
     */
    public Employee(String firstname, String lastname, String personalNumber,
            String email, Address address, String phone) {
        super(firstname, lastname, personalNumber, email, address, phone);
        // Sets the employee ID to nextEmployeeId, and adds 1 to nextEmployeeId
        employeeId = nextEmployeeId++;
    }
    
       /**
     * Returns a string representation of this insurance. The string
     * representation consists of each field with a short description separated
     * by a new line.
     * 
     * @return a string representation of this insurance
     */
    @Override
    public String toString() {
        // Creates a StringBuilder which will be returned at the end of the 
        // method.
        StringBuilder result = new StringBuilder();
        // Appends the fields with appropriate sentences.
        result.append(getEmployeeId());
        result.append("\n").append(super.toString());
        // Returns the string.
        return result.toString();
    }
    
    /**
     * Saves next ID to file
     * 
     * @throws IOException 
     */
    public static void saveNextIdToFile() throws IOException {
        try (DataOutputStream dos = new DataOutputStream(
                new BufferedOutputStream(
                        new FileOutputStream(employeeIdFileName)))) {
            dos.writeInt(nextEmployeeId);
        }
    }
    
    /**
     * Reads the next ID from file
     * 
     * @throws IOException 
     */
    public static void readNextIdFromFile() throws IOException {
        try (DataInputStream dis = new DataInputStream(
                new BufferedInputStream(
                        new FileInputStream(employeeIdFileName)))) {
            nextEmployeeId = dis.readInt();
        }
    }
    
    /**
     * Returns the ID of this employee
     * @return the ID
     */
    @Override
    public int getId() {
        return getEmployeeId();
    }

    /**
     * Returns the employeeId of this employee
     * 
     * @return the employeeId
     */
    public int getEmployeeId() {
        return employeeId;
    }
    
}
