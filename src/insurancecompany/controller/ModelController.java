package insurancecompany.controller;

import insurancecompany.misc.carinfo.CarInfo;
import insurancecompany.misc.carinfo.CarInfoRegister;
import insurancecompany.misc.logs.Log;
import insurancecompany.model.bills.Bill;
import insurancecompany.model.claims.Claim;
import insurancecompany.model.datastructures.*;
import insurancecompany.model.insurances.Insurance;
import insurancecompany.model.people.Customer;
import insurancecompany.model.people.Employee;
import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Set;
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
 * 
 * @since 19.05.2015
 */
public class ModelController {
    // Declares models:
    private CarInfoRegister carInfoRegister;
    private EmployeeRegister employeeRegister;
    private CustomerRegister customerRegister;
    private InsuranceRegister insuranceRegister;
    private ClaimRegister claimRegister;
    private LogRegister logRegister;
    private BillRegister billRegister;
    
    private Set<Employee> employees;
    private Set<Customer> customers;
    private Set<Insurance> insurances;
    private Set<Claim> claims;
    private List<Log> logs;
    private Set<Bill> bills;
    
    // Declare datastructures:
    
    
    /**
     * Constructor that initializes all models, which are specified as 
     * parameters.
     * 
     * @param billRegister
     * @param claimRegister
     * @param customerRegister
     * @param employeeRegister
     * @param insuranceRegister
     * @param logRegister 
     */
    public ModelController(BillRegister billRegister, ClaimRegister claimRegister, 
            CustomerRegister customerRegister, EmployeeRegister employeeRegister, 
            InsuranceRegister insuranceRegister, LogRegister logRegister) {
        // Initializes models:
        this.billRegister = billRegister;
        this.claimRegister = claimRegister;
        this.customerRegister = customerRegister;
        this.employeeRegister = employeeRegister;
        this.insuranceRegister = insuranceRegister;
        this.logRegister = logRegister;
        
        //this.employeeRegister;
        this.customers = customerRegister.getCustomers();
        this.insurances = insuranceRegister.getInsurances();
        this.claims = claimRegister.getClaims();
        this.logs = logRegister.getLogs();
        this.bills = billRegister.getBills();
        
        // Calls methods:
        unmarshalCarInfoRegister();
    }
    
    /**
     * Returns all ansurances based on the specified personal number of the 
     * desired customer.
     * 
     * @param personalNumber The specified personal number of the customer.
     * @return A List containing all insuranceRegister of the customer with the 
     * specified personal number.
     */
    public List<Insurance> getAllInsurancesByPersonalNumber(String personalNumber) {
        List<Insurance> result = new ArrayList<>();
        // Finds the customer that matches the personal number:
        Customer c = customerRegister.findCustomerByPersonalNumber(personalNumber);
        // If the customer exists:
        if (c != null) {
            // Gets the customer id:
            int customerId = c.getId();
            // Uses this id, to get all insuranceRegister of the customer:
            result = insuranceRegister.getAllInsurancesByCustomerId(customerId);
        }
        // Returns the result:
        return result;
    }
    
    /**
     * Calculates whether a customer gets total customer discount or not 
     * based on the number of active insuranceRegister of this customer.
     * 
     * @param customerId The customer id of the customer in question.
     * @return True if the customer is a total customer, which means the 
     * customer has three or more insuranceRegister.
     */
    public boolean calculateTotalCustomer(int customerId) {
        Customer c = customerRegister.findCustomerById(customerId);
        // Counts the number of active insuranceRegister on this customer:
        int count = 0;
        List<Insurance> insuranceList = insuranceRegister.getAllActiveInsurancesByCustomerId(customerId);
        // For every insurance that is active, count goes up by one:
        for (Insurance a : insuranceList) {
            if (a.getActive()) {
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
    
    /**
     * Updates all insurance payments. Runs through all insurances and 
     * creates a bill for each insurance that has reached its nextPayedDate. 
     * Adds this bill to the bill register and updates the next pay date 
     * for that insurance.
     */
    public void updatePayments() {
        for (Insurance insurance : insurances) {
            if (insurance != null) {
                // Get the next pay date for each insurance:
                Calendar nextPayDate = insurance.getNextPayDate();
                if (nextPayDate != null) {
                    // If Current date has reached or passed the next payed date:
                    if (nextPayDate.before(Calendar.getInstance()) || nextPayDate.equals(Calendar.getInstance())) {

                        int customerId = insurance.getCustomerId();
                        int insuranceId = insurance.getInsuranceId();
                        double fee = insurance.getMonthlyPremium();
                        // Find the customer for the insurance:
                        Customer c = customerRegister.findCustomerById(customerId);
                        if (c != null) {
                            // Check if that customer is a totalCustomer:
                            boolean totalCustomer = c.isTotalCustomer();
                            if (totalCustomer) {
                                // If so, adjust fee accordingly:
                                fee = fee * (1 - Customer.TOTAL_CUSTOMER_DISCOUNT);
                            }
                            // Create a new bill assigned to the customer and insurance:
                            Bill bill = new Bill(fee, customerId, insuranceId);
                            // Add this bill to the bill register:
                            billRegister.addBill(bill);
                            // Update next pay date to 1 month forward:
                            nextPayDate.add(Calendar.MONTH, 1);
                            insurance.setNextPayDate(nextPayDate); // redundant
                        }
                    }
                }
            }
        }
    }
    
    /**
     * Updates all total customer statuses. Runs through all customers 
     * and counts each customers number of active insurances. If the number
     * is equal to that of a total customer its status is changed to true, 
     * if not it is changed to false.
     */
    public void updateTotalCustomers() {
        // Loop through all customers
        for (Customer customer : customers) {
            if (customer != null) {
                int customerId = customer.getId();
                // Get the number of active insurances for each:
                int numberOfInsurances = insuranceRegister.getNumberOfActiveInsurances(customerId);
                if (numberOfInsurances >= 3) {
                    // If this is at 3 or above change total customer to true:
                    customer.setTotalCustomer(true);
                } else {
                    // If not change total customer to false:
                    customer.setTotalCustomer(false);
                }
            }
        }
    }
    
    /**
     * Updates all unpaid bills. Checks for every bill that is not payed if it
     * has reached past its due date or dunning date. If that is the case, the
     * bills dunning fee is increased and a new dunning date is set.
     */
    public void updateUnpaidBills() {
        // Loop through all bills:
        for (Bill bill : bills) {
            // Check for each bill that is not payed:
            if (!bill.isPaid()) {
                Calendar dunningDate = bill.getDunningDate();
                Calendar dueDate = bill.getDueDate();
                // First check if it has allready reached dunning date status:
                // if not:
                if (dunningDate == null) {
                    // If current date has reached past duedate:
                    if (dueDate.before(Calendar.getInstance())) {
                        bill.increaseDunningFee(Bill.FIXED_DUNNING_CHARGE);
                        dunningDate = (Calendar) dueDate.clone();
                        // Set the date for the dunning date:
                        dunningDate.add(Calendar.DAY_OF_YEAR, Bill.FIXED_DUE_DAYS);
                        // Set this to the bill:
                        bill.setDunningDate(dunningDate);
                    }
                // If we have allready reached dunning date:    
                } else {
                    // Check if current date has reached past dunning date:
                    if (dunningDate.before(Calendar.getInstance())) {
                        bill.increaseDunningFee(Bill.FIXED_DUNNING_CHARGE);
                        Calendar newDunningDate = (Calendar) dunningDate.clone();
                        // Set the date for the new dunning date:
                        newDunningDate.add(Calendar.DAY_OF_YEAR, Bill.FIXED_DUE_DAYS);
                        // Set this to the bill:
                        bill.setDunningDate(newDunningDate);
                    }
                }
            }
        }
    }
    
    /**
     * Automatically pay all unpaid bills.
     */
    public void autoPayAllBills() {
        for (Bill bill : bills) {
            if (bills != null) {
                if (!bill.isPaid()) {
                    bill.setPaid(true);
                    Calendar date = Calendar.getInstance();
                    //date.add(Calendar.DAY_OF_MONTH, -10); // Used for testing
                    bill.setPayedDate(date);
                }
            }
        }
    }
    
    /**
     * Automatically pays all bills that has reached its due date or is past 
     * its due date.
     */
    public void autoPayAllBillsDue() {
        // Get the current day of the year:
        int dayOfYearNow = Calendar.getInstance().get(Calendar.DAY_OF_YEAR);
        // For all bills:
        for (Bill bill : bills) {
            // That are not null:
            if (bills != null) {
                // And is not paid:
                if (!bill.isPaid()) {
                    // Get the dunning date:
                    Calendar dunningDate = bill.getDunningDate();
                    // Check if it has been set or not:
                    if (dunningDate != null) {
                        // If it has get its day of year value:
                        int dayOfYearDunning = dunningDate.get(Calendar.DAY_OF_YEAR);
                        // And compare it to current day:
                        if (dayOfYearNow >= dayOfYearDunning) {
                            // Set bill to paid:
                            bill.setPaid(true);
                            bill.setPayedDate(Calendar.getInstance());
                        }
                    } else {
                        // If not, then due date is the one with prominence(is set by default)
                        int dayOfYearDue = bill.getDueDate().get(Calendar.DAY_OF_YEAR);
                        // Compare it to current day:
                        if (dayOfYearNow >= dayOfYearDue) {
                            // Set bill to paid:
                            bill.setPaid(true);
                            bill.setPayedDate(Calendar.getInstance());
                        }
                    }
                }
            }
        }
    }
    
  
    public double getIncomeAtDate(int year, int month, int day, String type, 
            int customerId, int insuranceId) {
        double result = 0;
        for (Bill bill : bills) {
            int insId = bill.getInsuranceId();
            Insurance insurance = insuranceRegister.getInsuranceById(insId);
            String typeInsurance = insurance.getType();
            if ((bill.getPayedDate().get(Calendar.YEAR) == year) 
                    && (month == 0 || bill.getPayedDate().get(Calendar.MONTH) == month)
                    && (day == 0 || bill.getPayedDate().get(Calendar.DAY_OF_MONTH) == day)
                    && (type == null || type.equals(typeInsurance))
                    && (customerId == 0 || bill.getCustomerId() == customerId)
                    && (insuranceId == 0 || bill.getInsuranceId() == insuranceId)) {
                result += bill.getFee() + bill.getDunningCharge();
            }
        }
        return result;
    }
    
    public Calendar getOldestPayDate(String type, int customerId, int insuranceId) {
        Calendar oldest = null;
        for (Bill bill : bills) {
            int insId = bill.getInsuranceId();
            Insurance insurance = insuranceRegister.getInsuranceById(insId);
            String typeInsurance = insurance.getType();
            if ((type == null || type.equals(typeInsurance))
                    && (customerId == 0 || bill.getCustomerId() == customerId)
                    && (insuranceId == 0 || bill.getInsuranceId() == insuranceId)) {
                if (oldest == null) {
                    oldest = (Calendar) bill.getPayedDate().clone();
                }
                if (bill.getPayedDate().before(oldest) || bill.getPayedDate().equals(oldest)) {
                    oldest = (Calendar) bill.getPayedDate().clone();
                }
            }
        }
        return oldest;
    }
    
    public Calendar getNewestPayDate(String type, int customerId, int insuranceId) {
        Calendar newest = null;
        for (Bill bill : bills) {
            int insId = bill.getInsuranceId();
            Insurance insurance = insuranceRegister.getInsuranceById(insId);
            String typeInsurance = insurance.getType();
            if ((type == null || type.equals(typeInsurance))
                    && (customerId == 0 || bill.getCustomerId() == customerId)
                    && (insuranceId == 0 || bill.getInsuranceId() == insuranceId)) {            
            
                if (newest == null) {
                    newest = (Calendar) bill.getPayedDate().clone();
                }
                if (bill.getPayedDate().after(newest) || bill.getPayedDate().equals(newest)) {
                    newest = (Calendar) bill.getPayedDate().clone();
                }
            }
        }
        return newest;
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
