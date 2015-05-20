package insurancecompany.misc.enums;

/**
 * This enum represents the types of claims that can be registered.
 * 
 * @author André
 * @author Carl
 */
public enum ClaimType {
    BOAT_CLAIM, CAR_CLAIM, HOME_CLAIM, HOME_CONTENT_CLAIM, 
    HOLIDAY_HOME_CONTENT_CLAIM, HOLIDAY_HOME_CLAIM, TRAVEL_CLAIM;
    
    /**
     * Returns a string representation of the type of claim
     * @return a string representation of the type of claim
     */
    @Override
    public String toString() {
        switch(this) {
            case BOAT_CLAIM: return "Båtskademelding";
            case CAR_CLAIM: return "Bilskademelding";
            case HOME_CLAIM: return "Husskademelding";
            case HOME_CONTENT_CLAIM: return "Innboskademelding";
            case HOLIDAY_HOME_CLAIM: return "Fritidsboligskademelding";
            case HOLIDAY_HOME_CONTENT_CLAIM: return "Fritidsbolig innboskademelding";
            case TRAVEL_CLAIM: return "Reiseskademelding";
            default: throw new IllegalArgumentException();
        }
    }
}
