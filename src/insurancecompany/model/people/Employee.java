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
    
    /** SerialVersionUID used to identify this class for object IO. */
    private static final long serialVersionUID = 1L;
    /** The next employee ID. */
    private static int nextEmployeeId = 1000000;
    /** The file path of the file the customer IDs are saved to. */
    private static String employeeIdFileName = "src/insurancecompany/resources/nextidnumbers/employeeId.dta";
    /** Can be used for passwords in future upgrades. */
    private String passwordSalt;
    private String passwordHash;
    /** The employee ID. */
    private final int employeeId;
    
    /** @return The type of employee in form of a String. */
    public abstract String getType();
    
    /**
     * Constructs an employee with the specified parameters.
     * 
     * @param firstname The first name of this employee.
     * @param lastname The last name of this employee.
     * @param personalNumber The personal number of this employee.
     * @param email The email address of this employee.
     * @param address The address of this employee.
     * @param phone The phone number of this employee.
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
     * @return A string representation of this insurance.
     */
    @Override
    public String toString() {
        // Creates a StringBuilder which will be returned at the end of the 
        // method.
        StringBuilder result = new StringBuilder();
        // Appends the fields with appropriate sentences.
        result.append(employeeId);
        result.append("\n").append(super.toString());
        // Returns the string.
        return result.toString();
    }
    
    /**
     * Saves next ID to file.
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
     * Reads the next ID from file.
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
    
    /** @return The employee ID of the employee. */
    @Override
    public int getId() {
        return employeeId;
    }
}