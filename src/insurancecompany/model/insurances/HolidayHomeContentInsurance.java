/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package insurancecompany.model.insurances;

import insurancecompany.model.properties.HolidayHome;
import insurancecompany.misc.hometypes.HolidayHomeType;
import insurancecompany.misc.coverages.HolidayHomeInsuranceCoverage;
import java.io.Serializable;


/**
 *
 * @author Carl
 */
public class HolidayHomeContentInsurance extends PropertyInsurance
        implements Serializable {
    private static final long serialVersionUID = 1L;
    
    /** the home this insurance is for */
    private HolidayHome holidayhome;
    /** the hometype this insurance is for */
    private HolidayHomeType holidayhometype;
    /** the coverage of this insurance */
    private HolidayHomeInsuranceCoverage coverage;
    
    /**
     * Constructs a new holiday home contents insurance with the specified
     * customerId, excess, holidayhome, holidayhometype and coverage.
     * Active is set to true.
     * Date is set to the current date.
     * InsuranceId is automatically set to nextInsuranceId.
     * @param customerId the ID of the customer who owns this insurance
     * @param excess the excess of this insurance
     * @param holidayhome the holidayhome this insurance is for
     * @param holidayhometype the holidayhometype this insurance is for 
     * @param coverage the coverage of this insurance
     */
    public HolidayHomeContentInsurance(int customerId, int excess,
            HolidayHome holidayhome, HolidayHomeType holidayhometype,
            HolidayHomeInsuranceCoverage coverage){
        super(customerId, excess);
        this.holidayhome = holidayhome;
        this.holidayhometype = holidayhometype;
        this.coverage = coverage;
    }
    
    public HolidayHome getholidayHome(){
        return holidayhome;
    }
}
