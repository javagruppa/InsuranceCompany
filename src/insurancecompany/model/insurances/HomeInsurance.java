/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package insurancecompany.model.insurances;

import insurancecompany.model.properties.Home;
import insurancecompany.misc.hometypes.HomeType;
import java.io.Serializable;       

/**
 *
 * @author Sindre
 */
public class HomeInsurance extends PropertyInsurance implements Serializable {
    private static final long serialVersionUID = 1L;
    
    /** The home this insurance is for. */
    private Home home;
    /** The type of home this insurance is for */
    private HomeType hometype;
    
    
    /**
     * Constructs a new home insurance with the specified customerId, excess
     * and home. Active is set to true. Date is set to the current date.
     * InsuranceId is automatically set to nextInsuranceId.
     * 
     * @param customerId the id of the customer who owns this insurance
     * @param excess the excess of this insurance
     * @param home the holiday home this insurance is for
     * @param hometype the type of home this insurance is for
     */
    public HomeInsurance(int customerId, int excess, Home home, HomeType hometype) {
        super(customerId, excess);
        this.home = home;
        this.hometype = hometype;
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