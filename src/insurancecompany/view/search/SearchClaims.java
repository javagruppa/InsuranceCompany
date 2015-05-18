package insurancecompany.view.search;

import insurancecompany.misc.ClaimType;
import insurancecompany.misc.DateUtility;
import insurancecompany.misc.InsuranceType;
import insurancecompany.misc.coverages.Damage;
import insurancecompany.model.claims.Claim;
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
 * claims. It creates a pane which is sent to the controller and thereafter
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
 * of these claims for further examination. There are also buttons for showing
 * image attachments and the claim form.
 * 
 * <p> On the right the claim selected in the table is shown. It consists of
 * two parts. The upper part shows the information about the claim itself, 
 * while the bottom part shows information about the insurance that covers the 
 * claim.
 * 
 * @author Sindre
 */
public class SearchClaims {
    
    // Declaration of the main pane which is sent to the controller.
    private GridPane mainPane;
    
    // Declaration of the three parts of the main pane.
    private GridPane leftPane;
    private GridPane centerPane;
    private GridPane rightPane;
    
    // Declaration of all the nodes in the left part.
    private Button searchIdButton;
    private Button searchButton;
    private ComboBox<Object> claimTypeCombo;
    private ComboBox<Object> damageCombo;
    private ComboBox<String> numberSelectCombo;
    private DatePicker fromDatePicker;
    private DatePicker toDatePicker;
    private Text searchIdMessage;
    private Text idMessage;
    private TextField claimIdField;
    private TextField numberField;
    private TextField insuranceIdField;
    
    // Declaration of all the nodes in the center part.
    private Button formButton;
    private Button imageButton;
    private Button selectButton;
    private TableView<Claim> claimsTable;
    private TableColumn<Claim, String> claimTypeColumn;
    private TableColumn<Claim, Integer> claimIdColumn;
    private TableColumn<Claim, Integer> customerIdColumn;
    private TableColumn<Claim, String> dateColumn;
    private Text selectMessage;
    
    // Declaration of all the nodes in the right part.
    private Button disbursementButton;
    private TextArea claimArea;
    private TextArea insuranceArea;
    private TextField disbursementField;
    
    /**
     * Default constructor. Initializes all field and sets up the view.
     */
    public SearchClaims() {
        
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
        claimTypeCombo = new ComboBox<>();
        populateClaimTypeCombo();
        damageCombo = new ComboBox<>();
        damageCombo.setPrefWidth(150);
        numberSelectCombo = new ComboBox<>();
        populateNumberSelectCombo();
        fromDatePicker = new DatePicker();
        fromDatePicker.setPrefWidth(150);
        DateUtility.restrictDatePickerToOlder(fromDatePicker);
        toDatePicker = new DatePicker();
        toDatePicker.setPrefWidth(150);
        DateUtility.restrictDatePickerToOlder(toDatePicker);
        searchIdMessage = new Text("");
        idMessage = new Text("");
        claimIdField = new TextField();
        numberField = new TextField();
        insuranceIdField = new TextField();

        // Initialization of all the nodes in the center part.
        formButton = new Button("Vis skademeldingsskjema");
        imageButton = new Button("Vis bilder");
        selectButton = new Button("Velg skademelding");
        claimsTable = new TableView();
        claimsTable.setPrefWidth(400);
        claimTypeColumn = new TableColumn<>("Skademelding");
        claimIdColumn = new TableColumn<>("Skademeld. ID");
        customerIdColumn = new TableColumn<>("Kunde ID");
        dateColumn = new TableColumn<>("Skadedato");
        claimsTable.getColumns().addAll(claimTypeColumn, 
                claimIdColumn, customerIdColumn, dateColumn);
        claimsTable.setColumnResizePolicy(
                TableView.CONSTRAINED_RESIZE_POLICY);
        selectMessage = new Text("");

        // Initialization of all the nodes in the right part.
        disbursementButton = new Button("Sett erstatningsbeløp");
        claimArea = new TextArea();
        insuranceArea = new TextArea();
        disbursementField = new TextField();
        
        // Initialization of all the texts and labels which are used in the 
        // view. Many of them aren't fields.
        Text claimIdTitle = new Text("Velg en skademelding i registeret:");
        claimIdTitle.setId("textTitle");
        Text searchTermsTitle = new Text("Eller bruk søkebetingelser:");
        searchTermsTitle.setId("textTitle");
        Text claimsTitle = new Text("Skademeldinger:");
        claimsTitle.setId("textTitle");
        Text claimTitle = new Text("Valgt skademelding:");
        claimTitle.setId("textTitle");
        Text insuranceTitle = new Text("Forsikring:");
        insuranceTitle.setId("textTitle");
        Label claimIdLabel = new Label("Skademeldingsnummer:");
        Label claimTypeLabel = new Label("Skadetype:");
        Label damageLabel = new Label("Skade:");
        Label insuranceIdLabel = new Label("Forsikringsnummer:");
        Label fromDateLabel = new Label("Fra skadedato:");
        Label toDateLabel = new Label("Til skadedato:");
        
        // Adds the nodes to the left part.
        leftPane.add(claimIdTitle, 0, 0, 2, 1);
        leftPane.add(claimIdLabel, 0, 1);
        leftPane.add(claimIdField, 1, 1);
        leftPane.add(searchIdButton, 1, 2);
        leftPane.add(searchIdMessage, 0, 3);
        leftPane.add(searchTermsTitle, 0, 4, 2, 1);
        leftPane.add(numberSelectCombo, 0, 5);
        leftPane.add(numberField, 1, 5);
        leftPane.add(claimTypeLabel, 0, 6);
        leftPane.add(claimTypeCombo, 1, 6);
        leftPane.add(damageLabel, 0, 7);
        leftPane.add(damageCombo, 1, 7);
        leftPane.add(insuranceIdLabel, 0, 8);
        leftPane.add(insuranceIdField, 1, 8);
        leftPane.add(fromDateLabel, 0, 9);
        leftPane.add(fromDatePicker, 1, 9);
        leftPane.add(toDateLabel, 0, 10);
        leftPane.add(toDatePicker, 1, 10);
        leftPane.add(searchButton, 1, 11);
        leftPane.add(idMessage, 0, 12, 2, 1);
        
        // Adds the nodes to the center part.
        centerPane.add(claimsTitle, 0, 0, 3, 1);
        centerPane.add(claimsTable, 0, 1, 3, 1);
        centerPane.add(selectButton, 0, 2);
        centerPane.add(formButton, 1, 2);
        centerPane.add(imageButton, 2, 2);
        centerPane.add(selectMessage, 0, 3, 3, 1);
        
        // Adds the nodes to the right part.
        rightPane.add(claimTitle, 0, 0, 2, 1);
        rightPane.add(claimArea, 0, 1, 2, 1);
        rightPane.add(insuranceTitle, 0, 2, 2, 1);
        rightPane.add(insuranceArea, 0, 3, 2, 1);
        rightPane.add(disbursementField, 0, 4);
        rightPane.add(disbursementButton, 1, 4);
    }
    
    /** Sets the content of the ComboBox claimTypeCombo. */
    private void populateClaimTypeCombo() {
        ObservableList<ClaimType> obList;
        obList = FXCollections.observableArrayList(ClaimType.values());
        claimTypeCombo.getItems().add("");
        claimTypeCombo.getItems().addAll(obList);
        claimTypeCombo.setPrefWidth(150);
    }
    
    /** Sets the content of the ComboBox damageCombo. */
    public void populateDamageCombo(Damage[] damages) {
        damageCombo.setValue("");
        damageCombo.setPrefWidth(150);
        if (damages == null) {
            damageCombo.getItems().setAll("");
        } else {
            ObservableList<Damage> obList;
            obList = FXCollections.observableArrayList(damages);
            damageCombo.getItems().setAll("");
            damageCombo.getItems().addAll(obList);
        }
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
     * Sets the content of the TableView claimssTable. The table will 
     * consist of the claims in the parameter list.
     * 
     * @param claims The list of claims which will be displayed in the 
     * table.
     */
    public void populateClaimsTable(List<Claim> claims) {
        // Creates an observable list from the recieved claim list.
        ObservableList<Claim> obList 
                = FXCollections.observableArrayList(claims);
        // Places this list in the claim table view.
        claimsTable.setItems(obList);
        // Places a SimpleStringProperty version of the claim type in the 
        // first column.
        claimTypeColumn.setCellValueFactory(cellData -> {
            if ( cellData.getValue() != null) {
                return new SimpleStringProperty(cellData.getValue().getName());
            } else {
                return new SimpleStringProperty("<no name>");
            }
        });
        // Places a SimpleStringProperty version of the claim ID in the 
        // second column.
        claimIdColumn.setCellValueFactory(cellData -> {
            if ( cellData.getValue() != null) {
                return new SimpleObjectProperty<>(cellData.getValue()
                        .getClaimId());
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
        // Places a SimpleStringProperty version of the date happened in the 
        // fourth column.
        dateColumn.setCellValueFactory(cellData -> {
            if ( cellData.getValue() != null) {
                return new SimpleObjectProperty<>(DateUtility
                        .NORWEGIAN_DATE_FORMAT.format(cellData.getValue()
                        .getDateHappened().getTime()));
            } else {
                return new SimpleObjectProperty(0);
            }
        });
    }
    
    /**
     * Sets the event handler for the damageCombo.
     * 
     * @param value The event handler to set.
     */
    public void setClaimTypeEventHandler(EventHandler<ActionEvent> value) {
        claimTypeCombo.setOnAction(value);
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
    
    /** @return The claim ID of this class as a String. */
    public String getClaimId() {
        return claimIdField.getText();
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
     * @return The selected value of claimsTable as an Claim. Null if 
     * no claim is selected.
     */
    public Claim getClaimsTableValue() {
        return claimsTable.getSelectionModel() == null ? null : 
                claimsTable.getSelectionModel().getSelectedItem();
    }
    
    /** 
     * @return The claim type of this class as a String. Null if
     * no claim type is selected.
     */
    public String getClaimType() {
        return (ClaimType.class.isInstance(claimTypeCombo.getValue())) ?
            ((ClaimType)claimTypeCombo.getValue()).toString() : null;
    }
    
    /** 
     * @return The damage of this class as a Damage. Null if
     * no damage is selected.
     */
    public Damage getDamage() {
        return String.class.isInstance(damageCombo.getValue()) ?
                null : (Damage) damageCombo.getValue();
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
    
    /** @param text The text to set in claimArea. */
    public void setClaimArea(String text) {
        claimArea.setText(text);
    }
    
    /** @param text The text to set in insuranceArea. */
    public void setInsuranceArea(String text) {
        insuranceArea.setText(text);
    }
    
    /** Clears the areas, the table and removes the selected table option. */
    public void clearView() {
        setClaimArea("");
        populateClaimsTable(new ArrayList<>());
        claimsTable.getSelectionModel().clearSelection();
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