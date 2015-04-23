/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package insurancecompany.datastructures;

import insurancecompany.people.*;
import insurancecompany.claims.*;

import java.util.ArrayList;

import java.awt.*;
import java.awt.event.*;

/**
 *
 * @author André
 */
public class Controller {
    
    CustomerRegister customers;
    EmployeeRegister employees;
    ClaimRegister claims;
    
    public Controller() {
        
    }
    
    public CustomerRegister getCustomers() {
        return customers;
    }
    
    public EmployeeRegister getEmployeees() {
        return employees;
    }
    
    public ClaimRegister getClaims() {
        return claims;
    }
    
    public Customer getCustomer(int insuranceNumber) {
        Customer customer = customers.findCustomerById(insuranceNumber);
        return customer;
    }
    
    
    
}
