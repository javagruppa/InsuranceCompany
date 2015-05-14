/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package insurancecompany.view.search;

import insurancecompany.model.insurances.Insurance;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

/**
 *
 * @author Sindre
 */
public class InsuranceSearchView {
    
    // Panes:
    private GridPane mainPane;
    private GridPane leftPane;
    private GridPane centerPane;
    private GridPane rightPane;
    
    // Nodes in leftPane:
    private Button customerIdButton;
    private Button insuranceIdButton;
    private Button personalNumberButton;
    private ComboBox insuranceTypeCombo;
    private DatePicker fromDatePicker;
    private DatePicker toDatePicker;
    private TextField customerIdField;
    private TextField insuranceIdField;
    private TextField personalNumberField;
    
    // Nodes in centerPane:
    private Button selectButton;
    private TableView<Insurance> insurancesTable;
    private TableColumn<Insurance, String> insuranceTypeColumn;
    private TableColumn<Insurance, Integer> insuranceIdColumn;
    private TableColumn<Insurance, Integer> customerIdColumn;
    
    // Nodes in rightPane:
    private Text rightBottomTitle;
    private TextArea selectedInsuranceArea;
    private TextArea otherArea;
    
    public InsuranceSearchView() {
        // Sets up the paned:
        mainPane = new GridPane();
        leftPane = new GridPane();
        centerPane = new GridPane();
        rightPane = new GridPane();
        
        // Adds panes to mainPane:
        mainPane.add(leftPane, 0, 0);
        mainPane.add(centerPane, 1, 0);
        mainPane.add(rightPane, 2, 0);
        
        // Sets CSS ID:
        mainPane.setId("innerPane");
        leftPane.setId("innerPane");
        centerPane.setId("innerPane");
        rightPane.setId("innerPane");
        
        // Sets up column constraints. Width in pixels:
        ColumnConstraints col1 = new ColumnConstraints(325);
        ColumnConstraints col2 = new ColumnConstraints(325);
        ColumnConstraints col3 = new ColumnConstraints(325);
        
        // Adds these constraints:
        mainPane.getColumnConstraints().addAll(col1, col2, col3);
        
        // Initializes leftPane:
        customerIdButton = new Button("Søk");
        insuranceIdButton = new Button("Søk");
        personalNumberButton = new Button("Søk");
        insuranceTypeCombo = new ComboBox();
        fromDatePicker = new DatePicker();
        toDatePicker = new DatePicker();
        customerIdField = new TextField();
        insuranceIdField = new TextField();
        personalNumberField = new TextField();

        // Initializes centerPane:
        selectButton = new Button("Velg forsikring");
        insurancesTable = new TableView();
        insuranceTypeColumn = new TableColumn<>("Forsikring");
        customerIdColumn = new TableColumn<>("Forsikring ID");
        insuranceIdColumn = new TableColumn<>("Kunde ID");
        insurancesTable.getColumns().addAll(insuranceTypeColumn, 
                insuranceIdColumn, customerIdColumn);
        insurancesTable.setColumnResizePolicy(
                TableView.CONSTRAINED_RESIZE_POLICY);

        // Initializes rightPane:
        rightBottomTitle = new Text("");
        selectedInsuranceArea = new TextArea();
        otherArea = new TextArea();
        
        // Initializes Texts and Labels:
        Text leftUpperTitle = new Text("Velg først en kunde eller forsikring "
                + "i registeret:");
        leftUpperTitle.setId("textTitle");
        Text leftBottomTitle = new Text("Betingelser:");
        leftBottomTitle.setId("textTitle");
        Text centerTitle = new Text("Forsikringer:");
        centerTitle.setId("textTitle");
        Text rightUpperTitle = new Text("Forsikring");
        rightUpperTitle.setId("textTitle");
        rightBottomTitle.setId("textTitle");
        Label customerIdLabel = new Label("Kundenummer:");
        Label personalNumberLabel = new Label("Personnummer:");
        Label insuranceIdLabel = new Label("Forsikringssnummer:");
        Label insuranceTypeLabel = new Label("Type forsikring:");
        Label fromDateLabel = new Label("Fra dato:");
        Label toDateLabel = new Label("Til dato:");
        
        // Adds nodes to leftPane:
        leftPane.add(leftUpperTitle, 0, 0, 3, 1);
        leftPane.add(customerIdLabel, 0, 1);
        leftPane.add(customerIdField, 1, 1);
        leftPane.add(customerIdButton, 2, 1);
        leftPane.add(personalNumberLabel, 0, 2);
        leftPane.add(personalNumberField, 1, 2);
        leftPane.add(personalNumberButton, 2, 2);
        leftPane.add(insuranceIdLabel, 0, 3);
        leftPane.add(insuranceIdField, 1, 3);
        leftPane.add(insuranceIdButton, 2, 3);
        
        leftPane.add(leftBottomTitle, 0, 4);
        leftPane.add(insuranceTypeLabel, 0, 5);
        leftPane.add(insuranceTypeCombo, 1, 5);
        leftPane.add(fromDateLabel, 0, 6);
        leftPane.add(fromDatePicker, 1, 6, 2, 1);
        leftPane.add(toDateLabel, 0, 7);
        leftPane.add(toDatePicker, 1, 7, 2, 1);
        
        // Adds nodes to centerPane:
        centerPane.add(centerTitle, 0, 0);
        centerPane.add(insurancesTable, 0, 1);
        centerPane.add(selectButton, 0, 2);
        
        // Adds nodes to rightPane:
        rightPane.add(rightUpperTitle, 0, 0);
        rightPane.add(selectedInsuranceArea, 0, 1);
        rightPane.add(rightBottomTitle, 0, 2);
        rightPane.add(otherArea, 0, 3);
    }
    
    /** @return The main pane of this class. */
    public GridPane getMainPane() {
        return mainPane;
    }
}
