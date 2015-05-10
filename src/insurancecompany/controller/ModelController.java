/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package insurancecompany.controller;

import insurancecompany.model.datastructures.ClaimRegister;
import insurancecompany.model.datastructures.CustomerRegister;
import insurancecompany.model.datastructures.EmployeeRegister;
import insurancecompany.model.datastructures.InsuranceRegister;
import insurancecompany.model.datastructures.carinfo.*;
import insurancecompany.model.people.Customer;
import insurancecompany.model.properties.Address;
import insurancecompany.view.register.insurances.*;
import insurancecompany.view.register.persons.CustomerRegistration;
import java.io.File;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

/**
 *
 * @author Sindre
 */
public class ModelController {
    
    private CarInfoRegister carInfoRegister;
    
    public ModelController() {
        unmarshalCarInfoRegister();
    }
    
    public final void unmarshalCarInfoRegister() {        
        try {
		File file = new File("src/insurancecompany/resources/xml/Car_makes_and_models.xml");
                JAXBContext jaxbContext = JAXBContext.newInstance(CarInfoRegister.class);
		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();               
		CarInfoRegister carInfoRegister = (CarInfoRegister) jaxbUnmarshaller.unmarshal(file);
                this.carInfoRegister = carInfoRegister;
                
                // test:
                String name = carInfoRegister.getCars().get(1).getName();
                int to = carInfoRegister.getCars().get(0).getModelRegister().getModels().get(2).getTo();
		System.out.println(name + to);
 
	  } catch (JAXBException e) {
		e.printStackTrace();
	  }
    }
    
    public List<CarInfo> getCarInfos() {
        return carInfoRegister.getCars();
    }
    
    public CarInfo findCarInfo(String name) {
        return carInfoRegister.findCarByName(name);
    }
    
    /*
    // Insurance Registration Views
    private BoatInsuranceRegistration boatInsuranceRegistration;
    private CarInsuranceRegistration carInsuranceRegistration;
    private CustomerRegistration customerRegistration;
    private HolidayHomeInsuranceRegistration holidayHomeInsuranceRegistration;
    private HomeInsuranceRegistration homeInsuranceRegistration;
    private TravelInsuranceRegistration travelInsuranceRegistration;
    
    private CarInfoRegister carInfoRegister;
    
    public ModelController(BoatInsuranceRegistration boatInsuranceRegistration, 
            CarInsuranceRegistration carInsuranceRegistration, 
            HolidayHomeInsuranceRegistration holidayHomeInsuranceRegistration,
            HomeInsuranceRegistration homeInsuranceRegistration,
            TravelInsuranceRegistration travelInsuranceRegistration) {
        
        // Initializes Registration Views
        this.boatInsuranceRegistration = boatInsuranceRegistration;
        this.carInsuranceRegistration = carInsuranceRegistration;
        this.holidayHomeInsuranceRegistration = holidayHomeInsuranceRegistration;
        this.homeInsuranceRegistration = homeInsuranceRegistration;
        this.travelInsuranceRegistration = travelInsuranceRegistration;
        
        initializeEventHandlers();
        unmarshalCarInfoRegister();
    }
    
    public void initializeEventHandlers() {
        boatInsuranceRegistration.setRegisterButtonEventHandler(this::registerBoatInsuranceEventHandler);
    }
    
    private void registerCustomerEventHandler(ActionEvent e) {
        boolean ok = true;
        String output = "";
        
        String personalNumberS = customerRegistration.getPersonalNumberField().getText();
        String firstName = customerRegistration.getFirstNameField().getText();
        String lastName = customerRegistration.getLastNameField().getText();
        String street = customerRegistration.getStreetField().getText();
        String zipCodeS = customerRegistration.getZipCodeField().getText();
        String city = customerRegistration.getCityField().getText();
        String email = customerRegistration.getEmailField().getText();
        String phone = customerRegistration.getPhoneField().getText();
        
        if (personalNumberS.equals("")) {
            Label message = new Label();
            message.setText("*");
            message.setTextFill(Color.rgb(210, 39, 30));
            customerRegistration.setPersonalNumberMessage(message);
        }
 
        long personalNumber = 0;
        try {
            personalNumber = Long.parseLong(personalNumberS);
        } catch (NumberFormatException nfe) {

        }
        int zipCode = 0;
        try {
            zipCode = Integer.parseInt(zipCodeS);
        } catch (NumberFormatException nfe) {
            
        }
        
        Address adress = new Address(street, zipCode, city);
        
        Customer customer = new Customer(firstName, lastName, personalNumber, email, adress, phone);
        
        int customerId = customer.getCustomerId();
        System.out.println(customerId);
        //boolean ok1 = customers.addCustomer(customer);
        
        //Customer test = customers.findCustomerById(customerId);
        if (!ok) {
            output += "Får ikke lagt til kunden. Kunde med personnummer: " + personalNumberS 
                    + " eksisterer allerede i kunderegisteret.";
        }
    }
    
    private void registerBoatInsuranceEventHandler(ActionEvent e) {
        // Collects information about the customer and the insurance.
        String coverage = boatInsuranceRegistration.getCoverage();
        String customerId = boatInsuranceRegistration.getCustomerId();
        String customerPersonalNumber = boatInsuranceRegistration.getCustomerPersonalNumber();
        String excessString = boatInsuranceRegistration.getExcess();
        
        // Collects information about the boat.
        String alarmString = boatInsuranceRegistration.getAlarm();
        String brand = boatInsuranceRegistration.getBrand();
        String engineEffectString = boatInsuranceRegistration.getEngineEffect();
        String engineType = boatInsuranceRegistration.getEngineType();
        String lengthString = boatInsuranceRegistration.getLength();
        String model = boatInsuranceRegistration.getModel();
        String ownerPersonalNumber = boatInsuranceRegistration.getOwnerPersonalNumber();
        String registrationNumber = boatInsuranceRegistration.getRegistrationNumber();
        String registrationYearString = boatInsuranceRegistration.getRegistrationYear();
        
        // Creates a boolean which is to be  set true if the user has made a 
        // mistake and the method has to abort.
        boolean abort = false;
        
        // Creates strings to be used in messages to the user.
        String nfeMessage = "* Kan kun bestå av tall.";
        
        // Converts strings to integers.
        int engineEffect;
        try {
            engineEffect = Integer.parseInt(engineEffectString);
        } catch(NumberFormatException nfe) {
            boatInsuranceRegistration.setEngineEffectMessage(nfeMessage);
            abort = true;
        }
        
        int excess;
        try {
            excess = Integer.parseInt(excessString);
        } catch(NumberFormatException nfe) {
            boatInsuranceRegistration.setExcessMessage(nfeMessage);
            abort = true;
        }
        
        int length;
        try {
            length = Integer.parseInt(lengthString);
        } catch(NumberFormatException nfe) {
            boatInsuranceRegistration.
                    setLengthMessage("* Kan kun bestå av tall.");
            abort = true;
        }
        
        int registrationYear;
        try {
            registrationYear = Integer.parseInt(registrationYearString);
        } catch(NumberFormatException nfe) {
            boatInsuranceRegistration.
                    setRegistrationYearMessage("* Kan kun bestå av tall.");
            abort = true;
        }
        
        // Creates strings to be used in messages to the user.
        String missingMessage = "* Dette feltet må fylles ut.";
        
        if(coverage.equals("")) {
            boatInsuranceRegistration.setCoverageMessage(missingMessage);
            abort = true;
        }
        if(customerId.equals("")) {
            boatInsuranceRegistration.setCustomerIdMessage(missingMessage);
            abort = true;
        }
        if(customerPersonalNumber.equals("")) {
            boatInsuranceRegistration.setCustomerPersonalNumberMessage(missingMessage);
        }
        if(excessString.equals("")) {
            boatInsuranceRegistration.setExcessMessage(missingMessage);
        }
        if(alarmString.equals("")) {
            boatInsuranceRegistration.setAlarmMessage(missingMessage);
        }
        if(brand.equals("")) {
            boatInsuranceRegistration.setBrandMessage(missingMessage);
        }
        if(engineEffectString.equals("")) {
            boatInsuranceRegistration.setEngineEffectMessage(missingMessage);
        }
        if(engineType.equals("")) {
            boatInsuranceRegistration.setEngineTypeMessage(missingMessage);
        }
        if(lengthString.equals("")) {
            boatInsuranceRegistration.setLengthMessage(missingMessage);
        }
        if(model.equals("")) {
            boatInsuranceRegistration.setModelMessage(missingMessage);
        }
        if(ownerPersonalNumber.equals("")) {
            boatInsuranceRegistration.setOwnerPersonalNumberMessage(missingMessage);
        }
        if(registrationNumber.equals("")) {
            boatInsuranceRegistration.setRegistrationNumberMessage(missingMessage);
        }
        if(registrationYearString.equals("")) {
            boatInsuranceRegistration.setRegistrationYearMessage(missingMessage);
        }
        
        if(abort) {
            return;
        }
        
        // Creates Boat
        
        // Creates BoatInsurance
        
        // Adds insurance to Register
    }
    
    public final void unmarshalCarInfoRegister() {        
        try {
		File file = new File("src/insurancecompany/resources/xml/Car_makes_and_models.xml");
                JAXBContext jaxbContext = JAXBContext.newInstance(CarInfoRegister.class);
		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();               
		CarInfoRegister carInfoRegister = (CarInfoRegister) jaxbUnmarshaller.unmarshal(file);
                this.carInfoRegister = carInfoRegister;
                
                // test:
                String name = carInfoRegister.getCars().get(1).getName();
                int to = carInfoRegister.getCars().get(0).getModelRegister().getModels().get(2).getTo();
		System.out.println(name + to);
 
	  } catch (JAXBException e) {
		e.printStackTrace();
	  }
    }
    
    public List<CarInfo> getCarInfos() {
        return carInfoRegister.getCars();
    }
    
    public CarInfo findCarInfo(String name) {
        return carInfoRegister.findCarByName(name);
    }
}
