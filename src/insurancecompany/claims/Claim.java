/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package insurancecompany.claims;

import insurancecompany.people.Customer;

import java.util.Date;
import java.awt.Image;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 *
 * @author André
 */
public abstract class Claim {
    
    private static int nextClaimId = 1000000;
    private static String claimIdFileName = "/nextIdNumbers/claimId.dta";
    
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
        // Set the date to current date
        date = Calendar.getInstance().getTime();
        // Set unique claim id, aswell as uppdating next claim id:
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
}
