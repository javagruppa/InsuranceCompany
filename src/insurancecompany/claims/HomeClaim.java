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
public class HomeClaim extends Claim {
     
    /**
     * Empty constructor.
     */
    public HomeClaim() {
        
    }
    
    /**
     * Constructor initializing date and description of a claim.
     * @param date
     * @param description 
     */
    public HomeClaim(String description) {
        super(description);
    }

    @Override
    public String toString() {
        String text = "Hus- og innbokademelding\n";
        text += super.toString();
        return text;
    }
}
