/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package insurancecompany.people;

import insurancecompany.misc.Address;

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
    
    private int employeeId;
    
    public Employee(String firstname, String lastname, long personalNumber,
            String email, Address address, int phone) {
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
    
    public int getEmployeeId() {
        return employeeId;
    }
    
}
