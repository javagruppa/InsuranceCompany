/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package insurancecompany.model.damages;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author André
 */
public final class HolidayHomeInsuranceDamages {
        
    // HOLIDAY_HOME_COVERAGE AND CONTENT INSURANCE DAMAGES / NOR: HOLIDAY_HOME_COVERAGE OG INNBO
    public static final String FULL_VALUE_GUARANTEE = "Fullverdigaranti";
    public static final String BUILDING_DAMAGE = "Bygningsskader";
    public static final String TOTAL_REENTRY_OVER_SEVENTYFIVE = "Total gjennopføring ved mer enn 75% skade";
    public static final String GARDEN_ARTICLES = "Skader på hageanlegg";
    public static final String REBUILDING_FOR_WHEELCHAIR_USER = "Ombygging for rullestolbruker";
    public static final String LOSS_OF_RENT = "Husleietap";
    public static final String LEGAL_LIABILITIES = "Rettslige erstatningsansvar";
    public static final String LEGAL_AID = "Rettshjelp";
    public static final String RODENT_DAMAGE = "Skader etter gangere";
    
    // NOT IN USE, HOLIDAY_HOME_COVERAGE INSURANCE PLUS DAMAGES
    //public static final String FUNGUS_AND_ROT_DAMAGE = "Skader etter sopp og råte";
    //public static final String PEST_CONTROL = "Bekjempelse av skadeinntekter";
    //public static final String CONSEQUENTIAL_DAMAGE_AFTER_LEAK = "Følgeskader etter utett tak/vegg";
    //public static final String CONSEQUENTIAL_DAMAGE_AFTER_CRAFTS_ERROR = "Følgeskader av håndverksfeil ";
    //public static final String HOLIDAY_WARRANTY = "Feriegaranti"; // <- Only damage that is unique from HomeInsuranceCoverages
    
    
    public static final String FIRE_DAMAGE = "Brannskader";
    public static final String WATER_DAMAGE = "Vannskader ";
    public static final String THEFT = "Tyveri";
    public static final String NATURAL_DISASTER = "Naturskader";
    public static final String BAG_SNATCHING = "Veskenapping";
    public static final String ROBBERY_AND_ASSAULT = "Ran og overfall";
    public static final String SUBSISTENCE_EXPENSE = "Oppholdsutgifter";
    //public static final String LGEAL_LIABILITIES = "Rettslige erstatningsansvar";
    //public static final String LEGAL_AID = "Rettshjelp";
  
    
    // HOLIDAY_HOME_COVERAGE AND HOLIDAY_HOME_CONTENTS_COVERAGE INSURANCE COVERAGE
    public static final List<String> HOLIDAY_HOME_COVERAGE;
    public static final List<String> HOLIDAY_HOME_CONTENTS_COVERAGE;
    public static final List<String> HOLIDAY_HOME_FULL_COVERAGE;
            
    static {
        List<String> holidayHomeCoverage = new ArrayList();
        holidayHomeCoverage.add(FULL_VALUE_GUARANTEE);
        holidayHomeCoverage.add(BUILDING_DAMAGE);
        holidayHomeCoverage.add(TOTAL_REENTRY_OVER_SEVENTYFIVE);
        holidayHomeCoverage.add(GARDEN_ARTICLES);
        holidayHomeCoverage.add(REBUILDING_FOR_WHEELCHAIR_USER);
        holidayHomeCoverage.add(LOSS_OF_RENT);
        holidayHomeCoverage.add(LEGAL_LIABILITIES);
        holidayHomeCoverage.add(LEGAL_AID);
        holidayHomeCoverage.add(RODENT_DAMAGE);
        HOLIDAY_HOME_COVERAGE = Collections.unmodifiableList(holidayHomeCoverage);
               
        List<String> holidayHomeContentsCoverage = new ArrayList();
        holidayHomeContentsCoverage.add(FIRE_DAMAGE);
        holidayHomeContentsCoverage.add(WATER_DAMAGE);
        holidayHomeContentsCoverage.add(THEFT);
        holidayHomeContentsCoverage.add(NATURAL_DISASTER);
        holidayHomeContentsCoverage.add(BAG_SNATCHING);
        holidayHomeContentsCoverage.add(ROBBERY_AND_ASSAULT);
        holidayHomeContentsCoverage.add(SUBSISTENCE_EXPENSE);
        holidayHomeContentsCoverage.add(LEGAL_LIABILITIES);
        holidayHomeContentsCoverage.add(LEGAL_AID);
        HOLIDAY_HOME_CONTENTS_COVERAGE = Collections.unmodifiableList(holidayHomeContentsCoverage);
        
        List<String> holidayHomeFullCoverage = new ArrayList();
        holidayHomeFullCoverage.addAll(HOLIDAY_HOME_COVERAGE);
        holidayHomeFullCoverage.addAll(HOLIDAY_HOME_CONTENTS_COVERAGE);
        HOLIDAY_HOME_FULL_COVERAGE = Collections.unmodifiableList(holidayHomeFullCoverage);
    }
    
    private HolidayHomeInsuranceDamages() {
        
    }
}
