/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
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
    
    public LoginView() {
        
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        
        Label userName = new Label("Brukernavn:");
        grid.add(userName, 0, 1);
        
        userTextField = new TextField();
        grid.add(userTextField, 1, 1);
        
        Label passwordL = new Label("Passord");
        grid.add(passwordL, 0, 2);
        
        pwField = new PasswordField();
        grid.add(pwField, 1, 2);
        
        loginButton = new Button("Logg inn");
        grid.add(loginButton, 1, 3);
        
        GridPane pane = new GridPane();
        Label label1 = new Label("Test");
        pane.add(label1, 0, 0);
        grid.add(pane, 1, 4);
        
        
        
        scene = new Scene(grid, 300, 275);
        
    }
    
    public void show(Stage stage) {
        stage.setTitle("Innlogging");
        stage.setScene(scene);
        stage.show();
    }
    
    public Button getLoginBtn() {
        return loginButton;
    }
    
    public TextField getUserTextField() {
        return userTextField;
    }
    
    public PasswordField getPwField() {
        return pwField;
    }
    
}
