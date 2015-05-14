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
    // Declares models:
    private CarInfoRegister carInfoRegister;
    private EmployeeRegister employees;
    private CustomerRegister customers;
    private InsuranceRegister insurances;
    private ClaimRegister claims;
    private LogRegister logRegister;
    private BillRegister bills;
    
    /**
     * Constructor that initializes all models, which are specified as 
     * parameters.
     * 
     * @param bills
     * @param claims
     * @param customers
     * @param employees
     * @param insurances
     * @param logRegister 
     */
    public ModelController(BillRegister bills, ClaimRegister claims, 
            CustomerRegister customers, EmployeeRegister employees, 
            InsuranceRegister insurances, LogRegister logRegister) {
        // Initializes models:
        this.bills = bills;
        this.claims = claims;
        this.customers = customers;
        this.employees = employees;
        this.insurances = insurances;
        this.logRegister = logRegister;
        // Calls methods:
        unmarshalCarInfoRegister();
    }
    
    /**
     * Returns all ansurances based on the specified personal number of the 
     * desired customer.
     * 
     * @param personalNumber The specified personal number of the customer.
     * @return A List containing all insurances of the customer with the 
     * specified personal number.
     */
    public List<Insurance> getAllInsurancesByPersonalNumber(String personalNumber) {
        List<Insurance> result = new ArrayList<>();
        // Finds the customer that matches the personal number:
        Customer c = customers.findCustomerByPersonalNumber(personalNumber);
        // If the customer exists:
        if (c != null) {
            // Gets the customer id:
            int customerId = c.getId();
            // Uses this id, to get all insurances of the customer:
            result = insurances.getAllInsurancesByCustomerId(customerId);
        }
        // Returns the result:
        return result;
    }
    
    /**
     * Calculates whether a customer gets total customer discount or not
     * based on the number of active insurances of this customer.
     * 
     * @param customerId The customer id of the customer in question.
     * @return True if the customer is a total customer, which means the 
     * customer has three or more insurances.
     */
    public boolean calculateTotalCustomer(int customerId) {
        Customer c = customers.findCustomerById(customerId);
        // Counts the number of active insurances on this customer:
        int count = 0;
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
    
    // CAR INFO METHODS
    
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
} // end of class ModelController
