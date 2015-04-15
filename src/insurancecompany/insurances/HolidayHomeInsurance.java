/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package insurancecompany.insurances;

import insurancecompany.misc.Address;

/**
 *
 * @author Sindre
 */
public class HolidayHomeInsurance extends PropertyInsurance {
    private boolean rental;
    
    public HolidayHomeInsurance(int insurancePremium, String insuranceConditions,
            Address address, int constructionYear, String propertyType,
            String buildingMaterial, String standard, int floorArea,
            int buildingInsuranceAmount, int contentInsuranceAmount,
            boolean rental) {
        super(insurancePremium, insuranceConditions, address, constructionYear,
                propertyType, buildingMaterial, standard, floorArea,
                buildingInsuranceAmount, contentInsuranceAmount);
        this.rental = rental;                
    }
}