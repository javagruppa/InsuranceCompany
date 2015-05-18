package insurancecompany.view.statistics;

import insurancecompany.misc.DateUtility;
import insurancecompany.misc.InsuranceType;
import insurancecompany.misc.MonthType;
import insurancecompany.model.bills.Bill;
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
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
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
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

/**
 * This class creates the graphical user interface (GUI) for searching after 
 * insurances. It creates a pane which is sent to the controller and thereafter
 * displayed.
 * 
 * <p> The pane consists of three parts; one to the left, one in the center and
 * one to the right.
 * 
 * <p> On the left the user inputs the search terms or criteria. These are
 * sent through get methods to the controller, which validates them and 
 * executes the search.
 * 
 * <p> In the center a table with the result is shown. The user can select one 
 * of these insurances for further examination.
 * 
 * <p> On the right the insurance selected in the table is shown. It consists of
 * two parts. The upper part shows the information about the insurance itself, 
 * while the bottom part shows information about the car, boat or property
 * attached. For travel insurances, which has nothing attached, this bottom
 * part is hidden as it has no use.
 * 
 * @author Sindre
 */
public class StatisticsIncome {
    
    // Declaration of the main pane which is sent to the controller.
    private GridPane mainPane;
    
    // Declaration of the three parts of the main pane.
    private GridPane firstPane;
    private GridPane secondPane;
    
    // Declaration of all the nodes in the left part.
    private Button searchIdButton;
    private Button searchButton;
    private ComboBox<Object> insuranceTypeCombo;
    private ComboBox<String> numberSelectCombo;
    private ComboBox<String> fromYear;
    private ComboBox<String> fromMonth;
    private ComboBox<String> fromDay;
    private ComboBox<String> toYear;
    private ComboBox<String> toMonth;
    private ComboBox<String> toDay;
    private Text fromYearMessage;
    private Text toYearMessage;
    private Text fromMonthMessage;
    private Text toMonthMessage;
    private Text fromDayMessage;
    private Text toDayMessage;
    private Text searchIdMessage;
    private Text searchMessage;
    private TextField numberField;
    private TextField insuranceIdField;
    
    // Declaration of nodes in second pane:
    private CategoryAxis xAxis;
    private NumberAxis yAxis;
    
    private AreaChart<String, Number> lineChart;
    
    private XYChart.Series series;
    
    private static final int maxXAxis = 12;

    
    /**
     * Default constructor. Initializes all field and sets up the view.
     */
    public StatisticsIncome() {
        
        // Initialization of the panes.
        mainPane = new GridPane();
        firstPane = new GridPane();
        secondPane = new GridPane();
        
        // Adds the three parts to the main pane.
        mainPane.add(firstPane, 0, 0);
        mainPane.add(secondPane, 1, 0);
        
        // Sets the CSS ID to the panes.
        mainPane.setId("innerPane");
        firstPane.setId("innerPane");
        secondPane.setId("innerPane");
        
        // Sets up column constraints. The width is in pixels.
        ColumnConstraints col1 = new ColumnConstraints(400);
        ColumnConstraints col2 = new ColumnConstraints(800);
        mainPane.getColumnConstraints().addAll(col1, col2);
        
        // Initialization of all the nodes in the left part.
        searchIdButton = new Button("Søk");
        searchButton = new Button("Søk");
        insuranceTypeCombo = new ComboBox<>();
        populateInsuranceTypeCombo();
        numberSelectCombo = new ComboBox<>();
        populateNumberSelectCombo();
        searchIdMessage = new Text("");
        searchMessage = new Text("");
        numberField = new TextField();
        insuranceIdField = new TextField();
        
        fromYear = new ComboBox();
        populateYearCombo(fromYear);
        fromMonth = new ComboBox();
        populateMonthCombo(fromMonth);
        fromDay = new ComboBox();
        populateDayCombo(fromDay);
        
        toYear = new ComboBox();
        populateYearCombo(toYear);
        toMonth = new ComboBox();
        populateMonthCombo(toMonth);
        toDay = new ComboBox();
        populateDayCombo(toDay);

        // Initialization of all the nodes in the center part.
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Dato");
        lineChart = new AreaChart<String,Number>(xAxis,yAxis);
        lineChart.setTitle("Inntekter");
        
        series = new XYChart.Series();
        
        
        // Declaration and initialization of the texts and labels which are 
        // used in the view and are not fields.
        Text insuranceIdTitle = new Text("Velg en forsikring i registeret:");
        insuranceIdTitle.setId("textTitle");
        Text searchTermsTitle = new Text("Eller bruk søkebetingelser:");
        searchTermsTitle.setId("textTitle");
        Label insuranceIdLabel = new Label("Forsikringsnummer:");
        Label insuranceTypeLabel = new Label("Type forsikring:");
        
        Label fromYearLabel = new Label("Fra år:");
        Label fromMonthLabel = new Label("Fra måned:");
        Label fromDayLabel = new Label("Fra dag:");
        
        Label toYearLabel = new Label("Til år:");
        Label toMonthLabel = new Label("Til måned:");
        Label toDayLabel = new Label("Til dag:");
        
        fromYearMessage = new Text();
        toYearMessage = new Text();
        fromMonthMessage = new Text();
        toMonthMessage = new Text();
        fromDayMessage = new Text();
        toDayMessage = new Text();
        searchIdMessage = new Text();
        searchMessage = new Text();
        
        // Adds the nodes to the left part.
        firstPane.add(insuranceIdTitle, 0, 0, 2, 1);
        firstPane.add(insuranceIdLabel, 0, 1);
        firstPane.add(insuranceIdField, 1, 1);
        firstPane.add(searchIdButton, 1, 2);
        firstPane.add(searchIdMessage, 0, 3);
        firstPane.add(searchTermsTitle, 0, 4, 2, 1);
        firstPane.add(numberSelectCombo, 0, 5);
        firstPane.add(numberField, 1, 5);
        firstPane.add(insuranceTypeLabel, 0, 6);
        firstPane.add(insuranceTypeCombo, 1, 6);
        firstPane.add(fromYearLabel, 0, 7);
        firstPane.add(fromYear, 1, 7);
        firstPane.add(fromYearMessage, 2, 7);
        firstPane.add(toYearLabel, 0, 8);
        firstPane.add(toYear, 1, 8);
        firstPane.add(toYearMessage, 2, 8);
        firstPane.add(fromMonthLabel, 0, 9);
        firstPane.add(fromMonth, 1, 9);
        firstPane.add(fromMonthMessage, 2, 9);
        firstPane.add(toMonthLabel, 0, 10);
        firstPane.add(toMonth, 1, 10);
        firstPane.add(toMonthMessage, 2, 10);
        firstPane.add(fromDayLabel, 0, 11);
        firstPane.add(fromDay, 1, 11);
        firstPane.add(fromDayMessage, 2, 11);
        firstPane.add(toDayLabel, 0, 12);
        firstPane.add(toDay, 1, 12);
        firstPane.add(toDayMessage, 2, 12);
        firstPane.add(searchButton, 1, 13);
        firstPane.add(searchMessage, 0, 14, 2, 1);
        
        // Adds the nodes to the center part.
        secondPane.add(lineChart, 0, 0);
        populateLineChartAll(200, 1000, "2011", "2012", "Alle");
        
 
    }
    
    public void populateLineChartAll(int sumFrom, int sumTo, String dateFrom, String dateTo, String type) {
        series.setName(type);
        series.getData().add(new XYChart.Data(dateFrom, sumFrom));
        series.getData().add(new XYChart.Data(dateTo, sumTo));
        lineChart.getData().setAll(series);
    }
    
    private void populateYearCombo(ComboBox cb) {
        int yearNow = Calendar.getInstance().get(Calendar.YEAR);
        cb.getItems().add("");
        final int amountOfYears = 20;
        for (int i = yearNow; i > yearNow-amountOfYears; i--) {
            cb.getItems().add(i + "");
        }
        cb.setPrefWidth(100);
    }
    
    private void populateMonthCombo(ComboBox cb) {
        ObservableList<MonthType> obList;
        obList = FXCollections.observableArrayList(MonthType.values());
        cb.getItems().add("");
        cb.getItems().addAll(obList);
        cb.setPrefWidth(100);
    }
    
    private void populateDayCombo(ComboBox cb) {
        int numberOfDays = 31;
        cb.getItems().add("");
        for (int i = 1; i <= numberOfDays; i++) {
            cb.getItems().add(i + "");
        }
        cb.setPrefWidth(100);
    }
    
    /** Sets the content of the ComboBox insuranceTypeCombo. */
    private void populateInsuranceTypeCombo() {
        ObservableList<InsuranceType> obList;
        obList = FXCollections.observableArrayList(InsuranceType.values());
        insuranceTypeCombo.getItems().add("");
        insuranceTypeCombo.getItems().addAll(obList);
        insuranceTypeCombo.setPrefWidth(150);
    }
    
    /** Sets the content of the ComboBox numberSelectCombo. */
    private void populateNumberSelectCombo() {
        ObservableList<String> obList = FXCollections.observableArrayList();  
        obList.addAll("Kundenummer", "Personnummer");
        numberSelectCombo.getItems().setAll(obList);
        numberSelectCombo.setValue("Kundenummer");
        numberSelectCombo.setPrefWidth(150);
    }
    
    
    /**
     * Sets the event handler for the searchIdButton.
     * 
     * @param value The event handler to set.
     */
    public void setSearchIdEventHandler(EventHandler<ActionEvent> value) {
        searchIdButton.setOnAction(value);
    }
    
    /**
     * Sets the event handler for the searchButton.
     * 
     * @param value The event handler to set.
     */
    public void setSearchEventHandler(EventHandler<ActionEvent> value) {
        searchButton.setOnAction(value);
    }

    
    /** @return The main pane of this class as a GridPane. */
    public GridPane getMainPane() {
        return mainPane;
    }
    
    /** @return The number of this class as a String. */
    public String getNumber() {
        return numberField.getText();
    }
    
    /** @return True if customer ID is selected in numberSelectCombo. */
    public boolean isCustomerIdSelected() {
        return numberSelectCombo.getValue().equals("Kundenummer");
    }
    
    /** @return The insurance ID of this class as a String. */
    public String getInsuranceId() {
        return insuranceIdField.getText();
    }
    
    
    /** 
     * @return The insurance type of this class as a String. Null if
     * no insurance type is selected.
     */
    public String getInsuranceType() {
        return (InsuranceType.class.isInstance(insuranceTypeCombo.getValue())) ?
            ((InsuranceType)insuranceTypeCombo.getValue()).toString() : null;
    }
     
    
    /** @param message The message to set. */
    public void setSearchIdMessage(String message) {
        this.searchIdMessage.setFill(Color.FIREBRICK);
        this.searchIdMessage.setText(message);
    }
    
    /** @param message The message to set. */
    public void setSearchMessage(String message) {
        this.searchMessage.setFill(Color.FIREBRICK);
        this.searchMessage.setText(message);
    }
    
    
    /** Clears all messages. */
    public void clearMessages() {
        this.searchIdMessage.setText("");
        this.searchMessage.setText("");
    }
}