/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package insurancecompany.misc;

/**
 *
 * @author André
 */
public enum ClaimType {
    BOAT_CLAIM, CAR_CLAIM, HOME_CLAIM, HOLIDAY_HOME_CLAIM, TRAVEL_CLAIM;
    
    @Override
    public String toString() {
        switch(this) {
            case BOAT_CLAIM: return "Båtskademelding";
            case CAR_CLAIM: return "Bilskademelding";
            case HOME_CLAIM: return "Hus- og innboskademelding";
            case HOLIDAY_HOME_CLAIM: return "Fritidsboligskademelding";
            case TRAVEL_CLAIM: return "Reiseskademelding";
            default: throw new IllegalArgumentException();
        }
    }
}
