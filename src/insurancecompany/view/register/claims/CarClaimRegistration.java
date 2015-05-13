/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package insurancecompany.view.register.claims;

import insurancecompany.misc.coverages.Damage;
import insurancecompany.model.insurances.Insurance;
import java.time.LocalDate;
import java.time.chrono.HijrahChronology;
import java.util.ArrayList;
import java.util.List;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.util.Callback;

/**
 *
 * @author André
 */
public class CarClaimRegistration {
    
    /** The main pane of this class.*/
    private GridPane mainPane;
    
    // SEARCH FOR CUSTOMER NODES:
    // Input nodes, TextFields
    private TextField customerIdField;
    private TextField personalNumberField;
    // Output nodes, TextArea and TableView and Text
    private TextArea customerArea;
    private TableView<Insurance> insurancesTable;
    private TableColumn<Insurance, String> insuranceTypeColumn;
    private TableColumn<Insurance, String> insuranceCoverageColum;
    private TableColumn<Insurance, Integer> insuranceIdColumn;
    private Text selectInsuranceMessage;
    // Buttons:
    private Button searchCustomerIdButton;
    private Button searchPersonalNumberButton;
    private Button selectInsuranceButton;

    
    
    /** The date of when the damage happened */
    private DatePicker dateHappenedPicker;
    /** Textual description of the claim. */
    private TextArea descriptionTextArea;
    /** File chooser for image of the damage. */
    private FileChooser fileChooser;
    private TextField appraisalField;
    private GridPane damagesPane;
    private List<CheckBox> damagesCheckBox;
    // Buttons:
    private Button selectImageButton;
    private Button openClaimFormButton;
    private Button registerButton;
    
    // Output nodes, Text messages:

    
    
    public CarClaimRegistration() {
        
        // Sets up the mainPane
        mainPane = new GridPane();
        mainPane.setAlignment(Pos.CENTER);
        mainPane.setHgap(10);
        mainPane.setVgap(6);
        // Set background color:
        mainPane.setStyle("-fx-background-color: #E7E7FF;");
        // Set up column constraints. Width in pixels:
        ColumnConstraints col1 = new ColumnConstraints(120);
        ColumnConstraints col2 = new ColumnConstraints(100);
        ColumnConstraints col3 = new ColumnConstraints(40);
        ColumnConstraints col4 = new ColumnConstraints(50);
        ColumnConstraints col5 = new ColumnConstraints(150);
        ColumnConstraints col6 = new ColumnConstraints(150);
        ColumnConstraints col7 = new ColumnConstraints(150);
        // Add these constraints:
        mainPane.getColumnConstraints().addAll(col1, col2, col3, col4, col5, col6, col7);
        
        // Start initializing all nodes that are to be placed in the gridpane:
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
        customerArea.setEditable(false);
        customerArea.setPrefColumnCount(2);
        customerArea.setPrefRowCount(4);
        Label selectInsuranceLabel = new Label("Velg forsikringen denne skademelding går under:");
        selectInsuranceButton = new Button("Velg");
        selectInsuranceMessage = new Text();

        Text insurancesTitle = new Text("Eksisterende forsikringer til denne kunden:");
        insurancesTitle.setId("textTitle");
        insurancesTable = new TableView();
        insurancesTable.setPrefHeight(150);
        insuranceTypeColumn = new TableColumn<>("Forsikring");
        insuranceCoverageColum = new TableColumn<>("Dekning");
        insuranceIdColumn = new TableColumn<>("Forsikringsid");
        insurancesTable.getColumns().addAll(insuranceTypeColumn, insuranceCoverageColum, insuranceIdColumn);
        insurancesTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);    
        
        Label dateHappenedLabel = new Label("Skadedato:");
        dateHappenedPicker = new DatePicker();
        restrictDatePicker();
        Label descriptionLabel = new Label("Beskrivelse av skaden:");
        descriptionTextArea = new TextArea();
        damagesPane = new GridPane();
        Label damagesLabel = new Label("Skade:");
        damagesPane = new GridPane();
        damagesPane.setPrefSize(200, 100);
        Label appraisalLbel = new Label("Takseringsbeløp:");
        appraisalField = new TextField();
        Label selectImageLabel = new Label("Last opp et bilde som beskriver skaden");
        selectImageButton = new Button("Hent bilde");
        fileChooser = new FileChooser();
        Label openClaimFormLabel = new Label("Bilskademelingsskjema");
        openClaimFormButton = new Button("Åpne");
        registerButton = new Button("Registrer");
        
        // Add nodes to mainPane:
        mainPane.add(selectCustomerTitle, 0, 0);
        mainPane.add(customerIdLabel, 0, 1);
        mainPane.add(customerIdField, 1, 1);
        mainPane.add(searchCustomerIdButton, 2, 1);
        
        mainPane.add(personalNumberLabel, 0, 2);
        mainPane.add(personalNumberField, 1, 2);
        mainPane.add(searchPersonalNumberButton, 2, 2);
        
        mainPane.add(resultTitle, 0, 3);
        mainPane.add(customerArea, 0, 4, 3, 5);
        mainPane.add(insurancesTitle, 0, 10);
        mainPane.add(insurancesTable, 0, 11, 3, 5);
        
        // 
        mainPane.add(dateHappenedLabel, 4, 0);
        mainPane.add(dateHappenedPicker, 5, 0);
        mainPane.add(descriptionLabel, 4, 1);
        mainPane.add(descriptionTextArea,4, 2, 3, 4);
        mainPane.add(appraisalLbel, 4, 6);
        mainPane.add(appraisalField, 5, 6);
        mainPane.add(damagesLabel, 4, 7);
        mainPane.add(damagesPane, 5, 7);
        mainPane.add(selectImageLabel, 4, 8);
        mainPane.add(selectImageButton, 5, 8);
        mainPane.add(openClaimFormLabel, 4, 9);
        mainPane.add(openClaimFormButton, 5, 9);
        mainPane.add(registerButton, 4, 10);
      
    }
    
    public void populateDamagesPane(ArrayList<Damage> damages) {
        damagesCheckBox = new ArrayList<CheckBox>();
        int columns = 5;
        for (int i = 0; i < (damages.size() / columns); i++) {
            CheckBox cb = new CheckBox(damages.get(i).toString());
            damagesCheckBox.add(cb);
            for(int j = 0; j < columns; j++) {
                damagesPane.add(cb, j, i);
            }
        }
    }
    
    private void restrictDatePicker() {
        dateHappenedPicker.setValue(LocalDate.now());
        // Sets up a restricton, where only dates before the current date are optional:
        Callback<DatePicker, DateCell> dayCellFactory = dp -> new DateCell()
        {
            @Override
            public void updateItem(LocalDate item, boolean empty)
            {
                super.updateItem(item, empty);
                if(item.isAfter(LocalDate.now()))
                {   // Sets the background color of the invalid dates to a pink/red color:
                    setStyle("-fx-background-color: #ffc0cb;");
                    // Disables them, so they can not be picked:
                    setDisable(true);
                }
            }
        };
        dateHappenedPicker.setDayCellFactory(dayCellFactory);
        dateHappenedPicker.setPromptText("dd/MM/yyyy");
    }
    
    /**
     * @return the customerIdField
     */
    public String getCustomerIdField() {
        return customerIdField.getText();
    }

    /**
     * @return the personalNumberField
     */
    public String getPersonalNumberField() {
        return personalNumberField.getText();
    }


    /**
     * @param customerArea the customerArea to set
     */
    public void setCustomerArea(String customerArea) {
        this.customerArea.setText(customerArea);
    }

    /**
     * 
     * @param insurances 
     */
    public void populateInsurancesTable(List<Insurance> insurances) {
        ObservableList<Insurance> obList = FXCollections.observableArrayList(insurances);
        insurancesTable.setItems(obList);
        if (obList.isEmpty()) {
            System.err.println("Tom liste");
        } else {
            System.err.println("Ikke Tom liste");
        }
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
    
    public Insurance getInsuranceTableValue() {
        return insurancesTable.getSelectionModel().getSelectedItem();
    }
    
    public void setCoverage(String coverage, Damage[] damages) {
        
    }

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
    public void setSelectInsuranceButtonButtonEventHandler(EventHandler<ActionEvent> value) {
        selectInsuranceButton.setOnAction(value);
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
     * @param selectInsuranceMessage the selectInsuranceMessage to set
     */
    public void setSelectInsuranceMessage(Text selectInsuranceMessage) {
        this.selectInsuranceMessage = selectInsuranceMessage;
    }


}
