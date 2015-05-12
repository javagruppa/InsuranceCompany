/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package insurancecompany.model.claims;

import insurancecompany.misc.coverages.Damage;
import java.awt.Image;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 *
 * @author André
 */
public class HomeClaim extends Claim implements Serializable {
    private static final long serialVersionUID = 1L;
        
    private List<ClaimItem> items;

    public HomeClaim(int customerId, int insuranceId, String description, 
            Calendar dateHappened, Set<Damage> damages, int appraisal, 
            List<ClaimItem> items) {
        super(customerId, insuranceId, description, dateHappened, damages, 
                appraisal);
        this.items = items;
    }    
    
    public HomeClaim(int customerId, int insuranceId, String description, 
            Calendar dateHappened, Set<Damage> damages, int appraisal, 
            Image image, List<ClaimItem> items) {
        super(customerId, insuranceId, description, dateHappened, damages, 
                appraisal, image);
        this.items = items;
    }
    
    
    public void addItem(ClaimItem claimItem) {
        items.add(claimItem);
    }
    

    @Override
    public String toString() {
        String text = "Hus- og innbokademelding\n";
        text += super.toString();
        return text;
    }
}
