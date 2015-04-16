/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package insurancecompany.insurances;

import insurancecompany.properties.HolidayHome;

/**
 *
 * @author Sindre
 */
public class HolidayHomeInsurance extends PropertyInsurance {
    private HolidayHome holidayHome;
    
    public HolidayHomeInsurance(HolidayHome holidayHome, 
            int buildingInsuranceAmount, int contentInsuranceAmount) {
        super(buildingInsuranceAmount, contentInsuranceAmount);
        this.holidayHome = holidayHome;                
    }
}