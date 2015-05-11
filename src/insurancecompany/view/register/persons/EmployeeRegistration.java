/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package insurancecompany.view.register.persons;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
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
public class EmployeeRegistration extends Application {
    
    private Scene scene;
    private GridPane mainPane;
    
    private TextField personalNumberField;
    private TextField firstNameField;
    private TextField lastNameField;    
    private TextField streetField;
    private TextField zipCodeField;
    private TextField cityField;
    private TextField emailField;
    private TextField phoneField;
    
    private Label personalNumberMessage;
    private Label firstNameMessage;
    private Label lastNameMessage;    
    private Label streetMessage;
    private Label zipCodeMessage;
    private Label cityMessage;
    private Label emailMessage;
    private Label phoneMessage;
    
    private Button registerButton;
    
    private Text resultText;
    
    public static void main(String[] args) {
        launch(args);
    }
    
    public void start(Stage stage) {
        show(stage);
    }
    
    public EmployeeRegistration() {
        mainPane = new GridPane();
        mainPane.setAlignment(Pos.CENTER);
        mainPane.setHgap(10);
        mainPane.setVgap(10);
        mainPane.setStyle("-fx-background-color: #E7E7FF;");
        
        scene = new Scene(getMainPane(), 300, 275);
        
        Text registerT = new Text("Registrer ansatt");
        mainPane.add(registerT, 1, 1);
        Label personalNumber = new Label("Personnummer:");
        mainPane.add(personalNumber, 1, 2);
        personalNumberField = new TextField();
        mainPane.add(personalNumberField, 2, 2);
        personalNumberMessage = new Label();
        mainPane.add(personalNumberMessage, 3, 2);
        Label firstName = new Label("Fornavn:");
        mainPane.add(firstName, 1, 3);
        firstNameField = new TextField();
        mainPane.add(firstNameField, 2, 3);
        firstNameMessage = new Label();
        mainPane.add(firstNameMessage, 3, 2);
        Label lastName = new Label("Etternavn:");
        mainPane.add(lastName, 1, 4);
        lastNameField = new TextField();
        mainPane.add(lastNameField, 2, 4);
        lastNameMessage = new Label();
        mainPane.add(lastNameMessage, 3, 2);
        Text adress = new Text("Adresse");
        mainPane.add(adress, 1, 5);
        Label street = new Label("Gate:");
        mainPane.add(street, 1, 6);
        streetField = new TextField();
        mainPane.add(streetField, 2, 6);
        streetMessage = new Label();
        mainPane.add(streetMessage, 3, 2);
        Label zipCode = new Label("Postboks:");
        mainPane.add(zipCode, 1, 7);
        zipCodeField = new TextField();
        mainPane.add(zipCodeField, 2, 7);
        zipCodeMessage = new Label();
        mainPane.add(zipCodeMessage, 3, 2);
        Label city = new Label("By:");
        mainPane.add(city, 1, 8);
        cityField = new TextField();
        mainPane.add(cityField, 2, 8);
        cityMessage = new Label();
        mainPane.add(cityMessage, 3, 2);
        Text contact = new Text("Kontakt");
        mainPane.add(contact, 1, 9);
        Label email = new Label("E-post:");
        mainPane.add(email, 1, 10);
        emailField = new TextField();
        mainPane.add(emailField, 2, 10);
        emailMessage = new Label();
        mainPane.add(emailMessage, 3, 2);
        Label phone = new Label("Telefonnummer:");
        mainPane.add(phone, 1, 11);
        phoneField = new TextField();
        mainPane.add(phoneField, 2, 11);
        phoneMessage = new Label();
        mainPane.add(phoneMessage, 3, 2);
        registerButton = new Button("Registrer");
        HBox hbBtn = new HBox();
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(registerButton);
        mainPane.add(hbBtn, 2, 12);
        resultText = new Text();
        
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
    
}
