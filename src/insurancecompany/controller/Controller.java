/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package insurancecompany.controller;

import insurancecompany.datastructures.*;
import insurancecompany.gui.*;
import insurancecompany.people.*;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 * @author André
 */
public class Controller {
    
    private Login login;
    private RegisterGui registerGui;
    private CustomerRegistration cReg;
    private GuiAdmin guiAdmin;
    private EmployeeRegister employees;
    
    public Controller()  {
        login = new Login();
        registerGui = new RegisterGui();
        cReg = new CustomerRegistration();
        guiAdmin = new GuiAdmin();
        login.getLoginBtn().setOnAction(this::loginBtnEventHandler);
        guiAdmin.getRegisterButton().setOnAction(this::registerPaneEventHandler);
        registerGui.getCustomerButton().setOnAction(this::registerCustomerPaneEventHandler);
    }
    
    public void initGui() {
        Pane pane = registerGui.getMainPane();
        guiAdmin.getMainPane().setCenter(pane);
        Pane pane2 = cReg.getMainPane();
        registerGui.getMainPane().setCenter(pane2);
    }
    
    public void show(Stage stage) {
        //login.show(stage);
        guiAdmin.show(stage);
    }
    
    private void registerPaneEventHandler(ActionEvent e) {
        Pane pane = registerGui.getMainPane();
        guiAdmin.getMainPane().setCenter(pane);
    }
    
    private void registerCustomerPaneEventHandler(ActionEvent e) {
        Pane pane = cReg.getMainPane();
        registerGui.getMainPane().setCenter(pane);
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
