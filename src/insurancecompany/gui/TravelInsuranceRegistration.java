/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package insurancecompany.gui;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author Sindre
 */
public class TravelInsuranceRegistration {
    
    private Scene scene;
    private GridPane mainPane;
    
    private TextField areaField;
    private TextField coverageField;
    private TextField customerIdField;
    private TextField excessField;
    
    private Label areaMessage;
    private Label coverageMessage;
    private Label customerIdMessage;
    private Label excessMessage;
    
    private Button registerButton;
    private TextArea outputTextArea;
    
    public TravelInsuranceRegistration() {
        mainPane = new GridPane();
        mainPane.setAlignment(Pos.CENTER);
        mainPane.setHgap(10);
        mainPane.setVgap(10);
        mainPane.setStyle("-fx-background-color: #E7E7FF;");
        
        scene = new Scene(mainPane, 300, 275);
        
        areaField = new TextField();
        coverageField = new TextField();
        customerIdField = new TextField();
        excessField = new TextField();
        
        areaMessage = new Label();
        coverageMessage = new Label();
        customerIdMessage = new Label();
        excessMessage = new Label();
        
        registerButton = new Button("Registrer");
        outputTextArea = new TextArea();
        outputTextArea.setPrefColumnCount(30);
        outputTextArea.setEditable(false);
        
        mainPane.add(new Text("Registrer reiseforsikring"), 1, 1);
        
        mainPane.add(new Label("Kundenummer:"), 1, 2);
        mainPane.add(customerIdField, 2, 2);
        mainPane.add(customerIdMessage, 3, 2);
        
        mainPane.add(new Label("Type:"), 1, 3);
        mainPane.add(coverageField, 2, 3);
        mainPane.add(coverageMessage, 3, 3);
        
        mainPane.add(new Label("Egenandel:"), 1, 4);
        mainPane.add(excessField, 2, 4);
        mainPane.add(excessMessage, 3, 4);
        
        mainPane.add(new Label("Forsikringsområde:"), 1, 5);
        mainPane.add(areaField, 2, 5);
        mainPane.add(areaMessage, 3, 5);
        
        mainPane.add(registerButton, 2, 6);
        mainPane.add(outputTextArea, 4, 1, 1, 6);
    }
    
    public GridPane getMainPane() {
        return mainPane;
    }
    
    public void show(Stage stage) {
        stage.setTitle("Registrering av reiseforsikring.");
        stage.setScene(scene);
        stage.show();
    }
}