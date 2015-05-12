/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package insurancecompany.view.register.insurances;

import insurancecompany.misc.coverages.CarInsuranceCoverage;
import insurancecompany.model.datastructures.carinfo.CarInfo;
import insurancecompany.model.datastructures.carinfo.ModelInfo;
import java.util.ArrayList;
import java.util.Calendar;
import static java.util.Collections.list;
import java.util.List;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.util.Callback;

/**
 *
 * @author André
 */
public class CarInsuranceRegistration {
    
    
    private GridPane mainPane;
    
    private Label customerIdLabel;
    private Label personalNumberLabel;
    
    private Label existingBonusLabel;
    private Label drivingLengthLabel;
    private Label excessLabel; // Nor: Egenandel
    private Label brandLabel;
    private Label yearLabel;
    private Label modelLabel;
    private Label youngestDriverLabel;
    private Label alarmLabel; // FG-approved alarm
    private Label coverageLabel; // Nor: Dekning
    private Label parkingConditionLabel;
    private Label registrationNumberLabel;
    private Label personalNumberOwnerLabel;
    
    private Label totalInsurancesLabel;
    private Label premiumLabel;
    
    private TextField customerIdField;
    private TextField personalNumberField;
    
    private ComboBox existingBonusCombo;
    private ComboBox drivingLengthCombo;
    private ComboBox excessCombo; // Nor: Egenandel
    private ComboBox brandCombo;
    private ComboBox yearCombo;
    private ComboBox modelCombo;
    private ComboBox youngestDriverCombo;
    private ComboBox alarmCombo;
    private ComboBox coverageCombo; // Nor: Dekning
    private ComboBox parkingConditionCombo;
    private TextField registrationNumberField;
    private TextField personalNumberOwnerField;
    
    private ComboBox totalInsurancesCombo;
    private TextField premiumField;
    
    // Decalre TextArea and TableView
    private TextArea customerArea;
    private TableView insurancesTable;
    
    // Declare all Buttons.
    private Button searchCustomerIdButton;
    private Button searchPersonalNumberButton;
    private Button selectCustomerButton;
    
    private Button calculateButton;
    private Button registerButton;
    
    public CarInsuranceRegistration() {
        
        // Sets up the mainPane
        mainPane = new GridPane();
        mainPane.setAlignment(Pos.CENTER);
        mainPane.setHgap(10);
        mainPane.setVgap(10);
        mainPane.setStyle("-fx-background-color: #E7E7FF;");
        ColumnConstraints col1 = new ColumnConstraints(120);
        ColumnConstraints col2 = new ColumnConstraints(100);
        ColumnConstraints col3 = new ColumnConstraints(40);
        ColumnConstraints col4 = new ColumnConstraints(50);
        ColumnConstraints col5 = new ColumnConstraints(150);
        ColumnConstraints col6 = new ColumnConstraints(150);
        ColumnConstraints col7 = new ColumnConstraints(150);
        
        mainPane.getColumnConstraints().addAll(col1, col2, col3, col4, col5, col6, col7);
        
        
        Text selectCustomerTitle = new Text("Velg først en kunde i registeret:");
        customerIdLabel = new Label("Kundenummer:");
        customerIdField = new TextField();
        searchCustomerIdButton = new Button("Søk");
        personalNumberLabel = new Label("Personnummer:");
        personalNumberField = new TextField();
        searchPersonalNumberButton = new Button("Søk");
        customerArea = new TextArea();
        customerArea.setPrefColumnCount(2);
        customerArea.setPrefRowCount(4);
        Text insurancesTitle = new Text("Forsikringer til denne kunden:");
        insurancesTable = new TableView();
        insurancesTable.setPrefHeight(150);
        selectCustomerButton = new Button("Velg kunde");
        
        Text insuranceOptionsTitle = new Text("Betingelser:");
        existingBonusLabel = new Label("Eksisterende bonussats:");
        existingBonusCombo = new ComboBox();
        populateExistingBonusCombo();
        drivingLengthLabel = new Label("Kjørelengde:");
        drivingLengthCombo = new ComboBox();
        populateDrivingLengthCombo();
        excessLabel = new Label("Egenandel:");
        excessCombo = new ComboBox();
        populateExcessCombo();
        youngestDriverLabel = new Label("Yngste fører:");
        youngestDriverCombo = new ComboBox();
        populateYoungestDriverCombo();
        coverageLabel = new Label("Dekning:");
        coverageCombo = new ComboBox();
        populateCoverageCombo();
        parkingConditionLabel = new Label("Parkeringsforhold:");
        parkingConditionCombo = new ComboBox();
        populateParkingConditionCombo();
        
        Text carTitle = new Text("Bil:");
        personalNumberOwnerLabel = new Label("Eierens personnummer:");
        personalNumberOwnerField = new TextField();
        registrationNumberLabel = new Label("Registreringsnummer:");
        registrationNumberField = new TextField();
        brandLabel = new Label("Merke:");
        brandCombo = new ComboBox();
        brandCombo.setEditable(true);
        yearLabel = new Label("År:");      
        yearCombo = new ComboBox();
        yearCombo.setEditable(true);   
        modelLabel = new Label("Modell:");
        modelCombo = new ComboBox();
        modelCombo.setEditable(true);
        alarmLabel = new Label("FG-godkjent alarm:");
        alarmCombo = new ComboBox();
        popualteAlarmCombo();
        premiumLabel = new Label("Beregnet forsikringspremie:");
        premiumField = new TextField();
        calculateButton = new Button("Regn ut");
        registerButton = new Button("Registrer");
        
        // Add to mainPane:
        mainPane.add(selectCustomerTitle, 0, 0);
        mainPane.add(customerIdLabel, 0, 1);
        mainPane.add(customerIdField, 1, 1);
        mainPane.add(searchCustomerIdButton, 2, 1);
        
        mainPane.add(personalNumberLabel, 0, 2);
        mainPane.add(personalNumberField, 1, 2);
        mainPane.add(searchPersonalNumberButton, 2, 2);
        
        mainPane.add(customerArea, 0, 3, 3, 6);
        mainPane.add(insurancesTitle, 0, 9);
        mainPane.add(insurancesTable, 0, 10, 3, 5);
        mainPane.add(selectCustomerButton, 0, 15);
        
        mainPane.add(insuranceOptionsTitle, 4, 0);
        mainPane.add(existingBonusLabel, 4, 1);
        mainPane.add(existingBonusCombo, 5, 1);
        mainPane.add(drivingLengthLabel, 4, 2);
        mainPane.add(drivingLengthCombo, 5, 2);
        mainPane.add(excessLabel, 4, 3);
        mainPane.add(excessCombo, 5, 3);
        mainPane.add(youngestDriverLabel, 4, 4);
        mainPane.add(youngestDriverCombo, 5, 4);
        mainPane.add(coverageLabel, 4, 5);
        mainPane.add(coverageCombo, 5, 5);
        mainPane.add(parkingConditionLabel, 4, 6);
        mainPane.add(parkingConditionCombo, 5, 6);

        mainPane.add(carTitle, 4, 7);
        mainPane.add(personalNumberOwnerLabel, 4, 8);
        mainPane.add(personalNumberOwnerField, 5, 8);
        mainPane.add(registrationNumberLabel, 4, 9);
        mainPane.add(registrationNumberField, 5, 9);
        mainPane.add(brandLabel, 4, 10);
        mainPane.add(brandCombo, 5, 10);
        mainPane.add(yearLabel, 4, 11);
        mainPane.add(yearCombo, 5, 11);
        mainPane.add(modelLabel, 4, 12);
        mainPane.add(modelCombo, 5, 12);
        mainPane.add(alarmLabel, 4, 13);
        mainPane.add(alarmCombo, 5, 13);
        mainPane.add(premiumLabel, 4, 14);
        mainPane.add(premiumField, 5, 14);
        mainPane.add(calculateButton, 6, 14);
        mainPane.add(registerButton, 5, 15);
      
    }
    
    private void populateExistingBonusCombo() {
        String[] bonuses = {"-30", "-20", "-10", "0", "10", "20", "30", "40", 
            "50", "60", "70", "75"};
        existingBonusCombo.getItems().setAll(bonuses);
        existingBonusCombo.setPrefWidth(150);
    }
    
    private void populateDrivingLengthCombo() {
        String[] drivingLength = {"6000", "8000", "10000", "15000", "14000", 
            "16000", "18000", "20000", "25000", "30000", "40000", "50000", 
            "Ubegrenset"};
        drivingLengthCombo.getItems().setAll(drivingLength);
        drivingLengthCombo.setPrefWidth(150);
    }
    
    private void populateExcessCombo() {
        String[] excess = {"4000", "6000", "8000", "10000", "15000", "14000", 
            "16000", "18000", "20000", "25000", "30000"};
        excessCombo.getItems().setAll(excess);
        excessCombo.setPrefWidth(150);
    }
    
    private void populateYoungestDriverCombo() {
        String[] youngestDriver = {"Under 25", "Over 25"};
        youngestDriverCombo.getItems().setAll(youngestDriver);
        youngestDriverCombo.setPrefWidth(150);
    }
    
    private void populateCoverageCombo() {
        coverageCombo.getItems().setAll(CarInsuranceCoverage.values());
        coverageCombo.setPrefWidth(150);
    }
    
    private void populateParkingConditionCombo() {
        String[] parkingCondition = {"Har garasje", "Har ikke garasje"};
        parkingConditionCombo.getItems().setAll(parkingCondition);
        parkingConditionCombo.setPrefWidth(150);
    }
    
    private void popualteAlarmCombo() {
        String[] alarm = {"Ja", "Nei"};
        alarmCombo.getItems().setAll(alarm);
        alarmCombo.setPrefWidth(150);
    }
    
    public void populateBrandComboBox(List cars) {
        ObservableList obList = FXCollections.observableList(cars);
        // Reset the previous value(shown value)
        brandCombo.valueProperty().set(null);
        brandCombo.getItems().setAll(obList);
        brandCombo.setPrefWidth(150);
    }
    
    public void populateYearComboBox(List years) {
        ObservableList obList = FXCollections.observableList(years);
        // Reset the previous value(shown value)
        yearCombo.valueProperty().set(null);
        yearCombo.getItems().setAll(obList);
        yearCombo.setPrefWidth(150);
    }
    
    public void populateModelComboBox(List models) {
        ObservableList obList = FXCollections.observableList(models);
        // Reset the previous value(shown value)
        modelCombo.valueProperty().set(null);
        modelCombo.getItems().setAll(obList);
        modelCombo.setPrefWidth(150);
    }
    
    public void setBrandComboListener(ChangeListener listener) {
        brandCombo.valueProperty().addListener(listener);
    }
    
    public void setYearComboListener(ChangeListener listener) {
        yearCombo.valueProperty().addListener(listener);
    }
    
    public Object getBrandComboValue() {
        return brandCombo.getValue();
    }
    
    
    public Pane getMainPane() {
        return mainPane;
    }
}
