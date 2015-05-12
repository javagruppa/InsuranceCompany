/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package insurancecompany.view.register.insurances;

import insurancecompany.misc.coverages.HomeInsuranceCoverage;
import insurancecompany.misc.hometypes.HomeType;
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
public class HomeInsuranceRegistration {
    
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
    private ComboBox<HomeInsuranceCoverage> coverageCombo;
    private ComboBox<String> excessCombo;
    private ComboBox<HomeType> typeCombo;
    private TextField areaField;
    private TextField buildingAmountField;
    private TextField cityField;
    private TextField contentAmountField;
    private TextField materialField;
    private TextField premiumField;
    private TextField streetField;
    private TextField yearField;
    private TextField zipCodeField;
    // Output nodes, Text messages:
    private Text areaMessage;
    private Text buildingAmountMessage;
    private Text cityMessage;
    private Text coverageMessage;
    private Text contentAmountMessage;
    private Text excessMessage;
    private Text materialMessage;
    private Text streetMessage;
    private Text typeMessage;
    private Text yearMessage;
    private Text zipCodeMessage;
    // Buttons:
    private Button calculateButton;
    private Button registerButton;
    
    public HomeInsuranceRegistration() {
        
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
        coverageCombo = new ComboBox<>();
        populateCoverageCombo();
        excessCombo = new ComboBox<>();
        populateExcessCombo();
        typeCombo = new ComboBox<>();
        populateTypeCombo();
        areaField = new TextField();
        buildingAmountField = new TextField();
        cityField = new TextField();
        contentAmountField = new TextField();
        materialField = new TextField();
        premiumField = new TextField();
        streetField = new TextField();
        yearField = new TextField();
        zipCodeField = new TextField();
        // Initializes Output:
        areaMessage = new Text();
        buildingAmountMessage = new Text();
        cityMessage = new Text();
        contentAmountMessage = new Text();
        coverageMessage = new Text();
        excessMessage = new Text();
        materialMessage = new Text();
        streetMessage = new Text();
        typeMessage = new Text();
        yearMessage = new Text();
        zipCodeMessage = new Text();
        // Initializes Buttons:
        calculateButton = new Button("Regn ut");
        registerButton = new Button("Registrer");
        // Declares and initializes Texts and Labels:
        customerSelectedMessage = new Text();
        Text insuranceOptionsTitle = new Text("Betingelser:");
        insuranceOptionsTitle.setId("textTitle");
        Text houseTitle = new Text("Hus:");
        houseTitle.setId("textTitle");
        Text addressTitle = new Text("Adresse:");
        addressTitle.setId("textTitle");
        Label coverageLabel = new Label("Dekning:");
        Label excessLabel = new Label("Egenandel:");
        Label buildingAmountLabel = new Label("Forsikringsbeløp for bygning:");
        Label contentAmountLabel = new Label("Forsikringsbeløp for innbo:");
        Label areaLabel = new Label("Areal:");
        Label yearLabel = new Label("Byggeår:");
        Label materialLabel = new Label("Materiale:");
        Label typeLabel = new Label("Type:");
        Label streetLabel = new Label("Gate:");
        Label zipCodeLabel = new Label("Postboks:");
        Label cityLabel = new Label("By:");
        Label premiumLabel = new Label("Beregnet forsikringspremie:");
        
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
        mainPane.add(buildingAmountLabel, 4, 3);
        mainPane.add(buildingAmountField, 5, 3);
        mainPane.add(buildingAmountMessage, 6, 3);
        mainPane.add(contentAmountLabel, 4, 4);
        mainPane.add(contentAmountField, 5, 4);
        mainPane.add(contentAmountMessage, 6, 4);
        
        mainPane.add(houseTitle, 4, 5);
        mainPane.add(areaLabel, 4, 6);
        mainPane.add(areaField, 5, 6);
        mainPane.add(areaMessage, 6, 6);
        mainPane.add(yearLabel, 4, 7);
        mainPane.add(yearField, 5, 7);
        mainPane.add(yearMessage, 6, 7);
        mainPane.add(materialLabel, 4, 8);
        mainPane.add(materialField, 5, 8);
        mainPane.add(materialMessage, 6, 8);
        mainPane.add(typeLabel, 4, 9);
        mainPane.add(typeCombo, 5, 9);
        mainPane.add(typeMessage, 6, 9);
        
        mainPane.add(addressTitle, 4, 10);
        mainPane.add(streetLabel, 4, 11);
        mainPane.add(streetField, 5, 11);
        mainPane.add(streetMessage, 6, 11);
        mainPane.add(zipCodeLabel, 4, 12);
        mainPane.add(zipCodeField, 5, 12);
        mainPane.add(zipCodeMessage, 6, 12);
        mainPane.add(cityLabel, 4, 13);
        mainPane.add(cityField, 5, 13);
        mainPane.add(cityMessage, 6, 13);
        mainPane.add(premiumLabel, 4, 14);
        mainPane.add(premiumField, 5, 14);
        mainPane.add(calculateButton, 6, 14);
        mainPane.add(registerButton, 5, 15);
    }
    
    // POPULATE METHODS:
    
    private void populateCoverageCombo() {
        ObservableList<HomeInsuranceCoverage> obList;
        obList = FXCollections.observableArrayList(HomeInsuranceCoverage.values()); 
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
    
    private void populateTypeCombo() {
        ObservableList<HomeType> obList;
        obList = FXCollections.observableArrayList(HomeType.values()); 
        typeCombo.getItems().setAll(obList);
        typeCombo.setPrefWidth(150);
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
    
    /** @return The main pane of this class. */
    public GridPane getMainPane() {
        return mainPane;
    }

    /** @return The value of areaField. */
    public String getArea() {
        return areaField.getText();
    }

    /** @return The value of buildingAmountField. */
    public String getBuildingAmount() {
        return buildingAmountField.getText();
    }

    /** @return The value of cityField. */
    public String getCity() {
        return cityField.getText();
    }

    /** @return The value of contentAmountField. */
    public String getContentAmount() {
        return contentAmountField.getText();
    }
    
    /** @return The value of coverageCombo. */
    public HomeInsuranceCoverage getCoverage() {
        if (coverageCombo.getValue() instanceof HomeInsuranceCoverage) {
            // Casts the ComboBox value to HomeInsuranceCoverage and returns this value.
            HomeInsuranceCoverage coverage = (HomeInsuranceCoverage) coverageCombo.getValue();
            return coverage;
            // If for instance no value is selected, the value will not equal a HomeInsuranceCoverage, in this case return null.
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

    /** @return The value of materialField. */
    public String getMaterial() {
        return materialField.getText();
    }
    
    /** @return The value of personalNumberField. */
    public String getPersonalNumber() {
        return personalNumberField.getText();
    }

    /** @return The value of premiumField. */
    public String getPremium() {
        return premiumField.getText();
    }

    /** @return The value of streetField. */
    public String getStreet() {
        return streetField.getText();
    }

    /** @return The value of typeCombo. */
    public HomeType getType() {
        if (typeCombo.getValue() instanceof HomeType) {
            // Casts the ComboBox value to HomeType and returns this value.
            HomeType type = (HomeType) typeCombo.getValue();
            return type;
            // If for instance no value is selected, the value will not equal a HomeType, in this case return null.
        } else return null; 
    }

    /** @return The value of yearField. */
    public String getYear() {
        return yearField.getText();
    }

    /** @return The value of zipCodeField. */
    public String getZipCode() {
        return zipCodeField.getText();
    }
    
    // SET METHODS:

    /** @param message The message to set. */
    public void setAreaMessage(String message) {
        this.areaMessage.setFill(Color.FIREBRICK);
        this.areaMessage.setText(message);
    }

    /** @param message The message to set. */
    public void setBuildingAmountMessage(String message) {
        this.buildingAmountMessage.setFill(Color.FIREBRICK);
        this.buildingAmountMessage.setText(message);
    }

    /** @param message The message to set. */
    public void setCityMessage(String message) {
        this.cityMessage.setFill(Color.FIREBRICK);
        this.cityMessage.setText(message);
    }

    /** @param message The message to set. */
    public void setContentAmountMessage(String message) {
        this.contentAmountMessage.setFill(Color.FIREBRICK);
        this.contentAmountMessage.setText(message);
    }
    
    /** @param message The message to set. */
    public void setCoverageMessage(String message) {
        this.coverageMessage.setFill(Color.FIREBRICK);
        this.coverageMessage.setText(message);
    }

    /** @param message The message to set. */
    public void setExcessMessage(String message) {
        this.excessMessage.setFill(Color.FIREBRICK);
        this.excessMessage.setText(message);
    }

    /** @param message The message to set. */
    public void setMaterialMessage(String message) {
        this.materialMessage.setFill(Color.FIREBRICK);
        this.materialMessage.setText(message);
    }

    /** @param message The message to set. */
    public void setStreetMessage(String message) {
        this.streetMessage.setFill(Color.FIREBRICK);
        this.streetMessage.setText(message);
    }

    /** @param message The message to set. */
    public void setTypeMessage(String message) {
        this.typeMessage.setFill(Color.FIREBRICK);
        this.typeMessage.setText(message);
    }

    /** @param message The message to set. */
    public void setYearMessage(String message) {
        this.yearMessage.setFill(Color.FIREBRICK);
        this.yearMessage.setText(message);
    }

    /** @param message The message to set. */
    public void setZipCodeMessage(String message) {
        this.zipCodeMessage.setFill(Color.FIREBRICK);
        this.zipCodeMessage.setText(message);
    }
}