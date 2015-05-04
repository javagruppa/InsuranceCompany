/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package insurancecompany.gui;

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
        
        scene = new Scene(getMainPane(), 300, 275);
        
        Text registerT = new Text("Registrer kunde");
        mainPane.add(registerT, 1, 1);
        Label personalNumber = new Label("Personnummer:");
        mainPane.add(personalNumber, 1, 2);
        personalNumberField = new TextField();
        mainPane.add(personalNumberField, 2, 2);
        Label firstName = new Label("Fornavn:");
        mainPane.add(firstName, 1, 3);
        firstNameField = new TextField();
        mainPane.add(firstNameField, 2, 3);
        Label lastName = new Label("Etternavn:");
        mainPane.add(lastName, 1, 4);
        lastNameField = new TextField();
        mainPane.add(lastNameField, 2, 4);
        Text adress = new Text("Adresse");
        mainPane.add(adress, 1, 5);
        Label street = new Label("Gate:");
        mainPane.add(street, 1, 6);
        streetField = new TextField();
        mainPane.add(streetField, 2, 6);
        Label zipCode = new Label("Postboks:");
        mainPane.add(zipCode, 1, 7);
        zipCodeField = new TextField();
        mainPane.add(zipCodeField, 2, 7);
        Label city = new Label("By:");
        mainPane.add(city, 1, 8);
        cityField = new TextField();
        mainPane.add(cityField, 2, 8);
        Text contact = new Text("Kontakt");
        mainPane.add(contact, 1, 9);
        Label email = new Label("E-post:");
        mainPane.add(email, 1, 10);
        emailField = new TextField();
        mainPane.add(emailField, 2, 10);
        Label phone = new Label("Telefonnummer:");
        mainPane.add(phone, 1, 11);
        phoneField = new TextField();
        mainPane.add(phoneField, 2, 11);
        registerButton = new Button("Registrer");
        HBox hbBtn = new HBox();
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(registerButton);
        mainPane.add(hbBtn, 2, 12);
        outputTextArea = new TextArea();
        outputTextArea.setPrefColumnCount(30);
        outputTextArea.setEditable(false);
        mainPane.add(outputTextArea, 3, 1, 1, 12);
        
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
    
}
