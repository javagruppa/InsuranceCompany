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
public final class TravelInsuranceCoverages {
    
    // TRAVEL INSURANCE DAMAGES
    public static final String LUGGAGE = "Reisegods";
    public static final String DELAY_EVACUATION = "Forsinkelse/evakuering";
    public static final String CANCELLATION = "Avbestilling";
    public static final String SICKNESS_INJURY = "Sykdom/skade";
    public static final String FULL_TIME_ACCIDENT_INSURANCE = "Heltids ulykkesforsikring";
    public static final String LIABILITY_AND_LEGAL_AID_OUTSIDE_NORDIC = "Ansvar og rettshjelp utenfor Norden";
    
    // TRAVEL INSURANCE COVERAGE
    public static final ArrayList<String> TRAVEL_COVERAGE;        
    static {
        TRAVEL_COVERAGE = new ArrayList();
        TRAVEL_COVERAGE.add(LUGGAGE);
        TRAVEL_COVERAGE.add(DELAY_EVACUATION);
        TRAVEL_COVERAGE.add(CANCELLATION);
        TRAVEL_COVERAGE.add(SICKNESS_INJURY);
        TRAVEL_COVERAGE.add(FULL_TIME_ACCIDENT_INSURANCE);
        TRAVEL_COVERAGE.add(LIABILITY_AND_LEGAL_AID_OUTSIDE_NORDIC);       
    }
    
    private TravelInsuranceCoverages() {
        
    }    
}
