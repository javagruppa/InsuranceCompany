/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package insurancecompany.view.register.insurances;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
    
    // Declare the Gridpane and Scene.
    private GridPane mainPane;
    private Scene scene;
    
    // Declare all ComboBoxes and TextFields.
    private ComboBox<String> alarmComboBox;
    private TextField brandField;
    private ComboBox<String> coverageComboBox;
    private TextField customerIdField;
    private TextField customerPersonalNumberField;
    private TextField engineEffectField;
    private TextField engineTypeField;
    private TextField excessField;
    private TextField lengthField;
    private TextField modelField;
    private TextField ownerPersonalNumberField;
    private TextField premiumField;
    private TextField registrationNumberField;
    private TextField registrationYearField;
    
    // Declare all Label messages.
    private Label alarmMessage;
    private Label brandMessage;
    private Label coverageMessage;
    private Label customerIdMessage;
    private Label customerPersonalNumberMessage;
    private Label engineEffectMessage;
    private Label engineTypeMessage;
    private Label excessMessage;
    private Label lengthMessage;
    private Label modelMessage;
    private Label ownerPersonalNumberMessage;
    private Label registrationNumberMessage;
    private Label registrationYearMessage;
    
    // Declare all Buttons.
    private Button calculateButton;
    private Button registerButton;
    
    // Constrcutor
    public BoatInsuranceRegistration() {
        
        // Sets up the mainPane and scene.
        mainPane = new GridPane();
        mainPane.setAlignment(Pos.CENTER);
        mainPane.setHgap(10);
        mainPane.setVgap(10);
        mainPane.setStyle("-fx-background-color: #E7E7FF;");
        mainPane.getColumnConstraints().addAll(new ColumnConstraints(200), 
                new ColumnConstraints(200), new ColumnConstraints(200));
        scene = new Scene(mainPane, 300, 275);
        
        // Declares all ComboBoxes and TextFields.
        alarmComboBox = new ComboBox<>();
        alarmComboBox.getItems().addAll("Ja", "Nei");
        brandField = new TextField();
        coverageComboBox = new ComboBox<>();
        coverageComboBox.getItems().addAll("Kasko", "Delkasko", "Ansvar");
        customerIdField = new TextField();
        customerPersonalNumberField = new TextField();
        engineEffectField = new TextField();
        engineTypeField = new TextField();
        excessField = new TextField();
        lengthField = new TextField();
        modelField = new TextField();
        ownerPersonalNumberField = new TextField();
        premiumField = new TextField();
        premiumField.setEditable(false);
        registrationNumberField = new TextField();
        registrationYearField = new TextField();
        
        // Declares all Label messages.
        alarmMessage = new Label();
        brandMessage = new Label();
        coverageMessage = new Label();
        customerIdMessage = new Label();
        customerPersonalNumberMessage = new Label();
        engineEffectMessage = new Label();
        engineTypeMessage = new Label();
        excessMessage = new Label();
        lengthMessage = new Label();
        modelMessage = new Label();
        ownerPersonalNumberMessage = new Label();
        registrationNumberMessage = new Label();
        registrationYearMessage = new Label();
        
        // Declares all Buttons.
        calculateButton = new Button("Regn ut");
        registerButton = new Button("Registrer");
        
        // Adds all elements to the mainPane.
        mainPane.add(new Text("Registrer bilforsikring"), 0, 0);
        
        mainPane.add(new Label("Kundenummer:"), 0, 1);
        mainPane.add(customerIdField, 1, 1);
        mainPane.add(customerIdMessage, 2, 1);
        
        mainPane.add(new Label("Kundens personnummer:"), 0, 2);
        mainPane.add(customerPersonalNumberField, 1, 2);
        mainPane.add(customerPersonalNumberMessage, 2, 2);
        
        mainPane.add(new Label("Velg forsikring:"), 0, 3);
        mainPane.add(coverageComboBox, 1, 3);
        mainPane.add(coverageMessage, 2, 3);
        
        mainPane.add(new Label("Egenandel:"), 0, 4);
        mainPane.add(excessField, 1, 4);
        mainPane.add(excessMessage, 2, 4);
        
        mainPane.add(new Text("Båt"), 0, 5);
        
        mainPane.add(new Label("Eierens personnummer:"), 0, 6);
        mainPane.add(ownerPersonalNumberField, 1, 6);
        mainPane.add(ownerPersonalNumberMessage, 2, 6);
        
        mainPane.add(new Label("Registreringsnummer:"), 0, 7);
        mainPane.add(registrationNumberField, 1, 7);
        mainPane.add(registrationNumberMessage, 2, 7);
        
        mainPane.add(new Label("Merke:"), 0, 8);
        mainPane.add(brandField, 1, 8);
        mainPane.add(brandMessage, 2, 8);
        
        mainPane.add(new Label("Model:"), 0, 9);
        mainPane.add(modelField, 1, 9);
        mainPane.add(modelMessage, 2, 9);
        
        mainPane.add(new Label("Registreringsår:"), 0, 10);
        mainPane.add(registrationYearField, 1, 10);
        mainPane.add(registrationYearMessage, 2, 10);
        
        mainPane.add(new Label("Hestekrefter:"), 0, 11);
        mainPane.add(engineEffectField, 1, 11);
        mainPane.add(engineEffectMessage, 2, 11);
        
        mainPane.add(new Label("Motortype:"), 0, 12);
        mainPane.add(engineTypeField, 1, 12);
        mainPane.add(engineTypeMessage, 2, 12);
        
        mainPane.add(new Label("Lengde (i fot):"), 0, 13);
        mainPane.add(lengthField, 1, 13);
        mainPane.add(lengthMessage, 2, 13);
        
        mainPane.add(new Label("Har alarm:"), 0, 14);
        mainPane.add(alarmComboBox, 1, 14);
        mainPane.add(alarmMessage, 2, 14);
        
        mainPane.add(new Label("Forsikringspremie:"), 0, 15);
        mainPane.add(premiumField, 1, 15);
        mainPane.add(calculateButton, 2, 15);
        
        mainPane.add(registerButton, 1, 16);
    }
    
    public GridPane getMainPane() {
        return mainPane;
    }
    
    public void show(Stage stage) {
        stage.setTitle("Registrering av båtforsikring.");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @return the alarmComboBox
     */
     public String getAlarm() {
        return alarmComboBox.getValue() == null ? "" : alarmComboBox.getValue();
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
    public String getCoverage() {
        return coverageComboBox.getValue() == null ? "" : coverageComboBox.getValue();
    }

    /**
     * @return the customerIdField
     */
    public String getCustomerId() {
        return customerIdField.getText();
    }

    /**
     * @return the customerPersonalNumberField
     */
    public String getCustomerPersonalNumber() {
        return customerPersonalNumberField.getText();
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
        return excessField.getText();
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

    /**
     * @param alarmMessage the alarmMessage to set
     */
    public void setAlarmMessage(String alarmMessage) {
        this.alarmMessage.setText(alarmMessage);
    }

    /**
     * @param brandMessage the brandMessage to set
     */
    public void setBrandMessage(String brandMessage) {
        this.brandMessage.setText(brandMessage);
    }

    /**
     * @param customerIdMessage the customerIdMessage to set
     */
    public void setCustomerIdMessage(String customerIdMessage) {
        this.customerIdMessage.setText(customerIdMessage);
    }

    /**
     * @param customerPersonalNumberMessage the customerPersonalNumberMessage 
     * to set
     */
    public void setCustomerPersonalNumberMessage(
            String customerPersonalNumberMessage) {
        this.customerPersonalNumberMessage.
                setText(customerPersonalNumberMessage);
    }

    /**
     * @param coverageMessage the coverageMessage to set
     */
    public void setCoverageMessage(String coverageMessage) {
        this.coverageMessage.setText(coverageMessage);
    }

    /**
     * @param engineEffectMessage the engineEffectMessage to set
     */
    public void setEngineEffectMessage(String engineEffectMessage) {
        this.engineEffectMessage.setText(engineEffectMessage);
    }

    /**
     * @param engineTypeMessage the engineTypeMessage to set
     */
    public void setEngineTypeMessage(String engineTypeMessage) {
        this.engineTypeMessage.setText(engineTypeMessage);
    }

    /**
     * @param excessMessage the excessMessage to set
     */
    public void setExcessMessage(String excessMessage) {
        this.excessMessage.setText(excessMessage);
    }

    /**
     * @param lengthMessage the lengthMessage to set
     */
    public void setLengthMessage(String lengthMessage) {
        this.lengthMessage.setText(lengthMessage);
    }

    /**
     * @param modelMessage the modelMessage to set
     */
    public void setModelMessage(String modelMessage) {
        this.modelMessage.setText(modelMessage);
    }

    /**
     * @param ownerPersonalNumberMessage the ownerPersonalNumberMessage to set
     */
    public void setOwnerPersonalNumberMessage(
            String ownerPersonalNumberMessage) {
        this.ownerPersonalNumberMessage.setText(ownerPersonalNumberMessage);
    }

    /**
     * @param registrationNumberMessage the registrationNumberMessage to set
     */
    public void setRegistrationNumberMessage(String registrationNumberMessage) {
        this.registrationNumberMessage.setText(registrationNumberMessage);
    }

    /**
     * @param registrationYearMessage the registrationYearMessage to set
     */
    public void setRegistrationYearMessage(String registrationYearMessage) {
        this.registrationYearMessage.setText(registrationYearMessage);
    }

    public void setCalculateButtonEventHandler(EventHandler<ActionEvent> value) {
        calculateButton.setOnAction(value);
    }

    public void setRegisterButtonEventHandler(EventHandler<ActionEvent> value) {
        registerButton.setOnAction(value);
    }
}