/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package insurancecompany.controller;


import insurancecompany.misc.coverages.BoatInsuranceCoverage;
import insurancecompany.misc.coverages.HomeContentInsuranceCoverage;
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
import insurancecompany.model.insurances.HomeContentInsurance;
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
    
    public final void initializeEventHandlers() {
        
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
} // end of class ModelController
