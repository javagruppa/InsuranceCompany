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
public class CarClaim extends Claim {
    
    CarClaimForm claimForm;
    
    /**
     * Empty constructor.
     */
    public CarClaim(int customerId, int insuranceId, String description) {
        super(customerId, insuranceId, description);
    }
    
    /**
     * Constructor initializing date and description of a car claim.
     * @param date
     * @param description 
     */
    public CarClaim(int customerId, String description) {
        super(customerId, description);
    }
    
    public void setClaimForm(CarClaimForm claimForm) {
        this.claimForm = claimForm;
    }
    
    public CarClaimForm getClaimForm() {
        return claimForm;
    }
    
    @Override
    public String toString() {
        String text = "Bilskademelding\n";
        text += super.toString();
        return text;
    }
}
