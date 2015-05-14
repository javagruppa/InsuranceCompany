package insurancecompany.model.claims;

import insurancecompany.misc.coverages.Damage;
import java.awt.Image;
import java.io.Serializable;
import java.util.Calendar;
import java.util.List;
import java.util.Set;

/**
 * TravelClaim class. Sub class to claim. All claims regarding Travel
 * Insurances are registered as Travel claims.
 * 
 * @author André
 */
public class TravelClaim extends Claim implements Serializable {
    /** SerialVersionUID used to identify this class for object IO. */
    private static final long serialVersionUID = 1L;
    /** Brand of the customers credit card. */
    private String creditCardBrand;
    /** Country of the event. */
    private String country;
    /** List of the items this claim covers. */
    private List<ClaimItem> items;
    
    /**
     * Constructs a TravelClaim with the specified parameters.
     * 
     * @param customerId Id of customer owning the insurance covering this claim
     * @param insuranceId Id of insurance covering this claim
     * @param description description of the loss/damage
     * @param dateHappened date when the event happened
     * @param damages the damages covered by this claim
     * @param appraisal the appraisal to this claim
     * @param creditCardBrand the credit card brand of the customer
     * @param country the country the event happened
     * @param items the items this claim is for
     */
    public TravelClaim(int customerId, int insuranceId, String description, 
            Calendar dateHappened, Set<Damage> damages, int appraisal, 
            String creditCardBrand, String country, List<ClaimItem> items) {
        super(customerId, insuranceId, description, dateHappened, damages, 
                appraisal);
        this.creditCardBrand = creditCardBrand;
        this.country = country;
        this.items = items;
    }    
    
    /**
     * Constructs a TravelClaim with the specified parameters
     * 
     * @param customerId Id of customer owning the insurance covering this claim
     * @param insuranceId Id of insurance covering this claim
     * @param description description of the loss/damage
     * @param dateHappened date when the event happened
     * @param damages the damages covered by this claim
     * @param appraisal the appraisal to this claim
     * @param creditCardBrand the credit card brand of the customer
     * @param country the country the event happened
     * @param image image of the damage/loss
     * @param items the items this claim is for
     */
    public TravelClaim(int customerId, int insuranceId, String description, 
            Calendar dateHappened, Set<Damage> damages, int appraisal, 
            Image image, String creditCardBrand, String country,
            List<ClaimItem> items) {
        super(customerId, insuranceId, description, dateHappened, damages, 
                appraisal, image);
        this.creditCardBrand = creditCardBrand;
        this.country = country;
        this.items = items;
    }

    /**
     * Returns a string representation of this claim
     * @return a string representation of this claim
     */
    @Override
    public String toString() {
        String text = "Skademelding reiseforsikring\n";
        text += super.toString();
        return text;
    }

    /**
     * returns the credit card brand
     * @return the creditCardBrand
     */
    public String getCreditCardBrand() {
        return creditCardBrand;
    }

    /**
     * Sets the credit card brand
     * @param creditCardBrand the creditCardBrand to set
     */
    public void setCreditCardBrand(String creditCardBrand) {
        this.creditCardBrand = creditCardBrand;
    }

    /**
     * Returns the country where the event happened
     * @return the country
     */
    public String getCountry() {
        return country;
    }

    /**
     * sets the country in which the event happened
     * @param country the country to set
     */
    public void setCountry(String country) {
        this.country = country;
    }
}
