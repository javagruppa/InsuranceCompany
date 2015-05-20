package insurancecompany.misc.enums;

/**
 * This enum represents the types of insurances used by the company.
 * 
 * @author André
 */
public enum InsuranceType {
    BOAT_INSURANCE, CAR_INSURANCE, HOME_INSURANCE, HOME_CONTENT_INSURANCE, 
    HOLIDAY_HOME_INSURANCE, HOLIDAY_HOME_CONTENT_INSURANCE, TRAVEL_INSURANCE;
    
    /**
     * Returns a string representation of the type of an insurance
     * @return a string representation of the type of an insurance
     */
    @Override
    public String toString() {
        switch(this) {
            case BOAT_INSURANCE: return "Båtforsikring";
            case CAR_INSURANCE: return "Bilforsikring";
            case HOME_INSURANCE: return "Husforsikring";
            case HOME_CONTENT_INSURANCE: return "Innboforsikring";
            case HOLIDAY_HOME_INSURANCE: return "Fritidsboligforsikring";
            case HOLIDAY_HOME_CONTENT_INSURANCE: return "Fritidsbolig innboforsikring";
            case TRAVEL_INSURANCE: return "Reiseforsikring";
            default: throw new IllegalArgumentException();
        }
    }
}