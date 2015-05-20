/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package insurancecompany.misc.enums.coverages;

/**
 * This enum represents the different coverages of a holiday home insurance.
 * 
 * @author André
 * @author Carl
 */
public enum HolidayHomeInsuranceCoverage {
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
                Damage.CONSEQUENTIAL_DAMAGE_AFTER_CRAFTS_ERROR,
                Damage.HOLIDAY_WARRANTY
            };
            return damages;
        }
    };
    
    public abstract Damage[] damages();
    
    @Override
    public String toString() {
        switch(this) {
            case PLUS: return "Pluss";
            case BASIC: return "Basis";
            default: throw new IllegalArgumentException();
        }
    }
    
    public int getPricing() {
        switch(this) {
            case PLUS: return 1000;
            case BASIC: return 0;
            default: throw new IllegalArgumentException();
        }
    }
    
    
}
