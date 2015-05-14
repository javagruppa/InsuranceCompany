/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package insurancecompany.view.register.claims;

import insurancecompany.misc.coverages.Damage;
import insurancecompany.model.insurances.Insurance;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
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
import javafx.geometry.Pos;
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
import javafx.util.Callback;

/**
 * This class represents a home claim registration view. This view is connected
 * to the model through a controller. The class creates a GridPane with nodes.
 * The GridPane consists of 2 main parts. 
 * 
 * <p>The left side of this pane is used for searching for customers. If the 
 * search engine finds a customer this customer is displayed and a list of all 
 * this customer's insurances is as well displayed in a table view. The user 
 * is then able to select which insurance he/she wants to use for the claim. 
 * Only home insurances are allowed to be selected. 
 * 
 * <p>The right side of this pane is used for typing in the fields connected
 * to the damage/claim.
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
public class HomeClaimRegistration {
    
    /** The main pane of this class.*/
    private GridPane mainPane;
    
    // SEARCH FOR CUSTOMER NODES:
    // Input nodes, TextFields:
    /** Text field where user can input a customer id. */
    private TextField customerIdField;
    /** Text field where user can input a personal number. */
    private TextField personalNumberField;
    // Output nodes, TextArea and TableView and Text
    /** Text area where customer information is displayed. */
    private TextArea customerArea;
    /** Table view where a list of a customers insurances are displayed. */
    private TableView<Insurance> insurancesTable;
    /** Table column displaying an insurance type. */
    private TableColumn<Insurance, String> insuranceTypeColumn;
    /** Table column displaying an insurance's coverage. */
    private TableColumn<Insurance, String> insuranceCoverageColum;
    /** Table column displaying an insurance's id. */
    private TableColumn<Insurance, Integer> insuranceIdColumn;
    /** Text used to display information after an insurance is selected. */
    private Text selectInsuranceMessage;
    // Buttons:
    /** Button used to search for a customer through a customer id. */
    private Button searchCustomerIdButton;
    /** Button used to search for a customer through a personal number. */
    private Button searchPersonalNumberButton;
    /** Button used to select an insurance to use for the claim. */
    private Button selectInsuranceButton;

    /** Image uploaded by the user. */
    private Image image;
    /** The date of when the damage happened. */
    private DatePicker dateHappenedPicker;
    /** Textual description of the claim. */
    private TextArea descriptionTextArea;
    /** Text field where the user can type in an appraisal for the claim. */
    private TextField appraisalField;
    /** GridPane used to display the damages available for the customers coverage. */
    private GridPane damagesPane;
    /** List of check boxes used in conjuction with the damages. */
    private List<CheckBox> damageCheckBoxes;
    /** List of damages available for the customers coverage. */
    private ArrayList<Damage> damages;
    // Buttons:
    /** Button used to select an image describing the claim. */
    private Button selectImageButton;
    /** Button used to register the claim. */
    private Button registerButton;  
    // Output nodes, Text messages:
    
    /**
     * Sole constructor. Initializes the main Pane and sets up all its nodes.
     * Each node is then added to the Pane.
     */
    public HomeClaimRegistration() {
        
        // Sets up the mainPane
        mainPane = new GridPane();
        // Set CSS id:
        mainPane.setId("innerPane");
        // Set up column constraints. Width in pixels:
        ColumnConstraints col1 = new ColumnConstraints(120);
        ColumnConstraints col2 = new ColumnConstraints(100);
        ColumnConstraints col3 = new ColumnConstraints(40);
        ColumnConstraints col4 = new ColumnConstraints(50);
        ColumnConstraints col5 = new ColumnConstraints(150);
        ColumnConstraints col6 = new ColumnConstraints(100);
        ColumnConstraints col7 = new ColumnConstraints(150);
        // Add these constraints:
        mainPane.getColumnConstraints().addAll(col1, col2, col3, col4, col5, col6, col7);
        
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
        Text insurancesTitle = new Text("Velg forsikringen denne skademelding går under:");
        insurancesTitle.setId("textTitle");
        insurancesTable = new TableView();
        insurancesTable.setPrefHeight(100);
        insuranceTypeColumn = new TableColumn<>("Forsikring");
        insuranceCoverageColum = new TableColumn<>("Dekning");
        insuranceIdColumn = new TableColumn<>("Forsikringsid");
        insurancesTable.getColumns().addAll(insuranceTypeColumn, insuranceCoverageColum, insuranceIdColumn);
        insurancesTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        selectInsuranceButton = new Button("Velg");
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
        Label selectImageLabel = new Label("Bilde som beskriver skaden");
        selectImageButton = new Button("Hent bilde");
        registerButton = new Button("Registrer");
        
        // Add nodes to mainPane:
        // Nodes that are used for registering claim:
        mainPane.add(selectCustomerTitle, 0, 0);
        mainPane.add(customerIdLabel, 0, 1);
        mainPane.add(customerIdField, 1, 1);
        mainPane.add(searchCustomerIdButton, 2, 1);      
        mainPane.add(personalNumberLabel, 0, 2);
        mainPane.add(personalNumberField, 1, 2);
        mainPane.add(searchPersonalNumberButton, 2, 2);
        mainPane.add(resultTitle, 0, 3);
        mainPane.add(customerArea, 0, 4, 3, 4);
        mainPane.add(insurancesTitle, 0, 8);
        mainPane.add(insurancesTable, 0, 9, 3, 7);
        mainPane.add(selectInsuranceButton, 0, 16);
        mainPane.add(selectInsuranceMessage, 1, 16);
        // Nodes that are used for registering claim:
        mainPane.add(damagesTitle, 4, 0);
        mainPane.add(dateHappenedLabel, 4, 1);
        mainPane.add(dateHappenedPicker, 5, 1);
        mainPane.add(descriptionLabel, 4, 2);
        mainPane.add(descriptionTextArea,4, 3, 3, 4);
        mainPane.add(damagesLabel, 4, 7);
        mainPane.add(damagesPane, 4, 7, 3, 5);
        mainPane.add(appraisalLbel, 4, 13);
        mainPane.add(appraisalField, 5, 13);
        mainPane.add(selectImageLabel, 4, 14);
        mainPane.add(selectImageButton, 5, 14);
        mainPane.add(registerButton, 4, 16);
    } // end of sole Constructor
    
    /**
     * Places list of damages inside a list of combo boxes and places
     * each combo box inside the damage grid pane of this view.
     * @param damages 
     */
    public void populateDamagesPane(ArrayList<Damage> damages) {
        damageCheckBoxes = new ArrayList<CheckBox>();
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
    public String getCustomerIdField() {
        return customerIdField.getText();
    }

    /**
     * Returns the personal number field as a String.
     * @return the personalNumberField
     */
    public String getPersonalNumberField() {
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
    public void setSelectInsuranceMessage(Text selectInsuranceMessage) {
        this.selectInsuranceMessage = selectInsuranceMessage;
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

} // end of class HomeClaimRegistration
