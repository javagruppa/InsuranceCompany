/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package insurancecompany.misc.coverages;

/**
 * This enum represents the different coverages of a car insurance.
 * 
 * @author André
 * @author Carl
 */
public enum CarInsuranceCoverage {
    CASCO {
        @Override
        public Damage[] damages() {
            Damage[] damages = {
                Damage.LIABILITY_DAMAGE,
                Damage.LEGAL_AID,
                Damage.DRIVER_AND_PASSANGER_ACCIDENT,
                Damage.FIRE,
                Damage.THEFT,
                Damage.GLASS,
                Damage.ROADSIDE_ASSISTANCE_NORDIC,
                Damage.ROADSIDE_ASSISTANCE_EUROPE,
                Damage.CAR_ASSISTANCE_NORWAY,
                Damage.LUGGAGE,
                Damage.MISFUELING,
                Damage.WAGON_DAMAGE,
                Damage.RENTAL_CASCO
            };
            return damages;
        }
    }, PARTLY_CASCO {
        @Override
        public Damage[] damages() {
            Damage[] damages = {
                Damage.LIABILITY_DAMAGE,
                Damage.LEGAL_AID,
                Damage.DRIVER_AND_PASSANGER_ACCIDENT,
                Damage.FIRE,
                Damage.THEFT,
                Damage.GLASS,
                Damage.ROADSIDE_ASSISTANCE_NORDIC
            };
            return damages;
        }
    }, LIABILITY {
        @Override
        public Damage[] damages() {
            Damage[] damages = {
                Damage.LIABILITY_DAMAGE,
                Damage.LEGAL_AID,
                Damage.DRIVER_AND_PASSANGER_ACCIDENT
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
            case LIABILITY: return "Ansvar";
            default: throw new IllegalArgumentException();
        }
    }
    
    public int getPricing() {
        switch(this) {
            case CASCO: return 7000;
            case PARTLY_CASCO: return 5000;
            case LIABILITY: return 3000;
            default: throw new IllegalArgumentException();
        }
    }
    

}
