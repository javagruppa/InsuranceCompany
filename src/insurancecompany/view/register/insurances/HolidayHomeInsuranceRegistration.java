/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package insurancecompany.view.register.insurances;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
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
public class HolidayHomeInsuranceRegistration {
    
    private Scene scene;
    private GridPane mainPane;
    
    private TextField customerIdField;
    private TextField excessField;
    private TextField buildingAmountField;
    private TextField contentAmountField;
    private TextField areaField;
    private TextField yearField;
    private TextField materialField;
    private TextField typeField;
    private TextField streetField;
    private TextField zipCodeField;
    private TextField cityField;
    private TextField premiumField;
    
    private ComboBox rentalComboBox;
    
    private Label customerIdMessage;
    private Label excessMessage;
    private Label buildingAmountMessage;
    private Label contentAmountMessage;
    private Label rentalMessage;
    private Label areaMessage;
    private Label yearMessage;
    private Label materialMessage;
    private Label typeMessage;
    private Label streetMessage;
    private Label zipCodeMessage;
    private Label cityMessage;
    
    private Button calculateButton;
    private Button registerButton;
    
    public HolidayHomeInsuranceRegistration() {
        mainPane = new GridPane();
        mainPane.setAlignment(Pos.CENTER);
        mainPane.setHgap(10);
        mainPane.setVgap(10);
        mainPane.setStyle("-fx-background-color: #E7E7FF;");
        mainPane.getColumnConstraints().addAll(new ColumnConstraints(200), 
                new ColumnConstraints(200), new ColumnConstraints(200));
        
        scene = new Scene(mainPane, 300, 275);
        
        customerIdField = new TextField();
        excessField = new TextField();
        buildingAmountField = new TextField();
        contentAmountField = new TextField();
        areaField = new TextField();
        yearField = new TextField();
        materialField = new TextField();
        typeField = new TextField();
        streetField = new TextField();
        zipCodeField = new TextField();
        cityField = new TextField();
        premiumField = new TextField();
        
        rentalComboBox = new ComboBox();
        rentalComboBox.getItems().addAll("Ja", "Nei");
        
        customerIdMessage = new Label();
        excessMessage = new Label();
        buildingAmountMessage = new Label();
        contentAmountMessage = new Label();
        rentalMessage = new Label();
        areaMessage = new Label();
        yearMessage = new Label();
        materialMessage = new Label();
        typeMessage = new Label();
        streetMessage = new Label();
        zipCodeMessage = new Label();
        cityMessage = new Label();
        
        calculateButton = new Button("Regn ut");
        registerButton = new Button("Registrer");
        
        mainPane.add(new Text("Registrer hus- og innboforsikring"), 0, 0);
        
        mainPane.add(new Label("Personnummer:"), 0, 1);
        mainPane.add(customerIdField, 1, 1);
        mainPane.add(customerIdMessage, 2, 1);
        
        mainPane.add(new Label("Forsikringsbeløp for hus:"), 0, 2);
        mainPane.add(buildingAmountField, 1, 2);
        mainPane.add(buildingAmountMessage, 2, 2);
        
        mainPane.add(new Label("Forsikringsbeløp for innbo:"), 0, 3);
        mainPane.add(contentAmountField, 1, 3);
        mainPane.add(contentAmountMessage, 2, 3);
        
        mainPane.add(new Label("Leie:"), 0, 4);
        mainPane.add(rentalComboBox, 1, 4);
        mainPane.add(rentalMessage, 2, 4);
        
        mainPane.add(new Text("Hus"), 0, 5);
        
        mainPane.add(new Label("Areal:"), 0, 6);
        mainPane.add(areaField, 1, 6);
        mainPane.add(areaMessage, 2, 6);
        
        mainPane.add(new Label("Bygget i år:"), 0, 7);
        mainPane.add(yearField, 1, 7);
        mainPane.add(yearMessage, 2, 7);
        
        mainPane.add(new Label("Materiale:"), 0, 8);
        mainPane.add(materialField, 1, 8);
        mainPane.add(materialMessage, 2, 8);
        
        mainPane.add(new Label("Type:"), 0, 9);
        mainPane.add(typeField, 1, 9);
        mainPane.add(typeMessage, 2, 9);
        
        mainPane.add(new Text("Adresse"), 0, 10);
        
        mainPane.add(new Label("Gate:"), 0, 11);
        mainPane.add(streetField, 1, 11);
        mainPane.add(streetMessage, 2, 11);
        
        mainPane.add(new Label("Postboks:"), 0, 12);
        mainPane.add(zipCodeField, 1, 12);
        mainPane.add(zipCodeMessage, 2, 12);
        
        mainPane.add(new Label("By:"), 0, 13);
        mainPane.add(cityField, 1, 13);
        mainPane.add(cityMessage, 2, 13);
        
        mainPane.add(new Label("Forsikringspremie:"), 0, 14);
        mainPane.add(premiumField, 1, 14);
        mainPane.add(calculateButton, 2, 14);
        
        mainPane.add(registerButton, 1, 15);
    }
    
    public GridPane getMainPane() {
        return mainPane;
    }
    
    public void show(Stage stage) {
        stage.setTitle("Registrering av hus- og innboforsikring.");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @return the customerIdField
     */
    public String getCustomerId() {
        return customerIdField.getText();
    }

    /**
     * @return the excessField
     */
    public String getExcess() {
        return excessField.getText();
    }

    /**
     * @return the buildingAmountField
     */
    public String getBuildingAmount() {
        return buildingAmountField.getText();
    }

    /**
     * @return the contentAmountField
     */
    public String getContentAmount() {
        return contentAmountField.getText();
    }

    /**
     * @return the areaField
     */
    public String getArea() {
        return areaField.getText();
    }

    /**
     * @return the yearField
     */
    public String getYear() {
        return yearField.getText();
    }

    /**
     * @return the materialField
     */
    public String getMaterial() {
        return materialField.getText();
    }

    /**
     * @return the typeField
     */
    public String getType() {
        return typeField.getText();
    }

    /**
     * @return the streetField
     */
    public String getStreet() {
        return streetField.getText();
    }

    /**
     * @return the zipCodeField
     */
    public String getZipCode() {
        return zipCodeField.getText();
    }

    /**
     * @return the cityField
     */
    public String getCity() {
        return cityField.getText();
    }

    /**
     * @return the premiumField
     */
    public String getPremium() {
        return premiumField.getText();
    }

    /**
     * @return the rentalComboBox
     */
    public boolean getRental() {
        return rentalComboBox.getValue().equals("Ja)");
    }

    /**
     * @param customerIdMessage the customerIdMessage to set
     */
    public void setCustomerIdMessage(String customerIdMessage) {
        this.customerIdMessage.setText(customerIdMessage);
    }

    /**
     * @param excessMessage the excessMessage to set
     */
    public void setExcessMessage(String excessMessage) {
        this.excessMessage.setText(excessMessage);
    }

    /**
     * @param buildingAmountMessage the buildingAmountMessage to set
     */
    public void setBuildingAmountMessage(String buildingAmountMessage) {
        this.buildingAmountMessage.setText(buildingAmountMessage);
    }

    /**
     * @param contentAmountMessage the contentAmountMessage to set
     */
    public void setContentAmountMessage(String contentAmountMessage) {
        this.contentAmountMessage.setText(contentAmountMessage);
    }

    /**
     * @param rentalMessage the rentalMessage to set
     */
    public void setRentalMessage(String rentalMessage) {
        this.rentalMessage.setText(rentalMessage);
    }

    /**
     * @param areaMessage the areaMessage to set
     */
    public void setAreaMessage(String areaMessage) {
        this.areaMessage.setText(areaMessage);
    }

    /**
     * @param yearMessage the yearMessage to set
     */
    public void setYearMessage(String yearMessage) {
        this.yearMessage.setText(yearMessage);
    }

    /**
     * @param materialMessage the materialMessage to set
     */
    public void setMaterialMessage(String materialMessage) {
        this.materialMessage.setText(materialMessage);
    }

    /**
     * @param typeMessage the typeMessage to set
     */
    public void setTypeMessage(String typeMessage) {
        this.typeMessage.setText(typeMessage);
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

    public void setCalculateButtonEventHandler(EventHandler<ActionEvent> value) {
        calculateButton.setOnAction(value);
    }

    public void setRegisterButtonEventHandler(EventHandler<ActionEvent> value) {
        registerButton.setOnAction(value);
    }
}
