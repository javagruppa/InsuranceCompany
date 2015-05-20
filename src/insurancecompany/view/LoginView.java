package insurancecompany.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/** The login view of the application.
 *
 * @author André
 * 
 * @since 19.05.2015
 */
public class LoginView {
    
    private Scene scene;
    private TextField userTextField;
    private PasswordField pwField;
    private Button loginButton;
    private CheckBox fullScreenCheckBox;
    private Text userTextFieldMessage;
    private Text pwMessage;
    private Text inputMessage;
    
    /**
     * Constructs and sets up the login view.
     */
    public LoginView() {
               
        GridPane gridPane = new GridPane();
        gridPane.setId("loginPane");
        
        ColumnConstraints col1 = new ColumnConstraints(100);
        ColumnConstraints col2 = new ColumnConstraints(100);
        ColumnConstraints col3 = new ColumnConstraints(20);
        ColumnConstraints col4 = new ColumnConstraints(80);
        gridPane.getColumnConstraints().addAll(col1, col2, col3, col4);
        
        /*
        Image ifLogo = new Image("insurancecompany/resources/images/asiflogostor.png");
        gridPane.add(new ImageView(ifLogo), 0, 1); // add via stackPane maybe?
        */
        
        Label userName = new Label("Ansattnummer:");
        gridPane.add(userName, 0, 2);
        
        userTextField = new TextField();
        userTextField.setText("1000001");
        userTextField.setTooltip(new Tooltip("Eksisterende bruker med nummer: 1000001"));
        gridPane.add(userTextField, 1, 2, 2, 1);
        
        userTextFieldMessage = new Text();
        gridPane.add(userTextFieldMessage, 3, 2);
        
        Label passwordL = new Label("Passord");
        gridPane.add(passwordL, 0, 3);
        
        pwField = new PasswordField();
        pwField.setDisable(true);
        gridPane.add(pwField, 1, 3, 2, 1);
        
        pwMessage = new Text();
        gridPane.add(pwMessage, 3, 2);
        
        loginButton = new Button("Logg inn");
        gridPane.add(loginButton, 1, 4);
        
        Label fulllScreenLabel = new Label("Start i fullskjerm");
        fullScreenCheckBox = new CheckBox();
        fullScreenCheckBox.setSelected(true);
        
        gridPane.add(fulllScreenLabel, 1, 5);
        gridPane.add(fullScreenCheckBox, 2, 5);
        
        inputMessage = new Text();
        gridPane.add(inputMessage, 1, 6);

        scene = new Scene(gridPane, 400, 275);
        scene.getStylesheets().add("insurancecompany/resources/css/stylesheet.css");  
        
    }
    
    /**
     * Shows the view.
     * @param stage the stage to be set
     */
    public void show(Stage stage) {
        stage.setTitle("Innlogging");
        stage.setScene(scene);
        stage.show();
    }
    
    /**
     * Returns the login button
     * @return the login button
     */
    public Button getLoginBtn() {
        return loginButton;
    }
    
    /**
     * 
     * @return the text from the userTextField
     */
    public String getUserTextField() {
        return userTextField.getText();
    }
    
    /**
     * 
     * @return  the text from the passwordField
     */
    public String getPwField() {
        return pwField.getText();
    }
    
    /**
     * 
     * @return  whether or not the fullscreen-box is checked
     */
    public boolean getFullScreenCheckBoxValue() {
        return fullScreenCheckBox.isSelected();
    }

    /**Ssets the eventhandler og the login button
     * 
     * @param event the event to be set
     */
    public void setLoginButtonEventHandler(EventHandler<ActionEvent> event) {
        this.loginButton.setOnAction(event);
    }

    /**
     * @param inputMessage the inputMessage to set
     */
    public void setInputMessage(String inputMessage) {
        this.inputMessage.setText(inputMessage);
    }

    /**
     * @param userTextFieldMessage the userTextFieldMessage to set
     */
    public void setUserTextFieldMessage(String userTextFieldMessage) {
        this.userTextFieldMessage.setText(userTextFieldMessage);
    }

    /**
     * @param pwMessage the pwMessage to set
     */
    public void setPwMessage(String pwMessage) {
        this.pwMessage.setText(pwMessage);
    }
    
}
