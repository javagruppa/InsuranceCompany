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
public class HolidayHomeClaim extends Claim {
    
     /**
     * Empty constructor.
     */
    public HolidayHomeClaim() {
        
    }
    
    /**
     * Constructor initializing date and description of a holiday home claim.
     * @param date
     * @param description 
     */
    public HolidayHomeClaim(Date date, String description) {
        super(date, description);
    }
}
