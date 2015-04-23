/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package insurancecompany.gui;

import insurancecompany.datastructures.EmployeeRegister;
import insurancecompany.datastructures.ControlCenter;
import insurancecompany.people.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 *
 * @author André
 */
public class GuiLogin extends JFrame {
    
    public static final int ADMIN = 0;
    public static final int CASE_WORKER = 1;
    public static final int SERVICE_WORKER = 2;
    public static final int CUSTOMER = 3;
    
    EmployeeRegister employees;
    int typeOfPerson;
    
    public GuiLogin() {
        employees = null;
    }
    
    public void setup() {
        
    }
    
    public boolean validateEmployee() {
        int employeeId = 0;
        Employee employee = employees.getEmployeeById(employeeId);
        if (employee == null) {
            return false;
        }
        if (employee instanceof Admin) {
            typeOfPerson = ADMIN;
            return true;
        } else if (employee instanceof CaseWorker) {
            typeOfPerson = CASE_WORKER;
            return true;
        } else if (employee instanceof ServiceWorker) {
            typeOfPerson = SERVICE_WORKER;
            return true;
        }
        return false;
    }
}
