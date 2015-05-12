/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package insurancecompany.model.claims;

import insurancecompany.misc.coverages.Damage;
import java.awt.Image;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.Set;

/**
 *
 * @author André
 */
public class CarClaim extends Claim implements Serializable {
    private static final long serialVersionUID = 1L;
    
    CarClaimForm claimForm;

    public CarClaim(int customerId, int insuranceId, String description, 
            Calendar dateHappened, Set<Damage> damages, int appraisal, 
            CarClaimForm claimForm) {
        super(customerId, insuranceId, description, dateHappened, damages, 
                appraisal);
        this.claimForm = claimForm;
    }    
    
    public CarClaim(int customerId, int insuranceId, String description, 
            Calendar dateHappened, Set<Damage> damages, int appraisal, 
            Image image, CarClaimForm claimForm) {
        super(customerId, insuranceId, description, dateHappened, damages, 
                appraisal, image);
        this.claimForm = claimForm;
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
