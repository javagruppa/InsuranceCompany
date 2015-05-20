/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package insurancecompany.view.search;

import insurancecompany.misc.enums.ClaimType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author André
 */
public class SearchClaims {
    
    // Decalaration of the Gridpane and Scene.
    private GridPane gridPane;
    private BorderPane mainPane;
    private Scene scene;
    
    private DatePicker dateFromDatePicker;
    private DatePicker dateToDatePicker;
    
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
    public SearchClaims() {
        
        // Sets up the mainPane and scene.
        buildMainPane();

    }
    
    public void buildMainPane() {
        // Sets up the mainPane
        gridPane = new GridPane();
        mainPane = new BorderPane();
        mainPane.setId("innerPane");
                // Column constraints for the gridpane
        ColumnConstraints col0 = new ColumnConstraints(140); // gap
        ColumnConstraints col1 = new ColumnConstraints(200); // first field
        ColumnConstraints col2 = new ColumnConstraints(100); // gap
        ColumnConstraints col3 = new ColumnConstraints(100); // gap
        ColumnConstraints col4 = new ColumnConstraints(100); // second field
        ColumnConstraints col5 = new ColumnConstraints(10); // gap
        ColumnConstraints col6 = new ColumnConstraints(100); // third field
        ColumnConstraints col7 = new ColumnConstraints(10); // gap
        ColumnConstraints col8 = new ColumnConstraints(100); // fourth field
        ColumnConstraints col9 = new ColumnConstraints(5); // gap
        ColumnConstraints col10 = new ColumnConstraints(100); // fifth field
        // Add these constraints:
        gridPane.getColumnConstraints().addAll(col0, col1, col2, col3, col4, col5, col6, col7, col8, col9, col10);
        // Row constraints for the gridpane
        RowConstraints row0 = new RowConstraints(65); // gap
        RowConstraints row1 = new RowConstraints(50); // Skadedato, skadested
        RowConstraints row2 = new RowConstraints(17); // gap
        RowConstraints row3 = new RowConstraints(55); // Vitner
        RowConstraints row4 = new RowConstraints(27); // gap
        RowConstraints row5 = new RowConstraints(50); // Etternavn
        RowConstraints row6 = new RowConstraints(30); // Fornavn, fødselsnummer
        RowConstraints row7 = new RowConstraints(35); // Adresse
        RowConstraints row8 = new RowConstraints(38); // Postnr
        RowConstraints row9 = new RowConstraints(38); // Telefon
        RowConstraints row10 = new RowConstraints(36); // Epost
        RowConstraints row11 = new RowConstraints(42); // Regnr
        RowConstraints row12 = new RowConstraints(38); // Merke
        RowConstraints row13 = new RowConstraints(38); // empty
        // Add these constraints:
        gridPane.getRowConstraints().addAll(row0, row1, row2, row3, row4, row5, 
                row6, row7, row8, row9, row10, row11, row12, row13);
        
        Text searchClaimTitle = new Text("Søk etter skade:");
        
        // Initialize all Label messages:
        Label claimIdLabel = new Label("Skademeldingsnummer:");
        Label customerIdLabel = new Label("Kundenummer:");
        Label insuranceIdLabel = new Label("Forsikringsnummer:");
        Label claimTypeLabel = new Label("Skademelding type:");
        
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
       // gridPane.add()
        gridPane.add(customerIdLabel, 0, 0);
        gridPane.add(customerIdField, 1, 0);
        gridPane.add(searchCustomerIdButton, 2, 0);
        
        gridPane.add(insuranceIdLabel, 0, 1);
        gridPane.add(insuranceIdField, 1, 1);
        gridPane.add(searchInsuranceIdButton, 2, 1);
        
        gridPane.add(claimTypeLabel, 0, 2);
        gridPane.add(claimTypeCombo, 1, 2);
        gridPane.add(searchClaimTypeButton, 2, 2);
    }
    
    public ComboBox createClaimTypeCombo() {
        ComboBox cb = new ComboBox();
        ObservableList<ClaimType> obList;
        obList = FXCollections.observableArrayList(ClaimType.values()); 
        // Default value to all claims:
        final String allDamages = "Alle skademeldinger";
        cb.setValue(allDamages);
        // Set this String as a selectable option:
        cb.getItems().addAll(allDamages);
        cb.getItems().addAll(obList);
        
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

}