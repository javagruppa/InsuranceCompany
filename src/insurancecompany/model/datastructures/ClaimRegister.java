package insurancecompany.model.datastructures;

import insurancecompany.misc.coverages.Damage;
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
     * Finds and returns a claim based on claim id.
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