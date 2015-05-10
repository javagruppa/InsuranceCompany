/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package insurancecompany.model.insurances;

import insurancecompany.model.properties.HolidayHome;
import insurancecompany.misc.hometypes.HomeType;
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
    private HomeType hometype;
    
    /**
     * Constructs a new holiday home insurance with the specified 
     * buildingAmount, contentAmount, customerId, excess and holidayHome. 
     * Active is set to true. Date is set to the current date. InsuranceId is 
     * automatically set to nextInsuranceId.
     * 
     * @param buildingAmount the building insurance amount of this insurance
     * @param contentAmount the content insurance amount of this insurance
     * @param customerId the id of the customer who owns this insurance
     * @param excess the excess of this insurance
     * @param holidayHome the holiday home this insurance is for
     */
    public HolidayHomeInsurance(int buildingAmount, int contentAmount, 
            int customerId, int excess, HolidayHome holidayHome) {
        super(buildingAmount, contentAmount, customerId, excess);
        this.holidayHome = holidayHome;                
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