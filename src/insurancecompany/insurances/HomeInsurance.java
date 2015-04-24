/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package insurancecompany.insurances;

import insurancecompany.properties.Home;

/**
 *
 * @author Sindre
 */
public class HomeInsurance extends PropertyInsurance {
    /** The home this insurance is for. */
    private Home home;
    
    /**
     * Constructs a new home insurance with the specified buildingAmount, 
     * contentAmount, customerId, excess and home. Active is set to true. Date 
     * is set to the current date.
     * 
     * @param buildingAmount the building insurance amount of this insurance
     * @param contentAmount the content insurance amount of this insurance
     * @param customerId the id of the customer who owns this insurance
     * @param excess the excess of this insurance
     * @param home the holiday home this insurance is for
     */
    public HomeInsurance(int buildingAmount, int contentAmount, 
            int customerId, int excess, Home home) {
        super(buildingAmount, contentAmount, customerId, excess);
        this.home = home;  
    }
    
    /**
     * Returns the home this insurance is for.
     * 
     * @return the home this insurance is for
     */
    public Home getHome() {
        return home;
    }
}