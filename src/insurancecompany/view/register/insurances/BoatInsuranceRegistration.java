/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package insurancecompany.view.register.insurances;

import insurancecompany.misc.coverages.BoatInsuranceCoverage;
import insurancecompany.model.insurances.Insurance;
import java.util.List;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
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
 *
 * @author Sindre
 */
public class BoatInsuranceRegistration {
    
    /** The main pane of this class. */
    private GridPane mainPane;
    
    // SEARCH FOR CUSTOMER NODES:
    
    // Input nodes, TextFields:
    private TextField customerIdField;
    private TextField personalNumberField;
    // Output nodes, TextArea, TableView and Text:
    private TextArea customerArea;
    private TableView<Insurance> insurancesTable;
    private TableColumn<Insurance, String> insuranceTypeColumn;
    private TableColumn<Insurance, String> insuranceCoverageColum;
    private TableColumn<Insurance, Integer> insuranceIdColumn;
    private Text customerSelectedMessage;
    // Buttons:
    private Button searchCustomerIdButton;
    private Button searchPersonalNumberButton;
    private Button selectCustomerButton;
    // ints used to keep track of searched and selected customer id:
    private int tempCustomerId;
    private int selectedCustomerId;
    
    // REGISTER INSURANCE NODES:
    
    // Input nodes, ComboBoxes and TextFields:
    private ComboBox<String> alarmCombo;
    private ComboBox<BoatInsuranceCoverage> coverageCombo;
    private ComboBox<String> excessCombo;
    private TextField brandField;
    private TextField engineEffectField;
    private TextField engineTypeField;
    private TextField lengthField;
    private TextField modelField;
    private TextField ownerPersonalNumberField;
    private TextField premiumField;
    private TextField registrationNumberField;
    private TextField registrationYearField;
    // Output nodes, Text messages:
    private Text alarmMessage;
    private Text brandMessage;
    private Text coverageMessage;
    private Text engineEffectMessage;
    private Text engineTypeMessage;
    private Text excessMessage;
    private Text lengthMessage;
    private Text modelMessage;
    private Text ownerPersonalNumberMessage;
    private Text registrationNumberMessage;
    private Text registrationYearMessage;
    // Buttons:
    private Button calculateButton;
    private Button registerButton;
    
    public BoatInsuranceRegistration() {
        
        // Sets up the mainPane:
        mainPane = new GridPane();
        mainPane.setAlignment(Pos.CENTER);
        mainPane.setHgap(10);
        mainPane.setVgap(6);
        // Sets background color:
        mainPane.setStyle("-fx-background-color: #E7E7FF;");
        // Set up column constraints. Width in pixels:
        ColumnConstraints col1 = new ColumnConstraints(120);
        ColumnConstraints col2 = new ColumnConstraints(100);
        ColumnConstraints col3 = new ColumnConstraints(40);
        ColumnConstraints col4 = new ColumnConstraints(50);
        ColumnConstraints col5 = new ColumnConstraints(150);
        ColumnConstraints col6 = new ColumnConstraints(150);
        ColumnConstraints col7 = new ColumnConstraints(150);
        // Adds these constraints:
        mainPane.getColumnConstraints().addAll(col1, col2, col3, col4, col5, col6, col7);
        
        // SEARCH FOR CUSTOMER NODES:
        
        // Initializes Input:
        customerIdField = new TextField();
        personalNumberField = new TextField();
        // Initializes Output:
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
        // Initializes Buttons:
        searchCustomerIdButton = new Button("Søk");
        searchPersonalNumberButton = new Button("Søk");
        selectCustomerButton = new Button("Velg denne kunden");
        // Declares and initializes Texts and Labels:
        Label customerIdLabel = new Label("Kundenummer:");
        Label personalNumberLabel = new Label("Personnummer:");
        Text insurancesTitle = new Text("Eksisterende forsikringer til denne kunden:");
        insurancesTitle.setId("textTitle");
        Text resultTitle = new Text("Søkeresultat:");
        resultTitle.setId("textTitle");
        Text selectCustomerTitle = new Text("Velg først en kunde i registeret:");
        selectCustomerTitle.setId("textTitle");
        
        // REGISTER INSURANCE NODES:
        
        // Initializes Input:
        alarmCombo = new ComboBox<>();
        populateAlarmCombo();
        coverageCombo = new ComboBox<>();
        populateCoverageCombo();
        excessCombo = new ComboBox<>();
        populateExcessCombo();
        brandField = new TextField();
        engineEffectField = new TextField();
        engineTypeField = new TextField();
        lengthField = new TextField();
        modelField = new TextField();
        ownerPersonalNumberField = new TextField();
        premiumField = new TextField();
        premiumField.setEditable(false);
        registrationNumberField = new TextField();
        registrationYearField = new TextField();
        // Initializes Output:
        alarmMessage = new Text();
        brandMessage = new Text();
        coverageMessage = new Text();
        engineEffectMessage = new Text();
        engineTypeMessage = new Text();
        excessMessage = new Text();
        lengthMessage = new Text();
        modelMessage = new Text();
        ownerPersonalNumberMessage = new Text();
        registrationNumberMessage = new Text();
        registrationYearMessage = new Text();
        // Initializes Buttons:
        calculateButton = new Button("Regn ut");
        registerButton = new Button("Registrer");
        // Declares and initializes Texts and Labels:
        customerSelectedMessage = new Text();
        Text insuranceOptionsTitle = new Text("Betingelser:");
        insuranceOptionsTitle.setId("textTitle");
        Text boatTitle = new Text("Bil:");
        boatTitle.setId("textTitle");
        Label coverageLabel = new Label("Dekning:");
        Label excessLabel = new Label("Egenandel:");
        Label ownerPersonalNumberLabel = new Label("Eierens personnummer:");
        Label registrationNumberLabel = new Label("Registreringsnummer:");
        Label brandLabel = new Label("Merke:");
        Label modelLabel = new Label("Modell:");
        Label registrationYearLabel = new Label("År:");
        Label engineEffectLabel = new Label("Hestekrefter:");
        Label engineTypeLabel = new Label("Motortype:");
        Label lengthLabel = new Label("Lengde i fot:");
        Label alarmLabel = new Label("FG-godkjent alarm:");
        Label premiumLabel = new Label("Beregnet forsikringspremie:");
        
        // Adds nodes to mainPane
        mainPane.add(selectCustomerTitle, 0, 0);
        mainPane.add(customerIdLabel, 0, 1);
        mainPane.add(customerIdField, 1, 1);
        mainPane.add(searchCustomerIdButton, 2, 1);
        
        mainPane.add(personalNumberLabel, 0, 2);
        mainPane.add(personalNumberField, 1, 2);
        mainPane.add(searchPersonalNumberButton, 2, 2);
        
        mainPane.add(resultTitle, 0, 3);
        mainPane.add(customerArea, 0, 4, 3, 5);
        mainPane.add(selectCustomerButton, 0, 9);
        mainPane.add(customerSelectedMessage, 1, 9);
        mainPane.add(insurancesTitle, 0, 10);
        mainPane.add(insurancesTable, 0, 11, 3, 5);
        
        mainPane.add(insuranceOptionsTitle, 4, 0);
        mainPane.add(coverageLabel, 4, 1);
        mainPane.add(coverageCombo, 5, 1);
        mainPane.add(coverageMessage, 6, 1);
        mainPane.add(excessLabel, 4, 2);
        mainPane.add(excessCombo, 5, 2);
        mainPane.add(excessMessage, 6, 2);
        
        mainPane.add(boatTitle, 4, 3);
        mainPane.add(ownerPersonalNumberLabel, 4, 4);
        mainPane.add(ownerPersonalNumberField, 5, 4);
        mainPane.add(ownerPersonalNumberMessage, 6, 4);
        mainPane.add(registrationNumberLabel, 4, 5);
        mainPane.add(registrationNumberField, 5, 5);
        mainPane.add(registrationNumberMessage, 6, 5);
        mainPane.add(brandLabel, 4, 6);
        mainPane.add(brandField, 5, 6);
        mainPane.add(brandMessage, 6, 6);
        mainPane.add(modelLabel, 4, 7);
        mainPane.add(modelField, 5, 7);
        mainPane.add(modelMessage, 6, 7);
        mainPane.add(registrationYearLabel, 4, 8);
        mainPane.add(registrationYearField, 5, 8);
        mainPane.add(registrationYearMessage, 6, 8);
        mainPane.add(engineEffectLabel, 4, 9);
        mainPane.add(engineEffectField, 5, 9);
        mainPane.add(engineEffectMessage, 6, 9);
        mainPane.add(engineTypeLabel, 4, 10);
        mainPane.add(engineTypeField, 5, 10);
        mainPane.add(engineTypeMessage, 6, 10);
        mainPane.add(lengthLabel, 4, 11);
        mainPane.add(lengthField, 5, 11);
        mainPane.add(lengthMessage, 6, 11);
        mainPane.add(alarmLabel, 4, 12);
        mainPane.add(alarmCombo, 5, 12);
        mainPane.add(alarmMessage, 6, 12);
        mainPane.add(premiumLabel, 4, 13);
        mainPane.add(premiumField, 5, 13);
        mainPane.add(calculateButton, 6, 13);
        mainPane.add(registerButton, 5, 14);
    }
    
    // POPULATE METHODS:
    
    private void populateAlarmCombo() {
        ObservableList<String> alarm = FXCollections.observableArrayList();  
        alarm.addAll("Ja", "Nei");
        alarmCombo.getItems().setAll(alarm);
        alarmCombo.setPrefWidth(150);
    }
    
    private void populateCoverageCombo() {
        ObservableList<BoatInsuranceCoverage> obList;
        obList = FXCollections.observableArrayList(BoatInsuranceCoverage.values()); 
        coverageCombo.getItems().setAll(obList);
        coverageCombo.setPrefWidth(150);
    }
    
    private void populateExcessCombo() {
        ObservableList<String> excess = FXCollections.observableArrayList();
        excess.addAll("4000", "6000", "8000", "10000", "15000", "14000", 
                "16000", "18000", "20000", "25000", "30000");
        excessCombo.getItems().setAll(excess);
        excessCombo.setPrefWidth(150);
    }
    
    /**
     * 
     * @param insurances 
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
    
    // SET EVENTHANDLER METHODS:
    
    public void setCalculateButtonEventHandler(EventHandler<ActionEvent> value) {
        calculateButton.setOnAction(value);
    }

    public void setRegisterButtonEventHandler(EventHandler<ActionEvent> value) {
        registerButton.setOnAction(value);
    }
    
    // GET METHODS:
    
    public GridPane getMainPane() {
        return mainPane;
    }
    
    /** @return the value of alarmCombo */
    public String getAlarm() {
        return alarmCombo.getValue() == null ? "" : alarmCombo.getValue();
    }

    /**
     * @return the brandField
     */
    public String getBrand() {
        return brandField.getText();
    }

    /**
     * @return the coverageComboBox
     */
    public BoatInsuranceCoverage getCoverage() {
        return coverageCombo.getValue();
    }

    /**
     * @return the customerIdField
     */
    public String getCustomerId() {
        return customerIdField.getText();
    }

    /**
     * @return the engineEffectField
     */
    public String getEngineEffect() {
        return engineEffectField.getText();
    }

    /**
     * @return the engineTypeField
     */
    public String getEngineType() {
        return engineTypeField.getText();
    }

    /**
     * @return the excessField
     */
    public String getExcess() {
        return excessCombo.getValue() == null ? "" : excessCombo.getValue();
    }

    /**
     * @return the lengthField
     */
    public String getLength() {
        return lengthField.getText();
    }

    /**
     * @return the modelField
     */
    public String getModel() {
        return modelField.getText();
    }

    /**
     * @return the ownerPersonalNumberField
     */
    public String getOwnerPersonalNumber() {
        return ownerPersonalNumberField.getText();
    }

    /**
     * @return the customerPersonalNumberField
     */
    public String getPersonalNumber() {
        return personalNumberField.getText();
    }

    /**
     * @return the premiumField
     */
    public String getPremium() {
        return premiumField.getText();
    }

    /**
     * @return the registrationNumberField
     */
    public String getRegistrationNumber() {
        return registrationNumberField.getText();
    }

    /**
     * @return the registrationYearField
     */
    public String getRegistrationYear() {
        return registrationYearField.getText();
    }
    
    // SET METHODS:

    /**
     * @param alarmMessage the alarmMessage to set
     */
    public void setAlarmMessage(String alarmMessage) {
        this.alarmMessage.setFill(Color.FIREBRICK);
        this.alarmMessage.setText(alarmMessage);
    }

    /**
     * @param brandMessage the brandMessage to set
     */
    public void setBrandMessage(String brandMessage) {
        this.brandMessage.setFill(Color.FIREBRICK);
        this.brandMessage.setText(brandMessage);
    }

    /**
     * @param coverageMessage the coverageMessage to set
     */
    public void setCoverageMessage(String coverageMessage) {
        this.coverageMessage.setFill(Color.FIREBRICK);
        this.coverageMessage.setText(coverageMessage);
    }

    /**
     * @param engineEffectMessage the engineEffectMessage to set
     */
    public void setEngineEffectMessage(String engineEffectMessage) {
        this.engineEffectMessage.setFill(Color.FIREBRICK);
        this.engineEffectMessage.setText(engineEffectMessage);
    }

    /**
     * @param engineTypeMessage the engineTypeMessage to set
     */
    public void setEngineTypeMessage(String engineTypeMessage) {
        this.engineTypeMessage.setFill(Color.FIREBRICK);
        this.engineTypeMessage.setText(engineTypeMessage);
    }

    /**
     * @param excessMessage the excessMessage to set
     */
    public void setExcessMessage(String excessMessage) {
        this.excessMessage.setFill(Color.FIREBRICK);
        this.excessMessage.setText(excessMessage);
    }

    /**
     * @param lengthMessage the lengthMessage to set
     */
    public void setLengthMessage(String lengthMessage) {
        this.lengthMessage.setFill(Color.FIREBRICK);
        this.lengthMessage.setText(lengthMessage);
    }

    /**
     * @param modelMessage the modelMessage to set
     */
    public void setModelMessage(String modelMessage) {
        this.modelMessage.setFill(Color.FIREBRICK);
        this.modelMessage.setText(modelMessage);
    }

    /**
     * @param ownerPersonalNumberMessage the ownerPersonalNumberMessage to set
     */
    public void setOwnerPersonalNumberMessage(String ownerPersonalNumberMessage) {
        this.ownerPersonalNumberMessage.setFill(Color.FIREBRICK);
        this.ownerPersonalNumberMessage.setText(ownerPersonalNumberMessage);
    }

    /**
     * @param registrationNumberMessage the registrationNumberMessage to set
     */
    public void setRegistrationNumberMessage(String registrationNumberMessage) {
        this.registrationNumberMessage.setFill(Color.FIREBRICK);
        this.registrationNumberMessage.setText(registrationNumberMessage);
    }

    /**
     * @param registrationYearMessage the registrationYearMessage to set
     */
    public void setRegistrationYearMessage(String registrationYearMessage) {
        this.registrationYearMessage.setFill(Color.FIREBRICK);
        this.registrationYearMessage.setText(registrationYearMessage);
    }
}