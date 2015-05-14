/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package insurancecompany.controller;

import insurancecompany.model.datastructures.*;
import insurancecompany.model.datastructures.carinfo.*;
import insurancecompany.model.insurances.Insurance;
import insurancecompany.model.people.Customer;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
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
    
    public ModelController(BillRegister bills, ClaimRegister claims, 
            CustomerRegister customers, EmployeeRegister employees, 
            InsuranceRegister insurances, LogRegister logRegister) {
        
        // Models:
        this.bills = bills;
        this.claims = claims;
        this.customers = customers;
        this.employees = employees;
        this.insurances = insurances;
        this.logRegister = logRegister;
        
        unmarshalCarInfoRegister();
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
        // For every insurance that is active, count goes up by one:
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
