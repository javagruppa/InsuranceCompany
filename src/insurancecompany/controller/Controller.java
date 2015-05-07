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
import insurancecompany.model.datastructures.VehicleRegister;
import insurancecompany.model.datastructures.EmployeeRegister;
import insurancecompany.model.people.Employee;
import insurancecompany.model.datastructures.CustomerRegister;
import insurancecompany.model.properties.Address;
import insurancecompany.view.AdminView;
import insurancecompany.view.register.BoatInsuranceRegistration;
import insurancecompany.view.register.HolidayHomeInsuranceRegistration;
import insurancecompany.view.RegisterPane;
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
    private LoginView login;
    private RegisterPane registerPane;
    private CustomerRegistration cReg;
    private BoatInsuranceRegistration biReg;
    private HomeInsuranceRegistration hiReg;
    private HolidayHomeInsuranceRegistration hhiReg;
    private TravelInsuranceRegistration tiReg;
    private AdminView guiAdmin;
    
    // Models:
    private EmployeeRegister employees;
    private CustomerRegister customers;
    private InsuranceRegister insurances;
    private VehicleRegister vehicles;
    private ClaimRegister claims;
    
    public Controller()  {
           
        employees = new EmployeeRegister();
        customers = new CustomerRegister();
        insurances = new InsuranceRegister();
        vehicles = new VehicleRegister();
        claims = new ClaimRegister();
        
        login = new LoginView();
        registerPane = new RegisterPane();
        cReg = new CustomerRegistration();
        biReg = new BoatInsuranceRegistration();
        hiReg = new HomeInsuranceRegistration();
        hhiReg = new HolidayHomeInsuranceRegistration();
        tiReg = new TravelInsuranceRegistration();
        guiAdmin = new AdminView();
        login.getLoginBtn().setOnAction(this::loginBtnEventHandler);
        guiAdmin.getRegisterButton().setOnAction(this::registerPaneEventHandler);
        registerPane.getCustomerButton().setOnAction(this::registerCustomerPaneEventHandler);
        registerPane.getBoatInsuranceButton().setOnAction(this::registerBoatInsurancePaneEventHandler);
        registerPane.getHomeInsuranceButton().setOnAction(this::registerHomeInsurancePaneEventHandler);
        registerPane.getHolidayHomeInsuranceButton().setOnAction(this::registerHolidayHomeInsurancePaneEventHandler);
        registerPane.getTravelInsuranceButton().setOnAction(this::registerTravelInsurancePaneEventHandler);
        cReg.getRegisterButton().setOnAction(this::registerCustomerEventHandler);
    }
    
    public void initGui() {
        Pane pane = registerPane.getMainPane();
        guiAdmin.getMainPane().setCenter(pane);
        Pane pane2 = cReg.getMainPane();
        registerPane.getMainPane().setCenter(pane2);
    }
    
    public void show(Stage stage) {
        //login.show(stage);
        guiAdmin.show(stage);
    }
   
    
    private void registerCustomerEventHandler(ActionEvent e) {
        boolean ok = true;
        String output = "";
        
        String personalNumberS = cReg.getPersonalNumberField().getText();
        String firstName = cReg.getFirstNameField().getText();
        String lastName = cReg.getLastNameField().getText();
        String street = cReg.getStreetField().getText();
        String zipCodeS = cReg.getZipCodeField().getText();
        String city = cReg.getCityField().getText();
        String email = cReg.getEmailField().getText();
        String phone = cReg.getPhoneField().getText();
        
        if (personalNumberS.equals("")) {
            Label message = new Label();
            message.setText("*");
            message.setTextFill(Color.rgb(210, 39, 30));
            cReg.setPersonalNumberMessage(message);
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
        
        boolean ok1 = customers.addCustomer(customer);
        
        //Customer test = customers.findCustomerById(customerId);
        if (!ok) {
            output += "Får ikke lagt til kunden. Kunde med personnummer: " + personalNumberS 
                    + " eksisterer allerede i kunderegisteret.";
        }
        
        
        
    }
    
    /*private void registerBoatInsuranceEventHandler(ActionEvent e) {
        // Collects information about the boat.
        String regNumber = biReg.getRegNumberField().getText();
        String effectS = biReg.getEffectField().getText();
        String lengthS = biReg.getLengthField().getText();
        String engineType = biReg.getEngineTypeField().getText();
        String yearS = biReg.getYearField().getText();
        String make = biReg.getMakeField().getText();
        String model = biReg.getModelField().getText();
        
        // Collects information about the customer and the insurance.
        String customerId = biReg.getCustomerIdField().getText();
        String coverageS = biReg.getCoverageField().getText();
        String excessS = biReg.getExcessField().getText();
        boolean hasAlarm = biReg.getHasAlarmCheckBox().isSelected();
        
        // Converts strings to integers.
        int effect;
        try {
            effect = Integer.parseInt(effectS);
        } catch(NumberFormatException nfe) {
            
        }
        int length;
        try {
            length = Integer.parseInt(lengthS);
        } catch(NumberFormatException nfe) {
            
        }
        int year;
        try {
            year = Integer.parseInt(yearS);
        } catch(NumberFormatException nfe) {
            
        }
        int coverage;
        try {
            coverage = Integer.parseInt(coverageS);
        } catch(NumberFormatException nfe) {
            
        }
        int excess;
        try {
            excess = Integer.parseInt(excessS);
        } catch(NumberFormatException nfe) {
            
        }
        
        // Creates Boat
        Boat boat = new Boat(regNumber, effect, length, engineType, year, make, model);
        
        // Creates BoatInsurance
        
        // Adds insurance to Register
        
        //new Boat(int regNumber, int effect, int length, String engineType, int year, String make, String model)
    }*/
    
    private void registerPaneEventHandler(ActionEvent e) {
        Pane pane = registerPane.getMainPane();
        guiAdmin.getMainPane().setCenter(pane);       
    }
    
    private void registerCustomerPaneEventHandler(ActionEvent e) {
        Pane pane = cReg.getMainPane();
        registerPane.getMainPane().setCenter(pane); 
    }
    
    private void registerBoatInsurancePaneEventHandler(ActionEvent e) {
        Pane pane = biReg.getMainPane();
        registerPane.getMainPane().setCenter(pane); 

    }
    
    private void registerHomeInsurancePaneEventHandler(ActionEvent e) {
        Pane pane = hiReg.getMainPane();
        registerPane.getMainPane().setCenter(pane); 
        selectedButtonStyleLower(registerPane.getHomeInsuranceButton());
    }
    
    private void registerHolidayHomeInsurancePaneEventHandler(ActionEvent e) {
        Pane pane = hhiReg.getMainPane();
        registerPane.getMainPane().setCenter(pane); 
        selectedButtonStyleLower(registerPane.getHolidayHomeInsuranceButton());
    }
    
    private void registerTravelInsurancePaneEventHandler(ActionEvent e) {
        Pane pane = tiReg.getMainPane();
        registerPane.getMainPane().setCenter(pane); 
        //selectedButtonStyleLower(registerPane.getTravelInsuranceButton());
    }
    

    
    
    private void loginBtnEventHandler(ActionEvent e) {
        
        String userName = login.getUserTextField().getText();
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
