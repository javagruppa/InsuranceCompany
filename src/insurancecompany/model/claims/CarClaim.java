package insurancecompany.model.claims;

import insurancecompany.misc.ClaimType;
import insurancecompany.misc.coverages.Damage;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Set;
import javafx.scene.image.Image;

/**
 * Car claim class. Sub class to claim. All claims regarding cars are
 * registered as carclaims.
 * 
 * @author André
 */
public class CarClaim extends Claim implements Serializable {
    /** SerialVersionUID used to identify this class for object IO */
    private static final long serialVersionUID = 1L;
    /** The car claim form for this claim */
    CarClaimForm claimForm;

    /**
      * Constructs a car claim with the specified parameters
     * 
     * @param customerId the customerId of the customer who owns the insurance
     * @param insuranceId the Id of the insurance this claim is covered by
     * @param description the description of the damage
     * @param dateHappened the date the damage happened
     * @param damages the damage(s)
     * @param appraisal the appraisal for the damage
     * @param claimForm the car claim form for this claim
     */
    public CarClaim(int customerId, int insuranceId, String description, 
            Calendar dateHappened, Set<Damage> damages, int appraisal, 
            CarClaimForm claimForm) {
        super(customerId, insuranceId, description, dateHappened, damages, 
                appraisal);
        this.claimForm = claimForm;
    }    
    
    /**
     * Constructs a boatclaim with the specified parameters, with image of
     * the damage.
     * 
     * @param customerId the customerId of the customer who owns the isnurance
     * @param insuranceId the Id of the insurance this claim is covered by
     * @param description the description of the damage
     * @param dateHappened the date the damage happened
     * @param damages the damage(s)
     * @param appraisal the appraisal for the damage
     * @param claimForm the car claim form for this claim
     * @param image an image of the damage
     */
    public CarClaim(int customerId, int insuranceId, String description, 
            Calendar dateHappened, Set<Damage> damages, int appraisal, 
            Image image, CarClaimForm claimForm) {
        super(customerId, insuranceId, description, dateHappened, damages, 
                appraisal, image);
        this.claimForm = claimForm;
    }
    
    /** @return The type of claim in form of a String. */
    @Override
    public String getName() {
        return ClaimType.CAR_CLAIM.toString();
    }
    
    /**
     * Sets the car claim form for this insurance
     * @param claimForm to be set
     */
    public void setClaimForm(CarClaimForm claimForm) {
        this.claimForm = claimForm;
    }
    
    /**
     * returns the car claim form for this insurance
     * @return the carclaimform
     */
    public CarClaimForm getClaimForm() {
        return claimForm;
    }
    
    /**
     * Returns a string representation for this claim
     * @return a string representation for this claim
     */
    @Override
    public String toString() {
        String text = "Skademelding bil\n";
        text += super.toString();
        return text;
    }
}
