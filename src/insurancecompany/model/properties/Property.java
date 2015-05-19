package insurancecompany.model.properties;

import java.io.Serializable;

/**
 * This class represents a property object. It contains information about
 * the property and methods to manipulate and get the information.
 * 
 * @author Sindre
 */
public class Property implements Serializable {
    private static final long serialVersionUID = 1L;
    
    /** The address of this property. */
    private Address address;
    /** The floor area of this property. */
    private int area;
    /** The building material of this property. */
    private PropertyMaterial material;
    /** The construction year of this property. */
    private int year;
        
    /**
     * Constructs a new property with the specified parameters.
     * 
     * @param address The address of this property.
     * @param area The floor area of this property.
     * @param material The building material of this property.
     * @param year The building material of this property.
     */
    public Property(Address address, PropertyMaterial material, int area,
            int year) {
        this.address = address;
        this.area = area;
        this.material = material;
        this.year = year;
                
    }
    
    /** @return The material mulitplicator used to calculate premium. */
    public double getMaterialMultiplicator(){
        return material.getMultiplicator();
    }

    /** @return The address of this property. */
    public Address getAddress() {
        return address;
    }

    /** @return The building material of this property. */
    public int getYear() {
        return year;
    }
    
    /** @param area The floor area of this property. */
    public void setArea(int area) {
        this.area = area;
    }
    
    /** @param year The construction year of this property. */
    public void setYear(int year) {
        this.year = year;
    }
    
    /**
     * Returns a string representation of this property. The string
     * representation consists of each field with a short description separated
     * by a new line.
     * 
     * @return A string representation of this property.
     */
    @Override
    public String toString() {
        // Creates a StringBuilder which will be returned at the end of the 
        // method.
        StringBuilder result = new StringBuilder();
        // Appends the fields with appropriate sentences.
        result.append("Adresse: ").append(getAddress().toString());
        result.append("\nAntall kvadratmeter: ").append(area);
        result.append("\nByggeår: ").append(year);
        result.append("\nByggemateriale: ").append(material.toString());
        // Returns the string.
        return result.toString();
    }
}