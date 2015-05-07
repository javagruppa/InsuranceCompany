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
public abstract class Employee extends Person {
    
    private static int nextEmployeeId = 1000000;
    private static String employeeIdFileName = "/nextIdNumbers/employeeId.dta";
    
    private String passwordSalt;
    private String passwordHash;
    
    private final int employeeId;
    
    public Employee(String firstname, String lastname, long personalNumber,
            String email, Address address, String phone) {
        super(firstname, lastname, personalNumber, email, address, phone);
        employeeId = nextEmployeeId++;
    }
    
    public String toString(){
        String s = "";
        return s;
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
    
     /**
     * Indicates whether some other employee is equal to this one. The result 
     * is true if and only if the argument is not null and is a Employee 
     * object that contains the same employeeId value as this object.
     * 
     * @param obj the object to compare with
     * @return true if the objects are the same; false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if(obj != null && obj instanceof Employee) {
            Employee other = (Employee) obj;
            return getEmployeeId()== other.getEmployeeId();
        } else {
            return false;
        }   
    }
    
    /**
     * Returns a hash code value for this employee. This method is supported for 
     * the benefit of hash tables such as those provided by HashMap.
     * @return the hash code
     */
    @Override 
    public int hashCode() {
        final int prime = 31;
        int result = 17;
        result = prime * result + getEmployeeId();
        return result;
    }
    
    public int getEmployeeId() {
        return employeeId;
    }
    
}
