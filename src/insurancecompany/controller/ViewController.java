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
    private StatisticsIncome statisticsIncome;
    private StatisticsDisbursements statisticsDisbursements;
    
    
    // Constructor
    public ViewController(MainView mainView, LoginView loginView, ArrayList<Object> views) {
        
        // Initializes Module Views
        this.mainView = mainView;
        this.loginView = loginView;
        
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
        this.statisticsDisbursements = (StatisticsDisbursements) views.get(20);
        this.statisticsIncome = (StatisticsIncome) views.get(21);
        
        initializeEventHandlers();
    } // end of sole constructor
    
    public void setStage(Stage stage) {
        this.primaryStage = stage;
        // empty
    }
    
    private void initializeEventHandlers() {
        mainView.setRegisterButtonEventHandler(this::mainViewRegisterTabButtonEventHandler);
        mainView.setSearchButtonEventHandler(this::mainViewSearchTabButtonEventHandler);
        mainView.setStatisticsButtonEventHandler(this::adminViewStatisticsTabButtonEventHandler);
        mainView.setToolbarOnMouseClickedEventHandler(this::mainViewToolbarMouseClickedEventHandler);
        mainView.setToolbarOnMouseDraggedEventHandler(this::adminViewToolbarMouseDraggedEventHandler);
        mainView.setToolbarOnMousePressedEventHandler(this::mainViewToolbarMousePressedEventHandler);
        registerView.setInsurancesButtonEventHandler(this::registerViewInsurancesButtonEventHandler);
        registerView.setPersonsButtonEventHandler(this::registerViewPersonsButtonEventHandler);
        registerView.setClaimsButtonEventHandler(this::registerViewClaimsButtonEventHandler);
       
        // REGISTER SUB TAB CONTENT EVENT HANDLERS:
        registerPersons.setCustomerButtonEventHandler(this::registerPersonsCustomerButtonEventHandler);
        registerPersons.setEmployeeButtonEventHandler(this::registerPersonsEmployeeButtonEventHandler);
        registerInsurances.setBoatInsuranceButtonEventHandler(this::registerInsurancesBoatInsuranceButtonEventHandler);
        registerInsurances.setCarInsuranceButtonEventHandler(this::registerInsurancesCarInsuranceButtonEventHandler);
        registerInsurances.setHolidayHomeInsuranceButtonEventHandler(this::registerInsurancesHolidayHomeInsuranceButtonEventHandler);
        registerInsurances.setHolidayHomeContentInsuranceButtonEventHandler(this::registerInsurancesHolidayHomeContentInsuranceButtonEventHandler);
        registerInsurances.setHomeInsuranceButtonEventHandler(this::registerInsurancesHomeInsuranceButtonEventHandler);
        registerInsurances.setHomeContentInsuranceButtonEventHandler(this::registerInsurancesHomeContentInsuranceButtonEventHandler);
        registerInsurances.setTravelInsuranceButtonEventHandler(this::registerInsurancesTravelInsuranceButtonEventHandler);
        registerClaims.setBoatClaimButtonEventHandler(this::registerClaimsBoatClaimButtonEventHandler);
        registerClaims.setCarClaimButtonEventHandler(this::registerClaimsCarClaimButtonEventHandler);
        registerClaims.setHolidayHomeClaimButtonEventHandler(this::registerClaimsHolidayHomeClaimButtonEventHandler);
        registerClaims.setHolidayHomeContentClaimButtonEventHandler(this::registerClaimsHolidayHomeContentClaimButtonEventHandler);
        registerClaims.setHomeClaimButtonEventHandler(this::registerClaimsHomeClaimButtonEventHandler);
        registerClaims.setHomeContentClaimButtonEventHandler(this::registerClaimsHomeContentClaimButtonEventHandler);
        registerClaims.setTravelClaimButtonEventHandler(this::registerClaimsTravelClaimButtonEventHandler);
        
        // SEARCH SUB TAB EVENT HANDLERS:
        searchView.setClaimsButtonEventHandler(this::searchViewClaimsButtonEventHandler);
        searchView.setCustomersButtonEventHandler(this::searchViewCustomersButtonEventHandler);
        searchView.setEmployeesButtonEventHandler(this::searchViewEmployeesButtonEventHandler);
        searchView.setInsurancesButtonEventHandler(this::searchViewInsurancesButtonEventHandler);
        
        // STATISTCS SUB TAB EVENT HANDLERS:
        statisticsView.setDisbursementsButtonEventHandler(this::statisticsViewDisbursementsButtonEventHandler);
        statisticsView.setIncomeButtonEventHandler(this::statisticsViewIncomeButtonEventHandler);
        
    } // end of method initializeEventHandlers
    
    /** 
     * Switches the view between window mode and fullscreen by double clicking.
     * 
     * @param event 
     */
    private void mainViewToolbarMouseClickedEventHandler(MouseEvent event) {
        if(event.getButton().equals(MouseButton.PRIMARY)){
            if(event.getClickCount() == 2){
                if (primaryStage.isFullScreen()) {
                    primaryStage.setFullScreen(false);
                } else if (!primaryStage.isFullScreen()) {
                primaryStage.setFullScreen(true);
                }
            }
        }  
    } // end of method mainViewToolbarMouseClickedEventHandler
    
    /**
     * Moves the window around.
     * 
     * @param event 
     */
    private void mainViewToolbarMousePressedEventHandler(MouseEvent event) {
        if (!primaryStage.isFullScreen()) {
            mainView.setxOffset(event.getSceneX());
            mainView.setyOffset(event.getSceneY());
        }       
    } // end of method mainViewToolbarMousePressedEventHandler
    
    /**
     * Moves the window around.
     * 
     * @param event 
     */
    private void adminViewToolbarMouseDraggedEventHandler(MouseEvent event) {
        if (!primaryStage.isFullScreen()) {
            primaryStage.setX(event.getScreenX() - mainView.getxOffset());
            primaryStage.setY(event.getScreenY() - mainView.getyOffset());
        }
    } // end of method adminViewToolbarMouseDraggedEventHandler
   
    /** EventHandler for registerTabButton in MainView. */
    private void mainViewRegisterTabButtonEventHandler(ActionEvent event) {
        mainView.getMainPane().setCenter(registerView.getMainPane());
    }
    
    /** EventHandler for searchTabButton in MainView. */
    private void mainViewSearchTabButtonEventHandler(ActionEvent event) {
        mainView.getMainPane().setCenter(searchView.getMainPane());
    }
    
    /** EventHandler for statisticsTabButton in MainView. */
    private void adminViewStatisticsTabButtonEventHandler(ActionEvent event) {
        mainView.getMainPane().setCenter(statisticsView.getMainPane());
    }
    
    /** EventHandler for personsButton in RegisterView. */
    private void registerViewPersonsButtonEventHandler(ActionEvent event) {
        registerView.getMainPane().setCenter(registerPersons.getMainPane());
    }
    
    /** EventHandler for insurancesButton in RegisterView. */
    private void registerViewInsurancesButtonEventHandler(ActionEvent event) {
        registerView.getMainPane().setCenter(registerInsurances.getMainPane());
    }
    
    /** EventHandler for claimsButton in RegisterView. */
    private void registerViewClaimsButtonEventHandler(ActionEvent event) {
        registerView.getMainPane().setCenter(registerClaims.getMainPane());
    }
    
    /** EventHandler for boatClaimButton in RegisterClaims. */
    private void registerClaimsBoatClaimButtonEventHandler(ActionEvent event) {
        registerClaims.getMainPane().setCenter(registerBoatClaim.getMainPane());
    }
    
    /** EventHandler for carClaimButton in RegisterClaims. */
    private void registerClaimsCarClaimButtonEventHandler(ActionEvent event) {
        registerClaims.getMainPane().setCenter(registerCarClaim.getMainPane());
    }
    
    /** EventHandler for holidayHomeClaimButton in RegisterClaims. */
    private void registerClaimsHolidayHomeClaimButtonEventHandler(ActionEvent event) {
        registerClaims.getMainPane().setCenter(registerHolidayHomeClaim.getMainPane());
    }
    
    /** EventHandler for holidayHomeContentClaimButton in RegisterClaims. */
    private void registerClaimsHolidayHomeContentClaimButtonEventHandler(ActionEvent event) {
        registerClaims.getMainPane().setCenter(registerHolidayHomeContentClaim.getMainPane());
    }
    
    /** EventHandler for homeClaimButton in RegisterClaims. */
    private void registerClaimsHomeClaimButtonEventHandler(ActionEvent event) {
        registerClaims.getMainPane().setCenter(registerHomeClaim.getMainPane());
    }
    
    /** EventHandler for homeContentClaimButton in RegisterClaims. */
    private void registerClaimsHomeContentClaimButtonEventHandler(ActionEvent event) {
        registerClaims.getMainPane().setCenter(registerHomeContentClaim.getMainPane());
    }
    
    /** EventHandler for travelClaimButton in RegisterClaims. */
    private void registerClaimsTravelClaimButtonEventHandler(ActionEvent event) {
        registerClaims.getMainPane().setCenter(registerTravelClaim.getMainPane());
    }
    
    /** EventHandler for boatInsuranceButton in RegisterInsurances. */
    private void registerInsurancesBoatInsuranceButtonEventHandler(ActionEvent event) {
        registerInsurances.getMainPane().setCenter(registerBoatInsurance.getMainPane());
    }
    
    /** EventHandler for carInsuranceButton in RegisterInsurances. */
    private void registerInsurancesCarInsuranceButtonEventHandler(ActionEvent event) {
        registerInsurances.getMainPane().setCenter(registerCarInsurance.getMainPane());
    }
    
    /** EventHandler for holidayHomeInsuranceButton in RegisterInsurances. */
    private void registerInsurancesHolidayHomeInsuranceButtonEventHandler(ActionEvent event) {
        registerInsurances.getMainPane().setCenter(registerHolidayHomeInsurance.getMainPane());
    }
    
    /** EventHandler for holidayHomeContentInsuranceButton in RegisterInsurances. */
    private void registerInsurancesHolidayHomeContentInsuranceButtonEventHandler(ActionEvent event) {
        registerInsurances.getMainPane().setCenter(registerHolidayHomeContentInsurance.getMainPane());
    }
    
    /** EventHandler for homeInsuranceButton in RegisterInsurances. */
    private void registerInsurancesHomeInsuranceButtonEventHandler(ActionEvent event) {
        registerInsurances.getMainPane().setCenter(registerHomeInsurance.getMainPane());
    }
    
    /** EventHandler for homeContentInsuranceButton in RegisterInsurances. */
    private void registerInsurancesHomeContentInsuranceButtonEventHandler(ActionEvent event) {
        registerInsurances.getMainPane().setCenter(registerHomeContentInsurance.getMainPane());
    }
    
    /** EventHandler for travelInsuranceButton in RegisterInsurances. */
    private void registerInsurancesTravelInsuranceButtonEventHandler(ActionEvent event) {
        registerInsurances.getMainPane().setCenter(registerTravelInsurance.getMainPane());
    }
    
    /** EventHandler for customerButton in RegisterPersons. */
    private void registerPersonsCustomerButtonEventHandler(ActionEvent event) {
        registerPersons.getMainPane().setCenter(registerCustomer.getMainPane());
    }
    
    /** EventHandler for employeeButton in RegisterPersons. */
    private void registerPersonsEmployeeButtonEventHandler(ActionEvent event) {
        registerPersons.getMainPane().setCenter(registerEmployee.getMainPane());
    }
    
    /** EventHandler for claimsButton in SearchView. */
    private void searchViewClaimsButtonEventHandler(ActionEvent event) {
        searchView.getMainPane().setCenter(searchClaims.getMainPane());
    }
    
    /** EventHandler for insurancesButton in SearchView. */
    private void searchViewInsurancesButtonEventHandler(ActionEvent event) {
        searchView.getMainPane().setCenter(searchInsurances.getMainPane());
    }
    
    /** EventHandler for customersButton in SearchView. */
    private void searchViewCustomersButtonEventHandler(ActionEvent event) {
        searchView.getMainPane().setCenter(searchCustomers.getMainPane());
    }
    
    /** EventHandler for employeesButton in SearchView. */
    private void searchViewEmployeesButtonEventHandler(ActionEvent event) {
        searchView.getMainPane().setCenter(searchEmployees.getMainPane());
    }
    
    /** EventHandler for disbursementsButton in StatisticsView. */
    private void statisticsViewDisbursementsButtonEventHandler(ActionEvent event) {
        statisticsView.getMainPane().setCenter(statisticsDisbursements.getMainPane());
    }
    
    /** EventHandler for incomeButton in StatisticsView. */
    private void statisticsViewIncomeButtonEventHandler(ActionEvent event) {
        statisticsView.getMainPane().setCenter(statisticsIncome.getMainPane());
    }
}
