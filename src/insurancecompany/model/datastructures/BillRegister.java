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
     * @return 
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
     * @return 
     */
    public Bill getBill(int billId) {
        // Runs through the whole list:
        for (Bill bill : bills) {
            if (bill.getBillId()== billId) {
                return bill;
            }
        }
        return null;
    }
    
    /**
     * Returns the number of bills in this register.
     * @return 
     */
    public int size() {
        return bills.size();
    }
    
    /**
     * Returns all the bills between the two dates in the parameter.
     * @param from
     * @param to
     * @return 
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
    
    // TODO: In Model Controller, use setDunningDate & setDunningCharge on this Set
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
     * @throws IOException 
     */
    public void writeBillsToFile() throws IOException{
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(billsFilePath))) {
            oos.writeObject(bills);
        }
    }
    /**
     * Reads a set of bills from file and stores them in the set in this register.
     * @throws IOException
     * @throws ClassNotFoundException 
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
