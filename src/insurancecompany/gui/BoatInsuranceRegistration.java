/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package insurancecompany.gui;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author Sindre
 */
public class BoatInsuranceRegistration {
    
    private Scene scene;
    private GridPane mainPane;
    
    private TextField customerIdField;
    private TextField excessField;
    private TextField brandField;
    private TextField engineEffectField;
    private TextField engineTypeField;
    private TextField lengthField;
    private TextField modelField;
    private TextField personalNumberField;
    private TextField regNumberField;
    private TextField regYearField;
    private TextField premiumField;
    
    private ComboBox alarmComboBox;
    private ComboBox coverageComboBox;
    
    private Label customerIdMessage;
    private Label excessMessage;
    private Label brandMessage;
    private Label engineEffectMessage;
    private Label engineTypeMessage;
    private Label lengthMessage;
    private Label modelMessage;
    private Label personalNumberMessage;
    private Label regNumberMessage;
    private Label regYearMessage;
    private Label alarmMessage;
    private Label coverageMessage;
    
    private Button calculateButton;
    private Button registerButton;
    
    public BoatInsuranceRegistration() {
        mainPane = new GridPane();
        mainPane.setAlignment(Pos.CENTER);
        mainPane.setHgap(10);
        mainPane.setVgap(10);
        mainPane.setStyle("-fx-background-color: #E7E7FF;");
        mainPane.getColumnConstraints().addAll(new ColumnConstraints(200), 
                new ColumnConstraints(200), new ColumnConstraints(200));
        
        scene = new Scene(mainPane, 300, 275);
        
        customerIdField = new TextField();
        excessField = new TextField();
        brandField = new TextField();
        engineEffectField = new TextField();
        engineTypeField = new TextField();
        lengthField = new TextField();
        modelField = new TextField();
        personalNumberField = new TextField();
        regNumberField = new TextField();
        regYearField = new TextField();
        premiumField = new TextField();
        premiumField.setEditable(false);
        
        alarmComboBox = new ComboBox();
        alarmComboBox.getItems().addAll("Ja", "Nei");
        
        coverageComboBox = new ComboBox();
        coverageComboBox.getItems().addAll("Kasko", "Delkasko", "Ansvar");
    
        customerIdMessage = new Label("* Dette feltet må fylles ut!");
        coverageMessage = new Label();
        excessMessage = new Label();
        brandMessage = new Label();
        engineEffectMessage = new Label();
        engineTypeMessage = new Label();
        lengthMessage = new Label();
        modelMessage = new Label();
        personalNumberMessage = new Label();
        regNumberMessage = new Label();
        regYearMessage = new Label();
        alarmMessage = new Label();
        
        calculateButton = new Button("Regn ut");
        registerButton = new Button("Registrer");
        
        mainPane.add(new Text("Registrer bilforsikring"), 0, 0);
        
        mainPane.add(new Label("Personnummer:"), 0, 1);
        mainPane.add(customerIdField, 1, 1);
        mainPane.add(customerIdMessage, 2, 1);
        
        mainPane.add(new Label("Velg forsikring:"), 0, 2);
        mainPane.add(coverageComboBox, 1, 2);
        mainPane.add(coverageMessage, 2, 2);
        
        mainPane.add(new Label("Egenandel:"), 0, 3);
        mainPane.add(excessField, 1, 3);
        mainPane.add(excessMessage, 2, 3);
        
        mainPane.add(new Text("Båt"), 0, 4);
        
        mainPane.add(new Label("Personnummer til eier:"), 0, 5);
        mainPane.add(personalNumberField, 1, 5);
        mainPane.add(personalNumberMessage, 2, 5);
        
        mainPane.add(new Label("Registreringsnummer:"), 0, 6);
        mainPane.add(regNumberField, 1, 6);
        mainPane.add(regNumberMessage, 2, 6);
        
        mainPane.add(new Label("Merke:"), 0, 7);
        mainPane.add(brandField, 1, 7);
        mainPane.add(brandMessage, 2, 7);
        
        mainPane.add(new Label("Model:"), 0, 8);
        mainPane.add(modelField, 1, 8);
        mainPane.add(modelMessage, 2, 8);
        
        mainPane.add(new Label("Registreringsår:"), 0, 9);
        mainPane.add(regYearField, 1, 9);
        mainPane.add(regYearMessage, 2, 9);
        
        mainPane.add(new Label("Hestekrefter:"), 0, 10);
        mainPane.add(engineEffectField, 1, 10);
        mainPane.add(engineEffectMessage, 2, 10);
        
        mainPane.add(new Label("Motortype:"), 0, 11);
        mainPane.add(engineTypeField, 1, 11);
        mainPane.add(engineTypeMessage, 2, 11);
        
        mainPane.add(new Label("Lengde (i fot):"), 0, 12);
        mainPane.add(lengthField, 1, 12);
        mainPane.add(lengthMessage, 2, 12);
        
        mainPane.add(new Label("Har alarm:"), 0, 13);
        mainPane.add(alarmComboBox, 1, 13);
        mainPane.add(alarmMessage, 2, 13);
        
        mainPane.add(new Label("Forsikringspremie:"), 0, 14);
        mainPane.add(premiumField, 1, 14);
        mainPane.add(calculateButton, 2, 14);
        
        mainPane.add(registerButton, 1, 15);
    }
    
    public GridPane getMainPane() {
        return mainPane;
    }
    
    public void show(Stage stage) {
        stage.setTitle("Registrering av båtforsikring.");
        stage.setScene(scene);
        stage.show();
    }
}