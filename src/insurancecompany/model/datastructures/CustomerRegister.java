/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package insurancecompany.model.datastructures;

import insurancecompany.model.properties.Address;
import java.io.Serializable;

import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author André
 */
public class CustomerRegister implements Serializable {
    
    private ArrayList<Customer> customers;
    
    public CustomerRegister() {
        customers = new ArrayList<Customer>();
    }
    
    /**
     * Adds a new customer to this register if it does not already exist.
     * @param customer
     * @return 
     */
    public boolean addCustomer(Customer customer) {
        if (customers.contains(customer)) {
            return false;
        } else {
            customers.add(customer);
            return true;
        }
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
    
}
