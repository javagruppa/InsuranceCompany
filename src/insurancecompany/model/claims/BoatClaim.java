/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package insurancecompany.model.claims;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author André
 */
public class BoatClaim extends Claim implements Serializable {
    private static final long serialVersionUID = 1L;
    
    /**
     * Empty constructor.
     */
    public BoatClaim(int customerId, int insuranceId, String description) {
        super(customerId, insuranceId, description);
    }
    
    /**
     * 
     * @param customerId
     * @param description 
     */
    public BoatClaim(int customerId, String description) {
        super(customerId, description);
    }
    
    @Override
    public String toString() {
        String text = "Båtskademelding\n";
        text += super.toString();
        return text;
    }
}
