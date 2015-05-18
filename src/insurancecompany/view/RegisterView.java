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
 * This class creates the graphical user interface (GUI) for registration. It 
 * creates a pane which is sent to the controller and thereafter displayed.
 * 
 * It consists of a toolbar and a center part where another pane is placed.
 * 
 * @author André
 * @author Sindre
 */
public class RegisterView {
    
    // Declaration of the main pane which is sent to the controller.
    private BorderPane mainPane;
    
    // Declaration of all the nodes in the toolbar.
    private ToggleButton personsButton;
    private ToggleButton insurancesButton;
    private ToggleButton claimsButton;
    private ToggleGroup toggleGroup;
            
    /**
     * Default constructor. Initializes all field and sets up the view.
     */
    public RegisterView() {
        
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
        personsButton = new ToggleButton("Personer");
        personsButton.setId("subToolbarButton");
        insurancesButton = new ToggleButton("Forsikringer");
        insurancesButton.setId("subToolbarButton");
        claimsButton = new ToggleButton("Skademeldinger");
        claimsButton.setId("subToolbarButton");
        toggleGroup = new ToggleGroup();
        personsButton.setToggleGroup(toggleGroup);
        insurancesButton.setToggleGroup(toggleGroup);
        claimsButton.setToggleGroup(toggleGroup);
        ObservableList<ToggleButton> buttons = FXCollections.observableArrayList();
        buttons.addAll(personsButton, insurancesButton, claimsButton);        
        hbox.getChildren().addAll(buttons);
       
        return hbox;
    }
    
    /** @return The main pane of this class. */
    public BorderPane getMainPane() {
        return mainPane;
    }
    
    /**
     * Sets the event handler for the personsButton.
     * 
     * @param value The event handler to set.
     */
    public void setPersonsButtonEventHandler(EventHandler<ActionEvent> value) {
        personsButton.setOnAction(value);
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