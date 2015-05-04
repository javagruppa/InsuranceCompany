/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package insurancecompany.controller;

import insurancecompany.datastructures.*;
import insurancecompany.gui.*;
import insurancecompany.people.*;
import insurancecompany.misc.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.NotSerializableException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
    private Login login;
    private RegisterGui registerGui;
    private CustomerRegistration cReg;
    private GuiAdmin guiAdmin;
    
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
        
        login = new Login();
        registerGui = new RegisterGui();
        cReg = new CustomerRegistration();
        guiAdmin = new GuiAdmin();
        login.getLoginBtn().setOnAction(this::loginBtnEventHandler);
        guiAdmin.getRegisterButton().setOnAction(this::registerPaneEventHandler);
        registerGui.getCustomerButton().setOnAction(this::registerCustomerPaneEventHandler);
        cReg.getRegisterButton().setOnAction(this::registerCustomerEventHandler);
    }
    
    public void initGui() {
        Pane pane = registerGui.getMainPane();
        guiAdmin.getMainPane().setCenter(pane);
        Pane pane2 = cReg.getMainPane();
        registerGui.getMainPane().setCenter(pane2);
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
    
    private void registerPaneEventHandler(ActionEvent e) {
        Pane pane = registerGui.getMainPane();
        guiAdmin.getMainPane().setCenter(pane);
    }
    
    private void registerCustomerPaneEventHandler(ActionEvent e) {
        Pane pane = cReg.getMainPane();
        registerGui.getMainPane().setCenter(pane);
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
