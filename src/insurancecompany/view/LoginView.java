/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package insurancecompany.view;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author André
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
    
    public LoginView() {
        
        GridPane mainPane = new GridPane();
        mainPane.setId("loginPane");

        
        ColumnConstraints col1 = new ColumnConstraints(100);
        ColumnConstraints col2 = new ColumnConstraints(100);
        ColumnConstraints col3 = new ColumnConstraints(20);
        ColumnConstraints col4 = new ColumnConstraints(80);
        mainPane.getColumnConstraints().addAll(col1, col2, col3, col4);
        
        Label userName = new Label("Ansattnummer:");
        mainPane.add(userName, 0, 1);
        
        userTextField = new TextField();
        userTextField.setText("1000001");
        userTextField.setTooltip(new Tooltip("Eksisterende bruker med nummer: 1000001"));
        mainPane.add(userTextField, 1, 1, 2, 1);
        
        userTextFieldMessage = new Text();
        mainPane.add(userTextFieldMessage, 3, 1);
        
        Label passwordL = new Label("Passord");
        mainPane.add(passwordL, 0, 2);
        
        pwField = new PasswordField();
        pwField.setDisable(true);
        mainPane.add(pwField, 1, 2, 2, 1);
        
        pwMessage = new Text();
        mainPane.add(pwMessage, 3, 2);
        
        loginButton = new Button("Logg inn");
        mainPane.add(loginButton, 1, 3);
        
        Label fulllScreenLabel = new Label("Start i fullskjerm");
        fullScreenCheckBox = new CheckBox();
        fullScreenCheckBox.setSelected(true);
        
        mainPane.add(fulllScreenLabel, 1, 4);
        mainPane.add(fullScreenCheckBox, 2, 4);
        
        inputMessage = new Text();
        mainPane.add(inputMessage, 1, 5);

        scene = new Scene(mainPane, 400, 275);
        scene.getStylesheets().add("insurancecompany/resources/css/stylesheet.css");  
        
    }
    
    public void show(Stage stage) {
        stage.setTitle("Innlogging");
        stage.setScene(scene);
        stage.show();
    }
    
    public Button getLoginBtn() {
        return loginButton;
    }
    
    public String getUserTextField() {
        return userTextField.getText();
    }
    
    public String getPwField() {
        return pwField.getText();
    }
    
    public boolean getFullScreenCheckBoxValue() {
        return fullScreenCheckBox.isSelected();
    }

    /**
     * 
     * @param event 
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
