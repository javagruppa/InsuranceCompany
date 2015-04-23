/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package insurancecompany.insurances;

import insurancecompany.people.Customer;
import insurancecompany.properties.HolidayHome;

/**
 *
 * @author Sindre
 */
public class HolidayHomeInsurance extends PropertyInsurance {
    /** The holiday home this insurance is for. */
    private HolidayHome holidayHome;
    
    /**
     * Constructor.
     * @param customer the customer who owns this insurance
     * @param holidayHome the holiday home this insurance is for
     */
    public HolidayHomeInsurance(Customer customer, HolidayHome holidayHome) {
        super(customer);
        this.holidayHome = holidayHome;                
    }
}