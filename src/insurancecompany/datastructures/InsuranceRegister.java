/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package insurancecompany.datastructures;

import insurancecompany.insurances.Insurance;
import java.util.ArrayList;

/**
 *
 * @author Sindre
 */
public class InsuranceRegister {
    /** The list of this insurance register. */
    private ArrayList<Insurance> insurances;
    
    /**
     * Constructor initializing the list of this insurance register.
     */
    public InsuranceRegister() {
        insurances = new ArrayList<>();
    }
    
    /**
     * Adds an insurance to the list insurances.
     * @param insurance insurance to be appended to this list
     * @return true if this list changed as a result of the call
     */
    public boolean addInsurance(Insurance insurance) {
        return insurances.add(insurance);
    }
    
    /**
     * Deletes an insurance from the list insurances.
     * @param insurance insurance to be removed from this list
     * @return true if this list changed as a result of the call
     */
    public boolean deleteInsurance(Insurance insurance) {
        return insurances.removeIf(i -> i.equals(insurance));
    }
}