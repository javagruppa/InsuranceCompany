/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package insurancecompany.view.register.insurances;

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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
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
    private Label existingBonusLabel;
    private Label drivingLengthLabel;
    private Label excessLabel; // Nor: Egenandel
    private Label brandLabel;
    private Label yearLabel;
    private Label modelLabel;
    private Label modelVariantLabel;
    private Label youngestDriverLabel;
    private Label alarmLabel; // FG-approved alarm
    private Label coverageLabel; // Nor: Dekning
    //private Label previousClaimsLabel; // Number of claims in the last 3 years.
    private Label parkingConditionLabel;
    
    private TextField customerIdField;
    private ComboBox existingBonusCombo;
    private ComboBox drivingLengthCombo;
    private ComboBox excessCombo; // Nor: Egenandel
    private ComboBox brandCombo;
    private ComboBox yearCombo;
    private ComboBox modelCombo;
    private RadioButton youngestDriverRadio;
    private RadioButton alarmRadio;
    private ComboBox coverageCombo; // Nor: Dekning
    //private ComboBox previousClaimsCombo; // Number of claims in the last 3 years.
    private ComboBox parkingConditionCombo;
    
    
    
    public CarInsuranceRegistration() {
        
        // Sets up the mainPane
        mainPane = new GridPane();
        mainPane.setAlignment(Pos.CENTER);
        mainPane.setHgap(10);
        mainPane.setVgap(10);
        mainPane.setStyle("-fx-background-color: #E7E7FF;");
        mainPane.getColumnConstraints().addAll(new ColumnConstraints(200), 
                new ColumnConstraints(200), new ColumnConstraints(200));
        
        Text customerIdTitle = new Text("Tegning av bilforsikring krever at kunden allerede er registrert i systemet.");
        customerIdLabel = new Label("Kundenummer:");
        customerIdField = new TextField();
        Text insuranceOptionsTitle = new Text("Betingelser:");
        existingBonusLabel = new Label("Eksisterende bonussats:");
        existingBonusCombo = new ComboBox();
        drivingLengthLabel = new Label("Kjørelengde:");
        drivingLengthCombo = new ComboBox();
        excessLabel = new Label("Egenandel:");
        excessCombo = new ComboBox();
        brandLabel = new Label("Merke:");
        brandCombo = new ComboBox();
        brandCombo.setEditable(true);
        yearLabel = new Label("År:");      
        yearCombo = new ComboBox();
        yearCombo.setEditable(true);   
        modelLabel = new Label("Modell:");
        modelCombo = new ComboBox();
        modelCombo.setEditable(true);
        youngestDriverLabel = new Label("Yngste fører:");
        youngestDriverRadio = new RadioButton();
        alarmLabel = new Label("FG-godkjent alarm:");
        alarmRadio = new RadioButton();
        coverageLabel = new Label("Dekning:");
        coverageCombo = new ComboBox();
        parkingConditionLabel = new Label("Parkeringsforhold:");
        parkingConditionCombo = new ComboBox();
        

        mainPane.add(brandCombo, 1, 1);
        mainPane.add(yearCombo, 1, 2);
        mainPane.add(modelCombo, 1, 3);
      
    }
    
    public void populateBrandComboBox(List cars) {
        ObservableList obList = FXCollections.observableList(cars);
        // Reset the previous value(shown value)
        brandCombo.valueProperty().set(null);
        brandCombo.getItems().setAll(obList);
    }
    
    public void populateYearComboBox(List years) {
        ObservableList obList = FXCollections.observableList(years);
        // Reset the previous value(shown value)
        yearCombo.valueProperty().set(null);
        yearCombo.getItems().setAll(obList);
    }
    
    public void populateModelComboBox(List models) {
        ObservableList obList = FXCollections.observableList(models);
        // Reset the previous value(shown value)
        modelCombo.valueProperty().set(null);
        modelCombo.getItems().setAll(obList);
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
