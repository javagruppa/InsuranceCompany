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
public class TravelInsurance extends Insurance {
    private String insuranceArea;
    private int insuranceSum;
    
    public TravelInsurance(int insurancePremium, int insuranceAmount,
            String insuranceConditions, String insuranceArea,
            int insuranceSum) {
        super(insurancePremium, insuranceAmount, insuranceConditions);
        this.insuranceArea = insuranceArea;
        this.insuranceSum = insuranceSum;
    }
}