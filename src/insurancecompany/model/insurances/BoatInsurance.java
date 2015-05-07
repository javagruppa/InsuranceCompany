/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package insurancecompany.model.insurances;

import insurancecompany.model.coverages.BoatInsuranceCoverage;
import insurancecompany.model.vehicles.Boat;

/**
 *
 * @author Sindre
 */
public class BoatInsurance extends Insurance {
    /** The boat this insurance is for. */
    private Boat boat;
    /** The coverage of this insurance. */
    private BoatInsuranceCoverage coverage;
    
    /**
     * Constructs a new boat insurance with the specified boat, coverage, 
     * customerId, excess and hasAlarm. Active is set to true. Date is set to 
     * the current date. InsuranceId is automatically set to nextInsuranceId.
     * 
     * @param boat the boat this insurance is for
     * @param coverage the coverage of this insurance
     * @param customerId the id of the customer who owns this insurance
     * @param excess the excess of this insurance
     * @param hasAlarm whether the car this insurance is for has an alarm 
     * or not
     */
    public BoatInsurance(Boat boat, int customerId, 
            BoatInsuranceCoverage coverage, int excess, boolean hasAlarm) {
        super(customerId, excess);
        this.boat = boat;
        this.coverage = coverage;
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
        result.append("BÅTFORSIKRING");
        result.append("\n").append(super.toString());
        result.append("\nType: ").append(coverage.toString());
        // Returns the string.
        return result.toString();
    }
}