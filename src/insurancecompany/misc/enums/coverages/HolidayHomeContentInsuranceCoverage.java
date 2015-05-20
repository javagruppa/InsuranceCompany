/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package insurancecompany.misc.enums.coverages;

/**
 * This enum represents the different coverages of a holiday home content
 * insurance.
 * 
 * @author André
 * @author Carl
 */
public enum HolidayHomeContentInsuranceCoverage {
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
                Damage.LEGAL_LIABILITIES,
                Damage.LEGAL_AID,
                Damage.ACCIDENTAL_INJURY
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
            case PLUS: return 1200;
            case BASIC: return 0;
            default: throw new IllegalArgumentException();
        }
    }
    
    
        
}
