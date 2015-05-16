/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package insurancecompany.view.register.claims;

import insurancecompany.misc.DateUtility;
import insurancecompany.misc.coverages.Damage;
import insurancecompany.model.claims.ClaimItem;
import insurancecompany.model.insurances.Insurance;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * This class represents a home content claim registration view. This view is connected
 * to the model through a controller. The class creates a GridPane with nodes.
 * The mainPane consists of 3 main parts. 
 * 
 * <p>The left side of this pane is used for searching for customers. If the 
 * search engine finds a customer this customer is displayed and a list of all 
 * this customer's insurances is as well displayed in a table view. The user 
 * is then able to select which insurance he/she wants to use for the claim. 
 * Only home content insurances are allowed to be selected. 
 * 
 * <p>The center side of this pane is used for typing in the fields connected
 * to the damage/claim.
 * 
 * <p>The right side of this pane is used for adding items lost/damaged to the 
 * claim.
 * 
 * <p>After typing in all fields the user is able to register this as a claim
 * as long as all fields are selected and typed in properly.
 * 
 * <p>Each value used in the registration goes through validation, and 
 * informative messages are displayed to the user if the input is not valid.
 * 
 * <p>This class has a method the returns the GridPane. This method is used to
 * connect the content of this class to another view.
 * 
 * @author André
 */
public class RegisterHomeContentClaim {
    
    // The main pane of this class.
    private GridPane mainPane;
    private GridPane centerPane;
    private GridPane rightPane;
    
    // SEARCH FOR CUSTOMER NODES:
    // Input nodes, TextFields:
    // Text field where user can input a customer id. 
    private TextField customerIdField;
    // Text field where user can input a personal number. 
    private TextField personalNumberField;
    // Output nodes, TextArea and TableView and Text
    // Text area where customer information is displayed. 
    private TextArea customerArea;
    // Table view where a list of a customers insurances are displayed. 
    private TableView<Insurance> insurancesTable;
    // Table column displaying an insurance type. 
    private TableColumn<Insurance, String> insuranceTypeColumn;
    // Table column displaying an insurance's coverage. 
    private TableColumn<Insurance, String> insuranceCoverageColum;
    // Table column displaying an insurance's id. 
    private TableColumn<Insurance, Integer> insuranceIdColumn;
    // Text used to display information after an insurance is selected. 
    private Text selectInsuranceMessage;
    // Buttons:
    // Button used to search for a customer through a customer id. 
    private Button searchCustomerIdButton;
    // Button used to search for a customer through a personal number. 
    private Button searchPersonalNumberButton;
    // Button used to select an insurance to use for the claim. 
    private Button selectInsuranceButton;

    // Image uploaded by the user. 
    private Image image;
    // The date of when the damage happened. 
    private DatePicker dateHappenedPicker;
    // Textual description of the claim. 
    private TextArea descriptionTextArea;
    // Text field where the user can type in an appraisal for the claim. 
    private TextField appraisalField;
    // GridPane used to display the damages available for the customers coverage. 
    private GridPane damagesPane;
    // List of check boxes used in conjuction with the damages. 
    private List<CheckBox> damageCheckBoxes;
    // List of damages available for the customers coverage. 
    private ArrayList<Damage> damages;
    // Buttons:
    // Button used to select an image describing the claim. 
    private Button selectImageButton;
    // Button used to register the claim. 
    private Button registerButton;  
    // Output nodes, Text messages:
    // Text used to display a status/help message when the user presses the register button. 
    private Text registerButtonMessage;
    private Text selectImageStatus;
    // Text used to display a help message if the user types in an invalid value for the appraisal. 
    private Text appraisalFieldMessage;
    
    // The customerId used in the claim registration. 
    private int selectedCustomerId;
    
    // Input field for the description of the item. 
    private TextArea itemDescriptionTextArea;
    // Input field for where the item was acquired. 
    private TextField acquiredAreaField;
    // DatePicker for the date the item was acquired. 
    private DatePicker acquiredDatePicker;
    // Input field for the value of the item. 
    private TextField valueField;
    // Input field for the description of how the item can be documented. 
    private TextArea descriptionOfDocumentationTextArea;

    // Button used to register the item from the input fields. 
    private Button addItemButton;
    // Text message used to confirm that an item has been added. 
    private Text addItemConfirmMessage;
    // A list of claim items belonging to this claim.
    private List<ClaimItem> claimItems;
    
    /**
     * Sole constructor. Initializes the main Pane and sets up all its nodes.
     * Each node is then added to the Pane.
     */
    public RegisterHomeContentClaim() {
        
        // Sets up the mainPane
        mainPane = new GridPane();
        // Set CSS id:
        mainPane.setId("innerPane");
        // Set up the left pane:
        centerPane = new GridPane();
        centerPane.setId("innerSubPane");
        rightPane = new GridPane();
        rightPane.setId("innerSubPane");
        
        // Set up column constraints. Width in pixels:
        ColumnConstraints col1 = new ColumnConstraints(120);
        ColumnConstraints col2 = new ColumnConstraints(100);
        ColumnConstraints col3 = new ColumnConstraints(40);
        ColumnConstraints col4 = new ColumnConstraints(50);
        ColumnConstraints col5 = new ColumnConstraints(150);
        ColumnConstraints col6 = new ColumnConstraints(100);
        ColumnConstraints col7 = new ColumnConstraints(100);
        // Add these constraints:
        centerPane.getColumnConstraints().addAll(col1, col2, col3, col4, col5, col6, col7);
        
        // Start initializing all nodes that are to be placed in the gridpane:
        // Nodes that are used for customer searching and displaying customer info and insurances:
        Text selectCustomerTitle = new Text("Velg først en kunde i registeret:");
        selectCustomerTitle.setId("textTitle");
        Label customerIdLabel = new Label("Kundenummer:");
        customerIdField = new TextField();
        searchCustomerIdButton = new Button("Søk");
        Label personalNumberLabel = new Label("Personnummer:");
        personalNumberField = new TextField();
        searchPersonalNumberButton = new Button("Søk");
        Text resultTitle = new Text("Søkeresultat:");
        resultTitle.setId("textTitle");
        customerArea = new TextArea();
        customerArea.setPrefSize(100, 150);
        customerArea.setEditable(false);
        customerArea.setPrefColumnCount(2);
        customerArea.setPrefRowCount(3);
        Text insurancesTitle = new Text("Velg forsikringen til denne skaden:");
        insurancesTitle.setId("textTitle");
        insurancesTable = new TableView();
        insurancesTable.setPrefHeight(100);
        insuranceTypeColumn = new TableColumn<>("Forsikring");
        insuranceCoverageColum = new TableColumn<>("Dekning");
        insuranceIdColumn = new TableColumn<>("Forsikringsid");
        insurancesTable.getColumns().addAll(insuranceTypeColumn, insuranceCoverageColum, insuranceIdColumn);
        insurancesTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        selectInsuranceButton = new Button("Velg forsikring");
        selectInsuranceMessage = new Text();
        // Nodes that are used for registering claim:
        Text damagesTitle = new Text("Fyll inn informasjon:");
        damagesTitle.setId("textTitle");
        Label dateHappenedLabel = new Label("Skadedato:");
        dateHappenedPicker = new DatePicker();
        // Set restrictions for the datepicker:
        restrictDatePicker(dateHappenedPicker);
        Label descriptionLabel = new Label("Beskrivelse av skaden:");
        descriptionTextArea = new TextArea();
        // Wrap the text area, so that when text reaches the end of each line,
        // a line break is made:
        descriptionTextArea.setWrapText(true);
        descriptionTextArea.setPrefHeight(140);
        Label damagesLabel = new Label("Skade:");
        damagesPane = new GridPane();
        damagesPane.setPadding(new Insets(10));
        damagesPane.setPrefHeight(150);
        // Set gaps to damages pane:
        damagesPane.setHgap(10);
        damagesPane.setVgap(6);
        // Set CSS ID:
        damagesPane.setId("customPane1");
        Label appraisalLbel = new Label("Takseringsbeløp:");
        appraisalField = new TextField();
        appraisalFieldMessage = new Text();
        Label selectImageLabel = new Label("Bilde som beskriver skaden");
        selectImageButton = new Button("Hent bilde");

        selectImageStatus = new Text();
        
        // Initialize the ClaimItem list:
        claimItems = new ArrayList();
        Text addItemsTitle = new Text("Legg til gjenstander:");
        addItemsTitle.setId("textTitle");
        Label acquiredDateLabel = new Label("Tilskaffet dato(Circa):");
        acquiredDatePicker = new DatePicker();
        // Sets up a restriction to the DatePicker:
        DateUtility.restrictDatePickerToOlder(acquiredDatePicker);
        Label descriptionItemLabel = new Label("Beskrivelse av gjenstanden:");
        itemDescriptionTextArea = new TextArea();
        itemDescriptionTextArea.setWrapText(true);
        itemDescriptionTextArea.setPrefRowCount(4);
        Label acquiredAreaLabel = new Label("Sted tilskaffet:");
        acquiredAreaField = new TextField();
        Label valueLabel = new Label("Verdi ved ny:");
        valueField = new TextField();
        Label descriptionOfDocumentationLabel = new Label("Beskrivelse på hvordan gjenstanden kan dokumenteres:");
        descriptionOfDocumentationTextArea = new TextArea();
        descriptionOfDocumentationTextArea.setPrefRowCount(4);
        descriptionOfDocumentationTextArea.setWrapText(true);
        addItemButton = new Button("Legg til gjenstand");
        addItemConfirmMessage = new Text();
        registerButton = new Button("Registrer skademelding");
        registerButtonMessage = new Text();
        
        // Add nodes to mainPane:
        // Nodes that are used for registering claim:
        centerPane.add(selectCustomerTitle, 0, 0);
        centerPane.add(customerIdLabel, 0, 1);
        centerPane.add(customerIdField, 1, 1);
        centerPane.add(searchCustomerIdButton, 2, 1);      
        centerPane.add(personalNumberLabel, 0, 2);
        centerPane.add(personalNumberField, 1, 2);
        centerPane.add(searchPersonalNumberButton, 2, 2);
        centerPane.add(resultTitle, 0, 3);
        centerPane.add(customerArea, 0, 4, 3, 4);
        centerPane.add(insurancesTitle, 0, 8);
        centerPane.add(insurancesTable, 0, 9, 3, 7);
        centerPane.add(selectInsuranceButton, 0, 16);
        centerPane.add(selectInsuranceMessage, 1, 16);
        
        // Nodes that are used for registering claim:
        centerPane.add(damagesTitle, 4, 0);
        centerPane.add(dateHappenedLabel, 4, 1);
        centerPane.add(dateHappenedPicker, 5, 1);
        centerPane.add(descriptionLabel, 4, 2);
        centerPane.add(descriptionTextArea,4, 3, 3, 4);
        centerPane.add(damagesLabel, 4, 7);
        centerPane.add(damagesPane, 4, 7, 3, 5);
        centerPane.add(appraisalLbel, 4, 13);
        centerPane.add(appraisalField, 5, 13);
        centerPane.add(appraisalFieldMessage, 6, 13, 2, 1);
        centerPane.add(selectImageLabel, 4, 14);
        centerPane.add(selectImageButton, 5, 14);
        centerPane.add(selectImageStatus, 6, 14, 2, 1);
        
        // Nodes that are used for adding items:
        rightPane.add(addItemsTitle, 0, 0, 2, 1);
        rightPane.add(acquiredDateLabel, 0, 1, 2, 1);
        rightPane.add(acquiredDatePicker, 2, 1);
        rightPane.add(descriptionItemLabel, 0, 2, 3, 1);
        rightPane.add(itemDescriptionTextArea, 0, 3, 3, 2);
        rightPane.add(acquiredAreaLabel, 0, 6);
        rightPane.add(acquiredAreaField, 1, 6, 2, 1);
        rightPane.add(valueLabel, 0, 7);
        rightPane.add(valueField, 1, 7, 2, 1);
        rightPane.add(descriptionOfDocumentationLabel, 0, 8, 3, 1);
        rightPane.add(descriptionOfDocumentationTextArea, 0, 9, 3, 3);
        rightPane.add(addItemButton, 0, 12, 2, 1);
        rightPane.add(addItemConfirmMessage, 2, 12, 3, 1);
        rightPane.add(registerButton, 0, 13, 3, 1);
        rightPane.add(registerButtonMessage, 0, 14, 3, 1); 
        
        mainPane.add(centerPane, 0, 0);
        mainPane.add(rightPane, 2, 0);
        
    } // end of sole Constructor
    
    
    /**
     * Places list of damages inside a list of combo boxes and places
     * each combo box inside the damage grid pane of this view.
     * @param damages 
     */
    public void populateDamagesPane(ArrayList<Damage> damages) {
        damageCheckBoxes = new ArrayList<>();
        // Decides number of columns of damages:
        int columns = 3 ;
        // Start at first column:
        int column = 0;
        // Start at first row:
        int row = 0;
        // Go through all damages:
        for (Damage damage : damages) {
            // Create a checkbox for each damage:
            CheckBox cb = new CheckBox(damage.toString());
            // Add each checkbox to our list of checkboxes:
            damageCheckBoxes.add(cb);
            // When our column count has reached the value of our limit:
            if (column == columns) {
                // We reset our column back to 0
                column = 0;
                // And start at a new row:
                row++;
            }
            // We add each combobox to our GridPane:
            damagesPane.add(cb, column, row);
            // After each addition we ready our column for our next combobox:
            column++;
        }
    } // end of method populateDamagesPane
    
    /**
     * Sets up date restrictions to the DatePicker in the parameter.
     * @param dateHappenedPicker 
     */
    private void restrictDatePicker(DatePicker dateHappenedPicker) {
        dateHappenedPicker.setValue(LocalDate.now());
        // Sets up a restricton for choosable dates:
        Callback<DatePicker, DateCell> dayCellFactory = dp -> new DateCell()
        {
            @Override
            public void updateItem(LocalDate item, boolean empty)
            {
                super.updateItem(item, empty);
                // Only allow dates that are up to 2 months old, and not newer than current date:
                if(item.isBefore(LocalDate.now().minusMonths(2)) || item.isAfter(LocalDate.now()))
                {   // Sets the background color of the invalid dates to a pink/red color:
                    setStyle("-fx-background-color: #ffc0cb;");
                    // Disables them, so they can not be picked:
                    setDisable(true);
                }
            }
        };
        // Apply these restrictions to our DatePicker
        dateHappenedPicker.setDayCellFactory(dayCellFactory);
        dateHappenedPicker.setPromptText("dd/MM/yyyy");
    } // end of method restrictDatePicker
    
    /** 
     * Returns the customer id field as a String.
     * @return the customerIdField
     */
    public String getCustomerId() {
        return customerIdField.getText();
    }

    /**
     * Returns the personal number field as a String.
     * @return the personalNumberField
     */
    public String getPersonalNumber() {
        return personalNumberField.getText();
    }


    /**
     * Places the String in the parameter inside the customer
     * area TextArea.
     * @param customerArea the customerArea to set
     */
    public void setCustomerArea(String customerArea) {
        this.customerArea.setText(customerArea);
    }

    /**
     * Populates the insurance table of this class with a list of insurances:
     * @param insurances 
     */
    public void populateInsurancesTable(List<Insurance> insurances) {
        // Create an observable list from the recieved insurance list:
        ObservableList<Insurance> obList = FXCollections.observableArrayList(insurances);
        // Place this list in the insurance table view:
        insurancesTable.setItems(obList);
        insuranceTypeColumn.setCellValueFactory((cellData) -> {
                if ( cellData.getValue() != null) {
                    // Places a SimpleStringProperty version of the insurance name in
                    // the first column:
                    return new SimpleStringProperty(cellData.getValue().getName());
                } else {
                    return new SimpleStringProperty("<no name>");
                }
        });
        insuranceCoverageColum.setCellValueFactory((cellData) -> {
                if ( cellData.getValue() != null) {
                    // Places a SimpleObjectProperty version of the insurance's coverage in
                    // the second column:
                    return new SimpleObjectProperty<>(cellData.getValue().getCoverage().toString());
                } else {
                    return new SimpleObjectProperty(0);
                }
        });
        insuranceIdColumn.setCellValueFactory((cellData) -> {
                if ( cellData.getValue() != null) {
                    // Places a SimpleObjectProperty version of the insurance's id in
                    // the third column:
                    return new SimpleObjectProperty<>(cellData.getValue().getInsuranceId());
                } else {
                    return new SimpleObjectProperty(0);
                }
        });   
    }  // end of method populateInsurancesTable
    
    
    
    /**
     * Returns the Insurance object selected in the table.
     * @return 
     */
    public Insurance getInsuranceTableValue() {
        // If no row is selected in the table, retur null, otherwise return the selected insurance:
        return insurancesTable.getSelectionModel() == null ? null : insurancesTable.getSelectionModel().getSelectedItem();
    }
    
    /**
     * Takes an array of damages as a parameter and sets it to this views damages
     * list. Also forwards this list to the damages pane.
     * @param damages 
     */
    public void setDamages(Damage[] damages) {
        // Create an ArrayList of the recieved damages array:
        List<Damage> damage = new ArrayList(Arrays.asList(damages));
        // Initialize the damages field with this list of damages:
        this.damages = new ArrayList(Arrays.asList(damages));
        populateDamagesPane(this.damages);
    }  // end of method setDamages
    
    //////////////////////////////////////////
    // SET EVENT HANDLERS METHODS:
    //////////////////////////////////////////
    /**
     * Sets event handler for the search customer button of this view.
     * @param value 
     */
    public void setSearchCustomerIdButtonEventHandler(EventHandler<ActionEvent> value) {
        searchCustomerIdButton.setOnAction(value);
    }

    /**
     * Sets event handler for the search personal number button of this view.
     * @param value 
     */
    public void setSearchPersonalNumberButtonEventHandler(EventHandler<ActionEvent> value) {
        searchPersonalNumberButton.setOnAction(value);
    }
    
    /**
     * Sets event handler for the select insurance button of this view.
     * @param value 
     */
    public void setSelectInsuranceButtonEventHandler(EventHandler<ActionEvent> value) {
        selectInsuranceButton.setOnAction(value);
    }
    
    /**
     * Sets event handler for the select image button of this view.
     * @param value 
     */
    public void setSelectImageButtonEventHandler(EventHandler<ActionEvent> value) {
        selectImageButton.setOnAction(value);
    }
    
    /**
     * Sets event handler for the register button of this view.
     * @param value 
     */
    public void setRegisterButtonEventHandler(EventHandler<ActionEvent> value) {
        registerButton.setOnAction(value);
    }
    
    /**
     * Sets the event handler for the add item button of this view.
     * @param event
     */
    public void setAddItemButtonEventHandler(EventHandler<ActionEvent> event) {
        addItemButton.setOnAction(event);
    }
    
    /**
     * Returns the mainPane of this view.
     * @return the mainPane of this view.
     */
    public Pane getMainPane() {
        return mainPane;
    }

    /**
     * Sets the select insurance message of this view.
     * @param selectInsuranceMessage the selectInsuranceMessage to set
     */
    public void setSelectInsuranceMessage(String selectInsuranceMessage) {
        this.selectInsuranceMessage.setText(selectInsuranceMessage);
    }

    /**
     * Returns the text from the description text area as a String.
     * @return the descriptionTextArea
     */
    public String getDescriptionTextArea() {
        return descriptionTextArea.getText();
    }

    /**
     * Returns the text from the appraisal text field as a String.
     * @return the appraisalField
     */
    public String getAppraisalField() {
        return appraisalField.getText();
    }

    
    /**
     * Returns the damages selected from the check boxes.
     * Returns an empty set if no damages are selected.
     * @return a Set of Damages
     */
    public Set<Damage> getSelectedDamages() {
        // Creates an enum set:
        Set<Damage> result = Collections.synchronizedSet(EnumSet.noneOf(Damage.class));
        // Loops through all the checkboxes:
        for (int i = 0; i < damageCheckBoxes.size(); i++) {
            // For each checkbox that is selected:
            if (damageCheckBoxes.get(i).isSelected()) {
                // Add the damage from the damages list. This list is ordered in the same
                // order as its respective values in the checkbox list:
                result.add(damages.get(i));
            }
        }
        return result;
    } // end of method getSelectedDamages
    
    /**
     * Clears the messages used to display invalid input.
     */
    public void clearMessages() {
        selectInsuranceMessage.setText("");
        registerButtonMessage.setText("");
    }
    
    /**
     * Clears the uploaded data as well as clearing their status text.
     */
    public void clearUploads() {
        // Clear the image:
        image = null;
        // Clear the items:
        claimItems.clear();
        // Clear the status messages:
        selectImageStatus.setText("");
        addItemConfirmMessage.setText("");
    }

    /**
     * Get the image of the damage from this view.
     * @return the image
     */
    public Image getImage() {
        return image;
    }

    /**
     * Set an image of the damage.
     * @param image the image to set
     */
    public void setImage(Image image) {
        this.image = image;
    }

    /**
     * @param registerButtonMessage the registerButtonMessage to set
     */
    public void setRegisterButtonMessage(String registerButtonMessage) {
        this.registerButtonMessage.setText(registerButtonMessage);
    }

    /**
     * @return the selectedCustomerId
     */
    public int getSelectedCustomerId() {
        return selectedCustomerId;
    }

    /**
     * @param selectedCustomerId the selectedCustomerId to set
     */
    public void setSelectedCustomerId(int selectedCustomerId) {
        this.selectedCustomerId = selectedCustomerId;
    }

    /**
     * Returns a Calendar object of the selected date for when the damage
     * happened. Returns null if no date is selected.
     * @return the dateHappenedPicker
     */
    public Calendar getDateHappenedPickerValue() {
         // Get selected value:
        if (dateHappenedPicker.getValue() != null) {
            return  DateUtility.LocalDateToCalendar(dateHappenedPicker.getValue());
        } else {
            return null;
        }
    }

    /**
     * @param appraisalFieldMessage the appraisalFieldMessage to set
     */
    public void setAppraisalFieldMessage(String appraisalFieldMessage) {
        this.appraisalFieldMessage.setText(appraisalFieldMessage);
    }

    /**
     * @param selectImageMessage the selectImageStatus to set
     */
    public void setSelectImageMessage(String selectImageMessage) {
        this.selectImageStatus.setText(selectImageMessage);
    }
    
    /**
     * Returns a List of ClaimItem.
     * @return the claimItems
     */
    public List<ClaimItem> getClaimItems() {
        return claimItems;
    }
    
    /**
     * Adds a claim item to the claim items List of this view.
     * @param claimItem 
     */
    public void addClaimItem(ClaimItem claimItem) {
        claimItems.add(claimItem);
    }

    /**
     * @return the itemDescriptionTextArea
     */
    public String getItemDescriptionTextArea() {
        return itemDescriptionTextArea.getText();
    }

    /**
     * @return the acquiredAreaField
     */
    public String getAcquiredAreaField() {
        return acquiredAreaField.getText();
    }

    /**
     * @return the acquiredDatePicker
     */
    public Calendar getAcquiredDatePickerValue() {
        // Get selected value:
        if (acquiredDatePicker.getValue() != null) {
            return  DateUtility.LocalDateToCalendar(acquiredDatePicker.getValue());
        } else {
            return null;
        }
    }

    /**
     * @return the valueField
     */
    public String getValueField() {
        return valueField.getText();
    }

    /**
     * @return the descriptionOfDocumentationTextArea
     */
    public String getDescriptionOfDocumentationTextArea() {
        return descriptionOfDocumentationTextArea.getText();
    }

    /**
     * @param addItemConfirmMessage the addItemConfirmMessage to set
     */
    public void setAddItemConfirmMessage(String addItemConfirmMessage) {
        this.addItemConfirmMessage.setText(addItemConfirmMessage);
    }

} // end of class HomeContentClaimRegistration
