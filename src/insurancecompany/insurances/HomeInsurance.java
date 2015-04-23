/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package insurancecompany.insurances;

import insurancecompany.people.Customer;
import insurancecompany.properties.Home;

/**
 *
 * @author Sindre
 */
public class HomeInsurance extends PropertyInsurance {
    /** The home this insurance is for. */
    private Home home;
    
    /**
     * Constructor.
     * @param customer the customer who owns this insurance
     * @param home the home this insurance is for
     */
    public HomeInsurance(Customer customer, Home home) {
        super(customer);
        this.home = home;
    }
}