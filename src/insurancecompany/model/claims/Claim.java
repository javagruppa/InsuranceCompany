/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package insurancecompany.model.claims;

import insurancecompany.misc.coverages.Damage;
import insurancecompany.model.people.Customer;

import java.util.Date;
import java.awt.Image;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.EnumSet;
import java.util.Set;

/**
 *
 * @author André
 */
public abstract class Claim implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private static int nextClaimId = 1000000;
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
     * 
     * @param customerId
     * @param insuranceId
     * @param description 
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
     * @param owner 
     */
    public void setOwner(int customerId) {
        this.customerId = customerId;
    }
    
    /**
     * Sets a date to the claim.
     * @param date 
     */
    public void setDate(Calendar date) {
        this.date = date;
    }
    
    /**
     * Sets a description to the damage.
     * @param description 
     */
    public void setDescription(String description) {
        this.description = description;
    }
    
    /**
     * Sets an image of the damage.
     * @param image 
     */
    public void setImage(Image image) {
        this.image = image;
    }
    
    /**
     * Sets an appraisal sum to this claim.
     * @param appraisal 
     */
    public void setAppraisal(int appraisal) {
        this.appraisal = appraisal;
    }
    
    /**
     * Sets a disbursement sum to this claim.
     * @param disbursement 
     */
    public void setDisbursement(int disbursement) {
        this.disbursement = disbursement;
    }
    
    /**
     * 
     * @return owner of this claim
     */
    public int getCustomerId() {
        return customerId;
    }
    
    public int getClaimId() {
        return claimId;
    }
    
    /**
     * 
     * @return description of the damage.
     */
    public String getDescription() {
        return description;
    }
    
    /**
     * 
     * @return image of the damage.
     */
    public Image getImage() {
        return image;
    }
    
    /**
     * 
     * @return appraisal sum.
     */
    public int getAppraisal() {
        return appraisal;
    }
    
    /**
     * 
     * @return disbursement sum.
     */
    public int getDisbursement() {
        return disbursement;
    }
    
    public static void saveNextIdToFile() throws IOException {
        try (DataOutputStream dos = new DataOutputStream(
                new BufferedOutputStream(
                        new FileOutputStream(claimIdFileName)))) {
            dos.writeInt(nextClaimId);
        }
    }
    
    public static void readNextIdFromFile() throws IOException {
        try (DataInputStream dis = new DataInputStream(
                new BufferedInputStream(
                        new FileInputStream(claimIdFileName)))) {
            nextClaimId = dis.readInt();
        }
    }
    
    public void uploadImage() {
        
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
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        String text = "Dato: " + df.format(date) + "\n";
        text += "Skademeldingsnummer: " + claimId + "\n";
        text += "Beskrivelse av skade:\n";
        text += description + "\n";
        text += "Takseringsbeløp: " + appraisal + "\n";
        text += "Utbetalt erstatningsbeløp: " + disbursement;
        return text;
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
     * @return the insuranceId
     */
    public int getInsuranceId() {
        return insuranceId;
    }
}
