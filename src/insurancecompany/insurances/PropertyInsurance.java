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
public abstract class PropertyInsurance extends Insurance {
    private Address address;
    private int constructionYear;
    private String propertyType;
    private String buildingMaterial;
    private String standard;
    private int floorArea;
    private int buildingInsuranceAmount;
    private int contentInsuranceAmount;
    
    public PropertyInsurance(Address address, int constructionYear,
            String propertyType, String buildingMaterial, String standard,
            int floorArea, int buildingInsuranceAmount,
            int contentInsuranceAmount) {
        super();
        this.address = address;
        this.constructionYear = constructionYear;
        this.propertyType = propertyType;
        this.buildingMaterial = buildingMaterial;
        this.standard = standard;
        this.floorArea = floorArea;
        this.buildingInsuranceAmount = buildingInsuranceAmount;
        this.contentInsuranceAmount = contentInsuranceAmount;
    }
}