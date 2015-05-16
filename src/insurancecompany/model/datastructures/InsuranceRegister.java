/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package insurancecompany.model.datastructures;

import insurancecompany.model.insurances.CarInsurance;
import insurancecompany.model.insurances.Insurance;
import java.io.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Sindre
 */
public class InsuranceRegister {
    
    private static String insurancesFilePath = "src/insurancecompany/resources/datastructures/insuranceSet.dta";
    
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
    public boolean addInsurance(Insurance insurance) {
        return insurances.add(insurance);
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
    
    public List<Insurance> getInsuranceById(int insuranceId) {
        List<Insurance> result = new ArrayList<>();
        for (Insurance insurance : insurances) {
            if (insurance.getInsuranceId() == insuranceId) {
                result.add(insurance);
                return result;
            }
        }
        return result;
    }
    
    public List<Insurance> getInsurancesByCustomerId(int customerId, 
            Class<?> type, Calendar fromDate, Calendar toDate, boolean active) {
        List<Insurance> result = new ArrayList<>();
        for (Insurance insurance : insurances) {
            if (insurance.getCustomerId() == customerId) {
                if(type == null || type.isInstance(insurance)) {
                    if(fromDate == null || fromDate.compareTo(insurance.getDate()) <= 0) {
                        if(toDate == null || toDate.compareTo(insurance.getDate()) >= 0) {
                            if(!active || insurance.getActive()) {
                                result.add(insurance);
                            }
                        }
                    }
                }
            }
        }
        return result;
    }
    
    public List<Insurance> getInsurances(Class<?> type, Calendar fromDate, 
            Calendar toDate, boolean active) {
        List<Insurance> result = new ArrayList<>();
        for (Insurance insurance : insurances) {
            if(type == null || type.isInstance(insurance)) {
                if(fromDate == null || fromDate.compareTo(insurance.getDate()) <= 0) {
                    if(toDate == null || toDate.compareTo(insurance.getDate()) >= 0) {
                        if(!active || insurance.getActive()) {
                            result.add(insurance);
                        }
                    }
                }
            }
        }
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
    
    public List<Insurance> getAllActiveInsurancesByCustomerId(int customerId) {
        List<Insurance> result = new ArrayList<Insurance>();
        for (Insurance insurance : insurances) {
            if (insurance.getCustomerId() == customerId && insurance.getActive()) {
                result.add(insurance);
            }
        }
        return result;
    }
    
    /**
     * Returns a List of all active insurances of a specified class type to a 
     * customer by its customer id.
     * @param customerId
     * @param type
     * @return 
     */
    public List<Insurance> getAllActiveTypeInsurancesByCustomerId(int customerId, Class<?> type) {
        List<Insurance> result = new ArrayList<Insurance>();
        for (Insurance insurance : insurances) {
            if ((insurance.getCustomerId() == customerId) && insurance.getActive() && type.isInstance(insurance)) {
                result.add(insurance);
            }
        }
        return result;
    }
    
    
    public int getNumberOfActiveInsurances(int customerId) {
        int result = 0;
        // Goes through all insurances
        for (Insurance insurance : insurances) {
            // Adds a count for every active insurance matching the customer id:
            if (insurance.getCustomerId() == customerId && insurance.getActive()) {
                result++;
            }
        }
        // Returns the number of active insurances for the specified customer:
        return result;
    }
    
    /**
     * Returns the number of insurances of the specified type.
     * 
     * @param type the specified type of insurance
     * @return the number of insurances of the specified type
     */
    public int getNumberOfInsuranceByType(Class<?> type) {
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
    public int getTotalPremium(int customerId) {
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
    public int getTotalPremiumOfAll() {
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
    public int getTotalPremiumOfAllByType(Class<?> type) {
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
    
    /***
     * Updates all car insurance bonuses, where a year or more has gone since
     * last update or damage.
     */
    public void updateAllCarInusranceBonuses() {
        for (Insurance insurance : insurances) {
            // Find all car insurances:
            if (insurance instanceof CarInsurance) {
                CarInsurance carInsurance = (CarInsurance) insurance;
                // Increase the bonus (if one year has passed since last increase/damage
                carInsurance.increaseBonus();
            }
        }
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

    /**
     * @return the insurances
     */
    public Set<Insurance> getInsurances() {
        return insurances;
    }
}