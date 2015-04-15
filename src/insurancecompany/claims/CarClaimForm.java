/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package insurancecompany.claims;
import insurancecompany.vehicles.*;
import insurancecompany.people.*;
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
    
    public CarClaimForm(Car car, Customer owner, Person otherPerson, Car otherCar,
            String insuranceComp, String location, String events){
        this.car = car;
        this.owner = owner;
        this.otherPerson = otherPerson;
        this.otherCar = otherCar;
        this.insuranceCompanyOther = insuranceComp;
        this.location = location;
        this.courseOfEvents = events;
    }
    
    public Car getCar(){
        return car;
    }
    
    public Car getOtherCar(){
        return otherCar;
    }
    
    public Customer getOwner(){
        return owner;
    }
    
    public Person getOtherPerson(){
        return otherPerson;
    }
    
    public String getInsuranceCompanyOther(){
        return insuranceCompanyOther;
    }
    
    public String getLocation(){
        return location;
    }
    
    public String getCourseOfEvents(){
        return  courseOfEvents;
    }
    
    public String toString(){
        String s = "Kunde:\n" + owner.toString() + "\n\n" + "Kundens kjøretøy:\n" + car.toString() + "\n\n" +
                "Andre involverte part:\n" + otherPerson.toString() + "\n\n" + "Andre involverte kjøretøy:\n" +
                otherCar.toString() + "\n\nSted for hendelse:\n" + location + "\n\nHendelsesforløp:\n" + courseOfEvents;
        return s;
    }
    
    
}
