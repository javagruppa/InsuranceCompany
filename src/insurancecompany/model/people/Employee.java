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
    
    private static int nextEmployeeId = 1000000;
    private static String employeeIdFileName = "src/insurancecompany/resources/nextidnumbers/employeeId.dta";
    
    private String passwordSalt;
    private String passwordHash;
    
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
        result.append(employeeId);
        result.append("\n").append(super.toString());
        result.append("\nAktiv: ").append(active ? "Ja" : "Nei");
        // Returns the string.
        return result.toString();
    }
    
    public static void saveNextIdToFile() throws IOException {
        try (DataOutputStream dos = new DataOutputStream(
                new BufferedOutputStream(
                        new FileOutputStream(employeeIdFileName)))) {
            dos.writeInt(nextEmployeeId);
        }
    }
    
    public static void readNextIdFromFile() throws IOException {
        try (DataInputStream dis = new DataInputStream(
                new BufferedInputStream(
                        new FileInputStream(employeeIdFileName)))) {
            nextEmployeeId = dis.readInt();
        }
    }
    
    
    @Override
    public int getId() {
        return employeeId;
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
    
}
