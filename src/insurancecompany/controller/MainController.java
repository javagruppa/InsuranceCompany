/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package insurancecompany.controller;

import insurancecompany.view.register.persons.*;
import insurancecompany.view.register.insurances.*;
import insurancecompany.model.datastructures.*;
import insurancecompany.model.datastructures.carinfo.*;
import insurancecompany.model.people.*;
import java.io.*;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;
import javafx.beans.value.ObservableValue;
import javafx.stage.Stage;

/**
 *
 * @author André
 */
public class MainController {
    
    /** Reference to the logged in user.*/
    private Person user;
    
    // Models:
    private EmployeeRegister employees;
    private CustomerRegister customers;
    private InsuranceRegister insurances;
    private ClaimRegister claims;
    private LogRegister logs;
    private BillRegister bills;
    
    // Insurance Registration Views:
    private BoatInsuranceRegistration boatInsuranceRegistration;
    private CarInsuranceRegistration carInsuranceRegistration;
    private HolidayHomeInsuranceRegistration holidayHomeInsuranceRegistration;
    private HomeInsuranceRegistration homeInsuranceRegistration;
    private TravelInsuranceRegistration travelInsuranceRegistration;
    // Person Registration Views:
    private CustomerRegistration customerRegistration;
    private EmployeeRegistration employeeRegistration;
    
    // Controllers:
    private ModelController modelController;
    private ViewController viewController;
    
    public MainController()  {
        
        this.employees = new EmployeeRegister();
        this.customers = new CustomerRegister();
        this.insurances = new InsuranceRegister();
        this.claims = new ClaimRegister();
        this.logs = new LogRegister();
        this.bills = new BillRegister();
        
        this.boatInsuranceRegistration = new BoatInsuranceRegistration();
        this.carInsuranceRegistration = new CarInsuranceRegistration();
        this.holidayHomeInsuranceRegistration = new HolidayHomeInsuranceRegistration();
        this.homeInsuranceRegistration = new HomeInsuranceRegistration();
        this.travelInsuranceRegistration = new TravelInsuranceRegistration();
        this.customerRegistration = new CustomerRegistration();
        this.employeeRegistration = new EmployeeRegistration();
        
        
        this.modelController = new ModelController(claims, employees, insurances, customers);
        this.viewController = new ViewController(boatInsuranceRegistration, 
            carInsuranceRegistration, holidayHomeInsuranceRegistration,
            homeInsuranceRegistration, travelInsuranceRegistration, 
            customerRegistration, employeeRegistration);
        
        setBrandComboBox();
        initializeEventHandlers();
    }
    
    private void initializeEventHandlers() {
        carInsuranceRegistration.setBrandComboListener(this::brandComboListener);
        carInsuranceRegistration.setYearComboListener(this::yearComboListener);
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
    
    
    // READ AND WRITE FROM/TO FILE:
    
    public void writeClaimsToFile() {
        try {
            claims.writeClaimsToFile();
        } catch (NotSerializableException nse) {
            logs.add(nse.getStackTrace(), nse.getMessage(), user);
        } catch (IOException ioe) {
            logs.add(ioe.getStackTrace(), ioe.getMessage(), user);
        }
    }
    
    public void readClaimsFromFile() {
        try {
            claims.readClaimsFromFile();
        } catch (ClassNotFoundException cnfe) {
            logs.add(cnfe.getStackTrace(), cnfe.getMessage(), user);
        } catch (FileNotFoundException fnfe) {
            logs.add(fnfe.getStackTrace(), fnfe.getMessage(), user);
        } catch (IOException ioe) {
            logs.add(ioe.getStackTrace(), ioe.getMessage(), user);
        }
    }
    
    public void writeCustomersToFile() {
        try {
            customers.writeCustomersToFile();
        } catch (NotSerializableException nse) {
            logs.add(nse.getStackTrace(), nse.getMessage(), user);
        } catch (IOException ioe) {
            logs.add(ioe.getStackTrace(), ioe.getMessage(), user);
        }
    }
    
    public void readCustomersFromFile() {
        try {
            customers.readCustomersFromFile();
        } catch (ClassNotFoundException cnfe) {
            logs.add(cnfe.getStackTrace(), cnfe.getMessage(), user);
        } catch (FileNotFoundException fnfe) {
            logs.add(fnfe.getStackTrace(), fnfe.getMessage(), user);
        } catch (IOException ioe) {
            logs.add(ioe.getStackTrace(), ioe.getMessage(), user);
        }
    }
    
    public void writeEmployeesToFile() {
        try {
            employees.writeEmployeesToFile();
        } catch (NotSerializableException nse) {
            logs.add(nse.getStackTrace(), nse.getMessage(), user);
        } catch (IOException ioe) {
            logs.add(ioe.getStackTrace(), ioe.getMessage(), user);
        }
    }
    
    public void readEmployeesFromFile() {
        try {
            employees.readEmployeesFromFile();
        } catch (ClassNotFoundException cnfe) {
            logs.add(cnfe.getStackTrace(), cnfe.getMessage(), user);
        } catch (FileNotFoundException fnfe) {
            logs.add(fnfe.getStackTrace(), fnfe.getMessage(), user);
        } catch (IOException ioe) {
            logs.add(ioe.getStackTrace(), ioe.getMessage(), user);
        }
    }
    
    public void writeInsurancesToFile() {
        try {
            insurances.writeInsurancesToFile();
        } catch (NotSerializableException nse) {
            logs.add(nse.getStackTrace(), nse.getMessage(), user);
        } catch (IOException ioe) {
            logs.add(ioe.getStackTrace(), ioe.getMessage(), user);
        }
    }
    
    public void readInsurancesFromFile() {
        try {
            insurances.readInsurancesFromFile();
        } catch (ClassNotFoundException cnfe) {
            logs.add(cnfe.getStackTrace(), cnfe.getMessage(), user);
        } catch (FileNotFoundException fnfe) {
            logs.add(fnfe.getStackTrace(), fnfe.getMessage(), user);
        } catch (IOException ioe) {
            logs.add(ioe.getStackTrace(), ioe.getMessage(), user);
        }
    }
    
    public void writeBillsToFile() {
        try {
            bills.writeBillsToFile();
        } catch (NotSerializableException nse) {
            logs.add(nse.getStackTrace(), nse.getMessage(), user);
        } catch (IOException ioe) {
            logs.add(ioe.getStackTrace(), ioe.getMessage(), user);
        }
    }
    
    public void readBillsFromFile() {
        try {
            bills.readBillsFromFile();
        } catch (ClassNotFoundException cnfe) {
            logs.add(cnfe.getStackTrace(), cnfe.getMessage(), user);
        } catch (FileNotFoundException fnfe) {
            logs.add(fnfe.getStackTrace(), fnfe.getMessage(), user);
        } catch (IOException ioe) {
            logs.add(ioe.getStackTrace(), ioe.getMessage(), user);
        }
    }
    
    public void show(Stage stage) {
        viewController.show(stage);
    }
}
