/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package insurancecompany.damages;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author André
 */
public final class BoatInsuranceDamages {
    
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
    public static final List<String> CASCO_COVERAGE; // ALL RISK / NOR: KASKO
    public static final List<String> PARTLY_CASCO_COVERAGE; // NOR: DELKASKO
            
    static {
        List<String> cascoCoverage = new ArrayList();
        cascoCoverage.add(LIABILITY_DAMAGE);
        cascoCoverage.add(LEGAL_AID);
        cascoCoverage.add(ACCIDENTAL_INJURY);
        cascoCoverage.add(FIRE);
        cascoCoverage.add(THEFT);
        cascoCoverage.add(RESCUE);       
        cascoCoverage.add(CASCO_DAMAGE);
        cascoCoverage.add(TRANSPORT_STORAGE);
        CASCO_COVERAGE = Collections.unmodifiableList(cascoCoverage);
        
        List<String> partlyCascoCoverage = new ArrayList();
        partlyCascoCoverage.add(LIABILITY_DAMAGE);
        partlyCascoCoverage.add(LEGAL_AID);
        partlyCascoCoverage.add(ACCIDENTAL_INJURY);
        partlyCascoCoverage.add(FIRE);
        partlyCascoCoverage.add(THEFT);
        partlyCascoCoverage.add(RESCUE);
        PARTLY_CASCO_COVERAGE = Collections.unmodifiableList(cascoCoverage);
  
    }
    
    private BoatInsuranceDamages() {
        
    }
}
