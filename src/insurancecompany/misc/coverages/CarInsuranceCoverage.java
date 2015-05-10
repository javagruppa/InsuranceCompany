/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package insurancecompany.misc.coverages;

import insurancecompany.misc.damages.CarInsuranceDamages;
import java.util.List;

/**
 *
 * @author André
 */
public enum CarInsuranceCoverage {
    CASCO {
        @Override
        public List<String> damages() {
            return CarInsuranceDamages.CASCO_COVERAGE;
        }
    }, PARTLY_CASCO {
        @Override
        public List<String> damages() {
            return CarInsuranceDamages.PARTLY_CASCO_COVERAGE;
        }
    }, LIABILITY {
        @Override
        public List<String> damages() {
            return CarInsuranceDamages.LIABILITY_COVERAGE;
        }
    };
    
    public abstract List<String> damages();
    
    @Override
    public String toString() {
        switch(this) {
            case CASCO: return "Kasko";
            case PARTLY_CASCO: return "Delkasko";
            case LIABILITY: return "Ansvar";
            default: throw new IllegalArgumentException();
        }
    }
    
        public int getPricing() {
        switch(this) {
            case CASCO: return 4000;
            case PARTLY_CASCO: return 2500;
            case LIABILITY: return 1500;
            default: throw new IllegalArgumentException();
        }
    }
    

}