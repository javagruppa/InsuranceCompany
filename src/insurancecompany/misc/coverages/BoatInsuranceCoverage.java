/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package insurancecompany.misc.coverages;

import insurancecompany.misc.damages.BoatInsuranceDamages;

import java.util.List;

/**
 *
 * @author André
 */
public enum BoatInsuranceCoverage {
    CASCO, PARTLY_CASCO;
    
    public List<String> damages() {
        switch(this) {
            case CASCO: return BoatInsuranceDamages.CASCO_COVERAGE;
            case PARTLY_CASCO: return BoatInsuranceDamages.PARTLY_CASCO_COVERAGE;
            default: throw new IllegalArgumentException();
        }
    }
    
    @Override
    public String toString() {
        switch(this) {
            case CASCO: return "Kasko";
            case PARTLY_CASCO: return "Delkasko";
            default: throw new IllegalArgumentException();
        }
    }
}
