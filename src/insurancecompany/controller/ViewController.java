/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package insurancecompany.controller;

import insurancecompany.view.*;
import insurancecompany.view.modules.*;
import insurancecompany.view.process.*;
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
    private AdminView adminView;
    private CaseWorkerView caseWorkerView;
    private CustomerView customerView;
    private LoginView loginView;
    
    // Tab Views
    private RegisterView registerView;
    private ProcessView processView;
    private SearchView searchView;
    private StatisticsView statisticsView;
    
    // Sub-Tab Views for RegisterView
    private ClaimsView claimsView;
    private InsurancesView insurancesView;
    private PersonsView personsView;
  
    // Process Views:
    private BillsProcessView billsProcessView;
    private ClaimsProcessView claimsProcessView;
    private SubscriptionsProcessView subscriptionsProcessView;
    
    // Claim Registration Views:
    private BoatClaimRegistration boatClaimRegistration;
    private CarClaimRegistration carClaimRegistration;
    private HolidayHomeClaimRegistration holidayHomeClaimRegistration;
    private HolidayHomeContentClaimRegistration holidayHomeContentClaimRegistration;
    private HomeClaimRegistration homeClaimRegistration;
    private HomeContentClaimRegistration homeContentClaimRegistration;
    private TravelClaimRegistration travelClaimRegistration;
    
    // Insurance Registration Views:
    private BoatInsuranceRegistration boatInsuranceRegistration;
    private CarInsuranceRegistration carInsuranceRegistration;
    private HolidayHomeInsuranceRegistration holidayHomeInsuranceRegistration;
    private HolidayHomeContentInsuranceRegistration holidayHomeContentInsuranceRegistration;
    private HomeInsuranceRegistration homeInsuranceRegistration;
    private HomeContentInsuranceRegistration homeContentInsuranceRegistration;
    private TravelInsuranceRegistration travelInsuranceRegistration;
    
    // Person Registration Views:
    private CustomerRegistration customerRegistration;
    private EmployeeRegistration employeeRegistration;
    
    // Search Views:
    private ClaimSearchView claimSearchView;
    private CustomerSearchView customerSearchView;
    private EmployeeSearchView employeeSearchView;
    private InsuranceSearchView insuranceSearchView;
    
    // Statistics Views:
    private ClaimStatisticsView claimStatisticsView;
    private CustomerStatisticsView customerStatisticsView;
    private EmployeeStatisticsView employeeStatisticsView;
    private InsuranceStatisticsView insuranceStatisticsView;
    
    
    // Constructor
    public ViewController(AdminView adminView, ArrayList<Object> views) {
        
        // Initializes Module Views
        this.adminView = adminView;
        this.caseWorkerView = new CaseWorkerView();
        this.customerView = new CustomerView();
        this.loginView = new LoginView();
        
        // Initializes Tab Views
        this.registerView = new RegisterView();
        this.processView = new ProcessView();
        this.searchView = new SearchView();
        this.statisticsView = new StatisticsView();
        
        // Initializes Sub-Tab Views for RegisterView
        this.billsProcessView = new BillsProcessView();
        this.claimsProcessView = new ClaimsProcessView();
        this.subscriptionsProcessView = new SubscriptionsProcessView();
        
        // Initializes Sub-Tab Views for ProcessView
        this.claimsView = new ClaimsView();
        this.insurancesView = new InsurancesView();
        this.personsView = new PersonsView();
        
        // Process Views:
        this.billsProcessView = (BillsProcessView) views.get(0);
        this.claimsProcessView = (ClaimsProcessView) views.get(1);
        this.subscriptionsProcessView = (SubscriptionsProcessView) views.get(2);
        
        // Claim Registration Views:
        this.boatClaimRegistration = (BoatClaimRegistration) views.get(3);
        this.carClaimRegistration = (CarClaimRegistration) views.get(4);
        this.holidayHomeClaimRegistration = (HolidayHomeClaimRegistration) views.get(5);
        this.holidayHomeContentClaimRegistration = (HolidayHomeContentClaimRegistration) views.get(6);
        this.homeClaimRegistration = (HomeClaimRegistration) views.get(7);
        this.homeContentClaimRegistration = (HomeContentClaimRegistration) views.get(8);
        this.travelClaimRegistration = (TravelClaimRegistration) views.get(9);
        
        // Insurance Registration Views:
        this.boatInsuranceRegistration = (BoatInsuranceRegistration) views.get(10);
        this.carInsuranceRegistration = (CarInsuranceRegistration) views.get(11);
        this.holidayHomeInsuranceRegistration = (HolidayHomeInsuranceRegistration) views.get(12);
        this.holidayHomeContentInsuranceRegistration = (HolidayHomeContentInsuranceRegistration) views.get(13);
        this.homeInsuranceRegistration = (HomeInsuranceRegistration) views.get(14);
        this.homeContentInsuranceRegistration = (HomeContentInsuranceRegistration) views.get(15);
        this.travelInsuranceRegistration = (TravelInsuranceRegistration) views.get(16);
        
        // Person Registration Views:
        this.customerRegistration = (CustomerRegistration) views.get(17);
        this.employeeRegistration = (EmployeeRegistration) views.get(18);
    
        // Search Views:
        this.claimSearchView = (ClaimSearchView) views.get(19);
        this.customerSearchView = (CustomerSearchView) views.get(20);
        this.employeeSearchView = (EmployeeSearchView) views.get(21);
        this.insuranceSearchView = (InsuranceSearchView) views.get(22);

        // Statistics Views:
        this.claimStatisticsView = (ClaimStatisticsView) views.get(23);
        this.customerStatisticsView = (CustomerStatisticsView) views.get(24);
        this.employeeStatisticsView = (EmployeeStatisticsView) views.get(25);
        this.insuranceStatisticsView = (InsuranceStatisticsView) views.get(26);
        
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
        adminView.setProcessButtonEventHandler(this::adminViewProcessTabButtonEventHandler);
        adminView.setSearchButtonEventHandler(this::adminViewSearchTabButtonEventHandler);
        adminView.setStatisticsButtonEventHandler(this::adminViewStatisticsTabButtonEventHandler);
        adminView.setToolbarOnMouseClickedEventHandler(this::adminViewToolbarMouseClickedEventHandler);
        adminView.setToolbarOnMouseDraggedEventHandler(this::adminViewToolbarMouseDraggedEventHandler);
        adminView.setToolbarOnMousePressedEventHandler(this::adminViewToolbarMousePressedEventHandler);
        registerView.setInsurancesButtonEventHandler(this::registerViewInsurancesButtonEventHandler);
        registerView.setPersonsButtonEventHandler(this::registerViewPersonsButtonEventHandler);
        registerView.setClaimsButtonEventHandler(this::registerViewClaimsButtonEventHandler);
        processView.setBillsButtonEventHandler(null);
        processView.setClaimsButtonEventHandler(null);
        processView.setSubscriptionsButtonEventHandler(null);
       
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
        
        // PROCESS SUB TAB EVENT HANDLERS:
        processView.setClaimsButtonEventHandler(null);
        processView.setBillsButtonEventHandler(null);
        processView.setSubscriptionsButtonEventHandler(null);
        
        // SEARCH SUB TAB EVENT HANDLERS:
        searchView.setClaimsButtonEventHandler(this::searchViewClaimsButtonEventHandler);
        searchView.setCustomersButtonEventHandler(null);
        searchView.setEmployeesButtonEventHandler(null);
        searchView.setInsurancesButtonEventHandler(null);
        
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
    
    private void adminViewProcessTabButtonEventHandler(ActionEvent event) {
        adminView.getMainPane().setCenter(processView.getMainPane());
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
    
    // SEARCH CLAIMS VIEW EVENT HANDLERS
    
    private void searchViewClaimsButtonEventHandler(ActionEvent event) {
        searchView.getMainPane().setCenter(claimSearchView.getMainPane());
    }
}
