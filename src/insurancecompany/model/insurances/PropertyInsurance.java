/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package insurancecompany.model.insurances;

/**
 *
 * @author Sindre
 */
public abstract class PropertyInsurance extends Insurance {
    /** The building insurance amount of this insurance. */
    private int buildingAmount;
    /** The content insurance amount of this insurance. */
    private int contentAmount;
    
    /**
     * Constructs a new property insurance with the specified buildingAmount, 
     * contentAmount, customerId and excess. Active is set to true. Date is set 
     * to the current date. InsuranceId is automatically set to nextInsuranceId.
     * 
     * @param buildingAmount the building insurance amount of this insurance
     * @param contentAmount the content insurance amount of this insurance
     * @param customerId the id of the customer who owns this insurance
     * @param excess the excess of this insurance
     */
    public PropertyInsurance(int buildingAmount, int contentAmount, 
            int customerId, int excess) {
        super(customerId, excess);
        this.buildingAmount = buildingAmount;
        this.contentAmount = contentAmount;
    }
    
    /**
     * Returns the building insurance amount of this insurance.
     * 
     * @return the building insurance amount of this insurance
     */
    public int getBuildingAmoutn() {
        return buildingAmount;
    }
    
    /**
     * Returns the content insurance amount of this insurance.
     * 
     * @return the content insurance amount of this insurance
     */
    public int getContentAmount() {
        return contentAmount;
    }
    
    /**
     * Returns a string representation of this insurance. The string
     * representation consists of each field with a short description separated
     * by a new line.
     * 
     * @return a string representation of this insurance
     */
    @Override
    public String toString() {
        // Creates a StringBuilder which will be returned at the end of the 
        // method.
        StringBuilder result = new StringBuilder();
        // Appends the fields with appropriate sentences.
        result.append(super.toString());
        result.append("\nForsikringsbeløp for hus: ").append(buildingAmount);
        result.append("\nForsikringsbeløp for innbo: ").append(contentAmount);
        // Returns the string.
        return result.toString();
    }
}