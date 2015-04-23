/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package insurancecompany.properties;

import insurancecompany.misc.Address;

/**
 *
 * @author Sindre
 */
public abstract class Property {
    /** The address of this property */
    private Address address;
    /** The floor area of this property. */
    private int area;
    /** The construction year of this property. */
    private int constructionYear;
    /** The building material of this property. */
    private String material;
    /** The standard of this property. */
    private String standard;
    /** The property type of this property. */
    private String type;
    
    /**
     * Constructor.
     * @param address the address of this property
     */
    public Property(Address address) {
        this.address = address;
    }
    
    /**
     * Sets an area to this property.
     * @param area the floor area of this property
     */
    public void setArea(int area) {
        this.area = area;
    }
    
    /**
     * Sets a construction year to this property.
     * @param constructionYear the construction year of this property
     */
    public void setConstructionYear(int constructionYear) {
        this.constructionYear = constructionYear;
    }
    
    /**
     * Sets a material to this property.
     * @param material the building material of this property
     */
    public void setMaterial(String material) {
        this.material = material;
    }
    
    /**
     * Sets a standard to this property.
     * @param standard the standard of this property
     */
    public void setStandard(String standard) {
        this.standard = standard;
    }
    
    /**
     * Sets a property type to this property.
     * @param type the property type of this property
     */
    public void setType(String type) {
        this.type = type;
    }
    
    /**
     * Returns a string representation of this property. The string
     * representation consists of each field with a short description separated
     * by a new line.
     * @return a string representation of this property
     */
    @Override
    public String toString() {
        // Creates a StringBuilder which will be returned at the end of the 
        // method.
        StringBuilder out = new StringBuilder();
        // Appends the fields with appropriate sentences.
        out.append("Adresse: ").append(address);
        out.append("\nAntall kvadratmeter: ").append(area);
        out.append("\nByggeår: ").append(constructionYear);
        out.append("\nByggemateriale: ").append(material);
        out.append("\nStandard: ").append(standard);
        out.append("\nBoligtype: ").append(type);
        // Returns the string.
        return out.toString();
    }
}