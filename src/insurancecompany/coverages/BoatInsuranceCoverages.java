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
public final class BoatInsuranceCoverages {
    
    // BOAT INSURANCE DAMAGES
    public static final String LIABILITY_DAMAGE = "Ansvar";
    public static final String LEGAL_AID = "Rettshjelp";
    public static final String ACCIDENTAL_INJURY = "Ulykkesskade";
    public static final String FIRE = "Brann";
    public static final String THEFT = "Tyveri";
    public static final String RESCUE = "Assistanse/redning";
    
    public static final String CASCO_DAMAGE = "Kaskoskade";
    public static final String TRANSPORT_STORAGE = "Transport/opplag";
    
    // NOT IN USE, BOAT PLUS COVERAGE
    public static final String HOLIDAY_WARRANTY = "Feriegaranti";
    public static final String CONTAMINATED_FUEL = "Forurenset drivstoff";
    public static final String STORAGE_EQUIPMENT = "Utstyr til opplag";
       
    // BOAT INSURANCE COVERAGES
    public static final ArrayList<String> CASCO_COVERAGE; // ALL RISK / NOR: KASKO
    public static final ArrayList<String> PARTLY_CASCO_COVERAGE; // NOR: DELKASKO
            
    static {
        CASCO_COVERAGE = new ArrayList();
        CASCO_COVERAGE.add(LIABILITY_DAMAGE);
        CASCO_COVERAGE.add(LEGAL_AID);
        CASCO_COVERAGE.add(ACCIDENTAL_INJURY);
        CASCO_COVERAGE.add(FIRE);
        CASCO_COVERAGE.add(THEFT);
        CASCO_COVERAGE.add(RESCUE);       
        CASCO_COVERAGE.add(CASCO_DAMAGE);
        CASCO_COVERAGE.add(TRANSPORT_STORAGE);
        
        PARTLY_CASCO_COVERAGE = new ArrayList();
        PARTLY_CASCO_COVERAGE.add(LIABILITY_DAMAGE);
        PARTLY_CASCO_COVERAGE.add(LEGAL_AID);
        PARTLY_CASCO_COVERAGE.add(ACCIDENTAL_INJURY);
        PARTLY_CASCO_COVERAGE.add(FIRE);
        PARTLY_CASCO_COVERAGE.add(THEFT);
        PARTLY_CASCO_COVERAGE.add(RESCUE);
  
    }
    
    private BoatInsuranceCoverages() {
        
    }
}
