package insurancecompany.model.vehicles;

import java.io.Serializable;

/**
 * Abstract Vehicle class. This is a super class to boat and car, and represents a
 * vehicle that is either insured or a part of an accident.
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
    /** The effect of the engine of this vehicle. */
    private int engineEffect;
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
     * @param engineEffect the effect of the engine of this vehicle
     * @param model the model of this vehicle
     * @param personalNumber the personal number of the owner of this vehicle
     * @param registrationNumber the registration number of this vehicle
     * @param registrationYear the registration year of this vehicle
     */
    public Vehicle(boolean alarm, String brand, int engineEffect, String model, 
            String personalNumber, String registrationNumber, 
            int registrationYear){
        this.alarm = alarm;
        this.brand = brand;
        this.engineEffect = engineEffect;
        this.model = model;
        this.personalNumber = personalNumber;
        this.registrationNumber = registrationNumber;
        this.registrationYear = registrationYear;
    }

    /**
     * Returns the engine effect of this vehicle
     * @return the engineEffect
     */
    public int getEngineEffect() {
        return engineEffect;
    }

    /**
     * returns the personal number of the vehicles owner
     * @return the personalNumber
     */
    public String getPersonalNumber() {
        return personalNumber;
    }

    /**
     * returns the registration number of this vehicle
     * @return the registrationNumber
     */
    public String getRegistrationNumber() {
        return registrationNumber;
    }
    
    /**
     * returns whether or not this vehicle has an alarm
     * @return the alarm status
     */
    public boolean getAlarm() {
        return alarm;
    }

    /**
     * Sets new alarm status
     * @param alarm the alarm to set
     */
    public void setAlarm(boolean alarm) {
        this.alarm = alarm;
    }

    /**
     * Sets new engine effect
     * @param engineEffect the engineEffect to set
     */
    public void setEngineEffect(int engineEffect) {
        this.engineEffect = engineEffect;
    }

    /**
     * sets personal number of new owner
     * @param personalNumber the personalNumber to set
     */
    public void setPersonalNumber(String personalNumber) {
        this.personalNumber = personalNumber;
    }

    /**
     * Sets new registration number
     * @param registrationNumber the registrationNumber to set
     */
    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    /**
     * Sets new registration year
     * @param registrationYear the registrationYear to set
     */
    public void setRegistrationYear(int registrationYear) {
        this.registrationYear = registrationYear;
    }
    
    /**
     * Creates and returns a string representation of this vehicle
     * @return a string representation of this vehicle
     */
    public String toString() {
        // Creates a StringBuilder which will be returned at the end of the 
        // method.
        StringBuilder result = new StringBuilder();
        // Appends the fields with appropriate sentences.
        result.append("Registreringsnummer: ").append(registrationNumber);
        result.append("\nBilmerke: ").append(brand);
        result.append("\nModell: ").append(model);
        result.append("\nRegistreringsår: ").append(registrationYear);
        result.append("\nMotorstørrelse: ").append(engineEffect).append(" HK");
        result.append("\nAlarm").append(alarm ? "Ja" : "Nei");
        result.append("\nPersonnummer eier: ").append(personalNumber);
        // Returns the string.
        return result.toString();
    }

    /**
     * @return the alarm
     */
    public boolean isAlarm() {
        return alarm;
    }

    /**
     * @return the brand
     */
    public String getBrand() {
        return brand;
    }

    /**
     * @return the model
     */
    public String getModel() {
        return model;
    }

    /**
     * @return the registrationYear
     */
    public int getRegistrationYear() {
        return registrationYear;
    }
}