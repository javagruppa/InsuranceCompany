/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author André
 */
public enum InsuranceTypes {
    CAR ,
    BOAT,
    HOME,
    HOLIDAY_HOME,
    TRAVEL;
    
    public String getButtonText() {
        switch(this) {
            case CAR: return "Kasko";
            case BOAT: return "Delkasko";
            case HOME: return "Husforsikring";
            case HOLIDAY_HOME: return "Fritidsbolig forsikring";
            case TRAVEL: return "Reiseforsikring";
            default: throw new IllegalArgumentException();
        }
    }
    }
    
}
