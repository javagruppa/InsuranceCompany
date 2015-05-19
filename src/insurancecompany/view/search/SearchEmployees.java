package insurancecompany.view.search;

import insurancecompany.misc.EmployeeType;
import insurancecompany.model.people.Employee;
import java.util.ArrayList;
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
 * This class creates the graphical user interface (GUI) for searching after 
 * employees. It creates a pane which is sent to the controller and thereafter
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
 * of these employees for further examination.
 * 
 * <p> On the right the employee selected in the table is shown.
 * 
 * @author Sindre
 */
public class SearchEmployees {
    
    // Declaration of the main pane which is sent to the controller.
    private GridPane mainPane;
    
    // Declaration of the three parts of the main pane.
    private GridPane leftPane;
    private GridPane centerPane;
    private GridPane rightPane;
    
    // Declaration of all the nodes in the left part.
    private Button searchIdButton;
    private Button searchButton;
    private ComboBox<String> activeCombo;
    private ComboBox<Object> employeeTypeCombo;
    private ComboBox<String> numberSelectCombo;
    private Text searchIdMessage;
    private Text searchMessage;
    private TextField firstNameField;
    private TextField lastNameField;
    private TextField numberField;
    
    // Declaration of all the nodes in the center part.
    private Button deactivateButton;
    private Button selectButton;
    private TableView<Employee> employeesTable;
    private TableColumn<Employee, String> firstNameColumn;
    private TableColumn<Employee, String> lastNameColumn;
    private TableColumn<Employee, Integer> employeeIdColumn;
    private TableColumn<Employee, String> employeeTypeColumn;
    private Text numberOfResults;
    
    // Declaration of all the nodes in the right part.
    private TextArea employeeArea;
    private Text deactivateMessage;
    private Text selectMessage;
    
    /**
     * Default constructor. Initializes all field and sets up the view.
     */
    public SearchEmployees() {
        
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
        searchIdButton = new Button("Søk");
        searchButton = new Button("Søk");
        activeCombo = new ComboBox<>();
        populateActiveCombo();
        employeeTypeCombo = new ComboBox<>();
        populateEmployeeTypeCombo();
        numberSelectCombo = new ComboBox<>();
        populateNumberSelectCombo();
        searchIdMessage = new Text("");
        searchMessage = new Text("");
        firstNameField = new TextField();
        lastNameField = new TextField();
        numberField = new TextField();

        // Initialization of all the nodes in the center part.
        selectButton = new Button("Velg ansatt");
        employeesTable = new TableView();
        employeesTable.setPrefWidth(400);
        firstNameColumn = new TableColumn<>("Fornavn");
        lastNameColumn = new TableColumn<>("Etternavn");
        employeeIdColumn = new TableColumn<>("Ansatt ID");
        employeeTypeColumn = new TableColumn<>("Jobbtittel");
        employeesTable.getColumns().addAll(firstNameColumn, 
                lastNameColumn, employeeIdColumn, employeeTypeColumn);
        employeesTable.setColumnResizePolicy(
                TableView.CONSTRAINED_RESIZE_POLICY);
        numberOfResults = new Text ("Ingen resultater.");
        selectMessage = new Text("");

        // Initialization of all the nodes in the right part.
        deactivateButton = new Button("Gjør den ansatte aktiv/inaktiv");
        deactivateMessage = new Text("");
        employeeArea = new TextArea();
        employeeArea.setPrefHeight(400);
        
        // Declaration and initialization of the texts and labels which are 
        // used in the view and are not fields.
        Text employeeIdTitle = new Text("Velg en ansatt i registeret:");
        employeeIdTitle.setId("textTitle");
        Text searchTermsTitle = new Text("Eller bruk søkebetingelser:");
        searchTermsTitle.setId("textTitle");
        Text employeesTitle = new Text("Ansatte:");
        employeesTitle.setId("textTitle");
        Text employeeTitle = new Text("Valgt ansatt:");
        employeeTitle.setId("textTitle");
        Label firstNameLabel = new Label("Fornavn:");
        Label lastNameLabel = new Label("Etternavn:");
        Label employeeTypeLabel = new Label("Jobbtittel:");
        Label activeLabel = new Label("Kun nåværende ansatte:");
        
        // Adds the nodes to the left part.
        leftPane.add(employeeIdTitle, 0, 0, 2, 1);
        leftPane.add(numberSelectCombo, 0, 1);
        leftPane.add(numberField, 1, 1);
        leftPane.add(searchIdButton, 1, 2);
        leftPane.add(searchIdMessage, 0, 3);
        leftPane.add(searchTermsTitle, 0, 4, 2, 1);
        leftPane.add(firstNameLabel, 0, 5);
        leftPane.add(firstNameField, 1, 5);
        leftPane.add(lastNameLabel, 0, 6);
        leftPane.add(lastNameField, 1, 6);
        leftPane.add(employeeTypeLabel, 0, 7);
        leftPane.add(employeeTypeCombo, 1, 7);
        leftPane.add(activeLabel, 0, 8);
        leftPane.add(activeCombo, 1, 8);
        leftPane.add(searchButton, 1, 9);
        leftPane.add(searchMessage, 0, 10, 2, 1);
        
        // Adds the nodes to the center part.
        centerPane.add(employeesTitle, 0, 0);
        centerPane.add(employeesTable, 0, 1);
        centerPane.add(numberOfResults, 0, 2);
        centerPane.add(selectButton, 0, 3);
        centerPane.add(selectMessage, 0, 4);
        
        // Adds the nodes to the right part.
        rightPane.add(employeeTitle, 0, 0);
        rightPane.add(employeeArea, 0, 1);
        rightPane.add(deactivateButton, 0, 2);
        rightPane.add(deactivateMessage, 0, 3);
    }
    
    /** Sets the content of the ComboBox activeCombo. */
    private void populateActiveCombo() {
        ObservableList<String> obList = FXCollections.observableArrayList();  
        obList.addAll("Ja", "Nei");
        activeCombo.getItems().setAll(obList);
        activeCombo.setValue("Nei");
        activeCombo.setPrefWidth(150);
    }
    
    /** Sets the content of the ComboBox employeeTypeCombo. */
    private void populateEmployeeTypeCombo() {
        ObservableList<EmployeeType> obList;
        obList = FXCollections.observableArrayList(EmployeeType.values());
        employeeTypeCombo.getItems().add("");
        employeeTypeCombo.getItems().addAll(obList);
        employeeTypeCombo.setPrefWidth(150);
    }
    
    /** Sets the content of the ComboBox numberSelectCombo. */
    private void populateNumberSelectCombo() {
        ObservableList<String> obList = FXCollections.observableArrayList();  
        obList.addAll("Ansattnummer", "Personnummer");
        numberSelectCombo.getItems().setAll(obList);
        numberSelectCombo.setValue("Ansattnummer");
        numberSelectCombo.setPrefWidth(150);
    }
    
    /**
     * Sets the content of the TableView employeesTable. The table will 
     * consist of the employees in the parameter list.
     * 
     * @param employees The list of employees which will be displayed in the 
     * table.
     */
    public void populateEmployeesTable(List<Employee> employees) {
        // Creates an observable list from the recieved employee list.
        ObservableList<Employee> obList 
                = FXCollections.observableArrayList(employees);
        // Places this list in the employee table view.
        employeesTable.setItems(obList);
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
                return new SimpleObjectProperty("<no name>");
            }
        });
        // Places a SimpleStringProperty version of the employee ID in the 
        // third column.
        employeeIdColumn.setCellValueFactory(cellData -> {
            if ( cellData.getValue() != null) {
                return new SimpleObjectProperty<>(cellData.getValue().getId());
            } else {
                return new SimpleObjectProperty(0);
            }
        });
        // Places a SimpleStringProperty version of the employee type in the 
        // fourth column.
        employeeTypeColumn.setCellValueFactory(cellData -> {
            if ( cellData.getValue() != null) {
                return new SimpleStringProperty(cellData.getValue().getType());
            } else {
                return new SimpleStringProperty("<no name>");
            }
        });
    }
    
    /**
     * Sets the event handler for the deactivateButton.
     * 
     * @param value The event handler to set.
     */
    public void setDeactivateEventHandler(EventHandler<ActionEvent> value) {
        deactivateButton.setOnAction(value);
    }
    
    /**
     * Sets the event handler for the searchIdButton.
     * 
     * @param value The event handler to set.
     */
    public void setSearchIdEventHandler(EventHandler<ActionEvent> value) {
        searchIdButton.setOnAction(value);
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
    
    /** @return True if employee ID is selected in numberSelectCombo. */
    public boolean isEmployeeIdSelected() {
        return numberSelectCombo.getValue().equals("Ansattnummer");
    }
    
    /** 
     * @return The selected value of employeesTable as an Employee. Null if 
     * no employee is selected.
     */
    public Employee getEmployeesTableValue() {
        return employeesTable.getSelectionModel() == null ? null : 
                employeesTable.getSelectionModel().getSelectedItem();
    }
    
    /** @return The first name of this class as a String. */
    public String getFirstName() {
        return firstNameField.getText();
    }
    
    /** @return The last name of this class as a String. */
    public String getLastName() {
        return lastNameField.getText();
    }
    
    /** 
     * @return The employee type of this class as a String. Null if
     * no employee type is selected.
     */
    public String getEmployeeType() {
        return (EmployeeType.class.isInstance(employeeTypeCombo.getValue())) ?
            ((EmployeeType)employeeTypeCombo.getValue()).toString() : null;
    }
    
    /** @param text The text to set in employeeArea. */
    public void setEmployeeArea(String text) {
        employeeArea.setText(text);
    }
    
    /** @param number The number of results. */
    public void setNumberOfResults(int number) {
        if (number == 0) {
            numberOfResults.setText("Ingen resultater.");
        } else if (number == 1) {
            numberOfResults.setText("1 resultat.");
        } else {
            numberOfResults.setText(number + " resultater.");
        }
    }
    
    /** Clears the area, the table and removes the selected table option. */
    public void clearView() {
        setNumberOfResults(0);
        setEmployeeArea("");
        populateEmployeesTable(new ArrayList<>());
        employeesTable.getSelectionModel().clearSelection();
    }
    
    /** @param message The message to set. */
    public void setSearchIdMessage(String message) {
        this.searchIdMessage.setFill(Color.FIREBRICK);
        this.searchIdMessage.setText(message);
    }
    
    /** @param message The message to set. */
    public void setSearchMessage(String message) {
        this.searchMessage.setFill(Color.FIREBRICK);
        this.searchMessage.setText(message);
    }
    
    /** @param message The message to set. */
    public void setSelectMessage(String message) {
        this.selectMessage.setFill(Color.FIREBRICK);
        this.selectMessage.setText(message);
    }
    
    /** @param message The message to set. */
    public void setDeactivateMessage(String message) {
        this.deactivateMessage.setFill(Color.FIREBRICK);
        this.deactivateMessage.setText(message);
    }
    
    /** Clears all messages. */
    public void clearMessages() {
        this.searchIdMessage.setText("");
        this.searchMessage.setText("");
        this.selectMessage.setText("");
        this.deactivateMessage.setText("");
    }
}