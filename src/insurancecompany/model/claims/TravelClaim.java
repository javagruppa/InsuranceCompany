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
public class TravelClaim extends Claim implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String creditCardBrand;
    /**
     * Empty constructor.
     */
    public TravelClaim(int customerId, int insuranceId, String description) {
        super(customerId, insuranceId, description);
    }
    
    /**
     * Constructor initializing date and description of a travel claim.
     * @param date
     * @param description 
     */
    public TravelClaim(int customerId, String description) {
        super(customerId, description);
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
