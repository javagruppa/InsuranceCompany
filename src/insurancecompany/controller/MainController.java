/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package insurancecompany.controller;

import insurancecompany.view.MainView;
import insurancecompany.misc.ClaimType;
import insurancecompany.misc.EmployeeType;
import insurancecompany.misc.coverages.*;
import insurancecompany.misc.hometypes.*;
import insurancecompany.model.bills.*;
import insurancecompany.model.claims.*;
import insurancecompany.model.datastructures.*;
import insurancecompany.model.datastructures.carinfo.*;
import insurancecompany.model.insurances.*;
import insurancecompany.model.people.*;
import insurancecompany.model.properties.*;
import insurancecompany.model.vehicles.*;
import insurancecompany.view.register.claims.*;
import insurancecompany.view.register.insurances.*;
import insurancecompany.view.register.persons.*;
import insurancecompany.view.search.*;
import insurancecompany.view.statistics.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;

/**
 * Main controller. This class acts as a communication between the model and the view.
 * @author André
 */
public class MainController {
    
    /** Constant with a String value equal to the resources file path. */
    public static final String RES_FILE_PATH = "src/insurancecompany/resources/";
    
    /** Reference to the logged in user.*/
    private Person user;
    
    /** Primary Stage. */
    private Stage primaryStage;
    
    // Models:
    private BillRegister bills;
    private ClaimRegister claims;
    private CustomerRegister customers;
    private EmployeeRegister employees;
    private InsuranceRegister insurances;
    private LogRegister logs;
    
    // Modules:
    private MainView mainView;
    
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
    
    // Controllers:
    private ModelController modelController;
    private ViewController viewController;
    
    // Creates strings to be used in messages to the user:
    private static final String REGISTER_SUCCESS = "Registrering vellykket.";
    private static final String REGISTER_NO_SUCCESS = "Registrering ikke vellykket.";
    private static final String NO_CLAIM_MESSAGE = "Du har ikke valgt noen skademelding.";
    private static final String NO_CUSTOMER_MESSAGE = "Du har ikke valgt noen kunde.";
    private static final String NO_EMPLOYEE_MESSAGE = "Du har ikke valgt noen ansatt.";
    private static final String NO_INSURANCE_MESSAGE = "Du har ikke valgt noen forsikring.";
    private static final String FORMAT_MESSAGE = "Dette feltet kan kun bestå av tall.";
    private static final String EMPTY_MESSAGE = "Dette feltet må fylles ut.";
    private static final String CLAIMID_FORMAT_MESSAGE = "Skademeldingsnummeret kan kun bestå av tall.";
    private static final String CUSTOMERID_FORMAT_MESSAGE = "Kundenummeret kan kun bestå av tall.";
    private static final String CUSTOMERID_EMPTY_MESSAGE = "Du må skrive inn et kundenummer.";
    private static final String CUSTOMERID_NOT_FOUND_MESSAGE = "Fant ingen kunde med kundenummer: ";
    private static final String EMPLOYEEID_FORMAT_MESSAGE = "Ansattnummeret kan kun bestå av tall.";
    private static final String INSURANCEID_FORMAT_MESSAGE = "Forsikringsnummeret kan kun bestå av tall.";
    private static final String PERSONALNUMBER_EMPTY_MESSAGE = "Du må skrive inn et personnummer.";
    private static final String PERSONALNUMBER_INCORRECT_MESSAGE = "Et personnummer må bestå av 11 siffer.";
    private static final String PERSONALNUMBER_NOT_FOUND_MESSAGE = "Fant ingen kunde med personnummer: ";
    private static final String NO_DATE_MESSAGE = "Du har ikke valgt noen dato.";
    private static final String DESCRIPTION_EMPTY_MESSAGE = "Du må skrive inn en beskrivelse.";
    
    public MainController()  {
        
        user = new Admin("asd", "asd", "asd", "asd", null, "asd");
        
        // Models:
        this.bills = new BillRegister();
        this.claims = new ClaimRegister();
        this.customers = new CustomerRegister();
        this.employees = new EmployeeRegister();
        this.insurances = new InsuranceRegister();
        this.logs = new LogRegister();
        
        // Update models with stored data:
        readAllDataFromFile();
        
        // Modules:
        this.mainView = new MainView();
    
        // Claim Registration Views:
        this.registerBoatClaim = new RegisterBoatClaim();
        this.registerCarClaim = new RegisterCarClaim();
        this.registerHolidayHomeClaim = new RegisterHolidayHomeClaim();
        this.registerHolidayHomeContentClaim = new RegisterHolidayHomeContentClaim();
        this.registerHomeClaim = new RegisterHomeClaim();
        this.registerHomeContentClaim = new RegisterHomeContentClaim();
        this.registerTravelClaim = new RegisterTravelClaim();
        
        // Insurance Registration Views:
        this.registerBoatInsurance = new RegisterBoatInsurance();
        this.registerCarInsurance = new RegisterCarInsurance();
        this.registerHolidayHomeInsurance = new RegisterHolidayHomeInsurance();
        this.registerHolidayHomeContentInsurance = new RegisterHolidayHomeContentInsurance();
        this.registerHomeInsurance = new RegisterHomeInsurance();
        this.registerHomeContentInsurance = new RegisterHomeContentInsurance();
        this.registerTravelInsurance = new RegisterTravelInsurance();
        
        // Person Registration Views:
        this.registerCustomer = new RegisterCustomer();
        this.registerEmployee = new RegisterEmployee();
    
        // Search Views:
        this.searchClaims = new SearchClaims();
        this.searchCustomers = new SearchCustomers();
        this.searchEmployees = new SearchEmployees();
        this.searchInsurances = new SearchInsurances();

        // Statistics Views:
        this.statisticsClaims = new StatisticsClaims();
        this.statisticsIncome = new StatisticsIncome();
        this.statisticsDisbursements = new StatisticsDisbursements();
        
        // Creates an ArrayList with all views to be sent as a parameter to the 
        // view controller. This is done to make the constructor of the 
        // controller cleaner.
        ArrayList<Object> views = new ArrayList<>();
        views.add(0, registerBoatClaim);
        views.add(1, registerCarClaim);
        views.add(2, registerHolidayHomeClaim);
        views.add(3, registerHolidayHomeContentClaim);
        views.add(4, registerHomeClaim);
        views.add(5, registerHomeContentClaim);
        views.add(6, registerTravelClaim);
        views.add(7, registerBoatInsurance);
        views.add(8, registerCarInsurance);
        views.add(9, registerHolidayHomeInsurance);
        views.add(10, registerHolidayHomeContentInsurance);
        views.add(11, registerHomeInsurance);
        views.add(12, registerHomeContentInsurance);
        views.add(13, registerTravelInsurance);
        views.add(14, registerCustomer);
        views.add(15, registerEmployee);
        views.add(16, searchClaims);
        views.add(17, searchCustomers);
        views.add(18, searchEmployees);
        views.add(19, searchInsurances);
        views.add(20, statisticsClaims);
        views.add(21, statisticsDisbursements);
        views.add(22, statisticsIncome);

        
        // Controllers:
        this.modelController = new ModelController(bills, claims, customers, 
                employees, insurances, logs);
        this.viewController = new ViewController(mainView, views);
        
        
        setBrandComboBox();
        initializeEventHandlers();
        
        // Update all car insurance bonuses:
        insurances.updateAllCarInusranceBonuses();
        // Update all total customer statuses:
        modelController.updateTotalCustomers();
        // Update all insurance payments:
        modelController.updatePayments();
        // Update dunning on all unpaid bills:
        //modelController.updateUnpaidBills();
        
        modelController.autoPayAllBills(); // automaticall pay all unpaid bills:
        // modelController.autoPayAllBillsDue(); // Automatically pay all bills that are at due date or older:

    }
    
    public void show(Stage stage) {
        this.primaryStage = stage;
        viewController.show(stage);
    }
    
    private void initializeEventHandlers() {
        initializeRegisterInsuranceEventHandlers();
        initializeRegisterClaimEventHandlers();
        initializeSearchEventHandlers();
        initializeStatisticsEventHandlers();
        mainView.setSaveDataButtonEventHandler(this::mainViewSaveDataButtonEventHandler);
        mainView.setExitButtonEventHandler(this::mainViewExitButtonEventHandler);
        
        
        registerCustomer.setRegisterEventHandler(this::registerCustomerButtonEventHandler);
        registerEmployee.setRegisterEventHandler(this::registerEmployeeButtonEventHandler);

    }
    
    private void initializeRegisterInsuranceEventHandlers() {
        registerBoatInsurance.setRegisterEventHandler(this::boatInsuranceRegisterButtonEventHandler);
        registerBoatInsurance.setSearchCustomerIdEventHandler(this::boatInsuranceSearchCustomerIdButtonEventHandler);
        registerBoatInsurance.setSearchPersonalNumberEventHandler(this::boatInsuranceSearchPersonalNumberButtonEventHandler);
        
        registerCarInsurance.setSearchCustomerIdEventHandler(this::carInsuranceSearchCustomerIdButtonEventHandler);
        registerCarInsurance.setSearchPersonalNumberEventHandler(this::carInsuranceSearchPersonalNumberButtonEventHandler);
        registerCarInsurance.setBrandComboListener(this::brandComboListener);
        registerCarInsurance.setYearComboListener(this::yearComboListener);
        registerCarInsurance.setRegisterEventHandler(this::carInsuranceRegisterButtonEventHandler);
        
        registerHomeInsurance.setRegisterEventHandler(this::homeInsuranceRegisterButtonEventHandler);
        registerHomeInsurance.setSearchCustomerIdEventHandler(this::homeInsuranceSearchCustomerIdButtonEventHandler);
        registerHomeInsurance.setSearchPersonalNumberEventHandler(this::homeInsuranceSearchPersonalNumberButtonEventHandler);
        
        registerHomeContentInsurance.setRegisterEventHandler(this::homeContentInsuranceRegisterButtonEventHandler);
        registerHomeContentInsurance.setSearchCustomerIdEventHandler(this::homeContentInsuranceSearchCustomerIdButtonEventHandler);
        registerHomeContentInsurance.setSearchPersonalNumberEventHandler(this::homeContentInsuranceSearchPersonalNumberButtonEventHandler);
        
        registerHolidayHomeInsurance.setRegisterEventHandler(this::holidayHomeInsuranceRegisterButtonEventHandler);
        registerHolidayHomeInsurance.setSearchCustomerIdEventHandler(this::holidayHomeInsuranceSearchCustomerIdButtonEventHandler);
        registerHolidayHomeInsurance.setSearchPersonalNumberEventHandler(this::holidayHomeInsuranceSearchPersonalNumberButtonEventHandler);
        
        registerHolidayHomeContentInsurance.setRegisterEventHandler(this::holidayHomeContentInsuranceRegisterButtonEventHandler);
        registerHolidayHomeContentInsurance.setSearchCustomerIdEventHandler(this::holidayHomeContentInsuranceSearchCustomerIdButtonEventHandler);
        registerHolidayHomeContentInsurance.setSearchPersonalNumberEventHandler(this::holidayHomeContentInsuranceSearchPersonalNumberButtonEventHandler);
        
        registerTravelInsurance.setRegisterEventHandler(this::travelInsuranceRegisterButtonEventHandler);
        registerTravelInsurance.setSearchCustomerIdEventHandler(this::travelInsuranceSearchCustomerIdButtonEventHandler);
        registerTravelInsurance.setSearchPersonalNumberEventHandler(this::travelInsuranceSearchPersonalNumberButtonEventHandler);
    } // end of class initializeRegisterInsuranceEventHandlers
 
    private void initializeRegisterClaimEventHandlers() {
        registerCarClaim.setRegisterButtonEventHandler(this::carClaimRegisterButtonEventHandler);
        registerCarClaim.setSearchCustomerIdButtonEventHandler(this::carClaimSearchCustomerIdButtonEventHandler);
        registerCarClaim.setSearchPersonalNumberButtonEventHandler(this::carClaimSearchPersonalNumberButtonEventHandler);
        registerCarClaim.setSelectInsuranceButtonEventHandler(this::carClaimSelectInsuranceButtonEventHandler);
        registerCarClaim.setSelectImageButtonEventHandler(this::carClaimSelectImageButtonEventHandler);
        registerCarClaim.setOpenClaimFormButtonEventHandler(this::carClaimOpenClaimFormButtonEventHandler);
        
        registerBoatClaim.setRegisterButtonEventHandler(this::boatClaimRegisterButtonEventHandler);
        registerBoatClaim.setSearchCustomerIdButtonEventHandler(this::boatClaimSearchCustomerIdButtonEventHandler);
        registerBoatClaim.setSearchPersonalNumberButtonEventHandler(this::boatClaimSearchPersonalNumberButtonEventHandler);
        registerBoatClaim.setSelectImageButtonEventHandler(this::boatClaimSelectImageButtonEventHandler);
        registerBoatClaim.setSelectInsuranceButtonEventHandler(this::boatClaimSelectInsuranceButtonEventHandler);
        
        registerHomeClaim.setRegisterButtonEventHandler(this::homeClaimRegisterButtonEventHandler);
        registerHomeClaim.setSearchCustomerIdButtonEventHandler(this::homeClaimSearchCustomerIdButtonEventHandler);
        registerHomeClaim.setSearchPersonalNumberButtonEventHandler(this::homeClaimSearchPersonalNumberButtonEventHandler);
        registerHomeClaim.setSelectImageButtonEventHandler(this::homeClaimSelectImageButtonEventHandler);
        registerHomeClaim.setSelectInsuranceButtonEventHandler(this::homeClaimSelectInsuranceButtonEventHandler);
        
        registerHomeContentClaim.setRegisterButtonEventHandler(this::homeContentClaimRegisterButtonEventHandler);
        registerHomeContentClaim.setSearchCustomerIdButtonEventHandler(this::homeContentClaimSearchCustomerIdButtonEventHandler);
        registerHomeContentClaim.setSearchPersonalNumberButtonEventHandler(this::homeContentClaimSearchPersonalNumberButtonEventHandler);
        registerHomeContentClaim.setSelectImageButtonEventHandler(this::homeContentClaimSelectImageButtonEventHandler);
        registerHomeContentClaim.setSelectInsuranceButtonEventHandler(this::homeContentClaimSelectInsuranceButtonEventHandler);
        registerHomeContentClaim.setAddItemButtonEventHandler(this::homeContentClaimAddItemButtonEventHandler);
        
        registerHolidayHomeClaim.setRegisterButtonEventHandler(this::holidayHomeClaimRegisterButtonEventHandler);
        registerHolidayHomeClaim.setSearchCustomerIdButtonEventHandler(this::holidayHomeClaimSearchCustomerIdButtonEventHandler);
        registerHolidayHomeClaim.setSearchPersonalNumberButtonEventHandler(this::holidayHomeClaimSearchPersonalNumberButtonEventHandler);
        registerHolidayHomeClaim.setSelectImageButtonEventHandler(this::holidayHomeClaimSelectImageButtonEventHandler);
        registerHolidayHomeClaim.setSelectInsuranceButtonEventHandler(this::holidayHomeClaimSelectInsuranceButtonEventHandler);
        
        registerHolidayHomeContentClaim.setRegisterButtonEventHandler(this::holidayHomeContentClaimRegisterButtonEventHandler);
        registerHolidayHomeContentClaim.setSearchCustomerIdButtonEventHandler(this::holidayHomeContentClaimSearchCustomerIdButtonEventHandler);
        registerHolidayHomeContentClaim.setSearchPersonalNumberButtonEventHandler(this::holidayHomeContentClaimSearchPersonalNumberButtonEventHandler);
        registerHolidayHomeContentClaim.setSelectImageButtonEventHandler(this::holidayHomeContentClaimSelectImageButtonEventHandler);
        registerHolidayHomeContentClaim.setSelectInsuranceButtonEventHandler(this::holidayHomeContentClaimSelectInsuranceButtonEventHandler);
        registerHolidayHomeContentClaim.setAddItemButtonEventHandler(this::holidayHomeContentClaimAddItemButtonEventHandler);
        
        registerTravelClaim.setRegisterButtonEventHandler(this::travelClaimRegisterButtonEventHandler);
        registerTravelClaim.setSearchCustomerIdButtonEventHandler(this::travelClaimSearchCustomerIdButtonEventHandler);
        registerTravelClaim.setSearchPersonalNumberButtonEventHandler(this::travelClaimSearchPersonalNumberButtonEventHandler);    
        registerTravelClaim.setSelectImageButtonEventHandler(this::travelClaimSelectImageButtonEventHandler);
        registerTravelClaim.setSelectInsuranceButtonEventHandler(this::travelClaimSelectInsuranceButtonEventHandler);
        registerTravelClaim.setAddItemButtonEventHandler(this::travelClaimAddItemButtonEventHandler);
        
    } // end of class initializeRegisterClaimEventHandlers
        
    private void initializeSearchEventHandlers() {
        searchClaims.setClaimTypeEventHandler(this::searchClaimsTypeEventHandler);
        searchClaims.setDisbursementEventHandler(this::searchClaimsDisbursementEventHandler);
        searchClaims.setFormEventHandler(this::searchClaimsFormEventHandler);
        searchClaims.setImageEventHandler(this::searchClaimsImageEventHandler);
        searchClaims.setSearchEventHandler(this::searchClaimsSearchEventHandler);
        searchClaims.setSearchIdEventHandler(this::searchClaimsSearchIdEventHandler);
        searchClaims.setSelectEventHandler(this::searchClaimsSelectEventHandler);
        
        searchCustomers.setDeactivateEventHandler(this::searchCustomersDeactivateEventHandler);
        searchCustomers.setSearchEventHandler(this::searchCustomersSearchEventHandler);
        searchCustomers.setSearchIdEventHandler(this::searchCustomersSearchIdEventHandler);
        searchCustomers.setSelectEventHandler(this::searchCustomersSelectEventHandler);
        
        searchEmployees.setDeactivateEventHandler(this::searchEmployeesDeactivateEventHandler);
        searchEmployees.setSearchEventHandler(this::searchEmployeesSearchEventHandler);
        searchEmployees.setSearchIdEventHandler(this::searchEmployeesSearchIdEventHandler);
        searchEmployees.setSelectEventHandler(this::searchEmployeesSelectEventHandler);
        
        searchInsurances.setDeactivateEventHandler(this::searchInsuranceDeactivateEventHandler);
        searchInsurances.setSearchEventHandler(this::searchInsuranceSearchEventHandler);
        searchInsurances.setSearchIdEventHandler(this::searchInsuranceSearchIdEventHandler);
        searchInsurances.setSelectEventHandler(this::searchInsuranceSelectEventHandler);
    } // end of class initializeSearchEventHandlers
    
    private void initializeStatisticsEventHandlers() {
        statisticsIncome.setSearchEventHandler(this::statisticsIncomeSearchButtonEventHandler);
        statisticsIncome.setFromYearComboEventHandler(this::statisticsIncomeFromYearComboEventHandler);
        statisticsIncome.setFromMonthComboEventHandler(this::statisticsIncomeFromMonthComboEventHandler);
    }
    
    private void mainViewSaveDataButtonEventHandler(ActionEvent event) {
        writeBillsToFile();
        writeClaimsToFile();
        writeCustomersToFile();
        writeEmployeesToFile();
        writeInsurancesToFile();
        
        writeBillIdToFile();
        writeClaimIdToFile();
        writeCustomerIdToFile();
        writeEmployeeIdToFile();
        writeInsuranceIdToFile();
        
        writeLogsToFile();
    }
    
    public void readAllDataFromFile() {
        readBillIdFromFile();
        readBillsFromFile();
        readClaimIdFromFile();
        readClaimsFromFile();
        readCustomerIdFromFile();
        readCustomersFromFile();
        readEmployeeIdFromFile();
        readEmployeesFromFile();
        readInsuranceIdFromFile();
        readInsurancesFromFile();  
    }
    
    private void mainViewExitButtonEventHandler(ActionEvent event) {
        Platform.exit();
    }    
    
    /**
     * Registers a new Customer, based on the information put into the required
     * text fields in the GUI Window.
     * @param event The ActionEvent of this event button
     */
    private void registerCustomerButtonEventHandler(ActionEvent event) {
        /* Boolean ok, must be ok to register new Customer. Set to false if
         the required fields are filled in wrong */
        boolean ok = true;
        // zipCode initialized at 0 
        int zipCode = 0;
        
        // Fetches information from the required input fields 
        String personalNumber = registerCustomer.getPersonalNumber();
        String firstName = registerCustomer.getFirstName();
        String lastName = registerCustomer.getLastName();
        String street = registerCustomer.getStreet();
        String zipCodeS = registerCustomer.getZipCode();
        String city = registerCustomer.getCity();
        String email = registerCustomer.getEmail();
        String phone = registerCustomer.getPhone();
        
        // Clears all feedback messages 
        registerCustomer.clearMessages();
        
        // Checks whether the fields are filled in correctly 
        if (personalNumber.equals("")) {
            String message = "Fyll inn dette feltet.";
            registerCustomer.setPersonalNumberMessage(message);
            ok = false;
        } else if (!personalNumber.matches("\\d{11}")) {
            String message = "Fyll inn 11 siffer.";
            registerCustomer.setPersonalNumberMessage(message);
            ok = false;
        }
        
        if (firstName.equals("")) {
            String message = "Fyll inn dette feltet.";
            registerCustomer.setFirstNameMessage(message);
            ok = false;
        } else if (!firstName.matches("[ÆØÅæøåA-Za-z]{2,30}")) {
            String message = "Fyll inn korrekt navn, kun bokstaver tillatt.";
            registerCustomer.setFirstNameMessage(message);
            ok = false;
        }
        
        if (lastName.equals("")) {
            String message = "Fyll inn dette feltet.";
            registerCustomer.setLastNameMessage(message);
            ok = false;
        } else if (!lastName.matches("^[ÆØÅæøåA-Za-z]{2,30}")) {
            String message = "Fyll inn korrekt navn, kun bokstaver tillatt.";
            registerCustomer.setLastNameMessage(message);
            ok = false;
        }
        
        if (street.equals("")) {
            String message = "Fyll inn dette feltet.";
            registerCustomer.setStreetMessage(message);
            ok = false;
        } else if (!street.matches("[ÆØÅæøåa-zA-Z0-9]{2,30}")) {
            String message = "Fyll inn korrekt gateadresse, kun bokstaver og"
                    + " tall tillatt.";
            registerCustomer.setStreetMessage(message);
            ok = false;
        }
        
        if (city.equals("")) {
            String message = "Fyll inn dette feltet.";
            registerCustomer.setCityMessage(message);
            ok = false;
        } else if (!city.matches("^[æøåÆØÅa-zA-Z]{2,30}")) {
            String message = "Fyll inn korrekt poststed, kun bokstaver tillatt.";
            registerCustomer.setCityMessage(message);
            ok = false;
        }
        
        if (email.equals("")) {
            String message = "Fyll inn dette feltet.";
            registerCustomer.setEmailMessage(message);
            ok = false;
        } else if (!email.matches("^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+(?:\\.[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+)*@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$")) {
            String message = "Fyll inn korrekt email adresse.";
            registerCustomer.setEmailMessage(message);
            ok = false;
        }
        
        if (phone.equals("")) {
            String message = "Fyll inn dette feltet.";
            registerCustomer.setPhoneMessage(message);
            ok = false;
        } else if (!phone.matches("\\d{8,13}")) {
            String message = "Fyll inn korrekt telefonnummer.";
            registerCustomer.setPhoneMessage(message);
            ok = false;
        }
        
        if (zipCodeS.equals("")) {
            String message = "Fyll inn dette feltet.";
            registerCustomer.setZipCodeMessage(message);
            ok = false;
        } else if (!zipCodeS.matches("\\d{4}")) {
            String message = "Fyll inn korrekt postnummer, 4 siffer.";
            registerCustomer.setZipCodeMessage(message);
            ok = false;
        } else {
            
            try {
                zipCode = Integer.parseInt(zipCodeS);
            } catch (NumberFormatException nfe) {
                    ok = false;
                    logs.add(nfe.getStackTrace(), nfe.getMessage(), user);
            }
        }
        
        // If all fields are filled in correctly proceed to creating and adding customer:
        if (ok) {
            // Creates an adress object for the customer:
            Address adress = new Address(street, zipCode, city);
            // Creates a new customer:
            Customer customer = new Customer(firstName, lastName, personalNumber, email, adress, phone);
            // Adds the new customer to the datastructure:
            boolean addOk = customers.addCustomer(customer);
            String result;
            if (addOk) {
                result = "Kunde registrert. Kundenummer: " + customer.getId();
            } else {
                result = "Kunden kunne ikke registreres. Kunde med likt personnummer eksisterer allerede.";
            }
            registerCustomer.setResultMessage(result);
        }
    }
    
    // TODO: Trenger regex og validering
    private void registerEmployeeButtonEventHandler(ActionEvent event) {
        boolean ok = true;
        int zipCode = 0;
        
        EmployeeType position = registerEmployee.getPosition();
        String personalNumber = registerEmployee.getPersonalNumber();
        String firstName = registerEmployee.getFirstName();
        String lastName = registerEmployee.getLastName();
        String street = registerEmployee.getStreet();
        String zipCodeS = registerEmployee.getZipCode();
        String city = registerEmployee.getCity();
        String email = registerEmployee.getEmail();
        String phone = registerEmployee.getPhone();
        
        registerEmployee.clearMessages();
        
        if (position == null) {
            String message = "Velg stilling.";
            registerEmployee.setPositionMessage(message);
            ok = false;
        }
        
        if (personalNumber.equals("")) {
            String message = "Fyll inn dette feltet.";
            registerEmployee.setPersonalNumberMessage(message);
            ok = false;
        } else if (!personalNumber.matches("\\d{11}")) {
            String message = "Fyll inn 11 siffer.";
            registerEmployee.setPersonalNumberMessage(message);
            ok = false;
        }
        
        if (firstName.equals("")) {
            String message = "Fyll inn dette feltet.";
            registerEmployee.setFirstNameMessage(message);
            ok = false;
        } else if (!firstName.matches("[ÆØÅæøåA-Za-z]{2,30}")) {
            String message = "Fyll inn korrekt navn, kun bokstaver tillatt.";
            registerEmployee.setFirstNameMessage(message);
            ok = false;
        }
        
        if (lastName.equals("")) {
            String message = "Fyll inn dette feltet.";
            registerEmployee.setLastNameMessage(message);
            ok = false;
        } else if (!lastName.matches("^[ÆØÅæøåA-Za-z]{2,30}")) {
            String message = "Fyll inn korrekt navn, kun bokstaver tillatt.";
            registerEmployee.setLastNameMessage(message);
            ok = false;
        }
        
        if (street.equals("")) {
            String message = "Fyll inn dette feltet.";
            registerEmployee.setStreetMessage(message);
            ok = false;
        } else if (!street.matches("[ÆØÅæøåa-zA-Z0-9]{2,30}")) {
            String message = "Fyll inn korrekt gateadresse, kun bokstaver og"
                    + " tall tillatt.";
            registerEmployee.setStreetMessage(message);
            ok = false;
        }
        
        if (city.equals("")) {
            String message = "Fyll inn dette feltet.";
            registerEmployee.setCityMessage(message);
            ok = false;
        } else if (!city.matches("^[æøåÆØÅa-zA-Z]{2,30}")) {
            String message = "Fyll inn korrekt poststed, kun bokstaver tillatt.";
            registerEmployee.setCityMessage(message);
            ok = false;
        }
        
        if (email.equals("")) {
            String message = "Fyll inn dette feltet.";
            registerEmployee.setEmailMessage(message);
            ok = false;
        } else if (!email.matches("^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+(?:\\.[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+)*@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$")) {
            String message = "Fyll inn korrekt email adresse.";
            registerEmployee.setEmailMessage(message);
            ok = false;
        }
        
        if (phone.equals("")) {
            String message = "Fyll inn dette feltet.";
            registerEmployee.setPhoneMessage(message);
            ok = false;
        } else if (!phone.matches("\\d{8,13}")) {
            String message = "Fyll inn korrekt telefonnummer.";
            registerEmployee.setPhoneMessage(message);
            ok = false;
        }
        
        if (zipCodeS.equals("")) {
            String message = "Fyll inn dette feltet.";
            registerEmployee.setZipCodeMessage(message);
            ok = false;
        } else if (!zipCodeS.matches("\\d{4}")) {
            String message = "Fyll inn korrekt postnummer, 4 siffer.";
            registerEmployee.setZipCodeMessage(message);
            ok = false;
        } else {
            
            try {
                zipCode = Integer.parseInt(zipCodeS);
            } catch (NumberFormatException nfe) {
                    ok = false;
                    logs.add(nfe.getStackTrace(), nfe.getMessage(), user);
            }
        }
        
        
        if(ok){
        // Creates an adress object for the customer:
        Address adress = new Address(street, zipCode, city);
        // Creates a new customer:
        Employee employee;
        boolean addok = false;
        switch (position) {
            case SERVICE_WORKER : employee = new ServiceWorker(firstName, lastName, personalNumber, email, adress, phone);
            case CASE_WORKER : employee = new CaseWorker(firstName, lastName, personalNumber, email, adress, phone);
            case ADMIN : employee = new Admin(firstName, lastName, personalNumber, email, adress, phone);
            default: employee = new ServiceWorker(firstName, lastName, personalNumber, email, adress, phone);
        }
        // Adds the new customer to the datastructure:
        addok = employees.addEmployee(employee);
        String result;
        if (addok) {
            result = "Ansatt registrert. Ansattnummer: " + employee.getId();
        } else {
            result = "Ansatt kunne ikke registreres. Ansatt med likt personnummer eksisterer allerede.";
        }
        
        registerEmployee.setResultMessage(result);
    }
    }
    // STATISTICS INCOME EVENT HANDLERS
    
    private void statisticsIncomeFromYearComboEventHandler(ActionEvent event) {
        if (statisticsIncome.getFromYearComboValue().equals("")) {
            // Disable month and day comboboxes:
            statisticsIncome.setFromMonthComboDisable(true);
            statisticsIncome.setToMonthComboDisable(true);
            statisticsIncome.setFromDayComboDisable(true);
            statisticsIncome.setToDayComboDisable(true);
        } else {
            // Enable month comboboxes:
            statisticsIncome.setFromMonthComboDisable(false);
            statisticsIncome.setToMonthComboDisable(false);
        }
    }
    
    private void statisticsIncomeFromMonthComboEventHandler(ActionEvent event) {
        if (statisticsIncome.getFromMonthComboValue().equals("")) {
            // Disable day comboboxes:
            statisticsIncome.setFromDayComboDisable(true);
            statisticsIncome.setToDayComboDisable(true);
        } else {
            // Enable day comboboxes:
            statisticsIncome.setFromDayComboDisable(false);
            statisticsIncome.setToDayComboDisable(false);
        }
    }
    
    private void statisticsIncomeSearchButtonEventHandler(ActionEvent event) {
        statisticsIncome.clearMessages();
        String type = statisticsIncome.getInsuranceType();
        String title = type;
        if (type == null) {
            title = "Alle forsikringer";
        }
        int customerId = 0;
        String personalNumber;
        if (!statisticsIncome.getNumber().equals("")) {
            if (statisticsIncome.isCustomerIdSelected()) {
                try {
                    customerId = Integer.parseInt(statisticsIncome.getNumber());
                } catch (NumberFormatException nfe) {
                    statisticsIncome.setSearchMessage(CUSTOMERID_FORMAT_MESSAGE);
                }

            } else {
                personalNumber = statisticsIncome.getNumber();
                customerId = customers.findCustomerIdByPersonalNumber(personalNumber);
                if (customerId == -1) {
                    statisticsIncome.setSearchMessage(CUSTOMERID_NOT_FOUND_MESSAGE);
                }
            }
        }
        String fromDate = "";
        String toDate = "";
        
        String fromYearString = statisticsIncome.getFromYearComboValue();
        String fromMonthString = statisticsIncome.getFromMonthComboValue();
        String fromDayString = statisticsIncome.getFromDayComboValue();
        int fromYear = 0;
        int fromMonth = 0;
        int fromDay = 0;
        if (!fromYearString.equals("")) {
            fromYear = Integer.parseInt(fromYearString);
        }
        if (!fromMonthString.equals("")) {
            switch (fromMonthString) {
                case "Januar": fromMonth = 1;
                    break;
                case "Februar": fromMonth = 2;
                    break;
                case "Mars": fromMonth = 3;
                    break;
                case "April": fromMonth = 4;
                    break;
                case "Mai": fromMonth = 5;
                    break;
                case "Juni": fromMonth = 6;
                    break;
                case "Juli": fromMonth = 7;
                    break;
                case "August": fromMonth = 8;
                    break;
                case "September": fromMonth = 9;
                    break;
                case "Oktober": fromMonth = 10;
                    break;
                case "November": fromMonth = 11;
                    break;
                case "Desember": fromMonth = 12;
                    break;
                default: fromMonth = 0;
                    break;
            }
        }
        if (!fromDayString.equals("")) {
            fromDay = Integer.parseInt(fromDayString);
        }
        
        String toYearString = statisticsIncome.getToYearComboValue();
        String toMonthString = statisticsIncome.getToMonthComboValue();
        String toDayString = statisticsIncome.getToDayComboValue();
        int toYear = 0;
        int toMonth = 0;
        int toDay = 0;
                if (!toYearString.equals("")) {
            toYear = Integer.parseInt(toYearString);
        }
        if (!toMonthString.equals("")) {
            switch (toMonthString) {
                case "Januar": toMonth = 1;
                    break;
                case "Februar": toMonth = 2;
                    break;
                case "Mars": toMonth = 3;
                    break;
                case "April": toMonth = 4;
                    break;
                case "Mai": toMonth = 5;
                    break;
                case "Juni": toMonth = 6;
                    break;
                case "Juli": toMonth = 7;
                    break;
                case "August": toMonth = 8;
                    break;
                case "September": toMonth = 9;
                    break;
                case "Oktober": toMonth = 10;
                    break;
                case "November": toMonth = 11;
                    break;
                case "Desember": toMonth = 12;
                    break;
                default: toMonth = 0;
                    break;
            }
        }
        if (!toDayString.equals("")) {
            toDay = Integer.parseInt(toDayString);
        }
        
        int sumFrom = 0;
        int sumTo = 0;
        
        if (fromYearString.equals("") && toYearString.equals("")) {
            
            Calendar fromCal = modelController.getOldestPayDate(type, customerId, 0);
            Calendar toCal = modelController.getNewestPayDate(type, customerId, 0);
            
            if (fromCal != null && toCal != null) {
                fromYear = fromCal.get(Calendar.YEAR);
                fromMonth = fromCal.get(Calendar.MONTH);
                fromDay = fromCal.get(Calendar.DAY_OF_MONTH);

                toYear = toCal.get(Calendar.YEAR);
                toMonth = toCal.get(Calendar.MONTH);
                toDay = toCal.get(Calendar.DAY_OF_MONTH);

            } 
        }
        
        sumFrom = (int) modelController.getIncomeAtDate(fromYear, fromMonth, fromDay, type, customerId, 0);
        if (fromDay != 0) {
            fromDate = fromDay + ".";
        }
        if (fromMonth != 0) {
            fromDate += fromMonth + ".";
        }
        if (fromYear != 0) {
            fromDate += fromYear;
        }

        sumTo = (int) modelController.getIncomeAtDate(toYear, toMonth, toDay, type, customerId, 0);
        if (toDay != 0) {
            toDate = toDay + ".";
        }
        if (toMonth != 0) {
            toDate += toMonth + ".";
        }
        if (toYear != 0) {
            toDate += toYear;
        }
        statisticsIncome.populateLineChartAll(sumFrom, sumTo, fromDate, toDate, title); 
    }
     
    // CAR CLAIM REGISTRATION EVENT HANDLERS
    
    private void carClaimOpenClaimFormButtonEventHandler(ActionEvent event) {
        // Clear the previous claim form message:
        registerCarClaim.setClaimFormMessage("");
        // Create a new stage:
        Stage formStage = new Stage();
        // Create a new CarClaimFormView and send the carClaimRegistration view as a reference:
        RegisterCarClaimForm ccfv = new RegisterCarClaimForm(registerCarClaim);
        // initialize the carClaimFormViews register button event handler:
        ccfv.setRegisterButtonEventHandler();
        // BEFORE WE SHOW THE FORM; WE WANT TO POPULATE SOME OF ITS TEXT FIELDS WITH OUR
        // SELECTED CUSTOMER:
        
        // Get information on our customer and its car insurances car:
        try {
            int customerId = registerCarClaim.getSelectedCustomerId();
            Customer personA = customers.findCustomerById(customerId);
            if (personA == null) {
                registerCarClaim.setClaimFormMessage(NO_INSURANCE_MESSAGE);
            } else {
                CarInsurance insuranceA = (CarInsurance) registerCarClaim.getInsuranceTableValue();
                String lastNameA = personA.getLastName();
                String firstNameA = personA.getFirstName();
                String personalNumberA = personA.getPersonalNumber();
                Address addressA = personA.getAddress();
                String streetA = addressA.getStreet();
                int zipCodeA = addressA.getZipCode();
                String phoneA = personA.getPhone();
                String emailA = personA.getEmail();
                Car carA = insuranceA.getCar();
                String regA = carA.getRegistrationNumber();
                String brandA = carA.getBrand();

                // Apply this information to the car claim form view:
                ccfv.setLastNameA(lastNameA);
                ccfv.setFirstNameA(firstNameA);
                ccfv.setPersonalNumberA(personalNumberA);
                ccfv.setStreetA(streetA);
                ccfv.setZipCodeA(String.valueOf(zipCodeA));
                ccfv.setPhoneA(phoneA);
                ccfv.setEmailA(emailA);
                ccfv.setRegistrationNumberA(regA);
                ccfv.setBrandA(brandA);

                // Show the Car claim form:
                ccfv.show(formStage);
            }
        } catch (ClassCastException cce) {
            registerCarClaim.setClaimFormMessage(NO_INSURANCE_MESSAGE);
        }
    }
    
    private void carClaimRegisterButtonEventHandler(ActionEvent event) {
        boolean ok = true;
        // Clears the previous status messages:
        registerCarClaim.clearMessages();
        int insuranceId = 0;
        int appraisal = 0;
        int customerId = registerCarClaim.getSelectedCustomerId();
        if (customerId == 0) {
            ok = false;
            registerCarClaim.setRegisterButtonMessage(NO_CUSTOMER_MESSAGE);
            return;
        }
        CarInsurance insurance;
        // Get the Insurance selected from the table:
        if (registerCarClaim.getInsuranceTableValue() instanceof CarInsurance) {
            insurance = (CarInsurance) registerCarClaim.getInsuranceTableValue();
            // Checks if this value is null:
            if (insurance == null) {
                ok = false;
                // If so send a message to the user:
                registerCarClaim.setRegisterButtonMessage(NO_INSURANCE_MESSAGE);
                // Exit the method, as we don't need to check for anything else:
                return;
            } else {
                insuranceId = insurance.getInsuranceId();
            }
            Calendar dateHappened = registerCarClaim.getDateHappenedPickerValue();
            if (dateHappened == null) {
                ok = false;
                // If so send a message to the user:
                registerCarClaim.setRegisterButtonMessage(NO_DATE_MESSAGE);
                return; // leave method
            }
            String description = registerCarClaim.getDescriptionTextArea();
            if (description.equals("")) {
                ok = false;
                // If so send a message to the user:
                registerCarClaim.setRegisterButtonMessage(DESCRIPTION_EMPTY_MESSAGE);
                return; // leave method
            }
            // Get the selected damages:
            Set<Damage> damages = registerCarClaim.getSelectedDamages();
            // Returns an empty set if no damages are selected:
            if (damages.isEmpty()) {
                // This is still allowed.
            }
            Image image = registerCarClaim.getImage();
            if (image == null) {
                // This is also allowed.
            }

            String appraisalString = registerCarClaim.getAppraisalField();
            if (appraisalString.equals("")) {
                ok = false;
                registerCarClaim.setAppraisalFieldMessage(EMPTY_MESSAGE);
            } else {
                try {
                    appraisal = Integer.parseInt(appraisalString);
                } catch (NumberFormatException nfe) {
                    ok = false;
                    registerCarClaim.setAppraisalFieldMessage(FORMAT_MESSAGE);
                }
            }
            // If all fields are filled in correctly according to what we need to create a
            // claim, we proceed:
            if (ok) {
                RegisterCarClaimForm formView = registerCarClaim.getCarClaimFormView();
                if (formView == null) {
                    // Send carclaimform as null if no carclaimformview has been opened.
                    CarClaim claim = new CarClaim(customerId, insuranceId, description, 
                            dateHappened, damages, appraisal, image, null);
                    // Add this car claim to the claim register:
                    if (claims.addClaim(claim)) {
                            registerCarClaim.setRegisterButtonMessage(REGISTER_SUCCESS);
                    } else {
                        registerCarClaim.setRegisterButtonMessage(REGISTER_NO_SUCCESS);
                    }
                } else {
                    Customer personA = customers.findCustomerById(customerId);
                    Car carA = insurance.getCar();
                    String brandB = formView.getBrandB();
                    String regB = formView.getRegistrationNumberB();
                    String firstNameB = formView.getFirstNameA();
                    String lastNameB = formView.getLastNameB();
                    String personalNumberB = formView.getPersonalNumberB();
                    String steetB = formView.getStreetB();
                    String zipCodeStringB = formView.getZipCodeB();
                    int zipCodeB = 0;
                    try {
                        zipCodeB = Integer.parseInt(zipCodeStringB);
                    } catch (NumberFormatException nfe) {
                        logs.add("Problem med parsing av zipcode til ClarClaimFormView i CarClaimRegistration", user);
                    }
                    String CityB = "";
                    String emailB = formView.getEmailB();
                    String phoneB = formView.getPhoneB();
                    String insuranceCompanyB = formView.getInsuranceCompanyB();
                    String location = formView.getLocation();
                    Calendar date = formView.getDate();
                    String witnesses = formView.getWitnesses();
                    Image snapshot = formView.getSnapshot();
                    // If none of the fields for Person B are filled in, 
                    // create a car claim form consisting only of person B/the customer
                    if (brandB.equals("") && regB.equals("") &&
                            firstNameB.equals("") &&
                            lastNameB.equals("") &&
                            personalNumberB.equals("") &&
                            steetB.equals("") &&
                            zipCodeStringB.equals("") &&
                            zipCodeB == 0 &&
                            CityB.equals("") &&
                            emailB.equals("") &&
                            phoneB.equals("") &&
                            insuranceCompanyB.equals("")) {
                        // Create a new car claim form:
                        CarClaimForm carClaimForm = new CarClaimForm(carA, personA, location, insuranceId, snapshot);
                        // Create a new car claim with this claim form:
                        CarClaim claim = new CarClaim(customerId, insuranceId, description, 
                                dateHappened, damages, appraisal, image, carClaimForm);
                        // Add this car claim to the claim register:
                        if (claims.addClaim(claim)) {
                            registerCarClaim.setRegisterButtonMessage(REGISTER_SUCCESS);
                        }
                    } else { // If not we create a fuller car claim form:
                        // Creates a new car for person B with model as empty:
                        Car carB = new Car(brandB, "", personalNumberB, regB);
                        // Create a new address for person B
                        Address addressB = new Address(steetB, zipCodeB);
                        // Create a new vehicle owner representing person B
                        VehicleOwner personB = new VehicleOwner(firstNameB, lastNameB, 
                                personalNumberB, emailB, addressB, phoneB);
                        // Create a new car claim form with customer and person B
                        CarClaimForm carClaimForm = new CarClaimForm(carA, personA, 
                                personB, carB, insuranceCompanyB, location, insuranceId, 
                                witnesses, snapshot);
                        // Create a new car claim with these data:
                        CarClaim claim = new CarClaim(customerId, insuranceId, description, dateHappened, damages, appraisal, carClaimForm);
                        // Add this car claim to the claim register:
                        if (claims.addClaim(claim)) {
                            // Clear uploads(image and claim form):
                            registerCarClaim.clearUploads();
                            registerCarClaim.setRegisterButtonMessage(REGISTER_SUCCESS);
                        }
                    }
                }
            }  
        }   
    }
    
    private void carClaimSearchCustomerIdButtonEventHandler(ActionEvent event) {
        String customerIdString = registerCarClaim.getCustomerId();
        int customerId;
        if(customerIdString.equals("")) {
            // Gives the user an appropriate message if the user hasn't put in a customerId:
            registerCarClaim.setCustomerArea(CUSTOMERID_EMPTY_MESSAGE);
            return;
        }
        try {
            // Converts the customerId to int:
            customerId = Integer.parseInt(customerIdString);
        } catch(NumberFormatException nfe) {
            logs.add(nfe.getStackTrace(), nfe.getMessage(), user);
            // Gives the user an appropriate message if the customerId wasn't formatted correctly:
            registerCarClaim.setCustomerArea(CUSTOMERID_FORMAT_MESSAGE);
            return;
        }
        // TODO: Regex.
        // Searches for the customer by customerId:
        Customer customer = customers.findCustomerById(customerId);
        if(customer == null) {
            // Gives the user an appropriate message if the customer wasn't found:
            registerCarClaim.setCustomerArea(CUSTOMERID_NOT_FOUND_MESSAGE + customerId);
        } else {
            // Displays the customer:
            registerCarClaim.setCustomerArea(customer.toString());
            // Set this id as the selected one in the view class:
            registerCarClaim.setSelectedCustomerId(customerId);
            // Finds the customers insurances:
            List insuranceList = insurances.getAllActiveTypeInsurancesByCustomerId(customerId, CarInsurance.class);
            if (!insuranceList.isEmpty()) {
                // Displays the insurances if there is at least one:
                registerCarClaim.populateInsurancesTable(insuranceList);
            }
        }
    }
    
    private void carClaimSearchPersonalNumberButtonEventHandler(ActionEvent event) {
        String personalNumber = registerCarClaim.getPersonalNumber();
        if(personalNumber.equals("")) {
            // Gives the user an appropriate message if the user hasn't put in a personalNumber:
            registerCarClaim.setCustomerArea(PERSONALNUMBER_EMPTY_MESSAGE);
        } else if (!personalNumber.matches("\\d{11}")) {
            // Gives the user an appropriate message:
            registerCarClaim.setCustomerArea(PERSONALNUMBER_INCORRECT_MESSAGE);
        } else {
            // Searches for the customer by personalNumber:
            Customer customer = customers.findCustomerByPersonalNumber(personalNumber);
            if(customer == null) {
                // Gives the user an appropriate message if the customer wasn't found:
                registerCarClaim.setCustomerArea(PERSONALNUMBER_NOT_FOUND_MESSAGE + personalNumber);
            } else {
                // Finds the customers insurances:
                int customerId = customer.getId();
                // Displays the customer:
                registerCarClaim.setCustomerArea(customer.toString());
                // Set this id as the selected one in the view class:
                registerCarClaim.setSelectedCustomerId(customerId);
                List insuranceList = insurances.getAllActiveTypeInsurancesByCustomerId(customerId, CarInsurance.class);
                if (!insuranceList.isEmpty()) {
                    // Displays the insurances if there is at least one:
                    registerCarClaim.populateInsurancesTable(insuranceList);
                }
            }
        }
    }
    
    private void carClaimSelectInsuranceButtonEventHandler(ActionEvent event) {
        registerCarClaim.clearMessages();
        Insurance insurance = registerCarClaim.getInsuranceTableValue();
        String message = "";
        if (insurance instanceof CarInsurance) {
            CarInsuranceCoverage coverage = (CarInsuranceCoverage) insurance.getCoverage();
            registerCarClaim.setDamages(coverage.damages());
            message = insurance.getName() + " " + coverage.toString() + " er valgt.";
            registerCarClaim.setSelectInsuranceMessage(message);
        } else {
            message = "Ingen forsikring valgt,";
            registerCarClaim.setSelectInsuranceMessage(message);
        }
    }
    
    private void carClaimSelectImageButtonEventHandler(ActionEvent event) {
        // Initialize a file chooser:
        FileChooser fileChooser = new FileChooser();
        // Set the title:
        fileChooser.setTitle("Velg bilde");
        // Set the initial directory to the user folder:
        fileChooser.setInitialDirectory(
                new File(System.getProperty("user.home"))
        );
        // Set selectable file extennsion:
        fileChooser.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter("All Images", "*.*"),
            new FileChooser.ExtensionFilter("JPG", "*.jpg"),
            new FileChooser.ExtensionFilter("GIF", "*.gif"),
            new FileChooser.ExtensionFilter("BMP", "*.bmp"),
            new FileChooser.ExtensionFilter("PNG", "*.png")
        );
        // Get the file:
        File file = fileChooser.showOpenDialog(primaryStage);
        if (file != null) {
            try {
                BufferedImage bufferedImage = ImageIO.read(file);
                Image image = SwingFXUtils.toFXImage(bufferedImage, null);
                // ! Sets this image to the image in field in the carClaimRegistration view:
                registerCarClaim.setImage(image);
            } catch (IOException ioe) {
                logs.add(ioe.getStackTrace(), ioe.getMessage(), user);
            }
        }
    }
    
    // BOAT CLAIM REGISTRATION EVENT HANDLERS
    
    private void boatClaimRegisterButtonEventHandler(ActionEvent event) {
        boolean ok = true;
        // Clears the previous status messages:
        registerBoatClaim.clearMessages();
        int insuranceId = 0;
        int appraisal = 0;
        int customerId = registerBoatClaim.getSelectedCustomerId();
        if (customerId == 0) {
            ok = false;
            registerBoatClaim.setRegisterButtonMessage(NO_CUSTOMER_MESSAGE);
            return;
        }
        BoatInsurance insurance;
        // Get the Insurance selected from the table:
        if (registerBoatClaim.getInsuranceTableValue() instanceof BoatInsurance) {
            insurance = (BoatInsurance) registerBoatClaim.getInsuranceTableValue();
            // Checks if this value is null:
            if (insurance == null) {
                ok = false;
                // If so send a message to the user:
                registerBoatClaim.setRegisterButtonMessage(NO_INSURANCE_MESSAGE);
                // Exit the method, as we don't need to check for anything else:
                return;
            } else {
                insuranceId = insurance.getInsuranceId();
            }
            Calendar dateHappened = registerBoatClaim.getDateHappenedPickerValue();
            if (dateHappened == null) {
                ok = false;
                // If so send a message to the user:
                registerBoatClaim.setRegisterButtonMessage(NO_DATE_MESSAGE);
                return; // leave method
            }
            String description = registerBoatClaim.getDescriptionTextArea();
            if (description.equals("")) {
                ok = false;
                // If so send a message to the user:
                registerBoatClaim.setRegisterButtonMessage(DESCRIPTION_EMPTY_MESSAGE);
                return; // leave method
            }
            // Get the selected damages:
            Set<Damage> damages = registerBoatClaim.getSelectedDamages();
            // Returns an empty set if no damages are selected:
            if (damages.isEmpty()) {
                // This is still allowed.
            }
            Image image = registerBoatClaim.getImage();
            if (image == null) {
                // This is also allowed.
            }

            String appraisalString = registerBoatClaim.getAppraisalField();
            if (appraisalString.equals("")) {
                ok = false;
                registerBoatClaim.setAppraisalFieldMessage(EMPTY_MESSAGE);
            } else {
                try {
                    appraisal = Integer.parseInt(appraisalString);
                } catch (NumberFormatException nfe) {
                    ok = false;
                    registerBoatClaim.setAppraisalFieldMessage(FORMAT_MESSAGE);
                }
            }
            // If all fields are filled in correctly according to what we need to create a
            // claim, we proceed:
            if (ok) {
                // Create boat claim:
                BoatClaim claim = new BoatClaim(customerId, insuranceId, description, dateHappened, damages, appraisal, image);
                // Add this claim to the claim register:
                if (claims.addClaim(claim)) {
                    // Clear uploads(image):
                    registerBoatClaim.clearUploads();
                    registerBoatClaim.setRegisterButtonMessage(REGISTER_SUCCESS);
                } else {
                    registerBoatClaim.setRegisterButtonMessage(REGISTER_NO_SUCCESS);
                }
            }
        }
    }
    
    private void boatClaimSearchCustomerIdButtonEventHandler(ActionEvent event) {
        String customerIdString = registerBoatClaim.getCustomerId();
        int customerId;
        if(customerIdString.equals("")) {
            // Gives the user an appropriate message if the user hasn't put in a customerId:
            registerBoatClaim.setCustomerArea(CUSTOMERID_EMPTY_MESSAGE);
            return;
        }
        try {
            // Converts the customerId to int:
            customerId = Integer.parseInt(customerIdString);
        } catch(NumberFormatException nfe) {
            logs.add(nfe.getStackTrace(), nfe.getMessage(), user);
            // Gives the user an appropriate message if the customerId wasn't formatted correctly:
            registerBoatClaim.setCustomerArea(CUSTOMERID_FORMAT_MESSAGE);
            return;
        }
        // TODO: Regex.
        // Searches for the customer by customerId:
        Customer customer = customers.findCustomerById(customerId);
        if(customer == null) {
            // Gives the user an appropriate message if the customer wasn't found:
            registerBoatClaim.setCustomerArea(CUSTOMERID_NOT_FOUND_MESSAGE + customerId);
        } else {
            // Displays the customer:
            registerBoatClaim.setCustomerArea(customer.toString());
            // Set this id as the selected one in the view class:
            registerBoatClaim.setSelectedCustomerId(customerId);
            // Finds the customers insurances:
            List insuranceList = insurances.getAllActiveTypeInsurancesByCustomerId(customerId, BoatInsurance.class);
            if (!insuranceList.isEmpty()) {
                // Displays the insurances if there is at least one:
                registerBoatClaim.populateInsurancesTable(insuranceList);
            }
        }
    }
    
    private void boatClaimSearchPersonalNumberButtonEventHandler(ActionEvent event) {
        String personalNumber = registerBoatClaim.getPersonalNumber();
        if(personalNumber.equals("")) {
            // Gives the user an appropriate message if the user hasn't put in a personalNumber:
            registerBoatClaim.setCustomerArea(PERSONALNUMBER_EMPTY_MESSAGE);
        } else if (!personalNumber.matches("\\d{11}")) {
            // Gives the user an appropriate message:
            registerBoatClaim.setCustomerArea(PERSONALNUMBER_INCORRECT_MESSAGE);
        } else {
            // Searches for the customer by personalNumber:
            Customer customer = customers.findCustomerByPersonalNumber(personalNumber);
            if(customer == null) {
                // Gives the user an appropriate message if the customer wasn't found:
                registerBoatClaim.setCustomerArea(PERSONALNUMBER_NOT_FOUND_MESSAGE + personalNumber);
            } else {
                // Finds the customers insurances:
                int customerId = customer.getId();
                // Displays the customer:
                registerBoatClaim.setCustomerArea(customer.toString());
                // Set this id as the selected one in the view class:
                registerBoatClaim.setSelectedCustomerId(customerId);
                List insuranceList = insurances.getAllActiveTypeInsurancesByCustomerId(customerId, BoatInsurance.class);
                if (!insuranceList.isEmpty()) {
                    // Displays the insurances if there is at least one:
                    registerBoatClaim.populateInsurancesTable(insuranceList);
                }
            }
        }
    }
    
    private void boatClaimSelectInsuranceButtonEventHandler(ActionEvent event) {
        registerBoatClaim.clearMessages();
        Insurance insurance = registerBoatClaim.getInsuranceTableValue();
        String message = "";
        if (insurance instanceof BoatInsurance) {
            BoatInsuranceCoverage coverage = (BoatInsuranceCoverage) insurance.getCoverage();
            registerBoatClaim.setDamages(coverage.damages());
            message = insurance.getName() + " " + coverage.toString() + " er valgt.";
            registerBoatClaim.setSelectInsuranceMessage(message);
        } else {
            message = "Ingen forsikring valgt,";
            registerBoatClaim.setSelectInsuranceMessage(message);
        }
    }
    
    private void boatClaimSelectImageButtonEventHandler(ActionEvent event) {
        // Initialize a file chooser:
        FileChooser fileChooser = new FileChooser();
        // Set the title:
        fileChooser.setTitle("Velg bilde");
        // Set the initial directory to the user folder:
        fileChooser.setInitialDirectory(
                new File(System.getProperty("user.home"))
        );
        // Set selectable file extennsion:
        fileChooser.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter("All Images", "*.*"),
            new FileChooser.ExtensionFilter("JPG", "*.jpg"),
            new FileChooser.ExtensionFilter("GIF", "*.gif"),
            new FileChooser.ExtensionFilter("BMP", "*.bmp"),
            new FileChooser.ExtensionFilter("PNG", "*.png")
        );
        // Get the file:
        File file = fileChooser.showOpenDialog(primaryStage);
        if (file != null) {
            try {
                BufferedImage bufferedImage = ImageIO.read(file);
                Image image = SwingFXUtils.toFXImage(bufferedImage, null);
                // ! Sets this image to the image in field in the boat claim view:
                registerBoatClaim.setImage(image);
            } catch (IOException ioe) {
                logs.add(ioe.getStackTrace(), ioe.getMessage(), user);
            }
        }
    }
    
    // HOME CLAIM REGISTRATION EVENT HANDLERS
    
    private void homeClaimRegisterButtonEventHandler(ActionEvent event) {
        boolean ok = true;
        // Clears the previous status messages:
        registerHomeClaim.clearMessages();
        int insuranceId = 0;
        int appraisal = 0;
        int customerId = registerHomeClaim.getSelectedCustomerId();
        if (customerId == 0) {
            ok = false;
            registerHomeClaim.setRegisterButtonMessage(NO_CUSTOMER_MESSAGE);
            return;
        }
        HomeInsurance insurance;
        // Get the Insurance selected from the table:
        if (registerHomeClaim.getInsuranceTableValue() instanceof HomeInsurance) {
            insurance = (HomeInsurance) registerHomeClaim.getInsuranceTableValue();
            // Checks if this value is null:
            if (insurance == null) {
                ok = false;
                // If so send a message to the user:
                registerHomeClaim.setRegisterButtonMessage(NO_INSURANCE_MESSAGE);
                // Exit the method, as we don't need to check for anything else:
                return;
            } else {
                insuranceId = insurance.getInsuranceId();
            }
            Calendar dateHappened = registerHomeClaim.getDateHappenedPickerValue();
            if (dateHappened == null) {
                ok = false;
                // If so send a message to the user:
                registerHomeClaim.setRegisterButtonMessage(NO_DATE_MESSAGE);
                return; // leave method
            }
            String description = registerHomeClaim.getDescriptionTextArea();
            if (description.equals("")) {
                ok = false;
                // If so send a message to the user:
                registerHomeClaim.setRegisterButtonMessage(DESCRIPTION_EMPTY_MESSAGE);
                return; // leave method
            }
            // Get the selected damages:
            Set<Damage> damages = registerHomeClaim.getSelectedDamages();
            // Returns an empty set if no damages are selected:
            if (damages.isEmpty()) {
                // This is still allowed.
            }
            Image image = registerHomeClaim.getImage();
            if (image == null) {
                // This is also allowed.
            }

            String appraisalString = registerHomeClaim.getAppraisalField();
            if (appraisalString.equals("")) {
                ok = false;
                registerHomeClaim.setAppraisalFieldMessage(EMPTY_MESSAGE);
            } else {
                try {
                    appraisal = Integer.parseInt(appraisalString);
                } catch (NumberFormatException nfe) {
                    ok = false;
                    registerBoatClaim.setAppraisalFieldMessage(FORMAT_MESSAGE);
                }
            }
            // If all fields are filled in correctly according to what we need to create a
            // claim, we proceed:
            if (ok) {
                // Create home claim:
                HomeClaim claim = new HomeClaim(customerId, insuranceId, description, dateHappened, damages, appraisal, image);
                // Add this claim to the claim register:
                if (claims.addClaim(claim)) {
                    // Clear uploads(image):
                    registerHomeClaim.clearUploads();
                    registerHomeClaim.setRegisterButtonMessage(REGISTER_SUCCESS);
                } else {
                    registerHomeClaim.setRegisterButtonMessage(REGISTER_NO_SUCCESS);
                }
            }
        }
    }
    
    private void homeClaimSearchCustomerIdButtonEventHandler(ActionEvent event) {
        String customerIdString = registerHomeClaim.getCustomerId();
        int customerId;
        if(customerIdString.equals("")) {
            // Gives the user an appropriate message if the user hasn't put in a customerId:
            registerHomeClaim.setCustomerArea(CUSTOMERID_EMPTY_MESSAGE);
            return;
        }
        try {
            // Converts the customerId to int:
            customerId = Integer.parseInt(customerIdString);
        } catch(NumberFormatException nfe) {
            logs.add(nfe.getStackTrace(), nfe.getMessage(), user);
            // Gives the user an appropriate message if the customerId wasn't formatted correctly:
            registerHomeClaim.setCustomerArea(CUSTOMERID_FORMAT_MESSAGE);
            return;
        }
        // TODO: Regex.
        // Searches for the customer by customerId:
        Customer customer = customers.findCustomerById(customerId);
        if(customer == null) {
            // Gives the user an appropriate message if the customer wasn't found:
            registerHomeClaim.setCustomerArea(CUSTOMERID_NOT_FOUND_MESSAGE + customerId);
        } else {
            // Displays the customer:
            registerHomeClaim.setCustomerArea(customer.toString());
            // Set this id as the selected one in the view class:
            registerHomeClaim.setSelectedCustomerId(customerId);
            // Finds the customers insurances:
            List insuranceList = insurances.getAllActiveTypeInsurancesByCustomerId(customerId, HomeInsurance.class);
            if (!insuranceList.isEmpty()) {
                // Displays the insurances if there is at least one:
                registerHomeClaim.populateInsurancesTable(insuranceList);
            }
        }
    }
    
    private void homeClaimSearchPersonalNumberButtonEventHandler(ActionEvent event) {
        String personalNumber = registerHomeClaim.getPersonalNumber();
        if(personalNumber.equals("")) {
            // Gives the user an appropriate message if the user hasn't put in a personalNumber:
            registerHomeClaim.setCustomerArea(PERSONALNUMBER_EMPTY_MESSAGE);
        } else if (!personalNumber.matches("\\d{11}")) {
            // Gives the user an appropriate message:
            registerHomeClaim.setCustomerArea(PERSONALNUMBER_INCORRECT_MESSAGE);
        } else {
            // Searches for the customer by personalNumber:
            Customer customer = customers.findCustomerByPersonalNumber(personalNumber);
            if(customer == null) {
                // Gives the user an appropriate message if the customer wasn't found:
                registerHomeClaim.setCustomerArea(PERSONALNUMBER_NOT_FOUND_MESSAGE + personalNumber);
            } else {
                // Finds the customers insurances:
                int customerId = customer.getId();
                // Displays the customer:
                registerHomeClaim.setCustomerArea(customer.toString());
                // Set this id as the selected one in the view class:
                registerHomeClaim.setSelectedCustomerId(customerId);
                List insuranceList = insurances.getAllActiveTypeInsurancesByCustomerId(customerId, HomeInsurance.class);
                if (!insuranceList.isEmpty()) {
                    // Displays the insurances if there is at least one:
                    registerHomeClaim.populateInsurancesTable(insuranceList);
                }
            }
        }
    }
    
    private void homeClaimSelectInsuranceButtonEventHandler(ActionEvent event) {
        registerHomeClaim.clearMessages();
        Insurance insurance = registerHomeClaim.getInsuranceTableValue();
        String message = "";
        if (insurance instanceof HomeInsurance) {
            HomeInsuranceCoverage coverage = (HomeInsuranceCoverage) insurance.getCoverage();
            registerHomeClaim.setDamages(coverage.damages());
            message = insurance.getName() + " " + coverage.toString() + " er valgt.";
            registerHomeClaim.setSelectInsuranceMessage(message);
        } else {
            message = "Ingen forsikring valgt,";
            registerHomeClaim.setSelectInsuranceMessage(message);
        }
    }
    
    private void homeClaimSelectImageButtonEventHandler(ActionEvent event) {
        // Initialize a file chooser:
        FileChooser fileChooser = new FileChooser();
        // Set the title:
        fileChooser.setTitle("Velg bilde");
        // Set the initial directory to the user folder:
        fileChooser.setInitialDirectory(
                new File(System.getProperty("user.home"))
        );
        // Set selectable file extennsion:
        fileChooser.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter("All Images", "*.*"),
            new FileChooser.ExtensionFilter("JPG", "*.jpg"),
            new FileChooser.ExtensionFilter("GIF", "*.gif"),
            new FileChooser.ExtensionFilter("BMP", "*.bmp"),
            new FileChooser.ExtensionFilter("PNG", "*.png")
        );
        // Get the file:
        File file = fileChooser.showOpenDialog(primaryStage);
        if (file != null) {
            try {
                BufferedImage bufferedImage = ImageIO.read(file);
                Image image = SwingFXUtils.toFXImage(bufferedImage, null);
                // ! Sets this image to the image in field in the home claim view:
                registerHomeClaim.setImage(image);
            } catch (IOException ioe) {
                logs.add(ioe.getStackTrace(), ioe.getMessage(), user);
            }
        }
    }
    
    // HOME CONTENT CLAIM REGISTRATION EVENT HANDLERS
    
    private void homeContentClaimRegisterButtonEventHandler(ActionEvent event) {
        boolean ok = true;
        // Clears the previous status messages:
        registerHomeContentClaim.clearMessages();
        int insuranceId = 0;
        int appraisal = 0;
        int customerId = registerHomeContentClaim.getSelectedCustomerId();
        if (customerId == 0) {
            ok = false;
            registerHomeContentClaim.setRegisterButtonMessage(NO_CUSTOMER_MESSAGE);
            return;
        }
        HomeContentInsurance insurance;
        // Get the Insurance selected from the table:
        if (registerHomeContentClaim.getInsuranceTableValue() instanceof HomeContentInsurance) {
            insurance = (HomeContentInsurance) registerHomeContentClaim.getInsuranceTableValue();
            // Checks if this value is null:
            if (insurance == null) {
                ok = false;
                // If so send a message to the user:
                registerHomeContentClaim.setRegisterButtonMessage(NO_INSURANCE_MESSAGE);
                // Exit the method, as we don't need to check for anything else:
                return;
            } else {
                insuranceId = insurance.getInsuranceId();
            }
            Calendar dateHappened = registerHomeContentClaim.getDateHappenedPickerValue();
            if (dateHappened == null) {
                ok = false;
                // If so send a message to the user:
                registerHomeContentClaim.setRegisterButtonMessage(NO_DATE_MESSAGE);
                return; // leave method
            }
            String description = registerHomeContentClaim.getDescriptionTextArea();
            if (description.equals("")) {
                ok = false;
                // If so send a message to the user:
                registerHomeContentClaim.setRegisterButtonMessage(DESCRIPTION_EMPTY_MESSAGE);
                return; // leave method
            }
            // Get the selected damages:
            Set<Damage> damages = registerHomeContentClaim.getSelectedDamages();
            // Returns an empty set if no damages are selected:
            if (damages.isEmpty()) {
                // This is still allowed.
            }
            Image image = registerHomeContentClaim.getImage();
            if (image == null) {
                // This is also allowed.
            }

            String appraisalString = registerHomeContentClaim.getAppraisalField();
            if (appraisalString.equals("")) {
                ok = false;
                registerHomeContentClaim.setAppraisalFieldMessage(EMPTY_MESSAGE);
            } else {
                try {
                    appraisal = Integer.parseInt(appraisalString);
                } catch (NumberFormatException nfe) {
                    ok = false;
                    registerHomeContentClaim.setAppraisalFieldMessage(FORMAT_MESSAGE);
                }
            }
            // Get the list of claim items stored in the registration view.(empty or not)
            List<ClaimItem> claimItems = registerHomeContentClaim.getClaimItems();
            
            // If all fields are filled in correctly according to what we need to create a
            // claim, we proceed:
            if (ok) {
                // Create content claim:
                ContentClaim claim = new ContentClaim(customerId, insuranceId, 
                        description, dateHappened, damages, appraisal, claimItems, image);
                // Add this claim to the claim register:
                if (claims.addClaim(claim)) {
                    // Clear uploads(image):
                    registerHomeContentClaim.clearUploads();
                    registerHomeContentClaim.setRegisterButtonMessage(REGISTER_SUCCESS);
                } else {
                    registerHomeContentClaim.setRegisterButtonMessage(REGISTER_NO_SUCCESS);
                }
            }
        }
    }
    
    private void homeContentClaimAddItemButtonEventHandler(ActionEvent event) {
        // Clear the message:
        registerHomeContentClaim.setAddItemConfirmMessage("");
        boolean ok = true;
        String message; // Message used to display confirm status to the user:
        // We get the values needed to create a ClaimItem form the view:
        String itemDescription = registerHomeContentClaim.getItemDescriptionTextArea();
        String acquiredArea = registerHomeContentClaim.getAcquiredAreaField();
        Calendar acquiredDate = registerHomeContentClaim.getAcquiredDatePickerValue();
        String valueString = registerHomeContentClaim.getValueField();
        int value = 0;
        String descriptionOfDocumentation = registerHomeContentClaim.getDescriptionOfDocumentationTextArea();
        
        // Simple Validation: // We only care about item description and value:
        if (itemDescription.equals("")) {
            ok = false;
            registerHomeContentClaim.setAddItemConfirmMessage("Fyll inn beskrivelse og verdi.");
        } else if (valueString.equals("")) {
            ok = false;
            registerHomeContentClaim.setAddItemConfirmMessage("Fyll inn beskrivelse og verdi.");
            
        } else {
            try {
                // Parse the String to int:
                value = Integer.parseInt(valueString);
                // We create a new claim item:
                ClaimItem claimItem = new ClaimItem(itemDescription, acquiredArea, acquiredDate, value, descriptionOfDocumentation);
                // We then add this item to a list in the claim view: (will be recieved when claim is registered)
                registerHomeContentClaim.addClaimItem(claimItem);
                registerHomeContentClaim.setAddItemConfirmMessage("Gjenstand lagt til.");
            } catch (NumberFormatException nfe) {
                registerHomeContentClaim.setAddItemConfirmMessage("Kun tallverdier er gyldig i verdifelt.");
                return;
            }
        }
    }
    
    private void homeContentClaimSearchCustomerIdButtonEventHandler(ActionEvent event) {
        String customerIdString = registerHomeContentClaim.getCustomerId();
        int customerId;
        if(customerIdString.equals("")) {
            // Gives the user an appropriate message if the user hasn't put in a customerId:
            registerHomeContentClaim.setCustomerArea(CUSTOMERID_EMPTY_MESSAGE);
            return;
        }
        try {
            // Converts the customerId to int:
            customerId = Integer.parseInt(customerIdString);
        } catch(NumberFormatException nfe) {
            logs.add(nfe.getStackTrace(), nfe.getMessage(), user);
            // Gives the user an appropriate message if the customerId wasn't formatted correctly:
            registerHomeContentClaim.setCustomerArea(CUSTOMERID_FORMAT_MESSAGE);
            return;
        }
        // TODO: Regex.
        // Searches for the customer by customerId:
        Customer customer = customers.findCustomerById(customerId);
        if(customer == null) {
            // Gives the user an appropriate message if the customer wasn't found:
            registerHomeContentClaim.setCustomerArea(CUSTOMERID_NOT_FOUND_MESSAGE + customerId);
        } else {
            // Displays the customer:
            registerHomeContentClaim.setCustomerArea(customer.toString());
            // Set this id as the selected one in the view class:
            registerHomeContentClaim.setSelectedCustomerId(customerId);
            // Finds the customers holiday home content insurances:
            List insuranceList = insurances.getAllActiveTypeInsurancesByCustomerId(customerId, 
                    HomeContentInsurance.class);
            if (!insuranceList.isEmpty()) {
                // Displays the insurances if there is at least one:
                registerHomeContentClaim.populateInsurancesTable(insuranceList);
            }
        }
    }
    
    private void homeContentClaimSearchPersonalNumberButtonEventHandler(ActionEvent event) {
        String personalNumber = registerHomeContentClaim.getPersonalNumber();
        if(personalNumber.equals("")) {
            // Gives the user an appropriate message if the user hasn't put in a personalNumber:
            registerHomeContentClaim.setCustomerArea(PERSONALNUMBER_EMPTY_MESSAGE);
        } else if (!personalNumber.matches("\\d{11}")) {
            // Gives the user an appropriate message:
            registerHomeContentClaim.setCustomerArea(PERSONALNUMBER_INCORRECT_MESSAGE);
        } else {
            // Searches for the customer by personalNumber:
            Customer customer = customers.findCustomerByPersonalNumber(personalNumber);
            if(customer == null) {
                // Gives the user an appropriate message if the customer wasn't found:
                registerHomeContentClaim.setCustomerArea(PERSONALNUMBER_NOT_FOUND_MESSAGE + personalNumber);
            } else {
                // Finds the customers insurances:
                int customerId = customer.getId();
                // Displays the customer:
                registerHomeContentClaim.setCustomerArea(customer.toString());
                // Set this id as the selected one in the view class:
                registerHomeContentClaim.setSelectedCustomerId(customerId);
                // Finds the customers holiday home content insurances:
                List insuranceList = insurances.getAllActiveTypeInsurancesByCustomerId(customerId, 
                        HomeContentInsurance.class);
                if (!insuranceList.isEmpty()) {
                    // Displays the insurances if there is at least one:
                    registerHomeContentClaim.populateInsurancesTable(insuranceList);
                }
            }
        }
    }
    
    private void homeContentClaimSelectInsuranceButtonEventHandler(ActionEvent event) {
        registerHomeContentClaim.clearMessages();
        Insurance insurance = registerHomeContentClaim.getInsuranceTableValue();
        String message = "";
        if (insurance instanceof HomeContentInsurance) {
            HomeContentInsuranceCoverage coverage = (HomeContentInsuranceCoverage) insurance.getCoverage();
            registerHomeContentClaim.setDamages(coverage.damages());
            message = insurance.getName() + " " + coverage.toString() + " er valgt.";
            registerHomeContentClaim.setSelectInsuranceMessage(message);
        } else {
            message = "Ingen forsikring valgt,";
            registerHomeContentClaim.setSelectInsuranceMessage(message);
        }
    }
    
    private void homeContentClaimSelectImageButtonEventHandler(ActionEvent event) {
        // Initialize a file chooser:
        FileChooser fileChooser = new FileChooser();
        // Set the title:
        fileChooser.setTitle("Velg bilde");
        // Set the initial directory to the user folder:
        fileChooser.setInitialDirectory(
                new File(System.getProperty("user.home"))
        );
        // Set selectable file extennsion:
        fileChooser.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter("All Images", "*.*"),
            new FileChooser.ExtensionFilter("JPG", "*.jpg"),
            new FileChooser.ExtensionFilter("GIF", "*.gif"),
            new FileChooser.ExtensionFilter("BMP", "*.bmp"),
            new FileChooser.ExtensionFilter("PNG", "*.png")
        );
        // Get the file:
        File file = fileChooser.showOpenDialog(primaryStage);
        if (file != null) {
            try {
                BufferedImage bufferedImage = ImageIO.read(file);
                Image image = SwingFXUtils.toFXImage(bufferedImage, null);
                // ! Sets this image to the image in field in the view:
                registerHomeContentClaim.setImage(image);
            } catch (IOException ioe) {
                logs.add(ioe.getStackTrace(), ioe.getMessage(), user);
            }
        }
    }
    
    // HOLIDAY HOME CLAIM REGISTRATION EVENT HANDLERS
    
    private void holidayHomeClaimRegisterButtonEventHandler(ActionEvent event) {
        boolean ok = true;
        // Clears the previous status messages:
        registerHolidayHomeClaim.clearMessages();
        int insuranceId = 0;
        int appraisal = 0;
        int customerId = registerHolidayHomeClaim.getSelectedCustomerId();
        if (customerId == 0) {
            ok = false;
            registerHolidayHomeClaim.setRegisterButtonMessage(NO_CUSTOMER_MESSAGE);
            return;
        }
        HolidayHomeInsurance insurance;
        // Get the Insurance selected from the table:
        if (registerHomeClaim.getInsuranceTableValue() instanceof HolidayHomeInsurance) {
            insurance = (HolidayHomeInsurance) registerHolidayHomeClaim.getInsuranceTableValue();
            // Checks if this value is null:
            if (insurance == null) {
                ok = false;
                // If so send a message to the user:
                registerHolidayHomeClaim.setRegisterButtonMessage(NO_INSURANCE_MESSAGE);
                // Exit the method, as we don't need to check for anything else:
                return;
            } else {
                insuranceId = insurance.getInsuranceId();
            }
            Calendar dateHappened = registerHolidayHomeClaim.getDateHappenedPickerValue();
            if (dateHappened == null) {
                ok = false;
                // If so send a message to the user:
                registerHolidayHomeClaim.setRegisterButtonMessage(NO_DATE_MESSAGE);
                return; // leave method
            }
            String description = registerHolidayHomeClaim.getDescriptionTextArea();
            if (description.equals("")) {
                ok = false;
                // If so send a message to the user:
                registerHolidayHomeClaim.setRegisterButtonMessage(DESCRIPTION_EMPTY_MESSAGE);
                return; // leave method
            }
            // Get the selected damages:
            Set<Damage> damages = registerHolidayHomeClaim.getSelectedDamages();
            // Returns an empty set if no damages are selected:
            if (damages.isEmpty()) {
                // This is still allowed.
            }
            Image image = registerHolidayHomeClaim.getImage();
            if (image == null) {
                // This is also allowed.
            }

            String appraisalString = registerHolidayHomeClaim.getAppraisalField();
            if (appraisalString.equals("")) {
                ok = false;
                registerHolidayHomeClaim.setAppraisalFieldMessage(EMPTY_MESSAGE);
            } else {
                try {
                    appraisal = Integer.parseInt(appraisalString);
                } catch (NumberFormatException nfe) {
                    ok = false;
                    registerHolidayHomeClaim.setAppraisalFieldMessage(FORMAT_MESSAGE);
                }
            }
            // If all fields are filled in correctly according to what we need to create a
            // claim, we proceed:
            if (ok) {
                // Create holiday home claim:
                HolidayHomeClaim claim = new HolidayHomeClaim(customerId, insuranceId, 
                        description, dateHappened, damages, appraisal, image);
                // Add this claim to the claim register:
                if (claims.addClaim(claim)) {
                    // Clear uploads(image):
                    registerHolidayHomeClaim.clearUploads();
                    registerHolidayHomeClaim.setRegisterButtonMessage(REGISTER_SUCCESS);
                } else {
                    registerHolidayHomeClaim.setRegisterButtonMessage(REGISTER_NO_SUCCESS);
                }
            }
        }
    }
    
    private void holidayHomeClaimSearchCustomerIdButtonEventHandler(ActionEvent event) {
        String customerIdString = registerHolidayHomeClaim.getCustomerId();
        int customerId;
        if(customerIdString.equals("")) {
            // Gives the user an appropriate message if the user hasn't put in a customerId:
            registerHolidayHomeClaim.setCustomerArea(CUSTOMERID_EMPTY_MESSAGE);
            return;
        }
        try {
            // Converts the customerId to int:
            customerId = Integer.parseInt(customerIdString);
        } catch(NumberFormatException nfe) {
            logs.add(nfe.getStackTrace(), nfe.getMessage(), user);
            // Gives the user an appropriate message if the customerId wasn't formatted correctly:
            registerHolidayHomeClaim.setCustomerArea(CUSTOMERID_FORMAT_MESSAGE);
            return;
        }
        // TODO: Regex.
        // Searches for the customer by customerId:
        Customer customer = customers.findCustomerById(customerId);
        if(customer == null) {
            // Gives the user an appropriate message if the customer wasn't found:
            registerHolidayHomeClaim.setCustomerArea(CUSTOMERID_NOT_FOUND_MESSAGE + customerId);
        } else {
            // Displays the customer:
            registerHolidayHomeClaim.setCustomerArea(customer.toString());
            // Set this id as the selected one in the view class:
            registerHolidayHomeClaim.setSelectedCustomerId(customerId);
            // Finds the customers holiday home insurances:
            List insuranceList = insurances.getAllActiveTypeInsurancesByCustomerId(customerId, HolidayHomeInsurance.class);
            if (!insuranceList.isEmpty()) {
                // Displays the insurances if there is at least one:
                registerHolidayHomeClaim.populateInsurancesTable(insuranceList);
            }
        }
    }
    
    private void holidayHomeClaimSearchPersonalNumberButtonEventHandler(ActionEvent event) {
        String personalNumber = registerHolidayHomeClaim.getPersonalNumber();
        if(personalNumber.equals("")) {
            // Gives the user an appropriate message if the user hasn't put in a personalNumber:
            registerHolidayHomeClaim.setCustomerArea(PERSONALNUMBER_EMPTY_MESSAGE);
        } else if (!personalNumber.matches("\\d{11}")) {
            // Gives the user an appropriate message:
            registerHolidayHomeClaim.setCustomerArea(PERSONALNUMBER_INCORRECT_MESSAGE);
        } else {
            // Searches for the customer by personalNumber:
            Customer customer = customers.findCustomerByPersonalNumber(personalNumber);
            if(customer == null) {
                // Gives the user an appropriate message if the customer wasn't found:
                registerHolidayHomeClaim.setCustomerArea(PERSONALNUMBER_NOT_FOUND_MESSAGE + personalNumber);
            } else {
                // Finds the customers insurances:
                int customerId = customer.getId();
                // Displays the customer:
                registerHolidayHomeClaim.setCustomerArea(customer.toString());
                // Set this id as the selected one in the view class:
                registerHolidayHomeClaim.setSelectedCustomerId(customerId);
                // Finds the customers holiday home insurances:
                List insuranceList = insurances.getAllActiveTypeInsurancesByCustomerId(customerId, HolidayHomeInsurance.class);
                if (!insuranceList.isEmpty()) {
                    // Displays the insurances if there is at least one:
                    registerHolidayHomeClaim.populateInsurancesTable(insuranceList);
                }
            }
        }
    }
    
    private void holidayHomeClaimSelectInsuranceButtonEventHandler(ActionEvent event) {
        registerHolidayHomeClaim.clearMessages();
        Insurance insurance = registerHolidayHomeClaim.getInsuranceTableValue();
        String message = "";
        if (insurance instanceof HolidayHomeInsurance) {
            HolidayHomeInsuranceCoverage coverage = (HolidayHomeInsuranceCoverage) insurance.getCoverage();
            registerHolidayHomeClaim.setDamages(coverage.damages());
            message = insurance.getName() + " " + coverage.toString() + " er valgt.";
            registerHolidayHomeClaim.setSelectInsuranceMessage(message);
        } else {
            message = "Ingen forsikring valgt,";
            registerHolidayHomeClaim.setSelectInsuranceMessage(message);
        }
    }
    
    private void holidayHomeClaimSelectImageButtonEventHandler(ActionEvent event) {
        // Initialize a file chooser:
        FileChooser fileChooser = new FileChooser();
        // Set the title:
        fileChooser.setTitle("Velg bilde");
        // Set the initial directory to the user folder:
        fileChooser.setInitialDirectory(
                new File(System.getProperty("user.home"))
        );
        // Set selectable file extennsion:
        fileChooser.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter("All Images", "*.*"),
            new FileChooser.ExtensionFilter("JPG", "*.jpg"),
            new FileChooser.ExtensionFilter("GIF", "*.gif"),
            new FileChooser.ExtensionFilter("BMP", "*.bmp"),
            new FileChooser.ExtensionFilter("PNG", "*.png")
        );
        // Get the file:
        File file = fileChooser.showOpenDialog(primaryStage);
        if (file != null) {
            try {
                BufferedImage bufferedImage = ImageIO.read(file);
                Image image = SwingFXUtils.toFXImage(bufferedImage, null);
                // ! Sets this image to the image in field in the view:
                registerHolidayHomeClaim.setImage(image);
            } catch (IOException ioe) {
                logs.add(ioe.getStackTrace(), ioe.getMessage(), user);
            }
        }
    }
    
    // HOLIDAY HOME CONTENT CLAIM REGISTRATION EVENT HANDLERS
    
    private void holidayHomeContentClaimRegisterButtonEventHandler(ActionEvent event) {
        boolean ok = true;
        // Clears the previous status messages:
        registerHolidayHomeContentClaim.clearMessages();
        int insuranceId = 0;
        int appraisal = 0;
        int customerId = registerHolidayHomeContentClaim.getSelectedCustomerId();
        if (customerId == 0) {
            ok = false;
            registerHolidayHomeContentClaim.setRegisterButtonMessage(NO_CUSTOMER_MESSAGE);
            return;
        }
        HolidayHomeContentInsurance insurance;
        // Get the Insurance selected from the table:
        if (registerHolidayHomeContentClaim.getInsuranceTableValue() instanceof HolidayHomeContentInsurance) {
            insurance = (HolidayHomeContentInsurance) registerHolidayHomeContentClaim.getInsuranceTableValue();
            // Checks if this value is null:
            if (insurance == null) {
                ok = false;
                // If so send a message to the user:
                registerHolidayHomeContentClaim.setRegisterButtonMessage(NO_INSURANCE_MESSAGE);
                // Exit the method, as we don't need to check for anything else:
                return;
            } else {
                insuranceId = insurance.getInsuranceId();
            }
            Calendar dateHappened = registerHolidayHomeContentClaim.getDateHappenedPickerValue();
            if (dateHappened == null) {
                ok = false;
                // If so send a message to the user:
                registerHolidayHomeContentClaim.setRegisterButtonMessage(NO_DATE_MESSAGE);
                return; // leave method
            }
            String description = registerHolidayHomeContentClaim.getDescriptionTextArea();
            if (description.equals("")) {
                ok = false;
                // If so send a message to the user:
                registerHolidayHomeContentClaim.setRegisterButtonMessage(DESCRIPTION_EMPTY_MESSAGE);
                return; // leave method
            }
            // Get the selected damages:
            Set<Damage> damages = registerHolidayHomeContentClaim.getSelectedDamages();
            // Returns an empty set if no damages are selected:
            if (damages.isEmpty()) {
                // This is still allowed.
            }
            Image image = registerHolidayHomeContentClaim.getImage();
            if (image == null) {
                // This is also allowed.
            }

            String appraisalString = registerHolidayHomeContentClaim.getAppraisalField();
            if (appraisalString.equals("")) {
                ok = false;
                registerHolidayHomeContentClaim.setAppraisalFieldMessage(EMPTY_MESSAGE);
            } else {
                try {
                    appraisal = Integer.parseInt(appraisalString);
                } catch (NumberFormatException nfe) {
                    ok = false;
                    registerHolidayHomeContentClaim.setAppraisalFieldMessage(FORMAT_MESSAGE);
                }
            }
            // Get the list of claim items stored in the registration view.(empty or not)
            List<ClaimItem> claimItems = registerHolidayHomeContentClaim.getClaimItems();
            
            // If all fields are filled in correctly according to what we need to create a
            // claim, we proceed:
            if (ok) {
                // Create holiday home content claim:
                HolidayHomeContentClaim claim = new HolidayHomeContentClaim(customerId, insuranceId, 
                        description, dateHappened, damages, appraisal, claimItems, image);
                // Add this claim to the claim register:
                if (claims.addClaim(claim)) {
                    // Clear uploads(image):
                    registerHolidayHomeContentClaim.clearUploads();
                    registerHolidayHomeContentClaim.setRegisterButtonMessage(REGISTER_SUCCESS);
                } else {
                    registerHolidayHomeContentClaim.setRegisterButtonMessage(REGISTER_NO_SUCCESS);
                }
            }
        }
    }
    
    private void holidayHomeContentClaimAddItemButtonEventHandler(ActionEvent event) {
        // Clear the message:
        registerHolidayHomeContentClaim.setAddItemConfirmMessage("");
        boolean ok = true;
        String message; // Message used to display confirm status to the user:
        // We get the values needed to create a ClaimItem form the view:
        String itemDescription = registerHolidayHomeContentClaim.getItemDescriptionTextArea();
        String acquiredArea = registerHolidayHomeContentClaim.getAcquiredAreaField();
        Calendar acquiredDate = registerHolidayHomeContentClaim.getAcquiredDatePickerValue();
        String valueString = registerHolidayHomeContentClaim.getValueField();
        int value = 0;
        String descriptionOfDocumentation = registerHolidayHomeContentClaim.getDescriptionOfDocumentationTextArea();
        
        // Simple Validation: // We only care about item description and value:
        if (itemDescription.equals("")) {
            ok = false;
            registerHolidayHomeContentClaim.setAddItemConfirmMessage("Fyll inn beskrivelse og verdi.");
        } else if (valueString.equals("")) {
            ok = false;
            registerHolidayHomeContentClaim.setAddItemConfirmMessage("Fyll inn beskrivelse og verdi.");
            
        } else {
            try {
                // Parse the String to int:
                value = Integer.parseInt(valueString);
                // We create a new claim item:
                ClaimItem claimItem = new ClaimItem(itemDescription, acquiredArea, acquiredDate, value, descriptionOfDocumentation);
                // We then add this item to a list in the claim view: (will be recieved when claim is registered)
                registerHolidayHomeContentClaim.addClaimItem(claimItem);
                registerHolidayHomeContentClaim.setAddItemConfirmMessage("Gjenstand lagt til.");
            } catch (NumberFormatException nfe) {
                registerHolidayHomeContentClaim.setAddItemConfirmMessage("Kun tallverdier er gyldig i verdifelt.");
                return;
            }
        }
    }
    
    private void holidayHomeContentClaimSearchCustomerIdButtonEventHandler(ActionEvent event) {
        String customerIdString = registerHolidayHomeContentClaim.getCustomerId();
        int customerId;
        if(customerIdString.equals("")) {
            // Gives the user an appropriate message if the user hasn't put in a customerId:
            registerHolidayHomeContentClaim.setCustomerArea(CUSTOMERID_EMPTY_MESSAGE);
            return;
        }
        try {
            // Converts the customerId to int:
            customerId = Integer.parseInt(customerIdString);
        } catch(NumberFormatException nfe) {
            logs.add(nfe.getStackTrace(), nfe.getMessage(), user);
            // Gives the user an appropriate message if the customerId wasn't formatted correctly:
            registerHolidayHomeContentClaim.setCustomerArea(CUSTOMERID_FORMAT_MESSAGE);
            return;
        }
        // TODO: Regex.
        // Searches for the customer by customerId:
        Customer customer = customers.findCustomerById(customerId);
        if(customer == null) {
            // Gives the user an appropriate message if the customer wasn't found:
            registerHolidayHomeContentClaim.setCustomerArea(CUSTOMERID_NOT_FOUND_MESSAGE + customerId);
        } else {
            // Displays the customer:
            registerHolidayHomeContentClaim.setCustomerArea(customer.toString());
            // Set this id as the selected one in the view class:
            registerHolidayHomeContentClaim.setSelectedCustomerId(customerId);
            // Finds the customers holiday home content insurances:
            List insuranceList = insurances.getAllActiveTypeInsurancesByCustomerId(customerId, 
                    HolidayHomeContentInsurance.class);
            if (!insuranceList.isEmpty()) {
                // Displays the insurances if there is at least one:
                registerHolidayHomeContentClaim.populateInsurancesTable(insuranceList);
            }
        }
    }
    
    private void holidayHomeContentClaimSearchPersonalNumberButtonEventHandler(ActionEvent event) {
        String personalNumber = registerHolidayHomeContentClaim.getPersonalNumber();
        if(personalNumber.equals("")) {
            // Gives the user an appropriate message if the user hasn't put in a personalNumber:
            registerHolidayHomeContentClaim.setCustomerArea(PERSONALNUMBER_EMPTY_MESSAGE);
        } else if (!personalNumber.matches("\\d{11}")) {
            // Gives the user an appropriate message:
            registerHolidayHomeContentClaim.setCustomerArea(PERSONALNUMBER_INCORRECT_MESSAGE);
        } else {
            // Searches for the customer by personalNumber:
            Customer customer = customers.findCustomerByPersonalNumber(personalNumber);
            if(customer == null) {
                // Gives the user an appropriate message if the customer wasn't found:
                registerHolidayHomeContentClaim.setCustomerArea(PERSONALNUMBER_NOT_FOUND_MESSAGE + personalNumber);
            } else {
                // Finds the customers insurances:
                int customerId = customer.getId();
                // Displays the customer:
                registerHolidayHomeContentClaim.setCustomerArea(customer.toString());
                // Set this id as the selected one in the view class:
                registerHolidayHomeContentClaim.setSelectedCustomerId(customerId);
                // Finds the customers holiday home content insurances:
                List insuranceList = insurances.getAllActiveTypeInsurancesByCustomerId(customerId, 
                        HolidayHomeContentInsurance.class);
                if (!insuranceList.isEmpty()) {
                    // Displays the insurances if there is at least one:
                    registerHolidayHomeContentClaim.populateInsurancesTable(insuranceList);
                }
            }
        }
    }
    
    private void holidayHomeContentClaimSelectInsuranceButtonEventHandler(ActionEvent event) {
        registerHolidayHomeContentClaim.clearMessages();
        Insurance insurance = registerHolidayHomeContentClaim.getInsuranceTableValue();
        String message = "";
        if (insurance instanceof HolidayHomeContentInsurance) {
            HolidayHomeContentInsuranceCoverage coverage = (HolidayHomeContentInsuranceCoverage) insurance.getCoverage();
            registerHolidayHomeContentClaim.setDamages(coverage.damages());
            message = insurance.getName() + " " + coverage.toString() + " er valgt.";
            registerHolidayHomeContentClaim.setSelectInsuranceMessage(message);
        } else {
            message = "Ingen forsikring valgt,";
            registerHolidayHomeContentClaim.setSelectInsuranceMessage(message);
        }
    }
    
    private void holidayHomeContentClaimSelectImageButtonEventHandler(ActionEvent event) {
        // Initialize a file chooser:
        FileChooser fileChooser = new FileChooser();
        // Set the title:
        fileChooser.setTitle("Velg bilde");
        // Set the initial directory to the user folder:
        fileChooser.setInitialDirectory(
                new File(System.getProperty("user.home"))
        );
        // Set selectable file extennsion:
        fileChooser.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter("All Images", "*.*"),
            new FileChooser.ExtensionFilter("JPG", "*.jpg"),
            new FileChooser.ExtensionFilter("GIF", "*.gif"),
            new FileChooser.ExtensionFilter("BMP", "*.bmp"),
            new FileChooser.ExtensionFilter("PNG", "*.png")
        );
        // Get the file:
        File file = fileChooser.showOpenDialog(primaryStage);
        if (file != null) {
            try {
                BufferedImage bufferedImage = ImageIO.read(file);
                Image image = SwingFXUtils.toFXImage(bufferedImage, null);
                // ! Sets this image to the image in field in the view:
                registerHolidayHomeContentClaim.setImage(image);
            } catch (IOException ioe) {
                logs.add(ioe.getStackTrace(), ioe.getMessage(), user);
            }
        }
    }
    
    // TRAVEL CLAIM REGISTRATION EVENT HANDLERS
    
    private void travelClaimRegisterButtonEventHandler(ActionEvent event) {
        boolean ok = true;
        // Clears the previous status messages:
        registerTravelClaim.clearMessages();
        int insuranceId = 0;
        int appraisal = 0;
        int customerId = registerTravelClaim.getSelectedCustomerId();
        if (customerId == 0) {
            ok = false;
            registerTravelClaim.setRegisterButtonMessage(NO_CUSTOMER_MESSAGE);
            return;
        }
        TravelInsurance insurance;
        // Get the Insurance selected from the table:
        if (registerTravelClaim.getInsuranceTableValue() instanceof TravelInsurance) {
            insurance = (TravelInsurance) registerTravelClaim.getInsuranceTableValue();
            // Checks if this value is null:
            if (insurance == null) {
                ok = false;
                // If so send a message to the user:
                registerTravelClaim.setRegisterButtonMessage(NO_INSURANCE_MESSAGE);
                // Exit the method, as we don't need to check for anything else:
                return;
            } else {
                insuranceId = insurance.getInsuranceId();
            }
            Calendar dateHappened = registerTravelClaim.getDateHappenedPickerValue();
            if (dateHappened == null) {
                ok = false;
                // If so send a message to the user:
                registerTravelClaim.setRegisterButtonMessage(NO_DATE_MESSAGE);
                return; // leave method
            }
            String description = registerTravelClaim.getDescriptionTextArea();
            if (description.equals("")) {
                ok = false;
                // If so send a message to the user:
                registerTravelClaim.setRegisterButtonMessage(DESCRIPTION_EMPTY_MESSAGE);
                return; // leave method
            }
            // Get the selected damages:
            Set<Damage> damages = registerTravelClaim.getSelectedDamages();
            // Returns an empty set if no damages are selected:
            if (damages.isEmpty()) {
                // This is still allowed.
            }
            Image image = registerTravelClaim.getImage();
            if (image == null) {
                // This is also allowed.
            }

            String appraisalString = registerTravelClaim.getAppraisalField();
            if (appraisalString.equals("")) {
                ok = false;
                registerTravelClaim.setAppraisalFieldMessage(EMPTY_MESSAGE);
            } else {
                try {
                    appraisal = Integer.parseInt(appraisalString);
                } catch (NumberFormatException nfe) {
                    ok = false;
                    registerTravelClaim.setAppraisalFieldMessage(FORMAT_MESSAGE);
                }
            }
            // Get the list of claim items stored in the registration view.(empty or not)
            List<ClaimItem> claimItems = registerTravelClaim.getClaimItems();
            // Get the credit card brand and country. TODO: validation
            String creditCardBrand = registerTravelClaim.getCreditCardBrandField();
            String country = registerTravelClaim.getCountryField();
            // If all fields are filled in correctly according to what we need to create a
            // claim, we proceed:
            if (ok) {
                // Create travel claim:
                TravelClaim claim = new TravelClaim(customerId, insuranceId, description, dateHappened, damages, 
                        appraisal, image, creditCardBrand, country, claimItems);
                // Add this claim to the claim register:
                if (claims.addClaim(claim)) {
                    // Clear uploads(image):
                    registerTravelClaim.clearUploads();
                    registerTravelClaim.setRegisterButtonMessage(REGISTER_SUCCESS);
                } else {
                    registerTravelClaim.setRegisterButtonMessage(REGISTER_NO_SUCCESS);
                }
            }
        }
    }
    
    private void travelClaimAddItemButtonEventHandler(ActionEvent event) {
        // Clear the message:
        registerTravelClaim.setAddItemConfirmMessage("");
        boolean ok = true;
        String message; // Message used to display confirm status to the user:
        // We get the values needed to create a ClaimItem form the view:
        String itemDescription = registerTravelClaim.getItemDescriptionTextArea();
        String acquiredArea = registerTravelClaim.getAcquiredAreaField();
        Calendar acquiredDate = registerTravelClaim.getAcquiredDatePickerValue();
        String valueString = registerTravelClaim.getValueField();
        int value = 0;
        String descriptionOfDocumentation = registerTravelClaim.getDescriptionOfDocumentationTextArea();
        
        // Simple Validation: // We only care about item description and value:
        if (itemDescription.equals("")) {
            ok = false;
            registerTravelClaim.setAddItemConfirmMessage("Fyll inn beskrivelse og verdi.");
        } else if (valueString.equals("")) {
            ok = false;
            registerTravelClaim.setAddItemConfirmMessage("Fyll inn beskrivelse og verdi.");
            
        } else {
            try {
                // Parse the String to int:
                value = Integer.parseInt(valueString);
                // We create a new claim item:
                ClaimItem claimItem = new ClaimItem(itemDescription, acquiredArea, acquiredDate, value, descriptionOfDocumentation);
                // We then add this item to a list in the claim view: (will be recieved when claim is registered)
                registerTravelClaim.addClaimItem(claimItem);
                registerTravelClaim.setAddItemConfirmMessage("Gjenstand lagt til.");
            } catch (NumberFormatException nfe) {
                registerTravelClaim.setAddItemConfirmMessage("Kun tallverdier er gyldig i verdifelt.");
                return;
            }
        }
    }
    
    private void travelClaimSearchCustomerIdButtonEventHandler(ActionEvent event) {
        String customerIdString = registerTravelClaim.getCustomerId();
        int customerId;
        if(customerIdString.equals("")) {
            // Gives the user an appropriate message if the user hasn't put in a customerId:
            registerTravelClaim.setCustomerArea(CUSTOMERID_EMPTY_MESSAGE);
            return;
        }
        try {
            // Converts the customerId to int:
            customerId = Integer.parseInt(customerIdString);
        } catch(NumberFormatException nfe) {
            logs.add(nfe.getStackTrace(), nfe.getMessage(), user);
            // Gives the user an appropriate message if the customerId wasn't formatted correctly:
            registerTravelClaim.setCustomerArea(CUSTOMERID_FORMAT_MESSAGE);
            return;
        }
        // TODO: Regex.
        // Searches for the customer by customerId:
        Customer customer = customers.findCustomerById(customerId);
        if(customer == null) {
            // Gives the user an appropriate message if the customer wasn't found:
            registerTravelClaim.setCustomerArea(CUSTOMERID_NOT_FOUND_MESSAGE + customerId);
        } else {
            // Displays the customer:
            registerTravelClaim.setCustomerArea(customer.toString());
            // Set this id as the selected one in the view class:
            registerTravelClaim.setSelectedCustomerId(customerId);
            // Finds the customers holiday home content insurances:
            List insuranceList = insurances.getAllActiveTypeInsurancesByCustomerId(customerId, 
                    TravelInsurance.class);
            if (!insuranceList.isEmpty()) {
                // Displays the insurances if there is at least one:
                registerTravelClaim.populateInsurancesTable(insuranceList);
            }
        }
    }
    
    private void travelClaimSearchPersonalNumberButtonEventHandler(ActionEvent event) {
        String personalNumber = registerTravelClaim.getPersonalNumber();
        if(personalNumber.equals("")) {
            // Gives the user an appropriate message if the user hasn't put in a personalNumber:
            registerTravelClaim.setCustomerArea(PERSONALNUMBER_EMPTY_MESSAGE);
        } else if (!personalNumber.matches("\\d{11}")) {
            // Gives the user an appropriate message:
            registerTravelClaim.setCustomerArea(PERSONALNUMBER_INCORRECT_MESSAGE);
        } else {
            // Searches for the customer by personalNumber:
            Customer customer = customers.findCustomerByPersonalNumber(personalNumber);
            if(customer == null) {
                // Gives the user an appropriate message if the customer wasn't found:
                registerTravelClaim.setCustomerArea(PERSONALNUMBER_NOT_FOUND_MESSAGE + personalNumber);
            } else {
                // Finds the customers insurances:
                int customerId = customer.getId();
                // Displays the customer:
                registerTravelClaim.setCustomerArea(customer.toString());
                // Set this id as the selected one in the view class:
                registerTravelClaim.setSelectedCustomerId(customerId);
                // Finds the customers holiday home content insurances:
                List insuranceList = insurances.getAllActiveTypeInsurancesByCustomerId(customerId, 
                        TravelInsurance.class);
                if (!insuranceList.isEmpty()) {
                    // Displays the insurances if there is at least one:
                    registerTravelClaim.populateInsurancesTable(insuranceList);
                }
            }
        }
    }
    
    private void travelClaimSelectInsuranceButtonEventHandler(ActionEvent event) {
        registerTravelClaim.clearMessages();
        Insurance insurance = registerTravelClaim.getInsuranceTableValue();
        String message = "";
        if (insurance instanceof TravelInsurance) {
            TravelInsuranceCoverage coverage = (TravelInsuranceCoverage) insurance.getCoverage();
            registerTravelClaim.setDamages(coverage.damages());
            message = insurance.getName() + " " + coverage.toString() + " er valgt.";
            registerTravelClaim.setSelectInsuranceMessage(message);
        } else {
            message = "Ingen forsikring valgt,";
            registerTravelClaim.setSelectInsuranceMessage(message);
        }
    }
    
    private void travelClaimSelectImageButtonEventHandler(ActionEvent event) {
        // Initialize a file chooser:
        FileChooser fileChooser = new FileChooser();
        // Set the title:
        fileChooser.setTitle("Velg bilde");
        // Set the initial directory to the user folder:
        fileChooser.setInitialDirectory(
                new File(System.getProperty("user.home"))
        );
        // Set selectable file extennsion:
        fileChooser.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter("All Images", "*.*"),
            new FileChooser.ExtensionFilter("JPG", "*.jpg"),
            new FileChooser.ExtensionFilter("GIF", "*.gif"),
            new FileChooser.ExtensionFilter("BMP", "*.bmp"),
            new FileChooser.ExtensionFilter("PNG", "*.png")
        );
        // Get the file:
        File file = fileChooser.showOpenDialog(primaryStage);
        if (file != null) {
            try {
                BufferedImage bufferedImage = ImageIO.read(file);
                Image image = SwingFXUtils.toFXImage(bufferedImage, null);
                // ! Sets this image to the image in field in the view:
                registerTravelClaim.setImage(image);
            } catch (IOException ioe) {
                logs.add(ioe.getStackTrace(), ioe.getMessage(), user);
            }
        }
    }
    
    // BOAT INSURANCE REGISTRATION EVENT HANDLERS
    
    private void boatInsuranceRegisterButtonEventHandler(ActionEvent e) {
        
        // Clears previous messages:
        registerBoatInsurance.clearMessages();
        
        // Collects information about the customer and the insurance:
        BoatInsuranceCoverage coverage = registerBoatInsurance.getCoverage();
        int customerId = registerBoatInsurance.getSelectedCustomerId();
        String excessString = registerBoatInsurance.getExcess();
        
        // Collects information about the boat:
        String alarmString = registerBoatInsurance.getAlarm();
        String brand = registerBoatInsurance.getBrand();
        String engineEffectString = registerBoatInsurance.getEngineEffect();
        String engineType = registerBoatInsurance.getEngineType();
        String lengthString = registerBoatInsurance.getLength();
        String model = registerBoatInsurance.getModel();
        String ownerPersonalNumber = registerBoatInsurance.getOwnerPersonalNumber();
        String registrationNumber = registerBoatInsurance.getRegistrationNumber();
        String registrationYearString = registerBoatInsurance.getRegistrationYear();
        String valueString = registerBoatInsurance.getValue();
        
        // Creates a boolean which is to be set true if the user has made a 
        // mistake and the method has to abort:
        boolean abort = false;
        
        // Creates ints and booleans for the converted values:
        boolean alarm = false;
        int engineEffect = 0;
        int excess = 0;
        int length = 0;
        int registrationYear = 0;
        int value = 0;
        
        // Evaluates Input:
        if(brand.equals("")) {
            registerBoatInsurance.setBrandMessage(EMPTY_MESSAGE);
            abort = true;
        }
        if(coverage == null) {
            registerBoatInsurance.setCoverageMessage(EMPTY_MESSAGE);
            abort = true;
        }
        if(customerId == 0) {
            registerBoatInsurance.setCustomerSelectedMessage(NO_CUSTOMER_MESSAGE);
            abort = true;
        }
        if(engineType.equals("")) {
            registerBoatInsurance.setEngineTypeMessage(EMPTY_MESSAGE);
            abort = true;
        }
        if(model.equals("")) {
            registerBoatInsurance.setModelMessage(EMPTY_MESSAGE);
            abort = true;
        }
        if(ownerPersonalNumber.equals("")) {
            registerBoatInsurance.setOwnerPersonalNumberMessage(EMPTY_MESSAGE);
        }
        if(registrationNumber.equals("")) {
            registerBoatInsurance.setRegistrationNumberMessage(EMPTY_MESSAGE);
        }
        
        // Evaluates and converts Input:
        if(alarmString.equals("")) {
            registerBoatInsurance.setAlarmMessage(EMPTY_MESSAGE);
            abort = true;
        } else {
            alarm = alarmString.equals("Ja");
        }
        if(engineEffectString.equals("")) {
            registerBoatInsurance.setEngineEffectMessage(EMPTY_MESSAGE);
            abort = true;
        } else {
            try {
                engineEffect = Integer.parseInt(engineEffectString);
            } catch(NumberFormatException nfe) {
                registerBoatInsurance.setEngineEffectMessage(FORMAT_MESSAGE);
                abort = true;
            }
        }
        if(excessString.equals("")) {
            registerBoatInsurance.setExcessMessage(EMPTY_MESSAGE);
            abort = true;
        } else {
            try {
                excess = Integer.parseInt(excessString);
            } catch(NumberFormatException nfe) {
                registerBoatInsurance.setExcessMessage(FORMAT_MESSAGE);
                abort = true;
            }
        }
        if(lengthString.equals("")) {
            registerBoatInsurance.setLengthMessage(EMPTY_MESSAGE);
            abort = true;
        } else {
            try {
                length = Integer.parseInt(lengthString);
            } catch(NumberFormatException nfe) {
                registerBoatInsurance.setLengthMessage(FORMAT_MESSAGE);
                abort = true;
            }
        }
        if(registrationYearString.equals("")) {
            registerBoatInsurance.setRegistrationYearMessage(EMPTY_MESSAGE);
            abort = true;
        } else {
            try {
                registrationYear = Integer.parseInt(registrationYearString);
            } catch(NumberFormatException nfe) {
                registerBoatInsurance.setRegistrationYearMessage(FORMAT_MESSAGE);
                abort = true;
            }
        }
        if(valueString.equals("")) {
            registerBoatInsurance.setValueMessage(EMPTY_MESSAGE);
            abort = true;
        } else {
            try {
                value = Integer.parseInt(valueString);
            } catch(NumberFormatException nfe) {
                registerBoatInsurance.setValueMessage(FORMAT_MESSAGE);
                abort = true;
            }
        }
        
        if(abort) {
            return;
        }
        
        // Creates Boat:
        Boat boat = new Boat(alarm, brand, engineEffect, engineType, length, 
                model, ownerPersonalNumber, registrationNumber, 
                registrationYear, value);
        
        // Creates BoatInsurance:
        BoatInsurance insurance = new BoatInsurance(boat, coverage, customerId, 
                excess);
        
        // Adds insurance to Register:
        if (insurances.addInsurance(insurance)) {
            registerBoatInsurance.setRegisterButtonMessage(REGISTER_SUCCESS + "\nForsikringspremie: " + insurance.getPremium() + ",-");
            registerBoatInsurance.clearView();
        } else {
            registerBoatInsurance.setRegisterButtonMessage(REGISTER_NO_SUCCESS);
        }
    }
    
    private void boatInsuranceSearchCustomerIdButtonEventHandler(ActionEvent event) {
        String customerIdString = registerBoatInsurance.getCustomerId();
        int customerId;
        if(customerIdString.equals("")) {
            // Gives the user an appropriate message if the user hasn't put in a customerId:
            registerBoatInsurance.setCustomerArea(CUSTOMERID_EMPTY_MESSAGE);
            return;
        }
        try {
            // Converts the customerId to int:
            customerId = Integer.parseInt(customerIdString);
        } catch(NumberFormatException nfe) {
            // Gives the user an appropriate message if the customerId wasn't formatted correctly:
            registerBoatInsurance.setCustomerArea(CUSTOMERID_FORMAT_MESSAGE);
            return;
        }
        // TODO: Regex.
        // Searches for the customer by customerId:
        Customer customer = customers.findCustomerById(customerId);
        if(customer == null) {
            // Gives the user an appropriate message if the customer wasn't found:
            registerBoatInsurance.setCustomerArea(CUSTOMERID_NOT_FOUND_MESSAGE + customerId);
        } else {
            // Displays the customer:
            registerBoatInsurance.setCustomerArea(customer.toString());
            registerBoatInsurance.setSelectedCustomerId(customerId);
            // Finds the customers insurances:
            List insuranceList = insurances.getAllInsurancesByCustomerId(customerId);
            if (!insuranceList.isEmpty()) {
                // Displays the insurances if there is at least one:
                registerBoatInsurance.populateInsurancesTable(insuranceList);
            }
        }
    }
    
    private void boatInsuranceSearchPersonalNumberButtonEventHandler(ActionEvent event) {
        String personalNumber = registerBoatInsurance.getPersonalNumber();
        if(personalNumber.equals("")) {
            // Gives the user an appropriate message if the user hasn't put in a personalNumber:
            registerBoatInsurance.setCustomerArea(PERSONALNUMBER_EMPTY_MESSAGE);
            return;
        }
        // TODO: Regex.
        // Searches for the customer by personalNumber:
        Customer customer = customers.findCustomerByPersonalNumber(personalNumber);
        if(customer == null) {
            // Gives the user an appropriate message if the customer wasn't found:
            registerBoatInsurance.setCustomerArea(PERSONALNUMBER_NOT_FOUND_MESSAGE + personalNumber);
        } else {
            // Finds the customers insurances:
            int customerId = customer.getId();
            // Displays the customer:
            registerBoatInsurance.setCustomerArea(customer.toString());
            registerBoatInsurance.setSelectedCustomerId(customerId);
            List insuranceList = insurances.getAllInsurancesByCustomerId(customerId);
            if (!insuranceList.isEmpty()) {
                // Displays the insurances if there is at least one:
                registerBoatInsurance.populateInsurancesTable(insuranceList);
            }
        }
    }
    
    // CAR INSURANCE REGISTRATION EVENT HANDLERS
    
    private void carInsuranceRegisterButtonEventHandler(ActionEvent event) {
        
        // Clears previous messages:
        registerCarInsurance.clearMessages();
        
        // Collects information about the customer and the insurance:
        CarInsuranceCoverage coverage = registerCarInsurance.getCoverage();
        int customerId = registerCarInsurance.getSelectedCustomerId();
        String excessString = registerCarInsurance.getExcess();
        String existingBonusString = registerCarInsurance.getExistingBonus();
        String garageString = registerCarInsurance.getParkingCondition();
        String maxLengthString = registerCarInsurance.getDrivingLength();
        String youngDriverString = registerCarInsurance.getYoungestDriver();
        
        // Collects information about the car:
        String alarmString = registerCarInsurance.getAlarm();
        String brand = registerCarInsurance.getBrand();
        String model = registerCarInsurance.getModel();
        String ownerPersonalNumber = registerCarInsurance.getOwnerPersonalNumber();
        String registrationNumber = registerCarInsurance.getRegistrationNumber();
        String registrationYearString = registerCarInsurance.getYear();
        
        // Creates a boolean which is to be set true if the user has made a 
        // mistake and the method has to abort:
        boolean abort = false;
        
        // Creates ints and booleans for the converted values:
        boolean alarm = false;
        boolean garage = false;
        boolean youngDriver = false;
        int excess = 0;
        int existingBonus = 0;
        int maxLength = 0;
        int registrationYear = 0;
        
        // Evaluates Input:
        if(brand.equals("")) {
            registerCarInsurance.setBrandMessage(EMPTY_MESSAGE);
            abort = true;
        }
        if(coverage == null) {
            registerCarInsurance.setCoverageMessage(EMPTY_MESSAGE);
            abort = true;
        }
        if(customerId == 0) {
            registerCarInsurance.setCustomerSelectedMessage(NO_CUSTOMER_MESSAGE);
            abort = true;
        }
        if(model.equals("")) {
            registerCarInsurance.setModelMessage(EMPTY_MESSAGE);
            abort = true;
        }
        if(ownerPersonalNumber.equals("")) {
            registerCarInsurance.setOwnerPersonalNumberMessage(EMPTY_MESSAGE);
        }
        if(registrationNumber.equals("")) {
            registerCarInsurance.setRegistrationNumberMessage(EMPTY_MESSAGE);
        }
        
        // Evaluates and converts Input:
        if(alarmString.equals("")) {
            registerCarInsurance.setAlarmMessage(EMPTY_MESSAGE);
            abort = true;
        } else {
            alarm = alarmString.equals("Ja");
        }
        if(excessString.equals("")) {
            registerCarInsurance.setExcessMessage(EMPTY_MESSAGE);
            abort = true;
        } else {
            try {
                excess = Integer.parseInt(excessString);
            } catch(NumberFormatException nfe) {
                registerCarInsurance.setExcessMessage(FORMAT_MESSAGE);
                abort = true;
            }
        }
        if(existingBonusString.equals("")) {
            registerCarInsurance.setExistingBonusMessage(EMPTY_MESSAGE);
            abort = true;
        } else {
            try {
                existingBonus = Integer.parseInt(existingBonusString);
            } catch(NumberFormatException nfe) {
                registerCarInsurance.setExistingBonusMessage(FORMAT_MESSAGE);
                abort = true;
            }
        }
        if(garageString.equals("")) {
            registerCarInsurance.setParkingConditionMessage(EMPTY_MESSAGE);
            abort = true;
        } else {
            garage = garageString.equals("Har garasje");
        }
        if(maxLengthString.equals("")) {
            registerCarInsurance.setDrivingLengthMessage(EMPTY_MESSAGE);
            abort = true;
        } else if (maxLengthString.equals(registerCarInsurance.MAX_LENGTH_UNLIMITED)){
            maxLength = -1; // Set max length value to -1 (equals unlimited)
        } else {
            try {
                maxLength = Integer.parseInt(maxLengthString);
            } catch(NumberFormatException nfe) {
                registerCarInsurance.setDrivingLengthMessage(FORMAT_MESSAGE);
                abort = true;
            }
        }
        if(registrationYearString.equals("")) {
            registerCarInsurance.setYearMessage(EMPTY_MESSAGE);
            abort = true;
        } else {
            try {
                registrationYear = Integer.parseInt(registrationYearString);
            } catch(NumberFormatException nfe) {
                registerCarInsurance.setYearMessage(FORMAT_MESSAGE);
                abort = true;
            }
        }
        if(youngDriverString.equals("")) {
            registerCarInsurance.setYoungestDriverMessage(EMPTY_MESSAGE);
            abort = true;
        } else {
            youngDriver = youngDriverString.equals("Under 25");
        }
        
        if(abort) {
            return;
        }
        
        // Creates car:
        Car car = new Car(alarm, brand, model, ownerPersonalNumber, 
                registrationNumber, registrationYear);
        
        // Creates CarInsurance:
        CarInsurance insurance = new CarInsurance(car, coverage, customerId, 
                excess, existingBonus, garage, maxLength, youngDriver);
        // Calculate and set the premium:
        insurance.calculatePremium();
        
        // Adds insurance to Register:
        if (insurances.addInsurance(insurance)) {
            registerCarInsurance.setRegisterButtonMessage(REGISTER_SUCCESS + "\nForsikringspremie: " + insurance.getPremium() + ",-");
            registerCarInsurance.clearView();
        } else {
            registerCarInsurance.setRegisterButtonMessage(REGISTER_NO_SUCCESS);
        }
    }
    
    private void setBrandComboBox() {
        List cars = modelController.getCarInfos();
        registerCarInsurance.populateBrandCombo(cars);
    }
    
    private void setYearComboBox() {
        int since = 1970;
        int to = Calendar.getInstance().get(Calendar.YEAR);
        List<String> years = new LinkedList();
        for (int i = since; i <= to; i++) {
            years.add(i + "");
        }
        
        registerCarInsurance.populateYearCombo(years);
    }
    
    private void brandComboListener(ObservableValue observable, Object oldValue, Object newValue) {
        Object value = newValue;
        CarInfo car;
        int since = Integer.MAX_VALUE;
        int to = Integer.MIN_VALUE;
        if (value instanceof CarInfo) {
            car = (CarInfo) value;
            List<ModelInfo> models = car.getModelRegister().getModels();
            
            for (ModelInfo model : models) {
                if (model != null) {
                    // some car models have a since value equal to a model e.g since 9000, hence the extra check
                    if (model.getSince() < since && model.getSince() > 1900 && model.getSince() < Calendar.getInstance().get(Calendar.YEAR)) { 
                        since = model.getSince();
                    }
                    
                    if (model.getTo() > to) {
                        to = model.getTo();
                    }
                    if (model.getTo() == 0) {
                        to = Integer.MAX_VALUE;
                    }
                }
            }
        } else if (value instanceof String){
            // do nothing.
        }
        if (to == Integer.MAX_VALUE) {
            to = Calendar.getInstance().get(Calendar.YEAR);
        }
            
        if (since < 1930 || since > Calendar.getInstance().get(Calendar.YEAR)) {
            since = 1930;
        }        
        List<String> years = new LinkedList();
        // Populates the list with the years ranging between oldest and newest models in reverse order.
        // Newer years come first, older years come last.
        for (int i = to; i >= since; i--) {
                years.add(i + "");
        }
        
        // Whenever a change in the combox is done, this method gets called twice, hence this check to
        // avoid creating a complete list, for so to replace it with an empty list.
        if (!years.isEmpty()) {
            registerCarInsurance.populateYearCombo(years);
        }
        
    }
    
    private void yearComboListener(ObservableValue observable, Object oldValue, Object newValue) {
        Object value = newValue;
        String yearString;
        int year;
        CarInfo car = new CarInfo(); // new initialization will be set in one of the 2 next if statements
        String brandName;
        Object brandValue = registerCarInsurance.getBrandComboValue();
        if (brandValue instanceof CarInfo) {
            car = (CarInfo) brandValue;
        } else if (brandValue instanceof String) { // Custom/user defined car brand entered:
           brandName = (String) brandValue;
           car = modelController.findCarInfo(brandName);
           if (brandName == null) { // Custom/user defined brand does not match any cars in the info register.
               return;
           }
        }
        
        if (value instanceof String) {
            yearString = (String) value;
            try {
                year = Integer.parseInt(yearString);
                
                List<ModelInfo> models = car.getModelRegister().getModels();
                List<String> modelsResult = new LinkedList();
                for (ModelInfo model : models) {
                    if (model != null) {
                        int to = model.getTo();
                        int since = model.getSince();
                        if (since < 1945 || since > Calendar.getInstance().get(Calendar.YEAR)) {
                            since = Integer.MAX_VALUE;
                        }
                        if (model.getTo() == 0) {
                            to = Calendar.getInstance().get(Calendar.YEAR);
                        }
                        if (year >= since && year <= to) {
                            modelsResult.add(model.getName());
                        }
                    }
                }
                registerCarInsurance.populateModelCombo(modelsResult);
  
            } catch (NumberFormatException nfe) {
                //setSomeMessage
                return;
            }
        }
    }
    
    private void carInsuranceSearchCustomerIdButtonEventHandler(ActionEvent event) {
        String customerIdString = registerCarInsurance.getCustomerId();
        int customerId;
        if(customerIdString.equals("")) {
            // Gives the user an appropriate message if the user hasn't put in a customerId:
            registerCarInsurance.setCustomerArea(CUSTOMERID_EMPTY_MESSAGE);
            return;
        }
        try {
            // Converts the customerId to int:
            customerId = Integer.parseInt(customerIdString);
        } catch(NumberFormatException nfe) {
            // Gives the user an appropriate message if the customerId wasn't formatted correctly:
            registerCarInsurance.setCustomerArea(CUSTOMERID_FORMAT_MESSAGE);
            return;
        }
        // TODO: Regex.
        // Searches for the customer by customerId:
        Customer customer = customers.findCustomerById(customerId);
        if(customer == null) {
            // Gives the user an appropriate message if the customer wasn't found:
            registerCarInsurance.setCustomerArea(CUSTOMERID_NOT_FOUND_MESSAGE + customerId);
        } else {
            // Displays the customer:
            registerCarInsurance.setCustomerArea(customer.toString());
            registerCarInsurance.setSelectedCustomerId(customerId);
            // Finds the customers insurances:
            List insuranceList = insurances.getAllInsurancesByCustomerId(customerId);
            if (!insuranceList.isEmpty()) {
                // Displays the insurances if there is at least one:
                registerCarInsurance.populateInsurancesTable(insuranceList);
            }
        }
    }
    
    private void carInsuranceSearchPersonalNumberButtonEventHandler(ActionEvent event) {
        String personalNumber = registerCarInsurance.getPersonalNumber();
        if(personalNumber.equals("")) {
            // Gives the user an appropriate message if the user hasn't put in a personalNumber:
            registerCarInsurance.setCustomerArea(PERSONALNUMBER_EMPTY_MESSAGE);
            return;
        }
        // TODO: Regex.
        // Searches for the customer by personalNumber:
        Customer customer = customers.findCustomerByPersonalNumber(personalNumber);
        if(customer == null) {
            // Gives the user an appropriate message if the customer wasn't found:
            registerCarInsurance.setCustomerArea(PERSONALNUMBER_NOT_FOUND_MESSAGE + personalNumber);
        } else {
            // Finds the customers insurances:
            int customerId = customer.getId();
            // Displays the customer:
            registerCarInsurance.setCustomerArea(customer.toString());
            registerCarInsurance.setSelectedCustomerId(customerId);
            List insuranceList = insurances.getAllInsurancesByCustomerId(customerId);
            if (!insuranceList.isEmpty()) {
                // Displays the insurances if there is at least one:
                registerCarInsurance.populateInsurancesTable(insuranceList);
            }
        }
    }
    
    // HOLIDAY HOME INSURANCE EVENT HANDLERS
    
    private void holidayHomeInsuranceRegisterButtonEventHandler(ActionEvent e) {
        
        // Clears previous messages:
        registerHolidayHomeInsurance.clearMessages();
        
        // Collects information about the customer and the insurance:
        HolidayHomeInsuranceCoverage coverage = registerHolidayHomeInsurance.getCoverage();
        int customerId = registerHolidayHomeInsurance.getSelectedCustomerId();
        String excessString = registerHolidayHomeInsurance.getExcess();
        
        // Collects information about the property:
        HolidayHomeType type = registerHolidayHomeInsurance.getType();
        PropertyMaterial material = registerHolidayHomeInsurance.getMaterial();
        String areaString = registerHolidayHomeInsurance.getArea();
        String city = registerHolidayHomeInsurance.getCity();
        String rentalString = registerHolidayHomeInsurance.getRental();
        String street = registerHolidayHomeInsurance.getStreet();
        String yearString = registerHolidayHomeInsurance.getYear();
        String zipCodeString = registerHolidayHomeInsurance.getZipCode();
        
        // Creates a boolean which is to be set true if the user has made a 
        // mistake and the method has to abort:
        boolean abort = false;
        
        // Creates ints and booleans for the converted values:
        int area = 0;
        int excess = 0;
        boolean rental = false;
        int year = 0;
        int zipCode = 0;
        
        // Evaluates Input:
        if(city.equals("")) {
            registerHolidayHomeInsurance.setCityMessage(EMPTY_MESSAGE);
            abort = true;
        }
        if(coverage == null) {
            registerHolidayHomeInsurance.setCoverageMessage(EMPTY_MESSAGE);
            abort = true;
        }
        if(customerId == 0) {
            registerHolidayHomeInsurance.setCustomerSelectedMessage(NO_CUSTOMER_MESSAGE);
            abort = true;
        }
        if(material == null) {
            registerHolidayHomeInsurance.setMaterialMessage(EMPTY_MESSAGE);
            abort = true;
        }
        if(street.equals("")) {
            registerHolidayHomeInsurance.setStreetMessage(EMPTY_MESSAGE);
            abort = true;
        }
        if(type == null) {
            registerHolidayHomeInsurance.setTypeMessage(EMPTY_MESSAGE);
            abort = true;
        }
        
        // Evaluates and converts Input:
        if(areaString.equals("")) {
            registerHolidayHomeInsurance.setAreaMessage(EMPTY_MESSAGE);
            abort = true;
        } else {
            try {
                area = Integer.parseInt(areaString);
            } catch(NumberFormatException nfe) {
                registerHolidayHomeInsurance.setAreaMessage(FORMAT_MESSAGE);
                abort = true;
            }
        }
        if(excessString.equals("")) {
            registerHolidayHomeInsurance.setExcessMessage(EMPTY_MESSAGE);
            abort = true;
        } else {
            try {
                excess = Integer.parseInt(excessString);
            } catch(NumberFormatException nfe) {
                registerHolidayHomeInsurance.setExcessMessage(FORMAT_MESSAGE);
                abort = true;
            }
        }
        if(rentalString.equals("")) {
            registerHolidayHomeInsurance.setRentalMessage(EMPTY_MESSAGE);
            abort = true;
        } else {
            rental = rentalString.equals("Ja");
        }
        if(yearString.equals("")) {
            registerHolidayHomeInsurance.setYearMessage(EMPTY_MESSAGE);
            abort = true;
        } else {
            try {
                year = Integer.parseInt(yearString);
            } catch(NumberFormatException nfe) {
                registerHolidayHomeInsurance.setYearMessage(FORMAT_MESSAGE);
                abort = true;
            }
        }
        if(zipCodeString.equals("")) {
            registerHolidayHomeInsurance.setZipCodeMessage(EMPTY_MESSAGE);
            abort = true;
        } else {
            try {
                zipCode = Integer.parseInt(zipCodeString);
            } catch(NumberFormatException nfe) {
                registerHolidayHomeInsurance.setZipCodeMessage(FORMAT_MESSAGE);
                abort = true;
            }
        }
        
        if(abort) {
            return;
        }
        
        // Creates Address:
        Address address = new Address(street, zipCode, city);
        // Creates Property:
        Property property = new Property(address, material, area, year);
        // Creates HolidayHomeInsurance:
        HolidayHomeInsurance insurance = new HolidayHomeInsurance(coverage, 
                customerId, excess, property, rental, type);
        
        // Adds insurance to Register:
        if (insurances.addInsurance(insurance)) {
            registerHolidayHomeInsurance.setRegisterButtonMessage(REGISTER_SUCCESS + "\nForsikringspremie: " + insurance.getPremium() + ",-");
            registerHolidayHomeInsurance.setRegisterButtonMessage("");
        } else {
            registerHolidayHomeInsurance.setRegisterButtonMessage(REGISTER_NO_SUCCESS);
        }
    }
    
    private void holidayHomeInsuranceSearchCustomerIdButtonEventHandler(ActionEvent event) {
        String customerIdString = registerHolidayHomeInsurance.getCustomerId();
        int customerId;
        if(customerIdString.equals("")) {
            // Gives the user an appropriate message if the user hasn't put in a customerId:
            registerHolidayHomeInsurance.setCustomerArea(CUSTOMERID_EMPTY_MESSAGE);
            return;
        }
        try {
            // Converts the customerId to int:
            customerId = Integer.parseInt(customerIdString);
        } catch(NumberFormatException nfe) {
            // Gives the user an appropriate message if the customerId wasn't formatted correctly:
            registerHolidayHomeInsurance.setCustomerArea(CUSTOMERID_FORMAT_MESSAGE);
            return;
        }
        // TODO: Regex.
        // Searches for the customer by customerId:
        Customer customer = customers.findCustomerById(customerId);
        if(customer == null) {
            // Gives the user an appropriate message if the customer wasn't found:
            registerHolidayHomeInsurance.setCustomerArea(CUSTOMERID_NOT_FOUND_MESSAGE + customerId);
        } else {
            // Displays the customer:
            registerHolidayHomeInsurance.setCustomerArea(customer.toString());
            registerHolidayHomeInsurance.setSelectedCustomerId(customerId);
            // Finds the customers insurances:
            List insuranceList = insurances.getAllInsurancesByCustomerId(customerId);
            if (!insuranceList.isEmpty()) {
                // Displays the insurances if there is at least one:
                registerHolidayHomeInsurance.populateInsurancesTable(insuranceList);
            }
        }
    } // end of method holidayHomeInsuranceSearchCustomerIdButtonEventHandler
    
    private void holidayHomeInsuranceSearchPersonalNumberButtonEventHandler(ActionEvent event) {
        String personalNumber = registerHolidayHomeInsurance.getPersonalNumber();
        if(personalNumber.equals("")) {
            // Gives the user an appropriate message if the user hasn't put in a personalNumber:
            registerHolidayHomeInsurance.setCustomerArea(PERSONALNUMBER_EMPTY_MESSAGE);
            return;
        }
        // TODO: Regex.
        // Searches for the customer by personalNumber:
        Customer customer = customers.findCustomerByPersonalNumber(personalNumber);
        if(customer == null) {
            // Gives the user an appropriate message if the customer wasn't found:
            registerHolidayHomeInsurance.setCustomerArea(PERSONALNUMBER_NOT_FOUND_MESSAGE + personalNumber);
        } else {
            // Finds the customers insurances:
            int customerId = customer.getId();
            // Displays the customer:
            registerHolidayHomeInsurance.setCustomerArea(customer.toString());
            registerHolidayHomeInsurance.setSelectedCustomerId(customerId);
            List insuranceList = insurances.getAllInsurancesByCustomerId(customerId);
            if (!insuranceList.isEmpty()) {
                // Displays the insurances if there is at least one:
                registerHolidayHomeInsurance.populateInsurancesTable(insuranceList);
            }
        }
    } // end of method holidayHomeInsuranceSearchPersonalNumberButtonEventHandler
    
    // HOLIDAY HOME CONTENT INSURANCE EVENT HANDLERS
    
    private void holidayHomeContentInsuranceRegisterButtonEventHandler(ActionEvent e) {
        
        // Clears previous messages:
        registerHolidayHomeContentInsurance.clearMessages();
        
        // Collects information about the customer and the insurance:
        HolidayHomeContentInsuranceCoverage coverage = registerHolidayHomeContentInsurance.getCoverage();
        int customerId = registerHolidayHomeContentInsurance.getSelectedCustomerId();
        String excessString = registerHolidayHomeContentInsurance.getExcess();
        
        // Collects information about the property:
        HolidayHomeType type = registerHolidayHomeContentInsurance.getType();
        PropertyMaterial material = registerHolidayHomeContentInsurance.getMaterial();
        String amountString = registerHolidayHomeContentInsurance.getAmount();
        String areaString = registerHolidayHomeContentInsurance.getArea();
        String city = registerHolidayHomeContentInsurance.getCity();
        String street = registerHolidayHomeContentInsurance.getStreet();
        String yearString = registerHolidayHomeContentInsurance.getYear();
        String zipCodeString = registerHolidayHomeContentInsurance.getZipCode();
        
        // Creates a boolean which is to be set true if the user has made a 
        // mistake and the method has to abort:
        boolean abort = false;
        
        // Creates ints and booleans for the converted values:
        int amount = 0;
        int area = 0;
        int excess = 0;
        int year = 0;
        int zipCode = 0;
        
        // Evaluates Input:
        if(city.equals("")) {
            registerHolidayHomeContentInsurance.setCityMessage(EMPTY_MESSAGE);
            abort = true;
        }
        if(coverage == null) {
            registerHolidayHomeContentInsurance.setCoverageMessage(EMPTY_MESSAGE);
            abort = true;
        }
        if(customerId == 0) {
            registerHolidayHomeContentInsurance.setCustomerSelectedMessage(NO_CUSTOMER_MESSAGE);
            abort = true;
        }
        if(material == null) {
            registerHolidayHomeContentInsurance.setMaterialMessage(EMPTY_MESSAGE);
            abort = true;
        }
        if(street.equals("")) {
            registerHolidayHomeContentInsurance.setStreetMessage(EMPTY_MESSAGE);
            abort = true;
        }
        if(type == null) {
            registerHolidayHomeContentInsurance.setTypeMessage(EMPTY_MESSAGE);
            abort = true;
        }
        
        // Evaluates and converts Input:
        if(areaString.equals("")) {
            registerHolidayHomeContentInsurance.setAreaMessage(EMPTY_MESSAGE);
            abort = true;
        } else {
            try {
                area = Integer.parseInt(areaString);
            } catch(NumberFormatException nfe) {
                registerHolidayHomeContentInsurance.setAreaMessage(FORMAT_MESSAGE);
                abort = true;
            }
        }
        if(amountString.equals("")) {
            registerHomeContentInsurance.setAmountMessage(EMPTY_MESSAGE);
            abort = true;
        } else {
            try {
                amount = Integer.parseInt(amountString);
            } catch(NumberFormatException nfe) {
                registerHomeContentInsurance.setAmountMessage(FORMAT_MESSAGE);
                abort = true;
            }
        }
        if(excessString.equals("")) {
            registerHolidayHomeContentInsurance.setExcessMessage(EMPTY_MESSAGE);
            abort = true;
        } else {
            try {
                excess = Integer.parseInt(excessString);
            } catch(NumberFormatException nfe) {
                registerHolidayHomeContentInsurance.setExcessMessage(FORMAT_MESSAGE);
                abort = true;
            }
        }
        if(yearString.equals("")) {
            registerHolidayHomeContentInsurance.setYearMessage(EMPTY_MESSAGE);
            abort = true;
        } else {
            try {
                year = Integer.parseInt(yearString);
            } catch(NumberFormatException nfe) {
                registerHolidayHomeContentInsurance.setYearMessage(FORMAT_MESSAGE);
                abort = true;
            }
        }
        if(zipCodeString.equals("")) {
            registerHolidayHomeContentInsurance.setZipCodeMessage(EMPTY_MESSAGE);
            abort = true;
        } else {
            try {
                zipCode = Integer.parseInt(zipCodeString);
            } catch(NumberFormatException nfe) {
                registerHolidayHomeContentInsurance.setZipCodeMessage(FORMAT_MESSAGE);
                abort = true;
            }
        }
        
        if(abort) {
            return;
        }
        
        // Creates Address:
        Address address = new Address(street, zipCode, city);
        // Creates Property:
        Property property = new Property(address, material, area, year);
        // Creates HolidayHomeContentInsurance:
        HolidayHomeContentInsurance insurance = new HolidayHomeContentInsurance(
                amount, coverage, customerId, excess, property, type);
        
        // Adds insurance to Register:
        if (insurances.addInsurance(insurance)) {
            registerHolidayHomeContentInsurance.setRegisterButtonMessage(REGISTER_SUCCESS + "\nForsikringspremie: " + insurance.getPremium() + ",-");
            registerHolidayHomeContentInsurance.clearView();
        } else {
            registerHolidayHomeContentInsurance.setRegisterButtonMessage(REGISTER_NO_SUCCESS);
        }
    } // end of method holidayHomeContentInsuranceRegisterButtonEventHandler
    
    private void holidayHomeContentInsuranceSearchCustomerIdButtonEventHandler(ActionEvent event) {
        String customerIdString = registerHolidayHomeContentInsurance.getCustomerId();
        int customerId;
        if(customerIdString.equals("")) {
            // Gives the user an appropriate message if the user hasn't put in a customerId:
            registerHolidayHomeContentInsurance.setCustomerArea(CUSTOMERID_EMPTY_MESSAGE);
            return;
        }
        try {
            // Converts the customerId to int:
            customerId = Integer.parseInt(customerIdString);
        } catch(NumberFormatException nfe) {
            // Gives the user an appropriate message if the customerId wasn't formatted correctly:
            registerHolidayHomeContentInsurance.setCustomerArea(CUSTOMERID_FORMAT_MESSAGE);
            return;
        }
        // TODO: Regex.
        // Searches for the customer by customerId:
        Customer customer = customers.findCustomerById(customerId);
        if(customer == null) {
            // Gives the user an appropriate message if the customer wasn't found:
            registerHolidayHomeContentInsurance.setCustomerArea(CUSTOMERID_NOT_FOUND_MESSAGE + customerId);
        } else {
            // Displays the customer:
            registerHolidayHomeContentInsurance.setCustomerArea(customer.toString());
            registerHolidayHomeContentInsurance.setSelectedCustomerId(customerId);
            // Finds the customers insurances:
            List insuranceList = insurances.getAllInsurancesByCustomerId(customerId);
            if (!insuranceList.isEmpty()) {
                // Displays the insurances if there is at least one:
                registerHolidayHomeContentInsurance.populateInsurancesTable(insuranceList);
            }
        }
    } // end of method holidayHomeContentInsuranceSearchCustomerIdButtonEventHandler
    
    private void holidayHomeContentInsuranceSearchPersonalNumberButtonEventHandler(ActionEvent event) {
        String personalNumber = registerHolidayHomeContentInsurance.getPersonalNumber();
        if(personalNumber.equals("")) {
            // Gives the user an appropriate message if the user hasn't put in a personalNumber:
            registerHolidayHomeContentInsurance.setCustomerArea(PERSONALNUMBER_EMPTY_MESSAGE);
            return;
        }
        // TODO: Regex.
        // Searches for the customer by personalNumber:
        Customer customer = customers.findCustomerByPersonalNumber(personalNumber);
        if(customer == null) {
            // Gives the user an appropriate message if the customer wasn't found:
            registerHolidayHomeContentInsurance.setCustomerArea(PERSONALNUMBER_NOT_FOUND_MESSAGE + personalNumber);
        } else {
            // Finds the customers insurances:
            int customerId = customer.getId();
            // Displays the customer:
            registerHolidayHomeContentInsurance.setCustomerArea(customer.toString());
            registerHolidayHomeContentInsurance.setSelectedCustomerId(customerId);
            List insuranceList = insurances.getAllInsurancesByCustomerId(customerId);
            if (!insuranceList.isEmpty()) {
                // Displays the insurances if there is at least one:
                registerHolidayHomeContentInsurance.populateInsurancesTable(insuranceList);
            }
        }
    } // end of method holidayHomeContentInsuranceSearchPersonalNumberButtonEventHandler
    
    // HOME INSURANCE EVENT HANDLERS
    
    private void homeInsuranceRegisterButtonEventHandler(ActionEvent e) {
        
        // Clears previous messages:
        registerHomeInsurance.clearMessages();
        
        // Collects information about the customer and the insurance:
        HomeInsuranceCoverage coverage = registerHomeInsurance.getCoverage();
        int customerId = registerHomeInsurance.getSelectedCustomerId();
        String excessString = registerHomeInsurance.getExcess();
        
        // Collects information about the property:
        HomeType type = registerHomeInsurance.getType();
        PropertyMaterial material = registerHomeInsurance.getMaterial();
        String areaString = registerHomeInsurance.getArea();
        String city = registerHomeInsurance.getCity();
        String rentalString = registerHomeInsurance.getRental();
        String street = registerHomeInsurance.getStreet();
        String yearString = registerHomeInsurance.getYear();
        String zipCodeString = registerHomeInsurance.getZipCode();
        
        // Creates a boolean which is to be set true if the user has made a 
        // mistake and the method has to abort:
        boolean abort = false;
        
        // Creates ints and booleans for the converted values:
        int area = 0;
        int excess = 0;
        boolean rental = false;
        int year = 0;
        int zipCode = 0;
        
        // Evaluates Input:
        if(city.equals("")) {
            registerHomeInsurance.setCityMessage(EMPTY_MESSAGE);
            abort = true;
        }
        if(coverage == null) {
            registerHomeInsurance.setCoverageMessage(EMPTY_MESSAGE);
            abort = true;
        }
        if(customerId == 0) {
            registerHomeInsurance.setCustomerSelectedMessage(NO_CUSTOMER_MESSAGE);
            abort = true;
        }
        if(material == null) {
            registerHomeInsurance.setMaterialMessage(EMPTY_MESSAGE);
            abort = true;
        }
        if(street.equals("")) {
            registerHomeInsurance.setStreetMessage(EMPTY_MESSAGE);
            abort = true;
        }
        if(type == null) {
            registerHomeInsurance.setTypeMessage(EMPTY_MESSAGE);
            abort = true;
        }
        
        // Evaluates and converts Input:
        if(areaString.equals("")) {
            registerHomeInsurance.setAreaMessage(EMPTY_MESSAGE);
            abort = true;
        } else {
            try {
                area = Integer.parseInt(areaString);
            } catch(NumberFormatException nfe) {
                registerHomeInsurance.setAreaMessage(FORMAT_MESSAGE);

                abort = true;
            }
        }
        if(excessString.equals("")) {
            registerHomeInsurance.setExcessMessage(EMPTY_MESSAGE);
            abort = true;
        } else {
            try {
                excess = Integer.parseInt(excessString);
            } catch(NumberFormatException nfe) {
                registerHomeInsurance.setExcessMessage(FORMAT_MESSAGE);
                abort = true;
            }
        }
        if(rentalString.equals("")) {
            registerHomeInsurance.setRentalMessage(EMPTY_MESSAGE);
            abort = true;
        } else {
            rental = rentalString.equals("Ja");
        }
        if(yearString.equals("")) {
            registerHomeInsurance.setYearMessage(EMPTY_MESSAGE);
            abort = true;
        } else {
            try {
                year = Integer.parseInt(yearString);
            } catch(NumberFormatException nfe) {
                registerHomeInsurance.setYearMessage(FORMAT_MESSAGE);
                abort = true;
            }
        }
        if(zipCodeString.equals("")) {
            registerHomeInsurance.setZipCodeMessage(EMPTY_MESSAGE);
            abort = true;
        } else {
            try {
                zipCode = Integer.parseInt(zipCodeString);
            } catch(NumberFormatException nfe) {
                registerHomeInsurance.setZipCodeMessage(FORMAT_MESSAGE);
                abort = true;
            }
        }
        
        if(abort) {
            return;
        }
        
        // Creates Address:
        Address address = new Address(street, zipCode, city);
        // Creates Property:
        Property property = new Property(address, material, area, year);
        // Creates HomeInsurance:
        HomeInsurance insurance = new HomeInsurance(coverage, customerId, 
                excess, property, rental, type);
        
        // Adds insurance to Register:
        if (insurances.addInsurance(insurance)) {
            registerHomeInsurance.setRegisterButtonMessage(REGISTER_SUCCESS + "\nForsikringspremie: " + insurance.getPremium() + ",-");
            registerHomeInsurance.clearView();
        } else {
            registerHomeInsurance.setRegisterButtonMessage(REGISTER_NO_SUCCESS);
        }
    } // end of method homeInsuranceRegisterButtonEventHandler
    
    private void homeInsuranceSearchCustomerIdButtonEventHandler(ActionEvent event) {
        String customerIdString = registerHomeInsurance.getCustomerId();
        int customerId;
        if(customerIdString.equals("")) {
            // Gives the user an appropriate message if the user hasn't put in a customerId:
            registerHomeInsurance.setCustomerArea(CUSTOMERID_EMPTY_MESSAGE);
            return;
        }
        try {
            // Converts the customerId to int:
            customerId = Integer.parseInt(customerIdString);
        } catch(NumberFormatException nfe) {
            // Gives the user an appropriate message if the customerId wasn't formatted correctly:
            registerHomeInsurance.setCustomerArea(CUSTOMERID_FORMAT_MESSAGE);
            return;
        }
        // TODO: Regex.
        // Searches for the customer by customerId:
        Customer customer = customers.findCustomerById(customerId);
        if(customer == null) {
            // Gives the user an appropriate message if the customer wasn't found:
            registerHomeInsurance.setCustomerArea(CUSTOMERID_NOT_FOUND_MESSAGE + customerId);
        } else {
            // Displays the customer:
            registerHomeInsurance.setCustomerArea(customer.toString());
            registerHomeInsurance.setSelectedCustomerId(customerId);
            // Finds the customers insurances:
            List insuranceList = insurances.getAllInsurancesByCustomerId(customerId);
            if (!insuranceList.isEmpty()) {
                // Displays the insurances if there is at least one:
                registerHomeInsurance.populateInsurancesTable(insuranceList);
            }
        }
    } // end of method homeInsuranceSearchCustomerIdButtonEventHandler
    
    private void homeInsuranceSearchPersonalNumberButtonEventHandler(ActionEvent event) {
        String personalNumber = registerHomeInsurance.getPersonalNumber();
        if(personalNumber.equals("")) {
            // Gives the user an appropriate message if the user hasn't put in a personalNumber:
            registerHomeInsurance.setCustomerArea(PERSONALNUMBER_EMPTY_MESSAGE);
            return;
        }
        // TODO: Regex.
        // Searches for the customer by personalNumber:
        Customer customer = customers.findCustomerByPersonalNumber(personalNumber);
        if(customer == null) {
            // Gives the user an appropriate message if the customer wasn't found:
            registerHomeInsurance.setCustomerArea(PERSONALNUMBER_NOT_FOUND_MESSAGE + personalNumber);
        } else {
            // Finds the customers insurances:
            int customerId = customer.getId();
            // Displays the customer:
            registerHomeInsurance.setCustomerArea(customer.toString());
            registerHomeInsurance.setSelectedCustomerId(customerId);
            List insuranceList = insurances.getAllInsurancesByCustomerId(customerId);
            if (!insuranceList.isEmpty()) {
                // Displays the insurances if there is at least one:
                registerHomeInsurance.populateInsurancesTable(insuranceList);
            }
        }
    } // end of method homeInsuranceSearchPersonalNumberButtonEventHandler
    
    // HOME CONTENT INSURANCE EVENT HANDLERS
    
    private void homeContentInsuranceRegisterButtonEventHandler(ActionEvent e) {
        
        // Clears previous messages:
        registerHomeContentInsurance.clearMessages();
        
        // Collects information about the customer and the insurance:
        HomeContentInsuranceCoverage coverage = registerHomeContentInsurance.getCoverage();
        int customerId = registerHomeContentInsurance.getSelectedCustomerId();
        String amountString = registerHomeContentInsurance.getAmount();
        String excessString = registerHomeContentInsurance.getExcess();
        
        // Collects information about the property:
        HomeType type = registerHomeContentInsurance.getType();
        PropertyMaterial material = registerHomeContentInsurance.getMaterial();
        String areaString = registerHomeContentInsurance.getArea();
        String city = registerHomeContentInsurance.getCity();
        String street = registerHomeContentInsurance.getStreet();
        String yearString = registerHomeContentInsurance.getYear();
        String zipCodeString = registerHomeContentInsurance.getZipCode();
        
        // Creates a boolean which is to be set true if the user has made a 
        // mistake and the method has to abort:
        boolean abort = false;
        
        // Creates ints and booleans for the converted values:
        int amount = 0;
        int area = 0;
        int excess = 0;
        int year = 0;
        int zipCode = 0;
        
        // Evaluates Input:
        if(city.equals("")) {
            registerHomeContentInsurance.setCityMessage(EMPTY_MESSAGE);
            abort = true;
        }
        if(coverage == null) {
            registerHomeContentInsurance.setCoverageMessage(EMPTY_MESSAGE);
            abort = true;
        }
        if(customerId == 0) {
            registerHomeContentInsurance.setCustomerSelectedMessage(NO_CUSTOMER_MESSAGE);
            abort = true;
        }
        if(material == null) {
            registerHomeContentInsurance.setMaterialMessage(EMPTY_MESSAGE);
            abort = true;
        }
        if(street.equals("")) {
            registerHomeContentInsurance.setStreetMessage(EMPTY_MESSAGE);
            abort = true;
        }
        if(type == null) {
            registerHomeContentInsurance.setTypeMessage(EMPTY_MESSAGE);
            abort = true;
        }
        
        // Evaluates and converts Input:
        if(areaString.equals("")) {
            registerHomeContentInsurance.setAreaMessage(EMPTY_MESSAGE);
            abort = true;
        } else {
            try {
                area = Integer.parseInt(areaString);
            } catch(NumberFormatException nfe) {
                registerHomeContentInsurance.setAreaMessage(FORMAT_MESSAGE);
                abort = true;
            }
        }
        if(amountString.equals("")) {
            registerHomeContentInsurance.setAmountMessage(EMPTY_MESSAGE);
            abort = true;
        } else {
            try {
                amount = Integer.parseInt(amountString);
            } catch(NumberFormatException nfe) {
                registerHomeContentInsurance.setAmountMessage(FORMAT_MESSAGE);
                abort = true;
            }
        }
        if(excessString.equals("")) {
            registerHomeContentInsurance.setExcessMessage(EMPTY_MESSAGE);
            abort = true;
        } else {
            try {
                excess = Integer.parseInt(excessString);
            } catch(NumberFormatException nfe) {
                registerHomeContentInsurance.setExcessMessage(FORMAT_MESSAGE);
                abort = true;
            }
        }
        if(yearString.equals("")) {
            registerHomeContentInsurance.setYearMessage(EMPTY_MESSAGE);
            abort = true;
        } else {
            try {
                year = Integer.parseInt(yearString);
            } catch(NumberFormatException nfe) {
                registerHomeContentInsurance.setYearMessage(FORMAT_MESSAGE);
                abort = true;
            }
        }
        if(zipCodeString.equals("")) {
            registerHomeContentInsurance.setZipCodeMessage(EMPTY_MESSAGE);
            abort = true;
        } else {
            try {
                zipCode = Integer.parseInt(zipCodeString);
            } catch(NumberFormatException nfe) {
                registerHomeContentInsurance.setZipCodeMessage(FORMAT_MESSAGE);
                abort = true;
            }
        }
        
        if(abort) {
            return;
        }
        
        // Creates Address:
        Address address = new Address(street, zipCode, city);
        // Creates Property:
        Property property = new Property(address, material, area, year);
        // Creates HomeContentInsurance:
        HomeContentInsurance insurance = new HomeContentInsurance(amount, 
                coverage, customerId, excess, property, type);
        
        // Adds insurance to Register:
        if (insurances.addInsurance(insurance)) {
            registerHomeContentInsurance.setRegisterButtonMessage(REGISTER_SUCCESS + "\nForsikringspremie: " + insurance.getPremium() + ",-");
            registerHomeContentInsurance.clearView();
        } else {
            registerHomeContentInsurance.setRegisterButtonMessage(REGISTER_NO_SUCCESS);
        }
    } // end of method homeContentInsuranceRegisterButtonEventHandler
    
    private void homeContentInsuranceSearchCustomerIdButtonEventHandler(ActionEvent event) {
        String customerIdString = registerHomeContentInsurance.getCustomerId();
        int customerId;
        if(customerIdString.equals("")) {
            // Gives the user an appropriate message if the user hasn't put in a customerId:
            registerHomeContentInsurance.setCustomerArea(CUSTOMERID_EMPTY_MESSAGE);
            return;
        }
        try {
            // Converts the customerId to int:
            customerId = Integer.parseInt(customerIdString);
        } catch(NumberFormatException nfe) {
            // Gives the user an appropriate message if the customerId wasn't formatted correctly:
            registerHomeContentInsurance.setCustomerArea(CUSTOMERID_FORMAT_MESSAGE);
            return;
        }
        // TODO: Regex.
        // Searches for the customer by customerId:
        Customer customer = customers.findCustomerById(customerId);
        if(customer == null) {
            // Gives the user an appropriate message if the customer wasn't found:
            registerHomeContentInsurance.setCustomerArea(CUSTOMERID_NOT_FOUND_MESSAGE + customerId);
        } else {
            // Displays the customer:
            registerHomeContentInsurance.setCustomerArea(customer.toString());
            registerHomeContentInsurance.setSelectedCustomerId(customerId);
            // Finds the customers insurances:
            List insuranceList = insurances.getAllInsurancesByCustomerId(customerId);
            if (!insuranceList.isEmpty()) {
                // Displays the insurances if there is at least one:
                registerHomeContentInsurance.populateInsurancesTable(insuranceList);
            }
        }
    } // end of method homeContentInsuranceSearchCustomerIdButtonEventHandler
    
    private void homeContentInsuranceSearchPersonalNumberButtonEventHandler(ActionEvent event) {
        String personalNumber = registerHomeContentInsurance.getPersonalNumber();
        if(personalNumber.equals("")) {
            // Gives the user an appropriate message if the user hasn't put in a personalNumber:
            registerHomeContentInsurance.setCustomerArea(PERSONALNUMBER_EMPTY_MESSAGE);
            return;
        }
        // TODO: Regex.
        // Searches for the customer by personalNumber:
        Customer customer = customers.findCustomerByPersonalNumber(personalNumber);
        if(customer == null) {
            // Gives the user an appropriate message if the customer wasn't found:
            registerHomeContentInsurance.setCustomerArea(PERSONALNUMBER_NOT_FOUND_MESSAGE + personalNumber);
        } else {
            // Finds the customers insurances:
            int customerId = customer.getId();
            // Displays the customer:
            registerHomeContentInsurance.setCustomerArea(customer.toString());
            registerHomeContentInsurance.setSelectedCustomerId(customerId);
            List insuranceList = insurances.getAllInsurancesByCustomerId(customerId);
            if (!insuranceList.isEmpty()) {
                // Displays the insurances if there is at least one:
                registerHomeContentInsurance.populateInsurancesTable(insuranceList);
            }
        }
    } // end of method homeContentInsuranceSearchPersonalNumberButtonEventHandler
    
    // TRAVEL INSURANCE EVENT HANDLERS
    
    private void travelInsuranceRegisterButtonEventHandler(ActionEvent e) {
        
        // Clears previous messages:
        registerTravelInsurance.clearMessages();
        
        // Collects information about the customer and the insurance:
        TravelInsuranceCoverage coverage = registerTravelInsurance.getCoverage();
        int customerId = registerTravelInsurance.getSelectedCustomerId();
        String excessString = registerTravelInsurance.getExcess();
        
        // Creates a boolean which is to be set true if the user has made a 
        // mistake and the method has to abort:
        boolean abort = false;
        
        // Creates ints for the converted values:
        int excess = 0;
        
        // Evaluates Input:
        if(coverage == null) {
            registerTravelInsurance.setCoverageMessage(EMPTY_MESSAGE);
            abort = true;
        }
        if(customerId == 0) {
            registerTravelInsurance.setCustomerSelectedMessage(NO_CUSTOMER_MESSAGE);
            abort = true;
        }
        
        // Evaluates and converts Input:
        if(excessString.equals("")) {
            registerTravelInsurance.setExcessMessage(EMPTY_MESSAGE);
            abort = true;
        } else {
            try {
                excess = Integer.parseInt(excessString);
            } catch(NumberFormatException nfe) {
                registerTravelInsurance.setExcessMessage(FORMAT_MESSAGE);
                abort = true;
            }
        }
        
        if(abort) {
            return;
        }
        
        // Creates TravelInsurance:
        TravelInsurance insurance = new TravelInsurance(coverage, customerId, excess);
        
        // Adds insurance to Register:
        if (insurances.addInsurance(insurance)) {
            registerTravelInsurance.setRegisterButtonMessage(REGISTER_SUCCESS + "\nForsikringspremie: " + insurance.getPremium() + ",-");
            registerTravelInsurance.clearView();
        } else {
            registerTravelInsurance.setRegisterButtonMessage(REGISTER_NO_SUCCESS);
        }
    } // end of method travelInsuranceRegisterButtonEventHandler
    
    private void travelInsuranceSearchCustomerIdButtonEventHandler(ActionEvent event) {
        String customerIdString = registerTravelInsurance.getCustomerId();
        int customerId;
        if(customerIdString.equals("")) {
            // Gives the user an appropriate message if the user hasn't put in a customerId:
            registerTravelInsurance.setCustomerArea(CUSTOMERID_EMPTY_MESSAGE);
            return;
        }
        try {
            // Converts the customerId to int:
            customerId = Integer.parseInt(customerIdString);
        } catch(NumberFormatException nfe) {
            // Gives the user an appropriate message if the customerId wasn't formatted correctly:
            registerTravelInsurance.setCustomerArea(CUSTOMERID_FORMAT_MESSAGE);
            return;
        }
        // TODO: Regex.
        // Searches for the customer by customerId:
        Customer customer = customers.findCustomerById(customerId);
        if(customer == null) {
            // Gives the user an appropriate message if the customer wasn't found:
            registerTravelInsurance.setCustomerArea(CUSTOMERID_NOT_FOUND_MESSAGE + customerId);
        } else {
            // Displays the customer:
            registerTravelInsurance.setCustomerArea(customer.toString());
            registerTravelInsurance.setSelectedCustomerId(customerId);
            // Finds the customers insurances:
            List insuranceList = insurances.getAllInsurancesByCustomerId(customerId);
            if (!insuranceList.isEmpty()) {
                // Displays the insurances if there is at least one:
                registerTravelInsurance.populateInsurancesTable(insuranceList);
            }
        }
    } // end of method travelInsuranceSearchCustomerIdButtonEventHandler
    
    private void travelInsuranceSearchPersonalNumberButtonEventHandler(ActionEvent event) {
        String personalNumber = registerTravelInsurance.getPersonalNumber();
        if(personalNumber.equals("")) {
            // Gives the user an appropriate message if the user hasn't put in a personalNumber:
            registerTravelInsurance.setCustomerArea(PERSONALNUMBER_EMPTY_MESSAGE);
            return;
        }
        // TODO: Regex.
        // Searches for the customer by personalNumber:
        Customer customer = customers.findCustomerByPersonalNumber(personalNumber);
        if(customer == null) {
            // Gives the user an appropriate message if the customer wasn't found:
            registerTravelInsurance.setCustomerArea(PERSONALNUMBER_NOT_FOUND_MESSAGE + personalNumber);
        } else {
            // Finds the customers insurances:
            int customerId = customer.getId();
            // Displays the customer:
            registerTravelInsurance.setCustomerArea(customer.toString());
            registerTravelInsurance.setSelectedCustomerId(customerId);
            List insuranceList = insurances.getAllInsurancesByCustomerId(customerId);
            if (!insuranceList.isEmpty()) {
                // Displays the insurances if there is at least one:
                registerTravelInsurance.populateInsurancesTable(insuranceList);
            }
        }
    } // end of method travelInsuranceSearchPersonalNumberButtonEventHandler
    
    // INSURANCE SEARCH EVENT HANDLERS
    
    private void searchInsuranceSearchIdEventHandler(ActionEvent event) {
        // Clears all messages ans the view:
        searchInsurances.clearMessages();
        searchInsurances.clearView();
        
        // Declares variables to be converted to:
        int insuranceId = 0;
        
        // Collects information about the search terms:
        String insuranceIdString = searchInsurances.getInsuranceId();
        
        // Validates and converts input:
        if (!insuranceIdString.equals("")) {
            try {
                insuranceId = Integer.parseInt(insuranceIdString);
            } catch(NumberFormatException nfe) {
                searchInsurances.setSearchMessage(INSURANCEID_FORMAT_MESSAGE);
                return;
            }
        }
        
        // Searches through the register:
        List<Insurance> insuranceList;
        insuranceList = new ArrayList<>();
        Insurance insurance = insurances.getInsuranceById(insuranceId);
        if(insurance != null) {
            insuranceList.add(insurance);
        }
        
        // Populates the table:
        searchInsurances.populateInsurancesTable(insuranceList);
    }
    
    private void searchInsuranceSearchEventHandler(ActionEvent event) {
        // Clears all messages ans the view:
        searchInsurances.clearMessages();
        searchInsurances.clearView();
        
        // Declares variables to be converted to:
        int customerId = 0;
        
        // Collects information about the search terms:
        String number = searchInsurances.getNumber();
        String type = searchInsurances.getInsuranceType();
        boolean active = searchInsurances.getActive();
        Calendar fromDate = searchInsurances.getFromDate();
        Calendar toDate = searchInsurances.getToDate();
        
        // Validates and converts input:
        if(!number.equals("")) {
            if(searchInsurances.isCustomerIdSelected()) {
                try {
                    customerId = Integer.parseInt(number);
                } catch(NumberFormatException nfe) {
                    searchInsurances.setSearchMessage(CUSTOMERID_FORMAT_MESSAGE);
                    return;
                }
            } else {
                customerId = customers.findCustomerIdByPersonalNumber(number);
            }
        }
        
        // Searches through the register:
        List<Insurance> insuranceList;
        insuranceList = insurances.getInsurances(customerId, type, fromDate, 
                toDate, active);
        
        // Populates the table:
        searchInsurances.populateInsurancesTable(insuranceList);
    }
    
    private void searchInsuranceSelectEventHandler(ActionEvent event) {
        // Clears all messages:
        searchInsurances.clearMessages();
        
        // Gets the selected insurance:
        Insurance insurance = searchInsurances.getInsurancesTableValue();
        
        // Gives the user a message if no insurance is selected:
        if (insurance == null) {
            searchInsurances.setSelectMessage(NO_INSURANCE_MESSAGE);
            return;
        }
        
        // Displays information about the insurance:
        searchInsurances.setInsuranceArea(insurance.toString());
        
        // Displays information about the attachment:
        if (insurance instanceof BoatInsurance) {
            searchInsurances.setAttachmentArea("Båt:", 
                    ((BoatInsurance)insurance).getBoat().toString());
        } else if (insurance instanceof CarInsurance) {
            searchInsurances.setAttachmentArea("Bil:", 
                    ((CarInsurance) insurance).getCar().toString());
        } else if (insurance instanceof HolidayHomeInsurance) {
            searchInsurances.setAttachmentArea("Eiendom:", 
                    ((HolidayHomeInsurance) insurance).getProperty()
                            .toString());
        } else if (insurance instanceof HolidayHomeContentInsurance) {
            searchInsurances.setAttachmentArea("Eiendom:", 
                    ((HolidayHomeContentInsurance) insurance).getProperty()
                            .toString());
        } else if (insurance instanceof HomeInsurance) {
            searchInsurances.setAttachmentArea("Eiendom:", 
                    ((HomeInsurance) insurance).getProperty().toString());
        } else if (insurance instanceof HomeContentInsurance) {
            searchInsurances.setAttachmentArea("Eiendom:", 
                    ((HomeContentInsurance) insurance).getProperty()
                            .toString());
        } else if (insurance instanceof TravelInsurance) {
            searchInsurances.removeAttachmentArea();
        }
    }
    
    private void searchInsuranceDeactivateEventHandler(ActionEvent event) {
        // Clears all messages:
        searchInsurances.clearMessages();
        
        // Gets the selected insurance:
        Insurance insurance = searchInsurances.getInsurancesTableValue();
        
        // Gives the user a message if no insurance is selected:
        if (insurance == null) {
            searchInsurances.setDeactivateMessage(NO_INSURANCE_MESSAGE);
            return;
        }
        
        // Deactivates the insurance and clears the view:
        insurance.setActive(!insurance.getActive());
        searchInsurances.clearView();
    }
    
    private void searchClaimsTypeEventHandler(ActionEvent event) {
        // Gets the selected claim type:
        String type = searchClaims.getClaimType();
        
        // Clears the ComboBox if no claim type is selected:
        if (type == null) {
            searchClaims.populateDamageCombo(null);
            return;
        } 
        
        // Populates the ComboBox with the damages for the selected claim type:
        if (ClaimType.BOAT_CLAIM.toString().equals(type)) {
            searchClaims.populateDamageCombo(
                    BoatInsuranceCoverage.BOAT_PLUS.damages());
        } else if (ClaimType.CAR_CLAIM.toString().equals(type)) {
            searchClaims.populateDamageCombo(
                    CarInsuranceCoverage.CASCO.damages());
        } else if (ClaimType.HOLIDAY_HOME_CLAIM.toString().equals(type)) {
            searchClaims.populateDamageCombo(
                    HolidayHomeInsuranceCoverage.PLUS.damages());
        } else if (ClaimType.HOLIDAY_HOME_CONTENT_CLAIM.toString()
                .equals(type)) {
            searchClaims.populateDamageCombo(
                    HolidayHomeContentInsuranceCoverage.PLUS.damages());
        } else if (ClaimType.HOME_CLAIM.toString().equals(type)) {
            searchClaims.populateDamageCombo(
                    HomeInsuranceCoverage.PLUS.damages());
        } else if (ClaimType.HOME_CONTENT_CLAIM.toString().equals(type)) {
            searchClaims.populateDamageCombo(
                    HomeContentInsuranceCoverage.PLUS.damages());
        } else if (ClaimType.TRAVEL_CLAIM.toString().equals(type)) {
            searchClaims.populateDamageCombo(
                    TravelInsuranceCoverage.STANDARD.damages());
        }
    }
    
    private void searchClaimsSearchIdEventHandler(ActionEvent event) {
        // Clears all messages ans the view:
        searchClaims.clearMessages();
        searchClaims.clearView();
        
        // Declares variables to be converted to:
        int claimId = 0;
        
        // Collects information about the search terms:
        String claimIdString = searchClaims.getClaimId();
        
        // Validates and converts input:
        if (!claimIdString.equals("")) {
            try {
                claimId = Integer.parseInt(claimIdString);
            } catch(NumberFormatException nfe) {
                searchClaims.setSearchMessage(CLAIMID_FORMAT_MESSAGE);
                return;
            }
        }
        
        // Searches through the register:
        List<Claim> claimList;
        claimList = new ArrayList<>();
        Claim claim = claims.getClaimById(claimId);
        if(claim != null) {
            claimList.add(claim);
        }
        
        // Populates the table:
        searchClaims.populateClaimsTable(claimList);
    }
    
    private void searchClaimsSearchEventHandler(ActionEvent event ) {
        // Clears all messages ans the view:
        searchClaims.clearMessages();
        searchClaims.clearView();
        
        // Declares variables to be converted to:
        int customerId = 0;
        int insuranceId = 0;
        
        // Collects information about the search terms:
        String number = searchClaims.getNumber();
        String type = searchClaims.getClaimType();
        Damage damage = searchClaims.getDamage();
        String insuranceIdString = searchClaims.getInsuranceId();
        Calendar fromDate = searchClaims.getFromDate();
        Calendar toDate = searchClaims.getToDate();
        
        if(!number.equals("")) {
            if(searchClaims.isCustomerIdSelected()) {
                try {
                    customerId = Integer.parseInt(number);
                } catch(NumberFormatException nfe) {
                    searchClaims.setSearchMessage(CUSTOMERID_FORMAT_MESSAGE);
                    return;
                }
            } else {
                customerId = customers.findCustomerIdByPersonalNumber(number);
            }
        }
        if (!insuranceIdString.equals("")) {
            try {
                insuranceId = Integer.parseInt(insuranceIdString);
            } catch(NumberFormatException nfe) {
                searchClaims.setSearchMessage(INSURANCEID_FORMAT_MESSAGE);
                return;
            }
        }
        
        // Searches through the register:
        List<Claim> claimList;
        claimList = claims.getClaims(customerId, type, damage, insuranceId,
                fromDate, toDate);
        
        // Populates the table:
        searchClaims.populateClaimsTable(claimList);
    }
    
    private void searchClaimsSelectEventHandler(ActionEvent event ) {
        // Clears all messages:
        searchClaims.clearMessages();
        
        // Gets the selected claim:
        Claim claim = searchClaims.getClaimsTableValue();
        
        // Gives the user a message if no claim is selected:
        if (claim == null) {
            searchClaims.setSelectMessage(NO_CLAIM_MESSAGE);
            return;
        }
        
        // Displays information about the claim:
        searchClaims.setClaimArea(claim.toString());
        
        // Displays information about the insurance:
        int insuranceId = claim.getInsuranceId();
        Insurance insurance = insurances.getInsuranceById(insuranceId);
        String text = insurance.toString();
        searchClaims.setInsuranceArea(text);
    }
    
    private void searchClaimsFormEventHandler(ActionEvent event ) {
        // Get the selected claim:
        Claim claim = searchClaims.getClaimsTableValue();
        CarClaim carClaim = null;
        // Only proceed if its a car claim:
        if (claim instanceof CarClaim) {
            // Cast to car claim:
            carClaim = (CarClaim) claim;
            // Get the claimform to the car claim:
            CarClaimForm claimForm = carClaim.getClaimForm();
            // Only proceed if the claim form is not null:
            if (claimForm != null) {
                // Get the snapshot of that claimform:
                Image formImage = claimForm.getSnapshot();
                // Find the insurance to the car claim:
                int insuranceId = carClaim.getInsuranceId();
                Insurance insurance = insurances.getInsuranceById(insuranceId);
                CarInsurance carInsurance = null;
                if (insurance instanceof CarInsurance) {
                    // Cast this insurance to a car insurance:
                    carInsurance = (CarInsurance) insurance;
                    // Create a new ClaimFormProcess view, and send the form image and car insurance
                    // as paramater:
                    ClaimFormProcess form = new ClaimFormProcess(formImage, carInsurance);
                    // Create a new stage to be used for the car claim form window:
                    Stage formStage = new Stage();
                    // Show the claim form:
                    form.show(formStage);
                }
            }
        }    
    }
    
    private void searchClaimsImageEventHandler(ActionEvent event ) {
        // Get the selected claim:
        Claim claim = searchClaims.getClaimsTableValue();
        if (claim != null) {
            // Get the image to that claim:
            Image image = claim.getImage();
            // Only proceed if that imag exists:
            if (image != null) {
                // Add the image to an image view:
                final ImageView imv = new ImageView();
                imv.setImage(image);
                // Add the imageview to stackpane:
                StackPane sp = new StackPane();
                sp.getChildren().add(imv);
                // Add that stackpane to a new scene:
                Scene scene = new Scene(sp);
                // Create a new stage to be used for displaying the image:
                // Create a new stage and show the scene:
                Stage imageStage = new Stage();
                imageStage.setScene(scene);
                imageStage.show();
            }
        }
        
    }
    
    private void searchClaimsDisbursementEventHandler(ActionEvent event ) {
        // Clears all messages ans the view:
        searchClaims.clearMessages();
        
        // Collect information about the claim and the disbursement:
        Claim claim = searchClaims.getClaimsTableValue();
        String disbursementString = searchClaims.getDisbursement();
        int disbursement;
        
        // Gives the user a message if the field is empty:
        if (disbursementString.equals("")) {
            searchClaims.setDisbursementMessage(EMPTY_MESSAGE);
        }
        
        // Converts the disbursement:
        try {
            disbursement = Integer.parseInt(disbursementString);
        } catch (NumberFormatException nfe) {
            searchClaims.setDisbursementMessage(FORMAT_MESSAGE);
            return;
        }
        
        // Sets the disbursement and clears the view:
        claim.setDisbursement(disbursement);
        searchClaims.clearView();
    }
    
    private void searchCustomersSearchIdEventHandler(ActionEvent event) {
        // Clears all messages ans the view:
        searchCustomers.clearMessages();
        searchCustomers.clearView();
        
        // Declares variables to be converted to:
        int customerId = 0;
        
        // Collects information about the search terms:
        String number = searchCustomers.getNumber();
        
        // Validates and converts input:
        if(!number.equals("")) {
            if(searchCustomers.isCustomerIdSelected()) {
                try {
                    customerId = Integer.parseInt(number);
                } catch(NumberFormatException nfe) {
                    searchCustomers.setSearchMessage(CUSTOMERID_FORMAT_MESSAGE);
                    return;
                }
            } else {
                customerId = customers.findCustomerIdByPersonalNumber(number);
            }
        }
        
        // Searches through the register:
        List<Customer> customerList;
        customerList = new ArrayList<>();
        Customer customer = customers.findCustomerById(customerId);
        if (customer != null) {
            customerList.add(customer);
        }
        
        // Populates the table:
        searchCustomers.populateCustomersTable(customerList);
    }
    
    private void searchCustomersSearchEventHandler(ActionEvent event ) {
        // Clears all messages ans the view:
        searchCustomers.clearMessages();
        searchCustomers.clearView();
        
        // Collects information about the search terms:
        String firstName = searchCustomers.getFirstName();
        String lastName = searchCustomers.getLastName();
        boolean total = searchCustomers.getTotalCustomer();
        boolean active = searchCustomers.getActive();
        String insurance = searchCustomers.getInsuranceType();
        
        // Searches through the register:
        List<Customer> customerList;
        // Finds the customers who pass the other search terms:
        customerList = customers.getCustomers(firstName, lastName, total, 
                active);
        if (insurance != null) {
            // Finds all customer IDs of customers with the insurance type:
            List<Object> c = insurances.getCustomerIds(insurance);
            // Finds the customer objects for the IDs:
            c.replaceAll(id -> customers.findCustomerById((Integer)id));
            // Finds the customers who are in both lists.
            customerList.retainAll(c);
        }
        
        // Populates the table:
        searchCustomers.populateCustomersTable(customerList);
    }
    
    private void searchCustomersSelectEventHandler(ActionEvent event ) {
        // Clears all messages:
        searchCustomers.clearMessages();
        
        // Gets the selected customer:
        Customer customer = searchCustomers.getCustomersTableValue();
        
        // Gives the user a message if no customer is selected:
        if (customer == null) {
            searchCustomers.setSelectMessage(NO_CUSTOMER_MESSAGE);
            return;
        }
        
        // Displays information about the customer:
        searchCustomers.setCustomerArea(customer.toString());
    }
    
    private void searchCustomersDeactivateEventHandler(ActionEvent event ) {
        // Clears all messages:
        searchCustomers.clearMessages();
        
        // Gets the selected customer:
        Customer customer = searchCustomers.getCustomersTableValue();
        
        // Gives the user a message if no customer is selected:
        if (customer == null) {
            searchCustomers.setDeactivateMessage(NO_CUSTOMER_MESSAGE);
            return;
        }
        
        // Deactivates the customer and clears the view:
        customer.setActive(!customer.isActive());
        searchCustomers.clearView();
        
        // Deactivates all insurances to the customer if the customer is set to
        // inactive.
        if(!customer.isActive()) {
            insurances.setAllInsurancesInactive(customer.getId());
        }
    }
    
    private void searchEmployeesSearchIdEventHandler(ActionEvent event) {
        // Clears all messages:
        searchEmployees.clearMessages();
        searchEmployees.clearView();
        
        // Declares variables to be converted to:
        int employeeId = 0;
        
        // Collects information about the search terms:
        String number = searchEmployees.getNumber();
        
        // Validates and converts input:
        if(!number.equals("")) {
            if(searchEmployees.isEmployeeIdSelected()) {
                try {
                    employeeId = Integer.parseInt(number);
                } catch(NumberFormatException nfe) {
                    searchEmployees.setSearchMessage(EMPLOYEEID_FORMAT_MESSAGE);
                    return;
                }
            } else {
                employeeId = employees.getEmployeeIdByPersonalNumber(number);
            }
        }
        
        // Searches through the register:
        List<Employee> employeeList;
        employeeList = new ArrayList<>();
        Employee employee = employees.getEmployeeById(employeeId);
        if (employee != null) {
            employeeList.add(employee);
        }
        
        // Populates the table:
        searchEmployees.populateEmployeesTable(employeeList);
    }
    
    private void searchEmployeesSearchEventHandler(ActionEvent event ) {
        // Clears all messages:
        searchEmployees.clearMessages();
        searchEmployees.clearView();
        
        // Collects information about the search terms:
        String firstName = searchEmployees.getFirstName();
        String lastName = searchEmployees.getLastName();
        String type = searchEmployees.getEmployeeType();
        boolean active = searchEmployees.getActive();
        
        
        // Searches through the register:
        List<Employee> employeeList;
        employeeList = employees.getEmployees(firstName, lastName, type, 
                active);
        
        // Populates the table:
        searchEmployees.populateEmployeesTable(employeeList);
    }
    
    private void searchEmployeesSelectEventHandler(ActionEvent event ) {
        // Clears all messages:
        searchEmployees.clearMessages();
        
        // Gets the selected employee:
        Employee employee = searchEmployees.getEmployeesTableValue();
        
        // Gives the user a message if no employee is selected:
        if (employee == null) {
            searchEmployees.setSelectMessage(NO_EMPLOYEE_MESSAGE);
            return;
        }
        
        // Displays information about the employee:
        searchEmployees.setEmployeeArea(employee.toString());
    }
    
    private void searchEmployeesDeactivateEventHandler(ActionEvent event ) {
        // Clears all messages:
        searchEmployees.clearMessages();
        
        // Gets the selected employee:
        Employee employee = searchEmployees.getEmployeesTableValue();
        
        // Gives the user a message if no employee is selected:
        if (employee == null) {
            searchEmployees.setDeactivateMessage(NO_EMPLOYEE_MESSAGE);
            return;
        }
        
        // Deactivates the customer and clears the view:
        employee.setActive(!employee.isActive());
        searchEmployees.clearView();
    }
    
    // READ AND WRITE IDS FROM/TO FILE:
    
    /**
     * Writes next unique customer id to file.
     */
    public void writeCustomerIdToFile() {
        try {
            Customer.saveNextIdToFile();
        } catch (IOException ioe) {
            logs.add(ioe.getStackTrace(), ioe.getMessage(), user);
            System.err.println(ioe.getMessage());
        }
    }
    
    /**
     * Reads next unique customer id from file and saves it as a static 
     * in the Customer class.
     */
    public void readCustomerIdFromFile() {
        try {
            Customer.readNextIdFromFile();
        } catch (FileNotFoundException fnfe) {
            logs.add(fnfe.getStackTrace(), fnfe.getMessage(), user);
            System.err.println(fnfe.getMessage());
        } catch (IOException ioe) {
            logs.add(ioe.getStackTrace(), ioe.getMessage(), user);
            System.err.println(ioe.getMessage());
        }
    }
 
    /**
     * Writes next unique employee id to file.
     */
    public void writeEmployeeIdToFile() {
        try {
            Employee.saveNextIdToFile();
        } catch (IOException ioe) {
            logs.add(ioe.getStackTrace(), ioe.getMessage(), user);
            System.err.println(ioe.getMessage());
        }
    }
    
    /**
     * Reads next unique employee id from file and saves it as a static 
     * in the Employee class.
     */
    public void readEmployeeIdFromFile() {
        try {
            Employee.readNextIdFromFile();
        } catch (FileNotFoundException fnfe) {
            logs.add(fnfe.getStackTrace(), fnfe.getMessage(), user);
            System.err.println(fnfe.getMessage());
        } catch (IOException ioe) {
            logs.add(ioe.getStackTrace(), ioe.getMessage(), user);
            System.err.println(ioe.getMessage());
        }
    }
    
    /**
     * Writes next unique insurance id to file.
     */
    public void writeInsuranceIdToFile() {
        try {
            Insurance.saveNextIdToFile();
        } catch (IOException ioe) {
            logs.add(ioe.getStackTrace(), ioe.getMessage(), user);
            System.err.println(ioe.getMessage());
        }
    }
    
    /**
     * Reads next unique insurance id from file and saves it as a static 
     * in the Insurance class.
     */
    public void readInsuranceIdFromFile() {
        try {
            Insurance.readNextIdFromFile();
        } catch (FileNotFoundException fnfe) {
            logs.add(fnfe.getStackTrace(), fnfe.getMessage(), user);
            System.err.println(fnfe.getMessage());
        } catch (IOException ioe) {
            logs.add(ioe.getStackTrace(), ioe.getMessage(), user);
            System.err.println(ioe.getMessage());
        }
    }
    
    /**
     * Writes next unique claim id to file.
     */
    public void writeClaimIdToFile() {
        try {
            Claim.saveNextIdToFile();
        } catch (IOException ioe) {
            logs.add(ioe.getStackTrace(), ioe.getMessage(), user);
            System.err.println(ioe.getMessage());
        }
    }
    
    /**
     * Reads next unique claim id from file and saves it as a static 
     * in the Claim class.
     */
    public void readClaimIdFromFile() {
        try {
            Claim.readNextIdFromFile();
        } catch (FileNotFoundException fnfe) {
            logs.add(fnfe.getStackTrace(), fnfe.getMessage(), user);
            System.err.println(fnfe.getMessage());
        } catch (IOException ioe) {
            logs.add(ioe.getStackTrace(), ioe.getMessage(), user);
            System.err.println(ioe.getMessage());
        }
    }
    
    /**
     * Writes next unique bill id to file.
     */
    public void writeBillIdToFile() {
        try {
            Bill.saveNextIdToFile();
        } catch (IOException ioe) {
            logs.add(ioe.getStackTrace(), ioe.getMessage(), user);
            System.err.println(ioe.getMessage());
        }
    }
    
    /**
     * Reads next unique bill id from file and saves it as a static 
     * in the Bills class.
     */
    public void readBillIdFromFile() {
        try {
            Bill.readNextIdFromFile();
        } catch (FileNotFoundException fnfe) {
            logs.add(fnfe.getStackTrace(), fnfe.getMessage(), user);
            System.err.println(fnfe.getMessage());
        } catch (IOException ioe) {
            logs.add(ioe.getStackTrace(), ioe.getMessage(), user);
            System.err.println(ioe.getMessage());
        }
    }
    
    // READ AND WRITE DATASTRUCTURES FROM/TO FILE:
    
    public void writeClaimsToFile() {
        try {
            claims.writeClaimsToFile();
        } catch (NotSerializableException nse) {
            logs.add(nse.getStackTrace(), nse.getMessage(), user);
            System.err.println(nse.getMessage());
        } catch (IOException ioe) {
            logs.add(ioe.getStackTrace(), ioe.getMessage(), user);
            System.err.println(ioe.getMessage());
        }
    }
    
    public void readClaimsFromFile() {
        try {
            claims.readClaimsFromFile();
        } catch (ClassNotFoundException cnfe) {
            logs.add(cnfe.getStackTrace(), cnfe.getMessage(), user);
            System.err.println(cnfe.getMessage());
        } catch (FileNotFoundException fnfe) {
            logs.add(fnfe.getStackTrace(), fnfe.getMessage(), user);
            System.err.println(fnfe.getMessage());
        } catch (IOException ioe) {
            logs.add(ioe.getStackTrace(), ioe.getMessage(), user);
            System.err.println(ioe.getMessage());
        }
    }
    
    public void writeCustomersToFile() {
        try {
            customers.writeCustomersToFile();
        } catch (NotSerializableException nse) {
            logs.add(nse.getStackTrace(), nse.getMessage(), user);
            System.err.println(nse.getMessage());
        } catch (IOException ioe) {
            logs.add(ioe.getStackTrace(), ioe.getMessage(), user);
            System.err.println(ioe.getMessage());
        }
    }
    
    public void readCustomersFromFile() {
        try {
            customers.readCustomersFromFile();
        } catch (ClassNotFoundException cnfe) {
            logs.add(cnfe.getStackTrace(), cnfe.getMessage(), user);
            System.err.println(cnfe.getMessage());
        } catch (FileNotFoundException fnfe) {
            logs.add(fnfe.getStackTrace(), fnfe.getMessage(), user);
            System.err.println(fnfe.getMessage());
        } catch (IOException ioe) {
            logs.add(ioe.getStackTrace(), ioe.getMessage(), user);
            System.err.println(ioe.getMessage());
        }
    }
    
    public void writeEmployeesToFile() {
        try {
            employees.writeEmployeesToFile();
        } catch (NotSerializableException nse) {
            logs.add(nse.getStackTrace(), nse.getMessage(), user);
            System.err.println(nse.getMessage());
        } catch (IOException ioe) {
            logs.add(ioe.getStackTrace(), ioe.getMessage(), user);
            System.err.println(ioe.getMessage());
        }
    }
    
    public void readEmployeesFromFile() {
        try {
            employees.readEmployeesFromFile();
        } catch (ClassNotFoundException cnfe) {
            logs.add(cnfe.getStackTrace(), cnfe.getMessage(), user);
            System.err.println(cnfe.getMessage());
        } catch (FileNotFoundException fnfe) {
            logs.add(fnfe.getStackTrace(), fnfe.getMessage(), user);
            System.err.println(fnfe.getMessage());
        } catch (IOException ioe) {
            logs.add(ioe.getStackTrace(), ioe.getMessage(), user);
            System.err.println(ioe.getMessage());
        }
    }
    
    public void writeInsurancesToFile() {
        try {
            insurances.writeInsurancesToFile();
        } catch (NotSerializableException nse) {
            logs.add(nse.getStackTrace(), nse.getMessage(), user);
            System.err.println(nse.getMessage());
        } catch (IOException ioe) {
            logs.add(ioe.getStackTrace(), ioe.getMessage(), user);
            System.err.println(ioe.getMessage());
        }
    }
    
    public void readInsurancesFromFile() {
        try {
            insurances.readInsurancesFromFile();
        } catch (ClassNotFoundException cnfe) {
            logs.add(cnfe.getStackTrace(), cnfe.getMessage(), user);
            System.err.println(cnfe.getMessage());
        } catch (FileNotFoundException fnfe) {
            logs.add(fnfe.getStackTrace(), fnfe.getMessage(), user);
            System.err.println(fnfe.getMessage());
        } catch (IOException ioe) {
            logs.add(ioe.getStackTrace(), ioe.getMessage(), user);
            System.err.println(ioe.getMessage());
        }
    }
    
    public void writeBillsToFile() {
        try {
            bills.writeBillsToFile();
        } catch (NotSerializableException nse) {
            logs.add(nse.getStackTrace(), nse.getMessage(), user);
            System.err.println(nse.getMessage());
        } catch (IOException ioe) {
            logs.add(ioe.getStackTrace(), ioe.getMessage(), user);
            System.err.println(ioe.getMessage());
        }
    }
    
    public void readBillsFromFile() {
        try {
            bills.readBillsFromFile();
        } catch (ClassNotFoundException cnfe) {
            logs.add(cnfe.getStackTrace(), cnfe.getMessage(), user);
            System.err.println(cnfe.getMessage());
        } catch (FileNotFoundException fnfe) {
            logs.add(fnfe.getStackTrace(), fnfe.getMessage(), user);
            System.err.println(fnfe.getMessage());
        } catch (IOException ioe) {
            logs.add(ioe.getStackTrace(), ioe.getMessage(), user);
            System.err.println(ioe.getMessage());
        }
    }
    
    public void writeLogsToFile() {
        try {
            logs.writeToFile();
        } catch (IOException ioe) {
            System.err.println(ioe.getMessage());
        }
    }
}
