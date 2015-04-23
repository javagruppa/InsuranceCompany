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
public class Employee extends Person {
    
    private static int nextEmployeeId = 1000000;
    private static String EmployeeIdFileName = "/nextIdNumbers/claimId.dta";
    
    private int employeeId;
    
    public Employee(String firstname, String lastname, int personalNumber,
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
                        new FileOutputStream(claimIdFileName)))) {
            dos.writeInt(nextClaimId);
        }
    }
    
    public static void readNextIdFromFile() throws IOException {
        try (DataInputStream dis = new DataInputStream(
                new BufferedInputStream(
                        new FileInputStream(claimIdFileName)))) {
            nextClaimId = dis.readInt();
        }
    }
    
    public int getEmployeeId() {
        return employeeId;
    }
    
}
