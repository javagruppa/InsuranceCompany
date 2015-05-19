package insurancecompany.view.register.insurances;

import insurancecompany.misc.coverages.CarInsuranceCoverage;
import insurancecompany.model.insurances.Insurance;
import java.util.List;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
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
 * car insurances. It creates a pane which is sent to the controller and 
 * thereafter displayed.
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
 * @author André
 * @author Sindre
 */
public class RegisterCarInsurance {
    
    /** String value of the ComboCox option for unlimited max length. */
    public static final String MAX_LENGTH_UNLIMITED = "Ubegrenset";
    
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
    private ComboBox<String> alarmCombo;
    private ComboBox<String> brandCombo;
    private ComboBox<CarInsuranceCoverage> coverageCombo;
    private ComboBox<String> drivingLengthCombo;
    private ComboBox<String> excessCombo;
    private ComboBox<String> existingBonusCombo;
    private ComboBox<String> modelCombo;
    private ComboBox<String> parkingConditionCombo;
    private ComboBox<String> yearCombo;
    private ComboBox<String> youngestDriverCombo;
    private Text alarmMessage;
    private Text brandMessage;
    private Text coverageMessage;
    private Text drivingLengthMessage;
    private Text excessMessage;
    private Text existingBonusMessage;
    private Text modelMessage;
    private Text parkingConditionMessage;
    private Text ownerPersonalNumberMessage;
    private Text registrationNumberMessage;
    private Text yearMessage;
    private Text youngestDriverMessage;
    private Text registerButtonMessage;
    private TextField ownerPersonalNumberField;
    private TextField registrationNumberField;
    
    /**
     * Default constructor. Initializes all field and sets up the view.
     */
    public RegisterCarInsurance() {
        
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
        alarmCombo = new ComboBox();
        populateAlarmCombo();
        brandCombo = new ComboBox();
        brandCombo.setEditable(true);
        coverageCombo = new ComboBox();
        populateCoverageCombo();
        drivingLengthCombo = new ComboBox();
        populateDrivingLengthCombo();
        excessCombo = new ComboBox();
        populateExcessCombo();
        existingBonusCombo = new ComboBox();
        populateExistingBonusCombo();
        modelCombo = new ComboBox();
        modelCombo.setEditable(true);
        parkingConditionCombo = new ComboBox();
        populateParkingConditionCombo();
        yearCombo = new ComboBox();
        yearCombo.setEditable(true);
        youngestDriverCombo = new ComboBox();
        populateYoungestDriverCombo();
        ownerPersonalNumberField = new TextField();
        registrationNumberField = new TextField();
        alarmMessage = new Text();
        brandMessage = new Text(); 
        coverageMessage = new Text();
        drivingLengthMessage = new Text();
        excessMessage = new Text();
        existingBonusMessage = new Text();
        modelMessage = new Text();
        parkingConditionMessage = new Text();
        ownerPersonalNumberMessage = new Text();
        registrationNumberMessage = new Text();
        yearMessage = new Text();
        youngestDriverMessage = new Text();
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
        Text carTitle = new Text("Bil:");
        carTitle.setId("textTitle");
        Label customerIdLabel = new Label("Kundenummer:");
        Label personalNumberLabel = new Label("Personnummer:");
        Label alarmLabel = new Label("FG-godkjent alarm:");
        Label brandLabel = new Label("Merke:");
        Label coverageLabel = new Label("Dekning:");
        Label drivingLengthLabel = new Label("Kjørelengde:");
        Label excessLabel = new Label("Egenandel:");
        Label existingBonusLabel = new Label("Eksisterende bonussats:");
        Label modelLabel = new Label("Modell:");
        Label parkingConditionLabel = new Label("Parkeringsforhold:");
        Label personalNumberOwnerLabel = new Label("Eierens personnummer:");
        Label registrationNumberLabel = new Label("Registreringsnummer:");
        Label yearLabel = new Label("År:");     
        Label youngestDriverLabel = new Label("Yngste fører:");
        
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
        mainPane.add(existingBonusLabel, 4, 1);
        mainPane.add(existingBonusCombo, 5, 1);
        mainPane.add(existingBonusMessage, 6, 1);
        mainPane.add(drivingLengthLabel, 4, 2);
        mainPane.add(drivingLengthCombo, 5, 2);
        mainPane.add(drivingLengthMessage, 6, 2);
        mainPane.add(excessLabel, 4, 3);
        mainPane.add(excessCombo, 5, 3);
        mainPane.add(excessMessage, 6, 3);
        mainPane.add(youngestDriverLabel, 4, 4);
        mainPane.add(youngestDriverCombo, 5, 4);
        mainPane.add(youngestDriverMessage, 6, 4);
        mainPane.add(coverageLabel, 4, 5);
        mainPane.add(coverageCombo, 5, 5);
        mainPane.add(coverageMessage, 6, 5);
        mainPane.add(parkingConditionLabel, 4, 6);
        mainPane.add(parkingConditionCombo, 5, 6);
        mainPane.add(parkingConditionMessage, 6, 6);
        mainPane.add(carTitle, 4, 7);
        mainPane.add(personalNumberOwnerLabel, 4, 8);
        mainPane.add(ownerPersonalNumberField, 5, 8);
        mainPane.add(ownerPersonalNumberMessage, 6, 8);
        mainPane.add(registrationNumberLabel, 4, 9);
        mainPane.add(registrationNumberField, 5, 9);
        mainPane.add(registrationNumberMessage, 6, 9);
        mainPane.add(brandLabel, 4, 10);
        mainPane.add(brandCombo, 5, 10);
        mainPane.add(brandMessage, 6, 10);
        mainPane.add(yearLabel, 4, 11);
        mainPane.add(yearCombo, 5, 11);
        mainPane.add(yearMessage, 6, 11);
        mainPane.add(modelLabel, 4, 12);
        mainPane.add(modelCombo, 5, 12);
        mainPane.add(modelMessage, 6, 12);
        mainPane.add(alarmLabel, 4, 13);
        mainPane.add(alarmCombo, 5, 13);
        mainPane.add(alarmMessage, 6, 13);
        mainPane.add(registerButton, 5, 14);
        mainPane.add(registerButtonMessage, 5, 15);
    }
    
    /** Sets the content of the ComboBox alarmCombo. */
    private void populateAlarmCombo() {
        ObservableList<String> alarm = FXCollections.observableArrayList();  
        alarm.addAll("Ja", "Nei");
        alarmCombo.getItems().setAll(alarm);
        alarmCombo.setPrefWidth(150);
    }
    
    /** 
     * Sets the content of the ComboBox brandCombo. 
     * 
     * @param cars The list of cars which will be displayed in the brand
     * combo box.
     */
    public void populateBrandCombo(List cars) {
        ObservableList obList = FXCollections.observableList(cars);
        // Reset the previous value(shown value)
        brandCombo.valueProperty().set(null);
        brandCombo.getItems().setAll(obList);
        brandCombo.setPrefWidth(150);
    }
    
    /** Sets the content of the ComboBox coverageCombo. */
    private void populateCoverageCombo() {
        ObservableList<CarInsuranceCoverage> obList;
        obList = FXCollections.observableArrayList(CarInsuranceCoverage.values()); 
        coverageCombo.getItems().setAll(obList);
        coverageCombo.setPrefWidth(150);
    }
    
    /** Sets the content of the ComboBox drivingLengthCombo. */
    private void populateDrivingLengthCombo() {
        ObservableList<String> drivingLength = FXCollections.observableArrayList();
        drivingLength.addAll("6000", "8000", "10000", "15000", "14000", 
                "16000", "18000", "20000", "25000", "30000", "40000", "50000", 
                MAX_LENGTH_UNLIMITED);
        drivingLengthCombo.getItems().setAll(drivingLength);
        drivingLengthCombo.setPrefWidth(150);
    }
    
    /** Sets the content of the ComboBox excessCombo. */
    private void populateExcessCombo() {
        ObservableList<String> excess = FXCollections.observableArrayList();
        excess.addAll("0", "1000", "2000", "3000", "4000", "5000", 
                "6000", "7000", "8000", "9000", "10000");
        excessCombo.getItems().setAll(excess);
        excessCombo.setPrefWidth(150);
    }
    
    /** Sets the content of the ComboBox existingBonusCombo. */
    private void populateExistingBonusCombo() {
        ObservableList<String> bonuses = FXCollections.observableArrayList();
        bonuses.addAll("-30", "-20", "-10", "0", "10", "20", "30", "40", 
                "50", "60", "70", "75");
        existingBonusCombo.getItems().setAll(bonuses);
        existingBonusCombo.setPrefWidth(150);
    }
    
    /** 
     * Sets the content of the ComboBox modelCombo. 
     * 
     * @param models The list of models which will be displayed in the model
     * combo box.
     */
    public void populateModelCombo(List models) {
        ObservableList obList = FXCollections.observableList(models);
        // Reset the previous value(shown value)
        modelCombo.valueProperty().set(null);
        modelCombo.getItems().setAll(obList);
        modelCombo.setPrefWidth(150);
    }
    
    /** Sets the content of the ComboBox parkingConditionCombo. */
    private void populateParkingConditionCombo() {
        ObservableList<String> parkingCondition = FXCollections.observableArrayList(); 
        parkingCondition.addAll("Har garasje", "Har ikke garasje");
        parkingConditionCombo.getItems().setAll(parkingCondition);
        parkingConditionCombo.setPrefWidth(150);
    }
    
    /** 
     * Sets the content of the ComboBox yearCombo. 
     * 
     * @param years The list of years which will be displayed in the year
     * combo box.
     */
    public void populateYearCombo(List years) {
        ObservableList obList = FXCollections.observableList(years);
        // Reset the previous value(shown value)
        yearCombo.valueProperty().set(null);
        yearCombo.getItems().setAll(obList);
        yearCombo.setPrefWidth(150);
    }
    
    /** Sets the content of the ComboBox youngestDriverCombo. */
    private void populateYoungestDriverCombo() {
        ObservableList<String> youngestDriver = FXCollections.observableArrayList(); 
        youngestDriver.addAll("Under 25", "Over 25");
        youngestDriverCombo.getItems().setAll(youngestDriver);
        youngestDriverCombo.setPrefWidth(150);
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
     * Sets the change listener for the brandCombo.
     * 
     * @param listener The change listener to set.
     */
    public void setBrandComboListener(ChangeListener listener) {
        brandCombo.valueProperty().addListener(listener);
    }
    
    /**
     * Sets the change listener for the yearCombo.
     * 
     * @param listener The change listener to set.
     */
    public void setYearComboListener(ChangeListener listener) {
        yearCombo.valueProperty().addListener(listener);
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
    
    /** @return The value of alarmCombo. */
    public String getAlarm() {
        return alarmCombo.getValue() == null ? "" : alarmCombo.getValue();
    }

    /** @return The value of brandCombo. */
    public String getBrand() {
        return brandCombo.getValue() == null ? "" : brandCombo.getValue();
    }

    /** @return The value of coverageCombo. */
    public CarInsuranceCoverage getCoverage() {
        if (coverageCombo.getValue() instanceof CarInsuranceCoverage) {
            // Casts the ComboBox value to TravelInsuranceCoverage and returns this value.
            CarInsuranceCoverage coverage = (CarInsuranceCoverage) coverageCombo.getValue();
            return coverage;
            // If for instance no value is selected, the value will not equal a TravelInsuranceCoverage, in this case return null.
        } else return null; 
    }
    
    /** @return The value of customerIdField. */
    public String getCustomerId() {
        return customerIdField.getText();
    }

    /** @return The value of drivingLengthCombo. */
    public String getDrivingLength() {
        return drivingLengthCombo.getValue() == null ? "" : drivingLengthCombo.getValue();
    }

    /** @return The value of excessCombo. */
    public String getExcess() {
        return excessCombo.getValue() == null ? "" : excessCombo.getValue();
    }

    /** @return The value of existingBonusCombo. */
    public String getExistingBonus() {
        return existingBonusCombo.getValue() == null ? "" : existingBonusCombo.getValue();
    }

    /** @return The value of modelCombo. */
    public String getModel() {
        return modelCombo.getValue() == null ? "" : modelCombo.getValue();
    }

    /** @return The value of parkingConditionCombo. */
    public String getParkingCondition() {
        return parkingConditionCombo.getValue() == null ? "" : parkingConditionCombo.getValue();
    }

    /** @return The value of personalNumberField. */
    public String getPersonalNumber() {
        return personalNumberField.getText();
    }

    /** @return The value of ownerPersonalNumberField. */
    public String getOwnerPersonalNumber() {
        return ownerPersonalNumberField.getText();
    }

    /** @return The value of registrationNumberField. */
    public String getRegistrationNumber() {
        return registrationNumberField.getText();
    }

    /** @return The value of selectedCustomerId. */
    public int getSelectedCustomerId() {
        return selectedCustomerId;
    }

    /** @return The value of yearCombo. */
    public String getYear() {
        return yearCombo.getValue() == null ? "" : yearCombo.getValue();
    }

    /** @return The value of youngestDriverCombo. */
    public String getYoungestDriver() {
        return youngestDriverCombo.getValue() == null ? "" : youngestDriverCombo.getValue();
    }
    
    /** @return The value of brandCombo. */
    public Object getBrandComboValue() {
        return brandCombo.getValue();
    }

    /** @param selectedCustomerId The selectedCustomerId to set. */
    public void setSelectedCustomerId(int selectedCustomerId) {
        this.selectedCustomerId = selectedCustomerId;
    }
    
    /** @param message The message to set. */
    public void setAlarmMessage(String message) {
        this.alarmMessage.setFill(Color.FIREBRICK);
        this.alarmMessage.setText(message);
    }

    /** @param message The message to set. */
    public void setBrandMessage(String message) {
        this.brandMessage.setFill(Color.FIREBRICK);
        this.brandMessage.setText(message);
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
    public void setDrivingLengthMessage(String message) {
        this.drivingLengthMessage.setFill(Color.FIREBRICK);
        this.drivingLengthMessage.setText(message);
    }

    /** @param message The message to set. */
    public void setExcessMessage(String message) {
        this.excessMessage.setFill(Color.FIREBRICK);
        this.excessMessage.setText(message);
    }

    /** @param message The message to set. */
    public void setExistingBonusMessage(String message) {
        this.existingBonusMessage.setFill(Color.FIREBRICK);
        this.existingBonusMessage.setText(message);
    }

    /** @param message The message to set. */
    public void setModelMessage(String message) {
        this.modelMessage.setFill(Color.FIREBRICK);
        this.modelMessage.setText(message);
    }

    /** @param message The message to set. */
    public void setParkingConditionMessage(String message) {
        this.parkingConditionMessage.setFill(Color.FIREBRICK);
        this.parkingConditionMessage.setText(message);
    }

    /** @param message The message to set. */
    public void setOwnerPersonalNumberMessage(String message) {
        this.ownerPersonalNumberMessage.setFill(Color.FIREBRICK);
        this.ownerPersonalNumberMessage.setText(message);
    }

    /** @param message The message to set. */
    public void setRegistrationNumberMessage(String message) {
        this.registrationNumberMessage.setFill(Color.FIREBRICK);
        this.registrationNumberMessage.setText(message);
    }

    /** @param message The message to set. */
    public void setYearMessage(String message) {
        this.yearMessage.setFill(Color.FIREBRICK);
        this.yearMessage.setText(message);
    }

    /** @param message The message to set. */
    public void setYoungestDriverMessage(String message) {
        this.youngestDriverMessage.setFill(Color.FIREBRICK);
        this.youngestDriverMessage.setText(message);
    }

    /** @param message The message to set. */
    public void setRegisterButtonMessage(String message) {
        this.registerButtonMessage.setText(message);
    }
    
    /** Clears all messages. */
    public void clearMessages() {
        alarmMessage.setText("");
        brandMessage.setText("");
        coverageMessage.setText("");
        customerSelectedMessage.setText("");
        drivingLengthMessage.setText("");
        excessMessage.setText("");
        existingBonusMessage.setText("");
        modelMessage.setText("");
        parkingConditionMessage.setText("");
        ownerPersonalNumberMessage.setText("");
        registerButtonMessage.setText("");
        registrationNumberMessage.setText("");
        yearMessage.setText("");
        youngestDriverMessage.setText("");
    }
    
    /** Clears all text fields and combo boxes. */
    public void clearView() {
        alarmCombo.setValue("");
        coverageCombo.setValue(null);
        excessCombo.setValue("");
        existingBonusCombo.setValue("");
        drivingLengthCombo.setValue("");
        youngestDriverCombo.setValue("");
        parkingConditionCombo.setValue("");
        brandCombo.setValue("");
        yearCombo.setValue("");
        modelCombo.setValue("");
        ownerPersonalNumberField.setText("");
        registrationNumberField.setText("");
    }
}