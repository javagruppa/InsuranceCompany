/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package insurancecompany.controller;

import insurancecompany.datastructures.EmployeeRegister;
import insurancecompany.gui.*;
import insurancecompany.people.*;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;

/**
 *
 * @author André
 */
public class Controller {
    
    private Login login;
    private EmployeeRegister employees;
    
    public Controller()  {
        login = new Login();
        login.getLoginBtn().setOnAction(this::loginBtnEventHandler);
    }
    
    public void show(Stage stage) {
        login.show(stage);
    }
    
    
    private void loginBtnEventHandler(ActionEvent e) {
        
        String userName = login.getUserTextField().getText();
        int employeeId = Integer.parseInt(userName);
        Employee employee = employees.getEmployeeById(employeeId);
        if (employee instanceof Admin) {
            System.out.println("Admin login");
        } else  if (employee instanceof CaseWorker) {
            System.out.println("case worker login");
        } else if (employee instanceof ServiceWorker) {
            System.out.println("Service work login");
        }
    }
}
