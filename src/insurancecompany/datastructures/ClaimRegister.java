/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package insurancecompany.datastructures;

import insurancecompany.claims.*;

import java.util.ArrayList;
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
        ArrayList<Claim> c = new ArrayList<Claim>();
        for (int i = 0; i < claims.size(); i++) {
            if (claims.get(i).getCustomerId() == customerId) {
                c.add(claims.get(i));
            }
        }
        // Returns null if no matches are found:
        if (c.isEmpty()) {
            return null;
        } else {
            // Returns the newly created list otherwise:
            return c;
        }
    }
    
    /**
     * Finds and returns a claim based on claim id.
     * @param claimId claim id of a claim in the list
     * @return 
     */
    public Claim getClaim(int claimId) {
        for (int i = 0; i < claims.size(); i++) {
            if (claims.get(i).getClaimId() == claimId) {
                return claims.get(i);
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
