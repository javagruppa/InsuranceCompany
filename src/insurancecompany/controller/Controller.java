/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package insurancecompany.controller;

import insurancecompany.model.datastructures.ClaimRegister;
import insurancecompany.model.people.ServiceWorker;
import insurancecompany.model.people.Admin;
import insurancecompany.model.people.CaseWorker;
import insurancecompany.model.datastructures.InsuranceRegister;
import insurancecompany.model.people.Customer;
import insurancecompany.model.datastructures.EmployeeRegister;
import insurancecompany.model.people.Employee;
import insurancecompany.model.datastructures.CustomerRegister;
import insurancecompany.model.properties.Address;
import insurancecompany.view.AdminView;
import insurancecompany.view.register.BoatInsuranceRegistration;
import insurancecompany.view.register.HolidayHomeInsuranceRegistration;
import insurancecompany.view.RegisterView;
import insurancecompany.view.register.HomeInsuranceRegistration;
import insurancecompany.view.register.TravelInsuranceRegistration;
import insurancecompany.view.register.CustomerRegistration;
import insurancecompany.view.LoginView;
import insurancecompany.misc.*;
import insurancecompany.model.vehicles.Boat;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.NotSerializableException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 *
 * @author André
 */
public class Controller {
    
    private static final String customerFileName = "src/customerRegister.dta";
    
    // Views:
    private LoginView loginView;
    private RegisterView registerView;
    private CustomerRegistration customerRegistration;
    private BoatInsuranceRegistration boatInsuranceRegistration;
    private HomeInsuranceRegistration homeInsuranceRegistration;
    private HolidayHomeInsuranceRegistration holidayHomeInsuranceRegistration;
    private TravelInsuranceRegistration travelInsuranceRegistration;
    private AdminView adminView;
    
    // Models:
    private EmployeeRegister employees;
    private CustomerRegister customers;
    private InsuranceRegister insurances;
    private ClaimRegister claims;
    
    public Controller()  {          
        employees = new EmployeeRegister();
        customers = new CustomerRegister();
        insurances = new InsuranceRegister();
        claims = new ClaimRegister();
        initializeView();
        initializeEventHandlers();
    }
    
    public void initializeView() {        
        loginView = new LoginView();
        adminView = new AdminView();
        registerView = new RegisterView();
        customerRegistration = new CustomerRegistration();
        boatInsuranceRegistration = new BoatInsuranceRegistration();
        homeInsuranceRegistration = new HomeInsuranceRegistration();
        holidayHomeInsuranceRegistration = new HolidayHomeInsuranceRegistration();
        travelInsuranceRegistration = new TravelInsuranceRegistration();
    }
    
    public void initializeEventHandlers() {
        loginView.getLoginBtn().setOnAction(this::loginBtnEventHandler);
        customerRegistration.getRegisterButton().setOnAction(this::registerCustomerEventHandler);
        boatInsuranceRegistration.getRegisterButton().setOnAction(this::registerBoatInsuranceEventHandler);
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
        boolean alarm = boatInsuranceRegistration.getAlarm();
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
   
    
    private void loginBtnEventHandler(ActionEvent e) {
        
        String userName = loginView.getUserTextField().getText();
        int employeeId = Integer.parseInt(userName);
        Employee employee = employees.getEmployeeById(employeeId);
        if (employee instanceof Admin) {
            System.out.println("Admin login");
        } else  if (employee instanceof CaseWorker) {
            System.out.println("case worker login");
        } else if (employee instanceof ServiceWorker) {
            System.out.println("Service work login");
        }
    }
    
    public void readCustomersFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream(customerFileName))) {
            customers = (CustomerRegister) ois.readObject();
        } catch (ClassNotFoundException cnfe) {
            
        } catch (FileNotFoundException fnfe) {
            
        } catch (IOException ioe) {
            
        }
    }
    
    public void writeCustomersToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(customerFileName))) {
            oos.writeObject(customers);
        } catch ( NotSerializableException nse ) {
            
        } catch (IOException ioe) {
            
        }
    }
}
