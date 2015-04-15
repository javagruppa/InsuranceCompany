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
    
    public TravelInsurance(String insuranceArea, int insuranceSum) {
        super();
        this.insuranceArea = insuranceArea;
        this.insuranceSum = insuranceSum;
    }
}