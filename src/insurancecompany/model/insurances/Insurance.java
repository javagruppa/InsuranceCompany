package insurancecompany.model.insurances;

import insurancecompany.misc.DateUtility;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.Calendar;

/**
 *
 * @author Sindre
 */
public abstract class Insurance implements Serializable {
    
    /** SerialVersionUID used to identify this class for object IO. */
    private static final long serialVersionUID = 1L;
    /** The insurance id of the insurance created next. */
    private static int nextInsuranceId = 1000000;
    /** The path nextInsuranceId is saved to. */
    private static String insuranceIdFileName = "src/insurancecompany/resources/nextidnumbers/insuranceId.dta"; 
    /** Whether this insurance is active or not. */
    private boolean active;
    /** The id of the customer who owns this insurance. */
    private int customerId;
    /** The date this insurance was created. */
    private Calendar date;
    /** The next pay date for this insurance. */
    private Calendar nextPayDate;
    /** The excess of this insurance. */
    private int excess;
    /** Unique insurance id representing this insurance. */
    private final int insuranceId;
    /** The yearly insurance premium of this insurance. */
    private int premium;
    /** The monthly insurance premium of this insurance. */
    private int monthlyPremium;
    
    /**
     * Constructs a new insurance with the specified customerId and excess. 
     * Active is set to true. Date is set to the current date. InsuranceId is 
     * automatically set to nextInsuranceId.
     * 
     * @param customerId The id of the customer who owns this insurance.
     * @param excess The excess of this insurance.
     */
    public Insurance(int customerId, int excess) {
        this.active = true;
        this.customerId = customerId;
        this.date = Calendar.getInstance();
        this.insuranceId = nextInsuranceId++;
        this.excess = excess;
        nextPayDate = date;
        nextPayDate.add(Calendar.MONTH, 1);
    }
    
    /** Calculate the premium. */
    public abstract void calculatePremium();
    
    /** Calculate the monthly premium. */
    public void calculateMonthlyPremium() {
        monthlyPremium = premium / 12;
    }
    
    // GET METHODS
    
    /** @return The coverage of this insurance. */
    public abstract Object getCoverage();
    
    /** @return The type of insurance in form of a String. */
    public abstract String getName();
    
    /** @return Whether this insurance is active or not. */
    public boolean getActive() {
        return active;
    }
    
    /** @return The id of the customer who owns this insurance. */
    public int getCustomerId() {
        return customerId;
    }
    
    /** @return The date the insurance is signed. */
    public Calendar getDate() {
        return date;
    }
    
    /** @return The excess of this insurance. */
    public int getExcess() {
        return excess;
    }
    
    /** @return The unique insurance id representing this insurance. */
    public int getInsuranceId() {
        return insuranceId;
    }
    
    /** @return The yearly insurance premium of this insurance. */
    public int getPremium() {
        return premium;
    }
    
    // SET METHODS
    
    /**
     * Sets an activity status to this insurance.
     * 
     * @param active The activity status to be set to this insurance.
     */
    public void setActive(boolean active) {
        this.active = active;
    }
    
    /**
     * Sets a yearly insurance premium to this insurance.
     * 
     * @param premium The yearly insurance premium to be set to this insurance.
     */
    public void setPremium(int premium) {
        this.premium = premium;
    }
    
    // EQUALS AND HASHCODE METHODS
    
    /**
     * Indicates whether some other insurance is equal to this one. The result 
     * is true if and only if the argument is not null and is an Insurance 
     * object that contains the same insuranceId value as this object.
     * 
     * @param obj The object to compare with.
     * @return True if the objects are the same; false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if(obj != null && obj instanceof Insurance) {
            Insurance other = (Insurance) obj;
            return getInsuranceId() == other.getInsuranceId();
        } else {
            return false;
        }   
    }
    
    /**
     * Returns a hash code value for this insurance. This method is supported 
     * for the benefit of hash tables such as those provided by HashMap.
     * 
     * @return The hash code.
     */
    @Override 
    public int hashCode() {
        final int prime = 31;
        int result = 17;
        result = prime * result + getInsuranceId();
        return result;
    }
    
    // SAVE AND READ METHODS

    public static void saveNextIdToFile() throws IOException {
        try (DataOutputStream dos = new DataOutputStream(
                new BufferedOutputStream(
                        new FileOutputStream(insuranceIdFileName)))) {
            dos.writeInt(nextInsuranceId);
        }
    }
    
    public static void readNextIdFromFile() throws IOException {
        try (DataInputStream dis = new DataInputStream(
                new BufferedInputStream(
                        new FileInputStream(insuranceIdFileName)))) {
            nextInsuranceId = dis.readInt();
        }
    }
    
    // TO STRING METHOD
    
    /**
     * Returns a string representation of this insurance. The string
     * representation consists of each field with a short description separated
     * by a new line.
     * 
     * @return A string representation of this insurance.
     */
    @Override
    public String toString() {
        // Creates a StringBuilder which will be returned at the end of the 
        // method.
        StringBuilder result = new StringBuilder();
        // Appends the fields with appropriate sentences.
        result.append("Forsikringen er ").
                append(active ? "aktiv" : "inaktiv");
        result.append("\n\nForsikringen ble tegnet: ").
                append(DateUtility.NORWEGIAN_DATE_FORMAT.format(date.getTime()));
        result.append("\nForsikringsnummer: ").append(insuranceId);
        result.append("\nKundenummer: ").append(customerId);
        result.append("\nÅrlig forsikringspremie: ").append(premium);
        result.append("\nEgenandel: ").append(excess);
        // Returns the string.
        return result.toString();
    }

    /**
     * TEST METHOD. TO BE REMOVED. This method sets the date of the insurance. 
     * Useful for testing functionality that depends on older insurances.
     * @param date the date to set
     */
    public void setDate(Calendar date) {
        this.date = date;
    }

    /**
     * @return the nextPayDate
     */
    public Calendar getNextPayDate() {
        return nextPayDate;
    }

    /**
     * @param nextPayDate the nextPayDate to set
     */
    public void setNextPayDate(Calendar nextPayDate) {
        this.nextPayDate = nextPayDate;
    }

    /**
     * @return the monthlyPremium
     */
    public int getMonthlyPremium() {
        return monthlyPremium;
    }

    /**
     * @param monthlyPremium the monthlyPremium to set
     */
    public void setMonthlyPremium(int monthlyPremium) {
        this.monthlyPremium = monthlyPremium;
    }
}