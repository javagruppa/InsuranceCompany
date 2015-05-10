/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package insurancecompany.view.search;

import com.sun.org.apache.bcel.internal.generic.AALOAD;
import insurancecompany.misc.ClaimType;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
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
    
    // Declaration of all TextField and ComboBoxes
    private TextField claimIdField;
    private TextField customerIdField;
    private TextField insuranceIdField;
    private ComboBox claimTypeCombo;
    
    // Decalaration all Buttons.
    private Button searchClaimIdButton;
    private Button searchCustomerIdButton;
    private Button searchInsuranceIdButton;
    private Button searchClaimTypeButton;
    
    private TableView table;
    
    private TextArea textArea;
    
    // Constrcutor
    public ClaimSearchView() {
        
        // Sets up the mainPane and scene.
        buildMainPane();

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
        
        
        // Initialize all Label messages:
        claimIdLabel = new Label("Skademeldingsnummer:");
        customerIdLabel = new Label("Kundenummer:");
        insuranceIdLabel = new Label("Forsikringsnummer:");
        claimTypeLabel = new Label("Skademelding type:");
        
        // Initialize all ComboBoxes and TextFields:
        claimIdField = new TextField();
        customerIdField = new TextField();
        insuranceIdField = new TextField();
        claimTypeCombo = createClaimTypeCombo();
        
        // Initialize all Buttons:
        searchClaimIdButton = new Button("Søk");
        searchCustomerIdButton = new Button("Søk");
        searchInsuranceIdButton = new Button("Søk");
        searchClaimTypeButton = new Button("Søk");
        
        // Initialize tableview:
        table = new TableView();
        
        
        // Adds all elements to the mainPane:
        mainPane.add(customerIdLabel, 0, 0);
        mainPane.add(customerIdField, 1, 0);
        mainPane.add(searchCustomerIdButton, 2, 0);
        
        mainPane.add(insuranceIdLabel, 0, 1);
        mainPane.add(insuranceIdField, 1, 1);
        mainPane.add(searchInsuranceIdButton, 2, 1);
        
        mainPane.add(claimTypeLabel, 0, 2);
        mainPane.add(claimTypeCombo, 1, 2);
        mainPane.add(searchClaimTypeButton, 2, 2);
    }
    
    public ComboBox createClaimTypeCombo() {
        ComboBox cb = new ComboBox();
        cb.getItems().addAll(ClaimType.values());
        return cb;
    }
    
    public Pane getMainPane() {
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