/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package insurancecompany.controller;


import insurancecompany.misc.coverages.BoatInsuranceCoverage;
import insurancecompany.misc.coverages.HomeInsuranceCoverage;
import insurancecompany.misc.coverages.TravelInsuranceCoverage;
import insurancecompany.misc.hometypes.HomeType;
import insurancecompany.model.datastructures.BillRegister;
import insurancecompany.model.datastructures.ClaimRegister;
import insurancecompany.model.datastructures.CustomerRegister;
import insurancecompany.model.datastructures.EmployeeRegister;
import insurancecompany.model.datastructures.InsuranceRegister;
import insurancecompany.model.datastructures.LogRegister;
import insurancecompany.model.datastructures.carinfo.*;
import insurancecompany.model.insurances.BoatInsurance;
import insurancecompany.model.insurances.HomeInsurance;
import insurancecompany.model.insurances.Insurance;
import insurancecompany.model.insurances.TravelInsurance;
import insurancecompany.model.people.Customer;
import insurancecompany.model.properties.Address;
import insurancecompany.model.properties.Property;
import insurancecompany.model.properties.PropertyMaterial;
import insurancecompany.model.vehicles.Boat;
import insurancecompany.view.modules.AdminView;
import insurancecompany.view.process.BillsProcessView;
import insurancecompany.view.process.ClaimsProcessView;
import insurancecompany.view.process.SubscriptionsProcessView;
import insurancecompany.view.register.claims.BoatClaimRegistration;
import insurancecompany.view.register.claims.CarClaimRegistration;
import insurancecompany.view.register.claims.HolidayHomeClaimRegistration;
import insurancecompany.view.register.claims.HolidayHomeContentClaimRegistration;
import insurancecompany.view.register.claims.HomeClaimRegistration;
import insurancecompany.view.register.claims.HomeContentClaimRegistration;
import insurancecompany.view.register.claims.TravelClaimRegistration;
import insurancecompany.view.register.insurances.BoatInsuranceRegistration;
import insurancecompany.view.register.insurances.CarInsuranceRegistration;
import insurancecompany.view.register.insurances.HolidayHomeContentInsuranceRegistration;
import insurancecompany.view.register.insurances.HolidayHomeInsuranceRegistration;
import insurancecompany.view.register.insurances.HomeContentInsuranceRegistration;
import insurancecompany.view.register.insurances.HomeInsuranceRegistration;
import insurancecompany.view.register.insurances.TravelInsuranceRegistration;
import insurancecompany.view.register.persons.CustomerRegistration;
import insurancecompany.view.register.persons.EmployeeRegistration;
import insurancecompany.view.search.ClaimSearchView;
import insurancecompany.view.search.CustomerSearchView;
import insurancecompany.view.search.EmployeeSearchView;
import insurancecompany.view.search.InsuranceSearchView;
import insurancecompany.view.statistics.ClaimStatisticsView;
import insurancecompany.view.statistics.CustomerStatisticsView;
import insurancecompany.view.statistics.EmployeeStatisticsView;
import insurancecompany.view.statistics.InsuranceStatisticsView;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javafx.event.ActionEvent;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

/**
 * Model controller. This class uses the model and its data structures to 
 * update other parts of the model as well as finding information by combining
 * methods and data from different parts of the model and its different 
 * data structures.
 * 
 * @author André
 * @author Sindre
 */
public class ModelController {
    
    
    // Models:
    private CarInfoRegister carInfoRegister;
    private EmployeeRegister employees;
    private CustomerRegister customers;
    private InsuranceRegister insurances;
    private ClaimRegister claims;
    private LogRegister logRegister;
    private BillRegister bills;
    
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
        
    // Creates strings to be used in messages to the user:
    private final String NO_CUSTOMER_MESSAGE = "Du har ikke valgt noen kunde.";
    private final String FORMAT_MESSAGE = "Dette feltet kan kun bestå av tall.";
    private final String EMPTY_MESSAGE = "Dette feltet må fylles ut.";
    private final String CUSTOMERID_FORMAT_MESSAGE = "Kundenummeret kan kun bestå av tall.";
    private final String CUSTOMERID_EMPTY_MESSAGE = "Du må skrive inn et kundenummer.";
    private final String CUSTOMERID_NOT_FOUND_MESSAGE = "Fant ingen kunde med kundenummer: ";
    private final String PERSONALNUMBER_EMPTY_MESSAGE = "Du må skrive inn et personnummer.";
    private final String PERSONALNUMBER_NOT_FOUND_MESSAGE = "Fant ingen kunde med personnummer: ";
    
    public ModelController(BillRegister bills, ClaimRegister claims, CustomerRegister customers, 
            EmployeeRegister employees, InsuranceRegister insurances, LogRegister logRegister,
            ArrayList<Object> views) {
        
        // Models:
        this.bills = bills;
        this.claims = claims;
        this.customers = customers;
        this.employees = employees;
        this.insurances = insurances;
        this.logRegister = logRegister;
        
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
        unmarshalCarInfoRegister();
    }
    
    public void initializeEventHandlers() {
        boatInsuranceRegistration.setRegisterButtonEventHandler(this::boatInsuranceRegisterButtonEventHandler);
        boatInsuranceRegistration.setSearchPersonalNumberButtonEventHandler(this::boatInsuranceSearchPersonalNumberButtonEventHandler);
        homeInsuranceRegistration.setRegisterButtonEventHandler(this::homeInsuranceRegisterButtonEventHandler);
        travelInsuranceRegistration.setRegisterButtonEventHandler(this::travelInsuranceRegisterButtonEventHandler);
    }
    
    /**
     * Returns all insurances based on personal number of the insurance owner.
     * @param personalNumber
     * @return 
     */
    public List<Insurance> getAllInsurancesByPersonalNumber(String personalNumber) {
        List<Insurance> result = new ArrayList<>();
        // Find the customer that matches the personal number:
        Customer c = customers.findCustomerByPersonalNumber(personalNumber);
        // If he/she exists:
        if (c != null) {
            // Get the customer id:
            int customerId = c.getId();
            // Use this id, to get all insurances of this customer:
            result = insurances.getAllInsurancesByCustomerId(customerId);
        }
        // Return the result:
        return result;
    }
    
    public final void unmarshalCarInfoRegister() {        
        try {
		File file = new File("src/insurancecompany/resources/xml/Car_makes_and_models.xml");
                JAXBContext jaxbContext = JAXBContext.newInstance(CarInfoRegister.class);
		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();               
		CarInfoRegister carInfoRegister = (CarInfoRegister) jaxbUnmarshaller.unmarshal(file);
                this.carInfoRegister = carInfoRegister;
 
	  } catch (JAXBException e) {
              logRegister.add(e.getStackTrace(), e.getMessage());
	  }
    }
    
    public List<CarInfo> getCarInfos() {
        return carInfoRegister.getCars();
    }
    
    public CarInfo findCarInfo(String name) {
        return carInfoRegister.findCarByName(name);
    }
    
    /**
     * Calculates whether a customer gets total customer discount or not
     * based on the number of active insurances of this customer.
     * @param customerId
     * @return 
     */
    public boolean calculateTotalCustomer(int customerId) {
        Customer c = customers.findCustomerById(customerId);
        int count = 0; // Counts the number of active insurances on this customer.
        List<Insurance> insuranceList = insurances.getAllInsurancesByCustomerId(customerId);
        for (Insurance a : insuranceList) {
            if (a.isActive()) {
                count++;
            }
        }
        if (count >= 3) {
            c.setTotalCustomer(true);
            return true;
        } else {
            c.setTotalCustomer(false);
            return false;
        }
    }
    
    /*private void registerCustomerEventHandler(ActionEvent e) {
        boolean ok = true;
        String output = "";
        
        String personalNumberS = customerRegistration.getPersonalNumberField().getText();
        String firstName = customerRegistration.getFirstNameField().getText();
        String lastName = customerRegistration.getLastNameField().getText();
        String street = customerRegistration.getStreetField().getText();
        String zipCodeS = customerRegistration.getZipCodeField().getText();
        String city = customerRegistration.getCityField().getText();
        String email = customerRegistration.getEmailField().getText();
        String phone = customerRegistration.getPhoneField().getText();
        
        if (personalNumberS.equals("")) {
            Label message = new Label();
            message.setText("*");
            message.setTextFill(Color.rgb(210, 39, 30));
            customerRegistration.setPersonalNumberMessage(message);
        }
 
        long personalNumber = 0;
        try {
            personalNumber = Long.parseLong(personalNumberS);
        } catch (NumberFormatException nfe) {

        }
        int zipCode = 0;
        try {
            zipCode = Integer.parseInt(zipCodeS);
        } catch (NumberFormatException nfe) {
            
        }
        
        Address adress = new Address(street, zipCode, city);
        
        Customer customer = new Customer(firstName, lastName, personalNumber, email, adress, phone);
        
        int customerId = customer.getId();
        System.out.println(customerId);
        //boolean ok1 = customers.addCustomer(customer);
        
        //Customer test = customers.findCustomerById(customerId);
        if (!ok) {
            output += "Får ikke lagt til kunden. Kunde med personnummer: " + personalNumberS 
                    + " eksisterer allerede i kunderegisteret.";
        }
    }*/
    
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
        BoatInsurance insurance = new BoatInsurance(boat, customerId, coverage, excess);
        
        // Adds insurance to Register:
        insurances.addInsurance(insurance); //returns boolean
        // TODO: Give a message to the user whether the insurance was added or not. It would not
        //       be added if the register already contained such an insurance.
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
    
    // HOME INSURANCE EVENT HANDLERS
    
    private void homeInsuranceRegisterButtonEventHandler(ActionEvent e) {
        
        // Clears previous messages:
        homeInsuranceRegistration.clearMessages();
        
        // Collects information about the customer and the insurance:
        HomeInsuranceCoverage coverage = homeInsuranceRegistration.getCoverage();
        int customerId = homeInsuranceRegistration.getSelectedCustomerId();
//        String amountString = homeInsuranceRegistration.getAmount();
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
        int amount = 0;
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
        }/*
        if(amountString.equals("")) {
            homeInsuranceRegistration.setAmountMessage(EMPTY_MESSAGE);
            abort = true;
        } else {
            try {
                amount = Integer.parseInt(amountString);
            } catch(NumberFormatException nfe) {
                homeInsuranceRegistration.setAmountMessage(FORMAT_MESSAGE);
                abort = true;
            }
        }*/
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
        HomeInsurance insurance = new HomeInsurance(customerId, excess, 
                property, type, coverage, rental);
        
        // Adds insurance to Register:
        insurances.addInsurance(insurance); //returns boolean
        // TODO: Give a message to the user whether the insurance was added or not. It would not
        //       be added if the register already contained such an insurance.
    }
    
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
    }
    
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
    }
    
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
        TravelInsurance insurance = new TravelInsurance(customerId, coverage, excess);
        
        // Adds insurance to Register:
        insurances.addInsurance(insurance); //returns boolean
        // TODO: Give a message to the user whether the insurance was added or not. It would not
        //       be added if the register already contained such an insurance.
    }
    
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
    }
    
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
    }
}
