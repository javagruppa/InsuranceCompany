/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package insurancecompany.model.claims;

import java.util.Date;

/**
 *
 * @author André
 */
public class BoatClaim extends Claim {
    
    /**
     * Empty constructor.
     */
    public BoatClaim(int customerId, int insuranceId) {
        super(customerId, insuranceId);
    }
    
    /**
     * Constructor initializing date and description of a boat claim.
     * @param date
     * @param description 
     */
    public BoatClaim(String description) {
        super(description);
    }
    
    @Override
    public String toString() {
        String text = "Båtskademelding\n";
        text += super.toString();
        return text;
    }
}
