/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package insurancecompany.view.register.insurances;

import insurancecompany.misc.coverages.BoatInsuranceCoverage;
import insurancecompany.misc.coverages.HolidayHomeInsuranceCoverage;
import insurancecompany.model.insurances.Insurance;
import java.util.List;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
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
import javafx.stage.Stage;

/**
 *
 * @author Sindre
 */
public class HolidayHomeInsuranceRegistration {
    
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
    private Text customerSelectedMessage;
    // Buttons:
    private Button searchCustomerIdButton;
    private Button searchPersonalNumberButton;
    private Button selectCustomerButton;
    // ints used to keep track of searched and selected customer id:
    private int tempCustomerId;
    private int selectedCustomerId;
    
    // REGISTER INSURANCE NODES:
    // Input nodes, Comboboxes and textfiels:
    private ComboBox<HolidayHomeInsuranceCoverage> coverageCombo;
    private ComboBox<String> excessCombo;
    private ComboBox<String> rentalCombo;
    private TextField areaField;
    private TextField buildingAmountField;
    private TextField cityField;
    private TextField contentAmountField;
    private TextField materialField;
    private TextField premiumField;
    private TextField streetField;
    private TextField typeField;
    private TextField yearField;
    private TextField zipCodeField;
    // Output nodes, Text messages:
    private Text areaMessage;
    private Text buildingAmountMessage;
    private Text cityMessage;
    private Text coverageMessage;
    private Text contentAmountMessage;
    private Text customerIdMessage;
    private Text excessMessage;
    private Text materialMessage;
    private Text rentalMessage;
    private Text streetMessage;
    private Text typeMessage;
    private Text yearMessage;
    private Text zipCodeMessage;
    // Buttons:
    private Button calculateButton;
    private Button registerButton;
    
    public HolidayHomeInsuranceRegistration() {
        
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
        
        // SEARCH FOR CUSTOMER NODES:
        
        // Declares Input
        customerIdField = new TextField();
        personalNumberField = new TextField();
        // Declares Output
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
        // Declares Buttons
        searchCustomerIdButton = new Button("Søk");
        searchPersonalNumberButton = new Button("Søk");
        selectCustomerButton = new Button("Velg denne kunden");
        // Declares Text and Label
        Label customerIdLabel = new Label("Kundenummer:");
        Label personalNumberLabel = new Label("Personnummer:");
        Text insurancesTitle = new Text("Eksisterende forsikringer til denne kunden:");
        insurancesTitle.setId("textTitle");
        Text resultTitle = new Text("Søkeresultat:");
        resultTitle.setId("textTitle");
        Text selectCustomerTitle = new Text("Velg først en kunde i registeret:");
        selectCustomerTitle.setId("textTitle");
        
        // REGISTER INSURANCE NODES:
        
        // Declares Input
        coverageCombo = new ComboBox<>();
        populateCoverageCombo();
        excessCombo = new ComboBox<>();
        populateExcessCombo();
        rentalCombo = new ComboBox<>();
        populateRentalCombo();
        areaField = new TextField();
        buildingAmountField = new TextField();
        cityField = new TextField();
        contentAmountField = new TextField();
        materialField = new TextField();
        premiumField = new TextField();
        streetField = new TextField();
        typeField = new TextField();
        yearField = new TextField();
        zipCodeField = new TextField();
        // Declares Output
        areaMessage = new Text();
        buildingAmountMessage = new Text();
        cityMessage = new Text();
        contentAmountMessage = new Text();
        coverageMessage = new Text();
        customerIdMessage = new Text();
        excessMessage = new Text();
        materialMessage = new Text();
        rentalMessage = new Text();
        streetMessage = new Text();
        typeMessage = new Text();
        yearMessage = new Text();
        zipCodeMessage = new Text();
        // Declares all Buttons.
        calculateButton = new Button("Regn ut");
        registerButton = new Button("Registrer");
        // Declares Text and Label
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
        Label rentalLabel = new Label("For leie:");
        Label areaLabel = new Label("Areal:");
        Label yearLabel = new Label("Byggeår:");
        Label materialLabel = new Label("Materiale:");
        Label typeLabel = new Label("Type:");
        Label streetLabel = new Label("Gate:");
        Label zipCodeLabel = new Label("Postboks:");
        Label cityLabel = new Label("By:");
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
        mainPane.add(buildingAmountLabel, 4, 3);
        mainPane.add(buildingAmountField, 5, 3);
        mainPane.add(buildingAmountMessage, 6, 3);
        mainPane.add(contentAmountLabel, 4, 4);
        mainPane.add(contentAmountField, 5, 4);
        mainPane.add(contentAmountMessage, 6, 4);
        mainPane.add(rentalLabel, 4, 5);
        mainPane.add(rentalCombo, 5, 5);
        mainPane.add(rentalMessage, 6, 5);
        
        mainPane.add(houseTitle, 4, 6);
        mainPane.add(areaLabel, 4, 7);
        mainPane.add(areaField, 5, 7);
        mainPane.add(areaMessage, 6, 7);
        mainPane.add(yearLabel, 4, 8);
        mainPane.add(yearField, 5, 8);
        mainPane.add(yearMessage, 6, 8);
        mainPane.add(materialLabel, 4, 9);
        mainPane.add(materialField, 5, 9);
        mainPane.add(materialMessage, 6, 9);
        mainPane.add(typeLabel, 4, 10);
        mainPane.add(typeField, 5, 10);
        mainPane.add(typeMessage, 6, 10);
        
        mainPane.add(addressTitle, 4, 11);
        mainPane.add(streetLabel, 4, 12);
        mainPane.add(streetField, 5, 12);
        mainPane.add(streetMessage, 6, 12);
        mainPane.add(zipCodeLabel, 4, 13);
        mainPane.add(zipCodeField, 5, 13);
        mainPane.add(zipCodeMessage, 6, 13);
        mainPane.add(cityLabel, 4, 14);
        mainPane.add(cityField, 5, 14);
        mainPane.add(cityMessage, 6, 14);
        mainPane.add(premiumLabel, 4, 15);
        mainPane.add(premiumField, 5, 15);
        mainPane.add(calculateButton, 6, 15);
        mainPane.add(registerButton, 5, 16);
    }
    
    // POPULATE COMBOBOX
    
    private void populateCoverageCombo() {
        ObservableList<HolidayHomeInsuranceCoverage> obList;
        obList = FXCollections.observableArrayList(HolidayHomeInsuranceCoverage.values()); 
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
    
    private void populateRentalCombo() {
        ObservableList<String> rental = FXCollections.observableArrayList();  
        rental.addAll("Ja", "Nei");
        rentalCombo.getItems().setAll(rental);
        rentalCombo.setPrefWidth(150);
    }
    
    // POPULATE TABLE
    
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
    
    // EVENT HANDLERS

    public void setCalculateButtonEventHandler(EventHandler<ActionEvent> value) {
        calculateButton.setOnAction(value);
    }

    public void setRegisterButtonEventHandler(EventHandler<ActionEvent> value) {
        registerButton.setOnAction(value);
    }
    
    // GET METHODS
    
    public GridPane getMainPane() {
        return mainPane;
    }

    /**
     * @return the customerIdField
     */
    public String getCustomerId() {
        return customerIdField.getText();
    }

    /**
     * @return the excessField
     */
    public String getExcess() {
        return excessCombo.getValue() == null ? "" : excessCombo.getValue();
    }

    /**
     * @return the buildingAmountField
     */
    public String getBuildingAmount() {
        return buildingAmountField.getText();
    }

    /**
     * @return the contentAmountField
     */
    public String getContentAmount() {
        return contentAmountField.getText();
    }

    /**
     * @return the areaField
     */
    public String getArea() {
        return areaField.getText();
    }

    /**
     * @return the yearField
     */
    public String getYear() {
        return yearField.getText();
    }

    /**
     * @return the materialField
     */
    public String getMaterial() {
        return materialField.getText();
    }

    /**
     * @return the typeField
     */
    public String getType() {
        return typeField.getText();
    }

    /**
     * @return the streetField
     */
    public String getStreet() {
        return streetField.getText();
    }

    /**
     * @return the zipCodeField
     */
    public String getZipCode() {
        return zipCodeField.getText();
    }

    /**
     * @return the cityField
     */
    public String getCity() {
        return cityField.getText();
    }

    /**
     * @return the premiumField
     */
    public String getPremium() {
        return premiumField.getText();
    }

    /**
     * @return the rentalComboBox
     */
    public String getRental() {
        return rentalCombo.getValue() == null ? "" : rentalCombo.getValue();
    }
    
    // SET METHODS

    /**
     * @param excessMessage the excessMessage to set
     */
    public void setExcessMessage(String excessMessage) {
        this.excessMessage.setFill(Color.FIREBRICK);
        this.excessMessage.setText(excessMessage);
    }

    /**
     * @param buildingAmountMessage the buildingAmountMessage to set
     */
    public void setBuildingAmountMessage(String buildingAmountMessage) {
        this.buildingAmountMessage.setFill(Color.FIREBRICK);
        this.buildingAmountMessage.setText(buildingAmountMessage);
    }

    /**
     * @param contentAmountMessage the contentAmountMessage to set
     */
    public void setContentAmountMessage(String contentAmountMessage) {
        this.contentAmountMessage.setFill(Color.FIREBRICK);
        this.contentAmountMessage.setText(contentAmountMessage);
    }

    /**
     * @param rentalMessage the rentalMessage to set
     */
    public void setRentalMessage(String rentalMessage) {
        this.rentalMessage.setFill(Color.FIREBRICK);
        this.rentalMessage.setText(rentalMessage);
    }

    /**
     * @param areaMessage the areaMessage to set
     */
    public void setAreaMessage(String areaMessage) {
        this.areaMessage.setFill(Color.FIREBRICK);
        this.areaMessage.setText(areaMessage);
    }

    /**
     * @param yearMessage the yearMessage to set
     */
    public void setYearMessage(String yearMessage) {
        this.yearMessage.setFill(Color.FIREBRICK);
        this.yearMessage.setText(yearMessage);
    }

    /**
     * @param materialMessage the materialMessage to set
     */
    public void setMaterialMessage(String materialMessage) {
        this.materialMessage.setFill(Color.FIREBRICK);
        this.materialMessage.setText(materialMessage);
    }

    /**
     * @param typeMessage the typeMessage to set
     */
    public void setTypeMessage(String typeMessage) {
        this.typeMessage.setFill(Color.FIREBRICK);
        this.typeMessage.setText(typeMessage);
    }

    /**
     * @param streetMessage the streetMessage to set
     */
    public void setStreetMessage(String streetMessage) {
        this.streetMessage.setFill(Color.FIREBRICK);
        this.streetMessage.setText(streetMessage);
    }

    /**
     * @param zipCodeMessage the zipCodeMessage to set
     */
    public void setZipCodeMessage(String zipCodeMessage) {
        this.zipCodeMessage.setFill(Color.FIREBRICK);
        this.zipCodeMessage.setText(zipCodeMessage);
    }

    /**
     * @param cityMessage the cityMessage to set
     */
    public void setCityMessage(String cityMessage) {
        this.cityMessage.setFill(Color.FIREBRICK);
        this.cityMessage.setText(cityMessage);
    }
}
