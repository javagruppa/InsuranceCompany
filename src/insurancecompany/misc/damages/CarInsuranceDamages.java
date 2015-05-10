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
public final class CarInsuranceDamages {
    
    // CAR INSURANCE DAMAGES
    public static final String LIABILITY_DAMAGE = "Ansvar";
    public static final String LEGAL_AID = "Rettshjelp";
    public static final String DRIVER_AND_PASSANGER_ACCIDENT = "Fører- og passasjerulykke";
    public static final String FIRE = "Brann";
    public static final String THEFT = "Tyveri";
    public static final String GLASS = "Glass";
    public static final String ROADSIDE_ASSISTANCE_NORDIC = "Veihjelp i Norden";
    public static final String ROADSIDE_ASSISTANCE_EUROPE = "Veihjelp i Europa";
    public static final String CAR_ASSISTANCE_NORWAY = "Bilhjelp hjemme";
    public static final String LUGGAGE = "Bagasje";
    public static final String MISFUELING = "Feilfylling";
    public static final String WAGON_DAMAGE = "Vognskade";
    public static final String RENTAL_CASCO = "Leiebil kasko";
    
    
    // CAR INSURANCE COVERAGES
    public static final List<String> CASCO_COVERAGE; // ALL RISK / NOR: KASKO
    public static final List<String> PARTLY_CASCO_COVERAGE; // NOR: DELKASKO
    public static final List<String> LIABILITY_COVERAGE; // NOR: ANSVAR
            
    static {
        List<String> cascoCoverage = new ArrayList();
        cascoCoverage.add(LIABILITY_DAMAGE);
        cascoCoverage.add(LEGAL_AID);
        cascoCoverage.add(DRIVER_AND_PASSANGER_ACCIDENT);
        cascoCoverage.add(FIRE);
        cascoCoverage.add(THEFT);
        cascoCoverage.add(GLASS);
        cascoCoverage.add(ROADSIDE_ASSISTANCE_NORDIC);
        cascoCoverage.add(ROADSIDE_ASSISTANCE_EUROPE);
        cascoCoverage.add(CAR_ASSISTANCE_NORWAY);
        cascoCoverage.add(LUGGAGE);
        cascoCoverage.add(MISFUELING);
        cascoCoverage.add(WAGON_DAMAGE);
        cascoCoverage.add(RENTAL_CASCO);
        CASCO_COVERAGE = Collections.unmodifiableList(cascoCoverage);
        
        List<String> partlyCascoCoverage = new ArrayList();
        partlyCascoCoverage.add(LIABILITY_DAMAGE);
        partlyCascoCoverage.add(LEGAL_AID);
        partlyCascoCoverage.add(DRIVER_AND_PASSANGER_ACCIDENT);
        partlyCascoCoverage.add(FIRE);
        partlyCascoCoverage.add(THEFT);
        partlyCascoCoverage.add(GLASS);
        partlyCascoCoverage.add(ROADSIDE_ASSISTANCE_NORDIC);
        PARTLY_CASCO_COVERAGE = Collections.unmodifiableList(partlyCascoCoverage);
        
        List<String> liabilityCoverage = new ArrayList();
        liabilityCoverage.add(LIABILITY_DAMAGE);
        liabilityCoverage.add(LEGAL_AID);
        liabilityCoverage.add(DRIVER_AND_PASSANGER_ACCIDENT);
        LIABILITY_COVERAGE = Collections.unmodifiableList(liabilityCoverage);
    }
    
    private CarInsuranceDamages() {
        
    }
}