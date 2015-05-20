package insurancecompany.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;


/**
 * This class creates the graphical user interface (GUI) for statistics. It 
 * creates a pane which is sent to the controller and thereafter displayed.
 * 
 * It consists of a toolbar and a center part where another pane is placed.
 * 
 * @author André
 * @author Sindre
 * 
 * @since 19.05.2015
 */
public class StatisticsView {
    
    // Declaration of the main pane which is sent to the controller.
    private BorderPane mainPane;
    
    // Declaration of all the nodes in the toolbar.
    private ToggleButton incomeButton;
    private ToggleButton disbursementsButton;
    private ToggleGroup toggleGroup;
            
    /**
     * Default constructor. Initializes all field and sets up the view.
     */
    public StatisticsView() {
        
        // Initialization of the pane.
        mainPane = new BorderPane();
        
        // Creates and adds the toolbar.
        mainPane.setTop(createToolBar());
    }

    /** Creates the toolbar. */
    private HBox createToolBar() {
        HBox hbox = new HBox();
        hbox.getStyleClass().add("insurancecompany/resources/css/stylesheet.css");
        hbox.setStyle("-fx-background-color: #6577A1;");
        hbox.setPrefSize(640, 20);
        incomeButton = new ToggleButton("Inntekter");
        incomeButton.setId("subToolbarButton");
        disbursementsButton = new ToggleButton("Utbetalinger");
        disbursementsButton.setId("subToolbarButton");
        toggleGroup = new ToggleGroup();
        incomeButton.setToggleGroup(toggleGroup);
        disbursementsButton.setToggleGroup(toggleGroup);
        ObservableList<ToggleButton> buttons = FXCollections.observableArrayList ();
        buttons.addAll(incomeButton, disbursementsButton);        
        hbox.getChildren().addAll(buttons);
       
        return hbox;
    }
    
    /** @return The main pane of this class. */
    public BorderPane getMainPane() {
        return mainPane;
    }
    
    /**
     * Sets the event handler for the incomeButton.
     * 
     * @param value The event handler to set.
     */
    public void setIncomeButtonEventHandler(EventHandler<ActionEvent> value) {
        incomeButton.setOnAction(value);
    }
    
    /**
     * Sets the event handler for the disbursementsButton.
     * 
     * @param value The event handler to set.
     */
    public void setDisbursementsButtonEventHandler(EventHandler<ActionEvent> value) {
        disbursementsButton.setOnAction(value);
    }
}