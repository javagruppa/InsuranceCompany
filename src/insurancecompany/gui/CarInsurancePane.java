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
public class CarInsurancePane {
    
    
    private GridPane mainPane;
    
    private Label customerIdLabel;
    private Label existingBonusLabel;
    private Label drivingLengthLabel;
    private Label excessLabel; // Nor: Egenandel
    private Label brandLabel;
    private Label modelLabel;
    private Label modelVariantLabel;
    private Label youngestDriverLabel;
    private Label alarmLabel;
    private Label coverageLabel; // Nor: Dekning
    //private Label previousClaimsLabel; // Number of claims in the last 3 years.
    private Label parkingConditionLabel;
    
    private TextField customerIdField;
    private ComboBox existingBonusField;
    private ComboBox drivingLengthField;
    private ComboBox excessField; // Nor: Egenandel
    private ComboBox brandField;
    private TextField modelField;
    private TextField modelVariantField;
    private RadioButton youngestDriverField;
    private RadioButton alarmField;
    private ComboBox coverageField; // Nor: Dekning
    //private ComboBox previousClaimsField; // Number of claims in the last 3 years.
    private ComboBox parkingConditionField;
    
    
    
    public CarInsurancePane() {
        
        Text customerIdTitle = new Text("Tegning av bilforsikring krever at kunden allerede er registrert i systemet");
        customerIdLabel = new Label("Kundenummer:");
    }
}
