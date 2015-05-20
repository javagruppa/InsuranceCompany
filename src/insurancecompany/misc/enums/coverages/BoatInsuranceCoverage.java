/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package insurancecompany.misc.enums.coverages;

/**
 * This enum represents the different coverages of a boat insurance.
 * 
 * @author André
 * @author Carl
 */
public enum BoatInsuranceCoverage {
    CASCO {
        @Override
        public Damage[] damages() {
            Damage[] damages = {
                Damage.LIABILITY_DAMAGE,
                Damage.LEGAL_AID,
                Damage.ACCIDENTAL_INJURY,
                Damage.FIRE,
                Damage.THEFT,
                Damage.RESCUE,       
                Damage.CASCO_DAMAGE,
                Damage.TRANSPORT_STORAGE
            };
            return damages;
        }
    }, PARTLY_CASCO {
        @Override
        public Damage[] damages() {
            Damage[] damages = {
                Damage.LIABILITY_DAMAGE,
                Damage.LEGAL_AID,
                Damage.ACCIDENTAL_INJURY,
                Damage.FIRE,
                Damage.THEFT,
                Damage.RESCUE
            };
            return damages;
        }
    }, BOAT_PLUS {
        @Override
        public Damage[] damages() {
            Damage[] damages = {
                Damage.LIABILITY_DAMAGE,
                Damage.LEGAL_AID,
                Damage.ACCIDENTAL_INJURY,
                Damage.FIRE,
                Damage.THEFT,
                Damage.RESCUE,       
                Damage.CASCO_DAMAGE,
                Damage.TRANSPORT_STORAGE,
                Damage.HOLIDAY_WARRANTY,
                Damage.CONTAMINATED_FUEL,
                Damage.STORAGE_EQUIPMENT
            };
            return damages;
        }
    };

    public abstract Damage[] damages();
    
    @Override
    public String toString() {
        switch(this) {
            case CASCO: return "Kasko";
            case PARTLY_CASCO: return "Delkasko";
            case BOAT_PLUS: return "Båt pluss";
            default: throw new IllegalArgumentException();
        }
    }
    
    public int getPricing() {
        switch(this) {
            case CASCO: return 3000;
            case PARTLY_CASCO: return 1500;
            case BOAT_PLUS: return 4000;
            default: throw new IllegalArgumentException();
        }
    }
}
