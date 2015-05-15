package insurancecompany.view.search;

import insurancecompany.misc.DateUtility;
import insurancecompany.misc.InsuranceType;
import insurancecompany.model.insurances.Insurance;
import insurancecompany.model.people.Customer;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
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
 * This class creates the graphical user interface (GUI) for searching after 
 * customers. It creates a pane which is sent to the controller and thereafter
 * displayed.
 * 
 * <p> The pane consists of three parts; one to the left, one in the center and
 * one to the right.
 * 
 * <p> On the left the user inputs the search terms or criteria. These are
 * sent through get methods to the controller, which validates them and 
 * executes the search.
 * 
 * <p> In the center a table with the result is shown. The user can select one 
 * of these customers for further examination.
 * 
 * <p> On the right the customer selected in the table is shown.
 * 
 * @author Sindre
 */
public class CustomerSearchView {
    
    // Declaration of the main pane which is sent to the controller.
    private GridPane mainPane;
    
    // Declaration of the three parts of the main pane.
    private GridPane leftPane;
    private GridPane centerPane;
    private GridPane rightPane;
    
    // Declaration of all the nodes in the left part.
    private Button searchButton;
    private ComboBox<String> activeCombo;
    private ComboBox<Object> insuranceTypeCombo;
    private ComboBox<String> numberSelectCombo;
    private ComboBox<String> totalCustomerCombo;
    private Text idMessage;
    private TextField firstNameField;
    private TextField lastNameField;
    private TextField numberField;
    
    // Declaration of all the nodes in the center part.
    private Button selectButton;
    private TableView<Customer> customersTable;
    private TableColumn<Customer, String> firstNameColumn;
    private TableColumn<Customer, String> lastNameColumn;
    private TableColumn<Customer, String> activeColumn;
    private TableColumn<Customer, Integer> customerIdColumn;
    private Text selectMessage;
    
    // Declaration of all the nodes in the right part.
    private TextArea customerArea;
    
    /**
     * Default constructor. Initializes all field and sets up the view.
     */
    public CustomerSearchView() {
        
        // Initialization of the panes.
        mainPane = new GridPane();
        leftPane = new GridPane();
        centerPane = new GridPane();
        rightPane = new GridPane();
        
        // Adds the three parts to the main pane.
        mainPane.add(leftPane, 0, 0);
        mainPane.add(centerPane, 1, 0);
        mainPane.add(rightPane, 2, 0);
        
        // Sets the CSS ID to the panes.
        mainPane.setId("innerPane");
        leftPane.setId("innerPane");
        centerPane.setId("innerPane");
        rightPane.setId("innerPane");
        
        // Sets up column constraints. The width is in pixels.
        ColumnConstraints col1 = new ColumnConstraints(400);
        ColumnConstraints col2 = new ColumnConstraints(400);
        ColumnConstraints col3 = new ColumnConstraints(400);
        mainPane.getColumnConstraints().addAll(col1, col2, col3);
        
        // Initialization of all the nodes in the left part.
        searchButton = new Button("Søk");
        activeCombo = new ComboBox<>();
        populateActiveCombo();
        insuranceTypeCombo = new ComboBox<>();
        populateInsuranceTypeCombo();
        numberSelectCombo = new ComboBox<>();
        populateNumberSelectCombo();
        totalCustomerCombo = new ComboBox<>();
        populateTotalCustomerCombo();
        idMessage = new Text("");
        firstNameField = new TextField();
        lastNameField = new TextField();
        numberField = new TextField();

        // Initialization of all the nodes in the center part.
        selectButton = new Button("Velg kunde");
        customersTable = new TableView();
        firstNameColumn = new TableColumn<>("Fornavn");
        lastNameColumn = new TableColumn<>("Etternavn");
        customerIdColumn = new TableColumn<>("Kunde ID");
        activeColumn = new TableColumn<>("Aktiv");
        customersTable.getColumns().addAll(firstNameColumn, 
                lastNameColumn, customerIdColumn, activeColumn);
        customersTable.setColumnResizePolicy(
                TableView.CONSTRAINED_RESIZE_POLICY);
        selectMessage = new Text("");

        // Initialization of all the nodes in the right part.
        customerArea = new TextArea();
        
        // Initialization of all the texts and labels which are used in the 
        // view. Many of them aren't fields.
        Text customerIdTitle = new Text("Velg en kunde i registeret:");
        customerIdTitle.setId("textTitle");
        Text searchTermsTitle = new Text("Eller bruk søkebetingelser:");
        searchTermsTitle.setId("textTitle");
        Text customersTitle = new Text("Kunder:");
        customersTitle.setId("textTitle");
        Text customerTitle = new Text("Valgt kunde:");
        customerTitle.setId("textTitle");
        Label firstNameLabel = new Label("Fornavn:");
        Label lastNameLabel = new Label("Etternavn:");
        Label totalCustomerLabel = new Label("Kun totalkunder:");
        Label activeField = new Label("Kun aktive kunder:");
        Label insuranceTypeLabel = new Label("Eier forsikring:");
        
        // Adds the nodes to the left part.
        leftPane.add(customerIdTitle, 0, 0, 2, 1);
        leftPane.add(numberSelectCombo, 0, 1);
        leftPane.add(numberField, 1, 1);
        leftPane.add(new Text(""), 0, 2); // Creates a blank row.
        leftPane.add(searchTermsTitle, 0, 3, 2, 1);
        leftPane.add(firstNameLabel, 0, 4);
        leftPane.add(firstNameField, 1, 4);
        leftPane.add(lastNameLabel, 0, 5);
        leftPane.add(lastNameField, 1, 5);
        leftPane.add(totalCustomerLabel, 0, 6);
        leftPane.add(totalCustomerCombo, 1, 6);
        leftPane.add(activeField, 0, 7);
        leftPane.add(activeCombo, 1, 7);
        leftPane.add(insuranceTypeLabel, 0, 8);
        leftPane.add(insuranceTypeCombo, 1, 8);
        leftPane.add(searchButton, 1, 9);
        leftPane.add(idMessage, 0, 10, 2, 1);
        
        // Adds the nodes to the center part.
        centerPane.add(customersTitle, 0, 0);
        centerPane.add(customersTable, 0, 1);
        centerPane.add(selectButton, 0, 2);
        centerPane.add(selectMessage, 0, 3);
        
        // Adds the nodes to the right part.
        rightPane.add(customerTitle, 0, 0);
        rightPane.add(customerArea, 0, 1);
    }
    
    /** Sets the content of the ComboBox activeCombo. */
    private void populateActiveCombo() {
        ObservableList<String> obList = FXCollections.observableArrayList();  
        obList.addAll("Ja", "Nei");
        activeCombo.getItems().setAll(obList);
        activeCombo.setValue("Nei");
        activeCombo.setPrefWidth(150);
    }
    
    /** Sets the content of the ComboBox insuranceTypeCombo. */
    private void populateInsuranceTypeCombo() {
        ObservableList<InsuranceType> obList;
        obList = FXCollections.observableArrayList(InsuranceType.values());
        insuranceTypeCombo.getItems().add("");
        insuranceTypeCombo.getItems().addAll(obList);
        insuranceTypeCombo.setPrefWidth(150);
    }
    
    /** Sets the content of the ComboBox selectNumberCombo. */
    private void populateNumberSelectCombo() {
        ObservableList<String> obList = FXCollections.observableArrayList();  
        obList.addAll("Kundenummer", "Personnummer");
        numberSelectCombo.getItems().setAll(obList);
        numberSelectCombo.setValue("Kundenummer");
        numberSelectCombo.setPrefWidth(150);
    }
    
    /** Sets the content of the ComboBox totalCustomerCombo. */
    private void populateTotalCustomerCombo() {
        ObservableList<String> obList = FXCollections.observableArrayList();  
        obList.addAll("Ja", "Nei");
        totalCustomerCombo.getItems().setAll(obList);
        totalCustomerCombo.setValue("Nei");
        totalCustomerCombo.setPrefWidth(150);
    }
    
    /**
     * Sets the content of the TableView customersTable. The table will 
     * consist of the customers in the parameter list.
     * 
     * @param customers The list of customers which will be displayed in the 
     * table.
     */
    public void populateCustomersTable(List<Customer> customers) {
        // Creates an observable list from the recieved customer list.
        ObservableList<Customer> obList 
                = FXCollections.observableArrayList(customers);
        // Places this list in the customer table view.
        customersTable.setItems(obList);
        // Places a SimpleStringProperty version of the first name in the 
        // first column.
        firstNameColumn.setCellValueFactory(cellData -> {
            if ( cellData.getValue() != null) {
                return new SimpleStringProperty(cellData.getValue()
                        .getFirstName());
            } else {
                return new SimpleStringProperty("<no name>");
            }
        });
        // Places a SimpleStringProperty version of the last name in the 
        // second column.
        lastNameColumn.setCellValueFactory(cellData -> {
            if ( cellData.getValue() != null) {
                return new SimpleObjectProperty<>(cellData.getValue()
                        .getLastName());
            } else {
                return new SimpleObjectProperty(0);
            }
        });
        // Places a SimpleStringProperty version of the customer ID in the 
        // third column.
        customerIdColumn.setCellValueFactory(cellData -> {
            if ( cellData.getValue() != null) {
                return new SimpleObjectProperty<>(cellData.getValue().getId());
            } else {
                return new SimpleObjectProperty(0);
            }
        });
        // Places a SimpleStringProperty version of the activity status in the 
        // fourth column.
        activeColumn.setCellValueFactory(cellData -> {
            if (cellData.getValue() != null && cellData.getValue().isActive()) {
                return new SimpleObjectProperty<>("Ja");
            } else {
                return new SimpleObjectProperty("Nei");
            }
        });
    }
    
    /**
     * Sets the event handler for the searchButton.
     * 
     * @param value The event handler to set.
     */
    public void setSearchEventHandler(EventHandler<ActionEvent> value) {
        searchButton.setOnAction(value);
    }

    /**
     * Sets the event handler for the selectButton.
     * 
     * @param value The event handler to set.
     */
    public void setSelectEventHandler(EventHandler<ActionEvent> value) {
        selectButton.setOnAction(value);
    }
    
    /** @return The main pane of this class as a GridPane. */
    public GridPane getMainPane() {
        return mainPane;
    }
    
    /** @return True if activeCombo equals "Ja". */
    public boolean getActive() {
        return activeCombo.getValue().equals("Ja");
    }
    
    /** @return The number of this class as a String. */
    public String getNumber() {
        return numberField.getText();
    }
    
    /** @return True if customer ID is selected in numberSelectCombo. */
    public boolean isCustomerIdSelected() {
        return numberSelectCombo.getValue().equals("Kundenummer");
    }
    
    /** 
     * @return The selected value of customersTable as a Customer. Null if 
     * no insurance is selected.
     */
    public Customer getCustomersTableValue() {
        return customersTable.getSelectionModel() == null ? null : 
                customersTable.getSelectionModel().getSelectedItem();
    }
    
    /** 
     * @return The insurance type of this class as an InsuranceType. Null if
     * no insurance type is selected.
     */
    public InsuranceType getInsuranceType() {
        return String.class.isInstance(insuranceTypeCombo.getValue()) ?
                null : (InsuranceType) insuranceTypeCombo.getValue();
    }
    
    /** @param text The text to set in insuranceArea. */
    public void setInsuranceArea(String text) {
        customerArea.setText(text);
    }
    
    /** @param message The message to set. */
    public void setIdMessage(String message) {
        this.idMessage.setFill(Color.FIREBRICK);
        this.idMessage.setText(message);
    }
    
    /** @param message The message to set. */
    public void setSelectMessage(String message) {
        this.selectMessage.setFill(Color.FIREBRICK);
        this.selectMessage.setText(message);
    }
    
    /** Clears all messages. */
    public void clearMessages() {
        this.idMessage.setText("");
        this.selectMessage.setText("");
    }
}