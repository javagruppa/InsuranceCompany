/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package insurancecompany.insurances;

import java.util.Date;

/**
 *
 * @author Sindre
 */
public class Insurance {
    private boolean active;
    private Date date;
    private int insurancePremium;
    private int insuranceAmount;
    private String insuranceConditions;
    
    public Insurance(int insurancePremium, int insuranceAmount,
            String insuranceConditions) {
        this.active = true;
        this.date = new Date();
        this.insurancePremium = insurancePremium;
        this.insuranceAmount = insuranceAmount;
        this.insuranceConditions = insuranceConditions;
    }
    
    public String toString() {
        // <Skriver ut informasjon om objektet>
    }
}