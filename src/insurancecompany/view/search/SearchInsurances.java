package insurancecompany.view.search;

import insurancecompany.misc.DateUtility;
import insurancecompany.misc.InsuranceType;
import insurancecompany.model.insurances.Insurance;
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
 * insurances. It creates a pane which is sent to the controller and thereafter
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
 * of these insurances for further examination.
 * 
 * <p> On the right the insurance selected in the table is shown. It consists of
 * two parts. The upper part shows the information about the insurance itself, 
 * while the bottom part shows information about the car, boat or property
 * attached. For travel insurances, which has nothing attached, this bottom
 * part is hidden as it has no use.
 * 
 * @author Sindre
 */
public class SearchInsurances {
    
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
    private ComboBox<Object> insuranceTypeCombo;
    private ComboBox<String> numberSelectCombo;
    private DatePicker fromDatePicker;
    private DatePicker toDatePicker;
    private Text searchIdMessage;
    private Text searchMessage;
    private TextField numberField;
    private TextField insuranceIdField;
    
    // Declaration of all the nodes in the center part.
    private Button selectButton;
    private TableView<Insurance> insurancesTable;
    private TableColumn<Insurance, String> insuranceTypeColumn;
    private TableColumn<Insurance, Integer> insuranceIdColumn;
    private TableColumn<Insurance, Integer> customerIdColumn;
    private TableColumn<Insurance, String> activeColumn;
    private Text selectMessage;
    
    // Declaration of all the nodes in the right part.
    private Button deactivateButton;
    private Text deactivateMessage;
    private Text attachmentTitle;
    private TextArea insuranceArea;
    private TextArea attachmentArea;
    
    /**
     * Default constructor. Initializes all field and sets up the view.
     */
    public SearchInsurances() {
        
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
        insuranceTypeCombo = new ComboBox<>();
        populateInsuranceTypeCombo();
        numberSelectCombo = new ComboBox<>();
        populateNumberSelectCombo();
        fromDatePicker = new DatePicker();
        fromDatePicker.setPrefWidth(150);
        DateUtility.restrictDatePickerToOlder(fromDatePicker);
        toDatePicker = new DatePicker();
        toDatePicker.setPrefWidth(150);
        DateUtility.restrictDatePickerToOlder(toDatePicker);
        searchIdMessage = new Text("");
        searchMessage = new Text("");
        numberField = new TextField();
        insuranceIdField = new TextField();

        // Initialization of all the nodes in the center part.
        selectButton = new Button("Velg forsikring");
        insurancesTable = new TableView();
        insurancesTable.setPrefWidth(400);
        insuranceTypeColumn = new TableColumn<>("Forsikring");
        customerIdColumn = new TableColumn<>("Kunde ID");
        insuranceIdColumn = new TableColumn<>("Forsikring ID");
        activeColumn = new TableColumn<>("Aktiv");
        insurancesTable.getColumns().addAll(insuranceTypeColumn, 
                insuranceIdColumn, customerIdColumn, activeColumn);
        insurancesTable.setColumnResizePolicy(
                TableView.CONSTRAINED_RESIZE_POLICY);
        selectMessage = new Text("");

        // Initialization of all the nodes in the right part.
        deactivateButton = new Button("Gjør forsikringen aktiv/inaktiv");
        deactivateMessage = new Text("");
        attachmentTitle = new Text("Vedlegg:");
        insuranceArea = new TextArea();
        attachmentArea = new TextArea();
        
        // Declaration and initialization of the texts and labels which are 
        // used in the view and are not fields.
        Text insuranceIdTitle = new Text("Velg en forsikring i registeret:");
        insuranceIdTitle.setId("textTitle");
        Text searchTermsTitle = new Text("Eller bruk søkebetingelser:");
        searchTermsTitle.setId("textTitle");
        Text insurancesTitle = new Text("Forsikringer:");
        insurancesTitle.setId("textTitle");
        Text insuranceTitle = new Text("Valgt forsikring:");
        insuranceTitle.setId("textTitle");
        attachmentTitle.setId("textTitle");
        Label insuranceIdLabel = new Label("Forsikringsnummer:");
        Label insuranceTypeLabel = new Label("Type forsikring:");
        Label activeLabel = new Label("Kun aktive:");
        Label fromDateLabel = new Label("Fra dato:");
        Label toDateLabel = new Label("Til dato:");
        
        // Adds the nodes to the left part.
        leftPane.add(insuranceIdTitle, 0, 0, 2, 1);
        leftPane.add(insuranceIdLabel, 0, 1);
        leftPane.add(insuranceIdField, 1, 1);
        leftPane.add(searchIdButton, 1, 2);
        leftPane.add(searchIdMessage, 0, 3);
        leftPane.add(searchTermsTitle, 0, 4, 2, 1);
        leftPane.add(numberSelectCombo, 0, 5);
        leftPane.add(numberField, 1, 5);
        leftPane.add(insuranceTypeLabel, 0, 6);
        leftPane.add(insuranceTypeCombo, 1, 6);
        leftPane.add(activeLabel, 0, 7);
        leftPane.add(activeCombo, 1, 7);
        leftPane.add(fromDateLabel, 0, 8);
        leftPane.add(fromDatePicker, 1, 8);
        leftPane.add(toDateLabel, 0, 9);
        leftPane.add(toDatePicker, 1, 9);
        leftPane.add(searchButton, 1, 10);
        leftPane.add(searchMessage, 0, 11, 2, 1);
        
        // Adds the nodes to the center part.
        centerPane.add(insurancesTitle, 0, 0);
        centerPane.add(insurancesTable, 0, 1);
        centerPane.add(selectButton, 0, 2);
        centerPane.add(selectMessage, 0, 3);
        
        // Adds the nodes to the right part.
        rightPane.add(insuranceTitle, 0, 0);
        rightPane.add(insuranceArea, 0, 1);
        rightPane.add(attachmentTitle, 0, 2);
        rightPane.add(attachmentArea, 0, 3);
        rightPane.add(deactivateButton, 0, 4);
        rightPane.add(deactivateMessage, 0, 5);
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
    
    /** Sets the content of the ComboBox numberSelectCombo. */
    private void populateNumberSelectCombo() {
        ObservableList<String> obList = FXCollections.observableArrayList();  
        obList.addAll("Kundenummer", "Personnummer");
        numberSelectCombo.getItems().setAll(obList);
        numberSelectCombo.setValue("Kundenummer");
        numberSelectCombo.setPrefWidth(150);
    }
    
    /**
     * Sets the content of the TableView insurancesTable. The table will 
     * consist of the insurances in the parameter list.
     * 
     * @param insurances The list of insurances which will be displayed in the 
     * table.
     */
    public void populateInsurancesTable(List<Insurance> insurances) {
        // Creates an observable list from the recieved insurance list.
        ObservableList<Insurance> obList 
                = FXCollections.observableArrayList(insurances);
        // Places this list in the insurance table view.
        insurancesTable.setItems(obList);
        // Places a SimpleStringProperty version of the insurance type in the 
        // first column.
        insuranceTypeColumn.setCellValueFactory(cellData -> {
            if ( cellData.getValue() != null) {
                return new SimpleStringProperty(cellData.getValue().getName());
            } else {
                return new SimpleStringProperty("<no name>");
            }
        });
        // Places a SimpleStringProperty version of the insurance ID in the 
        // second column.
        insuranceIdColumn.setCellValueFactory(cellData -> {
            if ( cellData.getValue() != null) {
                return new SimpleObjectProperty<>(cellData.getValue()
                        .getInsuranceId());
            } else {
                return new SimpleObjectProperty(0);
            }
        });
        // Places a SimpleStringProperty version of the customer ID in the 
        // third column.
        customerIdColumn.setCellValueFactory(cellData -> {
            if ( cellData.getValue() != null) {
                return new SimpleObjectProperty<>(cellData.getValue()
                        .getCustomerId());
            } else {
                return new SimpleObjectProperty(0);
            }
        });
        // Places a SimpleStringProperty version of the activity status in the 
        // fourth column.
        activeColumn.setCellValueFactory(cellData -> {
            if (cellData.getValue() != null && cellData.getValue()
                    .getActive()) {
                return new SimpleObjectProperty<>("Ja");
            } else {
                return new SimpleObjectProperty("Nei");
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
    
    /** @return True if customer ID is selected in numberSelectCombo. */
    public boolean isCustomerIdSelected() {
        return numberSelectCombo.getValue().equals("Kundenummer");
    }
    
    /** @return The insurance ID of this class as a String. */
    public String getInsuranceId() {
        return insuranceIdField.getText();
    }
    
    /** 
     * @return The selected value of insurancesTable as an Insurance. Null if 
     * no insurance is selected.
     */
    public Insurance getInsurancesTableValue() {
        return insurancesTable.getSelectionModel() == null ? null : 
                insurancesTable.getSelectionModel().getSelectedItem();
    }
    
    /** 
     * @return The insurance type of this class as a String. Null if
     * no insurance type is selected.
     */
    public String getInsuranceType() {
        return (InsuranceType.class.isInstance(insuranceTypeCombo.getValue())) ?
            ((InsuranceType)insuranceTypeCombo.getValue()).toString() : null;
    }
    
    /**
     * @return The from date of this class as a Calendar. Null if no from date
     * is selected.
     */
    public Calendar getFromDate() {
        return fromDatePicker.getValue() != null ? 
        DateUtility.LocalDateToCalendar(fromDatePicker.getValue()) : null;
    }
    
    /**
     * @return The to date of this class as a Calendar. Null if no to date
     * is selected.
     */
    public Calendar getToDate() {
        return toDatePicker.getValue() != null ? 
        DateUtility.LocalDateToCalendar(toDatePicker.getValue()) : null;
    }
    
    /** @param text The text to set in insuranceArea. */
    public void setInsuranceArea(String text) {
        insuranceArea.setText(text);
    }
    
    /**
     * Sets the attachment title and area visible and sets the parameters 
     * as their text.
     * 
     * @param title The title to set as attachmentTitle.
     * @param text The text to set in attachmentArea.
     */
    public void setAttachmentArea(String title, String text) {
        attachmentTitle.setVisible(true);
        attachmentArea.setVisible(true);
        attachmentTitle.setText(title);
        attachmentArea.setText(text);
    }
    
    /** Clears and hides the attachmentArea and attachmentTitle. */
    public void removeAttachmentArea() {
        setAttachmentArea("", "");
        attachmentTitle.setVisible(false);
        attachmentArea.setVisible(false);
    }
    
    /** Clears the areas, the table and removes the selected table option. */
    public void clearView() {
        setInsuranceArea("");
        setAttachmentArea("Vedlegg:", "");
        populateInsurancesTable(new ArrayList<>());
        insurancesTable.getSelectionModel().clearSelection();
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