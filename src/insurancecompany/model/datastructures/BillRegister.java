package insurancecompany.model.datastructures;

import insurancecompany.model.bills.Bill;
import java.io.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Class BillRegister. Register of all bills and invoices towards the customers
 * @author André
 * 
 * @since 19.05.2015
 */
public class BillRegister {
    /** the file path of the file where the bills are saved */
    private static String billsFilePath = "src/insurancecompany/resources/datastructures/billSet.dta";
    /** the list of bills */
    private Set<Bill> bills;
    
    /**
     * Constructs a new BillRegister
     */
    public BillRegister() {
        bills = new HashSet<Bill>();
    }
    
    /**
     * Adds a new bill to this register if it does not already exist.
     * @param bill bill to be added to the register
     * @return the bill
     */
    public boolean addBill(Bill bill) {
        return bills.add(bill);
    }
    
    /**
     * Finds and returns a list of bills matching the owner of given customer id.
     * @param customerId customer id of a bill owner
     * @return a list of bills matching the owner of the customer id
     */
    public List<Bill> findBillsByCustomerId(int customerId) {
        // Creates an ArrayList which will be returned at the end of the method.
        List<Bill> result = new ArrayList<>();
        // Creates an iterator for the list:
        Iterator<Bill> iterator = bills.iterator();
        // Runs through the whole list:
        while(iterator.hasNext()) {
            Bill bill = iterator.next();
            if (bill.getCustomerId() == customerId) {
                result.add(bill);
            }
        }
        // Returns null if no matches are found:
        if (result.isEmpty()) {
            return null;
        } else {
            // Returns the newly created list otherwise:
            return result;
        }
    }
    
    /**
     * Finds bills in list by insuranceId
     * @param insuranceId the insuranceId to be used in search
     * @return the bills linked to the specified insuranceId
     */
    public List<Bill> findBillsByInsuranceId(int insuranceId) {
        // Creates an ArrayList which will be returned at the end of the method.
        List<Bill> result = new ArrayList<>();
        // Runs through the whole list and add each match to the result list:
        bills.stream().filter((bill) -> (bill.getInsuranceId() == insuranceId)).forEach((bill) -> {
            result.add(bill);
        });
        // Returns null if no matches are found:
        if (result.isEmpty()) {
            return null;
        } else {
            // Returns the newly created list otherwise:
            return result;
        }
    }
    
    /**
     * Finds and returns a bill based on bill id.
     * @param billId bill id of a bill in the list
     * @return the bill
     */
    public Bill getBill(int billId) {
        // Runs through the whole list:
        for (Bill bill : bills) {
            if (bill.getBillId()== billId) {
                return bill;
            }
        }
        return null; // If no bill is found
    }
    
    /**
     * Returns the number of bills in this register.
     * @return the size
     */
    public int size() {
        return bills.size();
    }
    
    /**
     * Returns all the bills between the two dates in the parameter.
     * @param from start date
     * @param to end date
     * @return the list of bills
     */
    public List<Bill> billsIssuedBetweenDates(Calendar from, Calendar to) {
        List<Bill> result = new ArrayList<>();
        // Checks for every bill in this register:
        bills.stream().forEach((bill) -> {
            // The date of when the bill is issued.
            Calendar date = bill.getIsuedDate();
            // Checks if the bills date is inbetween the 2 dates specified in the parameter.
            if (date.after(from) && date.before(to)) {
                // Adds this bill to the return set:
                result.add(bill);
            }
        });
        // Returns a list containing all bills between correct dates:
        return result;
    }
    
    /**
     * Returns a list of bills that are due in a certain period of time
     * @param from start of time period
     * @param to end of time period
     * @return a list of bills
     */
    public List<Bill> billsDueBetweenDates(Calendar from, Calendar to) {
        List<Bill> result = new ArrayList<>();
        // Checks for every bill in this register:
        for (Bill bill : bills) {
            // The date of when the bill is due:
            Calendar date = bill.getDueDate();
            // Checks if the bills date is inbetween the 2 dates specified in the parameter.
            if (date.after(from) && date.before(to)) {
                // Adds this bill to the return set:
                result.add(bill);
            }
        }
        // Returns a list containing all bills between correct dates:
        return result;
    }
    
    /**
     * Returns all bills that are due to be paid
     * @return a list of bills
     */
    public List<Bill> billsDue() {
        List<Bill> result = new ArrayList<Bill>();
        // Checks for every bill in this register:
        for (Bill bill : bills) {
            // The date of when the bill is due:
            Calendar date = bill.getDueDate();
            // The current date:
            Calendar current = Calendar.getInstance();
            // Checks if the current date has passed the due date and the bill is not payed:
            if (current.after(date) && !bill.isPaid()) {
                // Adds this bill to the return set:
                result.add(bill);
            }
        }
        // Returns a list containing all bills between correct dates:
        return result;
    }
    
    /**
     * Returns the total amount of payed income
     * @return the total paid amount
     */
    public double getTotalPayed() {
        double result = 0.0;
        for (Bill bill : bills) {
            if (bill != null && bill.isPaid()) {
                result += bill.getFee() + bill.getDunningCharge();
            }
        }
        return result;
    }
     
    /**
     * Writes this registers set of bills to file.
     * @throws IOException the exception to be thrown 
     */
    public void writeBillsToFile() throws IOException{
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(billsFilePath))) {
            oos.writeObject(bills);
        }
    }
    /**
     * Reads a set of bills from file and stores them in the set in this register.
     * @throws IOException the exception to be thrown
     * @throws ClassNotFoundException  the exception to be thrown
     */
    public void readBillsFromFile() throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream(billsFilePath))) {
            bills = (HashSet<Bill>) ois.readObject();        
        }
    }

    /**
     * @return the bills
     */
    public Set<Bill> getBills() {
        return bills;
    }
}
