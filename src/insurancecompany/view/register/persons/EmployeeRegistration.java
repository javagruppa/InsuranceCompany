/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package insurancecompany.view.register.persons;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author André
 */
public class EmployeeRegistration {
    
    private Scene scene;
    private GridPane mainPane;
    
    private ComboBox positionCombo;
    private TextField personalNumberField;
    private TextField firstNameField;
    private TextField lastNameField;    
    private TextField streetField;
    private TextField zipCodeField;
    private TextField cityField;
    private TextField emailField;
    private TextField phoneField;
    
    private Text personalNumberMessage;
    private Text firstNameMessage;
    private Text lastNameMessage;    
    private Text streetMessage;
    private Text zipCodeMessage;
    private Text cityMessage;
    private Text emailMessage;
    private Text phoneMessage;
    
    private Button registerButton;
    
    private Text resultText;
    
    public void start(Stage stage) {
        show(stage);
    }
    
    // TODO: Set fixed size for Text message fields:(or one of them)
    public EmployeeRegistration() {
        mainPane = new GridPane();
        mainPane.setAlignment(Pos.CENTER);
        mainPane.setHgap(10);
        mainPane.setVgap(10);
        mainPane.setStyle("-fx-background-color: #E7E7FF;");
        
        scene = new Scene(getMainPane(), 300, 275);
        
        Text registerT = new Text("Registrer ansatt");
        Label position = new Label("Posisjon");
        positionCombo = new ComboBox();
        positionCombo.getItems().addAll("Kundebehandler", "Saksbehandler", "Administrator");
        
        Label personalNumber = new Label("Personnummer:");
        personalNumberField = new TextField();
        personalNumberMessage = new Text();
        Label firstName = new Label("Fornavn:");
        firstNameField = new TextField();
        firstNameMessage = new Text();
        Label lastName = new Label("Etternavn:");
        lastNameField = new TextField();
        lastNameMessage = new Text();
        Text adress = new Text("Adresse");
        Label street = new Label("Gate:");
        streetField = new TextField();
        streetMessage = new Text();
        Label zipCode = new Label("Postboks:");
        zipCodeField = new TextField();
        zipCodeMessage = new Text();
        Label city = new Label("By:");
        cityField = new TextField();
        cityMessage = new Text();
        Text contact = new Text("Kontakt");
        Label email = new Label("E-post:");
        emailField = new TextField();
        emailMessage = new Text();
        Label phone = new Label("Telefonnummer:");
        phoneField = new TextField();     
        phoneMessage = new Text();      
        registerButton = new Button("Registrer");
        HBox hbBtn = new HBox();
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(registerButton);
        resultText = new Text();
        
        mainPane.add(registerT, 1, 1);
        
        mainPane.add(position, 1, 2);
        mainPane.add(positionCombo, 2, 2);
        
        mainPane.add(personalNumber, 1, 3);
        mainPane.add(personalNumberField, 2, 3);
        mainPane.add(personalNumberMessage, 3, 3);
        
        mainPane.add(firstName, 1, 4);
        mainPane.add(firstNameField, 2, 4);
        mainPane.add(firstNameMessage, 3, 4);
        
        mainPane.add(lastName, 1, 5);
        mainPane.add(lastNameField, 2, 5);
        mainPane.add(lastNameMessage, 3, 5);
        
        mainPane.add(adress, 1, 6);
        
        mainPane.add(street, 1, 7);
        mainPane.add(streetField, 2, 7);
        mainPane.add(streetMessage, 3, 7);
        
        mainPane.add(zipCode, 1, 8);
        mainPane.add(zipCodeField, 2, 8);
        mainPane.add(zipCodeMessage, 3, 8);
        
        mainPane.add(city, 1, 9);
        mainPane.add(cityField, 2, 9);
        mainPane.add(cityMessage, 3, 9);
        
        mainPane.add(contact, 1, 10);
        
        mainPane.add(email, 1, 11);
        mainPane.add(emailField, 2, 11);
        mainPane.add(emailMessage, 3, 11);
        
        mainPane.add(phone, 1, 12);
        mainPane.add(phoneField, 2, 12);
        mainPane.add(phoneMessage, 3, 12);
        
        mainPane.add(hbBtn, 2, 13);
        // column span of 3, row span of 2:
        mainPane.add(resultText, 1, 14, 3, 2);
        
    }
    
    public void show(Stage stage) {
        stage.setTitle("Kunderegistrering");
        stage.setScene(getScene());
        stage.show();
    }
    
    public Pane getPane() {
        return getMainPane();
    }

    /**
     * @return the scene
     */
    public Scene getScene() {
        return scene;
    }

    /**
     * @return the mainPane
     */
    public GridPane getMainPane() {
        return mainPane;
    }

    public String getPositionComboValue() {
        return positionCombo.getValue().toString();
    }
    
    /**
     * @return the personalNumberField
     */
    public String getPersonalNumberField() {
        return personalNumberField.getText();
    }

    /**
     * @return the firstNameField
     */
    public String getFirstNameField() {
        return firstNameField.getText();
    }

    /**
     * @return the lastNameField
     */
    public String getLastNameField() {
        return lastNameField.getText();
    }

    /**
     * @return the streetField
     */
    public String getStreetField() {
        return streetField.getText();
    }

    /**
     * @return the zipCodeField
     */
    public String getZipCodeField() {
        return zipCodeField.getText();
    }

    /**
     * @return the cityField
     */
    public String getCityField() {
        return cityField.getText();
    }

    /**
     * @return the emailField
     */
    public String getEmailField() {
        return emailField.getText();
    }

    /**
     * @return the phoneField
     */
    public String getPhoneField() {
        return phoneField.getText();
    }

    /**
     * @param personalNumberMessage the personalNumberMessage to set
     */
    public void setPersonalNumberMessage(String personalNumberMessage) {
        this.personalNumberMessage.setText(personalNumberMessage);
    }

    /**
     * @param firstNameMessage the firstNameMessage to set
     */
    public void setFirstNameMessage(String firstNameMessage) {
        this.firstNameMessage.setText(firstNameMessage);
    }

    /**
     * @param lastNameMessage the lastNameMessage to set
     */
    public void setLastNameMessage(String lastNameMessage) {
        this.lastNameMessage.setText(lastNameMessage);
    }

    /**
     * @param streetMessage the streetMessage to set
     */
    public void setStreetMessage(String streetMessage) {
        this.streetMessage.setText(streetMessage);
    }

    /**
     * @param zipCodeMessage the zipCodeMessage to set
     */
    public void setZipCodeMessage(String zipCodeMessage) {
        this.zipCodeMessage.setText(zipCodeMessage);
    }

    /**
     * @param cityMessage the cityMessage to set
     */
    public void setCityMessage(String cityMessage) {
        this.cityMessage.setText(cityMessage);
    }

    /**
     * @param emailMessage the emailMessage to set
     */
    public void setEmailMessage(String emailMessage) {
        this.emailMessage.setText(emailMessage);
    }

    /**
     * @param phoneMessage the phoneMessage to set
     */
    public void setPhoneMessage(String phoneMessage) {
        this.phoneMessage.setText(phoneMessage);
    }
    
    public void setResultText(String resultText) {
        this.resultText.setText(resultText);
    }
    
}
