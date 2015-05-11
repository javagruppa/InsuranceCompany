/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package insurancecompany.misc.coverages;

import insurancecompany.misc.damages.HomeInsuranceDamages;
import java.util.List;

/**
 *
 * @author André
 */
public enum HomeInsuranceCoverage {
    PLUS, BASIC;
    
    public List<String> damages() {
        switch(this) {
            case PLUS: return HomeInsuranceDamages.PLUS;
            case BASIC: return HomeInsuranceDamages.BASIC;
            default: throw new IllegalArgumentException();
        }
    }
    
    @Override
    public String toString() {
        switch(this) {
            case PLUS: return "Pluss";
            case BASIC: return "Basis";
            default: throw new IllegalArgumentException();
        }
    }
    
    public int getPricing() {
        switch(this) {
            case PLUS: return 1000;
            case BASIC: return 0;
            default: throw new IllegalArgumentException();
        }
    }
    
    
        
}
