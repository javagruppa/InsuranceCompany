package insurancecompany.view.register.insurances;

import insurancecompany.misc.coverages.TravelInsuranceCoverage;
import insurancecompany.model.insurances.Insurance;
import java.util.List;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

/**
 * This class creates the graphical user interface (GUI) for registration of 
 * travel insurances. It creates a pane which is sent to the controller and 
 * thereafter displayed.
 * 
 * <p> The pane consists of two parts; one to the left and one to the right.
 * 
 * <p> On the left the user chooses the customer. It also shows information
 * about the customer and its existing insurances.
 * 
 * <p> On the right the user inputs the information about the insurance. These 
 * are sent through get methods to the controller, which validates them and 
 * executes the registration.
 * 
 * @author Sindre
 */
public class RegisterTravelInsurance {
    
    // Declaration of the main pane which is sent to the controller.
    private GridPane mainPane;
    
    // Declaration of all the nodes in the left part.
    private Button searchCustomerIdButton;
    private Button searchPersonalNumberButton;
    private Text customerSelectedMessage;
    private TextArea customerArea;
    private TextField customerIdField;
    private TextField personalNumberField;
    private TableView<Insurance> insurancesTable;
    private TableColumn<Insurance, String> insuranceTypeColumn;
    private TableColumn<Insurance, String> insuranceCoverageColum;
    private TableColumn<Insurance, Integer> insuranceIdColumn;
    private int selectedCustomerId;
    
    // Declaration of all the nodes in the right part.
    private Button registerButton;
    private ComboBox<TravelInsuranceCoverage> coverageCombo;
    private ComboBox<String> excessCombo;
    private Text coverageMessage;
    private Text excessMessage;
    private Text registerButtonMessage;
    
    public RegisterTravelInsurance() {
        
        // Initialization of the pane.
        mainPane = new GridPane();
        
        // Sets the CSS ID to the pane.
        mainPane.setId("innerPane");
        
        // Sets up column constraints. The width is in pixels.
        ColumnConstraints col1 = new ColumnConstraints(120);
        ColumnConstraints col2 = new ColumnConstraints(100);
        ColumnConstraints col3 = new ColumnConstraints(40);
        ColumnConstraints col4 = new ColumnConstraints(50);
        ColumnConstraints col5 = new ColumnConstraints(150);
        ColumnConstraints col6 = new ColumnConstraints(150);
        ColumnConstraints col7 = new ColumnConstraints(150);
        mainPane.getColumnConstraints().addAll(col1, col2, col3, col4, col5, col6, col7);
        
        // Initialization of all the nodes in the left part.
        customerIdField = new TextField();
        personalNumberField = new TextField();
        customerArea = new TextArea();
        customerArea.setEditable(false);
        customerArea.setPrefColumnCount(2);
        customerArea.setPrefRowCount(4);
        insurancesTable = new TableView();
        insurancesTable.setPrefHeight(150);
        insuranceTypeColumn = new TableColumn<>("Forsikring");
        insuranceCoverageColum = new TableColumn<>("Dekning");
        insuranceIdColumn = new TableColumn<>("Forsikringsid");
        insurancesTable.getColumns().addAll(insuranceTypeColumn, insuranceCoverageColum, insuranceIdColumn);
        insurancesTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        searchCustomerIdButton = new Button("Søk");
        searchPersonalNumberButton = new Button("Søk");
        customerSelectedMessage = new Text();
        
        // Initialization of all the nodes in the right part.
        coverageCombo = new ComboBox<>();
        populateCoverageCombo();
        excessCombo = new ComboBox<>();
        populateExcessCombo();
        coverageMessage = new Text();
        excessMessage = new Text();
        registerButtonMessage = new Text();
        registerButton = new Button("Registrer");
        
        // Declaration and initialization of the texts and labels which are 
        // used in the view and are not fields.
        Text insurancesTitle = new Text("Eksisterende forsikringer til denne kunden:");
        insurancesTitle.setId("textTitle");
        Text resultTitle = new Text("Valgt kunde:");
        resultTitle.setId("textTitle");
        Text selectCustomerTitle = new Text("Velg først en kunde i registeret:");
        selectCustomerTitle.setId("textTitle");
        Text insuranceOptionsTitle = new Text("Betingelser:");
        insuranceOptionsTitle.setId("textTitle");
        Label customerIdLabel = new Label("Kundenummer:");
        Label personalNumberLabel = new Label("Personnummer:");
        Label coverageLabel = new Label("Dekning:");
        Label excessLabel = new Label("Egenandel:");
        
        // Adds the nodes to the left part.
        mainPane.add(selectCustomerTitle, 0, 0);
        mainPane.add(customerIdLabel, 0, 1);
        mainPane.add(customerIdField, 1, 1);
        mainPane.add(searchCustomerIdButton, 2, 1);
        mainPane.add(personalNumberLabel, 0, 2);
        mainPane.add(personalNumberField, 1, 2);
        mainPane.add(searchPersonalNumberButton, 2, 2);
        mainPane.add(resultTitle, 0, 3);
        mainPane.add(customerArea, 0, 4, 3, 5);
        mainPane.add(customerSelectedMessage, 0, 9);
        mainPane.add(insurancesTitle, 0, 10);
        mainPane.add(insurancesTable, 0, 11, 3, 5);
        
        // Adds the nodes to the right part.
        mainPane.add(insuranceOptionsTitle, 4, 0);
        mainPane.add(coverageLabel, 4, 1);
        mainPane.add(coverageCombo, 5, 1);
        mainPane.add(coverageMessage, 6, 1);
        mainPane.add(excessLabel, 4, 2);
        mainPane.add(excessCombo, 5, 2);
        mainPane.add(excessMessage, 6, 2);
        mainPane.add(registerButton, 5, 4);
        mainPane.add(registerButtonMessage, 5, 5);
    }
    
    /** Sets the content of the ComboBox coverageCombo. */
    private void populateCoverageCombo() {
        ObservableList<TravelInsuranceCoverage> obList;
        obList = FXCollections.observableArrayList(TravelInsuranceCoverage.values()); 
        coverageCombo.getItems().setAll(obList);
        coverageCombo.setPrefWidth(150);
    }
    
    /** Sets the content of the ComboBox excessCombo. */
    private void populateExcessCombo() {
        ObservableList<String> excess = FXCollections.observableArrayList();
        excess.addAll("0", "500", "1000", "1500", "2000", "2500", 
                "3000", "3500", "4000", "4500", "5000");
        excessCombo.getItems().setAll(excess);
        excessCombo.setPrefWidth(150);
    }
    
    /**
     * Sets the content of the TableView insurancesTable. The table will 
     * consist of the insurances in the parameter list.
     * 
     * @param insurances The list of insurances which will be displayed in the 
     * table.
     */
    public void populateInsurancesTable(List<Insurance> insurances) {
        ObservableList<Insurance> obList = FXCollections.observableArrayList(insurances);
        insurancesTable.setItems(obList);
        
        insuranceTypeColumn.setCellValueFactory((cellData) -> {
                if ( cellData.getValue() != null) {
                    return new SimpleStringProperty(cellData.getValue().getName());
                } else {
                    return new SimpleStringProperty("<no name>");
                }
        });
        insuranceCoverageColum.setCellValueFactory((cellData) -> {
                if ( cellData.getValue() != null) {
                    return new SimpleObjectProperty<>(cellData.getValue().getCoverage().toString());
                } else {
                    return new SimpleObjectProperty(0);
                }
        });
        insuranceIdColumn.setCellValueFactory((cellData) -> {
                if ( cellData.getValue() != null) {
                    return new SimpleObjectProperty<>(cellData.getValue().getInsuranceId());
                } else {
                    return new SimpleObjectProperty(0);
                }
        });   
    }
    
    /**
     * Sets the event handler for the registerButton.
     * 
     * @param value The event handler to set.
     */
    public void setRegisterEventHandler(EventHandler<ActionEvent> value) {
        registerButton.setOnAction(value);
    }
    
    /**
     * Sets the event handler for the searchCustomerIdButton.
     * 
     * @param value The event handler to set.
     */
    public void setSearchCustomerIdEventHandler(EventHandler<ActionEvent> value) {
        searchCustomerIdButton.setOnAction(value);
    }

    /**
     * Sets the event handler for the searchPersonalNumberButton.
     * 
     * @param value The event handler to set.
     */
    public void setSearchPersonalNumberEventHandler(EventHandler<ActionEvent> value) {
        searchPersonalNumberButton.setOnAction(value);
    }

    /** @return The main pane of this class. */
    public GridPane getMainPane() {
        return mainPane;
    }

    /** @return The value of coverageCombo. */
    public TravelInsuranceCoverage getCoverage() {
        if (coverageCombo.getValue() instanceof TravelInsuranceCoverage) {
            // Casts the ComboBox value to TravelInsuranceCoverage and returns this value.
            TravelInsuranceCoverage coverage = (TravelInsuranceCoverage) coverageCombo.getValue();
            return coverage;
            // If for instance no value is selected, the value will not equal a TravelInsuranceCoverage, in this case return null.
        } else return null; 
    }

    /** @return The value of customerIdField. */
    public String getCustomerId() {
        return customerIdField.getText();
    }

    /** @return The value of excessCombo. */
    public String getExcess() {
        return excessCombo.getValue() == null ? "" : excessCombo.getValue();
    }
    
    /** @return The value of personalNumberField. */
    public String getPersonalNumber() {
        return personalNumberField.getText();
    }

    /** @return The value of selectedCustomerId. */
    public int getSelectedCustomerId() {
        return selectedCustomerId;
    }
    
    /** @param selectedCustomerId The selectedCustomerId to set. */
    public void setSelectedCustomerId(int selectedCustomerId) {
        this.selectedCustomerId = selectedCustomerId;
    }
    
    /** @param message The message to set. */
    public void setCoverageMessage(String message) {
        this.coverageMessage.setFill(Color.FIREBRICK);
        this.coverageMessage.setText(message);
    }
    
    /** @param text The text to set. */
    public void setCustomerArea(String text) {
        this.customerArea.setText(text);
    }

    /** @param message The message to set. */
    public void setCustomerSelectedMessage(String message) {
        this.customerSelectedMessage.setFill(Color.FIREBRICK);
        this.customerSelectedMessage.setText(message);
    }

    /** @param message The message to set. */
    public void setExcessMessage(String message) {
        this.excessMessage.setFill(Color.FIREBRICK);
        this.excessMessage.setText(message);
    }

    /** @param message The message to set. */
    public void setRegisterButtonMessage(String message) {
        this.registerButtonMessage.setText(message);
    }
    
    /** Clears all messages. */
    public void clearMessages() {
        coverageMessage.setText("");
        customerSelectedMessage.setText("");
        excessMessage.setText("");
        registerButtonMessage.setText("");
    }
}