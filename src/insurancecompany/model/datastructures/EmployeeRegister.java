/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package insurancecompany.model.datastructures;


import insurancecompany.model.people.Employee;
import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author André
 */
public class EmployeeRegister {
    
    private static String employeesFilePath = "insurancecompany/resources/datastructures/employeeSet.dta";
    
    /** List of all the employees that works or has worked in this company. */
    private Set<Employee> employees;
    
    public EmployeeRegister() {
        employees = new HashSet<Employee>();
    }
    
    /**
     * Adds a new employee to this register if it does not already exist.
     * @param employee
     * @return 
     */
    public boolean addEmployee(Employee employee) {
        return employees.add(employee);
    }
    
        /**
     * Deletes an employee from the register.
     * @param employee employee to be removed from register
     * @return true if this list has changed as a result of the call
     */
    public boolean deleteEmployee(Employee employee) {
        return employees.removeIf(i -> i.equals(employee));
    }
    
    /**
     * Returns an employee that has a given employeeId
     * @param employeeId the employeeId of the employee to be returned
     * @return employee-object
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
     * Returns the number of employees in this register.
     * @return 
     */
    public int size() {
        return employees.size();
    }
    /**
     * Writes this registers set of employees to file.
     * @throws IOException 
     */
    public void writeEmployeesToFile() throws IOException{
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(employeesFilePath))) {
            oos.writeObject(employees);
        }
    }
    /**
     * Reads a set of employees from file and stores them in the set in this register.
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
