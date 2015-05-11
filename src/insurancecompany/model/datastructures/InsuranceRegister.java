/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package insurancecompany.model.datastructures;

import insurancecompany.model.insurances.Insurance;
import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Sindre
 */
public class InsuranceRegister {
    
    private static String insurancesFilePath = "insurancecompany/resources/datastructures/insuranceSet.dta";
    
    /** The list of this insurance register. */
    private Set<Insurance> insurances;
    
    /**
     * Constructs a new InsuranceRegister with an empty list.
     */
    public InsuranceRegister() {
        insurances = new HashSet<>();
    }
    
    /**
     * Adds an insurance to the list insurances.
     * 
     * @param insurance insurance to be appended to this list
     * @return true if this list changed as a result of the call
     */
    public void addInsurance(Insurance insurance) {
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
    public List<Integer> getCustomerIds(Class<?> type) {
        // Creates an ArrayList which will be returned at the end of the 
        // method.
        List<Integer> result = new ArrayList<>();
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
    
    public List<Insurance> getAllInsurancesByCustomerId(int customerId) {
        List<Insurance> result = new ArrayList<Insurance>();
        for (Insurance insurance : insurances) {
            if (insurance.getCustomerId() == customerId) {
                result.add(insurance);
            }
        }
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
     * customer id.
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
    
    /**
     * Returns the total yearly insurance premium of all customers.
     * 
     * @return the total yearly insurance premium
     */
    public int getTotalPremium() {
        // Creates an integer which will be returned at the end of the method.
        int result = 0;
        // Creates an iterator for the list.
        Iterator<Insurance> iterator = insurances.iterator();
        // Runs through the whole list.
        while(iterator.hasNext()) {
            // Appends the yearly insurance premium of the insurance to the 
            // result.
            Insurance insurance = iterator.next();
            result += insurance.getPremium();
        }
        // Returns the result.
        return result;
    }
    
    /**
     * Returns the total yearly insurance premium of the specified type of 
     * insurances.
     * 
     * @param type the specified type of insurances
     * @return the total yearly insurance premium of the type
     */
    public int getTotalPremium(Class<?> type) {
        // Creates an integer which will be returned at the end of the method.
        int result = 0;
        // Creates an iterator for the list.
        Iterator<Insurance> iterator = insurances.iterator();
        // Runs through the whole list.
        while(iterator.hasNext()) {
            // Appends the yearly insurance premium of the insurance to the 
            // result if the insurance is of the specified type.
            Insurance insurance = iterator.next();
            if(type.isInstance(insurance)) {
                result += insurance.getPremium();
            }
        }
        // Returns the result.
        return result;
    }
    /**
     * Writes this registers set of insurances to file.
     * @throws IOException 
     */
    public void writeInsurancesToFile() throws IOException{
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(insurancesFilePath))) {
            oos.writeObject(insurances);
        }
    }
    /**
     * Reads a set of insurances from file and stores them in the set in this register.
     * @throws IOException
     * @throws ClassNotFoundException 
     */
    public void readInsurancesFromFile() throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream(insurancesFilePath))) {
            insurances = (HashSet<Insurance>) ois.readObject();        
        }
    }    
}