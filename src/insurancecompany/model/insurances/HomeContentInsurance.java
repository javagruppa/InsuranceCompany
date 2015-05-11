/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package insurancecompany.model.insurances;

import insurancecompany.model.properties.Home;
import insurancecompany.misc.hometypes.HomeType;
import insurancecompany.misc.coverages.HomeInsuranceCoverage;
import java.io.Serializable;


/**
 *
 * @author Carl
 */
public class HomeContentInsurance extends PropertyInsurance implements Serializable {
    private static final long serialVersionUID = 1L;
    
    /** the home this insurance is for */
    private Home home;
    /** the hometype this insurance is for */
    private HomeType hometype;
    /** the coverage of this insurance */
    private HomeInsuranceCoverage coverage;
    
    /**
     * Constructs a new home contents insurance with the specified customerId,
     * excess, home, hometype and coverage. Active is set to true.
     * Date is set to the current date.
     * InsuranceId is automatically set to nextInsuranceId.
     * @param customerId the ID of the customer who owns this insurance
     * @param excess the excess of this insurance
     * @param home the home this insurance is for
     * @param hometype the hometype this insurance is for 
     * @param coverage the coverage of this insurance
     */
    public HomeContentInsurance(int customerId, int excess, Home home,
            HomeType hometype, HomeInsuranceCoverage coverage){
        super(customerId, excess);
        this.home = home;
        this.hometype = hometype;
        this.coverage = coverage;
    }
    
    
    
    public Home getHome(){
        return home;
    }
}
