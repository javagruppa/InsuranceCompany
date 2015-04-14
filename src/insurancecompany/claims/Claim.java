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
    
    public Claim(Date date, int claimNumber, String description) {
        
    }
    
    public void setOwner(Customer owner) {
        this.owner = owner;
    }
    
    public void setDate(Date date) {
        this.date = date;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public void setImage(Image image) {
        this.image = image;
    }
    
    public void setAppraisal(int appraisal) {
        this.appraisal = appraisal;
    }
    
    public void setDisbursement(int disbursement) {
        this.disbursement = disbursement;
    }
    
    public void uploadImage() {
        
    }
    
    
    
    
}
