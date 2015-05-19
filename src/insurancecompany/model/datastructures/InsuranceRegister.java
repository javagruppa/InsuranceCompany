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
 * This class creates the register for insurances. It consists of a HashSet with
 * all the claims as well as several methods to manipulate and get information 
 * from the register.
 * 
 * @author André
 * @author Sindre
 */
public class InsuranceRegister {
    /** The file path of the file where the insurances are saved. */
    private static String insurancesFilePath = "src/insurancecompany/resources/datastructures/insuranceSet.dta";
    /** The set of insurances. */
    private Set<Insurance> insurances;
    
    /**
     * Default constructor. Initializes the set.
     */
    public InsuranceRegister() {
        insurances = new HashSet<>();
    }
    
    /**
     * Adds a new insurance to this register if it does not already exist.
     * 
     * @param insurance Insurance to be added to the register.
     * @return True if this list changed as a result of the call.
     */
    public boolean addInsurance(Insurance insurance) {
        return insurances.add(insurance);
    }
    
    /**
     * Returns an ArrayList of customer ids belonging to customers who has an 
     * insurance of the specified type.
     * 
     * @param insuranceName The specified type of insurance.
     * @return An ArrayList of customer IDs.
     */
    public List<Object> getCustomerIds(String insuranceName) {
        // Creates an ArrayList which will be returned at the end of the 
        // method.
        List<Object> result = new ArrayList<>();
        // Creates an iterator for the list.
        Iterator<Insurance> iterator = insurances.iterator();
        // Runs through the whole list.
        while(iterator.hasNext()) {
            Insurance insurance = iterator.next();
            // Appends the customer ID of the insurance to the list if the 
            // insurance is of the specified type.
            if(insurance.getType().equals(insuranceName)) {
                result.add(insurance.getCustomerId());
            }
        }
        // Returns the list.
        return result;
    }
    
    /**
     * Finds and returns an insurance based on insurance ID.
     * 
     * @param insuranceId Insurance ID of the insurance that the method looks 
     * for.
     * @return The insurance if it is found. Null otherwise.
     */
    public Insurance getInsuranceById(int insuranceId) {
        for (Insurance insurance : insurances) {
            if (insurance.getInsuranceId() == insuranceId) {
                return insurance;
            }
        }
        return null;
    }
    
    /**
     * Finds and returns a list of insurances based on the parameters.
     * 
     * @param customerId The customer ID of the insurances that the method looks 
     * for. It's 0 if it's not part of the criteria.
     * @param type The insurance type of the insurances that the method looks 
     * for. It's null if it's not part of the criteria.
     * @param fromDate The earliest date the insurance was signed. It's null if 
     * it's not part of the criteria.
     * @param toDate The latest date the insurance was signed. It's null if it's
     * not part of the criteria.
     * @param active True if the method is only looking for active insurances.
     * @return A list of all the insurances that match the criteria.
     */
    public List<Insurance> getInsurances(int customerId, String type, 
            Calendar fromDate, Calendar toDate, boolean active) {
        List<Insurance> result = new ArrayList<>();
        for (Insurance insurance : insurances) {
            if ((customerId == 0 || customerId == insurance.getCustomerId())
                    && (type == null || type.equals(insurance.getType()))
                    && (fromDate == null ||
                            fromDate.compareTo(insurance.getDate()) <= 0)
                    && (toDate == null || 
                            toDate.compareTo(insurance.getDate()) >= 0)
                    && (!active || insurance.getActive())) {
                result.add(insurance);
            }
        }
        return result;
    } 

    /** @return A set of all the insurances in the register. */
    public Set<Insurance> getInsurances() {
        return insurances;
    }
    
    /**
     * Finds and returns all the insurances to a customer with the specified
     * customer ID.
     * 
     * @param customerId The customer ID of the customer who owns the 
     * insurances.
     * @return A list of insurances.
     */
    public List<Insurance> getAllInsurancesByCustomerId(int customerId) {
        List<Insurance> result = new ArrayList<>();
        for (Insurance insurance : insurances) {
            if (insurance.getCustomerId() == customerId) {
                result.add(insurance);
            }
        }
        return result;
    }
    
    /**
     * Finds and returns all active insurances to a customer with the specified
     * customer ID.
     * 
     * @param customerId The customer ID of the customer who owns the 
     * insurances.
     * @return A list of insurances.
     */
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
     * Finds and returns all active insurances of a specified type to a 
     * customer with the specified customer ID.
     * 
     * @param customerId The customer ID of the customer who owns the 
     * insurances.
     * @param type The insurance type of the insurances.
     * @return A list of insurances.
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
    
    /**
     * Finds and returns the number of active insurances to a customer with the
     * specified customer ID.
     * 
     * @param customerId The customer ID of the customer who owns the 
     * insurances.
     * @return The number of insurances as an int.
     */
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
     * Sets all the insurances of the customer with the specified customer ID
     * inactive.
     * 
     * @param customerId The customer ID of the customer who owns the 
     * insurances.
     */
    public void setAllInsurancesInactive(int customerId) {
        for (Insurance insurance : insurances) {
            if(insurance.getCustomerId() == customerId) {
                insurance.setActive(false);
            }
        }
    }
    
    /**
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
     * 
     * @throws IOException 
     */
    public void writeInsurancesToFile() throws IOException{
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(insurancesFilePath))) {
            oos.writeObject(insurances);
        }
    }
    
    /**
     * Reads a set of insurances from file and stores them in the set in this 
     * register.
     * 
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