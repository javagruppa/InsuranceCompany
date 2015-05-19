package insurancecompany.model.claims;

import insurancecompany.misc.ClaimType;
import insurancecompany.misc.coverages.Damage;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Set;
import javafx.scene.image.Image;

/**
 * Boat claim class. Sub class to claim. All claims regarding boats are
 * registered as boat claims
 * @author André
 * @author Carl
 */
public class BoatClaim extends Claim implements Serializable {
    /** SerialVersionUID used to identify this class for object IO */
    private static final long serialVersionUID = 1L;

    /**
     * Constructs a boat claim with the specified parameters.
     * 
     * @param customerId the customerId of the customer who owns the insurance
     * @param insuranceId the Id of the insurance this claim is covered by
     * @param description the description of the damage
     * @param dateHappened the date the damage happened
     * @param damages the damage(s)
     * @param appraisal the appraisal for the damage
     */
    public BoatClaim(int customerId, int insuranceId, String description, 
            Calendar dateHappened, Set<Damage> damages, int appraisal) {
        super(customerId, insuranceId, description, dateHappened, damages, 
                appraisal);
    }    
    
    /**
     * Constructs a boatclaim with the specified parameters, with image
     * 
     * @param customerId the customerId of the customer who owns the isnurance
     * @param insuranceId the Id of the insurance this claim is covered by
     * @param description the description of the damage
     * @param dateHappened the date the damage happened
     * @param damages the damage(s)
     * @param appraisal the appraisal for the damage
     * @param image image of the damage
     */
    public BoatClaim(int customerId, int insuranceId, String description, 
            Calendar dateHappened, Set<Damage> damages, int appraisal, 
            Image image) {
        super(customerId, insuranceId, description, dateHappened, damages, 
                appraisal, image);
    }
    
    /** @return The type of claim in form of a String. */
    @Override
    public String getName() {
        return ClaimType.BOAT_CLAIM.toString();
    }

    /**
     * Returns a string representation of this claim
     * @return a string representation of this claim
     */
    @Override
    public String toString() {
        String text = "SKADEMELDING BÅT\n";
        text += super.toString();
        return text;
    }
}
