/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package insurancecompany.view.register.insurances;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author André
 */
public class ClaimSearchView {
    
    // Decalaration of the Gridpane and Scene.
    private GridPane mainPane;
    private Scene scene;
    
    // Declaration of all Label messages.
    private Label claimIdLabel;
    private Label customerIdLabel;
    private Label insuranceIdLabel;
    private Label claimTypeLabel;
    private Label coverageLabel;
    private Label insuranceTypeLabel;
    
    // Declaration of all TextField and ComboBoxes
    private TextField claimIdField;
    private TextField customerIdField;
    private ComboBox claimTypeCombo;
    private ComboBox insuranceTypeCombo;
    
    // Decalaration all Buttons.
    private Button searchClaimIdButton;
    private Button searchCustomerIdButton;
    private Button searchInsuranceIdButton;
    
    private TableView table;
    
    // Constrcutor
    public ClaimSearchView() {
        
        // Sets up the mainPane and scene.

    }
    
    public void buildMainPane() {
        // Sets up the mainPane
        mainPane = new GridPane();
        mainPane.setAlignment(Pos.CENTER);
        mainPane.setHgap(10);
        mainPane.setVgap(10);
        mainPane.setStyle("-fx-background-color: #E7E7FF;");
        mainPane.getColumnConstraints().addAll(new ColumnConstraints(200), 
                new ColumnConstraints(200), new ColumnConstraints(200));
        // Initialize all ComboBoxes and TextFields.

        
        // Initialize all Label messages.

        
        // Initialize all Buttons.

        
        // Adds all elements to the mainPane.
    }
    
    public GridPane getMainPane() {
        return mainPane;
    }
    
    public void show(Stage stage) {
        stage.setTitle("Registrering av båtforsikring.");
        stage.setScene(scene);
        stage.show();
    }

    public void setCalculateButtonEventHandler(EventHandler<ActionEvent> value) {
        //calculateButton.setOnAction(value);
    }

    public void setRegisterButtonEventHandler(EventHandler<ActionEvent> value) {
        //registerButton.setOnAction(value);
    }
}