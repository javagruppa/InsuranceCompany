/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package insurancecompany.model.datastructures;


import insurancecompany.model.claims.Claim;
import java.util.ArrayList;
import java.util.Iterator;
/**
 *
 * @author André
 */
public class ClaimRegister {
    
    private ArrayList<Claim> claims;
    
    /**
     * Constructor:
     */
    public ClaimRegister() {
        claims = new ArrayList<Claim>();
    }
    
    /**
     * Adds a new claim to this register if it does not already exist.
     * @param claim claim to be added to the register
     * @return 
     */
    public boolean addClaim(Claim claim) {
        if (claims.contains(claim)) {
            return false;
        } else {
            claims.add(claim);
            return true;
        }
    }
    
    /**
     * Finds and returns a list of claims matching the owner of given customer id.
     * @param customerId customer id of a claim owner
     * @return a list of claims matching the owner of the customer id
     */
    public ArrayList<Claim> findClaimsByCustomerId(int customerId) {
        // Creates an ArrayList which will be returned at the end of the method.
        ArrayList<Claim> result = new ArrayList<Claim>();
        // Creates an iterator for the list:
        Iterator<Claim> iterator = claims.iterator();
        // Runs through the whole list:
        while(iterator.hasNext()) {
            Claim claim = iterator.next();
            if (claim.getCustomerId() == customerId) {
                result.add(claim);
            }
        }
        // Returns null if no matches are found:
        if (result.isEmpty()) {
            return null;
        } else {
            // Returns the newly created list otherwise:
            return result;
        }
    }
    
    /**
     * Finds and returns a claim based on claim id.
     * @param claimId claim id of a claim in the list
     * @return 
     */
    public Claim getClaim(int claimId) {
        // Creates an iterator for the list:
        Iterator<Claim> iterator = claims.iterator();
        // Runs through the whole list:
        while(iterator.hasNext()) {
            Claim claim = iterator.next();
            if (claim.getClaimId() == claimId) {
                return claim;
            }
        }
        return null;
    }
    
    /**
     * Returns the number of claims in this register.
     * @return 
     */
    public int size() {
        return claims.size();
    }
    
    
    
    
    
}
