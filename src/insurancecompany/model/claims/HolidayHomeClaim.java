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
public class HolidayHomeClaim extends Claim {
    
     /**
     * Empty constructor.
     */
    public HolidayHomeClaim(int customerId, int insuranceId, String description) {
        super(customerId, insuranceId, description);
    }
    
    /**
     * Constructor initializing date and description of a holiday home claim.
     * @param date
     * @param description 
     */
    public HolidayHomeClaim(int customerId, String description) {
        super(customerId, description);
    }
    
    @Override
    public String toString() {
        String text = "Fritidsboligskademelding\n";
        text += super.toString();
        return text;
    }    
}
