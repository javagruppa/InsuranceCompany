package insurancecompany.model.claims;

import insurancecompany.misc.DateUtility;
import insurancecompany.model.people.Customer;
import insurancecompany.model.people.VehicleOwner;
import insurancecompany.model.vehicles.Car;
import java.awt.image.BufferedImage;
import java.io.*;
import java.io.Serializable;
import java.util.Calendar;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javax.imageio.ImageIO;

/**
 * Class CarClaimForm. A form to be included with all car claim, giving
 * necessary information about the damage, drivers/cars included and course
 * of events.
 * 
 * @author Carl
 * @since 19.05.2015
 */
public class CarClaimForm implements Serializable {
    /** SerialVersionUID used to identify this class for object IO */
    private static final long serialVersionUID = 1L;
    /** The car covered by this car claim form */
    private Car car;
    /** The customer who owns the insurance */
    private Customer owner;
    /** The owner of another vehicle involved in the accident */
    private VehicleOwner otherPerson;
    /** Another car involved in the accident */
    private Car otherCar;
    /** The name of the insurance company of the other part involved */
    private String insuranceCompanyOther;
    /** The location of the accident */
    private String location;
    /** A user drawn image of the accident. */
    private transient Image snapshot;
    /** The date of the accident */
    private Calendar date;
    /** The Id for the insurance covering this damage */
    private int insuranceId;
    /** if there is any witnesses to the accident */
    private String witnesses;
    
    /**
     * Constructs a new CarClaimForm with the specified car, owner, otherperson,
     * othercar, insuranceComp, location and events.
     * This is for accidents with other cars involved
     * 
     * @param car the car this CarClaimForm covers
     * @param owner the owner of the card this CarClaimForm covers
     * @param otherPerson the other person involved in the accident
     * @param otherCar the other car involved in the accident
     * @param insuranceComp the insurance company of the other part involved
     * @param location where the accident happened
     * @param insuranceId the Id of the insurance covering this damage
     * @param witnesses the witnesses for the accident, if any
     * @param snapshot to the claimform
     */
    public CarClaimForm(Car car, Customer owner, VehicleOwner otherPerson,
            Car otherCar, String insuranceComp, String location,
            int insuranceId, String witnesses, Image snapshot){
        this.car = car;
        this.owner = owner;
        this.otherPerson = otherPerson;
        this.otherCar = otherCar;
        this.insuranceCompanyOther = insuranceComp;
        this.location = location;
        this.insuranceId = insuranceId;
        this.witnesses = witnesses;
        this.snapshot = snapshot;
        date = Calendar.getInstance();
    }
    
    /**
     * Constructs a new CarClaimForm with the specified car, owner, otherperson,
     * othercar, insuranceComp, location and events.
     * This is for accidents without other cars involved
     * 
     * @param car the car this CarClaimForm covers
     * @param owner the owner of the card this CarClaimForm covers
     * @param location where the accident happened
     * @param insuranceId the Id of the insurance covering this damage
     */
    public CarClaimForm(Car car, Customer owner, String location, 
            int insuranceId, Image snapshot){
        this.car = car;
        this.owner = owner;
        this.location = location;
        this.insuranceId = insuranceId;
        this.snapshot = snapshot;
        date = Calendar.getInstance();
    }
    
    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        if (snapshot != null) {
            out.writeBoolean(true);
            BufferedImage bufferedImage = SwingFXUtils.fromFXImage(snapshot, null);
            ImageIO.write(bufferedImage, "png", out); // png is lossless
        } else {
            out.writeBoolean(false);
        }
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        if (in.readBoolean() == true) {
            BufferedImage bufferedImage = ImageIO.read(in);
            snapshot = SwingFXUtils.toFXImage(bufferedImage, null);
        }
    }
    
    /**
     * Returns the specified car this CarClaimForm is for. 
     * @return a car-object from this CarClaimForm
     */
    public Car getCar(){
        return car;
    }
    
    /**
     * Returns the other car involved in this accident.
     * @return a car-object from this CarClaimForm
     */
    public Car getOtherCar(){
        return otherCar;
    }
    
    /**
     * Returns the owner of the car involved in this accident.
     * @return an object of the type VehicleOwner
     */
    public Customer getOwner(){
        return owner;
    }
    
    /**
     * Returns the other person involved in the accident.
     * @return a Person-object from this accident
     */
    public VehicleOwner getOtherPerson(){
        return otherPerson;
    }
    
    /**
     * Returns the insurance company of the other party involved in the
     * accident.
     * @return a String with the name of the insurance company
     */
    public String getInsuranceCompanyOther(){
        return insuranceCompanyOther;
    }
    
    /**
     * Returns a string with the location of the accident.
     * @return a string with the location of the accident
     */
    public String getLocation(){
        return location;
    }
    
    
    /**
     * Returns a string representation of the CarClaimForm. The string
     * representation consists of each field with a short description.
     * 
     * @return a string representation of this CarClaimForm
     */
    @Override
    public String toString() {
        // Creates a StringBuilder which will be returned at the end of the 
        // method.
        StringBuilder result = new StringBuilder();
        // Appends the fields with appropriate sentences.
        result.append("SKADEMELDINGSSKJEMA");
        result.append("\nDato registrert: ").append
                (DateUtility.NORWEGIAN_DATE_FORMAT.format(date));
        result.append("\n\nBIL A:");
        result.append("\nKundenummer: ").append(owner.getId());
        result.append("\nKundenavn: ").append(owner.getName());
        result.append("\nBilens registeringsnummer: ").append
                (car.getRegistrationNumber());
        result.append("\nForsikringsnummer: ").append(insuranceId);
        // If another person is involved in the accident, the following fields
        // will also be added
        if(otherPerson != null) {
            result.append("\n\nBIL B: ");
            result.append("\nEiers navn: ").append(otherPerson.getName());
            result.append("\nEiers personnummer: ").append
                    (otherPerson.getPersonalNumber());
            result.append("\nBilens registreringsnummer: ").append
                    (otherCar.getRegistrationNumber());
            result.append("\nForsikringsselskap: ").append
                    (insuranceCompanyOther);
        }
        // If there are witnesses, these are added
        if(witnesses != null) {
            result.append("\nVitne(r) med telefonnummer: ").append(witnesses);
        }
        // These fields are added regardless of if another person i involved
        result.append("\n\nHvor hendelsen skjedde:\n").append
                (location).append("\n");
        // Returns the string.
        return result.toString();
        
    }    

    /**
     * Returns a snapshot of the claim form.
     * @return the snapshot
     */
    public Image getSnapshot() {
        return snapshot;
    }
    
}
