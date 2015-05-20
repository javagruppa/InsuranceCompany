package insurancecompany.view.register;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 * This class creates the graphical user interface (GUI) for registration of 
 * persons. It creates a pane which is sent to the controller and 
 * thereafter displayed.
 * 
 * It consists of a side toolbar and a center part where another pane is placed.
 * 
 * @author André
 * @author Sindre
 * @author Carl
 * 
 * @since 19.05.2015
 */
public class RegisterPersons {
    
    // Declaration of the main pane which is sent to the controller.
    private BorderPane mainPane;
    
    // Declaration of all the nodes in the side toolbar.
    private Pane sideToolBarPane;
    private ToggleButton customerButton;
    private ToggleButton employeeButton;
    private ToggleGroup toggleGroup;
    
    /**
     * Default constructor. Initializes all field and sets up the view.
     */
    public RegisterPersons() {
        
        // Initialization of the pane.
        mainPane = new BorderPane();
        
        // Creates and adds the side toolbar.
        sideToolBarPane = createSideToolBar();
        mainPane.setLeft(sideToolBarPane);
    }
    
    /** Creates the side toolbar. */
    private VBox createSideToolBar() {
        VBox vbox = new VBox();
        vbox.setPrefWidth(160);
        vbox.getStyleClass().add("insurancecompany/resources/css/stylesheet.css");
        vbox.setStyle("-fx-background-color: #6577A1;");
    
        customerButton = new ToggleButton("Ny kunde");       
        customerButton.setId("sideToolbarButton");
        Image customerImage = new Image("insurancecompany/resources/images/customer.png");
        customerButton.setGraphic(new ImageView(customerImage));
        
        employeeButton = new ToggleButton("Ny ansatt");
        employeeButton.setId("sideToolbarButton");
        Image employeeImage = new Image("insurancecompany/resources/images/employee.png");
        employeeButton.setGraphic(new ImageView(employeeImage));
        
        // Sets up the ToggleGroup and connects all ToggleButtons.
        toggleGroup = new ToggleGroup();
        customerButton.setToggleGroup(toggleGroup);
        employeeButton.setToggleGroup(toggleGroup);
        
        ObservableList<ToggleButton> buttons = FXCollections.observableArrayList ();
        buttons.addAll(customerButton, employeeButton);
        buttons.forEach(b -> {
            b.setMinWidth(Button.USE_PREF_SIZE);
            b.setMaxWidth(Double.MAX_VALUE);
        });
                
        vbox.getChildren().addAll(buttons);
       
        return vbox;
    }
    
    /** @return The main pane of this class. */
    public BorderPane getMainPane() {
        return mainPane;
    }

    /**
     * Sets the event handler for the customerButton.
     * 
     * @param value The event handler to set.
     */
    public void setCustomerButtonEventHandler(EventHandler<ActionEvent> value) {
        customerButton.setOnAction(value);
    }
    
    /**
     * Sets the event handler for the employeeButton.
     * 
     * @param value The event handler to set.
     */
    public void setEmployeeButtonEventHandler(EventHandler<ActionEvent> value) {
        employeeButton.setOnAction(value);
    }
}