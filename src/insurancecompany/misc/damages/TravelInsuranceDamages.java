/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package insurancecompany.misc.damages;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author André
 */
public final class TravelInsuranceDamages {
    
    // TRAVEL INSURANCE DAMAGES
    public static final String LUGGAGE = "Reisegods";
    public static final String DELAY_EVACUATION = "Forsinkelse/evakuering";
    public static final String CANCELLATION = "Avbestilling";
    public static final String SICKNESS_INJURY = "Sykdom/skade";
    public static final String FULL_TIME_ACCIDENT_INSURANCE = "Heltids ulykkesforsikring";
    public static final String LIABILITY_AND_LEGAL_AID_OUTSIDE_NORDIC = "Ansvar og rettshjelp utenfor Norden";
    
    // TRAVEL INSURANCE COVERAGE
    public static final List<String> STANDARD_COVERAGE;
    
    static {
        List<String> standardCoverage = new ArrayList();
        standardCoverage.add(LUGGAGE);
        standardCoverage.add(DELAY_EVACUATION);
        standardCoverage.add(CANCELLATION);
        standardCoverage.add(SICKNESS_INJURY);
        standardCoverage.add(FULL_TIME_ACCIDENT_INSURANCE);
        standardCoverage.add(LIABILITY_AND_LEGAL_AID_OUTSIDE_NORDIC);
        STANDARD_COVERAGE = Collections.unmodifiableList(standardCoverage);
    }
    
    private TravelInsuranceDamages() {
        
    }    
}
