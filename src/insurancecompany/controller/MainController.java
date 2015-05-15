/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package insurancecompany.controller;

import insurancecompany.misc.EmployeeType;
import insurancecompany.misc.InsuranceType;
import insurancecompany.misc.coverages.*;
import insurancecompany.misc.hometypes.HolidayHomeType;
import insurancecompany.misc.hometypes.HomeType;
import insurancecompany.model.bills.*;
import insurancecompany.model.claims.*;
import insurancecompany.model.datastructures.*;
import insurancecompany.model.datastructures.carinfo.*;
import insurancecompany.model.insurances.*;
import insurancecompany.model.people.*;
import insurancecompany.model.properties.*;
import insurancecompany.model.vehicles.*;
import insurancecompany.view.modules.*;
import insurancecompany.view.process.*;
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
import javafx.scene.image.Image;
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
    private AdminView adminView;
    
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
    
    // Controllers:
    private ModelController modelController;
    private ViewController viewController;
    
    // Creates strings to be used in messages to the user:
    private static final String REGISTER_SUCCESS = "Registrering vellykket.";
    private static final String REGISTER_NO_SUCCESS = "Registrering ikke vellykket.";
    private static final String NO_CUSTOMER_MESSAGE = "Du har ikke valgt noen kunde.";
    private static final String FORMAT_MESSAGE = "Dette feltet kan kun bestå av tall.";
    private static final String EMPTY_MESSAGE = "Dette feltet må fylles ut.";
    private static final String CUSTOMERID_FORMAT_MESSAGE = "Kundenummeret kan kun bestå av tall.";
    private static final String CUSTOMERID_EMPTY_MESSAGE = "Du må skrive inn et kundenummer.";
    private static final String CUSTOMERID_NOT_FOUND_MESSAGE = "Fant ingen kunde med kundenummer: ";
    private static final String INSURANCEID_FORMAT_MESSAGE = "Forsikringsnummeret kan kun bestå av tall.";
    private static final String PERSONALNUMBER_EMPTY_MESSAGE = "Du må skrive inn et personnummer.";
    private static final String PERSONALNUMBER_NOT_FOUND_MESSAGE = "Fant ingen kunde med personnummer: ";
    private static final String NO_INSURANCE_MESSAGE = "Du har ikke valgt noen forsikring.";
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
        
        // Modules:
        this.adminView = new AdminView();
    
        // Process Views:
        this.billsProcessView = new BillsProcessView();
        this.claimsProcessView = new ClaimsProcessView();
        this.subscriptionsProcessView = new SubscriptionsProcessView();
        
        // Claim Registration Views:
        this.boatClaimRegistration = new BoatClaimRegistration();
        this.carClaimRegistration = new CarClaimRegistration();
        this.holidayHomeClaimRegistration = new HolidayHomeClaimRegistration();
        this.holidayHomeContentClaimRegistration = new HolidayHomeContentClaimRegistration();
        this.homeClaimRegistration = new HomeClaimRegistration();
        this.homeContentClaimRegistration = new HomeContentClaimRegistration();
        this.travelClaimRegistration = new TravelClaimRegistration();
        
        // Insurance Registration Views:
        this.boatInsuranceRegistration = new BoatInsuranceRegistration();
        this.carInsuranceRegistration = new CarInsuranceRegistration();
        this.holidayHomeInsuranceRegistration = new HolidayHomeInsuranceRegistration();
        this.holidayHomeContentInsuranceRegistration = new HolidayHomeContentInsuranceRegistration();
        this.homeInsuranceRegistration = new HomeInsuranceRegistration();
        this.homeContentInsuranceRegistration = new HomeContentInsuranceRegistration();
        this.travelInsuranceRegistration = new TravelInsuranceRegistration();
        
        // Person Registration Views:
        this.customerRegistration = new CustomerRegistration();
        this.employeeRegistration = new EmployeeRegistration();
    
        // Search Views:
        this.claimSearchView = new ClaimSearchView();
        this.customerSearchView = new CustomerSearchView();
        this.employeeSearchView = new EmployeeSearchView();
        this.insuranceSearchView = new InsuranceSearchView();

        // Statistics Views:
        this.claimStatisticsView = new ClaimStatisticsView();
        this.customerStatisticsView = new CustomerStatisticsView();
        this.employeeStatisticsView = new EmployeeStatisticsView();
        this.insuranceStatisticsView = new InsuranceStatisticsView();
        
        // Create an ArrayList with all views to be sent as a parameter to the 
        // controllers. This is done to make the constructor of the controllers
        // cleaner.
        ArrayList<Object> views = new ArrayList<>();
        views.add(0, billsProcessView);
        views.add(1, claimsProcessView);
        views.add(2, subscriptionsProcessView);
        views.add(3, boatClaimRegistration);
        views.add(4, carClaimRegistration);
        views.add(5, holidayHomeClaimRegistration);
        views.add(6, holidayHomeContentClaimRegistration);
        views.add(7, homeClaimRegistration);
        views.add(8, homeContentClaimRegistration);
        views.add(9, travelClaimRegistration);
        views.add(10, boatInsuranceRegistration);
        views.add(11, carInsuranceRegistration);
        views.add(12, holidayHomeInsuranceRegistration);
        views.add(13, holidayHomeContentInsuranceRegistration);
        views.add(14, homeInsuranceRegistration);
        views.add(15, homeContentInsuranceRegistration);
        views.add(16, travelInsuranceRegistration);
        views.add(17, customerRegistration);
        views.add(18, employeeRegistration);
        views.add(19, claimSearchView);
        views.add(20, customerSearchView);
        views.add(21, employeeSearchView);
        views.add(22, insuranceSearchView);
        views.add(23, claimStatisticsView);
        views.add(24, customerStatisticsView);
        views.add(25, employeeStatisticsView);
        views.add(26, insuranceStatisticsView);
                
        // Controllers:
        this.modelController = new ModelController(bills, claims, customers, 
                employees, insurances, logs);
        this.viewController = new ViewController(adminView, views);
        
        readAllDataFromFile();
        setBrandComboBox();
        initializeEventHandlers();
    }
    
    public void show(Stage stage) {
        this.primaryStage = stage;
        viewController.show(stage);
    }
    
    private void initializeEventHandlers() {
        initializeRegisterInsuranceEventHandlers();
        initializeRegisterClaimEventHandlers();
        adminView.setSaveDataButtonEventHandler(this::adminViewSaveDataButtonEventHandler);
        adminView.setExitButtonEventHandler(this::adminViewExitButtonEventHandler);
        
        
        customerRegistration.setRegisterButtonEventHandler(this::registerCustomerButtonEventHandler);
        employeeRegistration.setRegisterButtonEventHandler(this::registerEmployeeButtonEventHandler);

    }
    
    private void initializeRegisterInsuranceEventHandlers() {
        boatInsuranceRegistration.setRegisterButtonEventHandler(this::boatInsuranceRegisterButtonEventHandler);
        boatInsuranceRegistration.setSearchCustomerIdButtonEventHandler(this::boatInsuranceSearchCustomerIdButtonEventHandler);
        boatInsuranceRegistration.setSearchPersonalNumberButtonEventHandler(this::boatInsuranceSearchPersonalNumberButtonEventHandler);
        
        carInsuranceRegistration.setSearchCustomerIdButtonEventHandler(this::carInsuranceSearchCustomerIdButtonEventHandler);
        carInsuranceRegistration.setSearchPersonalNumberButtonEventHandler(this::carInsuranceSearchPersonalNumberButtonEventHandler);
        carInsuranceRegistration.setBrandComboListener(this::brandComboListener);
        carInsuranceRegistration.setYearComboListener(this::yearComboListener);
        carInsuranceRegistration.setCalculateButtonEventHandler(null);
        carInsuranceRegistration.setRegisterButtonEventHandler(this::carInsuranceRegisterButtonEventHandler);
        
        homeInsuranceRegistration.setRegisterButtonEventHandler(this::homeInsuranceRegisterButtonEventHandler);
        homeInsuranceRegistration.setSearchCustomerIdButtonEventHandler(this::homeInsuranceSearchCustomerIdButtonEventHandler);
        homeInsuranceRegistration.setSearchPersonalNumberButtonEventHandler(this::homeInsuranceSearchPersonalNumberButtonEventHandler);
        
        homeContentInsuranceRegistration.setRegisterButtonEventHandler(this::homeContentInsuranceRegisterButtonEventHandler);
        homeContentInsuranceRegistration.setSearchCustomerIdButtonEventHandler(this::homeContentInsuranceSearchCustomerIdButtonEventHandler);
        homeContentInsuranceRegistration.setSearchPersonalNumberButtonEventHandler(this::homeContentInsuranceSearchPersonalNumberButtonEventHandler);
        
        holidayHomeInsuranceRegistration.setRegisterButtonEventHandler(this::holidayHomeInsuranceRegisterButtonEventHandler);
        holidayHomeInsuranceRegistration.setSearchCustomerIdButtonEventHandler(this::holidayHomeInsuranceSearchCustomerIdButtonEventHandler);
        holidayHomeInsuranceRegistration.setSearchPersonalNumberButtonEventHandler(this::holidayHomeInsuranceSearchPersonalNumberButtonEventHandler);
        
        holidayHomeContentInsuranceRegistration.setRegisterButtonEventHandler(this::holidayHomeContentInsuranceRegisterButtonEventHandler);
        holidayHomeContentInsuranceRegistration.setSearchCustomerIdButtonEventHandler(this::holidayHomeContentInsuranceSearchCustomerIdButtonEventHandler);
        holidayHomeContentInsuranceRegistration.setSearchPersonalNumberButtonEventHandler(this::holidayHomeContentInsuranceSearchPersonalNumberButtonEventHandler);
        
        travelInsuranceRegistration.setRegisterButtonEventHandler(this::travelInsuranceRegisterButtonEventHandler);
        travelInsuranceRegistration.setSearchCustomerIdButtonEventHandler(this::travelInsuranceSearchCustomerIdButtonEventHandler);
        travelInsuranceRegistration.setSearchPersonalNumberButtonEventHandler(this::travelInsuranceSearchPersonalNumberButtonEventHandler);
        
        insuranceSearchView.setSearchEventHandler(this::insuranceSearchViewSearchEventHandler);
        insuranceSearchView.setSelectEventHandler(this::insuranceSearchViewSelectEventHandler);
        insuranceSearchView.setDeactivateEventHandler(this::insuranceSearchViewDeactivateEventHandler);
    } // end of class initializeRegisterInsuranceEventHandlers
 
    private void initializeRegisterClaimEventHandlers() {
        carClaimRegistration.setRegisterButtonEventHandler(this::carClaimRegisterButtonEventHandler);
        carClaimRegistration.setSearchCustomerIdButtonEventHandler(this::carClaimSearchCustomerIdButtonEventHandler);
        carClaimRegistration.setSearchPersonalNumberButtonEventHandler(this::carClaimSearchPersonalNumberButtonEventHandler);
        carClaimRegistration.setSelectInsuranceButtonEventHandler(this::carClaimSelectInsuranceButtonEventHandler);
        carClaimRegistration.setSelectImageButtonEventHandler(this::carClaimSelectImageButtonEventHandler);
        
    } // end of class initializeRegisterClaimEventHandlers
    
    private void adminViewSaveDataButtonEventHandler(ActionEvent event) {
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
    
    private void readAllDataFromFile() {
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
    
    private void adminViewExitButtonEventHandler(ActionEvent event) {
        Platform.exit();
    }    
    
    // TODO: Trenger regex og validering
    private void registerCustomerButtonEventHandler(ActionEvent event) {
        boolean ok = true;
        
        String personalNumber = customerRegistration.getPersonalNumberField();
        String firstName = customerRegistration.getFirstNameField();
        String lastName = customerRegistration.getLastNameField();
        String street = customerRegistration.getStreetField();
        String zipCodeS = customerRegistration.getZipCodeField();
        String city = customerRegistration.getCityField();
        String email = customerRegistration.getEmailField();
        String phone = customerRegistration.getPhoneField();
        
        if (personalNumber.equals("")) {
            String message = "Fyll inn dette feltet.";
            customerRegistration.setPersonalNumberMessage(message);
            ok = false;
        }
        
        int zipCode = 0;
        try {
            zipCode = Integer.parseInt(zipCodeS);
        } catch (NumberFormatException nfe) {
            ok = false;
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
            customerRegistration.setResultText(result);
        }
    }
    
    // TODO: Trenger regex og validering
    private void registerEmployeeButtonEventHandler(ActionEvent event) {
        
        EmployeeType position = employeeRegistration.getPositionComboValue();
        String personalNumber = employeeRegistration.getPersonalNumberField();
        String firstName = employeeRegistration.getFirstNameField();
        String lastName = employeeRegistration.getLastNameField();
        String street = employeeRegistration.getStreetField();
        String zipCodeS = employeeRegistration.getZipCodeField();
        String city = employeeRegistration.getCityField();
        String email = employeeRegistration.getEmailField();
        String phone = employeeRegistration.getPhoneField();

        System.out.println(position.toString());
        if (personalNumber.equals("")) {
            String message = "Fyll inn dette feltet.";
            employeeRegistration.setPersonalNumberMessage(message);
        } 
 
        int zipCode = 0;
        try {
            zipCode = Integer.parseInt(zipCodeS);
        } catch (NumberFormatException nfe) {
            
        }
        // Creates an adress object for the customer:
        Address adress = new Address(street, zipCode, city);
        // Creates a new customer:
        Employee employee;
        boolean ok = false;
        switch (position) {
            case SERVICE_WORKER : employee = new ServiceWorker(firstName, lastName, personalNumber, email, adress, phone);
            case CASE_WORKER : employee = new CaseWorker(firstName, lastName, personalNumber, email, adress, phone);
            case ADMIN : employee = new Admin(firstName, lastName, personalNumber, email, adress, phone);
            default: employee = new ServiceWorker(firstName, lastName, personalNumber, email, adress, phone);
        }
        // Adds the new customer to the datastructure:
        ok = employees.addEmployee(employee);
        String result;
        if (ok) {
            result = "Ansatt registrert. Ansattnuummer: " + employee.getId();
        } else {
            result = "Ansatt kunne ikke registreres. Ansatt med likt personnummer eksisterer allerede.";
        }
        
        employeeRegistration.setResultText(result);
    }
    
    private void setBrandComboBox() {
        List cars = modelController.getCarInfos();
        carInsuranceRegistration.populateBrandCombo(cars);
    }
    
    private void setYearComboBox() {
        int since = 1970;
        int to = Calendar.getInstance().get(Calendar.YEAR);
        List<String> years = new LinkedList();
        for (int i = since; i <= to; i++) {
            years.add(i + "");
        }
        
        carInsuranceRegistration.populateYearCombo(years);
    }
    
    // BUG: method gets called twice for every change, but this is resolved by an if statement at the end of the method:
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
            carInsuranceRegistration.populateYearCombo(years);
        }
        
    }
    
    private void yearComboListener(ObservableValue observable, Object oldValue, Object newValue) {
        Object value = newValue;
        String yearString;
        int year;
        CarInfo car = new CarInfo(); // new initialization will be set in one of the 2 next if statements
        String brandName;
        Object brandValue = carInsuranceRegistration.getBrandComboValue();
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
                carInsuranceRegistration.populateModelCombo(modelsResult);
  
            } catch (NumberFormatException nfe) {
                //setSomeMessage
                return;
            }
        }
    }
    
    // CAR CLAIM REGISTRATION EVENT HANDLERS
    
    // TODO:zlokex Create this method
    private void carClaimRegisterButtonEventHandler(ActionEvent event) {
        boolean ok = true;
        // Clears the previous status messages:
        carClaimRegistration.clearMessages();
        int insuranceId = 0;
        int appraisal = 0;
        int customerId = carClaimRegistration.getSelectedCustomerId();
        if (customerId == 0) {
            ok = false;
            carClaimRegistration.setRegisterButtonMessage(NO_CUSTOMER_MESSAGE);
            return;
        }
        CarInsurance insurance;
        // Get the Insurance selected from the table:
        if (carClaimRegistration.getInsuranceTableValue() instanceof CarInsurance) {
            insurance = (CarInsurance) carClaimRegistration.getInsuranceTableValue();
            // Checks if this value is null:
            if (insurance == null) {
                ok = false;
                // If so send a message to the user:
                carClaimRegistration.setRegisterButtonMessage(NO_INSURANCE_MESSAGE);
                // Exit the method, as we don't need to check for anything else:
                return;
            } else {
                insuranceId = insurance.getCustomerId();
            }
            Calendar dateHappened = carClaimRegistration.getDateHappenedPickerValue();
            if (dateHappened == null) {
                ok = false;
                // If so send a message to the user:
                carClaimRegistration.setRegisterButtonMessage(NO_DATE_MESSAGE);
                return; // leave method
            }
            String description = carClaimRegistration.getDescriptionTextArea();
            if (description.equals("")) {
                ok = false;
                // If so send a message to the user:
                carClaimRegistration.setRegisterButtonMessage(DESCRIPTION_EMPTY_MESSAGE);
                return; // leave method
            }
            // Get the selected damages:
            Set<Damage> damages = carClaimRegistration.getSelectedDamages();
            // Returns an empty set if no damages are selected:
            if (damages.isEmpty()) {
                // This is still allowed.
            }
            Image image = carClaimRegistration.getImage();
            if (image == null) {
                // This is also allowed.
            }

            String appraisalString = carClaimRegistration.getAppraisalField();
            if (appraisalString.equals("")) {
                ok = false;
                carClaimRegistration.setAppraisalFieldMessage(EMPTY_MESSAGE);
            } else {
                try {
                    appraisal = Integer.parseInt(appraisalString);
                } catch (NumberFormatException nfe) {
                    ok = false;
                    carClaimRegistration.setAppraisalFieldMessage(FORMAT_MESSAGE);
                }
            }
            // If all fields are filled in correctly according to what we need to create a
            // claim, we proceed:
            if (ok) {
                CarClaimFormView formView = carClaimRegistration.getCarClaimFormView();
                if (formView == null) {
                    // Send carclaimform as null if no carclaimformview has been opened.
                    CarClaim claim = new CarClaim(customerId, insuranceId, description, dateHappened, damages, appraisal, null);
                    // Add this car claim to the claim register:
                    if (claims.addClaim(claim)) {
                            carClaimRegistration.setRegisterButtonMessage(REGISTER_SUCCESS);
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
                    Image drawnImage = formView.getDrawnImage();
                    Image snapshot = formView.getSnapshotOfCarLcaimForm();
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
                        CarClaimForm carClaimForm = new CarClaimForm(carA, personA, location, insuranceId, drawnImage);
                        // Set a snapshot:
                        carClaimForm.setCarClaimFormSnapshot(snapshot);
                        // Create a new car claim with this claim form:
                        CarClaim claim = new CarClaim(customerId, insuranceId, description, dateHappened, damages, appraisal, carClaimForm);
                        // Add this car claim to the claim register:
                        if (claims.addClaim(claim)) {
                            carClaimRegistration.setRegisterButtonMessage(REGISTER_SUCCESS);
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
                                witnesses, drawnImage);
                        // Set a snapshot to this claim form:
                        carClaimForm.setCarClaimFormSnapshot(snapshot);
                        // Create a new car claim with these data:
                        CarClaim claim = new CarClaim(customerId, insuranceId, description, dateHappened, damages, appraisal, carClaimForm);
                        // Add this car claim to the claim register:
                        if (claims.addClaim(claim)) {
                            // Clear uploads(image and claim form:
                            carClaimRegistration.clearUploads();
                            carClaimRegistration.setRegisterButtonMessage(REGISTER_SUCCESS);
                        }
                    }
                }
            }  
        }   
    }
    
    private void carClaimSearchCustomerIdButtonEventHandler(ActionEvent event) {
        String customerIdString = carClaimRegistration.getCustomerId();
        int customerId;
        if(customerIdString.equals("")) {
            // Gives the user an appropriate message if the user hasn't put in a customerId:
            carClaimRegistration.setCustomerArea(CUSTOMERID_EMPTY_MESSAGE);
            return;
        }
        try {
            // Converts the customerId to int:
            customerId = Integer.parseInt(customerIdString);
        } catch(NumberFormatException nfe) {
            logs.add(nfe.getStackTrace(), nfe.getMessage(), user);
            // Gives the user an appropriate message if the customerId wasn't formatted correctly:
            carClaimRegistration.setCustomerArea(CUSTOMERID_FORMAT_MESSAGE);
            return;
        }
        // TODO: Regex.
        // Searches for the customer by customerId:
        Customer customer = customers.findCustomerById(customerId);
        if(customer == null) {
            // Gives the user an appropriate message if the customer wasn't found:
            carClaimRegistration.setCustomerArea(CUSTOMERID_NOT_FOUND_MESSAGE + customerId);
        } else {
            // Displays the customer:
            carClaimRegistration.setCustomerArea(customer.toString());
            // Set this id as the selected one in the view class:
            carClaimRegistration.setSelectedCustomerId(customerId);
            // Finds the customers insurances:
            List insuranceList = insurances.getAllActiveTypeInsurancesByCustomerId(customerId, CarInsurance.class);
            if (!insuranceList.isEmpty()) {
                // Displays the insurances if there is at least one:
                carClaimRegistration.populateInsurancesTable(insuranceList);
            }
        }
    }
    
    private void carClaimSearchPersonalNumberButtonEventHandler(ActionEvent event) {
        String personalNumber = carClaimRegistration.getPersonalNumber();
        if(personalNumber.equals("")) {
            // Gives the user an appropriate message if the user hasn't put in a personalNumber:
            carClaimRegistration.setCustomerArea(PERSONALNUMBER_EMPTY_MESSAGE);
            return;
        }
        // TODO: Regex.
        // Searches for the customer by personalNumber:
        Customer customer = customers.findCustomerByPersonalNumber(personalNumber);
        if(customer == null) {
            // Gives the user an appropriate message if the customer wasn't found:
            carClaimRegistration.setCustomerArea(PERSONALNUMBER_NOT_FOUND_MESSAGE + personalNumber);
        } else {
            // Finds the customers insurances:
            int customerId = customer.getId();
            // Displays the customer:
            carClaimRegistration.setCustomerArea(customer.toString());
            // Set this id as the selected one in the view class:
            carClaimRegistration.setSelectedCustomerId(customerId);
            List insuranceList = insurances.getAllActiveTypeInsurancesByCustomerId(customerId, CarInsurance.class);
            if (!insuranceList.isEmpty()) {
                // Displays the insurances if there is at least one:
                carClaimRegistration.populateInsurancesTable(insuranceList);
            }
        }
    }
    
    private void carClaimSelectInsuranceButtonEventHandler(ActionEvent event) {
        carClaimRegistration.clearMessages();
        Insurance insurance = carClaimRegistration.getInsuranceTableValue();
        String message = "";
        if (insurance instanceof CarInsurance) {
            CarInsuranceCoverage coverage = (CarInsuranceCoverage) insurance.getCoverage();
            carClaimRegistration.setDamages(coverage.damages());
            message = insurance.getName() + " " + coverage.toString() + " er valgt.";
            carClaimRegistration.setSelectInsuranceMessage(message);
        } else {
            message = "Ingen forsikring valgt,";
            carClaimRegistration.setSelectInsuranceMessage(message);
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
                carClaimRegistration.setImage(image);
            } catch (IOException ioe) {
                logs.add(ioe.getStackTrace(), ioe.getMessage(), user);
            }
        }
    }
    
    // BOAT INSURANCE REGISTRATION EVENT HANDLERS
    
    private void boatInsuranceRegisterButtonEventHandler(ActionEvent e) {
        
        // Clears previous messages:
        boatInsuranceRegistration.clearMessages();
        
        // Collects information about the customer and the insurance:
        BoatInsuranceCoverage coverage = boatInsuranceRegistration.getCoverage();
        int customerId = boatInsuranceRegistration.getSelectedCustomerId();
        String excessString = boatInsuranceRegistration.getExcess();
        
        // Collects information about the boat:
        String alarmString = boatInsuranceRegistration.getAlarm();
        String brand = boatInsuranceRegistration.getBrand();
        String engineEffectString = boatInsuranceRegistration.getEngineEffect();
        String engineType = boatInsuranceRegistration.getEngineType();
        String lengthString = boatInsuranceRegistration.getLength();
        String model = boatInsuranceRegistration.getModel();
        String ownerPersonalNumber = boatInsuranceRegistration.getOwnerPersonalNumber();
        String registrationNumber = boatInsuranceRegistration.getRegistrationNumber();
        String registrationYearString = boatInsuranceRegistration.getRegistrationYear();
        String valueString = boatInsuranceRegistration.getValue();
        
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
            boatInsuranceRegistration.setBrandMessage(EMPTY_MESSAGE);
            abort = true;
        }
        if(coverage == null) {
            boatInsuranceRegistration.setCoverageMessage(EMPTY_MESSAGE);
            abort = true;
        }
        if(customerId == 0) {
            boatInsuranceRegistration.setCustomerSelectedMessage(NO_CUSTOMER_MESSAGE);
            abort = true;
        }
        if(engineType.equals("")) {
            boatInsuranceRegistration.setEngineTypeMessage(EMPTY_MESSAGE);
            abort = true;
        }
        if(model.equals("")) {
            boatInsuranceRegistration.setModelMessage(EMPTY_MESSAGE);
            abort = true;
        }
        if(ownerPersonalNumber.equals("")) {
            boatInsuranceRegistration.setOwnerPersonalNumberMessage(EMPTY_MESSAGE);
        }
        if(registrationNumber.equals("")) {
            boatInsuranceRegistration.setRegistrationNumberMessage(EMPTY_MESSAGE);
        }
        
        // Evaluates and converts Input:
        if(alarmString.equals("")) {
            boatInsuranceRegistration.setAlarmMessage(EMPTY_MESSAGE);
            abort = true;
        } else {
            alarm = alarmString.equals("Ja");
        }
        if(engineEffectString.equals("")) {
            boatInsuranceRegistration.setEngineEffectMessage(EMPTY_MESSAGE);
            abort = true;
        } else {
            try {
                engineEffect = Integer.parseInt(engineEffectString);
            } catch(NumberFormatException nfe) {
                boatInsuranceRegistration.setEngineEffectMessage(FORMAT_MESSAGE);
                abort = true;
            }
        }
        if(excessString.equals("")) {
            boatInsuranceRegistration.setExcessMessage(EMPTY_MESSAGE);
            abort = true;
        } else {
            try {
                excess = Integer.parseInt(excessString);
            } catch(NumberFormatException nfe) {
                boatInsuranceRegistration.setExcessMessage(FORMAT_MESSAGE);
                abort = true;
            }
        }
        if(lengthString.equals("")) {
            boatInsuranceRegistration.setLengthMessage(EMPTY_MESSAGE);
            abort = true;
        } else {
            try {
                length = Integer.parseInt(lengthString);
            } catch(NumberFormatException nfe) {
                boatInsuranceRegistration.setLengthMessage(FORMAT_MESSAGE);
                abort = true;
            }
        }
        if(registrationYearString.equals("")) {
            boatInsuranceRegistration.setRegistrationYearMessage(EMPTY_MESSAGE);
            abort = true;
        } else {
            try {
                registrationYear = Integer.parseInt(registrationYearString);
            } catch(NumberFormatException nfe) {
                boatInsuranceRegistration.setRegistrationYearMessage(FORMAT_MESSAGE);
                abort = true;
            }
        }
        if(valueString.equals("")) {
            boatInsuranceRegistration.setValueMessage(EMPTY_MESSAGE);
            abort = true;
        } else {
            try {
                value = Integer.parseInt(valueString);
            } catch(NumberFormatException nfe) {
                boatInsuranceRegistration.setValueMessage(FORMAT_MESSAGE);
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
            boatInsuranceRegistration.setRegisterButtonMessage(REGISTER_SUCCESS);
        } else {
            boatInsuranceRegistration.setRegisterButtonMessage(REGISTER_NO_SUCCESS);
        }
    }
    
    private void boatInsuranceSearchCustomerIdButtonEventHandler(ActionEvent event) {
        String customerIdString = boatInsuranceRegistration.getCustomerId();
        int customerId;
        if(customerIdString.equals("")) {
            // Gives the user an appropriate message if the user hasn't put in a customerId:
            boatInsuranceRegistration.setCustomerArea(CUSTOMERID_EMPTY_MESSAGE);
            return;
        }
        try {
            // Converts the customerId to int:
            customerId = Integer.parseInt(customerIdString);
        } catch(NumberFormatException nfe) {
            // Gives the user an appropriate message if the customerId wasn't formatted correctly:
            boatInsuranceRegistration.setCustomerArea(CUSTOMERID_FORMAT_MESSAGE);
            return;
        }
        // TODO: Regex.
        // Searches for the customer by customerId:
        Customer customer = customers.findCustomerById(customerId);
        if(customer == null) {
            // Gives the user an appropriate message if the customer wasn't found:
            boatInsuranceRegistration.setCustomerArea(CUSTOMERID_NOT_FOUND_MESSAGE + customerId);
        } else {
            // Displays the customer:
            boatInsuranceRegistration.setCustomerArea(customer.toString());
            boatInsuranceRegistration.setSelectedCustomerId(customerId);
            // Finds the customers insurances:
            List insuranceList = insurances.getAllInsurancesByCustomerId(customerId);
            if (!insuranceList.isEmpty()) {
                // Displays the insurances if there is at least one:
                boatInsuranceRegistration.populateInsurancesTable(insuranceList);
            }
        }
    }
    
    private void boatInsuranceSearchPersonalNumberButtonEventHandler(ActionEvent event) {
        String personalNumber = boatInsuranceRegistration.getPersonalNumber();
        if(personalNumber.equals("")) {
            // Gives the user an appropriate message if the user hasn't put in a personalNumber:
            boatInsuranceRegistration.setCustomerArea(PERSONALNUMBER_EMPTY_MESSAGE);
            return;
        }
        // TODO: Regex.
        // Searches for the customer by personalNumber:
        Customer customer = customers.findCustomerByPersonalNumber(personalNumber);
        if(customer == null) {
            // Gives the user an appropriate message if the customer wasn't found:
            boatInsuranceRegistration.setCustomerArea(PERSONALNUMBER_NOT_FOUND_MESSAGE + personalNumber);
        } else {
            // Finds the customers insurances:
            int customerId = customer.getId();
            // Displays the customer:
            boatInsuranceRegistration.setCustomerArea(customer.toString());
            boatInsuranceRegistration.setSelectedCustomerId(customerId);
            List insuranceList = insurances.getAllInsurancesByCustomerId(customerId);
            if (!insuranceList.isEmpty()) {
                // Displays the insurances if there is at least one:
                boatInsuranceRegistration.populateInsurancesTable(insuranceList);
            }
        }
    }
    
    // CAR INSURANCE REGISTRATION EVENT HANDLERS
    
    private void carInsuranceRegisterButtonEventHandler(ActionEvent e) {
        
        // Clears previous messages:
        carInsuranceRegistration.clearMessages();
        
        // Collects information about the customer and the insurance:
        CarInsuranceCoverage coverage = carInsuranceRegistration.getCoverage();
        int customerId = carInsuranceRegistration.getSelectedCustomerId();
        String excessString = carInsuranceRegistration.getExcess();
        String existingBonusString = carInsuranceRegistration.getExistingBonus();
        String garageString = carInsuranceRegistration.getParkingCondition();
        String maxLengthString = carInsuranceRegistration.getDrivingLength();
        String youngDriverString = carInsuranceRegistration.getYoungestDriver();
        
        // Collects information about the car:
        String alarmString = carInsuranceRegistration.getAlarm();
        String brand = carInsuranceRegistration.getBrand();
        String model = carInsuranceRegistration.getModel();
        String ownerPersonalNumber = carInsuranceRegistration.getOwnerPersonalNumber();
        String registrationNumber = carInsuranceRegistration.getRegistrationNumber();
        String registrationYearString = carInsuranceRegistration.getYear();
        
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
            carInsuranceRegistration.setBrandMessage(EMPTY_MESSAGE);
            abort = true;
        }
        if(coverage == null) {
            carInsuranceRegistration.setCoverageMessage(EMPTY_MESSAGE);
            abort = true;
        }
        if(customerId == 0) {
            carInsuranceRegistration.setCustomerSelectedMessage(NO_CUSTOMER_MESSAGE);
            abort = true;
        }
        if(model.equals("")) {
            carInsuranceRegistration.setModelMessage(EMPTY_MESSAGE);
            abort = true;
        }
        if(ownerPersonalNumber.equals("")) {
            carInsuranceRegistration.setOwnerPersonalNumberMessage(EMPTY_MESSAGE);
        }
        if(registrationNumber.equals("")) {
            carInsuranceRegistration.setRegistrationNumberMessage(EMPTY_MESSAGE);
        }
        
        // Evaluates and converts Input:
        if(alarmString.equals("")) {
            carInsuranceRegistration.setAlarmMessage(EMPTY_MESSAGE);
            abort = true;
        } else {
            alarm = alarmString.equals("Ja");
        }
        if(excessString.equals("")) {
            carInsuranceRegistration.setExcessMessage(EMPTY_MESSAGE);
            abort = true;
        } else {
            try {
                excess = Integer.parseInt(excessString);
            } catch(NumberFormatException nfe) {
                carInsuranceRegistration.setExcessMessage(FORMAT_MESSAGE);
                abort = true;
            }
        }
        if(existingBonusString.equals("")) {
            carInsuranceRegistration.setExistingBonusMessage(EMPTY_MESSAGE);
            abort = true;
        } else {
            try {
                existingBonus = Integer.parseInt(existingBonusString);
            } catch(NumberFormatException nfe) {
                carInsuranceRegistration.setExistingBonusMessage(FORMAT_MESSAGE);
                abort = true;
            }
        }
        if(garageString.equals("")) {
            carInsuranceRegistration.setParkingConditionMessage(EMPTY_MESSAGE);
            abort = true;
        } else {
            garage = garageString.equals("Har garasje");
        }
        if(maxLengthString.equals("")) {
            carInsuranceRegistration.setDrivingLengthMessage(EMPTY_MESSAGE);
            abort = true;
        } else {
            try {
                maxLength = Integer.parseInt(maxLengthString);
            } catch(NumberFormatException nfe) {
                carInsuranceRegistration.setDrivingLengthMessage(FORMAT_MESSAGE);
                abort = true;
            }
        }
        if(registrationYearString.equals("")) {
            carInsuranceRegistration.setYearMessage(EMPTY_MESSAGE);
            abort = true;
        } else {
            try {
                registrationYear = Integer.parseInt(registrationYearString);
            } catch(NumberFormatException nfe) {
                carInsuranceRegistration.setYearMessage(FORMAT_MESSAGE);
                abort = true;
            }
        }
        if(youngDriverString.equals("")) {
            carInsuranceRegistration.setYoungestDriverMessage(EMPTY_MESSAGE);
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
        
        // Adds insurance to Register:
        if (insurances.addInsurance(insurance)) {
            carInsuranceRegistration.setRegisterButtonMessage(REGISTER_SUCCESS);
        } else {
            carInsuranceRegistration.setRegisterButtonMessage(REGISTER_NO_SUCCESS);
        }
    }
    
    private void carInsuranceSearchCustomerIdButtonEventHandler(ActionEvent event) {
        String customerIdString = carInsuranceRegistration.getCustomerId();
        int customerId;
        if(customerIdString.equals("")) {
            // Gives the user an appropriate message if the user hasn't put in a customerId:
            carInsuranceRegistration.setCustomerArea(CUSTOMERID_EMPTY_MESSAGE);
            return;
        }
        try {
            // Converts the customerId to int:
            customerId = Integer.parseInt(customerIdString);
        } catch(NumberFormatException nfe) {
            // Gives the user an appropriate message if the customerId wasn't formatted correctly:
            carInsuranceRegistration.setCustomerArea(CUSTOMERID_FORMAT_MESSAGE);
            return;
        }
        // TODO: Regex.
        // Searches for the customer by customerId:
        Customer customer = customers.findCustomerById(customerId);
        if(customer == null) {
            // Gives the user an appropriate message if the customer wasn't found:
            carInsuranceRegistration.setCustomerArea(CUSTOMERID_NOT_FOUND_MESSAGE + customerId);
        } else {
            // Displays the customer:
            carInsuranceRegistration.setCustomerArea(customer.toString());
            carInsuranceRegistration.setSelectedCustomerId(customerId);
            // Finds the customers insurances:
            List insuranceList = insurances.getAllInsurancesByCustomerId(customerId);
            if (!insuranceList.isEmpty()) {
                // Displays the insurances if there is at least one:
                carInsuranceRegistration.populateInsurancesTable(insuranceList);
            }
        }
    }
    
    private void carInsuranceSearchPersonalNumberButtonEventHandler(ActionEvent event) {
        String personalNumber = carInsuranceRegistration.getPersonalNumber();
        if(personalNumber.equals("")) {
            // Gives the user an appropriate message if the user hasn't put in a personalNumber:
            carInsuranceRegistration.setCustomerArea(PERSONALNUMBER_EMPTY_MESSAGE);
            return;
        }
        // TODO: Regex.
        // Searches for the customer by personalNumber:
        Customer customer = customers.findCustomerByPersonalNumber(personalNumber);
        if(customer == null) {
            // Gives the user an appropriate message if the customer wasn't found:
            carInsuranceRegistration.setCustomerArea(PERSONALNUMBER_NOT_FOUND_MESSAGE + personalNumber);
        } else {
            // Finds the customers insurances:
            int customerId = customer.getId();
            // Displays the customer:
            carInsuranceRegistration.setCustomerArea(customer.toString());
            carInsuranceRegistration.setSelectedCustomerId(customerId);
            List insuranceList = insurances.getAllInsurancesByCustomerId(customerId);
            if (!insuranceList.isEmpty()) {
                // Displays the insurances if there is at least one:
                carInsuranceRegistration.populateInsurancesTable(insuranceList);
            }
        }
    }
    
    // HOLIDAY HOME INSURANCE EVENT HANDLERS
    
    private void holidayHomeInsuranceRegisterButtonEventHandler(ActionEvent e) {
        
        // Clears previous messages:
        holidayHomeInsuranceRegistration.clearMessages();
        
        // Collects information about the customer and the insurance:
        HolidayHomeInsuranceCoverage coverage = holidayHomeInsuranceRegistration.getCoverage();
        int customerId = holidayHomeInsuranceRegistration.getSelectedCustomerId();
        String excessString = holidayHomeInsuranceRegistration.getExcess();
        
        // Collects information about the property:
        HolidayHomeType type = holidayHomeInsuranceRegistration.getType();
        PropertyMaterial material = holidayHomeInsuranceRegistration.getMaterial();
        String areaString = holidayHomeInsuranceRegistration.getArea();
        String city = holidayHomeInsuranceRegistration.getCity();
        String rentalString = holidayHomeInsuranceRegistration.getRental();
        String street = holidayHomeInsuranceRegistration.getStreet();
        String yearString = holidayHomeInsuranceRegistration.getYear();
        String zipCodeString = holidayHomeInsuranceRegistration.getZipCode();
        
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
            holidayHomeInsuranceRegistration.setCityMessage(EMPTY_MESSAGE);
            abort = true;
        }
        if(coverage == null) {
            holidayHomeInsuranceRegistration.setCoverageMessage(EMPTY_MESSAGE);
            abort = true;
        }
        if(customerId == 0) {
            holidayHomeInsuranceRegistration.setCustomerSelectedMessage(NO_CUSTOMER_MESSAGE);
            abort = true;
        }
        if(material == null) {
            holidayHomeInsuranceRegistration.setMaterialMessage(EMPTY_MESSAGE);
            abort = true;
        }
        if(street.equals("")) {
            holidayHomeInsuranceRegistration.setStreetMessage(EMPTY_MESSAGE);
            abort = true;
        }
        if(type == null) {
            holidayHomeInsuranceRegistration.setTypeMessage(EMPTY_MESSAGE);
            abort = true;
        }
        
        // Evaluates and converts Input:
        if(areaString.equals("")) {
            holidayHomeInsuranceRegistration.setAreaMessage(EMPTY_MESSAGE);
            abort = true;
        } else {
            try {
                area = Integer.parseInt(areaString);
            } catch(NumberFormatException nfe) {
                holidayHomeInsuranceRegistration.setAreaMessage(FORMAT_MESSAGE);
                abort = true;
            }
        }
        if(excessString.equals("")) {
            holidayHomeInsuranceRegistration.setExcessMessage(EMPTY_MESSAGE);
            abort = true;
        } else {
            try {
                excess = Integer.parseInt(excessString);
            } catch(NumberFormatException nfe) {
                holidayHomeInsuranceRegistration.setExcessMessage(FORMAT_MESSAGE);
                abort = true;
            }
        }
        if(rentalString.equals("")) {
            holidayHomeInsuranceRegistration.setRentalMessage(EMPTY_MESSAGE);
            abort = true;
        } else {
            rental = rentalString.equals("Ja");
        }
        if(yearString.equals("")) {
            holidayHomeInsuranceRegistration.setYearMessage(EMPTY_MESSAGE);
            abort = true;
        } else {
            try {
                year = Integer.parseInt(yearString);
            } catch(NumberFormatException nfe) {
                holidayHomeInsuranceRegistration.setYearMessage(FORMAT_MESSAGE);
                abort = true;
            }
        }
        if(zipCodeString.equals("")) {
            holidayHomeInsuranceRegistration.setZipCodeMessage(EMPTY_MESSAGE);
            abort = true;
        } else {
            try {
                zipCode = Integer.parseInt(zipCodeString);
            } catch(NumberFormatException nfe) {
                holidayHomeInsuranceRegistration.setZipCodeMessage(FORMAT_MESSAGE);
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
            holidayHomeInsuranceRegistration.setRegisterButtonMessage(REGISTER_SUCCESS);
        } else {
            holidayHomeInsuranceRegistration.setRegisterButtonMessage(REGISTER_NO_SUCCESS);
        }
    }
    
    private void holidayHomeInsuranceSearchCustomerIdButtonEventHandler(ActionEvent event) {
        String customerIdString = holidayHomeInsuranceRegistration.getCustomerId();
        int customerId;
        if(customerIdString.equals("")) {
            // Gives the user an appropriate message if the user hasn't put in a customerId:
            holidayHomeInsuranceRegistration.setCustomerArea(CUSTOMERID_EMPTY_MESSAGE);
            return;
        }
        try {
            // Converts the customerId to int:
            customerId = Integer.parseInt(customerIdString);
        } catch(NumberFormatException nfe) {
            // Gives the user an appropriate message if the customerId wasn't formatted correctly:
            holidayHomeInsuranceRegistration.setCustomerArea(CUSTOMERID_FORMAT_MESSAGE);
            return;
        }
        // TODO: Regex.
        // Searches for the customer by customerId:
        Customer customer = customers.findCustomerById(customerId);
        if(customer == null) {
            // Gives the user an appropriate message if the customer wasn't found:
            holidayHomeInsuranceRegistration.setCustomerArea(CUSTOMERID_NOT_FOUND_MESSAGE + customerId);
        } else {
            // Displays the customer:
            holidayHomeInsuranceRegistration.setCustomerArea(customer.toString());
            holidayHomeInsuranceRegistration.setSelectedCustomerId(customerId);
            // Finds the customers insurances:
            List insuranceList = insurances.getAllInsurancesByCustomerId(customerId);
            if (!insuranceList.isEmpty()) {
                // Displays the insurances if there is at least one:
                holidayHomeInsuranceRegistration.populateInsurancesTable(insuranceList);
            }
        }
    } // end of method holidayHomeInsuranceSearchCustomerIdButtonEventHandler
    
    private void holidayHomeInsuranceSearchPersonalNumberButtonEventHandler(ActionEvent event) {
        String personalNumber = holidayHomeInsuranceRegistration.getPersonalNumber();
        if(personalNumber.equals("")) {
            // Gives the user an appropriate message if the user hasn't put in a personalNumber:
            holidayHomeInsuranceRegistration.setCustomerArea(PERSONALNUMBER_EMPTY_MESSAGE);
            return;
        }
        // TODO: Regex.
        // Searches for the customer by personalNumber:
        Customer customer = customers.findCustomerByPersonalNumber(personalNumber);
        if(customer == null) {
            // Gives the user an appropriate message if the customer wasn't found:
            holidayHomeInsuranceRegistration.setCustomerArea(PERSONALNUMBER_NOT_FOUND_MESSAGE + personalNumber);
        } else {
            // Finds the customers insurances:
            int customerId = customer.getId();
            // Displays the customer:
            holidayHomeInsuranceRegistration.setCustomerArea(customer.toString());
            holidayHomeInsuranceRegistration.setSelectedCustomerId(customerId);
            List insuranceList = insurances.getAllInsurancesByCustomerId(customerId);
            if (!insuranceList.isEmpty()) {
                // Displays the insurances if there is at least one:
                holidayHomeInsuranceRegistration.populateInsurancesTable(insuranceList);
            }
        }
    } // end of method holidayHomeInsuranceSearchPersonalNumberButtonEventHandler
    
    // HOLIDAY HOME CONTENT INSURANCE EVENT HANDLERS
    
    private void holidayHomeContentInsuranceRegisterButtonEventHandler(ActionEvent e) {
        
        // Clears previous messages:
        holidayHomeContentInsuranceRegistration.clearMessages();
        
        // Collects information about the customer and the insurance:
        HolidayHomeContentInsuranceCoverage coverage = holidayHomeContentInsuranceRegistration.getCoverage();
        int customerId = holidayHomeContentInsuranceRegistration.getSelectedCustomerId();
        String excessString = holidayHomeContentInsuranceRegistration.getExcess();
        
        // Collects information about the property:
        HolidayHomeType type = holidayHomeContentInsuranceRegistration.getType();
        PropertyMaterial material = holidayHomeContentInsuranceRegistration.getMaterial();
        String amountString = holidayHomeContentInsuranceRegistration.getAmount();
        String areaString = holidayHomeContentInsuranceRegistration.getArea();
        String city = holidayHomeContentInsuranceRegistration.getCity();
        String street = holidayHomeContentInsuranceRegistration.getStreet();
        String yearString = holidayHomeContentInsuranceRegistration.getYear();
        String zipCodeString = holidayHomeContentInsuranceRegistration.getZipCode();
        
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
            holidayHomeContentInsuranceRegistration.setCityMessage(EMPTY_MESSAGE);
            abort = true;
        }
        if(coverage == null) {
            holidayHomeContentInsuranceRegistration.setCoverageMessage(EMPTY_MESSAGE);
            abort = true;
        }
        if(customerId == 0) {
            holidayHomeContentInsuranceRegistration.setCustomerSelectedMessage(NO_CUSTOMER_MESSAGE);
            abort = true;
        }
        if(material == null) {
            holidayHomeContentInsuranceRegistration.setMaterialMessage(EMPTY_MESSAGE);
            abort = true;
        }
        if(street.equals("")) {
            holidayHomeContentInsuranceRegistration.setStreetMessage(EMPTY_MESSAGE);
            abort = true;
        }
        if(type == null) {
            holidayHomeContentInsuranceRegistration.setTypeMessage(EMPTY_MESSAGE);
            abort = true;
        }
        
        // Evaluates and converts Input:
        if(areaString.equals("")) {
            holidayHomeContentInsuranceRegistration.setAreaMessage(EMPTY_MESSAGE);
            abort = true;
        } else {
            try {
                area = Integer.parseInt(areaString);
            } catch(NumberFormatException nfe) {
                holidayHomeContentInsuranceRegistration.setAreaMessage(FORMAT_MESSAGE);
                abort = true;
            }
        }
        if(amountString.equals("")) {
            homeContentInsuranceRegistration.setAmountMessage(EMPTY_MESSAGE);
            abort = true;
        } else {
            try {
                amount = Integer.parseInt(amountString);
            } catch(NumberFormatException nfe) {
                homeContentInsuranceRegistration.setAmountMessage(FORMAT_MESSAGE);
                abort = true;
            }
        }
        if(excessString.equals("")) {
            holidayHomeContentInsuranceRegistration.setExcessMessage(EMPTY_MESSAGE);
            abort = true;
        } else {
            try {
                excess = Integer.parseInt(excessString);
            } catch(NumberFormatException nfe) {
                holidayHomeContentInsuranceRegistration.setExcessMessage(FORMAT_MESSAGE);
                abort = true;
            }
        }
        if(yearString.equals("")) {
            holidayHomeContentInsuranceRegistration.setYearMessage(EMPTY_MESSAGE);
            abort = true;
        } else {
            try {
                year = Integer.parseInt(yearString);
            } catch(NumberFormatException nfe) {
                holidayHomeContentInsuranceRegistration.setYearMessage(FORMAT_MESSAGE);
                abort = true;
            }
        }
        if(zipCodeString.equals("")) {
            holidayHomeContentInsuranceRegistration.setZipCodeMessage(EMPTY_MESSAGE);
            abort = true;
        } else {
            try {
                zipCode = Integer.parseInt(zipCodeString);
            } catch(NumberFormatException nfe) {
                holidayHomeContentInsuranceRegistration.setZipCodeMessage(FORMAT_MESSAGE);
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
            holidayHomeContentInsuranceRegistration.setRegisterButtonMessage(REGISTER_SUCCESS);
        } else {
            holidayHomeContentInsuranceRegistration.setRegisterButtonMessage(REGISTER_NO_SUCCESS);
        }
    } // end of method holidayHomeContentInsuranceRegisterButtonEventHandler
    
    private void holidayHomeContentInsuranceSearchCustomerIdButtonEventHandler(ActionEvent event) {
        String customerIdString = holidayHomeContentInsuranceRegistration.getCustomerId();
        int customerId;
        if(customerIdString.equals("")) {
            // Gives the user an appropriate message if the user hasn't put in a customerId:
            holidayHomeContentInsuranceRegistration.setCustomerArea(CUSTOMERID_EMPTY_MESSAGE);
            return;
        }
        try {
            // Converts the customerId to int:
            customerId = Integer.parseInt(customerIdString);
        } catch(NumberFormatException nfe) {
            // Gives the user an appropriate message if the customerId wasn't formatted correctly:
            holidayHomeContentInsuranceRegistration.setCustomerArea(CUSTOMERID_FORMAT_MESSAGE);
            return;
        }
        // TODO: Regex.
        // Searches for the customer by customerId:
        Customer customer = customers.findCustomerById(customerId);
        if(customer == null) {
            // Gives the user an appropriate message if the customer wasn't found:
            holidayHomeContentInsuranceRegistration.setCustomerArea(CUSTOMERID_NOT_FOUND_MESSAGE + customerId);
        } else {
            // Displays the customer:
            holidayHomeContentInsuranceRegistration.setCustomerArea(customer.toString());
            holidayHomeContentInsuranceRegistration.setSelectedCustomerId(customerId);
            // Finds the customers insurances:
            List insuranceList = insurances.getAllInsurancesByCustomerId(customerId);
            if (!insuranceList.isEmpty()) {
                // Displays the insurances if there is at least one:
                holidayHomeContentInsuranceRegistration.populateInsurancesTable(insuranceList);
            }
        }
    } // end of method holidayHomeContentInsuranceSearchCustomerIdButtonEventHandler
    
    private void holidayHomeContentInsuranceSearchPersonalNumberButtonEventHandler(ActionEvent event) {
        String personalNumber = holidayHomeContentInsuranceRegistration.getPersonalNumber();
        if(personalNumber.equals("")) {
            // Gives the user an appropriate message if the user hasn't put in a personalNumber:
            holidayHomeContentInsuranceRegistration.setCustomerArea(PERSONALNUMBER_EMPTY_MESSAGE);
            return;
        }
        // TODO: Regex.
        // Searches for the customer by personalNumber:
        Customer customer = customers.findCustomerByPersonalNumber(personalNumber);
        if(customer == null) {
            // Gives the user an appropriate message if the customer wasn't found:
            holidayHomeContentInsuranceRegistration.setCustomerArea(PERSONALNUMBER_NOT_FOUND_MESSAGE + personalNumber);
        } else {
            // Finds the customers insurances:
            int customerId = customer.getId();
            // Displays the customer:
            holidayHomeContentInsuranceRegistration.setCustomerArea(customer.toString());
            holidayHomeContentInsuranceRegistration.setSelectedCustomerId(customerId);
            List insuranceList = insurances.getAllInsurancesByCustomerId(customerId);
            if (!insuranceList.isEmpty()) {
                // Displays the insurances if there is at least one:
                holidayHomeContentInsuranceRegistration.populateInsurancesTable(insuranceList);
            }
        }
    } // end of method holidayHomeContentInsuranceSearchPersonalNumberButtonEventHandler
    
    // HOME INSURANCE EVENT HANDLERS
    
    private void homeInsuranceRegisterButtonEventHandler(ActionEvent e) {
        
        // Clears previous messages:
        homeInsuranceRegistration.clearMessages();
        
        // Collects information about the customer and the insurance:
        HomeInsuranceCoverage coverage = homeInsuranceRegistration.getCoverage();
        int customerId = homeInsuranceRegistration.getSelectedCustomerId();
        String excessString = homeInsuranceRegistration.getExcess();
        
        // Collects information about the property:
        HomeType type = homeInsuranceRegistration.getType();
        PropertyMaterial material = homeInsuranceRegistration.getMaterial();
        String areaString = homeInsuranceRegistration.getArea();
        String city = homeInsuranceRegistration.getCity();
        String rentalString = homeInsuranceRegistration.getRental();
        String street = homeInsuranceRegistration.getStreet();
        String yearString = homeInsuranceRegistration.getYear();
        String zipCodeString = homeInsuranceRegistration.getZipCode();
        
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
            homeInsuranceRegistration.setCityMessage(EMPTY_MESSAGE);
            abort = true;
        }
        if(coverage == null) {
            homeInsuranceRegistration.setCoverageMessage(EMPTY_MESSAGE);
            abort = true;
        }
        if(customerId == 0) {
            homeInsuranceRegistration.setCustomerSelectedMessage(NO_CUSTOMER_MESSAGE);
            abort = true;
        }
        if(material == null) {
            homeInsuranceRegistration.setMaterialMessage(EMPTY_MESSAGE);
            abort = true;
        }
        if(street.equals("")) {
            homeInsuranceRegistration.setStreetMessage(EMPTY_MESSAGE);
            abort = true;
        }
        if(type == null) {
            homeInsuranceRegistration.setTypeMessage(EMPTY_MESSAGE);
            abort = true;
        }
        
        // Evaluates and converts Input:
        if(areaString.equals("")) {
            homeInsuranceRegistration.setAreaMessage(EMPTY_MESSAGE);
            abort = true;
        } else {
            try {
                area = Integer.parseInt(areaString);
            } catch(NumberFormatException nfe) {
                homeInsuranceRegistration.setAreaMessage(FORMAT_MESSAGE);

                abort = true;
            }
        }
        if(excessString.equals("")) {
            homeInsuranceRegistration.setExcessMessage(EMPTY_MESSAGE);
            abort = true;
        } else {
            try {
                excess = Integer.parseInt(excessString);
            } catch(NumberFormatException nfe) {
                homeInsuranceRegistration.setExcessMessage(FORMAT_MESSAGE);
                abort = true;
            }
        }
        if(rentalString.equals("")) {
            homeInsuranceRegistration.setRentalMessage(EMPTY_MESSAGE);
            abort = true;
        } else {
            rental = rentalString.equals("Ja");
        }
        if(yearString.equals("")) {
            homeInsuranceRegistration.setYearMessage(EMPTY_MESSAGE);
            abort = true;
        } else {
            try {
                year = Integer.parseInt(yearString);
            } catch(NumberFormatException nfe) {
                homeInsuranceRegistration.setYearMessage(FORMAT_MESSAGE);
                abort = true;
            }
        }
        if(zipCodeString.equals("")) {
            homeInsuranceRegistration.setZipCodeMessage(EMPTY_MESSAGE);
            abort = true;
        } else {
            try {
                zipCode = Integer.parseInt(zipCodeString);
            } catch(NumberFormatException nfe) {
                homeInsuranceRegistration.setZipCodeMessage(FORMAT_MESSAGE);
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
            homeInsuranceRegistration.setRegisterButtonMessage(REGISTER_SUCCESS);
        } else {
            homeInsuranceRegistration.setRegisterButtonMessage(REGISTER_NO_SUCCESS);
        }
    } // end of method homeInsuranceRegisterButtonEventHandler
    
    private void homeInsuranceSearchCustomerIdButtonEventHandler(ActionEvent event) {
        String customerIdString = homeInsuranceRegistration.getCustomerId();
        int customerId;
        if(customerIdString.equals("")) {
            // Gives the user an appropriate message if the user hasn't put in a customerId:
            homeInsuranceRegistration.setCustomerArea(CUSTOMERID_EMPTY_MESSAGE);
            return;
        }
        try {
            // Converts the customerId to int:
            customerId = Integer.parseInt(customerIdString);
        } catch(NumberFormatException nfe) {
            // Gives the user an appropriate message if the customerId wasn't formatted correctly:
            homeInsuranceRegistration.setCustomerArea(CUSTOMERID_FORMAT_MESSAGE);
            return;
        }
        // TODO: Regex.
        // Searches for the customer by customerId:
        Customer customer = customers.findCustomerById(customerId);
        if(customer == null) {
            // Gives the user an appropriate message if the customer wasn't found:
            homeInsuranceRegistration.setCustomerArea(CUSTOMERID_NOT_FOUND_MESSAGE + customerId);
        } else {
            // Displays the customer:
            homeInsuranceRegistration.setCustomerArea(customer.toString());
            homeInsuranceRegistration.setSelectedCustomerId(customerId);
            // Finds the customers insurances:
            List insuranceList = insurances.getAllInsurancesByCustomerId(customerId);
            if (!insuranceList.isEmpty()) {
                // Displays the insurances if there is at least one:
                homeInsuranceRegistration.populateInsurancesTable(insuranceList);
            }
        }
    } // end of method homeInsuranceSearchCustomerIdButtonEventHandler
    
    private void homeInsuranceSearchPersonalNumberButtonEventHandler(ActionEvent event) {
        String personalNumber = homeInsuranceRegistration.getPersonalNumber();
        if(personalNumber.equals("")) {
            // Gives the user an appropriate message if the user hasn't put in a personalNumber:
            homeInsuranceRegistration.setCustomerArea(PERSONALNUMBER_EMPTY_MESSAGE);
            return;
        }
        // TODO: Regex.
        // Searches for the customer by personalNumber:
        Customer customer = customers.findCustomerByPersonalNumber(personalNumber);
        if(customer == null) {
            // Gives the user an appropriate message if the customer wasn't found:
            homeInsuranceRegistration.setCustomerArea(PERSONALNUMBER_NOT_FOUND_MESSAGE + personalNumber);
        } else {
            // Finds the customers insurances:
            int customerId = customer.getId();
            // Displays the customer:
            homeInsuranceRegistration.setCustomerArea(customer.toString());
            homeInsuranceRegistration.setSelectedCustomerId(customerId);
            List insuranceList = insurances.getAllInsurancesByCustomerId(customerId);
            if (!insuranceList.isEmpty()) {
                // Displays the insurances if there is at least one:
                homeInsuranceRegistration.populateInsurancesTable(insuranceList);
            }
        }
    } // end of method homeInsuranceSearchPersonalNumberButtonEventHandler
    
    // HOME CONTENT INSURANCE EVENT HANDLERS
    
    private void homeContentInsuranceRegisterButtonEventHandler(ActionEvent e) {
        
        // Clears previous messages:
        homeContentInsuranceRegistration.clearMessages();
        
        // Collects information about the customer and the insurance:
        HomeContentInsuranceCoverage coverage = homeContentInsuranceRegistration.getCoverage();
        int customerId = homeContentInsuranceRegistration.getSelectedCustomerId();
        String amountString = homeContentInsuranceRegistration.getAmount();
        String excessString = homeContentInsuranceRegistration.getExcess();
        
        // Collects information about the property:
        HomeType type = homeContentInsuranceRegistration.getType();
        PropertyMaterial material = homeContentInsuranceRegistration.getMaterial();
        String areaString = homeContentInsuranceRegistration.getArea();
        String city = homeContentInsuranceRegistration.getCity();
        String street = homeContentInsuranceRegistration.getStreet();
        String yearString = homeContentInsuranceRegistration.getYear();
        String zipCodeString = homeContentInsuranceRegistration.getZipCode();
        
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
            homeContentInsuranceRegistration.setCityMessage(EMPTY_MESSAGE);
            abort = true;
        }
        if(coverage == null) {
            homeContentInsuranceRegistration.setCoverageMessage(EMPTY_MESSAGE);
            abort = true;
        }
        if(customerId == 0) {
            homeContentInsuranceRegistration.setCustomerSelectedMessage(NO_CUSTOMER_MESSAGE);
            abort = true;
        }
        if(material == null) {
            homeContentInsuranceRegistration.setMaterialMessage(EMPTY_MESSAGE);
            abort = true;
        }
        if(street.equals("")) {
            homeContentInsuranceRegistration.setStreetMessage(EMPTY_MESSAGE);
            abort = true;
        }
        if(type == null) {
            homeContentInsuranceRegistration.setTypeMessage(EMPTY_MESSAGE);
            abort = true;
        }
        
        // Evaluates and converts Input:
        if(areaString.equals("")) {
            homeContentInsuranceRegistration.setAreaMessage(EMPTY_MESSAGE);
            abort = true;
        } else {
            try {
                area = Integer.parseInt(areaString);
            } catch(NumberFormatException nfe) {
                homeContentInsuranceRegistration.setAreaMessage(FORMAT_MESSAGE);
                abort = true;
            }
        }
        if(amountString.equals("")) {
            homeContentInsuranceRegistration.setAmountMessage(EMPTY_MESSAGE);
            abort = true;
        } else {
            try {
                amount = Integer.parseInt(amountString);
            } catch(NumberFormatException nfe) {
                homeContentInsuranceRegistration.setAmountMessage(FORMAT_MESSAGE);
                abort = true;
            }
        }
        if(excessString.equals("")) {
            homeContentInsuranceRegistration.setExcessMessage(EMPTY_MESSAGE);
            abort = true;
        } else {
            try {
                excess = Integer.parseInt(excessString);
            } catch(NumberFormatException nfe) {
                homeContentInsuranceRegistration.setExcessMessage(FORMAT_MESSAGE);
                abort = true;
            }
        }
        if(yearString.equals("")) {
            homeContentInsuranceRegistration.setYearMessage(EMPTY_MESSAGE);
            abort = true;
        } else {
            try {
                year = Integer.parseInt(yearString);
            } catch(NumberFormatException nfe) {
                homeContentInsuranceRegistration.setYearMessage(FORMAT_MESSAGE);
                abort = true;
            }
        }
        if(zipCodeString.equals("")) {
            homeContentInsuranceRegistration.setZipCodeMessage(EMPTY_MESSAGE);
            abort = true;
        } else {
            try {
                zipCode = Integer.parseInt(zipCodeString);
            } catch(NumberFormatException nfe) {
                homeContentInsuranceRegistration.setZipCodeMessage(FORMAT_MESSAGE);
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
            homeContentInsuranceRegistration.setRegisterButtonMessage(REGISTER_SUCCESS);
        } else {
            homeContentInsuranceRegistration.setRegisterButtonMessage(REGISTER_NO_SUCCESS);
        }
    } // end of method homeContentInsuranceRegisterButtonEventHandler
    
    private void homeContentInsuranceSearchCustomerIdButtonEventHandler(ActionEvent event) {
        String customerIdString = homeContentInsuranceRegistration.getCustomerId();
        int customerId;
        if(customerIdString.equals("")) {
            // Gives the user an appropriate message if the user hasn't put in a customerId:
            homeContentInsuranceRegistration.setCustomerArea(CUSTOMERID_EMPTY_MESSAGE);
            return;
        }
        try {
            // Converts the customerId to int:
            customerId = Integer.parseInt(customerIdString);
        } catch(NumberFormatException nfe) {
            // Gives the user an appropriate message if the customerId wasn't formatted correctly:
            homeContentInsuranceRegistration.setCustomerArea(CUSTOMERID_FORMAT_MESSAGE);
            return;
        }
        // TODO: Regex.
        // Searches for the customer by customerId:
        Customer customer = customers.findCustomerById(customerId);
        if(customer == null) {
            // Gives the user an appropriate message if the customer wasn't found:
            homeContentInsuranceRegistration.setCustomerArea(CUSTOMERID_NOT_FOUND_MESSAGE + customerId);
        } else {
            // Displays the customer:
            homeContentInsuranceRegistration.setCustomerArea(customer.toString());
            homeContentInsuranceRegistration.setSelectedCustomerId(customerId);
            // Finds the customers insurances:
            List insuranceList = insurances.getAllInsurancesByCustomerId(customerId);
            if (!insuranceList.isEmpty()) {
                // Displays the insurances if there is at least one:
                homeContentInsuranceRegistration.populateInsurancesTable(insuranceList);
            }
        }
    } // end of method homeContentInsuranceSearchCustomerIdButtonEventHandler
    
    private void homeContentInsuranceSearchPersonalNumberButtonEventHandler(ActionEvent event) {
        String personalNumber = homeContentInsuranceRegistration.getPersonalNumber();
        if(personalNumber.equals("")) {
            // Gives the user an appropriate message if the user hasn't put in a personalNumber:
            homeContentInsuranceRegistration.setCustomerArea(PERSONALNUMBER_EMPTY_MESSAGE);
            return;
        }
        // TODO: Regex.
        // Searches for the customer by personalNumber:
        Customer customer = customers.findCustomerByPersonalNumber(personalNumber);
        if(customer == null) {
            // Gives the user an appropriate message if the customer wasn't found:
            homeContentInsuranceRegistration.setCustomerArea(PERSONALNUMBER_NOT_FOUND_MESSAGE + personalNumber);
        } else {
            // Finds the customers insurances:
            int customerId = customer.getId();
            // Displays the customer:
            homeContentInsuranceRegistration.setCustomerArea(customer.toString());
            homeContentInsuranceRegistration.setSelectedCustomerId(customerId);
            List insuranceList = insurances.getAllInsurancesByCustomerId(customerId);
            if (!insuranceList.isEmpty()) {
                // Displays the insurances if there is at least one:
                homeContentInsuranceRegistration.populateInsurancesTable(insuranceList);
            }
        }
    } // end of method homeContentInsuranceSearchPersonalNumberButtonEventHandler
    
    // TRAVEL INSURANCE EVENT HANDLERS
    
    private void travelInsuranceRegisterButtonEventHandler(ActionEvent e) {
        
        // Clears previous messages:
        travelInsuranceRegistration.clearMessages();
        
        // Collects information about the customer and the insurance:
        TravelInsuranceCoverage coverage = travelInsuranceRegistration.getCoverage();
        int customerId = travelInsuranceRegistration.getSelectedCustomerId();
        String excessString = travelInsuranceRegistration.getExcess();
        
        // Creates a boolean which is to be set true if the user has made a 
        // mistake and the method has to abort:
        boolean abort = false;
        
        // Creates ints for the converted values:
        int excess = 0;
        
        // Evaluates Input:
        if(coverage == null) {
            travelInsuranceRegistration.setCoverageMessage(EMPTY_MESSAGE);
            abort = true;
        }
        if(customerId == 0) {
            travelInsuranceRegistration.setCustomerSelectedMessage(NO_CUSTOMER_MESSAGE);
            abort = true;
        }
        
        // Evaluates and converts Input:
        if(excessString.equals("")) {
            travelInsuranceRegistration.setExcessMessage(EMPTY_MESSAGE);
            abort = true;
        } else {
            try {
                excess = Integer.parseInt(excessString);
            } catch(NumberFormatException nfe) {
                travelInsuranceRegistration.setExcessMessage(FORMAT_MESSAGE);
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
            travelInsuranceRegistration.setRegisterButtonMessage(REGISTER_SUCCESS);
        } else {
            travelInsuranceRegistration.setRegisterButtonMessage(REGISTER_NO_SUCCESS);
        }
    } // end of method travelInsuranceRegisterButtonEventHandler
    
    private void travelInsuranceSearchCustomerIdButtonEventHandler(ActionEvent event) {
        String customerIdString = travelInsuranceRegistration.getCustomerId();
        int customerId;
        if(customerIdString.equals("")) {
            // Gives the user an appropriate message if the user hasn't put in a customerId:
            travelInsuranceRegistration.setCustomerArea(CUSTOMERID_EMPTY_MESSAGE);
            return;
        }
        try {
            // Converts the customerId to int:
            customerId = Integer.parseInt(customerIdString);
        } catch(NumberFormatException nfe) {
            // Gives the user an appropriate message if the customerId wasn't formatted correctly:
            travelInsuranceRegistration.setCustomerArea(CUSTOMERID_FORMAT_MESSAGE);
            return;
        }
        // TODO: Regex.
        // Searches for the customer by customerId:
        Customer customer = customers.findCustomerById(customerId);
        if(customer == null) {
            // Gives the user an appropriate message if the customer wasn't found:
            travelInsuranceRegistration.setCustomerArea(CUSTOMERID_NOT_FOUND_MESSAGE + customerId);
        } else {
            // Displays the customer:
            travelInsuranceRegistration.setCustomerArea(customer.toString());
            travelInsuranceRegistration.setSelectedCustomerId(customerId);
            // Finds the customers insurances:
            List insuranceList = insurances.getAllInsurancesByCustomerId(customerId);
            if (!insuranceList.isEmpty()) {
                // Displays the insurances if there is at least one:
                travelInsuranceRegistration.populateInsurancesTable(insuranceList);
            }
        }
    } // end of method travelInsuranceSearchCustomerIdButtonEventHandler
    
    private void travelInsuranceSearchPersonalNumberButtonEventHandler(ActionEvent event) {
        String personalNumber = travelInsuranceRegistration.getPersonalNumber();
        if(personalNumber.equals("")) {
            // Gives the user an appropriate message if the user hasn't put in a personalNumber:
            travelInsuranceRegistration.setCustomerArea(PERSONALNUMBER_EMPTY_MESSAGE);
            return;
        }
        // TODO: Regex.
        // Searches for the customer by personalNumber:
        Customer customer = customers.findCustomerByPersonalNumber(personalNumber);
        if(customer == null) {
            // Gives the user an appropriate message if the customer wasn't found:
            travelInsuranceRegistration.setCustomerArea(PERSONALNUMBER_NOT_FOUND_MESSAGE + personalNumber);
        } else {
            // Finds the customers insurances:
            int customerId = customer.getId();
            // Displays the customer:
            travelInsuranceRegistration.setCustomerArea(customer.toString());
            travelInsuranceRegistration.setSelectedCustomerId(customerId);
            List insuranceList = insurances.getAllInsurancesByCustomerId(customerId);
            if (!insuranceList.isEmpty()) {
                // Displays the insurances if there is at least one:
                travelInsuranceRegistration.populateInsurancesTable(insuranceList);
            }
        }
    } // end of method travelInsuranceSearchPersonalNumberButtonEventHandler
    
    // INSURANCE SEARCH EVENT HANDLERS
    
    private void insuranceSearchViewSearchEventHandler(ActionEvent event) {
        
        insuranceSearchView.clearMessages();
        
        // Collects information about the customer or insurance:
        String customerIdString = insuranceSearchView.getCustomerId();
        String personalNumber = insuranceSearchView.getPersonalNumber();
        String insuranceIdString = insuranceSearchView.getInsuranceId();
        
        // Collects information about the search terms:
        InsuranceType insuranceType = insuranceSearchView.getInsuranceType();
        boolean active = insuranceSearchView.getActive();
        Calendar fromDate = insuranceSearchView.getFromDate();
        Calendar toDate = insuranceSearchView.getToDate();
        
        // Declares ints and classes to be converted to:
        int customerId = 0;
        int insuranceId = 0;
        Class type = null;
        
        // Evaluates and converts input:
        if (!insuranceIdString.equals("")) {
            try {
                insuranceId = Integer.parseInt(insuranceIdString);
            } catch(NumberFormatException nfe) {
                insuranceSearchView.setIdMessage(INSURANCEID_FORMAT_MESSAGE);
                return;
            }
        }
        if (!customerIdString.equals("")) {
            try {
                customerId = Integer.parseInt(customerIdString);
            } catch(NumberFormatException nfe) {
                insuranceSearchView.setIdMessage(CUSTOMERID_FORMAT_MESSAGE);
                return;
            }
        }
        if (!(insuranceType == null)) {
            switch (insuranceType) {
                case BOAT_INSURANCE: type = BoatInsurance.class;
                    break;
                case CAR_INSURANCE: type = CarInsurance.class;
                    break;
                case HOME_INSURANCE: type = HomeInsurance.class;
                    break;
                case HOME_CONTENT_INSURANCE: type = HomeContentInsurance.class;
                    break;
                case HOLIDAY_HOME_INSURANCE: type = HolidayHomeInsurance.class;
                    break;
                case HOLIDAY_HOME_CONTENT_INSURANCE: type = HolidayHomeContentInsurance.class;
                    break;
                case TRAVEL_INSURANCE: type = TravelInsurance.class;
                    break;
                default: type = null;
            }
        }
        
        List<Insurance> insuranceList;
        if (insuranceId != 0) {
            insuranceList = insurances.getInsuranceById(insuranceId);
        } else if (customerId != 0) {
            insuranceList = insurances.getInsurancesByCustomerId(customerId, type, fromDate, toDate, active);
        } else if (!personalNumber.equals("")) {
            customerId = customers.findCustomerIdByPersonalNumber(personalNumber);
            insuranceList = insurances.getInsurancesByCustomerId(customerId, type, fromDate, toDate, active);
        } else {
            insuranceList = insurances.getInsurances(type, fromDate, toDate, active);
        }
        insuranceSearchView.populateInsurancesTable(insuranceList);
    }
    
    private void insuranceSearchViewSelectEventHandler(ActionEvent event) {
        
        insuranceSearchView.clearMessages();
        
       Insurance insurance = insuranceSearchView.getInsuranceTableValue();
       
       if (insurance == null) {
           insuranceSearchView.setSelectMessage(NO_INSURANCE_MESSAGE);
           return;
       }
       
       insuranceSearchView.setInsuranceArea(insurance.toString());
       
       if (insurance instanceof BoatInsurance) {
           insuranceSearchView.setAttachmentArea("Båt:", ((BoatInsurance)insurance).getBoat().toString());
       } else if (insurance instanceof CarInsurance) {
           insuranceSearchView.setAttachmentArea("Bil:", ((CarInsurance) insurance).getCar().toString());
       } else if (insurance instanceof HolidayHomeInsurance) {
           insuranceSearchView.setAttachmentArea("Eiendom:", ((HolidayHomeInsurance) insurance).getProperty().toString());
       } else if (insurance instanceof HolidayHomeContentInsurance) {
           insuranceSearchView.setAttachmentArea("Eiendom:", ((HolidayHomeContentInsurance) insurance).getProperty().toString());
       } else if (insurance instanceof HomeInsurance) {
           insuranceSearchView.setAttachmentArea("Eiendom:", ((HomeInsurance) insurance).getProperty().toString());
       } else if (insurance instanceof HomeContentInsurance) {
           insuranceSearchView.setAttachmentArea("Eiendom:", ((HomeContentInsurance) insurance).getProperty().toString());
       } else if (insurance instanceof TravelInsurance) {
           insuranceSearchView.removeAttachmentArea();
       }
    }
    
    private void insuranceSearchViewDeactivateEventHandler(ActionEvent event) {
        
        insuranceSearchView.clearMessages();
        
       Insurance insurance = insuranceSearchView.getInsuranceTableValue();
       
       if (insurance == null) {
           insuranceSearchView.setDeactivateMessage(NO_INSURANCE_MESSAGE);
       } else {
           insurance.setActive(!insurance.getActive());
           insuranceSearchView.clearView();
       }
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
