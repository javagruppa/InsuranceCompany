/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package insurancecompany.datastructures;

import insurancecompany.people.*;
import insurancecompany.claims.*;

import java.util.ArrayList;

/**
 *
 * @author André
 */
public class ControlCenter {
    
    CustomerRegister customers;
    EmployeeRegister employees;
    ClaimRegister claims;
    
    public ControlCenter() {
        
    }
    
    public Customer getCustomer(int insuranceNumber) {
        Customer customer = customers.findCustomerById(insuranceNumber);
        return customer;
    }
    
    
    
}