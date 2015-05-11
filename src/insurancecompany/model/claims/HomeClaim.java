/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package insurancecompany.model.claims;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author André
 */
public class HomeClaim extends Claim implements Serializable {
    private static final long serialVersionUID = 1L;
     
    
    private List<ClaimItem> items;
    
    /**
     * 
     * @param customerId
     * @param insuranceId
     * @param description
     */
    public HomeClaim(int customerId, int insuranceId, String description) {
        super(customerId, insuranceId, description);
        items = new ArrayList<ClaimItem>();
    }
    
    /**
     * Constructor initializing date and description of a claim.
     * @param date
     * @param description 
     */
    public HomeClaim(int customerId, String description) {
        super(customerId, description);
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
