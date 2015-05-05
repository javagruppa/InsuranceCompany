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
public final class CarInsuranceCoverages {
    
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
    public static final ArrayList<String> CASCO_COVERAGE; // ALL RISK / NOR: KASKO
    public static final ArrayList<String> PARTLY_CASCO_COVERAGE; // NOR: DELKASKO
    public static final ArrayList<String> LIABILITY_COVERAGE; // NOR: ANSVAR
            
    static {
        CASCO_COVERAGE = new ArrayList();
        CASCO_COVERAGE.add(LIABILITY_DAMAGE);
        CASCO_COVERAGE.add(LEGAL_AID);
        CASCO_COVERAGE.add(DRIVER_AND_PASSANGER_ACCIDENT);
        CASCO_COVERAGE.add(FIRE);
        CASCO_COVERAGE.add(THEFT);
        CASCO_COVERAGE.add(GLASS);
        CASCO_COVERAGE.add(ROADSIDE_ASSISTANCE_NORDIC);
        CASCO_COVERAGE.add(ROADSIDE_ASSISTANCE_EUROPE);
        CASCO_COVERAGE.add(CAR_ASSISTANCE_NORWAY);
        CASCO_COVERAGE.add(LUGGAGE);
        CASCO_COVERAGE.add(MISFUELING);
        CASCO_COVERAGE.add(WAGON_DAMAGE);
        CASCO_COVERAGE.add(RENTAL_CASCO);
        
        PARTLY_CASCO_COVERAGE = new ArrayList();
        PARTLY_CASCO_COVERAGE.add(LIABILITY_DAMAGE);
        PARTLY_CASCO_COVERAGE.add(LEGAL_AID);
        PARTLY_CASCO_COVERAGE.add(DRIVER_AND_PASSANGER_ACCIDENT);
        PARTLY_CASCO_COVERAGE.add(FIRE);
        PARTLY_CASCO_COVERAGE.add(THEFT);
        PARTLY_CASCO_COVERAGE.add(GLASS);
        PARTLY_CASCO_COVERAGE.add(ROADSIDE_ASSISTANCE_NORDIC);
        
        LIABILITY_COVERAGE = new ArrayList();
        LIABILITY_COVERAGE.add(LIABILITY_DAMAGE);
        LIABILITY_COVERAGE.add(LEGAL_AID);
        LIABILITY_COVERAGE.add(DRIVER_AND_PASSANGER_ACCIDENT);        
    }
    
    private CarInsuranceCoverages() {
        
    }
}