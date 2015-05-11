/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package discarded;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author André
 */
public final class HomeInsuranceDamages {
    
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
    public static final List<String> HOME_COVERAGE;
    public static final List<String> CONTENTS_COVERAGE;
    public static final List<String> HOME_AND_CONTENTS_COVERAGE;
            
    static {
        List<String> homeCoverage = new ArrayList<String>();
        homeCoverage.add(FULL_VALUE_GUARANTEE);
        homeCoverage.add(BUILDING_DAMAGE);
        homeCoverage.add(TOTAL_REENTRY_OVER_SEVENTYFIVE);
        homeCoverage.add(GARDEN_ARTICLES);
        homeCoverage.add(REBUILDING_FOR_WHEELCHAIR_USER);
        homeCoverage.add(LOSS_OF_RENT);
        homeCoverage.add(LEGAL_LIABILITIES);
        homeCoverage.add(LEGAL_AID);
        homeCoverage.add(RODENT_DAMAGE);
        HOME_COVERAGE = Collections.unmodifiableList(homeCoverage);
        
        List<String> contentsCoverage = new ArrayList<String>();
        contentsCoverage.add(FIRE_DAMAGE);
        contentsCoverage.add(WATER_DAMAGE);
        contentsCoverage.add(THEFT);
        contentsCoverage.add(NATURAL_DISASTER);
        contentsCoverage.add(BAG_SNATCHING);
        contentsCoverage.add(ROBBERY_AND_ASSAULT);
        contentsCoverage.add(SUBSISTENCE_EXPENSE);
        contentsCoverage.add(LEGAL_LIABILITIES);
        contentsCoverage.add(LEGAL_AID);
        CONTENTS_COVERAGE = Collections.unmodifiableList(contentsCoverage);
        
        List<String> homeAndContentsCoverage = new ArrayList<String>();
        homeAndContentsCoverage.addAll(HOME_COVERAGE);
        homeAndContentsCoverage.addAll(CONTENTS_COVERAGE);       
        HOME_AND_CONTENTS_COVERAGE = Collections.unmodifiableList(homeAndContentsCoverage);
    }
    
    private HomeInsuranceDamages() {
        
    }
}
