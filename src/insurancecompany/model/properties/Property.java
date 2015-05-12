/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package insurancecompany.model.properties;

import java.io.Serializable;

/**
 *
 * @author Sindre
 */
public abstract class Property implements Serializable {
    private static final long serialVersionUID = 1L;
    
    /** The address of this property. */
    private Address address;
    /** The floor area of this property. */
    private int area;
    /** The construction year of this property. */
    private int year;
    /** The building material of this property. */
    private PropertyMaterial material;
    /** Whether this property is to be rented out. */
    private boolean rental;
    
    /**
     * Constructs a new property with the specified address. Area and year are 
     * set to 0. Material is set to null, rental is set to false.
     * 
     * @param address the address of this property
     */
    public Property(Address address, PropertyMaterial material, int area,
            int year, boolean rental) {
        this.address = address;
        this.area = area;
        this.year = year;
        this.material = material;
        this.rental = rental;
        
    }
    
    public double getMaterialMultiplicator(){
        return material.getMultiplicator();
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
     * Sets the rental status of this property
     * @param rental whether this property is a rental
     */
    public void setRental(boolean rental){
        this.rental = rental;
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
        result.append("\nUtleiebolig: ").append(rental ? "Ja" : "Nei");
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
     * @return the rental status
     */
    public boolean getRental() {
        return rental;
    }
    
    /**
     * 
     * @return the building material
     */
    public String getMaterial() {
        return material.toString();
    }
}