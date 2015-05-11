/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package insurancecompany.model.people;

import insurancecompany.model.properties.Address;
import insurancecompany.model.vehicles.Vehicle;

import java.util.ArrayList;
import java.io.*;
import java.util.List;
/**
 *
 * @author Carl
 */
public class Customer extends Person {
    
    private static int nextCustomerId = 1000000;
    private static String customerIdFileName = "/nextIdNumbers/customerId.dta";
    
    public static final double totalCustomerDiscount = 0.1; // 10% rabbat for totalkunde
    private boolean totalCustomer;
    
    private String passwordSalt;
    private String passwordHash;
    
    private final int customerId;
    private List<Vehicle> vehicles;
    
    public Customer(String firstname, String lastname, long personalNumber, String email, Address address, String phone) {
        super(firstname, lastname, personalNumber, email, address, phone);
        customerId = nextCustomerId++;
    }
    
    @Override
    public int getId(){
        return customerId;
    }
    
    @Override
    public String toString(){
        String s = "Kundenummer: " + customerId + "\n";
        s = super.toString();
        return s;
    }
     /**
     * Indicates whether some other customer is equal to this one. The result 
     * is true if and only if the argument is not null and is a Customer 
     * object that contains the same employeeId value as this object.
     * 
     * @param obj the object to compare with
     * @return true if the objects are the same; false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if(obj != null && obj instanceof Customer) {
            Customer other = (Customer) obj;
            return getId()== other.getId();
        } else {
            return false;
        }   
    }
    
    /**
     * Returns a hash code value for this customer. This method is supported for 
     * the benefit of hash tables such as those provided by HashMap.
     * @return the hash code
     */
    @Override 
    public int hashCode() {
        final int prime = 31;
        int result = 17;
        result = prime * result + getId();
        return result;
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

    /**
     * @return the totalCustomer
     */
    public boolean isTotalCustomer() {
        return totalCustomer;
    }

    /**
     * @param totalCustomer the totalCustomer to set
     */
    public void setTotalCustomer(boolean totalCustomer) {
        this.totalCustomer = totalCustomer;
    }
    
}
