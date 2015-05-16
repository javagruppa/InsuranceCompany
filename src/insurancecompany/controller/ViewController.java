/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package insurancecompany.controller;

import insurancecompany.view.MainView;
import insurancecompany.view.LoginView;
import insurancecompany.view.*;
import insurancecompany.view.register.*;
import insurancecompany.view.register.claims.*;
import insurancecompany.view.register.insurances.*;
import insurancecompany.view.register.persons.*;
import insurancecompany.view.search.*;
import insurancecompany.view.statistics.*;
import java.util.ArrayList;
import javafx.event.ActionEvent;
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
    private MainView adminView;
    private LoginView loginView;
    
    // Tab Views
    private RegisterView registerView;
    private SearchView searchView;
    private StatisticsView statisticsView;
    
    // Sub-Tab Views for RegisterView
    private RegisterClaims claimsView;
    private RegisterInsurances insurancesView;
    private RegisterPersons personsView;
  
    // Claim Registration Views:
    private RegisterBoatClaim boatClaimRegistration;
    private RegisterCarClaim carClaimRegistration;
    private RegisterHolidayHomeClaim holidayHomeClaimRegistration;
    private RegisterHolidayHomeContentClaim holidayHomeContentClaimRegistration;
    private RegisterHomeClaim homeClaimRegistration;
    private RegisterHomeContentClaim homeContentClaimRegistration;
    private RegisterTravelClaim travelClaimRegistration;
    
    // Insurance Registration Views:
    private RegisterBoatInsurance boatInsuranceRegistration;
    private RegisterCarInsurance carInsuranceRegistration;
    private RegisterHolidayHomeInsurance holidayHomeInsuranceRegistration;
    private RegisterHolidayHomeContentInsurance holidayHomeContentInsuranceRegistration;
    private RegisterHomeInsurance homeInsuranceRegistration;
    private RegisterHomeContentInsurance homeContentInsuranceRegistration;
    private RegisterTravelInsurance travelInsuranceRegistration;
    
    // Person Registration Views:
    private RegisterCustomer customerRegistration;
    private RegisterEmployee employeeRegistration;
    
    // Search Views:
    private SearchClaims claimSearchView;
    private SearchCustomers customerSearchView;
    private SearchEmployees employeeSearchView;
    private SearchInsurances insuranceSearchView;
    
    // Statistics Views:
    private StatisticsClaims claimStatisticsView;
    private StatisticsIncome customerStatisticsView;
    private StatisticsDisbursements employeeStatisticsView;
    
    
    // Constructor
    public ViewController(MainView adminView, ArrayList<Object> views) {
        
        // Initializes Module Views
        this.adminView = adminView;
        this.loginView = new LoginView();
        
        // Initializes Tab Views
        this.registerView = new RegisterView();
        this.searchView = new SearchView();
        this.statisticsView = new StatisticsView();
        
        // Initializes Sub-Tab Views for ProcessView
        this.claimsView = new RegisterClaims();
        this.insurancesView = new RegisterInsurances();
        this.personsView = new RegisterPersons();
        
        // Claim Registration Views:
        this.boatClaimRegistration = (RegisterBoatClaim) views.get(3);
        this.carClaimRegistration = (RegisterCarClaim) views.get(4);
        this.holidayHomeClaimRegistration = (RegisterHolidayHomeClaim) views.get(5);
        this.holidayHomeContentClaimRegistration = (RegisterHolidayHomeContentClaim) views.get(6);
        this.homeClaimRegistration = (RegisterHomeClaim) views.get(7);
        this.homeContentClaimRegistration = (RegisterHomeContentClaim) views.get(8);
        this.travelClaimRegistration = (RegisterTravelClaim) views.get(9);
        
        // Insurance Registration Views:
        this.boatInsuranceRegistration = (RegisterBoatInsurance) views.get(10);
        this.carInsuranceRegistration = (RegisterCarInsurance) views.get(11);
        this.holidayHomeInsuranceRegistration = (RegisterHolidayHomeInsurance) views.get(12);
        this.holidayHomeContentInsuranceRegistration = (RegisterHolidayHomeContentInsurance) views.get(13);
        this.homeInsuranceRegistration = (RegisterHomeInsurance) views.get(14);
        this.homeContentInsuranceRegistration = (RegisterHomeContentInsurance) views.get(15);
        this.travelInsuranceRegistration = (RegisterTravelInsurance) views.get(16);
        
        // Person Registration Views:
        this.customerRegistration = (RegisterCustomer) views.get(17);
        this.employeeRegistration = (RegisterEmployee) views.get(18);
    
        // Search Views:
        this.claimSearchView = (SearchClaims) views.get(19);
        this.customerSearchView = (SearchCustomers) views.get(20);
        this.employeeSearchView = (SearchEmployees) views.get(21);
        this.insuranceSearchView = (SearchInsurances) views.get(22);

        // Statistics Views:
        this.claimStatisticsView = (StatisticsClaims) views.get(23);
        this.customerStatisticsView = (StatisticsIncome) views.get(24);
        this.employeeStatisticsView = (StatisticsDisbursements) views.get(25);
        
        initializeEventHandlers();
    } // end of sole constructor
    
    public void show(Stage stage) {
        this.primaryStage = stage;
        //login.show(stage);
        //CarClaimFormView car = new CarClaimFormView();
        //car.show(stage);
        adminView.show(primaryStage);
    }
    
    public void initializeEventHandlers() {
        adminView.setRegisterButtonEventHandler(this::adminViewRegisterTabButtonEventHandler);
        adminView.setSearchButtonEventHandler(this::adminViewSearchTabButtonEventHandler);
        adminView.setStatisticsButtonEventHandler(this::adminViewStatisticsTabButtonEventHandler);
        adminView.setToolbarOnMouseClickedEventHandler(this::adminViewToolbarMouseClickedEventHandler);
        adminView.setToolbarOnMouseDraggedEventHandler(this::adminViewToolbarMouseDraggedEventHandler);
        adminView.setToolbarOnMousePressedEventHandler(this::adminViewToolbarMousePressedEventHandler);
        registerView.setInsurancesButtonEventHandler(this::registerViewInsurancesButtonEventHandler);
        registerView.setPersonsButtonEventHandler(this::registerViewPersonsButtonEventHandler);
        registerView.setClaimsButtonEventHandler(this::registerViewClaimsButtonEventHandler);
       
        // REGISTER SUB TAB CONTENT EVENT HANDLERS:
        personsView.setCustomerButtonEventHandler(this::personsViewCustomerRegistrationButtonEventHandler);
        personsView.setEmployeeButtonEventHandler(this::personsViewEmployeeRegistrationButtonEventHandler);
        insurancesView.setBoatInsuranceButtonEventHandler(this::insurancesViewBoatInsuranceButtonEventHandler);
        insurancesView.setCarInsuranceButtonEventHandler(this::insurancesViewCarInsuranceButtonEventHandler);
        insurancesView.setHolidayHomeInsuranceButtonEventHandler(this::insurancesViewHolidayHomeInsuranceButtonEventHandler);
        insurancesView.setHolidayHomeContentInsuranceButtonEventHandler(this::insurancesViewHolidayHomeContentInsuranceButtonEventHandler);
        insurancesView.setHomeInsuranceButtonEventHandler(this::insurancesViewHomeInsuranceButtonEventHandler);
        insurancesView.setHomeContentInsuranceButtonEventHandler(this::insurancesViewHomeContentInsuranceButtonEventHandler);
        insurancesView.setTravelInsuranceButtonEventHandler(this::insurancesViewTravelInsuranceButtonEventHandler);
        claimsView.setBoatClaimButtonEventHandler(this::claimsViewBoatClaimButtonEventHandler);
        claimsView.setCarClaimButtonEventHandler(this::claimsViewCarClaimButtonEventHandler);
        claimsView.setHolidayHomeClaimButtonEventHandler(this::claimsViewHolidayHomeClaimButtonEventHandler);
        claimsView.setHolidayHomeContentClaimButtonEventHandler(this::claimsViewHolidayHomeContentClaimButtonEventHandler);
        claimsView.setHomeClaimButtonEventHandler(this::claimsViewHomeClaimButtonEventHandler);
        claimsView.setHomeContentClaimButtonEventHandler(this::claimsViewHomeContentClaimButtonEventHandler);
        claimsView.setTravelClaimButtonEventHandler(this::claimsViewTravelClaimButtonEventHandler);
        
        // SEARCH SUB TAB EVENT HANDLERS:
        searchView.setClaimsButtonEventHandler(this::searchViewClaimsButtonEventHandler);
        searchView.setCustomersButtonEventHandler(this::searchViewCustomersButtonEventHandler);
        searchView.setEmployeesButtonEventHandler(this::searchViewEmployeesButtonEventHandler);
        searchView.setInsurancesButtonEventHandler(this::searchViewInsurancesButtonEventHandler);
        
        // STATISTCS SUB TAB EVENT HANDLERS:
        statisticsView.setClaimsButtonEventHandler(null);
        statisticsView.setCustomersButtonEventHandler(null);
        statisticsView.setEmployeesButtonEventHandler(null);
        statisticsView.setInsurancesButtonEventHandler(null);
        
    } // end of method initializeEventHandlers
   
    // ADMIN VIEW EVENT HANDLERS
    
    private void adminViewRegisterTabButtonEventHandler(ActionEvent event) {
        adminView.getMainPane().setCenter(registerView.getMainPane());
    }
    
    private void adminViewSearchTabButtonEventHandler(ActionEvent event) {
        adminView.getMainPane().setCenter(searchView.getMainPane());
    }
    
    private void adminViewStatisticsTabButtonEventHandler(ActionEvent event) {
        adminView.getMainPane().setCenter(statisticsView.getMainPane());
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
    } // end of method adminViewToolbarMouseClickedEventHandler
    
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
    }
    
    private void registerViewInsurancesButtonEventHandler(ActionEvent event) {
        registerView.getMainPane().setCenter(insurancesView.getMainPane());
    }
    
    private void registerViewClaimsButtonEventHandler(ActionEvent event) {
        registerView.getMainPane().setCenter(claimsView.getMainPane());
    }
    
    // REGISTER CLAIMS VIEW EVENT HANDLERS
    
    private void claimsViewBoatClaimButtonEventHandler(ActionEvent event) {
        claimsView.getMainPane().setCenter(boatClaimRegistration.getMainPane());
    }
    
    private void claimsViewCarClaimButtonEventHandler(ActionEvent event) {
        claimsView.getMainPane().setCenter(carClaimRegistration.getMainPane());
    }
    
    private void claimsViewHomeClaimButtonEventHandler(ActionEvent event) {
        claimsView.getMainPane().setCenter(homeClaimRegistration.getMainPane());
    }
    
    private void claimsViewHomeContentClaimButtonEventHandler(ActionEvent event) {
        claimsView.getMainPane().setCenter(homeContentClaimRegistration.getMainPane());
    }

    private void claimsViewHolidayHomeClaimButtonEventHandler(ActionEvent event) {
        claimsView.getMainPane().setCenter(holidayHomeClaimRegistration.getMainPane());
    }
        
    private void claimsViewHolidayHomeContentClaimButtonEventHandler(ActionEvent event) {
        claimsView.getMainPane().setCenter(holidayHomeContentClaimRegistration.getMainPane());
    }
    
    private void claimsViewTravelClaimButtonEventHandler(ActionEvent event) {
        claimsView.getMainPane().setCenter(travelClaimRegistration.getMainPane());
    }
    
    // REGISTER INSURANCES VIEW EVENT HANDLERS
    
    private void insurancesViewBoatInsuranceButtonEventHandler(ActionEvent event) {
        insurancesView.getMainPane().setCenter(boatInsuranceRegistration.getMainPane());
    }
    
    private void insurancesViewCarInsuranceButtonEventHandler(ActionEvent event) {
        insurancesView.getMainPane().setCenter(carInsuranceRegistration.getMainPane());
    }
    
    private void insurancesViewHomeInsuranceButtonEventHandler(ActionEvent event) {
        insurancesView.getMainPane().setCenter(homeInsuranceRegistration.getMainPane());
    }
    
    private void insurancesViewHomeContentInsuranceButtonEventHandler(ActionEvent event) {
        insurancesView.getMainPane().setCenter(homeContentInsuranceRegistration.getMainPane());
    }
    
    private void insurancesViewHolidayHomeInsuranceButtonEventHandler(ActionEvent event) {
        insurancesView.getMainPane().setCenter(holidayHomeInsuranceRegistration.getMainPane());
    }
    
    private void insurancesViewHolidayHomeContentInsuranceButtonEventHandler(ActionEvent event) {
        insurancesView.getMainPane().setCenter(holidayHomeContentInsuranceRegistration.getMainPane());
    }
 
    private void insurancesViewTravelInsuranceButtonEventHandler(ActionEvent event) {
        insurancesView.getMainPane().setCenter(travelInsuranceRegistration.getMainPane());
    }
    
    // REGISTER PERSONS VIEW INSURANCEHANDLERS
    
    private void personsViewCustomerRegistrationButtonEventHandler(ActionEvent event) {
        personsView.getMainPane().setCenter(customerRegistration.getMainPane());
    }
    
    private void personsViewEmployeeRegistrationButtonEventHandler(ActionEvent event) {
        personsView.getMainPane().setCenter(employeeRegistration.getMainPane());
    }
    
    // PROCESS VIEW EVENT HANDLERS
    
    private void processViewSubscriptionsButtonEventHandler(ActionEvent event) {
        
    }
    
    // SEARCH VIEW EVENT HANDLERS
    
    private void searchViewClaimsButtonEventHandler(ActionEvent event) {
        searchView.getMainPane().setCenter(claimSearchView.getMainPane());
    }
    
    private void searchViewInsurancesButtonEventHandler(ActionEvent event) {
        searchView.getMainPane().setCenter(insuranceSearchView.getMainPane());
    }
    
    private void searchViewCustomersButtonEventHandler(ActionEvent event) {
        searchView.getMainPane().setCenter(customerSearchView.getMainPane());
    }
    
    private void searchViewEmployeesButtonEventHandler(ActionEvent event) {
        searchView.getMainPane().setCenter(employeeSearchView.getMainPane());
    }
}
