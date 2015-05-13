/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package insurancecompany.model.properties;

/**
 *
 * @author Carl
 */
public enum PropertyMaterial {
    WOOD, BRICK, WOOD_AND_BRICK;
    
    public String toString() {
        switch(this) {
            case WOOD: return "Tre";
            case BRICK: return "Mur";
            case WOOD_AND_BRICK: return "Tre og mur";
            default: throw new IllegalArgumentException();  
        }
    }
    
    public double getMultiplicator() {
        switch(this) {
            case WOOD: return 1.2;
            case BRICK: return 0.8;
            case WOOD_AND_BRICK: return 1;
            default: throw new IllegalArgumentException();  
        }
    }
    
}