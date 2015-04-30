/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package insurancecompany.gui;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 *
 * @author André
 */
public class CustomerRegistration {
    
    private Scene scene;
    
    private TextField nameField;
    private TextField nameField;
    private TextField nameField;
    
    protected int personalNumber;
    protected String firstname;
    protected String lastname;
    protected Address address;
    protected String email;
    protected int phone;
    
    public CustomerRegistration() {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        
        scene = new Scene(grid, 300, 275);
        
        Label customerL = new Label("Kunde");
        customerField = new TextField();
    }
    
}
