package insurancecompany.model.datastructures;

import insurancecompany.model.people.Employee;
import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * This class creates the register for employees. It consists of a HashSet with 
 * all the employees as well as several methods to manipulate and get 
 * information from the register.
 * 
 * @author André
 * @author Sindre
 */
public class EmployeeRegister {
    /** The file path of the file where the employees are saved. */
    private static String employeesFilePath = "src/insurancecompany/resources/datastructures/employeeSet.dta";
    /** The set of employees. */
    private Set<Employee> employees;
    
    /**
     * Default constructor. Initializes the set.
     */
    public EmployeeRegister() {
        employees = new HashSet<>();
    }
    
    /**
     * Adds a new employee to this register if it does not already exist.
     * 
     * @param employee Employee to be added to the register.
     * @return True if this list changed as a result of the call.
     */
    public boolean addEmployee(Employee employee) {
        return employees.add(employee);
    }
    
    /**
     * Finds and returns an employee based on employee ID.
     * 
     * @param employeeId Employee ID of the employee that the method looks for.
     * @return The employee if it is found. Null otherwise.
     */
    public Employee getEmployeeById(int employeeId) {
        for (Employee employee : employees) {
            if (employee.getId() == employeeId) {
                return employee;
            }
        }
        return null;
    }
    
    /**
     * Finds and returns a employee ID based on personal number.
     * 
     * @param personalNumber Personal number of the employee ID that the method 
     * looks for.
     * @return The employee ID as an int if it is found. -1 otherwise.
     */
    public int getEmployeeIdByPersonalNumber(String personalNumber) {
        for (Employee employee : employees) {
            if (employee.getPersonalNumber().equals(personalNumber)) {
                return employee.getId();
            }
        }
        return -1;
    }
    
    /**
     * Finds and returns a list of employees based on the parameters.
     * 
     * @param firstName The first name of the employees that the method looks
     * for. It's "" if it's not part of the criteria.
     * @param lastName The last name of the employees that the method looks
     * for. It's "" if it's not part of the criteria.
     * @param type The employee type of the employees that the method looks for.
     * It's null if it's not part of the criteria.
     * @param active True if the method is only looking for active employees.
     * @return A list of all the employees that match the criteria.
     */
    public List<Employee> getEmployees(String firstName, String lastName, 
            String type, boolean active) {
        List<Employee> result = new ArrayList<>();
        for (Employee employee : employees) {
            if ((firstName.equals("") || 
                    firstName.equalsIgnoreCase(employee.getFirstName()))
                    && (lastName.equals("") || 
                            lastName.equalsIgnoreCase(employee.getLastName()))
                    && (type == null || type.equals(employee.getType()))
                    && (!active || employee.isActive())) {
                result.add(employee);
            }
        }
        return result;
    }

    /** @return A set of all the employees in the register. */
    public Set<Employee> getEmployees() {
        return employees;
    }
    
    /**
     * Writes this registers set of employees to file.
     * 
     * @throws IOException 
     */
    public void writeEmployeesToFile() throws IOException{
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(employeesFilePath))) {
            oos.writeObject(employees);
        }
    }
    
    /**
     * Reads a set of employees from file and stores them in the set in this 
     * register.
     * 
     * @throws IOException
     * @throws ClassNotFoundException 
     */
    public void readEmployeesFromFile() throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream(employeesFilePath))) {
            employees = (HashSet<Employee>) ois.readObject();        
        }
    }
}