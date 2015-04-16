/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package insurancecompany.insurances;

/**
 *
 * @author Sindre
 */
public abstract class PropertyInsurance extends Insurance {
    private int buildingInsuranceAmount;
    private int contentInsuranceAmount;
    
    public PropertyInsurance(int buildingInsuranceAmount, 
            int contentInsuranceAmount) {
        super();
        this.buildingInsuranceAmount = buildingInsuranceAmount;
        this.contentInsuranceAmount = contentInsuranceAmount;
    }
}