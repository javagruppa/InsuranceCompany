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
public enum InsuranceType {
    BOAT_INSURANCE, CAR_INSURANCE, HOME_INSURANCE, HOLIDAY_HOME_INSURANCE, TRAVEL_INSURANCE;
    
    @Override
    public String toString() {
        switch(this) {
            case BOAT_INSURANCE: return "Båtforsikring";
            case CAR_INSURANCE: return "Bilforsikring";
            case HOME_INSURANCE: return "Hus- og innboforsikring";
            case HOLIDAY_HOME_INSURANCE: return "Fritidsboligforsikring";
            case TRAVEL_INSURANCE: return "Reiseforsikring";
            default: throw new IllegalArgumentException();
        }
    }
}