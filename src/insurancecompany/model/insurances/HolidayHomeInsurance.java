/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package insurancecompany.model.insurances;

import insurancecompany.model.properties.HolidayHome;
import insurancecompany.misc.hometypes.HolidayHomeType;
import java.io.Serializable;
/**
 *
 * @author Sindre
 */
public class HolidayHomeInsurance extends PropertyInsurance implements Serializable {
    private static final long serialVersionUID = 1L;
    
    /** The holiday home this insurance is for. */
    private HolidayHome holidayHome;
    /** The type of house this insurance is for. */
    private HolidayHomeType holidayhometype;
    
    /**
     *  * Constructs a new holiday home insurance with the specified contentAmount, 
     * customerId, excess, holidayhometype, holidayhomecoverage and holidayHome. 
     * Active is set to true. Date is set to the current date. InsuranceId is 
     * automatically set to nextInsuranceId.
     * 
     * @param type the type of holidayhouse this insurance is for
     * @param customerId the customer that has this insurance
     * @param excess the excess of this insurance
     * @param holidayHome  the holidayhome that is insured
     */
    public HolidayHomeInsurance(HolidayHomeType type, int customerId, int excess, HolidayHome holidayHome) {
        super(customerId, excess);
        this.holidayHome = holidayHome;
        this.holidayhometype = type;
    }
    
    /**
     * Returns the holiday home this insurance is for.
     * 
     * @return the holiday home this insurance is for
     */
    public HolidayHome getHolidayHome() {
        return holidayHome;
    }
}