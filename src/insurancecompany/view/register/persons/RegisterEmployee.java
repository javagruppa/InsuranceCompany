package insurancecompany.view.register.persons;

import insurancecompany.misc.enums.EmployeeType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

/**
 * This class creates the graphical user interface (GUI) for registration of 
 * employees. It creates a pane which is sent to the controller and 
 * thereafter displayed.
 * 
 * <p> The pane consists of a column of text fields and a registration button.
 * The text fields are where the user inputs the information about the employee.
 * When the button is pressed the information is sent through get methods to the
 * controller, which validates them and executes the registration.
 * 
 * @author André
 * @author Sindre
 * @author Carl
 */
public class RegisterEmployee {
    
    // Declaration of the main pane which is sent to the controller.
    private GridPane mainPane;
    
    // Declaration of all the nodes.
    private Button registerButton;
    private ComboBox<EmployeeType> positionCombo;
    private Text positionMessage;
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
    public RegisterEmployee() {
        
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
        Text registerT = new Text("Registrer ansatt");
        registerT.setId("textTitle");
        Label position = new Label("Posisjon");
        positionCombo = new ComboBox<>();
        populatePositionCombo();
        positionMessage = new Text();
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
        Label city = new Label("Poststed:");
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
        mainPane.add(position, 0, 2);
        mainPane.add(positionCombo, 1, 2);
        mainPane.add(positionMessage, 2, 2);
        mainPane.add(personalNumber, 0, 3);
        mainPane.add(personalNumberField, 1, 3);
        mainPane.add(personalNumberMessage, 2, 3);
        mainPane.add(firstName, 0, 4);
        mainPane.add(firstNameField, 1, 4);
        mainPane.add(firstNameMessage, 2, 4);
        mainPane.add(lastName, 0, 5);
        mainPane.add(lastNameField, 1, 5);
        mainPane.add(lastNameMessage, 2, 5);
        mainPane.add(adress, 0, 6);
        mainPane.add(street, 0, 7);
        mainPane.add(streetField, 1, 7);
        mainPane.add(streetMessage, 2, 7);
        mainPane.add(zipCode, 0, 8);
        mainPane.add(zipCodeField, 1, 8);
        mainPane.add(zipCodeMessage, 2, 8);
        mainPane.add(city, 0, 9);
        mainPane.add(cityField, 1, 9);
        mainPane.add(cityMessage, 2, 9);
        mainPane.add(contact, 0, 10);
        mainPane.add(email, 0, 11);
        mainPane.add(emailField, 1, 11);
        mainPane.add(emailMessage, 2, 11);
        mainPane.add(phone, 0, 12);
        mainPane.add(phoneField, 1, 12);
        mainPane.add(phoneMessage, 2, 12);
        mainPane.add(hbBtn, 1, 13);
        mainPane.add(resultMessage, 0, 14, 4, 2);
    }
    
    /** Sets the content of the ComboBox positionCombo. */
    private void populatePositionCombo() {
        ObservableList<EmployeeType> obList;
        obList = FXCollections.observableArrayList(EmployeeType.values()); 
        positionCombo.getItems().addAll(obList);
        positionCombo.setPrefWidth(150);
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

    /** @return The value of positionCombo. */
    public EmployeeType getPosition() {
        return positionCombo.getValue();
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
    public void setPositionMessage(String message) {
        this.positionMessage.setFill(Color.FIREBRICK);
        this.positionMessage.setText(message);
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
    
    /** Clears all messages. */
    public void clearMessages() {
        positionMessage.setText("");
        personalNumberMessage.setText("");
        firstNameMessage.setText("");
        lastNameMessage.setText("");
        streetMessage.setText("");
        zipCodeMessage.setText("");
        cityMessage.setText("");
        emailMessage.setText("");
        phoneMessage.setText("");
    }
    
    /** Clears all text fields and combo boxes. */
    public void clearView() {
        positionCombo.setValue(null);
        personalNumberField.setText("");
        firstNameField.setText("");
        lastNameField.setText("");
        streetField.setText("");
        zipCodeField.setText("");
        cityField.setText("");
        emailField.setText("");
        phoneField.setText("");
    }
}