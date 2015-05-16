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
    private MainView mainView;
    private LoginView loginView;
    
    // Tab Views
    private RegisterView registerView;
    private SearchView searchView;
    private StatisticsView statisticsView;
    
    // Sub-Tab Views for RegisterView
    private RegisterClaims registerClaims;
    private RegisterInsurances registerInsurances;
    private RegisterPersons registerPersons;
  
    // Claim Registration Views:
    private RegisterBoatClaim registerBoatClaim;
    private RegisterCarClaim registerCarClaim;
    private RegisterHolidayHomeClaim registerHolidayHomeClaim;
    private RegisterHolidayHomeContentClaim registerHolidayHomeContentClaim;
    private RegisterHomeClaim registerHomeClaim;
    private RegisterHomeContentClaim registerHomeContentClaim;
    private RegisterTravelClaim registerTravelClaim;
    
    // Insurance Registration Views:
    private RegisterBoatInsurance registerBoatInsurance;
    private RegisterCarInsurance registerCarInsurance;
    private RegisterHolidayHomeInsurance registerHolidayHomeInsurance;
    private RegisterHolidayHomeContentInsurance registerHolidayHomeContentInsurance;
    private RegisterHomeInsurance registerHomeInsurance;
    private RegisterHomeContentInsurance registerHomeContentInsurance;
    private RegisterTravelInsurance registerTravelInsurance;
    
    // Person Registration Views:
    private RegisterCustomer registerCustomer;
    private RegisterEmployee registerEmployee;
    
    // Search Views:
    private SearchClaims searchClaims;
    private SearchCustomers searchCustomers;
    private SearchEmployees searchEmployees;
    private SearchInsurances searchInsurances;
    
    // Statistics Views:
    private StatisticsClaims statisticsClaims;
    private StatisticsIncome statisticsIncome;
    private StatisticsDisbursements statisticsDisbursements;
    
    
    // Constructor
    public ViewController(MainView mainView, ArrayList<Object> views) {
        
        // Initializes Module Views
        this.mainView = mainView;
        this.loginView = new LoginView();
        
        // Initializes Tab Views
        this.registerView = new RegisterView();
        this.searchView = new SearchView();
        this.statisticsView = new StatisticsView();
        
        // Initializes Sub-Tab Views for ProcessView
        this.registerClaims = new RegisterClaims();
        this.registerInsurances = new RegisterInsurances();
        this.registerPersons = new RegisterPersons();
        
        // Claim Registration Views:
        this.registerBoatClaim = (RegisterBoatClaim) views.get(0);
        this.registerCarClaim = (RegisterCarClaim) views.get(1);
        this.registerHolidayHomeClaim = (RegisterHolidayHomeClaim) views.get(2);
        this.registerHolidayHomeContentClaim = (RegisterHolidayHomeContentClaim) views.get(3);
        this.registerHomeClaim = (RegisterHomeClaim) views.get(4);
        this.registerHomeContentClaim = (RegisterHomeContentClaim) views.get(5);
        this.registerTravelClaim = (RegisterTravelClaim) views.get(6);
        
        // Insurance Registration Views:
        this.registerBoatInsurance = (RegisterBoatInsurance) views.get(7);
        this.registerCarInsurance = (RegisterCarInsurance) views.get(8);
        this.registerHolidayHomeInsurance = (RegisterHolidayHomeInsurance) views.get(9);
        this.registerHolidayHomeContentInsurance = (RegisterHolidayHomeContentInsurance) views.get(10);
        this.registerHomeInsurance = (RegisterHomeInsurance) views.get(11);
        this.registerHomeContentInsurance = (RegisterHomeContentInsurance) views.get(12);
        this.registerTravelInsurance = (RegisterTravelInsurance) views.get(13);
        
        // Person Registration Views:
        this.registerCustomer = (RegisterCustomer) views.get(14);
        this.registerEmployee = (RegisterEmployee) views.get(15);
    
        // Search Views:
        this.searchClaims = (SearchClaims) views.get(16);
        this.searchCustomers = (SearchCustomers) views.get(17);
        this.searchEmployees = (SearchEmployees) views.get(18);
        this.searchInsurances = (SearchInsurances) views.get(19);

        // Statistics Views:
        this.statisticsClaims = (StatisticsClaims) views.get(20);
        this.statisticsIncome = (StatisticsIncome) views.get(21);
        this.statisticsDisbursements = (StatisticsDisbursements) views.get(22);
        
        initializeEventHandlers();
    } // end of sole constructor
    
    public void show(Stage stage) {
        this.primaryStage = stage;
        //login.show(stage);
        //CarClaimFormView car = new CarClaimFormView();
        //car.show(stage);
        mainView.show(primaryStage);
    }
    
    public void initializeEventHandlers() {
        mainView.setRegisterButtonEventHandler(this::adminViewRegisterTabButtonEventHandler);
        mainView.setSearchButtonEventHandler(this::adminViewSearchTabButtonEventHandler);
        mainView.setStatisticsButtonEventHandler(this::adminViewStatisticsTabButtonEventHandler);
        mainView.setToolbarOnMouseClickedEventHandler(this::adminViewToolbarMouseClickedEventHandler);
        mainView.setToolbarOnMouseDraggedEventHandler(this::adminViewToolbarMouseDraggedEventHandler);
        mainView.setToolbarOnMousePressedEventHandler(this::adminViewToolbarMousePressedEventHandler);
        registerView.setInsurancesButtonEventHandler(this::registerViewInsurancesButtonEventHandler);
        registerView.setPersonsButtonEventHandler(this::registerViewPersonsButtonEventHandler);
        registerView.setClaimsButtonEventHandler(this::registerViewClaimsButtonEventHandler);
       
        // REGISTER SUB TAB CONTENT EVENT HANDLERS:
        registerPersons.setCustomerButtonEventHandler(this::personsViewCustomerRegistrationButtonEventHandler);
        registerPersons.setEmployeeButtonEventHandler(this::personsViewEmployeeRegistrationButtonEventHandler);
        registerInsurances.setBoatInsuranceButtonEventHandler(this::insurancesViewBoatInsuranceButtonEventHandler);
        registerInsurances.setCarInsuranceButtonEventHandler(this::insurancesViewCarInsuranceButtonEventHandler);
        registerInsurances.setHolidayHomeInsuranceButtonEventHandler(this::insurancesViewHolidayHomeInsuranceButtonEventHandler);
        registerInsurances.setHolidayHomeContentInsuranceButtonEventHandler(this::insurancesViewHolidayHomeContentInsuranceButtonEventHandler);
        registerInsurances.setHomeInsuranceButtonEventHandler(this::insurancesViewHomeInsuranceButtonEventHandler);
        registerInsurances.setHomeContentInsuranceButtonEventHandler(this::insurancesViewHomeContentInsuranceButtonEventHandler);
        registerInsurances.setTravelInsuranceButtonEventHandler(this::insurancesViewTravelInsuranceButtonEventHandler);
        registerClaims.setBoatClaimButtonEventHandler(this::claimsViewBoatClaimButtonEventHandler);
        registerClaims.setCarClaimButtonEventHandler(this::claimsViewCarClaimButtonEventHandler);
        registerClaims.setHolidayHomeClaimButtonEventHandler(this::claimsViewHolidayHomeClaimButtonEventHandler);
        registerClaims.setHolidayHomeContentClaimButtonEventHandler(this::claimsViewHolidayHomeContentClaimButtonEventHandler);
        registerClaims.setHomeClaimButtonEventHandler(this::claimsViewHomeClaimButtonEventHandler);
        registerClaims.setHomeContentClaimButtonEventHandler(this::claimsViewHomeContentClaimButtonEventHandler);
        registerClaims.setTravelClaimButtonEventHandler(this::claimsViewTravelClaimButtonEventHandler);
        
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
        mainView.getMainPane().setCenter(registerView.getMainPane());
    }
    
    private void adminViewSearchTabButtonEventHandler(ActionEvent event) {
        mainView.getMainPane().setCenter(searchView.getMainPane());
    }
    
    private void adminViewStatisticsTabButtonEventHandler(ActionEvent event) {
        mainView.getMainPane().setCenter(statisticsView.getMainPane());
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
            mainView.setxOffset(event.getSceneX());
            mainView.setyOffset(event.getSceneY());
        }       
    }
    
    private void adminViewToolbarMouseDraggedEventHandler(MouseEvent event) {
        // Used to move the window around:
        if (!primaryStage.isFullScreen()) {
            primaryStage.setX(event.getScreenX() - mainView.getxOffset());
            primaryStage.setY(event.getScreenY() - mainView.getyOffset());
        }
    }
    
    // REGISTER VIEW EVENT HANDLERS
    
    private void registerViewPersonsButtonEventHandler(ActionEvent event) {
        registerView.getMainPane().setCenter(registerPersons.getMainPane());
    }
    
    private void registerViewInsurancesButtonEventHandler(ActionEvent event) {
        registerView.getMainPane().setCenter(registerInsurances.getMainPane());
    }
    
    private void registerViewClaimsButtonEventHandler(ActionEvent event) {
        registerView.getMainPane().setCenter(registerClaims.getMainPane());
    }
    
    // REGISTER CLAIMS VIEW EVENT HANDLERS
    
    private void claimsViewBoatClaimButtonEventHandler(ActionEvent event) {
        registerClaims.getMainPane().setCenter(registerBoatClaim.getMainPane());
    }
    
    private void claimsViewCarClaimButtonEventHandler(ActionEvent event) {
        registerClaims.getMainPane().setCenter(registerCarClaim.getMainPane());
    }
    
    private void claimsViewHomeClaimButtonEventHandler(ActionEvent event) {
        registerClaims.getMainPane().setCenter(registerHomeClaim.getMainPane());
    }
    
    private void claimsViewHomeContentClaimButtonEventHandler(ActionEvent event) {
        registerClaims.getMainPane().setCenter(registerHomeContentClaim.getMainPane());
    }

    private void claimsViewHolidayHomeClaimButtonEventHandler(ActionEvent event) {
        registerClaims.getMainPane().setCenter(registerHolidayHomeClaim.getMainPane());
    }
        
    private void claimsViewHolidayHomeContentClaimButtonEventHandler(ActionEvent event) {
        registerClaims.getMainPane().setCenter(registerHolidayHomeContentClaim.getMainPane());
    }
    
    private void claimsViewTravelClaimButtonEventHandler(ActionEvent event) {
        registerClaims.getMainPane().setCenter(registerTravelClaim.getMainPane());
    }
    
    // REGISTER INSURANCES VIEW EVENT HANDLERS
    
    private void insurancesViewBoatInsuranceButtonEventHandler(ActionEvent event) {
        registerInsurances.getMainPane().setCenter(registerBoatInsurance.getMainPane());
    }
    
    private void insurancesViewCarInsuranceButtonEventHandler(ActionEvent event) {
        registerInsurances.getMainPane().setCenter(registerCarInsurance.getMainPane());
    }
    
    private void insurancesViewHomeInsuranceButtonEventHandler(ActionEvent event) {
        registerInsurances.getMainPane().setCenter(registerHomeInsurance.getMainPane());
    }
    
    private void insurancesViewHomeContentInsuranceButtonEventHandler(ActionEvent event) {
        registerInsurances.getMainPane().setCenter(registerHomeContentInsurance.getMainPane());
    }
    
    private void insurancesViewHolidayHomeInsuranceButtonEventHandler(ActionEvent event) {
        registerInsurances.getMainPane().setCenter(registerHolidayHomeInsurance.getMainPane());
    }
    
    private void insurancesViewHolidayHomeContentInsuranceButtonEventHandler(ActionEvent event) {
        registerInsurances.getMainPane().setCenter(registerHolidayHomeContentInsurance.getMainPane());
    }
 
    private void insurancesViewTravelInsuranceButtonEventHandler(ActionEvent event) {
        registerInsurances.getMainPane().setCenter(registerTravelInsurance.getMainPane());
    }
    
    // REGISTER PERSONS VIEW INSURANCEHANDLERS
    
    private void personsViewCustomerRegistrationButtonEventHandler(ActionEvent event) {
        registerPersons.getMainPane().setCenter(registerCustomer.getMainPane());
    }
    
    private void personsViewEmployeeRegistrationButtonEventHandler(ActionEvent event) {
        registerPersons.getMainPane().setCenter(registerEmployee.getMainPane());
    }
    
    // PROCESS VIEW EVENT HANDLERS
    
    private void processViewSubscriptionsButtonEventHandler(ActionEvent event) {
        
    }
    
    // SEARCH VIEW EVENT HANDLERS
    
    private void searchViewClaimsButtonEventHandler(ActionEvent event) {
        searchView.getMainPane().setCenter(registerClaims.getMainPane());
    }
    
    private void searchViewInsurancesButtonEventHandler(ActionEvent event) {
        searchView.getMainPane().setCenter(searchInsurances.getMainPane());
    }
    
    private void searchViewCustomersButtonEventHandler(ActionEvent event) {
        searchView.getMainPane().setCenter(searchCustomers.getMainPane());
    }
    
    private void searchViewEmployeesButtonEventHandler(ActionEvent event) {
        searchView.getMainPane().setCenter(searchEmployees.getMainPane());
    }
}
