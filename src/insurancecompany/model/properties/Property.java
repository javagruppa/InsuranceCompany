/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package insurancecompany.model.properties;

/**
 *
 * @author Sindre
 */
public abstract class Property {
    /** The address of this property. */
    private Address address;
    /** The floor area of this property. */
    private int area;
    /** The construction year of this property. */
    private int year;
    /** The building material of this property. */
    private String material;
    /** The property type of this property. */
    private String type;
    
    /**
     * Constructs a new property with the specified address. Area and year are 
     * set to 0. Material and type are set to null.
     * 
     * @param address the address of this property
     */
    public Property(Address address) {
        this.address = address;
        this.area = 0;
        this.year = 0;
        this.material = null;
        this.type = null;
    }
    
    /**
     * Sets an area to this property.
     * 
     * @param area the floor area of this property
     */
    public void setArea(int area) {
        this.area = area;
    }
    
    /**
     * Sets a construction year to this property.
     * 
     * @param year the construction year of this property
     */
    public void setYear(int year) {
        this.year = year;
    }
    
    /**
     * Sets a building material to this property.
     * 
     * @param material the building material of this property
     */
    public void setMaterial(String material) {
        this.material = material;
    }
    
    /**
     * Sets a property type to this property.
     * 
     * @param type the property type of this property
     */
    public void setType(String type) {
        this.type = type;
    }
    
    
    /**
     * Returns a string representation of this property. The string
     * representation consists of each field with a short description separated
     * by a new line.
     * 
     * @return a string representation of this property
     */
    @Override
    public String toString() {
        // Creates a StringBuilder which will be returned at the end of the 
        // method.
        StringBuilder result = new StringBuilder();
        // Appends the fields with appropriate sentences.
        result.append("Adresse: ").append(getAddress().toString());
        result.append("\nAntall kvadratmeter: ").append(getArea());
        result.append("\nByggeår: ").append(getYear());
        result.append("\nByggemateriale: ").append(getMaterial());
        result.append("\nBoligtype: ").append(getType());
        // Returns the string.
        return result.toString();
    }

    /**
     * @return the address
     */
    public Address getAddress() {
        return address;
    }

    /**
     * @return the area
     */
    public int getArea() {
        return area;
    }

    /**
     * @return the year
     */
    public int getYear() {
        return year;
    }

    /**
     * @return the material
     */
    public String getMaterial() {
        return material;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }
}