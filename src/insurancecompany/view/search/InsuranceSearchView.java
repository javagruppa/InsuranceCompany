/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package insurancecompany.view.search;

import insurancecompany.misc.DateUtility;
import insurancecompany.misc.InsuranceType;
import insurancecompany.model.insurances.Insurance;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

/**
 *
 * @author Sindre
 */
public class InsuranceSearchView {
    
    // Panes:
    private GridPane mainPane;
    private GridPane leftPane;
    private GridPane centerPane;
    private GridPane rightPane;
    
    // Nodes in leftPane:
    private Button searchButton;
    private ComboBox<String> activeCombo;
    private ComboBox<Object> insuranceTypeCombo;
    private DatePicker fromDatePicker;
    private DatePicker toDatePicker;
    private TextField customerIdField;
    private TextField insuranceIdField;
    private TextField personalNumberField;
    
    // Nodes in centerPane:
    private Button selectButton;
    private TableView<Insurance> insurancesTable;
    private TableColumn<Insurance, String> insuranceTypeColumn;
    private TableColumn<Insurance, Integer> insuranceIdColumn;
    private TableColumn<Insurance, Integer> customerIdColumn;
    private TableColumn<Insurance, String> activeColumn;
    
    // Nodes in rightPane:
    private Button deactivateButton;
    private Text rightBottomTitle;
    private TextArea insuranceArea;
    private TextArea accessoryArea;
    
    public InsuranceSearchView() {
        // Sets up the paned:
        mainPane = new GridPane();
        leftPane = new GridPane();
        centerPane = new GridPane();
        rightPane = new GridPane();
        
        // Adds panes to mainPane:
        mainPane.add(leftPane, 0, 0);
        mainPane.add(centerPane, 1, 0);
        mainPane.add(rightPane, 2, 0);
        
        // Sets CSS ID:
        mainPane.setId("innerPane");
        leftPane.setId("innerPane");
        centerPane.setId("innerPane");
        rightPane.setId("innerPane");
        
        // Sets up column constraints. Width in pixels:
        ColumnConstraints col1 = new ColumnConstraints(400);
        ColumnConstraints col2 = new ColumnConstraints(400);
        ColumnConstraints col3 = new ColumnConstraints(400);
        
        // Adds these constraints:
        mainPane.getColumnConstraints().addAll(col1, col2, col3);
        
        // Initializes leftPane:
        searchButton = new Button("Søk");
        insuranceTypeCombo = new ComboBox<>();
        populateInsuranceTypeCombo();
        activeCombo = new ComboBox<>();
        populateActiveCombo();
        fromDatePicker = new DatePicker();
        toDatePicker = new DatePicker();
        customerIdField = new TextField();
        insuranceIdField = new TextField();
        personalNumberField = new TextField();

        // Initializes centerPane:
        selectButton = new Button("Velg forsikring");
        insurancesTable = new TableView();
        insuranceTypeColumn = new TableColumn<>("Forsikring");
        customerIdColumn = new TableColumn<>("Kunde ID");
        insuranceIdColumn = new TableColumn<>("Forsikring ID");
        activeColumn = new TableColumn<>("Aktiv");
        insurancesTable.getColumns().addAll(insuranceTypeColumn, 
                insuranceIdColumn, customerIdColumn, activeColumn);
        insurancesTable.setColumnResizePolicy(
                TableView.CONSTRAINED_RESIZE_POLICY);

        // Initializes rightPane:
        deactivateButton = new Button("Gjør inaktiv");
        rightBottomTitle = new Text("");
        rightBottomTitle.setVisible(false);
        insuranceArea = new TextArea();
        accessoryArea = new TextArea();
        accessoryArea.setVisible(false);
        
        // Initializes Texts and Labels:
        Text leftUpperTitle = new Text("Velg en forsikring i registeret:");
        leftUpperTitle.setId("textTitle");
        Text leftBottomTitle = new Text("Eller bruk søkebetingelser:");
        leftBottomTitle.setId("textTitle");
        Text centerTitle = new Text("Forsikringer:");
        centerTitle.setId("textTitle");
        Text rightUpperTitle = new Text("Forsikring:");
        rightUpperTitle.setId("textTitle");
        rightBottomTitle.setId("textTitle");
        Label customerIdLabel = new Label("Kundenummer:");
        Label personalNumberLabel = new Label("Personnummer:");
        Label insuranceIdLabel = new Label("Forsikringssnummer:");
        Label insuranceTypeLabel = new Label("Type forsikring:");
        Label activeLabel = new Label("Kun aktive:");
        Label fromDateLabel = new Label("Fra dato:");
        Label toDateLabel = new Label("Til dato:");
        
        // Adds nodes to leftPane:
        leftPane.add(leftUpperTitle, 0, 0, 3, 1);
        leftPane.add(insuranceIdLabel, 0, 1);
        leftPane.add(insuranceIdField, 1, 1);
        leftPane.add(new Text(""), 0, 2);
        
        leftPane.add(leftBottomTitle, 0, 3);
        leftPane.add(customerIdLabel, 0, 4);
        leftPane.add(customerIdField, 1, 4);
        leftPane.add(personalNumberLabel, 0, 5);
        leftPane.add(personalNumberField, 1, 5);
        leftPane.add(insuranceTypeLabel, 0, 6);
        leftPane.add(insuranceTypeCombo, 1, 6);
        leftPane.add(activeLabel, 0, 7);
        leftPane.add(activeCombo, 1, 7);
        leftPane.add(fromDateLabel, 0, 8);
        leftPane.add(fromDatePicker, 1, 8);
        leftPane.add(toDateLabel, 0, 9);
        leftPane.add(toDatePicker, 1, 9);
        leftPane.add(searchButton, 1, 10);
        
        // Adds nodes to centerPane:
        centerPane.add(centerTitle, 0, 0);
        centerPane.add(insurancesTable, 0, 1);
        centerPane.add(selectButton, 0, 2);
        
        // Adds nodes to rightPane:
        rightPane.add(rightUpperTitle, 0, 0);
        rightPane.add(insuranceArea, 0, 1);
        rightPane.add(rightBottomTitle, 0, 2);
        rightPane.add(accessoryArea, 0, 3);
        rightPane.add(deactivateButton, 0, 4);
    }
    
    // POPULATE METHODS
    
    private void populateActiveCombo() {
        ObservableList<String> active = FXCollections.observableArrayList();  
        active.addAll("Ja", "Nei");
        activeCombo.getItems().setAll(active);
        activeCombo.setValue("Nei");
        activeCombo.setPrefWidth(150);
    }
    
    private void populateInsuranceTypeCombo() {
        ObservableList<InsuranceType> obList;
        obList = FXCollections.observableArrayList(InsuranceType.values());
        insuranceTypeCombo.getItems().add("");
        insuranceTypeCombo.getItems().addAll(obList);
        insuranceTypeCombo.setPrefWidth(150);
    }
    
    public void populateInsurancesTable(List<Insurance> insurances) {
        ObservableList<Insurance> obList = FXCollections.observableArrayList(insurances);
        insurancesTable.setItems(obList);
        
        insuranceTypeColumn.setCellValueFactory((cellData) -> {
                if ( cellData.getValue() != null) {
                    return new SimpleStringProperty(cellData.getValue().getName());
                } else {
                    return new SimpleStringProperty("<no name>");
                }
        });
        insuranceIdColumn.setCellValueFactory((cellData) -> {
                if ( cellData.getValue() != null) {
                    return new SimpleObjectProperty<>(cellData.getValue().getInsuranceId());
                } else {
                    return new SimpleObjectProperty(0);
                }
        });   
        customerIdColumn.setCellValueFactory((cellData) -> {
                if ( cellData.getValue() != null) {
                    return new SimpleObjectProperty<>(cellData.getValue().getCustomerId());
                } else {
                    return new SimpleObjectProperty(0);
                }
        });   
        activeColumn.setCellValueFactory((cellData) -> {
                if (cellData.getValue() != null && cellData.getValue().getActive()) {
                    return new SimpleObjectProperty<>("Ja");
                } else {
                    return new SimpleObjectProperty("Nei");
                }
        });
    }
    
    // SET EVENTHANDLER METHODS:

    public void setDeactivateButtonEventHandler(EventHandler<ActionEvent> value) {
        deactivateButton.setOnAction(value);
    }
    
    public void setSearchButtonEventHandler(EventHandler<ActionEvent> value) {
        searchButton.setOnAction(value);
    }

    public void setSelectButtonEventHandler(EventHandler<ActionEvent> value) {
        selectButton.setOnAction(value);
    }
    
    // GET METHODS
    
    /** @return The main pane of this class. */
    public GridPane getMainPane() {
        return mainPane;
    }
    
    public boolean getActive() {
        return activeCombo.getValue().equals("Ja");
    }
    
    /** @return The customer id of this class. */
    public String getCustomerId() {
        return customerIdField.getText();
    }
    
    /** @return The personal number of this class. */
    public String getPersonalNumber() {
        return personalNumberField.getText();
    }
    
    /** @return The insurance id of this class. */
    public String getInsuranceId() {
        return insuranceIdField.getText();
    }
    
    /** @return The selected value of insuranceTable. */
    public Insurance getInsuranceTableValue() {
        return insurancesTable.getSelectionModel() == null ? null : insurancesTable.getSelectionModel().getSelectedItem();
    }
    
    /** @return The insurance type of this class. */
    public InsuranceType getInsuranceType() {
        if(String.class.isInstance(insuranceTypeCombo.getValue())) {
            return null;
        } else {
            return (InsuranceType) insuranceTypeCombo.getValue();
        }
    }
    
    /** @return The from date of this class. */
    public Calendar getFromDate() {
        if (fromDatePicker.getValue() != null) {
            return  DateUtility.LocalDateToCalendar(fromDatePicker.getValue());
        } else {
            return null;
        }
    }
    
    /** @return The to date of this class. */
    public Calendar getToDate() {
        if (toDatePicker.getValue() != null) {
            return  DateUtility.LocalDateToCalendar(toDatePicker.getValue());
        } else {
            return null;
        }
    }
    
    // SET METHODS
    
    /** @param info The information to set in insuranceArea. */
    public void setInsuranceArea(String info) {
        insuranceArea.setText(info);
    }
    
    /** 
     * @param title The title to set in rightBottomTitle.
     * @param info The information to set in insuranceArea.
     */
    public void setAccessoryArea(String title, String info) {
        rightBottomTitle.setVisible(true);
        accessoryArea.setVisible(true);
        rightBottomTitle.setText(title);
        accessoryArea.setText(info);
    }
    
    public void removeAccessoryArea() {
        setAccessoryArea("", "");
        rightBottomTitle.setVisible(false);
        accessoryArea.setVisible(false);
        insurancesTable.getSelectionModel().clearSelection();
    }
    
    public void clearView() {
        setInsuranceArea("");
        removeAccessoryArea();
        populateInsurancesTable(new ArrayList<>());
    }
}