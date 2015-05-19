package insurancecompany.view.register.insurances;

import insurancecompany.misc.coverages.HolidayHomeInsuranceCoverage;
import insurancecompany.misc.hometypes.HolidayHomeType;
import insurancecompany.model.insurances.Insurance;
import insurancecompany.model.properties.PropertyMaterial;
import java.util.List;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

/**
 * This class creates the graphical user interface (GUI) for registration of 
 * holiday home insurances. It creates a pane which is sent to the controller 
 * and thereafter displayed.
 * 
 * <p> The pane consists of two parts; one to the left and one to the right.
 * 
 * <p> On the left the user chooses the customer. It also shows information
 * about the customer and its existing insurances.
 * 
 * <p> On the right the user inputs the information about the insurance. These 
 * are sent through get methods to the controller, which validates them and 
 * executes the registration.
 * 
 * @author Carl
 * @author Sindre
 */
public class RegisterHolidayHomeInsurance {
    
    // Declaration of the main pane which is sent to the controller.
    private GridPane mainPane;
    
    // Declaration of all the nodes in the left part.
    private Button searchCustomerIdButton;
    private Button searchPersonalNumberButton;
    private Text customerSelectedMessage;
    private TextArea customerArea;
    private TextField customerIdField;
    private TextField personalNumberField;
    private TableView<Insurance> insurancesTable;
    private TableColumn<Insurance, String> insuranceTypeColumn;
    private TableColumn<Insurance, String> insuranceCoverageColum;
    private TableColumn<Insurance, Integer> insuranceIdColumn;
    private int selectedCustomerId;
    
    // Declaration of all the nodes in the right part.
    private Button registerButton;
    private ComboBox<HolidayHomeInsuranceCoverage> coverageCombo;
    private ComboBox<String> excessCombo;
    private ComboBox<PropertyMaterial> materialCombo;
    private ComboBox<String> rentalCombo;
    private ComboBox<HolidayHomeType> typeCombo;
    private Text areaMessage;
    private Text cityMessage;
    private Text coverageMessage;
    private Text excessMessage;
    private Text materialMessage;
    private Text rentalMessage;
    private Text streetMessage;
    private Text typeMessage;
    private Text yearMessage;
    private Text zipCodeMessage;
    private Text registerButtonMessage;
    private TextField areaField;
    private TextField cityField;
    private TextField streetField;
    private TextField yearField;
    private TextField zipCodeField;
    
    /**
     * Default constructor. Initializes all field and sets up the view.
     */
    public RegisterHolidayHomeInsurance() {
        
        // Initialization of the pane.
        mainPane = new GridPane();
        
        // Sets the CSS ID to the pane.
        mainPane.setId("innerPane");
        
        // Sets up column constraints. The width is in pixels.
        ColumnConstraints col1 = new ColumnConstraints(120);
        ColumnConstraints col2 = new ColumnConstraints(100);
        ColumnConstraints col3 = new ColumnConstraints(40);
        ColumnConstraints col4 = new ColumnConstraints(50);
        ColumnConstraints col5 = new ColumnConstraints(150);
        ColumnConstraints col6 = new ColumnConstraints(150);
        ColumnConstraints col7 = new ColumnConstraints(150);
        mainPane.getColumnConstraints().addAll(col1, col2, col3, col4, col5, col6, col7);
        
        // Initialization of all the nodes in the left part.
        customerIdField = new TextField();
        personalNumberField = new TextField();
        customerArea = new TextArea();
        customerArea.setEditable(false);
        customerArea.setPrefColumnCount(2);
        customerArea.setPrefRowCount(4);
        insurancesTable = new TableView();
        insurancesTable.setPrefHeight(150);
        insuranceTypeColumn = new TableColumn<>("Forsikring");
        insuranceCoverageColum = new TableColumn<>("Dekning");
        insuranceIdColumn = new TableColumn<>("Forsikringsid");
        insurancesTable.getColumns().addAll(insuranceTypeColumn, insuranceCoverageColum, insuranceIdColumn);
        insurancesTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        searchCustomerIdButton = new Button("Søk");
        searchPersonalNumberButton = new Button("Søk");
        customerSelectedMessage = new Text();
        
        // Initialization of all the nodes in the right part.
        coverageCombo = new ComboBox<>();
        populateCoverageCombo();
        excessCombo = new ComboBox<>();
        populateExcessCombo();
        materialCombo = new ComboBox<>();
        populateMaterialCombo();
        rentalCombo = new ComboBox<>();
        populateRentalCombo();
        typeCombo = new ComboBox<>();
        populateTypeCombo();
        areaField = new TextField();
        cityField = new TextField();
        streetField = new TextField();
        yearField = new TextField();
        zipCodeField = new TextField();
        areaMessage = new Text();
        cityMessage = new Text();
        coverageMessage = new Text();
        excessMessage = new Text();
        materialMessage = new Text();
        rentalMessage = new Text();
        streetMessage = new Text();
        typeMessage = new Text();
        yearMessage = new Text();
        zipCodeMessage = new Text();
        registerButtonMessage = new Text();
        registerButton = new Button("Registrer");
        
        // Declaration and initialization of the texts and labels which are 
        // used in the view and are not fields.
        Text insurancesTitle = new Text("Eksisterende forsikringer til denne kunden:");
        insurancesTitle.setId("textTitle");
        Text resultTitle = new Text("Valgt kunde:");
        resultTitle.setId("textTitle");
        Text selectCustomerTitle = new Text("Velg først en kunde i registeret:");
        selectCustomerTitle.setId("textTitle");
        Text insuranceOptionsTitle = new Text("Betingelser:");
        insuranceOptionsTitle.setId("textTitle");
        Text houseTitle = new Text("Hus:");
        houseTitle.setId("textTitle");
        Text addressTitle = new Text("Adresse:");
        addressTitle.setId("textTitle");
        Label customerIdLabel = new Label("Kundenummer:");
        Label personalNumberLabel = new Label("Personnummer:");
        Label areaLabel = new Label("Areal:");
        Label cityLabel = new Label("Poststed:");
        Label coverageLabel = new Label("Dekning:");
        Label excessLabel = new Label("Egenandel:");
        Label materialLabel = new Label("Materiale:");
        Label rentalLabel = new Label("For leie:");
        Label streetLabel = new Label("Gate:");
        Label typeLabel = new Label("Type:");
        Label yearLabel = new Label("Byggeår:");
        Label zipCodeLabel = new Label("Postnummer:");
        
        // Adds the nodes to the left part.
        mainPane.add(selectCustomerTitle, 0, 0);
        mainPane.add(customerIdLabel, 0, 1);
        mainPane.add(customerIdField, 1, 1);
        mainPane.add(searchCustomerIdButton, 2, 1);
        mainPane.add(personalNumberLabel, 0, 2);
        mainPane.add(personalNumberField, 1, 2);
        mainPane.add(searchPersonalNumberButton, 2, 2);
        mainPane.add(resultTitle, 0, 3);
        mainPane.add(customerArea, 0, 4, 3, 5);
        mainPane.add(customerSelectedMessage, 0, 9);
        mainPane.add(insurancesTitle, 0, 10);
        mainPane.add(insurancesTable, 0, 11, 3, 5);
        
        // Adds the nodes to the right part.
        mainPane.add(insuranceOptionsTitle, 4, 0);
        mainPane.add(coverageLabel, 4, 1);
        mainPane.add(coverageCombo, 5, 1);
        mainPane.add(coverageMessage, 6, 1);
        mainPane.add(excessLabel, 4, 2);
        mainPane.add(excessCombo, 5, 2);
        mainPane.add(excessMessage, 6, 2);
        mainPane.add(rentalLabel, 4, 3);
        mainPane.add(rentalCombo, 5, 3);
        mainPane.add(rentalMessage, 6, 3);
        mainPane.add(houseTitle, 4, 4);
        mainPane.add(areaLabel, 4, 5);
        mainPane.add(areaField, 5, 5);
        mainPane.add(areaMessage, 6, 5);
        mainPane.add(yearLabel, 4, 6);
        mainPane.add(yearField, 5, 6);
        mainPane.add(yearMessage, 6, 6);
        mainPane.add(materialLabel, 4, 7);
        mainPane.add(materialCombo, 5, 7);
        mainPane.add(materialMessage, 6, 7);
        mainPane.add(typeLabel, 4, 8);
        mainPane.add(typeCombo, 5, 8);
        mainPane.add(typeMessage, 6, 8);
        mainPane.add(addressTitle, 4, 9);
        mainPane.add(streetLabel, 4, 10);
        mainPane.add(streetField, 5, 10);
        mainPane.add(streetMessage, 6, 10);
        mainPane.add(zipCodeLabel, 4, 11);
        mainPane.add(zipCodeField, 5, 11);
        mainPane.add(zipCodeMessage, 6, 11);
        mainPane.add(cityLabel, 4, 12);
        mainPane.add(cityField, 5, 12);
        mainPane.add(cityMessage, 6, 12);
        mainPane.add(registerButton, 5, 13);
        mainPane.add(registerButtonMessage, 5, 14);
    }
    
    /** Sets the content of the ComboBox coverageCombo. */
    private void populateCoverageCombo() {
        ObservableList<HolidayHomeInsuranceCoverage> obList;
        obList = FXCollections.observableArrayList(HolidayHomeInsuranceCoverage.values()); 
        coverageCombo.getItems().setAll(obList);
        coverageCombo.setPrefWidth(150);
    }
    
    /** Sets the content of the ComboBox excessCombo. */
    private void populateExcessCombo() {
        ObservableList<String> excess = FXCollections.observableArrayList();
        excess.addAll("0", "2000", "4000", "6000", "8000", "10000", 
                "12000", "14000", "16000", "18000", "20000");
        excessCombo.getItems().setAll(excess);
        excessCombo.setPrefWidth(150);
    }
    
    /** Sets the content of the ComboBox materialCombo. */
    private void populateMaterialCombo() {
        ObservableList<PropertyMaterial> obList;
        obList = FXCollections.observableArrayList(PropertyMaterial.values()); 
        materialCombo.getItems().setAll(obList);
        materialCombo.setPrefWidth(150);
    }
    
    /** Sets the content of the ComboBox rentalCombo. */
    private void populateRentalCombo() {
        ObservableList<String> rental = FXCollections.observableArrayList();  
        rental.addAll("Ja", "Nei");
        rentalCombo.getItems().setAll(rental);
        rentalCombo.setPrefWidth(150);
    }
    
    /** Sets the content of the ComboBox typeCombo. */
    private void populateTypeCombo() {
        ObservableList<HolidayHomeType> obList;
        obList = FXCollections.observableArrayList(HolidayHomeType.values()); 
        typeCombo.getItems().setAll(obList);
        typeCombo.setPrefWidth(150);
    }
    
    /**
     * Sets the content of the TableView insurancesTable. The table will 
     * consist of the insurances in the parameter list.
     * 
     * @param insurances The list of insurances which will be displayed in the 
     * table.
     */
    public void populateInsurancesTable(List<Insurance> insurances) {
        ObservableList<Insurance> obList = FXCollections.observableArrayList(insurances);
        insurancesTable.setItems(obList);
        
        insuranceTypeColumn.setCellValueFactory((cellData) -> {
                if ( cellData.getValue() != null) {
                    return new SimpleStringProperty(cellData.getValue().getType());
                } else {
                    return new SimpleStringProperty("<no name>");
                }
        });
        insuranceCoverageColum.setCellValueFactory((cellData) -> {
                if ( cellData.getValue() != null) {
                    return new SimpleObjectProperty<>(cellData.getValue().getCoverage().toString());
                } else {
                    return new SimpleObjectProperty(0);
                }
        });
        insuranceIdColumn.setCellValueFactory((cellData) -> {
                if ( cellData.getValue() != null) {
                    return new SimpleObjectProperty<>(cellData.getValue().getInsuranceId());
                } else {
                    return new SimpleObjectProperty(0);
                }
        });   
    }
    
    /**
     * Sets the event handler for the registerButton.
     * 
     * @param value The event handler to set.
     */
    public void setRegisterEventHandler(EventHandler<ActionEvent> value) {
        registerButton.setOnAction(value);
    }
    
    /**
     * Sets the event handler for the searchCustomerIdButton.
     * 
     * @param value The event handler to set.
     */
    public void setSearchCustomerIdEventHandler(EventHandler<ActionEvent> value) {
        searchCustomerIdButton.setOnAction(value);
    }

    /**
     * Sets the event handler for the searchPersonalNumberButton.
     * 
     * @param value The event handler to set.
     */
    public void setSearchPersonalNumberEventHandler(EventHandler<ActionEvent> value) {
        searchPersonalNumberButton.setOnAction(value);
    }

    /** @return The main pane of this class. */
    public GridPane getMainPane() {
        return mainPane;
    }

    /** @return The value of areaField. */
    public String getArea() {
        return areaField.getText();
    }

    /** @return The value of cityField. */
    public String getCity() {
        return cityField.getText();
    }

    /** @return The value of coverageCombo. */
    public HolidayHomeInsuranceCoverage getCoverage() {
        if (coverageCombo.getValue() instanceof HolidayHomeInsuranceCoverage) {
            // Casts the ComboBox value to HolidayHomeInsuranceCoverage and returns this value.
            HolidayHomeInsuranceCoverage coverage = (HolidayHomeInsuranceCoverage) coverageCombo.getValue();
            return coverage;
            // If for instance no value is selected, the value will not equal a HolidayHomeInsuranceCoverage, in this case return null.
        } else return null; 
    }
    
    /** @return The value of customerIdField. */
    public String getCustomerId() {
        return customerIdField.getText();
    }

    /** @return The value of excessCombo. */
    public String getExcess() {
        return excessCombo.getValue() == null ? "" : excessCombo.getValue();
    }

    /** @return The value of materialCombo. */
    public PropertyMaterial getMaterial() {
        if (materialCombo.getValue() instanceof PropertyMaterial) {
            // Casts the ComboBox value to PropertyMaterial and returns this value.
            PropertyMaterial material = (PropertyMaterial) materialCombo.getValue();
            return material;
            // If for instance no value is selected, the value will not equal a PropertyMaterial, in this case return null.
        } else return null; 
    }
    
    /** @return The value of personalNumberField. */
    public String getPersonalNumber() {
        return personalNumberField.getText();
    }

    /** @return The value of rentalCombo. */
    public String getRental() {
        return rentalCombo.getValue() == null ? "" : rentalCombo.getValue();
    }

    /** @return The value of selectedCustomerId. */
    public int getSelectedCustomerId() {
        return selectedCustomerId;
    }

    /** @return The value of streetField. */
    public String getStreet() {
        return streetField.getText();
    }

    /** @return The value of typeCombo. */
    public HolidayHomeType getType() {
        if (typeCombo.getValue() instanceof HolidayHomeType) {
            // Casts the ComboBox value to HolidayHomeType and returns this value.
            HolidayHomeType type = (HolidayHomeType) typeCombo.getValue();
            return type;
            // If for instance no value is selected, the value will not equal a HolidayHomeType, in this case return null.
        } else return null; 
    }

    /** @return The value of yearField. */
    public String getYear() {
        return yearField.getText();
    }

    /** @return The value of zipCodeField. */
    public String getZipCode() {
        return zipCodeField.getText();
    }
    
    /** @param message The message to set. */
    public void setAreaMessage(String message) {
        this.areaMessage.setFill(Color.FIREBRICK);
        this.areaMessage.setText(message);
    }

    /** @param message The message to set. */
    public void setCityMessage(String message) {
        this.cityMessage.setFill(Color.FIREBRICK);
        this.cityMessage.setText(message);
    }

    /** @param message The message to set. */
    public void setCoverageMessage(String message) {
        this.coverageMessage.setFill(Color.FIREBRICK);
        this.coverageMessage.setText(message);
    }
    
    /** @param text The text to set. */
    public void setCustomerArea(String text) {
        this.customerArea.setText(text);
    }

    /** @param message The message to set. */
    public void setCustomerSelectedMessage(String message) {
        this.customerSelectedMessage.setFill(Color.FIREBRICK);
        this.customerSelectedMessage.setText(message);
    }

    /** @param message The message to set. */
    public void setExcessMessage(String message) {
        this.excessMessage.setFill(Color.FIREBRICK);
        this.excessMessage.setText(message);
    }

    /** @param message The message to set. */
    public void setMaterialMessage(String message) {
        this.materialMessage.setFill(Color.FIREBRICK);
        this.materialMessage.setText(message);
    }

    /** @param message The message to set. */
    public void setRentalMessage(String message) {
        this.rentalMessage.setFill(Color.FIREBRICK);
        this.rentalMessage.setText(message);
    }
    
    /** @param selectedCustomerId The selectedCustomerId to set. */
    public void setSelectedCustomerId(int selectedCustomerId) {
        this.selectedCustomerId = selectedCustomerId;
    }

    /** @param message The message to set. */
    public void setStreetMessage(String message) {
        this.streetMessage.setFill(Color.FIREBRICK);
        this.streetMessage.setText(message);
    }

    /** @param message The message to set. */
    public void setTypeMessage(String message) {
        this.typeMessage.setFill(Color.FIREBRICK);
        this.typeMessage.setText(message);
    }

    /** @param message The message to set. */
    public void setYearMessage(String message) {
        this.yearMessage.setFill(Color.FIREBRICK);
        this.yearMessage.setText(message);
    }

    /** @param message The message to set. */
    public void setZipCodeMessage(String message) {
        this.zipCodeMessage.setFill(Color.FIREBRICK);
        this.zipCodeMessage.setText(message);
    }

    /** @param message The message to set. */
    public void setRegisterButtonMessage(String message) {
        this.registerButtonMessage.setText(message);
    }
    
    /** Clears all messages. */
    public void clearMessages() {
        areaMessage.setText("");
        cityMessage.setText("");
        coverageMessage.setText("");
        customerSelectedMessage.setText("");
        excessMessage.setText("");
        materialMessage.setText("");
        registerButtonMessage.setText("");
        rentalMessage.setText("");
        streetMessage.setText("");
        typeMessage.setText("");
        yearMessage.setText("");
        zipCodeMessage.setText("");
    }
    
    /** Clears all text fields and combo boxes. */
    public void clearView() {
        coverageCombo.setValue(null);
        excessCombo.setValue("");
        rentalCombo.setValue("");
        areaField.setText("");
        yearField.setText("");
        materialCombo.setValue(null);
        typeCombo.setValue(null);
        streetField.setText("");
        zipCodeField.setText("");
        cityField.setText("");
    }
}