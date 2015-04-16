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
    
    private static int nextClaimNumber;
    private static int claimNumberFileName;
    
    private Customer owner;
    private Date date;
    private int claimNumber;
    private String description;
    private Image image;
    private int appraisal;
    private int disbursement;
    
    /**
     * Empty constructor.
     */
    public Claim() {
        claimNumber = nextClaimNumber++;
    }
    
    /**
     * Constructor initializing date and description of a claim.
     * @param date
     * @param description 
     */
    public Claim(Date date, String description) {
        this.date = date;
        this.description = description;
    }
    
    /**
     * Sets owner to the claim.
     * @param owner 
     */
    public void setOwner(Customer owner) {
        this.owner = owner;
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
    public Customer getOwner() {
        return owner;
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
