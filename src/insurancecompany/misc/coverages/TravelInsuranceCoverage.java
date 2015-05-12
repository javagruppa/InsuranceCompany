/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package insurancecompany.misc.coverages;

/**
 *
 * @author André
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
    
    public abstract Damage[] damages();
    
    @Override
    public String toString() {
        switch(this) {
            case STANDARD: return "Standard";
            default: throw new IllegalArgumentException();
        }
    }
}
