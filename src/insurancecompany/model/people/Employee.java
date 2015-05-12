/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package insurancecompany.model.people;

import insurancecompany.model.properties.Address;

import java.io.*;

/**
 *
 * @author Carl
 */
public abstract class Employee extends Person implements Serializable {
    private static final long serialVersionUID = 1L;
    //The next employee ID
    private static int nextEmployeeId = 1000000;
    //Filename of employee IDs
    private static String employeeIdFileName = "src/insurancecompany/resources/nextidnumbers/employeeId.dta";
    //To be used if the program is to be extended in the future
    private String passwordSalt;
    private String passwordHash;
    //The customer ID
    private final int employeeId;
    
    /** Decides if this employees position/hire is active.*/
    private boolean active;
    
    public Employee(String firstname, String lastname, String personalNumber,
            String email, Address address, String phone) {
        super(firstname, lastname, personalNumber, email, address, phone);
        employeeId = nextEmployeeId++;
        active = true;
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
        result.append("\nAktiv: ").append(active ? "Ja" : "Nei");
        // Returns the string.
        return result.toString();
    }
    
    /**
     * Saves next ID to file
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
     * @return the active
     */
    public boolean isActive() {
        return active;
    }

    /**
     * @param active the active to set
     */
    public void setActive(boolean active) {
        this.active = active;
    }

    /**
     * @return the employeeId
     */
    public int getEmployeeId() {
        return employeeId;
    }
    
}
