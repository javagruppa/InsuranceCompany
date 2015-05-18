package insurancecompany.view.register.persons;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

/**
 * This class creates the graphical user interface (GUI) for registration of 
 * customers. It creates a pane which is sent to the controller and 
 * thereafter displayed.
 * 
 * <p> The pane consists of a column of text fields and a registration button.
 * The text fields are where the user inputs the information about the customer.
 * When the button is pressed the information is sent through get methods to the
 * controller, which validates them and executes the registration.
 * 
 * @author André
 * @author Sindre
 */
public class RegisterCustomer {
    
    // Declaration of the main pane which is sent to the controller.
    private GridPane mainPane;
    
    // Declaration of all the nodes.
    private Button registerButton;
    private Text personalNumberMessage;
    private Text firstNameMessage;
    private Text lastNameMessage;    
    private Text streetMessage;
    private Text zipCodeMessage;
    private Text cityMessage;
    private Text emailMessage;
    private Text phoneMessage;
    private Text resultMessage;
    private TextField personalNumberField;
    private TextField firstNameField;
    private TextField lastNameField;    
    private TextField streetField;
    private TextField zipCodeField;
    private TextField cityField;
    private TextField emailField;
    private TextField phoneField;
    
    /**
     * Default constructor. Initializes all field and sets up the view.
     */
    public RegisterCustomer() {
        
        // Initialization of the pane.
        mainPane = new GridPane();
        
        // Sets the CSS ID to the pane.
        mainPane.setId("innerPane");
        
        // Sets up column constraints. The width is in pixels.
        ColumnConstraints col1 = new ColumnConstraints(150);
        ColumnConstraints col2 = new ColumnConstraints(150);
        ColumnConstraints col3 = new ColumnConstraints(150);
        mainPane.getColumnConstraints().addAll(col1, col2, col3);
        
        // Initialization of all the nodes. As well as declaration and
        // initializarion of all Texts and Labels that aren't fields.
        Text registerT = new Text("Registrer kunde");
        registerT.setId("textTitle");
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
        adress.setId("textTitle");
        Label street = new Label("Gate:");
        streetField = new TextField();
        streetMessage = new Text();
        Label zipCode = new Label("Postnummer:");
        zipCodeField = new TextField();
        zipCodeMessage = new Text();
        Label city = new Label("By:");
        cityField = new TextField();
        cityMessage = new Text();
        Text contact = new Text("Kontakt");
        contact.setId("textTitle");
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
        resultMessage = new Text();
        
        // Adds the nodes to the main pane.
        mainPane.add(registerT, 0, 1);
        mainPane.add(personalNumber, 0, 2);
        mainPane.add(personalNumberField, 1, 2);
        mainPane.add(personalNumberMessage, 2, 2);
        mainPane.add(firstName, 0, 3);
        mainPane.add(firstNameField, 1, 3);
        mainPane.add(firstNameMessage, 2, 3);
        mainPane.add(lastName, 0, 4);
        mainPane.add(lastNameField, 1, 4);
        mainPane.add(lastNameMessage, 2, 4);
        mainPane.add(adress, 0, 5);
        mainPane.add(street, 0, 6);
        mainPane.add(streetField, 1, 6);
        mainPane.add(streetMessage, 2, 6);
        mainPane.add(zipCode, 0, 7);
        mainPane.add(zipCodeField, 1, 7);
        mainPane.add(zipCodeMessage, 2, 7);
        mainPane.add(city, 0, 8);
        mainPane.add(cityField, 1, 8);
        mainPane.add(cityMessage, 2, 8);
        mainPane.add(contact, 0, 9);
        mainPane.add(email, 0, 10);
        mainPane.add(emailField, 1, 10);
        mainPane.add(emailMessage, 2, 10);
        mainPane.add(phone, 0, 11);
        mainPane.add(phoneField, 1, 11);
        mainPane.add(phoneMessage, 2, 11);
        mainPane.add(hbBtn, 1, 12);
        mainPane.add(resultMessage, 0, 13, 4, 2);
    }
       
    /**
     * Sets the event handler for the registerButton.
     * 
     * @param value The event handler to set.
     */
    public void setRegisterEventHandler(EventHandler<ActionEvent> value) {
        registerButton.setOnAction(value);
    }
    
    /** @return The main pane of this class. */
    public GridPane getMainPane() {
        return mainPane;
    }

    /** @return The value of personalNumberField. */
    public String getPersonalNumber() {
        return personalNumberField.getText();
    }

    /** @return The value of firstNameField. */
    public String getFirstName() {
        return firstNameField.getText();
    }

    /** @return The value of lastNameField. */
    public String getLastName() {
        return lastNameField.getText();
    }

    /** @return The value of streetField. */
    public String getStreet() {
        return streetField.getText();
    }

    /** @return The value of zipCodeField. */
    public String getZipCode() {
        return zipCodeField.getText();
    }

    /** @return The value of cityField. */
    public String getCity() {
        return cityField.getText();
    }

    /** @return The value of emailField. */
    public String getEmail() {
        return emailField.getText();
    }

    /** @return The value of phoneField. */
    public String getPhone() {
        return phoneField.getText();
    }

    /** @param message The message to set. */
    public void setPersonalNumberMessage(String message) {
        this.personalNumberMessage.setFill(Color.FIREBRICK);
        this.personalNumberMessage.setText(message);
    }

    /** @param message The message to set. */
    public void setFirstNameMessage(String message) {
        this.firstNameMessage.setFill(Color.FIREBRICK);
        this.firstNameMessage.setText(message);
    }

    /** @param message The message to set. */
    public void setLastNameMessage(String message) {
        this.lastNameMessage.setFill(Color.FIREBRICK);
        this.lastNameMessage.setText(message);
    }

    /** @param message The message to set. */
    public void setStreetMessage(String message) {
        this.streetMessage.setFill(Color.FIREBRICK);
        this.streetMessage.setText(message);
    }

    /** @param message The message to set. */
    public void setZipCodeMessage(String message) {
        this.zipCodeMessage.setFill(Color.FIREBRICK);
        this.zipCodeMessage.setText(message);
    }

    /** @param message The message to set. */
    public void setCityMessage(String message) {
        this.cityMessage.setFill(Color.FIREBRICK);
        this.cityMessage.setText(message);
    }

    /** @param message The message to set. */
    public void setEmailMessage(String message) {
        this.emailMessage.setFill(Color.FIREBRICK);
        this.emailMessage.setText(message);
    }

    /** @param message The message to set. */
    public void setPhoneMessage(String message) {
        this.phoneMessage.setFill(Color.FIREBRICK);
        this.phoneMessage.setText(message);
    }
    
    /** @param message The message to set. */
    public void setResultMessage(String message) {
        this.resultMessage.setText(message);
    }
}