
package insurancecompany.misc.enums.coverages;

/**
 * This enum represents the different coverages of a home insurance.
 * 
 * @author André
 * @author Carl
 * 
 * @since 17.05.2015
 */
public enum HomeInsuranceCoverage {
    BASIC {
        @Override
        public Damage[] damages() {
            Damage[] damages = {
                Damage.FULL_VALUE_GUARANTEE,
                Damage.BUILDING_DAMAGE,
                Damage.TOTAL_REENTRY_OVER_SEVENTYFIVE,
                Damage.GARDEN_ARTICLES,
                Damage.REBUILDING_FOR_WHEELCHAIR_USER,
                Damage.LOSS_OF_RENT,
                Damage.LEGAL_LIABILITIES,
                Damage.LEGAL_AID,
                Damage.RODENT_DAMAGE
            };
            return damages;
        }
    }, PLUS {
        @Override
        public Damage[] damages() {
            Damage[] damages = {
                Damage.FULL_VALUE_GUARANTEE,
                Damage.BUILDING_DAMAGE,
                Damage.TOTAL_REENTRY_OVER_SEVENTYFIVE,
                Damage.GARDEN_ARTICLES,
                Damage.REBUILDING_FOR_WHEELCHAIR_USER,
                Damage.LOSS_OF_RENT,
                Damage.LEGAL_LIABILITIES,
                Damage.LEGAL_AID,
                Damage.RODENT_DAMAGE,
                Damage.FUNGUS_AND_ROT_DAMAGE,
                Damage.PEST_CONTROL,
                Damage.CONSEQUENTIAL_DAMAGE_AFTER_LEAK,
                Damage.CONSEQUENTIAL_DAMAGE_AFTER_CRAFTS_ERROR
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
            case PLUS: return 1500;
            case BASIC: return 0;
            default: throw new IllegalArgumentException();
        }
    }
    
    
        
}
