/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package insurancecompany.claims;

import java.util.Date;

/**
 *
 * @author André
 */
public class TravelClaim extends Claim {
     
    /**
     * Empty constructor.
     */
    public TravelClaim() {
        
    }
    
    /**
     * Constructor initializing date and description of a travel claim.
     * @param date
     * @param description 
     */
    public TravelClaim(String description) {
        super(description);
    }
}
