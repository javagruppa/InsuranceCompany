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
public class HomeInsurance extends PropertyInsurance {
    public HomeInsurance(Address address, int constructionYear,
            String propertyType, String buildingMaterial, String standard,
            int floorArea, int buildingInsuranceAmount,
            int contentInsuranceAmount) {
        super(address, constructionYear, propertyType, buildingMaterial,
                standard, floorArea, buildingInsuranceAmount,
                contentInsuranceAmount);
    }
}