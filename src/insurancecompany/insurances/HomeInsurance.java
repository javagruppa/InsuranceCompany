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
     * Constructor initializing active, date, insuranceConditions and home of 
     * this insurance.
     * @param home the home this insurance is for
     */
    public HomeInsurance(Home home) {
        super();
        this.home = home;
    }
}