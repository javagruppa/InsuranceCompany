

package insurancecompany.misc.enums.hometypes;

/**
 * This enum represents the different types a holiday home can be of.
 * 
 * @author Carl
 * 
 * @since 19.05.2015
 */
public enum HolidayHomeType {
    APARTMENT, VILLA, LINKED_HOUSE, DETACHED_HOUSE, SEMI_DETACHED_HOUSE;
    
    @Override
    public String toString() {
        switch(this) {
            case APARTMENT: return "Leilighet";
            case VILLA: return "Villa";
            case LINKED_HOUSE: return "Rekkehus";
            case DETACHED_HOUSE: return "Enebolig";
            case SEMI_DETACHED_HOUSE: return "Tomannsbolig";
            default: throw new IllegalArgumentException();  
        }
    }
    
    /**
     * 
     * @return the pricing of the holiday home type
     */
    public int getPricing() {
        switch(this) {
            case APARTMENT: return 4000;
            case VILLA: return 8000;
            case LINKED_HOUSE: return 500;
            case DETACHED_HOUSE: return 6500;
            case SEMI_DETACHED_HOUSE: return 5000;
            default: throw new IllegalArgumentException();
        }
    }
}
