
package insurancecompany.misc.enums.coverages;

/**
 * This enum represents the different coverages of a travel insurance.
 * 
 * @author André
 * @author Carl
 * 
 * @since 17.05.2015
 */
public enum TravelInsuranceCoverage {
    STANDARD {
        @Override
        public Damage[] damages() {
            Damage[] damages = {
                Damage.LUGGAGE,
                Damage.DELAY_EVACUATION,
                Damage.CANCELLATION,
                Damage.SICKNESS_INJURY,
                Damage.FULL_TIME_ACCIDENT_INSURANCE,
                Damage.LIABILITY_AND_LEGAL_AID_OUTSIDE_NORDIC
            };
            return damages;
        }
    };
    
    /** @return damages covered */
    public abstract Damage[] damages();
    
    @Override
    public String toString() {
        switch(this) {
            case STANDARD: return "Standard";
            default: throw new IllegalArgumentException();
        }
    }
    
    /** @return the pricing of the coverage */
    public int getPricing() {
        switch(this) {
            case STANDARD: return 2000;
            default: throw new IllegalArgumentException();
        }
    }
}
