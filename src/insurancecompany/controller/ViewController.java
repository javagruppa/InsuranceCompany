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
import insurancecompany.view.search.*;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * View Controller. This class connects all the views together.
 * @author André
 * @author Sindre
 */
public class ViewController {
    
    private Stage primaryStage;
    
    // Module Views
    private AdminView adminView;
    private CaseWorkerView caseWorkerView;
    private CustomerView customerView;
    private LoginView loginView;
    
    // Tab Views
    private RegisterView registerView;
    private SearchView searchView;
    private StatisticsView statisticsView;
    
    // Sub-Tab Views for RegisterView
    private ClaimsView claimsView;
    private InsurancesView insurancesView;
    private PersonsView personsView;
    
    // Sub-Tab Views for SearchView
    private CustomerSearchView customerSearchView;
    private EmployeeSearchView employeeSearchView;
    private InsuranceSearchView insuranceSearchView;
    private ClaimSearchView claimsSearchView;
    
    // Sub-Tab Views for PersonsView
    
    // Insurance Registration Views
    private BoatInsuranceRegistration boatInsuranceRegistration;
    private CarInsuranceRegistration carInsuranceRegistration;
    private CustomerRegistration customerRegistration;
    private EmployeeRegistration employeeRegistration;
    private HolidayHomeInsuranceRegistration holidayHomeInsuranceRegistration;
    private HomeInsuranceRegistration homeInsuranceRegistration;
    private TravelInsuranceRegistration travelInsuranceRegistration;
    
    // Constructor
    public ViewController(AdminView adminView, BoatInsuranceRegistration boatInsuranceRegistration, 
            CarInsuranceRegistration carInsuranceRegistration, 
            HolidayHomeInsuranceRegistration holidayHomeInsuranceRegistration,
            HomeInsuranceRegistration homeInsuranceRegistration,
            TravelInsuranceRegistration travelInsuranceRegistration,
            CustomerRegistration customerRegistration,
            EmployeeRegistration employeeRegistration) {
        
        // Initializes Module Views
        this.adminView = adminView;
        this.caseWorkerView = new CaseWorkerView();
        this.customerView = new CustomerView();
        this.loginView = new LoginView();
        
        // Initializes Tab Views
        this.registerView = new RegisterView();
        this.searchView = new SearchView();
        this.statisticsView = new StatisticsView();
        
        // Initializes Sub-Tab Views for RegisterView
        this.claimsView = new ClaimsView();
        this.insurancesView = new InsurancesView();
        this.personsView = new PersonsView();
        
        // Initializes Sub-Tab Views for SearchView
        this.customerSearchView = new CustomerSearchView();
        this.employeeSearchView = new EmployeeSearchView();
        this.insuranceSearchView = new InsuranceSearchView();
        this.claimsSearchView = new ClaimSearchView();
        
        // Initializes Registration Views
        this.boatInsuranceRegistration = boatInsuranceRegistration;
        this.carInsuranceRegistration = carInsuranceRegistration;
        this.holidayHomeInsuranceRegistration = holidayHomeInsuranceRegistration;
        this.homeInsuranceRegistration = homeInsuranceRegistration;
        this.travelInsuranceRegistration = travelInsuranceRegistration;
        
        this.customerRegistration = customerRegistration;
        this.employeeRegistration = employeeRegistration; 
        
        initializeEventHandlers();
    }
    
    public void initializeEventHandlers() {
        adminView.setRegisterButtonEventHandler(this::adminViewRegisterTabButtonEventHandler);
        adminView.setSearchButtonEventHandler(this::adminViewSearchTabButtonEventHandler);
        adminView.setToolbarOnMouseClickedEventHandler(this::adminViewToolbarMouseClickedEventHandler);
        adminView.setToolbarOnMouseDraggedEventHandler(this::adminViewToolbarMouseDraggedEventHandler);
        adminView.setToolbarOnMousePressedEventHandler(this::adminViewToolbarMousePressedEventHandler);
        registerView.setInsurancesButtonEventHandler(this::registerViewInsurancesButtonEventHandler);
        registerView.setPersonsButtonEventHandler(this::registerViewPersonsButtonEventHandler);
        registerView.setClaimsButtonEventHandler(this::registerViewClaimsButtonEventHandler);
        insurancesView.setBoatInsuranceButtonEventHandler(this::insurancesViewBoatInsuranceButtonEventHandler);
        insurancesView.setCarInsuranceButtonEventHandler(this::insurancesViewCarInsuranceButtonEventHandler);
        insurancesView.setHolidayHomeInsuranceButtonEventHandler(this::insurancesViewHolidayHomeInsuranceButtonEventHandler);
        insurancesView.setHomeInsuranceButtonEventHandler(this::insurancesViewHomeInsuranceButtonEventHandler);
        insurancesView.setTravelInsuranceButtonEventHandler(this::insurancesViewTravelInsuranceButtonEventHandler);
        personsView.setCustomerButtonEventHandler(this::personsViewCustomerRegistrationButtonEventHandler);
        personsView.setEmployeeButtonEventHandler(this::personsViewEmployeeRegistrationButtonEventHandler);
        searchView.setClaimsButtonEventHandler(this::searchViewClaimsButtonEventHandler);
        //searchView.setCustomersButtonEventHandler(null);
        //searchView.setEmployeesButtonEventHandler(null);
        //searchView.setInsurancesButtonEventHandler(null);
        
    }
    
    public void show(Stage stage) {
        this.primaryStage = stage;
        //login.show(stage);
        
        adminView.show(primaryStage);
    }
    
    // ADMIN VIEW EVENT HANDLERS
    
    private void adminViewRegisterTabButtonEventHandler(ActionEvent event) {
        adminView.getMainPane().setCenter(registerView.getMainPane());
        adminView.selectedButtonStyleUpper((Button) event.getSource());
    }
    
    private void adminViewSearchTabButtonEventHandler(ActionEvent event) {
        adminView.getMainPane().setCenter(searchView.getMainPane());
        adminView.selectedButtonStyleUpper((Button) event.getSource());
    }
    
    private void adminViewToolbarMouseClickedEventHandler(MouseEvent event) {
        // Switches the view between window mode and fullscreen by double clicks:
        if(event.getButton().equals(MouseButton.PRIMARY)){
            if(event.getClickCount() == 2){
                if (primaryStage.isFullScreen()) {
                    primaryStage.setFullScreen(false);
                } else if (!primaryStage.isFullScreen()) {
                primaryStage.setFullScreen(true);
                }
            }
        }  
    }
    
    private void adminViewToolbarMousePressedEventHandler(MouseEvent event) {
        // Used to move the window around:
        if (!primaryStage.isFullScreen()) {
            adminView.setxOffset(event.getSceneX());
            adminView.setyOffset(event.getSceneY());
        }       
    }
    
    private void adminViewToolbarMouseDraggedEventHandler(MouseEvent event) {
        // Used to move the window around:
        if (!primaryStage.isFullScreen()) {
            primaryStage.setX(event.getScreenX() - adminView.getxOffset());
            primaryStage.setY(event.getScreenY() - adminView.getyOffset());
        }
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
    
    private void registerViewClaimsButtonEventHandler(ActionEvent event) {
        registerView.getMainPane().setCenter(claimsView.getMainPane());
        registerView.selectedButtonStyleLower((Button) event.getSource());
    }
    
    // REGISTER INSURANCES VIEW EVENT HANDLERS
    
    private void insurancesViewBoatInsuranceButtonEventHandler(ActionEvent event) {
        insurancesView.getMainPane().setCenter(boatInsuranceRegistration.getMainPane());
        insurancesView.selectedButtonStyleLower((Button) event.getSource());
    }
    
    private void insurancesViewCarInsuranceButtonEventHandler(ActionEvent event) {
        insurancesView.getMainPane().setCenter(carInsuranceRegistration.getMainPane());
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
    
    // REGISTER PERSONS VIEW INSURANCEHANDLER
    
    private void personsViewCustomerRegistrationButtonEventHandler(ActionEvent event) {
        personsView.getMainPane().setCenter(customerRegistration.getMainPane());
        personsView.selectedButtonStyleLower((Button) event.getSource());
    }
    
    private void personsViewEmployeeRegistrationButtonEventHandler(ActionEvent event) {
        personsView.getMainPane().setCenter(employeeRegistration.getMainPane());
        personsView.selectedButtonStyleLower((Button) event.getSource());
    }
    
    
    // SEARCH CLAIMS VIEW EVENT HANDLERS
    
    private void searchViewClaimsButtonEventHandler(ActionEvent event) {
        searchView.getMainPane().setCenter(claimsSearchView.getMainPane());
        searchView.selectedButtonStyleLower((Button) event.getSource());
    }
}
