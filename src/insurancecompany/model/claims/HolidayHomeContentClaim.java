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
 * HolidayHome Content claim class. Sub class to claim. All claims regarding 
 * Holiday Home content insurances are registered as HolidayHome content claims.
 * 
 * @author André
 * @author Carl
 */
public class HolidayHomeContentClaim extends Claim implements Serializable {
    /** SerialVersionUID used to identify this class for object IO. */
    private static final long serialVersionUID = 1L;
    /** List of the items this claim covers. */    
    private List<ClaimItem> items;

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
     */
    public HolidayHomeContentClaim(int customerId, int insuranceId, String description, 
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
     * @param dateHappened the date the event occured
     * @param damages the damage(s) this claim is for
     * @param appraisal the appraisal of this claim
     * @param items the items this claim is for
     * @param image an image of the damage(s)
     */
    public HolidayHomeContentClaim(int customerId, int insuranceId, String description, 
            Calendar dateHappened, Set<Damage> damages, int appraisal, 
            List<ClaimItem> items, Image image) {
        super(customerId, insuranceId, description, dateHappened, damages, 
                appraisal, image);
        this.items = items;
    }
    
    /** @return The type of claim in form of a String. */
    @Override
    public String getName() {
        return ClaimType.HOLIDAY_HOME_CONTENT_CLAIM.toString();
    }
    
    /**
     * Adds an item to the list of item claimed for
     * @param claimItem the item to add
     */
    public void addItem(ClaimItem claimItem) {
        getItems().add(claimItem);
    }
    
    /**
     * Returns a string representation of this claim
     * @return a string representation of this claim
     */
    @Override
    public String toString() {
        String text = "Skademelding innbo fritidsbolig\n";
        text += super.toString();
        return text;
    }

    /**
     * Returns the list of items claimed for in this insurance
     * @return the items claimed for
     */
    public List<ClaimItem> getItems() {
        return items;
    }
}
