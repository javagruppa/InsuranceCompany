/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package insurancecompany.misc.hometypes;

/**
 *
 * @author Carl
 */
public enum HomeType {
    APARTMENT, VILLA, LINKED_HOUSE, DETACHED_HOUSE, SEMI_DETACHED_HOUSE;
    
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
    
    public int getPricing() {
        switch(this) {
            case APARTMENT: return 4000;
            case VILLA: return 8000;
            case LINKED_HOUSE: return 5000;
            case DETACHED_HOUSE: return 6500;
            case SEMI_DETACHED_HOUSE: return 5000;
            default: throw new IllegalArgumentException();
        }
    }
}
