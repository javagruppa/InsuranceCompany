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
    private Home home;
    
    public HomeInsurance(Home home, int buildingInsuranceAmount,
            int contentInsuranceAmount) {
        super(buildingInsuranceAmount, contentInsuranceAmount);
        this.home = home;
    }
}