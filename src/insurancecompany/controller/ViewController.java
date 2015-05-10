/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package insurancecompany.controller;

import insurancecompany.view.*;
import insurancecompany.view.modules.*;
import insurancecompany.view.register.*;
import insurancecompany.view.register.insurances.*;
import insurancecompany.view.register.persons.*;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 *
 * @author Sindre
 */
public class ViewController {
    
    // Module Views
    private AdminView adminView;
    private CaseWorkerView caseWorkerView;
    private CustomerView customerView;
    private LoginView loginView;
    
    // Tab Views
    private RegisterView registerView;
    private SearchView searchView;
    private StatisticsView statisticsView;
    
    // Sub-Tab Views
    private ClaimsView claimsView;
    private InsurancesView insurancesView;
    private PersonsView personsView;
    
    // Insurance Registration Views
    private BoatInsuranceRegistration boatInsuranceRegistration;
    private CarInsuranceRegistration carInsuranceRegistration;
    private CustomerRegistration customerRegistration;
    private HolidayHomeInsuranceRegistration holidayHomeInsuranceRegistration;
    private HomeInsuranceRegistration homeInsuranceRegistration;
    private TravelInsuranceRegistration travelInsuranceRegistration;
    
    // Constructor
    public ViewController(BoatInsuranceRegistration boatInsuranceRegistration, 
            CarInsuranceRegistration carInsuranceRegistration, 
            HolidayHomeInsuranceRegistration holidayHomeInsuranceRegistration,
            HomeInsuranceRegistration homeInsuranceRegistration,
            TravelInsuranceRegistration travelInsuranceRegistration) {
        
        // Initializes Module Views
        this.adminView = new AdminView();
        this.caseWorkerView = new CaseWorkerView();
        this.customerView = new CustomerView();
        this.loginView = new LoginView();
        
        // Initializes Tab Views
        this.registerView = new RegisterView();
        this.searchView = new SearchView();
        this.statisticsView = new StatisticsView();
        
        // Initializes Sub-Tab Views
        this.claimsView = new ClaimsView();
        this.insurancesView = new InsurancesView();
        this.personsView = new PersonsView();
        
        // Initializes Registration Views
        this.boatInsuranceRegistration = boatInsuranceRegistration;
        this.carInsuranceRegistration = carInsuranceRegistration;
        this.holidayHomeInsuranceRegistration = holidayHomeInsuranceRegistration;
        this.homeInsuranceRegistration = homeInsuranceRegistration;
        this.travelInsuranceRegistration = travelInsuranceRegistration;
        
        initializeEventHandlers();
    }
    
    public void initializeEventHandlers() {
        adminView.setRegisterButtonEventHandler(this::adminViewRegisterTabButtonEventHandler);
        adminView.setExitButtonEventHandler(this::adminViewExitButtonEventHandler);
        registerView.setInsurancesButtonEventHandler(this::registerViewInsurancesButtonEventHandler);
        registerView.setPersonsButtonEventHandler(this::registerViewPersonsButtonEventHandler);
        insurancesView.setBoatInsuranceButtonEventHandler(this::insurancesViewBoatInsuranceButtonEventHandler);
        insurancesView.setCarInsuranceButtonEventHandler(this::insurancesViewCarInsuranceButtonEventHandler);
        insurancesView.setHolidayHomeInsuranceButtonEventHandler(this::insurancesViewHolidayHomeInsuranceButtonEventHandler);
        insurancesView.setHomeInsuranceButtonEventHandler(this::insurancesViewHomeInsuranceButtonEventHandler);
        insurancesView.setTravelInsuranceButtonEventHandler(this::insurancesViewTravelInsuranceButtonEventHandler);
    }
    
    public void show(Stage stage) {
        //login.show(stage);
        adminView.show(stage);
    }
    
    // ADMIN VIEW EVENT HANDLERS
    
    private void adminViewRegisterTabButtonEventHandler(ActionEvent event) {
        adminView.getMainPane().setCenter(registerView.getMainPane());
        adminView.selectedButtonStyleUpper((Button) event.getSource());
    }
    
    private void adminViewExitButtonEventHandler(ActionEvent event) {
        Platform.exit();
    }
    
    // REGISTER VIEW EVENT HANDLERS
    
    private void registerViewPersonsButtonEventHandler(ActionEvent event) {
        registerView.getMainPane().setCenter(personsView.getMainPane());
        registerView.selectedButtonStyleLower((Button) event.getSource());
    }
    
    private void registerViewInsurancesButtonEventHandler(ActionEvent event) {
        registerView.getMainPane().setCenter(insurancesView.getMainPane());
        registerView.selectedButtonStyleLower((Button) event.getSource());
    }
    
    // INSURANCES VIEW EVENT HANDLERS
    
    private void insurancesViewBoatInsuranceButtonEventHandler(ActionEvent event) {
        insurancesView.getMainPane().setCenter(boatInsuranceRegistration.getMainPane());
        insurancesView.selectedButtonStyleLower((Button) event.getSource());
    }
    
    private void insurancesViewCarInsuranceButtonEventHandler(ActionEvent event) {
        //insurancesView.getMainPane().setCenter(carInsuranceRegistration.getMainPane());
        insurancesView.selectedButtonStyleLower((Button) event.getSource());
    }
    
    private void insurancesViewHomeInsuranceButtonEventHandler(ActionEvent event) {
        insurancesView.getMainPane().setCenter(homeInsuranceRegistration.getMainPane());
        insurancesView.selectedButtonStyleLower((Button) event.getSource());
    }
    
    private void insurancesViewHolidayHomeInsuranceButtonEventHandler(ActionEvent event) {
        insurancesView.getMainPane().setCenter(holidayHomeInsuranceRegistration.getMainPane());
        insurancesView.selectedButtonStyleLower((Button) event.getSource());
    }
    
    private void insurancesViewTravelInsuranceButtonEventHandler(ActionEvent event) {
        insurancesView.getMainPane().setCenter(travelInsuranceRegistration.getMainPane());
        insurancesView.selectedButtonStyleLower((Button) event.getSource());
    }
}