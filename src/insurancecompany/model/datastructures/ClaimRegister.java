/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package insurancecompany.model.datastructures;


import insurancecompany.model.claims.Claim;
import java.io.*;
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
    
    public void saveClaimSetToFile() throws IOException{
        
    }
    
    public Set<Claim> readClaimSetFromFile() throws IOException {
        
        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream("insurancecompany/resources/datastructures/claimSet.dta"))) {
            Set<Claim> c = (HashSet<Claim>) ois.readObject();
            
        } catch (ClassNotFoundException cnfe) {
            // write to log
        }
    }
        
        
        
        
        
        try (ObjectInputStream innfil = new ObjectInputStream(
 83             new FileInputStream( "insurancecompany/resources/datastructures/claimSet.dta")))
 84     {
 85       heltallsliste = (Heltallsliste) innfil.readObject();
 86     }
 87     catch(ClassNotFoundException cnfe)
 88     {
 89       lista.setText(cnfe.getMessage());
 90       lista.append("\nOppretter tom liste.\n");
 91       heltallsliste = new Heltallsliste();
 92     }
 93     catch(FileNotFoundException fne)
 94     {
 95       lista.setText("Finner ikke datafil. Oppretter tom liste.\n");
 96       heltallsliste = new Heltallsliste();
 97     }
 98     catch(IOException ioe)
 99     {
100       lista.setText("Innlesingsfeil. Oppretter tom liste.\n");
101       heltallsliste = new Heltallsliste();
102     }
    }
    
    
    
}
