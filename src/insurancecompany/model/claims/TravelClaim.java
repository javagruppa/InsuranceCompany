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
public class TravelClaim extends Claim implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String creditCardBrand;

    public TravelClaim(int customerId, int insuranceId, String description, 
            Calendar dateHappened, Set<Damage> damages, int appraisal, 
            String creditCardBrand) {
        super(customerId, insuranceId, description, dateHappened, damages, 
                appraisal);
        this.creditCardBrand = creditCardBrand;
    }    
    
    public TravelClaim(int customerId, int insuranceId, String description, 
            Calendar dateHappened, Set<Damage> damages, int appraisal, 
            Image image, String creditCardBrand) {
        super(customerId, insuranceId, description, dateHappened, damages, 
                appraisal, image);
        this.creditCardBrand = creditCardBrand;
    }

    
    @Override
    public String toString() {
        String text = "Reiseskademelding\n";
        text += super.toString();
        return text;
    }

    /**
     * @return the creditCardBrand
     */
    public String getCreditCardBrand() {
        return creditCardBrand;
    }

    /**
     * @param creditCardBrand the creditCardBrand to set
     */
    public void setCreditCardBrand(String creditCardBrand) {
        this.creditCardBrand = creditCardBrand;
    }
}
