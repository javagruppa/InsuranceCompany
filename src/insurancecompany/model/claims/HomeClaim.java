package insurancecompany.model.claims;

import insurancecompany.misc.ClaimType;
import insurancecompany.misc.coverages.Damage;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;
import javafx.scene.image.Image;

/**
 * HomeClaim class. Sub class to claim. All claims regarding Home
 * insurances are registered as Home claims.
 * 
 * @author André
 */
public class HomeClaim extends Claim implements Serializable {
    /** SerialVersionUID used to identify this class for object IO */
    private static final long serialVersionUID = 1L;
        
    /**
     * Constructs a HomeClaim with the specified parameters
     * 
     * @param customerId the Id of the customers who owns the insurance
     * @param insuranceId the Id of the insurance covering the claim
     * @param description description of the event
     * @param dateHappened when the event occurred
     * @param damages the damaged this claim is for
     * @param appraisal the appraisal of this claim
     */
    public HomeClaim(int customerId, int insuranceId, String description, 
            Calendar dateHappened, Set<Damage> damages, int appraisal) {
        super(customerId, insuranceId, description, dateHappened, damages, 
                appraisal);
    }    
    
    /**
     * Constructs a HolidayHomeClaim with the specified parameters
     * 
     * @param customerId the Id of the customers who owns the insurance
     * @param insuranceId the Id of the insurance covering the claim
     * @param description description of the event
     * @param dateHappened when the event occured
     * @param damages the damaged this claim is for
     * @param appraisal the appraisal of this claim
     * @param image an image of the damage(s)
     * 
     */
    public HomeClaim(int customerId, int insuranceId, String description, 
            Calendar dateHappened, Set<Damage> damages, int appraisal, 
            Image image) {
        super(customerId, insuranceId, description, dateHappened, damages, 
                appraisal, image);
    }
    
    /** @return The type of claim in form of a String. */
    @Override
    public String getName() {
        return ClaimType.HOME_CLAIM.toString();
    }
    
    
    /**
     * Returns a string representation of this claim
     * @return a string representation of this claim
     */
    @Override
    public String toString() {
        String text = "Skademelding husforsikring\n";
        text += super.toString();
        return text;
    }
}
