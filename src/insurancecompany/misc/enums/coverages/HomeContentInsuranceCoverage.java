
package insurancecompany.misc.enums.coverages;


/**
 * This enum represents the different coverages of a home content insurance.
 * 
 * @author André
 * @author Carl
 * 
 * @since 17.05.2015
 */
public enum HomeContentInsuranceCoverage {
    BASIC {
        @Override
        public Damage[] damages() {
            Damage[] damages = {
                Damage.FIRE_DAMAGE,
                Damage.WATER_DAMAGE,
                Damage.THEFT,
                Damage.NATURAL_DISASTER,
                Damage.BAG_SNATCHING,
                Damage.ROBBERY_AND_ASSAULT,
                Damage.SUBSISTENCE_EXPENSE,
                Damage.LEGAL_LIABILITIES,
                Damage.LEGAL_AID
            };
            return damages;
        }
    }, PLUS {
        @Override
        public Damage[] damages() {
            Damage[] damages = {
                Damage.FIRE_DAMAGE,
                Damage.WATER_DAMAGE,
                Damage.THEFT,
                Damage.NATURAL_DISASTER,
                Damage.BAG_SNATCHING,
                Damage.ROBBERY_AND_ASSAULT,
                Damage.SUBSISTENCE_EXPENSE,
                Damage.LEGAL_LIABILITIES,
                Damage.LEGAL_AID,
                Damage.ACCIDENTAL_INJURY,
                Damage.MOVING_INSURANCE,
                Damage.ID_ANTITHEFT
            };
            return damages;
        }
    };
    
    /** @return damages covered */
    public abstract Damage[] damages();
    
    @Override
    public String toString() {
        switch(this) {
            case PLUS: return "Pluss";
            case BASIC: return "Basis";
            default: throw new IllegalArgumentException();
        }
    }
    
    /** @return the pricing of the coverage */
    public int getPricing() {
        switch(this) {
            case PLUS: return 1000;
            case BASIC: return 0;
            default: throw new IllegalArgumentException();
        }
    }
    
    
        
}
