package insurancecompany.misc;

/**
 * Class ClaimType. Enum setting the type of a claim - what type of
 * insurance covers the claim
 * 
 * @author André
 */
public enum ClaimType {
    BOAT_CLAIM, CAR_CLAIM, HOME_CLAIM, HOLIDAY_HOME_CLAIM, TRAVEL_CLAIM;
    
    /**
     * Returns a string representation of the type of claim
     * @return a string representation of the type of claim
     */
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
