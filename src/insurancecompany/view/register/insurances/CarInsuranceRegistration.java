/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package insurancecompany.view.register.insurances;

import insurancecompany.misc.coverages.CarInsuranceCoverage;
import insurancecompany.model.insurances.Insurance;
import java.util.List;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
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
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

/**
 *
 * @author André
 * @author Sindre
 */
public class CarInsuranceRegistration {
    
    /** The main pane of this class.*/
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
    // The customerId used in the insurance registration:
    private int selectedCustomerId;
    
    // REGISTER INSURANCE NODES:
    
    // Input nodes, ComboBoxes and TextFields:
    private ComboBox<String> alarmCombo;
    private ComboBox brandCombo;
    private ComboBox coverageCombo;
    private ComboBox drivingLengthCombo;
    private ComboBox excessCombo;
    private ComboBox existingBonusCombo;
    private ComboBox modelCombo;
    private ComboBox parkingConditionCombo;
    private ComboBox yearCombo;
    private ComboBox youngestDriverCombo;
    private TextField personalNumberOwnerField;
    private TextField premiumField;
    private TextField registrationNumberField;
    // Output nodes, Text messages:
    private Text alarmMessage;
    private Text brandMessage;
    private Text coverageMessage;
    private Text drivingLengthMessage;
    private Text excessMessage;
    private Text existingBonusMessage;
    private Text modelMessage;
    private Text parkingConditionMessage;
    private Text personalNumberOwnerMessage;
    private Text registrationNumberMessage;
    private Text yearMessage;
    private Text youngestDriverMessage;
    // Buttons:
    private Button calculateButton;
    private Button registerButton;
    
    
    public CarInsuranceRegistration() {
        
        // Sets up the mainPane:
        mainPane = new GridPane();
        mainPane.setAlignment(Pos.CENTER);
        mainPane.setHgap(10);
        mainPane.setVgap(6);
        // Sets background color:
        mainPane.setStyle("-fx-background-color: #E7E7FF;");
        // Sets up column constraints. Width in pixels:
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
        // Declares and initializes Texts and Labels:
        Label customerIdLabel = new Label("Kundenummer:");
        Label personalNumberLabel = new Label("Personnummer:");
        Text insurancesTitle = new Text("Eksisterende forsikringer til denne kunden:");
        insurancesTitle.setId("textTitle");
        Text resultTitle = new Text("Valgt kunde:");
        resultTitle.setId("textTitle");
        Text selectCustomerTitle = new Text("Velg først en kunde i registeret:");
        selectCustomerTitle.setId("textTitle");
        
        // REGISTER INSURANCE NODES:
        
        // Initializes Input:
        alarmCombo = new ComboBox();
        populateAlarmCombo();
        brandCombo = new ComboBox();
        brandCombo.setEditable(true);
        coverageCombo = new ComboBox();
        populateCoverageCombo();
        drivingLengthCombo = new ComboBox();
        populateDrivingLengthCombo();
        excessCombo = new ComboBox();
        populateExcessCombo();
        existingBonusCombo = new ComboBox();
        populateExistingBonusCombo();
        modelCombo = new ComboBox();
        modelCombo.setEditable(true);
        parkingConditionCombo = new ComboBox();
        populateParkingConditionCombo();
        yearCombo = new ComboBox();
        yearCombo.setEditable(true);
        youngestDriverCombo = new ComboBox();
        populateYoungestDriverCombo();
        personalNumberOwnerField = new TextField();
        premiumField = new TextField();
        premiumField.setEditable(false);
        registrationNumberField = new TextField();
        // Initializes Output:
        alarmMessage = new Text();
        brandMessage = new Text(); 
        coverageMessage = new Text();
        drivingLengthMessage = new Text();
        excessMessage = new Text();
        existingBonusMessage = new Text();
        modelMessage = new Text();
        parkingConditionMessage = new Text();
        personalNumberOwnerMessage = new Text();
        registrationNumberMessage = new Text();
        yearMessage = new Text();
        youngestDriverMessage = new Text();
        // Initializes Buttons:
        calculateButton = new Button("Regn ut");
        registerButton = new Button("Registrer");
        // Declares and initializes Texts and Labels:
        customerSelectedMessage = new Text();
        Text insuranceOptionsTitle = new Text("Betingelser:");
        insuranceOptionsTitle.setId("textTitle");
        Text carTitle = new Text("Bil:");
        carTitle.setId("textTitle");
        Label alarmLabel = new Label("FG-godkjent alarm:");
        Label brandLabel = new Label("Merke:");
        Label coverageLabel = new Label("Dekning:");
        Label drivingLengthLabel = new Label("Kjørelengde:");
        Label excessLabel = new Label("Egenandel:");
        Label existingBonusLabel = new Label("Eksisterende bonussats:");
        Label modelLabel = new Label("Modell:");
        Label parkingConditionLabel = new Label("Parkeringsforhold:");
        Label personalNumberOwnerLabel = new Label("Eierens personnummer:");
        Label premiumLabel = new Label("Beregnet forsikringspremie:");
        Label registrationNumberLabel = new Label("Registreringsnummer:");
        Label yearLabel = new Label("År:");     
        Label youngestDriverLabel = new Label("Yngste fører:");
        
        
        // Adds nodes to mainPane:
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
        
        mainPane.add(insuranceOptionsTitle, 4, 0);
        mainPane.add(existingBonusLabel, 4, 1);
        mainPane.add(existingBonusCombo, 5, 1);
        mainPane.add(existingBonusMessage, 6, 1);
        mainPane.add(drivingLengthLabel, 4, 2);
        mainPane.add(drivingLengthCombo, 5, 2);
        mainPane.add(drivingLengthMessage, 6, 2);
        mainPane.add(excessLabel, 4, 3);
        mainPane.add(excessCombo, 5, 3);
        mainPane.add(excessMessage, 6, 3);
        mainPane.add(youngestDriverLabel, 4, 4);
        mainPane.add(youngestDriverCombo, 5, 4);
        mainPane.add(youngestDriverMessage, 6, 4);
        mainPane.add(coverageLabel, 4, 5);
        mainPane.add(coverageCombo, 5, 5);
        mainPane.add(coverageMessage, 6, 5);
        mainPane.add(parkingConditionLabel, 4, 6);
        mainPane.add(parkingConditionCombo, 5, 6);
        mainPane.add(parkingConditionMessage, 6, 6);

        mainPane.add(carTitle, 4, 7);
        mainPane.add(personalNumberOwnerLabel, 4, 8);
        mainPane.add(personalNumberOwnerField, 5, 8);
        mainPane.add(personalNumberOwnerMessage, 6, 8);
        mainPane.add(registrationNumberLabel, 4, 9);
        mainPane.add(registrationNumberField, 5, 9);
        mainPane.add(registrationNumberMessage, 6, 9);
        mainPane.add(brandLabel, 4, 10);
        mainPane.add(brandCombo, 5, 10);
        mainPane.add(brandMessage, 6, 10);
        mainPane.add(yearLabel, 4, 11);
        mainPane.add(yearCombo, 5, 11);
        mainPane.add(yearMessage, 6, 11);
        mainPane.add(modelLabel, 4, 12);
        mainPane.add(modelCombo, 5, 12);
        mainPane.add(modelMessage, 6, 12);
        mainPane.add(alarmLabel, 4, 13);
        mainPane.add(alarmCombo, 5, 13);
        mainPane.add(alarmMessage, 6, 13);
        mainPane.add(premiumLabel, 4, 14);
        mainPane.add(premiumField, 5, 14);
        mainPane.add(calculateButton, 6, 14);
        mainPane.add(registerButton, 5, 15);
      
    }
    
    // POPULATE METHODS:
    
    private void populateAlarmCombo() {
        ObservableList<String> alarm = FXCollections.observableArrayList();  
        alarm.addAll("Ja", "Nei");
        alarmCombo.getItems().setAll(alarm);
        alarmCombo.setPrefWidth(150);
    }
    
    public void populateBrandCombo(List cars) {
        ObservableList obList = FXCollections.observableList(cars);
        // Reset the previous value(shown value)
        brandCombo.valueProperty().set(null);
        brandCombo.getItems().setAll(obList);
        brandCombo.setPrefWidth(150);
    }
    
    private void populateCoverageCombo() {
        ObservableList<CarInsuranceCoverage> obList;
        obList = FXCollections.observableArrayList(CarInsuranceCoverage.values()); 
        coverageCombo.getItems().setAll(obList);
        coverageCombo.setPrefWidth(150);
    }
    
    private void populateDrivingLengthCombo() {
        ObservableList<String> drivingLength = FXCollections.observableArrayList();
        drivingLength.addAll("6000", "8000", "10000", "15000", "14000", 
                "16000", "18000", "20000", "25000", "30000", "40000", "50000", 
                "Ubegrenset");
        drivingLengthCombo.getItems().setAll(drivingLength);
        drivingLengthCombo.setPrefWidth(150);
    }
    
    private void populateExcessCombo() {
        ObservableList<String> excess = FXCollections.observableArrayList();
        excess.addAll("4000", "6000", "8000", "10000", "15000", "14000", 
                "16000", "18000", "20000", "25000", "30000");
        excessCombo.getItems().setAll(excess);
        excessCombo.setPrefWidth(150);
    }
    
    private void populateExistingBonusCombo() {
        ObservableList<String> bonuses = FXCollections.observableArrayList();
        bonuses.addAll("-30", "-20", "-10", "0", "10", "20", "30", "40", 
                "50", "60", "70", "75");
        existingBonusCombo.getItems().setAll(bonuses);
        existingBonusCombo.setPrefWidth(150);
    }
    
    public void populateModelCombo(List models) {
        ObservableList obList = FXCollections.observableList(models);
        // Reset the previous value(shown value)
        modelCombo.valueProperty().set(null);
        modelCombo.getItems().setAll(obList);
        modelCombo.setPrefWidth(150);
    }
    
    private void populateParkingConditionCombo() {
        ObservableList<String> parkingCondition = FXCollections.observableArrayList(); 
        parkingCondition.addAll("Har garasje", "Har ikke garasje");
        parkingConditionCombo.getItems().setAll(parkingCondition);
        parkingConditionCombo.setPrefWidth(150);
    }
    
    public void populateYearCombo(List years) {
        ObservableList obList = FXCollections.observableList(years);
        // Reset the previous value(shown value)
        yearCombo.valueProperty().set(null);
        yearCombo.getItems().setAll(obList);
        yearCombo.setPrefWidth(150);
    }
    
    private void populateYoungestDriverCombo() {
        ObservableList<String> youngestDriver = FXCollections.observableArrayList(); 
        youngestDriver.addAll("Under 25", "Over 25");
        youngestDriverCombo.getItems().setAll(youngestDriver);
        youngestDriverCombo.setPrefWidth(150);
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
    
    // SET COMBOLISTENER METHODS:
    
    public void setBrandComboListener(ChangeListener listener) {
        brandCombo.valueProperty().addListener(listener);
    }
    
    public void setYearComboListener(ChangeListener listener) {
        yearCombo.valueProperty().addListener(listener);
    }
    
    // SET EVENTHANDLER METHODS:

    public void setCalculateButtonEventHandler(EventHandler<ActionEvent> value) {
        calculateButton.setOnAction(value);
    }

    public void setRegisterButtonEventHandler(EventHandler<ActionEvent> value) {
        registerButton.setOnAction(value);
    }
    
    public void setSearchCustomerIdButtonEventHandler(EventHandler<ActionEvent> value) {
        searchCustomerIdButton.setOnAction(value);
    }

    public void setSearchPersonalNumberButtonEventHandler(EventHandler<ActionEvent> value) {
        searchPersonalNumberButton.setOnAction(value);
    }
    
    // CLEAR MESSAGES METHOD:
    
    public void clearMessages() {
        alarmMessage.setText("");
        brandMessage.setText("");
        coverageMessage.setText("");
        drivingLengthMessage.setText("");
        excessMessage.setText("");
        existingBonusMessage.setText("");
        modelMessage.setText("");
        parkingConditionMessage.setText("");
        personalNumberOwnerMessage.setText("");
        registrationNumberMessage.setText("");
        yearMessage.setText("");
        youngestDriverMessage.setText("");
    }
    
    // GET METHODS:
    
    /** @return The main pane of this class. */
    public GridPane getMainPane() {
        return mainPane;
    }
    
    /** @return The value of alarmCombo. */
    public String getAlarm() {
        return alarmCombo.getValue() == null ? "" : alarmCombo.getValue();
    }

    /** @return The value of brandCombo. */
    public String getBrand() {
        return brandCombo.getValue() == null ? "" : brandCombo.getValue().toString();
    }
    
    
    ///
    ///
    /// SINDRE HAR KOMMET SÅ LANGT MED Å OPPDATERE DENNE KLASSEN. =D
    ///
    ///
    
    public Object getBrandComboValue() {
        return brandCombo.getValue();
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
     * @return the existingBonusCombo
     */
    public String getExistingBonusComboValue() {
        return existingBonusCombo.getValue() == null ? null : existingBonusCombo.getValue().toString();
    }

    /**
     * @return the drivingLengthCombo
     */
    public String getDrivingLengthCombo() {
        return drivingLengthCombo.getValue() == null ? null : drivingLengthCombo.getValue().toString();
    }

    /**
     * @return the excessCombo
     */
    public String getExcessCombo() {
        return excessCombo.getValue() == null ? null : excessCombo.getValue().toString();
    }

    /**
     * @return the yearCombo
     */
    public String getYearCombo() {
        return yearCombo.getValue() == null ? null : yearCombo.getValue().toString();
    }

    /**
     * @return the modelCombo
     */
    public String getModelCombo() {
        return modelCombo.getValue() == null ? null : modelCombo.getValue().toString();
    }

    /**
     * @return the youngestDriverCombo
     */
    public String getYoungestDriverCombo() {
        return youngestDriverCombo.getValue() == null ? null : youngestDriverCombo.getValue().toString();
    }

    /**
     * @return the coverageCombo
     */
    public CarInsuranceCoverage getCoverageCombo() {
        if (coverageCombo.getValue() instanceof CarInsuranceCoverage) {
            // Casts the combobox value to CarInsuranceCoverage and return this value
            CarInsuranceCoverage coverage = (CarInsuranceCoverage) coverageCombo.getValue();
            return coverage;
            // If for instance no value is selected, the value will not equal a CarInsuranceCoverage, 
            //in this case return null:
        } else return null; 
    }

    /**
     * @return the parkingConditionCombo
     */
    public String getParkingConditionCombo() {
        return parkingConditionCombo.getValue() == null ? null : parkingConditionCombo.getValue().toString();
    }

    /**
     * @return the registrationNumberField
     */
    public String getRegistrationNumberField() {
        return registrationNumberField.getText();
    }

    /**
     * @return the personalNumberOwnerField
     */
    public String getPersonalNumberOwnerField() {
        return personalNumberOwnerField.getText();
    }

    /**
     * @return the premiumField
     */
    public String getPremiumField() {
        return premiumField.getText();
    }

    /**
     * @param customerArea the customerArea to set
     */
    public void setCustomerArea(String customerArea) {
        this.customerArea.setText(customerArea);
    }

    /**
     * @param existingBonusMessage the existingBonusMessage to set
     */
    public void setExistingBonusMessage(String existingBonusMessage) {
        this.existingBonusMessage.setFill(Color.FIREBRICK);
        this.existingBonusMessage.setText(existingBonusMessage);
    }

    /**
     * @param drivingLengthMessage the drivingLengthMessage to set
     */
    public void setDrivingLengthMessage(String drivingLengthMessage) {
        this.drivingLengthMessage.setFill(Color.FIREBRICK);
        this.drivingLengthMessage.setText(drivingLengthMessage);
    }

    /**
     * @param excessMessage the excessMessage to set
     */
    public void setExcessMessage(String excessMessage) {
        this.excessMessage.setFill(Color.FIREBRICK);
        this.excessMessage.setText(excessMessage);
    }

    /**
     * @param brandMessage the brandMessage to set
     */
    public void setBrandMessage(String brandMessage) {
        this.brandMessage.setFill(Color.FIREBRICK);
        this.brandMessage.setText(brandMessage);
    }

    /**
     * @param yearMessage the yearMessage to set
     */
    public void setYearMessage(String yearMessage) {
        this.yearMessage.setFill(Color.FIREBRICK);
        this.yearMessage.setText(yearMessage);
    }

    /**
     * @param modelMessage the modelMessage to set
     */
    public void setModelMessage(String modelMessage) {
        this.modelMessage.setFill(Color.FIREBRICK);
        this.modelMessage.setText(modelMessage);
    }

    /**
     * @param youngestDriverMessage the youngestDriverMessage to set
     */
    public void setYoungestDriverMessage(String youngestDriverMessage) {
        this.youngestDriverMessage.setFill(Color.FIREBRICK);
        this.youngestDriverMessage.setText(youngestDriverMessage);
    }

    /**
     * @param alarmMessage the alarmMessage to set
     */
    public void setAlarmMessage(String alarmMessage) {
        this.alarmMessage.setFill(Color.FIREBRICK);
        this.alarmMessage.setText(alarmMessage);
    }

    /**
     * @param coverageMessage the coverageMessage to set
     */
    public void setCoverageMessage(String coverageMessage) {
        this.coverageMessage.setFill(Color.FIREBRICK);
        this.coverageMessage.setText(coverageMessage);
    }

    /**
     * @param parkingConditionMessage the parkingConditionMessage to set
     */
    public void setParkingConditionMessage(String parkingConditionMessage) {
        this.parkingConditionMessage.setFill(Color.FIREBRICK);
        this.parkingConditionMessage.setText(parkingConditionMessage);
    }

    /**
     * @param registrationNumberMessage the registrationNumberMessage to set
     */
    public void setRegistrationNumberMessage(String registrationNumberMessage) {
        this.registrationNumberMessage.setFill(Color.FIREBRICK);
        this.registrationNumberMessage.setText(registrationNumberMessage);
    }

    /**
     * @param personalNumberOwnerMessage the personalNumberOwnerMessage to set
     */
    public void setPersonalNumberOwnerMessage(String personalNumberOwnerMessage) {
        this.personalNumberOwnerMessage.setFill(Color.FIREBRICK);
        this.personalNumberOwnerMessage.setText(personalNumberOwnerMessage);
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
     * @param customerSelectedMessage the customerSelectedMessage to set
     */
    public void setCustomerSelectedMessage(String customerSelectedMessage) {
        this.customerSelectedMessage.setText(customerSelectedMessage);
    }
}
