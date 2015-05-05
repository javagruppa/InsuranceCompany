/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package insurancecompany.coverages;

import java.util.ArrayList;

/**
 *
 * @author André
 */
public final class HomeInsuranceCoverages {
    
    // HOME_COVERAGE AND CONTENT INSURANCE DAMAGES / NOR: HOME_COVERAGE OG INNBO
    public static final String FULL_VALUE_GUARANTEE = "Fullverdigaranti";
    public static final String BUILDING_DAMAGE = "Bygningsskader";
    public static final String TOTAL_REENTRY_OVER_SEVENTYFIVE = "Total gjennopføring ved mer enn 75% skade";
    public static final String GARDEN_ARTICLES = "Skader på hageanlegg";
    public static final String REBUILDING_FOR_WHEELCHAIR_USER = "Ombygging for rullestolbruker";
    public static final String LOSS_OF_RENT = "Husleietap";
    public static final String LEGAL_LIABILITIES = "Rettslige erstatningsansvar";
    public static final String LEGAL_AID = "Rettshjelp";
    public static final String RODENT_DAMAGE = "Skader etter gangere";
    
    // NOT IN USE, HOME_COVERAGE INSURANCE PLUS DAMAGES
    //public static final String FUNGUS_AND_ROT_DAMAGE = "Skader etter sopp og råte";
    //public static final String PEST_CONTROL = "Bekjempelse av skadeinntekter";
    //public static final String CONSEQUENTIAL_DAMAGE_AFTER_LEAK = "Følgeskader etter utett tak/vegg";
    //public static final String CONSEQUENTIAL_DAMAGE_AFTER_CRAFTS_ERROR = "Følgeskader av håndverksfeil ";
    
    
    public static final String FIRE_DAMAGE = "Brannskader";
    public static final String WATER_DAMAGE = "Vannskader ";
    public static final String THEFT = "Tyveri";
    public static final String NATURAL_DISASTER = "Naturskader";
    public static final String BAG_SNATCHING = "Veskenapping";
    public static final String ROBBERY_AND_ASSAULT = "Ran og overfall";
    public static final String SUBSISTENCE_EXPENSE = "Oppholdsutgifter";
    //public static final String LGEAL_LIABILITIES = "Rettslige erstatningsansvar";
    //public static final String LEGAL_AID = "Rettshjelp";
    
    // NOT IN USE, CONTENT INSURANCE PLUS DAMAGES
    //public static final String ACCIDENTAL_INJURY = "Uhellskader";
    //public static final String MOVING_INSURANCE = "Flytteforsikring";
    //public static final String ID_ANTITHEFT = "ID-tyverisikring";
    
    
    // HOME_COVERAGE AND CONTENTS_COVERAGE INSURANCE COVERAGE
    public static final ArrayList<String> HOME_COVERAGE;
    public static final ArrayList<String> CONTENTS_COVERAGE;
            
    static {
        HOME_COVERAGE = new ArrayList();
        HOME_COVERAGE.add(FULL_VALUE_GUARANTEE);
        HOME_COVERAGE.add(BUILDING_DAMAGE);
        HOME_COVERAGE.add(TOTAL_REENTRY_OVER_SEVENTYFIVE);
        HOME_COVERAGE.add(GARDEN_ARTICLES);
        HOME_COVERAGE.add(REBUILDING_FOR_WHEELCHAIR_USER);
        HOME_COVERAGE.add(LOSS_OF_RENT);
        HOME_COVERAGE.add(LEGAL_LIABILITIES);
        HOME_COVERAGE.add(LEGAL_AID);
        HOME_COVERAGE.add(RODENT_DAMAGE);
        
        CONTENTS_COVERAGE = new ArrayList();
        CONTENTS_COVERAGE.add(FIRE_DAMAGE);
        CONTENTS_COVERAGE.add(WATER_DAMAGE);
        CONTENTS_COVERAGE.add(THEFT);
        CONTENTS_COVERAGE.add(NATURAL_DISASTER);
        CONTENTS_COVERAGE.add(BAG_SNATCHING);
        CONTENTS_COVERAGE.add(ROBBERY_AND_ASSAULT);
        CONTENTS_COVERAGE.add(SUBSISTENCE_EXPENSE);
        CONTENTS_COVERAGE.add(LEGAL_LIABILITIES);
        CONTENTS_COVERAGE.add(LEGAL_AID); 
    }
    
    private HomeInsuranceCoverages() {
        
    }
}
