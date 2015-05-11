/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package insurancecompany.model.datastructures;

import insurancecompany.model.people.Customer;
import insurancecompany.model.properties.Address;
import java.io.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 *
 * @author André
 */
public class CustomerRegister {
    
    private static String customersFilePath = "insurancecompany/resources/datastructures/customerSet.dta";
    
    private Set<Customer> customers;
    
    public CustomerRegister() {
        customers = new HashSet<Customer>();
    }
    
    /**
     * Adds a new customer to this register if it does not already exist.
     * @param customer
     * @return 
     */
    public void addCustomer(Customer customer) {
        customers.add(customer);
    }
    
    /**
     * Returns a customer matching the customer id.
     * @param customerId
     * @return 
     */
    public Customer findCustomerById(int customerId) {
        for (Customer customer : customers) {
            if (customer.getCustomerId() == customerId) {
                return customer;
            }
        }
        return null;
    }
    
    /**
     * Returns the first customer matching the given name.
     * @param name
     * @return 
     */
    public Customer findCustomerByName(String name) {
        for (Customer customer : customers) {
            if (customer.getName().equals(name)) {
                return customer;
            }
        }
        return null;
    }
    
    /**
     * Returns an ArrayList containing customers living at given address.
     * @param address
     * @return 
     */
    public ArrayList<Customer> findCustomersByAdress(Address address) {
        ArrayList<Customer> result = new ArrayList<Customer>();
        for (Customer customer : customers) {
            if (customer.getAddress().equals(address)) {
                result.add(customer);
            }
        }
        // Returns null if no matches are found:
        if (result.isEmpty()) {
            return null;
        } else {
            // Returns the newly created list otherwise:
            return result;
        }
    }
    /**
     * Writes this registers set of customers to file.
     * @throws IOException 
     */
    public void writeCustomersToFile() throws IOException{
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(customersFilePath))) {
            oos.writeObject(customers);
        }
    }
    /**
     * Reads a set of customers from file and stores them in the set in this register.
     * @throws IOException
     * @throws ClassNotFoundException 
     */
    public void readCustomersFromFile() throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream(customersFilePath))) {
            customers = (HashSet<Customer>) ois.readObject();        
        }
    }    
}
