/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package insurancecompany.view.register;

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
public class CustomerRegistration extends Application {
    
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
    private TextArea outputTextArea;
    
    public static void main(String[] args) {
        launch(args);
    }
    
    public void start(Stage stage) {
        show(stage);
    }
    
    public CustomerRegistration() {
        mainPane = new GridPane();
        mainPane.setAlignment(Pos.CENTER);
        mainPane.setHgap(10);
        mainPane.setVgap(10);
        mainPane.setStyle("-fx-background-color: #E7E7FF;");
        
        scene = new Scene(getMainPane(), 300, 275);
        
        Text registerT = new Text("Registrer kunde");
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
        outputTextArea = new TextArea();
        outputTextArea.setPrefColumnCount(30);
        outputTextArea.setEditable(false);
        mainPane.add(outputTextArea, 4, 1, 1, 12);
        
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
    public TextField getPersonalNumberField() {
        return personalNumberField;
    }

    /**
     * @return the firstNameField
     */
    public TextField getFirstNameField() {
        return firstNameField;
    }

    /**
     * @return the lastNameField
     */
    public TextField getLastNameField() {
        return lastNameField;
    }

    /**
     * @return the streetField
     */
    public TextField getStreetField() {
        return streetField;
    }

    /**
     * @return the zipCodeField
     */
    public TextField getZipCodeField() {
        return zipCodeField;
    }

    /**
     * @return the cityField
     */
    public TextField getCityField() {
        return cityField;
    }

    /**
     * @return the emailField
     */
    public TextField getEmailField() {
        return emailField;
    }

    /**
     * @return the phoneField
     */
    public TextField getPhoneField() {
        return phoneField;
    }

    /**
     * @return the registerButton
     */
    public Button getRegisterButton() {
        return registerButton;
    }

    /**
     * 
     * @param text 
     */
    public void setOutputTextArea(String text) {
        outputTextArea.setText(text);
    }

    /**
     * @param personalNumberMessage the personalNumberMessage to set
     */
    public void setPersonalNumberMessage(Label personalNumberMessage) {
        this.personalNumberMessage = personalNumberMessage;
    }

    /**
     * @param firstNameMessage the firstNameMessage to set
     */
    public void setFirstNameMessage(Label firstNameMessage) {
        this.firstNameMessage = firstNameMessage;
    }

    /**
     * @param lastNameMessage the lastNameMessage to set
     */
    public void setLastNameMessage(Label lastNameMessage) {
        this.lastNameMessage = lastNameMessage;
    }

    /**
     * @param streetMessage the streetMessage to set
     */
    public void setStreetMessage(Label streetMessage) {
        this.streetMessage = streetMessage;
    }

    /**
     * @param zipCodeMessage the zipCodeMessage to set
     */
    public void setZipCodeMessage(Label zipCodeMessage) {
        this.zipCodeMessage = zipCodeMessage;
    }

    /**
     * @param cityMessage the cityMessage to set
     */
    public void setCityMessage(Label cityMessage) {
        this.cityMessage = cityMessage;
    }

    /**
     * @param emailMessage the emailMessage to set
     */
    public void setEmailMessage(Label emailMessage) {
        this.emailMessage = emailMessage;
    }

    /**
     * @param phoneMessage the phoneMessage to set
     */
    public void setPhoneMessage(Label phoneMessage) {
        this.phoneMessage = phoneMessage;
    }
    
}
