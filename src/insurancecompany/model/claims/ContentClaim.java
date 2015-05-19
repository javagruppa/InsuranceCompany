package insurancecompany.model.claims;

import insurancecompany.misc.ClaimType;
import insurancecompany.misc.coverages.Damage;
import java.io.Serializable;
import java.util.Calendar;
import java.util.List;
import java.util.Set;
import javafx.scene.image.Image;

/**
 * Content claim class. Sub class to claim. All claims regarding content
 * insurances are registered as content claims.
 * 
 * @author André
 * @author Carl
 */
public class ContentClaim extends Claim implements Serializable {
    /** SerialVersionUID used to identify this class for object IO */
    private static final long serialVersionUID = 1L;
    /** List of the items this claim is for */    
    private List<ClaimItem> items;

    /**
     * Constructs a content claim with the specified parameters
     * 
     * @param customerId the Id of the customer who owns this insurance
     * @param insuranceId the Id of the insurance covering this claim
     * @param description description of what has happened
     * @param dateHappened the date the event occured
     * @param damages the damage(s) this claim is for
     * @param appraisal the appraisal of this claim
     * @param items the items this claim is for
     */
    public ContentClaim(int customerId, int insuranceId, String description, 
            Calendar dateHappened, Set<Damage> damages, int appraisal, 
            List<ClaimItem> items) {
        super(customerId, insuranceId, description, dateHappened, damages, 
                appraisal);
        this.items = items;
    }    
    
    /**
     * Constructs a content claim with the specified parameters
     * 
     * @param customerId the Id of the customer who owns this insurance
     * @param insuranceId the Id of the insurance covering this claim
     * @param description description of what has happened
     * @param dateHappened the date the event occurred
     * @param damages the damage(s) this claim is for
     * @param appraisal the appraisal of this claim
     * @param items the items this claim is for
     * @param image an image of the damage(s)
     */
    public ContentClaim(int customerId, int insuranceId, String description, 
            Calendar dateHappened, Set<Damage> damages, int appraisal, 
            List<ClaimItem> items, Image image) {
        super(customerId, insuranceId, description, dateHappened, damages, 
                appraisal, image);
        this.items = items;
    }
    
    /** @return The type of claim in form of a String. */
    @Override
    public String getName() {
        return ClaimType.HOME_CONTENT_CLAIM.toString();
    }
    
    /**
     * Adds an item to the list of damaged/lost items
     * @param claimItem the item to be added
     */
    public void addItem(ClaimItem claimItem) {
        getItems().add(claimItem);
    }
    
    /**
     * Returns the list of item concerned in this claim
     * @return the items
     */
    public List<ClaimItem> getItems() {
        return items;
    }
    
    /**
     * Returns a string representation of this claim
     * @return a string representation of this claim
     */
    @Override
    public String toString() {
        String text = "Skademelding innbo\n";
        text += super.toString();
        return text;
    }

    
}
