package insurancecompany.misc;

/**
 * class InsuranceType. Enums showing the type of an insurance
 * @author André
 */
public enum InsuranceType {
    BOAT_INSURANCE, CAR_INSURANCE, HOME_INSURANCE, HOLIDAY_HOME_INSURANCE, TRAVEL_INSURANCE;
    
    /**
     * Returns a string representation of the type of an insurance
     * @return a string representation of the type of an insurance
     */
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