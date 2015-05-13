/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package insurancecompany.controller;


import insurancecompany.misc.coverages.BoatInsuranceCoverage;
import insurancecompany.model.datastructures.ClaimRegister;
import insurancecompany.model.datastructures.CustomerRegister;
import insurancecompany.model.datastructures.EmployeeRegister;
import insurancecompany.model.datastructures.InsuranceRegister;
import insurancecompany.model.datastructures.LogRegister;
import insurancecompany.model.datastructures.carinfo.*;
import insurancecompany.model.insurances.Insurance;
import insurancecompany.model.people.Customer;
import insurancecompany.model.properties.Address;
import insurancecompany.view.register.insurances.BoatInsuranceRegistration;
import insurancecompany.view.register.insurances.CarInsuranceRegistration;
import insurancecompany.view.register.insurances.HolidayHomeInsuranceRegistration;
import insurancecompany.view.register.insurances.HomeInsuranceRegistration;
import insurancecompany.view.register.insurances.TravelInsuranceRegistration;
import insurancecompany.view.register.persons.CustomerRegistration;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

/**
 * Model controller. This class uses the model and its data structures to 
 * update other parts of the model as well as finding information by combining
 * methods and data from different parts of the model and its different 
 * data structures.
 * 
 * @author André
 * @author Sindre
 */
public class ModelController {
    
    // Insurance Registration Views
    private BoatInsuranceRegistration boatInsuranceRegistration;
    private CarInsuranceRegistration carInsuranceRegistration;
    private CustomerRegistration customerRegistration;
    private HolidayHomeInsuranceRegistration holidayHomeInsuranceRegistration;
    private HomeInsuranceRegistration homeInsuranceRegistration;
    private TravelInsuranceRegistration travelInsuranceRegistration;
    
    private CarInfoRegister carInfoRegister;
    private LogRegister logRegister;
    
    private ClaimRegister claims;
    private EmployeeRegister employees;
    private InsuranceRegister insurances;
    private CustomerRegister customers;
    
    public ModelController(BoatInsuranceRegistration boatInsuranceRegistration, 
            CarInsuranceRegistration carInsuranceRegistration, 
            HolidayHomeInsuranceRegistration holidayHomeInsuranceRegistration,
            HomeInsuranceRegistration homeInsuranceRegistration,
            TravelInsuranceRegistration travelInsuranceRegistration, 
            ClaimRegister claims, EmployeeRegister employees, 
            InsuranceRegister insurances, CustomerRegister customers) {
        
        // Initializes Registration Views
        this.boatInsuranceRegistration = boatInsuranceRegistration;
        this.carInsuranceRegistration = carInsuranceRegistration;
        this.holidayHomeInsuranceRegistration = holidayHomeInsuranceRegistration;
        this.homeInsuranceRegistration = homeInsuranceRegistration;
        this.travelInsuranceRegistration = travelInsuranceRegistration;
        
        this.claims = claims;
        this.employees = employees;
        this.insurances = insurances;
        this.customers = customers;
        
        initializeEventHandlers();
        unmarshalCarInfoRegister();
    }
    
    public void initializeEventHandlers() {
        boatInsuranceRegistration.setRegisterButtonEventHandler(this::registerBoatInsuranceEventHandler);
    }
    
    /**
     * Returns all insurances based on personal number of the insurance owner.
     * @param personalNumber
     * @return 
     */
    public List<Insurance> getAllInsurancesByPersonalNumber(String personalNumber) {
        List<Insurance> result = new ArrayList<>();
        // Find the customer that matches the personal number:
        Customer c = customers.findCustomerByPersonalNumber(personalNumber);
        // If he/she exists:
        if (c != null) {
            // Get the customer id:
            int customerId = c.getId();
            // Use this id, to get all insurances of this customer:
            result = insurances.getAllInsurancesByCustomerId(customerId);
        }
        // Return the result:
        return result;
    }
    
    public final void unmarshalCarInfoRegister() {        
        try {
		File file = new File("src/insurancecompany/resources/xml/Car_makes_and_models.xml");
                JAXBContext jaxbContext = JAXBContext.newInstance(CarInfoRegister.class);
		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();               
		CarInfoRegister carInfoRegister = (CarInfoRegister) jaxbUnmarshaller.unmarshal(file);
                this.carInfoRegister = carInfoRegister;
 
	  } catch (JAXBException e) {
              logRegister.add(e.getStackTrace(), e.getMessage());
	  }
    }
    
    public List<CarInfo> getCarInfos() {
        return carInfoRegister.getCars();
    }
    
    public CarInfo findCarInfo(String name) {
        return carInfoRegister.findCarByName(name);
    }
    
    /**
     * Calculates whether a customer gets total customer discount or not
     * based on the number of active insurances of this customer.
     * @param customerId
     * @return 
     */
    public boolean calculateTotalCustomer(int customerId) {
        Customer c = customers.findCustomerById(customerId);
        int count = 0; // Counts the number of active insurances on this customer.
        List<Insurance> insuranceList = insurances.getAllInsurancesByCustomerId(customerId);
        for (Insurance a : insuranceList) {
            if (a.isActive()) {
                count++;
            }
        }
        if (count >= 3) {
            c.setTotalCustomer(true);
            return true;
        } else {
            c.setTotalCustomer(false);
            return false;
        }
    }
    
    /*private void registerCustomerEventHandler(ActionEvent e) {
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
        
        int customerId = customer.getId();
        System.out.println(customerId);
        //boolean ok1 = customers.addCustomer(customer);
        
        //Customer test = customers.findCustomerById(customerId);
        if (!ok) {
            output += "Får ikke lagt til kunden. Kunde med personnummer: " + personalNumberS 
                    + " eksisterer allerede i kunderegisteret.";
        }
    }*/
    
    private void registerBoatInsuranceEventHandler(ActionEvent e) {
        // Collects information about the customer and the insurance.
        BoatInsuranceCoverage coverage = boatInsuranceRegistration.getCoverage();
        String customerId = boatInsuranceRegistration.getCustomerId();
        String customerPersonalNumber = boatInsuranceRegistration.getPersonalNumber();
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
}