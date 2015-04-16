/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package insurancecompany.datastructures;

import insurancecompany.people.*;

import java.util.ArrayList;

/**
 *
 * @author André
 */
public class CustomerRegister {
    
    private ArrayList<Customer> customers;
    
    public CustomerRegister() {
        customers = new ArrayList<Customer>();
    }
    
    /**
     * 
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
    
}
