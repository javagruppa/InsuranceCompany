/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package insurancecompany.datastructures;

import insurancecompany.insurances.Insurance;
import insurancecompany.people.Customer;
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
    
    public ArrayList<Customer> getCustomers(String type) {
        // <Create return list>
        // <Run through the whole insurances list>
            // <Check if insurance is of the type>
            // <If yes, add the customer of the insurance to the list>
        // <Return the list>
    }
    
    public int getNumberOfInsurance(String type) {
        // <Create return int>
        // <Run through the whole insurances list>
            // <Check if the insurance is of type>
            // <If yes, add 1 to the int>
        // <Return the int>
    }
    
    public int getPremium(Customer customer) {
        // <Create return int>
        // <Run through the whole insurances list>
            // <Check if the insurance belongs to the customer>
            // <If yes, add the yearly insurance premium to the int>
        // <Return the int>
    }
    
    public int getTotalPremium() {
        // <Create return int>
        // <Run through the whole insurances list>
            // <Add the yearly insurance premium to the int>
        // <Return the int>
    }
    
    public int getTotalPremium(String type) {
        // <Create return int>
        // <Run through the whole insurances list>
            // <Check if insurance is of the type>
            // <If yes, add the yearly insurance premium to the int>
        // <Return the int>
    }
}