/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package insurancecompany.insurances;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Sindre
 */
public abstract class Insurance {
    private boolean active;
    private Date date;
    private int insuranceAmount;
    private int insurancePremium;
    private List<String> insuranceConditions;
    
    public Insurance() {
        this.active = false;
        this.date = new Date();
        this.insuranceConditions = new ArrayList<>();
    }
    
    public boolean addInsuranceCondition(String insuranceCondition) {
        return insuranceConditions.add(insuranceCondition);
    }
    
    public boolean deleteInsuranceCondition(String insuranceCondition) {
        return insuranceConditions.removeIf(i -> i.equals(insuranceCondition));
    }
    
    public void setActive(boolean active) {
        this.active = active;
    }
    
    public void setInsuranceAmount(int insuranceAmount) {
        this.insuranceAmount = insuranceAmount;
    }
    
    public void setInsurancePremium(int insurancePremium) {
        this.insurancePremium = insurancePremium;
    }
    
    public String toString() {
        // <Skriver ut informasjon om objektet>
        return null;
    }
}