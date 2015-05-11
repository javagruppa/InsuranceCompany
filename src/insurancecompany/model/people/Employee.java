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
    
    public Employee(String firstname, String lastname, String personalNumber,
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
    
    
    @Override
    public int getId() {
        return employeeId;
    }
    
}
