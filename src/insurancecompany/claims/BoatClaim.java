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
public class BoatClaim extends Claim {
    
    /**
     * Empty constructor.
     */
    public BoatClaim() {
        
    }
    
    /**
     * Constructor initializing date and description of a boat claim.
     * @param date
     * @param description 
     */
    public BoatClaim(String description) {
        super(description);
    }
}
