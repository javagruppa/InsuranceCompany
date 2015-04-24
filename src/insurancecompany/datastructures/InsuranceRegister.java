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
    
    /**
     * Returns an ArrayList of customer ids belonging to customers who has an 
     * insurances of the specified type.
     * 
     * @param type the specified type of insurance
     * @return an ArrayList of customer ids
     */
    public ArrayList<Integer> getCustomerIds(Class<?> type) {
        // Creates an ArrayList which will be returned at the end of the 
        // method.
        ArrayList<Integer> result = new ArrayList<>();
        // Creates an iterator for the list.
        Iterator<Insurance> iterator = insurances.iterator();
        // Runs through the whole list.
        while(iterator.hasNext()) {
            Insurance insurance = iterator.next();
            // Appends the customer id of the insurance to the list if the 
            // insurance is of the specified type.
            if(type.isInstance(insurance)) {
                result.add(insurance.getCustomerId());
            }
        }
        // Returns the list.
        return result;
    }
    
    /**
     * Returns the number of insurances of the specified type.
     * 
     * @param type the specified type of insurance
     * @return the number of insurances of the specified type
     */
    public int getNumberOfInsurance(Class<?> type) {
        // Creates an integer which will be returned at the end of the method.
        int result = 0;
        // Creates an iterator for the list.
        Iterator<Insurance> iterator = insurances.iterator();
        // Runs through the whole list.
        while(iterator.hasNext()) {
            Insurance insurance = iterator.next();
            // Increases the result by one if the insurance is of the specified 
            // type.
            if(type.isInstance(insurance)) {
                result++;
            }
        }
        // Returns the result.
        return result;
    }
    
    /**
     * Returns the yearly insurance premium of a customer with the specified 
     * customeri id.
     * 
     * @param customerId the specified customer id
     * @return the yearly insurance premium of a customer
     */
    public int getPremium(int customerId) {
        // Creates an integer which will be returned at the end of the method.
        int result = 0;
        // Creates an iterator for the list.
        Iterator<Insurance> iterator = insurances.iterator();
        // Runs through the whole list.
        while(iterator.hasNext()) {
            Insurance insurance = iterator.next();
            // Appends the yearly insurance premium of the insurance to the 
            // result if the insurance belongs to the customer with the 
            // specified customer id.
            if(insurance.getCustomerId() == customerId) {
                result += insurance.getPremium();
            }
        }
        // Returns the result.
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