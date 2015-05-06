/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package insurancecompany.gui;

import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

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
    private TextField modelField;
    private TextField modelVariantField;
    private RadioButton youngestDriverRadio;
    private RadioButton alarmRadio;
    private ComboBox coverageCombo; // Nor: Dekning
    //private ComboBox previousClaimsCombo; // Number of claims in the last 3 years.
    private ComboBox parkingConditionCombo;
    
    
    
    public CarInsuranceRegistration() {
        
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
        modelLabel = new Label("Modell:");
        modelField = new TextField();
        modelVariantLabel = new Label("Modell variant:");
        modelVariantField = new TextField();
        youngestDriverLabel = new Label("Yngste fører:");
        youngestDriverRadio = new RadioButton();
        alarmLabel = new Label("FG-godkjent alarm:");
        alarmRadio = new RadioButton();
        coverageLabel = new Label("Dekning:");
        coverageCombo = new ComboBox();
        parkingConditionLabel = new Label("Parkeringsforhold:");
        parkingConditionCombo = new ComboBox();
        
    }
}
