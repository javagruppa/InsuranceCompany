package insurancecompany.model.claims;

import insurancecompany.misc.DateUtility;
import java.io.Serializable;
import java.util.Calendar;

/**
 * The class claim item. This is the item damaged/lost, that the customer
 * needs repaired/replaced
 * 
 * @author André
 * @since 19.05.2015
 */
public class ClaimItem implements Serializable {
    /** SerialVersionUID used to identify this class for object IO */
    private static final long serialVersionUID = 1L;
    /** description of the item */
    private String description;
    /** Where the item was acquired */
    private String aqquiredArea;
    /** The date the item was acquired */
    private Calendar aqquiredDate;
    /** The value of the item */
    private int value;
    /** Description of how the item can be documented */
    private String descriptionOfDocumentation;
    
    /**
     * Constructs a claimItem with the specified parameters
     * @param description description of the item
     * @param aqquiredArea where the item was acquired
     * @param aqquiredDate when the item was acquired
     * @param value the value of the item
     * @param descOfDocumentation description of how the item can be documented
     */
    public ClaimItem(String description, String aqquiredArea,
            Calendar aqquiredDate, int value, String descOfDocumentation) {
        this.description = description;
        this.aqquiredArea = aqquiredArea;
        this.aqquiredDate = aqquiredDate;
        this.descriptionOfDocumentation = descOfDocumentation;
    }

    /**
     * Returns the description of the item
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the item
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Returns where the item was acquired
     * @return the aqquiredArea
     */
    public String getAqquiredArea() {
        return aqquiredArea;
    }

    /**
     * sets where the item was acquired
     * @param aqquiredArea the aqquiredArea to set
     */
    public void setAqquiredArea(String aqquiredArea) {
        this.aqquiredArea = aqquiredArea;
    }

    /**
     * Returns when the item was acquired
     * @return the aqquiredDate
     */
    public Calendar getAqquiredDate() {
        return aqquiredDate;
    }

    /**
     * sets when the item was acquired
     * @param aqquiredDate the aqquiredDate to set
     */
    public void setAqquiredDate(Calendar aqquiredDate) {
        this.aqquiredDate = aqquiredDate;
    }

    /**
     * Returns the value of the item
     * @return the value
     */
    public int getValue() {
        return value;
    }

    /**
     * Sets the value of the item
     * @param value the value to set
     */
    public void setValue(int value) {
        this.value = value;
    }

    /**
     * Returns the description of how the item can be documented
     * @return the descriptionOfDocumentation
     */
    public String getDescriptionOfDocumentation() {
        return descriptionOfDocumentation;
    }

    /**
     * Sets the description of how the item can be documented
     * @param descriptionOfDocumentation the descriptionOfDocumentation to set
     */
    public void setDescriptionOfDocumentation(String descriptionOfDocumentation) {
        this.descriptionOfDocumentation = descriptionOfDocumentation;
    }
    
    /**
     * Returns a string representation of this claim item.
     * @return a string representation of this claim item
     */
    public String toString() {
        // Creates a StringBuilder which will be returned at the end of the 
        // method.
        StringBuilder result = new StringBuilder();
        // Appends the fields with appropriate sentences.
        result.append("Beskrivelse av objektet: ").append(description);
        result.append("\nSted for anskaffelse: ").append(aqquiredArea);
        if (aqquiredDate != null) {
            result.append("\nDato for anskaffelse: ").append(DateUtility.NORWEGIAN_DATE_FORMAT.format(aqquiredDate.getTime()));
        }
        result.append("\nVerdi: ").append(value);
        result.append("\nHvordan kan objektet dokumenteres: ").append(descriptionOfDocumentation);
        // Returns the string.
        return result.toString();
    }
    
}
