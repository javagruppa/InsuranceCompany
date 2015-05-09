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
public class MainController {
    
    private static final String customerFileName = "src/customerRegister.dta";
    
    // Models:
    private EmployeeRegister employees;
    private CustomerRegister customers;
    private InsuranceRegister insurances;
    private ClaimRegister claims;
    
    // Sub Controllers:
    private ViewController viewController;
    
    public MainController()  {          
        employees = new EmployeeRegister();
        customers = new CustomerRegister();
        insurances = new InsuranceRegister();
        claims = new ClaimRegister();
        viewController = new ViewController();
    }
    
    public void show(Stage stage) {;
        viewController.show(stage);
    }
}