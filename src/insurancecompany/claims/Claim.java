/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package insurancecompany.claims;

import people.Customer;

import java.util.Date;
import java.awt.Image;

/**
 *
 * @author André
 */
public class Claim {
    
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
        
    }
    
    public Claim(Date date, String description) {
        
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
     * Sets a description to the claim.
     * @param description 
     */
    public void setDescription(String description) {
        this.description = description;
    }
    
    /**
     * Sets an image to the claim.
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
    
    public Customer getOwner() {
        return owner;
    }
    
    public String getDescription() {
        return description;
    }
    
    public Image getImage() {
        return image;
    }
    
    public int getAppraisal() {
        return appraisal;
    }
    
    public int getFisbursement() {
        return disbursement;
    }
    
    public void uploadImage() {
        
    }
    
    
    
    
}
