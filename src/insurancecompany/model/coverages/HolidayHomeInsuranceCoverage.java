/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package insurancecompany.model.coverages;

import insurancecompany.model.damages.HolidayHomeInsuranceDamages;
import java.util.List;

/**
 *
 * @author André
 */
public enum HolidayHomeInsuranceCoverage {
    HOME, CONTENTS, HOME_AND_CONTENTS;
    
    public List<String> damages() {
        switch(this) {
            case HOME: return HolidayHomeInsuranceDamages.HOLIDAY_HOME_COVERAGE;
            case CONTENTS: return HolidayHomeInsuranceDamages.HOLIDAY_HOME_CONTENTS_COVERAGE;
            case HOME_AND_CONTENTS: return HolidayHomeInsuranceDamages.HOLIDAY_HOME_FULL_COVERAGE;
            default: throw new IllegalArgumentException();
        }
    }
    
    @Override
    public String toString() {
        switch(this) {
            case HOME: return "Fritidsbolig hus";
            case CONTENTS: return "Fritidsbolig innbo";
            case HOME_AND_CONTENTS: return "Fritidsbolig hus og innbo";
            default: throw new IllegalArgumentException();
        }
    }
}
