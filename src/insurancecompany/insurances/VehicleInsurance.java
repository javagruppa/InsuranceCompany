/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package insurancecompany.insurances;

import insurancecompany.people.Customer;

/**
 *
 * @author Sindre
 */
public abstract class VehicleInsurance extends Insurance {
    
    /**
     * Constructor initializing active, customer, date and insuranceConditions 
     * of this insurance.
     * @param customer customer who owns this insurance
     */
    public VehicleInsurance(Customer customer) {
        super(customer);
    }
}