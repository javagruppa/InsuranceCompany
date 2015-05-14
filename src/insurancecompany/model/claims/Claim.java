package insurancecompany.model.claims;

import insurancecompany.misc.DateUtility;
import insurancecompany.misc.coverages.Damage;
import java.awt.Image;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Set;

/**
 *
 * @author André
 */
public abstract class Claim implements Serializable {
    /** SerialVersionUID used to identify this class for object IO */
    private static final long serialVersionUID = 1L;
    /** Claim ID for the next registered claim */
    private static int nextClaimId = 1000000;
    /** the file path of the file the claim Id's are saved in */
    private static String claimIdFileName = "src/insurancecompany/resources/nextidnumbers/claimId.dta";
    /** Customer id to the owner of this claim. */
    private int customerId;
    /** Insurance id that belongs to this claim. */
    private int insuranceId;
    /** The date this claim was submitted. */
    private Calendar date;
    /** The date of when the damage happened */
    private Calendar dateHappened;
    /** Unique claim id representing this claim. */
    private final int claimId;
    /** Textual description of this claim. */
    private String description;
    /** Set of damages belonging to the claim.*/
    private Set<Damage> damages;
    /** Image description of this claim. */
    private Image image;
    /** Appraisal sum set to this claim. */
    private int appraisal; // NOR : takseringsbeløp av skaden
    /** Disbursement set to this claim. */
    private int disbursement; // NOR : utbetalt erstatningsbeløp
    
    /**
     * Constructs a claim with the specified parameters
     * 
     * @param customerId the customer Id of the customer reporting the claim
     * @param insuranceId the insurance Id of the insurance this claim is for
     * @param description description of the damage/claim
     * @param dateHappened the date the damage/accident happened
     * @param damages the damage(s) that has happened
     * @param appraisal the appraisal for this claim
     */
    public Claim(int customerId, int insuranceId, String description, 
            Calendar dateHappened, Set<Damage> damages, int appraisal) {
        this.customerId = customerId;
        this.insuranceId = insuranceId;
        this.description = description;
        this.dateHappened = dateHappened;
        this.damages = damages;
        this.appraisal = appraisal;
        // Set the date to current date:
        date = Calendar.getInstance();
        // Set unique claim id, aswell as uppdating next claim id:
        claimId = nextClaimId++;
    }
    
    /**
     * Constructs a claim with the specified parameters. Also includes image.
     * @param insuranceId the insurance Id of the insurance this claim is for
     * @param description description of the damage/claim
     * @param dateHappened the date the damage/accident happened
     * @param damages the damage(s) that has happened
     * @param appraisal the appraisal for this claim
     * @param image image of the damage
     */
    public Claim(int customerId, int insuranceId, String description, 
            Calendar dateHappened, Set<Damage> damages, int appraisal,
            Image image) {
        this.customerId = customerId;
        this.insuranceId = insuranceId;
        this.description = description;
        this.dateHappened = dateHappened;
        this.damages = damages;
        this.appraisal = appraisal;
        this.image = image;
        // Set the date to current date:
        date = Calendar.getInstance();
        // Set unique claim id, aswell as uppdating next claim id:
        claimId = nextClaimId++;
    }
    
    /**
     * Sets owner to this claim.
     * @param owner the owner of this claim
     */
    public void setOwner(int customerId) {
        this.customerId = customerId;
    }
    
    /**
     * Sets a date to the claim.
     * @param date the date for this claim
     */
    public void setDate(Calendar date) {
        this.date = date;
    }
    
    /**
     * Sets a description to the damage.
     * @param description the description of the damage
     */
    public void setDescription(String description) {
        this.description = description;
    }
    
    /**
     * Sets an image of the damage.
     * @param image image of the damage
     */
    public void setImage(Image image) {
        this.image = image;
    }
    
    /**
     * Sets an appraisal sum to this claim.
     * @param appraisal sum of appraisal for the claim
     */
    public void setAppraisal(int appraisal) {
        this.appraisal = appraisal;
    }
    
    /**
     * Sets a disbursement sum to this claim.
     * @param disbursement sum of disbursement for this claim
     */
    public void setDisbursement(int disbursement) {
        this.disbursement = disbursement;
    }
    
    /**
     * returns the owner of this claim
     * @return owner of this claim
     */
    public int getCustomerId() {
        return customerId;
    }
    
    /**
     * Returns the Id for this claim
     * @return the claimId
     */
    public int getClaimId() {
        return claimId;
    }
    
    /**
     * returns the description of the damage
     * @return description of the damage.
     */
    public String getDescription() {
        return description;
    }
    
    /**
     * returns the image of the damage
     * @return image of the damage.
     */
    public Image getImage() {
        return image;
    }
    
    /**
     * returns the appraisal sum for this claim
     * @return appraisal sum.
     */
    public int getAppraisal() {
        return appraisal;
    }
    
    /**
     * returns the disbursement sum for this claim
     * @return disbursement sum.
     */
    public int getDisbursement() {
        return disbursement;
    }
    
    /**
     * Saves the next id to file
     * @throws IOException 
     */
    public static void saveNextIdToFile() throws IOException {
        try (DataOutputStream dos = new DataOutputStream(
                new BufferedOutputStream(
                        new FileOutputStream(claimIdFileName)))) {
            dos.writeInt(nextClaimId);
        }
    }
    
    /**
     * reads the next Id from file
     * @throws IOException 
     */
    public static void readNextIdFromFile() throws IOException {
        try (DataInputStream dis = new DataInputStream(
                new BufferedInputStream(
                        new FileInputStream(claimIdFileName)))) {
            nextClaimId = dis.readInt();
        }
    }
    
    /**
     * Uploads an image
     */
    public void uploadImage(Image image) {
        this.image = image;
    }
    
    /**
     * Indicates whether some other claim is equal to this one. The result 
     * is true if and only if the argument is not null and is a Claim 
     * object that contains the same claimId value as this object.
     * 
     * @param obj the object to compare with
     * @return true if the objects are the same; false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if(obj != null && obj instanceof Claim) {
            Claim other = (Claim) obj;
            return getClaimId()== other.getClaimId();
        } else {
            return false;
        }   
    }
    
    /**
     * Returns a hash code value for this claim. This method is supported for 
     * the benefit of hash tables such as those provided by HashMap.
     * @return the hash code
     */
    @Override 
    public int hashCode() {
        final int prime = 31;
        int result = 17;
        result = prime * result + getClaimId();
        return result;
    }
    
    @Override
    public String toString() {
        // Creates a StringBuilder which will be returned at the end of the 
        // method.
        StringBuilder result = new StringBuilder();
        // Appends the fields with appropriate sentences.
        result.append("Kundenummer: ").append(customerId);
        result.append("\nForsikringspolise nummer: ").append(insuranceId);
        result.append("\nDato registrert: ").append(DateUtility.NORWEGIAN_DATE_FORMAT.format(date));
        result.append("\nSkademeldingsnummer: ").append(claimId);
        result.append("\nDato for skade/hendelse: ").append(dateHappened);
        result.append("\nBeskrivelse av skade:\n").append(description).append("\n");
        result.append("\nTakseringsbeløp: ").append(appraisal);
        result.append("\nUtbetalt erstatningsbeløp: ").append(disbursement);
        // Returns the string.
        return result.toString();
        
    }

    /**
     * @return the dateHappened
     */
    public Calendar getDateHappened() {
        return dateHappened;
    }

    /**
     * @param dateHappened the dateHappened to set
     */
    public void setDateHappened(Calendar dateHappened) {
        this.dateHappened = dateHappened;
    }

    /**
     * returns the insuranceId
     * @return the insuranceId
     */
    public int getInsuranceId() {
        return insuranceId;
    }
}
