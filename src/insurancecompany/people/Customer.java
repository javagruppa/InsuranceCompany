/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package insurancecompany.people;

import insurancecompany.misc.Address;
import insurancecompany.vehicles.Vehicle;

import java.util.ArrayList;
import java.io.*;
/**
 *
 * @author Carl
 */
public class Customer extends Person {
    
    private static int nextCustomerId = 1000000;
    private static String customerIdFileName = "/nextIdNumbers/customerId.dta";
    
    private String passwordSalt;
    private String passwordHash;
    
    private int customerId;
    private ArrayList<Vehicle> vehicles;
    
    public Customer(String firstname, String lastname, long personalNumber, String email, Address address, String phone) {
        super(firstname, lastname, personalNumber, email, address, phone);
        customerId = nextCustomerId++;
    }
    
    public int getCustomerId(){
        return customerId;
    }
    

    public String toString(){
        String s = "Kundenummer: " + customerId + "\n";
        s = super.toString();
        return s;
    }
    
    public static void saveNextIdToFile() throws IOException {
        try (DataOutputStream dos = new DataOutputStream(
                new BufferedOutputStream(
                        new FileOutputStream(customerIdFileName)))) {
            dos.writeInt(nextCustomerId);
        }
    }
    
    public static void readNextIdFromFile() throws IOException {
        try (DataInputStream dis = new DataInputStream(
                new BufferedInputStream(
                        new FileInputStream(customerIdFileName)))) {
            nextCustomerId = dis.readInt();
        }
    }    
    
}
