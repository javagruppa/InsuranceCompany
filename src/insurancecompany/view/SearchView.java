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
 * This class creates the graphical user interface (GUI) for searching. It 
 * creates a pane which is sent to the controller and thereafter displayed.
 * 
 * It consists of a toolbar and a center part where another pane is placed.
 * 
 * @author André
 * @author Sindre
 * 
 * @since 19.05.2015
 */
public class SearchView {
    
    // Declaration of the main pane which is sent to the controller.
    private BorderPane mainPane;
    
    // Declaration of all the nodes in the toolbar.
    private ToggleButton customersButton;
    private ToggleButton employeesButton;
    private ToggleButton insurancesButton;
    private ToggleButton claimsButton;
    private ToggleGroup toggleGroup;
            
    /**
     * Default constructor. Initializes all field and sets up the view.
     */
    public SearchView() {
        
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
        customersButton = new ToggleButton("Kunder");
        customersButton.setId("subToolbarButton");
        employeesButton = new ToggleButton("Ansatte");
        employeesButton.setId("subToolbarButton");
        insurancesButton = new ToggleButton("Forsikringer");
        insurancesButton.setId("subToolbarButton");
        claimsButton = new ToggleButton("Skademeldinger");
        claimsButton.setId("subToolbarButton");
        toggleGroup = new ToggleGroup();
        customersButton.setToggleGroup(toggleGroup);
        employeesButton.setToggleGroup(toggleGroup);
        insurancesButton.setToggleGroup(toggleGroup);
        claimsButton.setToggleGroup(toggleGroup);
        ObservableList<ToggleButton> buttons = FXCollections.observableArrayList ();
        buttons.addAll(customersButton, employeesButton, insurancesButton, claimsButton);        
        hbox.getChildren().addAll(buttons);
       
        return hbox;
    }
    
    /** @return The main pane of this class. */
    public BorderPane getMainPane() {
        return mainPane;
    }
    
    /**
     * Sets the event handler for the customersButton.
     * 
     * @param value The event handler to set.
     */
    public void setCustomersButtonEventHandler(EventHandler<ActionEvent> value) {
        customersButton.setOnAction(value);
    }
    
    /**
     * Sets the event handler for the employeesButton.
     * 
     * @param value The event handler to set.
     */
    public void setEmployeesButtonEventHandler(EventHandler<ActionEvent> value) {
        employeesButton.setOnAction(value);
    }
    
    /**
     * Sets the event handler for the insurancesButton.
     * 
     * @param value The event handler to set.
     */
    public void setInsurancesButtonEventHandler(EventHandler<ActionEvent> value) {
        insurancesButton.setOnAction(value);
    }
    
    /**
     * Sets the event handler for the claimsButton.
     * 
     * @param value The event handler to set.
     */
    public void setClaimsButtonEventHandler(EventHandler<ActionEvent> value) {
        claimsButton.setOnAction(value);
    }
}