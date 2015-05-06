/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package insurancecompany.coverages;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author André
 */
public final class TravelInsuranceCoverages {
    
    // TRAVEL INSURANCE DAMAGES
    public static final String LUGGAGE = "Reisegods";
    public static final String DELAY_EVACUATION = "Forsinkelse/evakuering";
    public static final String CANCELLATION = "Avbestilling";
    public static final String SICKNESS_INJURY = "Sykdom/skade";
    public static final String FULL_TIME_ACCIDENT_INSURANCE = "Heltids ulykkesforsikring";
    public static final String LIABILITY_AND_LEGAL_AID_OUTSIDE_NORDIC = "Ansvar og rettshjelp utenfor Norden";
    
    // TRAVEL INSURANCE COVERAGE
    public static final List<String> TRAVEL_COVERAGE;
    
    static {
        List<String> travelCoverage = new ArrayList();
        travelCoverage.add(LUGGAGE);
        travelCoverage.add(DELAY_EVACUATION);
        travelCoverage.add(CANCELLATION);
        travelCoverage.add(SICKNESS_INJURY);
        travelCoverage.add(FULL_TIME_ACCIDENT_INSURANCE);
        travelCoverage.add(LIABILITY_AND_LEGAL_AID_OUTSIDE_NORDIC);
        TRAVEL_COVERAGE = Collections.un
    }
    
    private TravelInsuranceCoverages() {
        
    }    
}
