/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package insurancecompany.claims;

import insurancecompany.people.Customer;

import java.util.Date;
import java.awt.Image;

/**
 *
 * @author André
 */
public abstract class Claim {
    
    private static int nextClaimId;
    private static int claimIdFileName;
    
    /** Customer id to the owner of this claim. */
    private int customerId;
    /** The date this claim was submitted. */
    private Date date;
    /** Unique claim id representing this claim. */
    private int claimId;
    /** Textual description of this claim. */
    private String description;
    /** Image description of this claim. */
    private Image image;
    /** Appraisal sum set to this claim. */
    private int appraisal;
    /** Disbursement set to this claim. */
    private int disbursement;
    
    /**
     * Empty constructor.
     */
    public Claim() {
        date = new Date();
        claimId = nextClaimId++;
    }
    
    /**
     * Constructor initializing date and description of a claim.
     * @param date
     * @param description 
     */
    public Claim(String description) {
        date = new Date();
        this.description = description;
    }
    
    /**
     * Sets owner to the claim.
     * @param owner 
     */
    public void setOwner(int customerId) {
        this.customerId = customerId;
    }
    
    /**
     * Sets a date to the claim.
     * @param date 
     */
    public void setDate(Date date) {
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
     * Sets an appraisal sum to the claim.
     * @param appraisal 
     */
    public void setAppraisal(int appraisal) {
        this.appraisal = appraisal;
    }
    
    /**
     * Sets a disbursement sum to the claim.
     * @param disbursement 
     */
    public void setDisbursement(int disbursement) {
        this.disbursement = disbursement;
    }
    
    /**
     * 
     * @return owner of the claim.
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
    
    public static void saveNextClaimNumber() {
        
    }
    
    public static void readNextClaimNumber() {
        
    }
    
    public void uploadImage() {
        
    }
    
    public String toString() {
        String text = "";
        return text;
    }
}
