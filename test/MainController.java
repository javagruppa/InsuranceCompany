/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package insurancecompany.controller;

import insurancecompany.misc.EmployeeType;
import insurancecompany.misc.coverages.CarInsuranceCoverage;
import insurancecompany.model.bills.*;
import insurancecompany.model.claims.*;
import insurancecompany.model.datastructures.*;
import insurancecompany.model.datastructures.carinfo.*;
import insurancecompany.model.insurances.*;
import insurancecompany.model.people.*;
import insurancecompany.model.properties.*;
import insurancecompany.view.modules.*;
import insurancecompany.view.process.*;
import insurancecompany.view.register.claims.*;
import insurancecompany.view.register.insurances.*;
import insurancecompany.view.register.persons.*;
import insurancecompany.view.search.*;
import insurancecompany.view.statistics.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;
import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.stage.Stage;

/**
 * Main controller. This class acts as a communication between the model and the view.
 * @author André
 */
public class MainController {
    
    /** Reference to the logged in user.*/
    private Person user;
    
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
                employees, insurances, logs, views);
        this.viewController = new ViewController(adminView, views);
        
        readAllDataFromFile();
        setBrandComboBox();
        initializeEventHandlers();
    }
    
    private void initializeEventHandlers() {
        adminView.setSaveDataButtonEventHandler(this::adminViewSaveDataButtonEventHandler);
        adminView.setExitButtonEventHandler(this::adminViewExitButtonEventHandler);
        
        carInsuranceRegistration.setSearchCustomerIdButtonEventHandler(this::carInsuranceSearchCustomerButtonEventHandler);
        carInsuranceRegistration.setSearchPersonalNumberButtonEventHandler(this::carInsuranceSearchPersonalNumberButtonEventHandler);
        carInsuranceRegistration.setSelectCustomerButtonEventHandler(this::carInsuranceSelectCustomerButtonEventHandler);
        carInsuranceRegistration.setBrandComboListener(this::brandComboListener);
        carInsuranceRegistration.setYearComboListener(this::yearComboListener);
        carInsuranceRegistration.setCalculateButtonEventHandler(null);
        carInsuranceRegistration.setRegisterButtonEventHandler(this::carInsuranceRegisterButtonEventHandler);
     
        customerRegistration.setRegisterButtonEventHandler(this::registerCustomerButtonEventHandler);
        employeeRegistration.setRegisterButtonEventHandler(this::registerEmployeeButtonEventHandler);
    }
 
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
        int zipCode = 0;
        
        String personalNumber = customerRegistration.getPersonalNumberField();
        String firstName = customerRegistration.getFirstNameField();
        String lastName = customerRegistration.getLastNameField();
        String street = customerRegistration.getStreetField();
        String zipCodeS = customerRegistration.getZipCodeField();
        String city = customerRegistration.getCityField();
        String email = customerRegistration.getEmailField();
        String phone = customerRegistration.getPhoneField();
        
        customerRegistration.setPersonalNumberMessage("");
        customerRegistration.setFirstNameMessage("");
        customerRegistration.setLastNameMessage("");
        customerRegistration.setStreetMessage("");
        customerRegistration.setCityMessage("");
        customerRegistration.setEmailMessage("");
        customerRegistration.setPhoneMessage("");
        customerRegistration.setZipCodeMessage("");
        
        if (personalNumber.equals("")) {
            String message = "Fyll inn dette feltet.";
            customerRegistration.setPersonalNumberMessage(message);
            ok = false;
        } else if (!personalNumber.matches("\\d{11}")) {
            String message = "Fyll inn 11 siffer.";
            customerRegistration.setPersonalNumberMessage(message);
            ok = false;
        }
        
        if (firstName.equals("")) {
            String message = "Fyll inn dette feltet.";
            customerRegistration.setFirstNameMessage(message);
            ok = false;
        } else if (!firstName.matches("^[ÆØÅæøåA-Za-z]{2-30}")) {
            String message = "Fyll inn korrekt navn, kun bokstaver tillatt";
            customerRegistration.setFirstNameMessage(message);
            ok = false;
        }
        
        if (lastName.equals("")) {
            String message = "Fyll inn dette feltet.";
            customerRegistration.setLastNameMessage(message);
            ok = false;
        } else if (!lastName.matches("^[ÆØÅæøåA-Za-z]{2-30}")) {
            String message = "Fyll inn korrekt navn, kun bokstaver tillatt";
            customerRegistration.setLastNameMessage(message);
            ok = false;
        }
        
        if (street.equals("")) {
            String message = "Fyll inn dette feltet.";
            customerRegistration.setStreetMessage(message);
            ok = false;
        } else if (!street.matches("[ÆØÅæøåa-zA-Z0-9]{2-30}")) {
            String message = "Fyll inn korrekt gateadresse, kun bokstaver og"
                    + " tall tillatt";
            customerRegistration.setStreetMessage(message);
            ok = false;
        }
        
        if (city.equals("")) {
            String message = "Fyll inn dette feltet.";
            customerRegistration.setCityMessage(message);
            ok = false;
        } else if (!city.matches("^[æøåÆØÅa-zA-Z]{2-30}")) {
            String message = "Fyll inn korrekt poststed, kun bokstaver tillatt";
            customerRegistration.setCityMessage(message);
            ok = false;
        }
        
        if (email.equals("")) {
            String message = "Fyll inn dette feltet.";
            customerRegistration.setEmailMessage(message);
            ok = false;
        } else if (!email.matches("^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+(?:\\.[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+)*@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$")) {
            String message = "Fyll inn korrekt email adresse.";
            customerRegistration.setEmailMessage(message);
            ok = false;
        }
        
        if (phone.equals("")) {
            String message = "Fyll inn dette feltet.";
            customerRegistration.setPhoneMessage(message);
            ok = false;
        } else if (!phone.matches("\\d{8-13}")) {
            String message = "Fyll inn korrekt telefonnummer.";
            customerRegistration.setPhoneMessage(message);
            ok = false;
        }
        
        
        if (zipCodeS.equals("")) {
            String message = "Fyll inn dette feltet.";
            customerRegistration.setZipCodeMessage(message);
            ok = false;
        } else if (!zipCodeS.matches("\\d{4}")) {
            String message = "Fyll inn korrekt postnummer, 4 siffer.";
            customerRegistration.setZipCodeMessage(message);
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
            customerRegistration.setResultText(result);
        }
    }
    
    // TODO: Trenger regex og validering
    private void registerEmployeeButtonEventHandler(ActionEvent event) {
        boolean ok = true;
        int zipCode = 0;
        EmployeeType position = employeeRegistration.getPositionComboValue();
        String personalNumber = employeeRegistration.getPersonalNumberField();
        String firstName = employeeRegistration.getFirstNameField();
        String lastName = employeeRegistration.getLastNameField();
        String street = employeeRegistration.getStreetField();
        String zipCodeS = employeeRegistration.getZipCodeField();
        String city = employeeRegistration.getCityField();
        String email = employeeRegistration.getEmailField();
        String phone = employeeRegistration.getPhoneField();

        customerRegistration.setPersonalNumberMessage("");
        customerRegistration.setFirstNameMessage("");
        customerRegistration.setLastNameMessage("");
        customerRegistration.setStreetMessage("");
        customerRegistration.setZipCodeMessage("");
        customerRegistration.setCityMessage("");
        customerRegistration.setEmailMessage("");
        customerRegistration.setPersonalNumberMessage("");
        
        
        if (personalNumber.equals("")) {
            String message = "Fyll inn dette feltet.";
            customerRegistration.setPersonalNumberMessage(message);
            ok = false;
        } else if (!personalNumber.matches("\\d{11}")) {
            String message = "Fyll inn 11 siffer.";
            customerRegistration.setPersonalNumberMessage(message);
            ok = false;
        }
        
        if (firstName.equals("")) {
            String message = "Fyll inn dette feltet.";
            customerRegistration.setFirstNameMessage(message);
            ok = false;
        } else if (!firstName.matches("^[ÆØÅæøåA-Za-z]{2-30}")) {
            String message = "Fyll inn korrekt navn, kun bokstaver tillatt";
            customerRegistration.setFirstNameMessage(message);
            ok = false;
        }
        
        if (lastName.equals("")) {
            String message = "Fyll inn dette feltet.";
            customerRegistration.setLastNameMessage(message);
            ok = false;
        } else if (!lastName.matches("^[ÆØÅæøåA-Za-z]{2-30}")) {
            String message = "Fyll inn korrekt navn, kun bokstaver tillatt";
            customerRegistration.setLastNameMessage(message);
            ok = false;
        }
        
        if (street.equals("")) {
            String message = "Fyll inn dette feltet.";
            customerRegistration.setStreetMessage(message);
            ok = false;
        } else if (!street.matches("[ÆØÅæøåa-zA-Z0-9]{2-30}")) {
            String message = "Fyll inn korrekt gateadresse, kun bokstaver og"
                    + " tall tillatt";
            customerRegistration.setStreetMessage(message);
            ok = false;
        }
        
        if (city.equals("")) {
            String message = "Fyll inn dette feltet.";
            customerRegistration.setCityMessage(message);
            ok = false;
        } else if (!city.matches("^[æøåÆØÅa-zA-Z]{2-30}")) {
            String message = "Fyll inn korrekt poststed, kun bokstaver tillatt";
            customerRegistration.setCityMessage(message);
            ok = false;
        }
        
        if (email.equals("")) {
            String message = "Fyll inn dette feltet.";
            customerRegistration.setEmailMessage(message);
            ok = false;
        } else if (!email.matches("^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+(?:\\.[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+)*@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$")) {
            String message = "Fyll inn korrekt email adresse.";
            customerRegistration.setEmailMessage(message);
            ok = false;
        }
        
        if (phone.equals("")) {
            String message = "Fyll inn dette feltet.";
            customerRegistration.setPhoneMessage(message);
            ok = false;
        } else if (!phone.matches("\\d{8-13}")) {
            String message = "Fyll inn korrekt telefonnummer.";
            customerRegistration.setPhoneMessage(message);
            ok = false;
        }
 
        if (zipCodeS.equals("")) {
            String message = "Fyll inn dette feltet.";
            customerRegistration.setZipCodeMessage(message);
            ok = false;
        } else if (!zipCodeS.matches("\\d{4}")) {
            String message = "Fyll inn korrekt postnummer, 4 siffer.";
            customerRegistration.setZipCodeMessage(message);
            ok = false;
        } else {
            
            try {
                zipCode = Integer.parseInt(zipCodeS);
            } catch (NumberFormatException nfe) {
                    ok = false;
                    logs.add(nfe.getStackTrace(), nfe.getMessage(), user);
            }
        }
        
        // Creates an adress object for the customer:
        Address adress = new Address(street, zipCode, city);
        // Creates a new customer:
        Employee employee;

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
    
    
    private void carInsuranceSearchCustomerButtonEventHandler(ActionEvent event) {
        
        String customerId = carInsuranceRegistration.getCustomerIdField();
        
       // Customer c = customers.findCustomerById(customerId);
    }
    
    private void carInsuranceSearchPersonalNumberButtonEventHandler(ActionEvent event) {
        String personalNumber = carInsuranceRegistration.getPersonalNumberField();
        // TODO regex:
        // Search for customer by personal numer:
        Customer c = customers.findCustomerByPersonalNumber(personalNumber);
        // If we find a customer we proceed:
        if (c != null) {
            // Show the customer in the text area inside our view:
            carInsuranceRegistration.setCustomerArea(c.toString());
            int cId = c.getId();
            // Search for all insurances by 
            List inc = insurances.getAllInsurancesByCustomerId(cId);
            if (!inc.isEmpty()) {
                carInsuranceRegistration.populateInsurancesTable(inc);
            }
        }
    }
    
    private void carInsuranceSelectCustomerButtonEventHandler(ActionEvent event) {
        
        String idS = carInsuranceRegistration.getCustomerIdField();
        int id = Integer.parseInt(idS);
        carInsuranceRegistration.setSelectedCustomerId(id);
    }
    
    private void carInsuranceRegisterButtonEventHandler(ActionEvent event) {
        // get all fields to String, then 
        CarInsuranceCoverage coverage = carInsuranceRegistration.getCoverageCombo();
        int customerId = carInsuranceRegistration.getSelectedCustomerId();
        int excess = 1000;
        int maxLength = 100;
        CarInsurance ci = new CarInsurance(null, CarInsuranceCoverage.CASCO, customerId, excess, true, maxLength, true);
        if (insurances.addInsurance(ci)) {
            System.err.println("Registret");
        } else {
            System.err.println("Ikke reg");
        }
    }
    
    private void setBrandComboBox() {
        List cars = modelController.getCarInfos();
        carInsuranceRegistration.populateBrandComboBox(cars);
    }
    
    private void setYearComboBox() {
        int since = 1970;
        int to = Calendar.getInstance().get(Calendar.YEAR);
        List<String> years = new LinkedList();
        for (int i = since; i <= to; i++) {
            years.add(i + "");
        }
        
        carInsuranceRegistration.populateYearComboBox(years);
    }
    
    // INVISIBLE BUG: method gets called twice for every change.
    private void brandComboListener(ObservableValue observable, Object oldValue, Object newValue) {
        //Object value = carInsuranceRegistration.getBrandComboValue();
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
        if (years.size() != 0) {
            carInsuranceRegistration.populateYearComboBox(years);
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
                carInsuranceRegistration.populateModelComboBox(modelsResult);
  
            } catch (NumberFormatException nfe) {
                //setSomeMessage
                return;
            }
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
    
    public void show(Stage stage) {
        viewController.setStage(stage);
    }
}
