/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package insurancecompany.model.claims;
/**
 *
 * @author Carl
 */
public class CarClaimForm {
    private Car car;
    private Customer owner;
    private Person otherPerson;
    private Car otherCar;
    private String insuranceCompanyOther;
    private String location;
    private String courseOfEvents;
    
    /**
     * Constructs a new CarClaimForm with the specified car, owner, otherperson,
     * othercar, insuranceComp, location and events.
     * 
     * @param car the car this CarClaimForm covers
     * @param owner the owner of the card this CarClaimForm covers
     * @param otherPerson the other person involved in the accident
     * @param otherCar the other car involved in the accident
     * @param insuranceComp the insurance company of the other part involved
     * @param location where the accident happened
     * @param events the courseOfEvents
     */
    public CarClaimForm(Car car, Customer owner, Person otherPerson,
            Car otherCar, String insuranceComp, String location, String events){
        this.car = car;
        this.owner = owner;
        this.otherPerson = otherPerson;
        this.otherCar = otherCar;
        this.insuranceCompanyOther = insuranceComp;
        this.location = location;
        this.courseOfEvents = events;
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
    public Person getOtherPerson(){
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
     * Return a string with the course of events for the accident
     * @return  a string representation of the course of events.
     */
    public String getCourseOfEvents(){
        return  courseOfEvents;
    }
    
    /**
     * Returns a string representation of the CarClaimForm. The string
     * representation consists of each field with a short description.
     * 
     * @return a string representation of this CarClaimForm
     */
    public String toString(){
        String s = "Kunde:\n" + owner.toString() + "\n\n"
                + "Kundens kjøretøy:\n" + car.toString() + "\n\n"
                + "Andre involverte part:\n" + otherPerson.toString()
                + "\n\n" + "Andre involverte kjøretøy:\n"
                + otherCar.toString() + "\n\nSted for hendelse:\n" + location
                + "\n\nHendelsesforløp:\n" + courseOfEvents;
        return s;
    }
    
    
}
