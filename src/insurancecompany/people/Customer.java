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
    
    private int customerId;
    private ArrayList<Vehicle> vehicles;
    
    public Customer(String firstname, String lastname, int personalNumber, String email, Address address, int phone) {
        super(firstname, lastname, personalNumber, email, address, phone);
        customerId = nextCustomerId++;
    }
    
    public int getCustomerId(){
        return customerId;
    }
    
    public String getName(){
        return firstname + " " + lastname;
    }
    
    public String getEmail(){
        return email;
    }
    
    public int getPersonalNumber(){
        return personalNumber;
    }
    
    public String getAddress(){
        return address.toString();
    }
    
    public void setAddress(Address newAddress){
        this.address = newAddress;
    }
    
    public void setEmail(String newMail){
        this.email = newMail;
    }
    
    
    public String toString(){
        String s = "";
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
