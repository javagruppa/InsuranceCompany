/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package insurancecompany.model.datastructures;


import insurancecompany.model.claims.Claim;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
/**
 *
 * @author André
 */
public class ClaimRegister {
    
    private Set<Claim> claims;
    
    /**
     * Constructor:
     */
    public ClaimRegister() {
        claims = new HashSet<Claim>();
    }
    
    /**
     * Adds a new claim to this register if it does not already exist.
     * @param claim claim to be added to the register
     * @return 
     */
    public void addClaim(Claim claim) {
        claims.add(claim);
    }
    
    /**
     * Finds and returns a list of claims matching the owner of given customer id.
     * @param customerId customer id of a claim owner
     * @return a list of claims matching the owner of the customer id
     */
    public List<Claim> findClaimsByCustomerId(int customerId) {
        // Creates an ArrayList which will be returned at the end of the method.
        List<Claim> result = new ArrayList<Claim>();
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
