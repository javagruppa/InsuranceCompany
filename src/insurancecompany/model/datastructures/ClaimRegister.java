package insurancecompany.model.datastructures;

import insurancecompany.misc.enums.coverages.Damage;
import insurancecompany.model.claims.Claim;
import java.io.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * This class creates the register for claims. It consists of a HashSet with all
 * the claims as well as several methods to manipulate and get information from
 * the register.
 * 
 * @author André
 * @author Sindre
 * 
 * @since 19.05.2015
 */
public class ClaimRegister {
    /** The file path of the file where the claims are saved. */
    private static String claimsFilePath = "src/insurancecompany/resources/datastructures/claimSet.dta";
    /** The set of claims. */
    private Set<Claim> claims;
    
    /**
     * Default constructor. Initializes the set.
     */
    public ClaimRegister() {
        claims = new HashSet<>();
    }
    
    /**
     * Adds a new claim to this register if it does not already exist.
     * 
     * @param claim Claim to be added to the register.
     * @return True if this list changed as a result of the call.
     */
    public boolean addClaim(Claim claim) {
        return claims.add(claim);
    }
    
    /**
     * Finds and returns a claim based on claim ID.
     * 
     * @param claimId Claim ID of the claim that the method looks for.
     * @return The claim if it is found. Null otherwise.
     */
    public Claim getClaimById(int claimId) {
        for (Claim claim : claims) {
            if (claim.getClaimId() == claimId) {
                return claim;
            }
        }
        return null;
    }
    
    /**
     * Finds and returns a list of claims based on the parameters.
     * 
     * @param customerId The customer ID of the claims that the method looks 
     * for. It's 0 if it's not part of the criteria.
     * @param type The claim type of the claims that the method looks for. It's
     * null if it's not part of the criteria.
     * @param damage The damage of the claims that the method looks for. It's 
     * null if it's not part of the criteria.
     * @param insuranceId The insurance ID of the claims that the method looks 
     * for. It's null if it's not part of the criteria.
     * @param fromDate The earliest date the incident happened. It's null if 
     * it's not part of the criteria.
     * @param toDate The latest date the incident happened. It's null if it's
     * not part of the criteria.
     * @return A list of all the claims that match the criteria.
     */
    public List<Claim> getClaims(int customerId, String type, Damage damage, 
            int insuranceId, Calendar fromDate, Calendar toDate) {
        List<Claim> result = new ArrayList<>();
        for (Claim claim : claims) {
            if ((customerId == 0 || customerId == claim.getCustomerId())
                    && (type == null || type.equals(claim.getName()))
                    && (damage == null || claim.getDamages().contains(damage))
                    && (fromDate == null ||
                            fromDate.compareTo(claim.getDateHappened()) <= 0)
                    && (toDate == null || 
                            toDate.compareTo(claim.getDateHappened()) >= 0)) {
                result.add(claim);
            }
        }
        return result;
    }

    /** @return A set of all the claims in the register. */
    public Set<Claim> getClaims() {
        return claims;
    }
    
    /**
     * Gets all disbursements from a certain date.
     * If any parameter is null, the method will return data using the
     * information applicable.
     * 
     * @param year the year
     * @param month the month
     * @param day the date
     * @param type the type
     * @param customerId the customerId
     * @param insuranceId the insuranceId
     * @return all disbursements found
     */
    public double getDisbursementAtDate(int year, int month, int day, String type, 
            int customerId, int insuranceId) {
        double result = 0;
        for (Claim claim : claims) {
            int insId = claim.getInsuranceId();
            if ((claim.getDate().get(Calendar.YEAR) == year) 
                    && (month == 0 || claim.getDate().get(Calendar.MONTH) == month)
                    && (day == 0 || claim.getDate().get(Calendar.DAY_OF_MONTH) == day)
                    && (type == null || type.equals(claim.getName()))
                    && (customerId == 0 || claim.getCustomerId() == customerId)
                    && (insuranceId == 0 || claim.getInsuranceId() == insuranceId)) {
                result += claim.getDisbursement();
            }
        }
        return result;
    }
    
    /**
     * Gets the oldest disbursement date found with the given parameters.
     * If any parameters are missing, the method will work with the information
     * applicable
     * 
     * @param type the type
     * @param customerId the customerId
     * @param insuranceId the insuranceId
     * @return the oldest disbursement date found
     */
    public Calendar getOldestDisbursementDate(String type, int customerId, int insuranceId) {
        Calendar oldest = null;
        for (Claim claim : claims) {
            if ((type == null || type.equals(claim.getName()))
                    && (customerId == 0 || claim.getCustomerId() == customerId)
                    && (insuranceId == 0 || claim.getInsuranceId() == insuranceId)
                    && (claim.getDisbursement() != 0)) {
                if (oldest == null) {
                    oldest = (Calendar) claim.getDate().clone();
                }
                if (claim.getDate().before(oldest) || claim.getDate().equals(oldest)) {
                    oldest = (Calendar) claim.getDate().clone();
                }
            }
        }
        return oldest;
    }
    
    /** Gets the newest disbursement date found with the given parameters.
     * If any parameters are missing, the method will work with the information
     * applicable
     * 
     * @param type the type
     * @param customerId the cutomerId
     * @param insuranceId the insuranceId
     * @return the newest disbursement date found.
     */
    public Calendar getNewestDisbursementDate(String type, int customerId, int insuranceId) {
        Calendar newest = null;
        for (Claim claim : claims) {
            if ((type == null || type.equals(claim.getName()))
                    && (customerId == 0 || claim.getCustomerId() == customerId)
                    && (insuranceId == 0 || claim.getInsuranceId() == insuranceId)) {            
            
                if (newest == null) {
                    newest = (Calendar) claim.getDate().clone();
                }
                if (claim.getDate().after(newest) || claim.getDate().equals(newest)) {
                    newest = (Calendar) claim.getDate().clone();
                }
            }
        }
        return newest;
    }
    
    /**
     * Writes this registers set of claims to file.
     * 
     * @throws IOException 
     */
    public void writeClaimsToFile() throws IOException{
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(claimsFilePath))) {
            oos.writeObject(claims);
        }
    }
    
    /**
     * Reads a set of claims from file and stores them in the set in this 
     * register.
     * 
     * @throws IOException
     * @throws ClassNotFoundException 
     */
    public void readClaimsFromFile() throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream(claimsFilePath))) {
            claims = (HashSet<Claim>) ois.readObject();        
        }
    }
}