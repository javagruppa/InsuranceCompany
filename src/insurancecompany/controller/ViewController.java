/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package insurancecompany.controller;

import insurancecompany.model.people.Admin;
import insurancecompany.model.people.CaseWorker;
import insurancecompany.model.people.Customer;
import insurancecompany.model.people.Employee;
import insurancecompany.model.people.ServiceWorker;
import insurancecompany.model.properties.Address;
import insurancecompany.view.AdminView;
import insurancecompany.view.LoginView;
import insurancecompany.view.RegisterView;
import insurancecompany.view.register.BoatInsuranceRegistration;
import insurancecompany.view.register.CarInsuranceRegistration;
import insurancecompany.view.register.CustomerRegistration;
import insurancecompany.view.register.HolidayHomeInsuranceRegistration;
import insurancecompany.view.register.HomeInsuranceRegistration;
import insurancecompany.view.register.TravelInsuranceRegistration;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 *
 * @author Sindre
 */
public class ViewController {
    // General Views:
    private AdminView adminView;
    private LoginView loginView;
    private RegisterView registerView;
    
    // Registration Views:
    private BoatInsuranceRegistration boatInsuranceRegistration;
    private CarInsuranceRegistration carInsuranceRegistration;
    private CustomerRegistration customerRegistration;
    private HolidayHomeInsuranceRegistration holidayHomeInsuranceRegistration;
    private HomeInsuranceRegistration homeInsuranceRegistration;
    private TravelInsuranceRegistration travelInsuranceRegistration;
    
    // Constructor
    public ViewController() {
        initializeViews();
        initializeEventHandlers();
    }
    
    public void initializeViews() {
        // General Views:
        adminView = new AdminView();
        loginView = new LoginView();
        registerView = new RegisterView();
        
        // Registration Views:
        boatInsuranceRegistration = new BoatInsuranceRegistration();
        carInsuranceRegistration = new CarInsuranceRegistration();
        customerRegistration = new CustomerRegistration();
        holidayHomeInsuranceRegistration = new HolidayHomeInsuranceRegistration();
        homeInsuranceRegistration = new HomeInsuranceRegistration();
        travelInsuranceRegistration = new TravelInsuranceRegistration();
    }
    
    public void initializeEventHandlers() {
        boatInsuranceRegistration.setRegisterButtonEventHandler(this::registerBoatInsuranceEventHandler);
    }
    
    public void show(Stage stage) {
        //login.show(stage);
        adminView.show(stage);
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
        String customerPersonalNumber = boatInsuranceRegistration.
                getCustomerPersonalNumber();
        String excessString = boatInsuranceRegistration.getExcess();
        
        // Collects information about the boat.
        String alarmString = boatInsuranceRegistration.getAlarm();
        String brand = boatInsuranceRegistration.getBrand();
        String engineEffectString = boatInsuranceRegistration.
                getEngineEffect();
        String engineType = boatInsuranceRegistration.getEngineType();
        String lengthString = boatInsuranceRegistration.getLength();
        String model = boatInsuranceRegistration.getModel();
        String ownerPersonalNumber = boatInsuranceRegistration.
                getOwnerPersonalNumber();
        String registrationNumber = boatInsuranceRegistration.
                getRegistrationNumber();
        String registrationYearString = boatInsuranceRegistration.
                getRegistrationYear();
        
        // Creates a boolean which is to set true if the user has made a 
        // mistake and the method has to abort.
        boolean abort = false;
        
        // Converts strings to integers.
        int engineEffect;
        try {
            engineEffect = Integer.parseInt(engineEffectString);
        } catch(NumberFormatException nfe) {
            boatInsuranceRegistration.
                    setEngineEffectMessage("* Kan kun bestå av tall.");
            abort = true;
        }
        
        int excess;
        try {
            excess = Integer.parseInt(excessString);
        } catch(NumberFormatException nfe) {
            boatInsuranceRegistration.
                    setExcessMessage("* Kan kun bestå av tall.");
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
        
        if(abort) {
            return;
        }
        
        // Creates Boat
        
        // Creates BoatInsurance
        
        // Adds insurance to Register
    }
}
