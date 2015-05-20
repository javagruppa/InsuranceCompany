package insurancecompany.view.statistics;

import insurancecompany.misc.enums.InsuranceType;
import java.util.Calendar;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

/**
 * This class creates the graphical user interface (GUI) for showing income 
 * statistics.
 * 
 * <p> The pane consists of 2 parts.
 * 
 * <p> On the left the user inputs the search terms or criteria. These are
 * sent through get methods to the controller, which validates them and 
 * executes the search.
 * 
 * <p> On the right side a line chart displays values for up to two of the 
 * chosen dates.
 * 
 * @author André
 * 
 * @since 19.05.2015
 */
public class StatisticsIncome {
    
    // Declaration of the main pane which is sent to the controller.
    private GridPane mainPane;
    
    // Declaration of the three parts of the main pane.
    private GridPane firstPane;
    private GridPane secondPane;
    
    // Declaration of all the nodes in the left part.
    private Button searchButton;
    private ComboBox<Object> insuranceTypeCombo;
    private ComboBox<String> numberSelectCombo;
    private ComboBox<String> fromYearCombo;
    private ComboBox<String> fromMonthCombo;
    private ComboBox<String> fromDayCombo;
    private ComboBox<String> toYearCombo;
    private ComboBox<String> toMonthCombo;
    private ComboBox<String> toDayCombo;
    private Text fromYearMessage;
    private Text toYearMessage;
    private Text fromMonthMessage;
    private Text toMonthMessage;
    private Text fromDayMessage;
    private Text toDayMessage;
    private Text searchMessage;
    private TextField numberField;
    
    // Declaration of nodes in second pane:
    private CategoryAxis xAxis;
    private NumberAxis yAxis;
    
    private LineChart<String, Number> lineChart;
    
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
        searchButton = new Button("Søk");
        insuranceTypeCombo = new ComboBox<>();
        populateInsuranceTypeCombo();
        numberSelectCombo = new ComboBox<>();
        populateNumberSelectCombo();
        searchMessage = new Text("");
        numberField = new TextField();
        
        fromYearCombo = new ComboBox();
        populateYearCombo(fromYearCombo);
        fromMonthCombo = new ComboBox();
        populateMonthCombo(fromMonthCombo);
        fromDayCombo = new ComboBox();
        populateDayCombo(fromDayCombo);
        
        toYearCombo = new ComboBox();
        populateYearCombo(toYearCombo);
        toMonthCombo = new ComboBox();
        populateMonthCombo(toMonthCombo);
        toDayCombo = new ComboBox();
        populateDayCombo(toDayCombo);
        
        // Temporarily disable the month and day boxes:
        fromMonthCombo.setDisable(true);
        toMonthCombo.setDisable(true);
        fromDayCombo.setDisable(true);
        toDayCombo.setDisable(true);

        // Initialization of all the nodes in the center part.
        final CategoryAxis xAxis = new CategoryAxis();
        
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Dato");
        yAxis.setLabel("Kroner");
        lineChart = new LineChart<String,Number>(xAxis,yAxis);
        lineChart.setTitle("Inntekter");
        series = new XYChart.Series();
        lineChart.getData().setAll(series);
        
        
        // Declaration and initialization of the texts and labels which are 
        // used in the view and are not fields.
        Text searchTermsTitle = new Text("Bruk søkebetingelser:");
        searchTermsTitle.setId("textTitle");
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
        searchMessage = new Text();
        
        // Adds the nodes to the left part.
        firstPane.add(searchTermsTitle, 0, 4, 2, 1);
        firstPane.add(numberSelectCombo, 0, 5);
        firstPane.add(numberField, 1, 5);
        firstPane.add(insuranceTypeLabel, 0, 6);
        firstPane.add(insuranceTypeCombo, 1, 6);
        firstPane.add(fromYearLabel, 0, 7);
        firstPane.add(fromYearCombo, 1, 7);
        firstPane.add(fromYearMessage, 2, 7);
        firstPane.add(toYearLabel, 0, 8);
        firstPane.add(toYearCombo, 1, 8);
        firstPane.add(toYearMessage, 2, 8);
        firstPane.add(fromMonthLabel, 0, 9);
        firstPane.add(fromMonthCombo, 1, 9);
        firstPane.add(fromMonthMessage, 2, 9);
        firstPane.add(toMonthLabel, 0, 10);
        firstPane.add(toMonthCombo, 1, 10);
        firstPane.add(toMonthMessage, 2, 10);
        firstPane.add(fromDayLabel, 0, 11);
        firstPane.add(fromDayCombo, 1, 11);
        firstPane.add(fromDayMessage, 2, 11);
        firstPane.add(toDayLabel, 0, 12);
        firstPane.add(toDayCombo, 1, 12);
        firstPane.add(toDayMessage, 2, 12);
        firstPane.add(searchButton, 1, 13);
        firstPane.add(searchMessage, 0, 14, 2, 1);
        
        // Adds the nodes to the center part.
        secondPane.add(lineChart, 0, 0);
        //populateLineChartAll(200, 1000, "2011", "2012", "Alle");
        
 
    }
    
    public void populateLineChartAll(int sumFrom, int sumTo, String dateFrom, String dateTo, String type) {
        series.setName(type);
        XYChart.Data<String, Number> data1 = new XYChart.Data(dateFrom, sumFrom);
        XYChart.Data<String, Number> data2 = new XYChart.Data(dateTo, sumTo);
        data1.setNode(new Label(String.valueOf(data1.getYValue())));
        data2.setNode(new Label(String.valueOf(data2.getYValue())));
        series.getData().setAll(data1, data2);
        //lineChart.getData().setAll(series);
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
        cb.getItems().addAll("", "Januar", "Februar", "Mars", "April", "Mai", 
                "Juni", "Juli", "August", "September", "Oktober", "November", "Desember");
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
    
    /** 
     * @return The insurance type of this class as a String. Null if
     * no insurance type is selected.
     */
    public String getInsuranceType() {
        return (InsuranceType.class.isInstance(insuranceTypeCombo.getValue())) ?
            ((InsuranceType)insuranceTypeCombo.getValue()).toString() : null;
    }
     
    
    /** @param message The message to set. */
    public void setSearchMessage(String message) {
        this.searchMessage.setFill(Color.FIREBRICK);
        this.searchMessage.setText(message);
    }
    
    
    /** Clears all messages. */
    public void clearMessages() {
        this.searchMessage.setText("");
    }

    /**
     * @return the fromYearCombo
     */
    public String getFromYearComboValue() {
        return fromYearCombo.getValue() == null ? "" : fromYearCombo.getValue();
    }

    /**
     * @return the fromMonthCombo
     */
    public String getFromMonthComboValue() {
        return fromMonthCombo.getValue() == null ? "" : fromMonthCombo.getValue();
    }

    /**
     * @return the fromDayCombo
     */
    public String getFromDayComboValue() {
        return fromDayCombo.getValue() == null ? "" : fromDayCombo.getValue();
    }

    /**
     * @return the toYearCombo
     */
    public String getToYearComboValue() {
        return toYearCombo.getValue() == null ? "" : toYearCombo.getValue();
    }

    /**
     * @return the toMonthCombo
     */
    public String getToMonthComboValue() {
        return toMonthCombo.getValue() == null ? "" : toMonthCombo.getValue();
    }

    /**
     * @return the toDayCombo
     */
    public String getToDayComboValue() {
        return toDayCombo.getValue() == null ? "" : toDayCombo.getValue();
    }

    /**
     * @param fromYearMessage the fromYearMessage to set
     */
    public void setFromYearMessage(String fromYearMessage) {
        this.fromYearMessage.setText(fromYearMessage);
    }

    /**
     * @param toYearMessage the toYearMessage to set
     */
    public void setToYearMessage(String toYearMessage) {
        this.toYearMessage.setText(toYearMessage);
    }

    /**
     * @param fromMonthMessage the fromMonthMessage to set
     */
    public void setFromMonthMessage(String fromMonthMessage) {
        this.fromMonthMessage.setText(fromMonthMessage);
    }

    /**
     * @param toMonthMessage the toMonthMessage to set
     */
    public void setToMonthMessage(String toMonthMessage) {
        this.toMonthMessage.setText(toMonthMessage);
    }

    /**
     * @param fromDayMessage the fromDayMessage to set
     */
    public void setFromDayMessage(String fromDayMessage) {
        this.fromDayMessage.setText(fromDayMessage);
    }

    /**
     * @param toDayMessage the toDayMessage to set
     */
    public void setToDayMessage(String toDayMessage) {
        this.toDayMessage.setText(toDayMessage);
    }

    /**
     * @param disable the disable to set
     */
    public void setFromMonthComboDisable(boolean disable) {
        this.fromMonthCombo.setDisable(disable);
    }

    /**
     * @param disable the disable to set
     */
    public void setFromDayComboDisable(boolean disable) {
        this.fromDayCombo.setDisable(disable);
    }

    /**
     * @param disable the disable to set
     */
    public void setToMonthComboDisable(boolean disable) {
        this.toMonthCombo.setDisable(disable);
    }

    /**
     * @param disable the disable to set
     */
    public void setToDayComboDisable(boolean disable) {
        this.toDayCombo.setDisable(disable);
    }

    /**Sets event handler
     * @param event the event to set
     */
    public void setFromYearComboEventHandler(EventHandler<ActionEvent> event) {
        this.fromYearCombo.setOnAction(event);
    }

    /**sets event handler
     * @param event the event to set
     */
    public void setFromMonthComboEventHandler(EventHandler<ActionEvent> event) {
        this.fromMonthCombo.setOnAction(event);
    }
    
}