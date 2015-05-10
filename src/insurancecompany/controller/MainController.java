/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package insurancecompany.controller;

import insurancecompany.view.register.persons.CustomerRegistration;
import insurancecompany.view.register.insurances.TravelInsuranceRegistration;
import insurancecompany.view.register.insurances.CarInsuranceRegistration;
import insurancecompany.view.register.insurances.HomeInsuranceRegistration;
import insurancecompany.view.register.insurances.HolidayHomeInsuranceRegistration;
import insurancecompany.view.register.insurances.BoatInsuranceRegistration;
import insurancecompany.model.datastructures.*;
import javafx.stage.Stage;

/**
 *
 * @author André
 */
public class MainController {
    
    // Models:
    private EmployeeRegister employees;
    private CustomerRegister customers;
    private InsuranceRegister insurances;
    private ClaimRegister claims;
    
    // Insurance Registration Views:
    private BoatInsuranceRegistration boatInsuranceRegistration;
    private CarInsuranceRegistration carInsuranceRegistration;
    private CustomerRegistration customerRegistration;
    private HolidayHomeInsuranceRegistration holidayHomeInsuranceRegistration;
    private HomeInsuranceRegistration homeInsuranceRegistration;
    private TravelInsuranceRegistration travelInsuranceRegistration;
    
    // Controllers:
    private ModelController modelController;
    private ViewController viewController;
    
    public MainController()  {
        
        this.employees = new EmployeeRegister();
        this.customers = new CustomerRegister();
        this.insurances = new InsuranceRegister();
        this.claims = new ClaimRegister();
        
        this.boatInsuranceRegistration = new BoatInsuranceRegistration();
        this.carInsuranceRegistration = new CarInsuranceRegistration();
        this.holidayHomeInsuranceRegistration = new HolidayHomeInsuranceRegistration();
        this.homeInsuranceRegistration = new HomeInsuranceRegistration();
        this.travelInsuranceRegistration = new TravelInsuranceRegistration();
        
        this.modelController = new ModelController(boatInsuranceRegistration, 
            carInsuranceRegistration, holidayHomeInsuranceRegistration,
            homeInsuranceRegistration, travelInsuranceRegistration);
        this.viewController = new ViewController(boatInsuranceRegistration, 
            carInsuranceRegistration, holidayHomeInsuranceRegistration,
            homeInsuranceRegistration, travelInsuranceRegistration);
    }
    
    public void show(Stage stage) {;
        viewController.show(stage);
    }
}