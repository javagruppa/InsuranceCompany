/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package insurancecompany.insurances;

import insurancecompany.people.Customer;

/**
 *
 * @author Sindre
 */
public abstract class PropertyInsurance extends Insurance {
    /** The building insurance amount of this insurance. Part of the total 
     * insurance amount. */
    private int buildingAmount;
    /** The content insurance amount of this insurance. Part of the total 
     * insurance amount. */
    private int contentAmount;
    
    /**
     * Constructor initializing active, customer, date and insuranceConditions 
     * of this insurance.
     * @param customer customer who owns this insurance
     */
    public PropertyInsurance(Customer customer) {
        super(customer);
    }
    
    /**
     * Set a building insurance amount to this insurance. Updates the total 
     * insurance amount.
     * @param buildingAmount the building insurance amount of this insurance
     */
    public void setBuildingAmount(int buildingAmount) {
        this.buildingAmount = buildingAmount;
        updateInsuranceAmount();
    }
    
    /**
     * Set a content insurance amount to this insurance. Updates the total 
     * insurance amount.
     * @param contentAmount the content insurance amount of this insurance
     */
    public void setContentAmount(int contentAmount) {
        this.contentAmount = contentAmount;
        updateInsuranceAmount();
    }
    
    /**
     * Updates the insurance amount based on the sum of the building insurance 
     * amount and the content insurance amount.
     */
    public void updateInsuranceAmount() {
        setAmount(buildingAmount + contentAmount);
    }
}