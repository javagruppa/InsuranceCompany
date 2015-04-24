/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package insurancecompany.datastructures;

import insurancecompany.insurances.Insurance;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Sindre
 */
public class InsuranceRegister {
    /** The list of this insurance register. */
    private ArrayList<Insurance> insurances;
    
    /**
     * Constructs a new InsuranceRegister with an empty list.
     */
    public InsuranceRegister() {
        insurances = new ArrayList<>();
    }
    
    /**
     * Adds an insurance to the list insurances.
     * 
     * @param insurance insurance to be appended to this list
     * @return true if this list changed as a result of the call
     */
    public boolean addInsurance(Insurance insurance) {
        return insurances.contains(insurance) ? false : 
                insurances.add(insurance);
    }
    
    /**
     * Deletes an insurance from the list insurances.
     * 
     * @param insurance insurance to be removed from this list
     * @return true if this list changed as a result of the call
     */
    public boolean deleteInsurance(Insurance insurance) {
        return insurances.removeIf(i -> i.equals(insurance));
    }
    
    public ArrayList<Integer> getCustomerIds(Class<?> type) {
        ArrayList<Integer> result = new ArrayList<>();
        Iterator<Insurance> iterator = insurances.iterator();
        while(iterator.hasNext()) {
            Insurance insurance = iterator.next();
            if(type.isInstance(insurance)) {
                result.add(insurance.getCustomerId());
            }
        }
        return result;
    }
    
    public int getNumberOfInsurance(Class<?> type) {
        int result = 0;
        Iterator<Insurance> iterator = insurances.iterator();
        while(iterator.hasNext()) {
            Insurance insurance = iterator.next();
            if(type.isInstance(insurance)) {
                result++;
            }
        }
        return result;
        
        // <Create return int>
        // <Run through the whole insurances list>
            // <Check if the insurance is of type>
            // <If yes, add 1 to the int>
        // <Return the int>
    }
    
    public int getPremium(int customerId) {
        int result = 0;
        Iterator<Insurance> iterator = insurances.iterator();
        while(iterator.hasNext()) {
            Insurance insurance = iterator.next();
            if(insurance.getCustomerId() == customerId) {
                result += insurance.getPremium();
            }
        }
        return result;
    }
    
    public int getTotalPremium() {
        int result = 0;
        Iterator<Insurance> iterator = insurances.iterator();
        while(iterator.hasNext()) {
            Insurance insurance = iterator.next();
            result += insurance.getPremium();
        }
        return result;
        
        // <Create return int>
        // <Run through the whole insurances list>
            // <Add the yearly insurance premium to the int>
        // <Return the int>
    }
    
    public int getTotalPremium(Class<?> type) {
        int result = 0;
        Iterator<Insurance> iterator = insurances.iterator();
        while(iterator.hasNext()) {
            Insurance insurance = iterator.next();
            if(type.isInstance(insurance)) {
                result += insurance.getPremium();
            }
        }
        return result;
        
        // <Create return int>
        // <Run through the whole insurances list>
            // <Check if insurance is of the type>
            // <If yes, add the yearly insurance premium to the int>
        // <Return the int>
    }
}