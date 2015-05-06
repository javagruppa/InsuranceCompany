/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package insurancecompany.gui;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
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
    private CheckBox hasAlarmCheckBox;
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
    private Label hasAlarmMessage;
    private Label coverageMessage;
            
    private Button registerButton;
    private TextArea outputTextArea;
    
    public BoatInsuranceRegistration() {
        mainPane = new GridPane();
        mainPane.setAlignment(Pos.CENTER);
        mainPane.setHgap(10);
        mainPane.setVgap(10);
        mainPane.setStyle("-fx-background-color: #E7E7FF;");
        
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
        hasAlarmCheckBox = new CheckBox();
        coverageComboBox = new ComboBox();
        coverageComboBox.getItems().addAll("Kasko", "Delkasko", "Ansvar");
    
        customerIdMessage = new Label();
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
        hasAlarmMessage = new Label();
        
        registerButton = new Button("Registrer");
        outputTextArea = new TextArea();
        outputTextArea.setPrefColumnCount(30);
        outputTextArea.setEditable(false);
        
        mainPane.add(new Text("Registrer bilforsikring"), 1, 1);
        
        mainPane.add(new Label("Kundenummer:"), 1, 2);
        mainPane.add(customerIdField, 2, 2);
        mainPane.add(customerIdMessage, 3, 2);
        
        mainPane.add(new Label("Type:"), 1, 3);
        mainPane.add(coverageComboBox, 2, 3);
        mainPane.add(coverageMessage, 3, 3);
        
        mainPane.add(new Label("Egenandel:"), 1, 4);
        mainPane.add(excessField, 2, 4);
        mainPane.add(excessMessage, 3, 4);
        
        mainPane.add(new Text("Båt"), 1, 5);
        
        mainPane.add(new Label("Merke:"), 1, 6);
        mainPane.add(brandField, 2, 6);
        mainPane.add(brandMessage, 3, 6);
        
        mainPane.add(new Label("Hestekrefter:"), 1, 7);
        mainPane.add(engineEffectField, 2, 7);
        mainPane.add(engineEffectMessage, 3, 7);
        
        mainPane.add(new Label("Motortype:"), 1, 8);
        mainPane.add(engineTypeField, 2, 8);
        mainPane.add(engineTypeMessage, 3, 8);
        
        mainPane.add(new Label("Lengde (i fot):"), 1, 9);
        mainPane.add(lengthField, 2, 9);
        mainPane.add(lengthMessage, 3, 9);
        
        mainPane.add(new Label("Model:"), 1, 10);
        mainPane.add(modelField, 2, 10);
        mainPane.add(modelMessage, 3, 10);
        
        mainPane.add(new Label("Registreringsnummer:"), 1, 11);
        mainPane.add(regNumberField, 2, 11);
        mainPane.add(regNumberMessage, 3, 11);
        
        mainPane.add(new Label("Registreringsår:"), 1, 12);
        mainPane.add(regYearField, 2, 12);
        mainPane.add(regYearMessage, 3, 12);
        
        mainPane.add(new Label("Har alarm:"), 1, 13);
        mainPane.add(hasAlarmCheckBox, 2, 13);
        mainPane.add(hasAlarmMessage, 3, 13);
        
        mainPane.add(new Text("Båteier"), 1, 14);
        
        mainPane.add(new Label("Personnummer:"), 1, 15);
        mainPane.add(personalNumberField, 2, 15);
        mainPane.add(personalNumberMessage, 3, 15);
        
        mainPane.add(registerButton, 2, 16);
        mainPane.add(outputTextArea, 4, 1, 1, 16);
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