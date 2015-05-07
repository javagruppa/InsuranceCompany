/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package insurancecompany.model.claims;

import java.util.Calendar;

/**
 *
 * @author André
 */
public class ClaimItem {
    
    private String description; // Type/modell/fabrikk/år
    private String aqquiredArea; // Hvor kjøpt/Av hvem fått
    private Calendar aqquiredDate;
    private int value;
    private String descriptionOfDocumentation; //Beskriv hvordan anskaffelsen kan dokumenteres
    
    
    public ClaimItem(String description) {
        this.description = description;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the aqquiredArea
     */
    public String getAqquiredArea() {
        return aqquiredArea;
    }

    /**
     * @param aqquiredArea the aqquiredArea to set
     */
    public void setAqquiredArea(String aqquiredArea) {
        this.aqquiredArea = aqquiredArea;
    }

    /**
     * @return the aqquiredDate
     */
    public Calendar getAqquiredDate() {
        return aqquiredDate;
    }

    /**
     * @param aqquiredDate the aqquiredDate to set
     */
    public void setAqquiredDate(Calendar aqquiredDate) {
        this.aqquiredDate = aqquiredDate;
    }

    /**
     * @return the value
     */
    public int getValue() {
        return value;
    }

    /**
     * @param value the value to set
     */
    public void setValue(int value) {
        this.value = value;
    }

    /**
     * @return the descriptionOfDocumentation
     */
    public String getDescriptionOfDocumentation() {
        return descriptionOfDocumentation;
    }

    /**
     * @param descriptionOfDocumentation the descriptionOfDocumentation to set
     */
    public void setDescriptionOfDocumentation(String descriptionOfDocumentation) {
        this.descriptionOfDocumentation = descriptionOfDocumentation;
    }
    
    
}
