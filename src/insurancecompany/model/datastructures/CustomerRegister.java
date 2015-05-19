package insurancecompany.model.datastructures;

import insurancecompany.model.people.Customer;
import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * This class creates the register for customers. It consists of a HashSet with 
 * all the customers as well as several methods to manipulate and get 
 * information from the register.
 * 
 * @author André
 * @author Sindre
 */
public class CustomerRegister {
    /** The file path of the file where the customers are saved. */
    private static String customersFilePath = "src/insurancecompany/resources/datastructures/customerSet.dta";
    /** The set of customers. */
    private Set<Customer> customers;
    
    /**
     * Default constructor. Initializes the set.
     */
    public CustomerRegister() {
        customers = new HashSet<>();
    }
    
    /**
     * Adds a new customer to this register if it does not already exist.
     * 
     * @param customer Customer to be added to the register.
     * @return True if this list changed as a result of the call.
     */
    public boolean addCustomer(Customer customer) {
        return customers.add(customer);
    }
    
    /**
     * Finds and returns a customer based on customer ID.
     * 
     * @param customerId Customer ID of the customer that the method looks for.
     * @return The customer if it is found. Null otherwise.
     */
    public Customer findCustomerById(int customerId) {
        for (Customer customer : customers) {
            if (customer.getId() == customerId) {
                return customer;
            }
        }
        return null;
    }
    
    /**
     * Finds and returns a customer based on personal number.
     * 
     * @param personalNumber Personal number of the customer that the method 
     * looks for.
     * @return The customer if it is found. Null otherwise.
     */
    public Customer findCustomerByPersonalNumber(String personalNumber) {
        for (Customer customer : customers) {
            if (customer.getPersonalNumber().equals(personalNumber)) {
                return customer;
            }
        }
        return null;
    }
    
    /**
     * Finds and returns a customer ID based on personal number.
     * 
     * @param personalNumber Personal number of the customer ID that the method 
     * looks for.
     * @return The customer ID as an int if it is found. -1 otherwise.
     */
    public int findCustomerIdByPersonalNumber(String personalNumber) {
        for (Customer customer : customers) {
            if (customer.getPersonalNumber().equals(personalNumber)) {
                return customer.getId();
            }
        }
        return -1;
    }
    
    /**
     * Finds and returns a list of customers based on the parameters.
     * 
     * @param firstName The first name of the customers that the method looks
     * for. It's "" if it's not part of the criteria.
     * @param lastName The last name of the customers that the method looks
     * for. It's "" if it's not part of the criteria.
     * @param total True if the method is only looking for total customers.
     * @param active True if the method is only looking for active customers.
     * @return A list of all the customers that match the criteria.
     */
    public List<Customer> getCustomers(String firstName, String lastName, 
            boolean total, boolean active) {
        List<Customer> result = new ArrayList<>();
        for (Customer customer : customers) {
            if ((firstName.equals("") || 
                    firstName.equalsIgnoreCase(customer.getFirstName()))
                    && ((lastName.equals("")) ||
                            lastName.equalsIgnoreCase(customer.getLastName()))
                    && (!total || customer.isTotalCustomer())
                    && (!active || customer.isActive())) {
                result.add(customer);
            }
        }
        return result;
    }  

    /** @return A set of all the customers in the register. */
    public Set<Customer> getCustomers() {
        return customers;
    }
    
    /**
     * Writes this registers set of customers to file.
     * 
     * @throws IOException 
     */
    public void writeCustomersToFile() throws IOException{
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(customersFilePath))) {
            oos.writeObject(customers);
        }
    }
    /**
     * Reads a set of customers from file and stores them in the set in this 
     * register.
     * 
     * @throws IOException
     * @throws ClassNotFoundException 
     */
    public void readCustomersFromFile() throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream(customersFilePath))) {
            customers = (HashSet<Customer>) ois.readObject();        
        }
    }
}