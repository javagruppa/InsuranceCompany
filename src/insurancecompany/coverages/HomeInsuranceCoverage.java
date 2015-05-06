/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package insurancecompany.coverages;

import insurancecompany.damages.HomeInsuranceDamages;
import java.util.List;

/**
 *
 * @author André
 */
public enum HomeInsuranceCoverage {
    HOME, CONTENTS, HOME_AND_CONTENTS;
    
    public List<String> damages() {
        switch(this) {
            case HOME: return HomeInsuranceDamages.HOME_COVERAGE;
            case CONTENTS: return HomeInsuranceDamages.CONTENTS_COVERAGE;
            case HOME_AND_CONTENTS: return HomeInsuranceDamages.HOME_AND_CONTENTS_COVERAGE;
            default: throw new IllegalArgumentException();
        }
    }
    
    @Override
    public String toString() {
        switch(this) {
            case HOME: return "Hus";
            case CONTENTS: return "Innbo";
            case HOME_AND_CONTENTS: return "Hus og innbo";
            default: throw new IllegalArgumentException();
        }
    }
}
