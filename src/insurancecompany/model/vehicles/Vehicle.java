package insurancecompany.model.vehicles;

import java.io.Serializable;

/**
 * Abstract Vehicle class. This is a super class to boat and car, and represents 
 * a vehicle that is either insured or a part of an accident.
 * 
 * @author André
 * @author Sindre
 */
public abstract class Vehicle implements Serializable {
    /** SerialVersionUID used to identify this class for object IO */
    private static final long serialVersionUID = 1L;
    /** Whether this vehicle has an alarm or not. */
    private boolean alarm;
    /** The brand of this vehicle. */
    private final String brand;
    /** The model of this vehicle. */
    private final String model;
    /** The personal number of the owner of this vehicle. */
    private String personalNumber;
    /** The registration number of this vehicle. */
    private String registrationNumber;
    /** The registration year of this vehicle. */
    private int registrationYear;
    
    /**
     * Constructs a new vehicle of the specified brand and model, and with the
     * specified engine effect, registration number and registration year. The
     * owner of this vehicle has the specified personal number.
     * 
     * @param alarm whether this vehicle has an alarm or not
     * @param brand the brand of this vehicle
     * @param model the model of this vehicle
     * @param personalNumber the personal number of the owner of this vehicle
     * @param registrationNumber the registration number of this vehicle
     * @param registrationYear the registration year of this vehicle
     */
    public Vehicle(boolean alarm, String brand, String model, 
            String personalNumber, String registrationNumber, 
            int registrationYear){
        this.alarm = alarm;
        this.brand = brand;
        this.model = model;
        this.personalNumber = personalNumber;
        this.registrationNumber = registrationNumber;
        this.registrationYear = registrationYear;
    }
    
     /**
     * Constructs a new vehicle of the specified brand and model and 
     * registration number. The owner of this vehicle has the specified 
     * personal number.
     * 
     * @param brand the brand of this vehicle
     * @param model the model of this vehicle
     * @param personalNumber the personal number of the owner of this vehicle
     * @param registrationNumber the registration number of this vehicle
     */
    public Vehicle(String brand, String model, 
            String personalNumber, String registrationNumber){
        this.brand = brand;
        this.model = model;
        this.personalNumber = personalNumber;
        this.registrationNumber = registrationNumber;
    }

    /** @return The personal number of the owner of this vehicle. */
    public String getPersonalNumber() {
        return personalNumber;
    }

    /** @return The registration number of this vehicle. */
    public String getRegistrationNumber() {
        return registrationNumber;
    }
    
    /** @return True if the vehicle has an alarm. */
    public boolean getAlarm() {
        return alarm;
    }

    /** @return The brand of this vehicle. */
    public String getBrand() {
        return brand;
    }

    /** @return The model of this vehicle. */
    public String getModel() {
        return model;
    }

    /** @return The registration year of this vehicle. */
    public int getRegistrationYear() {
        return registrationYear;
    }

    /**
     * Creates and returns a string representation of this vehicle
     * 
     * @return A string representation of this vehicle.
     */
    @Override
    public String toString() {
        // Creates a StringBuilder which will be returned at the end of the 
        // method.
        StringBuilder result = new StringBuilder();
        // Appends the fields with appropriate sentences.
        result.append("Registreringsnummer: ").append(registrationNumber);
        result.append("\nMerke: ").append(brand);
        result.append("\nModell: ").append(model);
        result.append("\nRegistreringsår: ").append(registrationYear);
        result.append("\nAlarm: ").append(alarm ? "Ja" : "Nei");
        result.append("\nPersonnummer til eier: ").append(personalNumber);
        // Returns the string.
        return result.toString();
    }
}