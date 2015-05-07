/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package insurancecompany.model.coverages;

import insurancecompany.model.damages.TravelInsuranceDamages;
import java.util.List;

/**
 *
 * @author André
 */
public enum TravelInsuranceCoverage {
    STANDARD;
    //PLUS,
    //FAMILY,
    //FAMILY_PLUS;
    
    public List<String> damages() {
        switch(this) {
            case STANDARD: return TravelInsuranceDamages.STANDARD_COVERAGE;
            default: throw new IllegalArgumentException();
        }
    }
    
    @Override
    public String toString() {
        switch(this) {
            case STANDARD: return "Standard";
            default: throw new IllegalArgumentException();
        }
    }
}
