/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package insurancecompany.view.register.insurances;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
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
    private TextField premiumField;
    
    private Label areaMessage;
    private Label coverageMessage;
    private Label customerIdMessage;
    private Label excessMessage;
    
    private Button calculateButton;
    private Button registerButton;
    
    public TravelInsuranceRegistration() {
        mainPane = new GridPane();
        mainPane.setAlignment(Pos.CENTER);
        mainPane.setHgap(10);
        mainPane.setVgap(10);
        mainPane.setStyle("-fx-background-color: #E7E7FF;");
        mainPane.getColumnConstraints().addAll(new ColumnConstraints(200), 
                new ColumnConstraints(200), new ColumnConstraints(200));
        
        scene = new Scene(mainPane, 300, 275);
        
        areaField = new TextField();
        coverageField = new TextField();
        customerIdField = new TextField();
        excessField = new TextField();
        premiumField = new TextField();
        premiumField.setEditable(false);
        
        areaMessage = new Label();
        coverageMessage = new Label();
        customerIdMessage = new Label();
        excessMessage = new Label();
        
        calculateButton = new Button("Regn ut");
        registerButton = new Button("Registrer");
        
        mainPane.add(new Text("Registrer reiseforsikring"), 0, 0);
        
        mainPane.add(new Label("Kundenummer:"), 0, 1);
        mainPane.add(customerIdField, 1, 1);
        mainPane.add(customerIdMessage, 2, 1);
        
        mainPane.add(new Label("Type:"), 0, 2);
        mainPane.add(coverageField, 1, 2);
        mainPane.add(coverageMessage, 2, 2);
        
        mainPane.add(new Label("Egenandel:"), 0, 3);
        mainPane.add(excessField, 1, 3);
        mainPane.add(excessMessage, 2, 3);
        
        mainPane.add(new Label("Forsikringsområde:"), 0, 4);
        mainPane.add(areaField, 1, 4);
        mainPane.add(areaMessage, 2, 4);
        
        mainPane.add(new Label("Forsikringspremie:"), 0, 5);
        mainPane.add(premiumField, 1, 5);
        mainPane.add(calculateButton, 2, 5);
        
        mainPane.add(registerButton, 1, 6);
    }
    
    public GridPane getMainPane() {
        return mainPane;
    }
    
    public void show(Stage stage) {
        stage.setTitle("Registrering av reiseforsikring.");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @return the areaField
     */
    public TextField getAreaField() {
        return areaField;
    }

    /**
     * @return the coverageField
     */
    public TextField getCoverageField() {
        return coverageField;
    }

    /**
     * @return the customerIdField
     */
    public TextField getCustomerIdField() {
        return customerIdField;
    }

    /**
     * @return the excessField
     */
    public TextField getExcessField() {
        return excessField;
    }

    /**
     * @return the premiumField
     */
    public TextField getPremiumField() {
        return premiumField;
    }

    /**
     * @param areaMessage the areaMessage to set
     */
    public void setAreaMessage(Label areaMessage) {
        this.areaMessage = areaMessage;
    }

    /**
     * @param coverageMessage the coverageMessage to set
     */
    public void setCoverageMessage(Label coverageMessage) {
        this.coverageMessage = coverageMessage;
    }

    /**
     * @param customerIdMessage the customerIdMessage to set
     */
    public void setCustomerIdMessage(Label customerIdMessage) {
        this.customerIdMessage = customerIdMessage;
    }

    /**
     * @param excessMessage the excessMessage to set
     */
    public void setExcessMessage(Label excessMessage) {
        this.excessMessage = excessMessage;
    }

    /**
     * @return the calculateButton
     */
    public Button getCalculateButton() {
        return calculateButton;
    }

    /**
     * @return the registerButton
     */
    public Button getRegisterButton() {
        return registerButton;
    }
}