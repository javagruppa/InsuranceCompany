

package insurancecompany.model.properties;

/**This class sets the building material of a property.
 *
 * @author Carl
 * 
 * @since 15.05.2015
 */
public enum PropertyMaterial {
    WOOD, BRICK, WOOD_AND_BRICK;
    
    @Override
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