
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
 * insurances. It creates a pane which is sent to the controller and 
 * thereafter displayed.
 * 
 * It consists of a side toolbar and a center part where another pane is placed.
 * 
 * @author André
 * @author Sindre
 */
public class RegisterInsurances {
    
    // Declaration of the main pane which is sent to the controller.
    private BorderPane mainPane;
    
    // Declaration of all the nodes in the side toolbar.
    private Pane sideToolBarPane;
    private ToggleButton boatInsuranceButton;
    private ToggleButton carInsuranceButton;
    private ToggleButton homeInsuranceButton;
    private ToggleButton homeContentInsuranceButton;
    private ToggleButton holidayHomeInsuranceButton;
    private ToggleButton holidayHomeContentInsuranceButton;
    private ToggleButton travelInsuranceButton;
    private ToggleGroup toggleGroup;
    
    /**
     * Default constructor. Initializes all field and sets up the view.
     */
    public RegisterInsurances() {
        
        // Initialization of the pane.
        mainPane = new BorderPane();
        
        // Creates and adds the side toolbar.
        sideToolBarPane = createSideToolBar();
        mainPane.setLeft(sideToolBarPane);  
    }
    
    /* Creates the side toolbar. */
    private VBox createSideToolBar() {
        VBox vbox = new VBox();
        vbox.setPrefWidth(160);
        vbox.getStyleClass().add("insurancecompany/resources/css/stylesheet.css");
        vbox.setStyle("-fx-background-color: #6577A1;");
    
        carInsuranceButton = new ToggleButton("Bilforsikring");       
        carInsuranceButton.setId("sideToolbarButton");
        Image carImage = new Image("insurancecompany/resources/images/car.png");
        carInsuranceButton.setGraphic(new ImageView(carImage));
        
        boatInsuranceButton = new ToggleButton("Båtforsikring");
        boatInsuranceButton.setId("sideToolbarButton");
        Image boatImage = new Image("insurancecompany/resources/images/boat.png");
        boatInsuranceButton.setGraphic(new ImageView(boatImage));
        
        homeInsuranceButton = new ToggleButton("Husforsikring");
        homeInsuranceButton.setId("sideToolbarButton");
        Image houseImage = new Image("insurancecompany/resources/images/house.png");
        homeInsuranceButton.setGraphic(new ImageView(houseImage));
        
        homeContentInsuranceButton = new ToggleButton("Innboforsikring");
        homeContentInsuranceButton.setId("sideToolbarButton");
        homeContentInsuranceButton.setGraphic(new ImageView(houseImage));
        
        holidayHomeInsuranceButton = new ToggleButton("Fritidsbolig-\nforsikring");
        holidayHomeInsuranceButton.setId("sideToolbarButton");
        Image cabinImage = new Image("insurancecompany/resources/images/cabin.png");
        holidayHomeInsuranceButton.setGraphic(new ImageView(cabinImage));
        
        holidayHomeContentInsuranceButton = new ToggleButton("Fritidsbolig-\ninnboforsikring");
        holidayHomeContentInsuranceButton.setId("sideToolbarButton");
        holidayHomeContentInsuranceButton.setGraphic(new ImageView(cabinImage));
        
        travelInsuranceButton = new ToggleButton("Reiseforsikring");
        travelInsuranceButton.setId("sideToolbarButton");
        Image airplaneImage = new Image("insurancecompany/resources/images/airplane.png");
        travelInsuranceButton.setGraphic(new ImageView(airplaneImage));
        
        // Sets up the ToggleGroup and connects all ToggleButtons.
        toggleGroup = new ToggleGroup();
        carInsuranceButton.setToggleGroup(toggleGroup);
        boatInsuranceButton.setToggleGroup(toggleGroup);
        homeInsuranceButton.setToggleGroup(toggleGroup);
        homeContentInsuranceButton.setToggleGroup(toggleGroup);
        holidayHomeInsuranceButton.setToggleGroup(toggleGroup);
        holidayHomeContentInsuranceButton.setToggleGroup(toggleGroup);
        travelInsuranceButton.setToggleGroup(toggleGroup);
        ObservableList<ToggleButton> buttons = FXCollections.observableArrayList ();
        buttons.addAll(carInsuranceButton, boatInsuranceButton, homeInsuranceButton, 
                homeContentInsuranceButton, holidayHomeInsuranceButton, 
                holidayHomeContentInsuranceButton, travelInsuranceButton);
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
     * Sets the event handler for the boatInsuranceButton.
     * 
     * @param value The event handler to set.
     */
    public void setBoatInsuranceButtonEventHandler(EventHandler<ActionEvent> value) {
        boatInsuranceButton.setOnAction(value);
    }

    /**
     * Sets the event handler for the carInsuranceButton.
     * 
     * @param value The event handler to set.
     */
    public void setCarInsuranceButtonEventHandler(EventHandler<ActionEvent> value) {
        carInsuranceButton.setOnAction(value);
    }

    /**
     * Sets the event handler for the homeInsuranceButton.
     * 
     * @param value The event handler to set.
     */
    public void setHomeInsuranceButtonEventHandler(EventHandler<ActionEvent> value) {
        homeInsuranceButton.setOnAction(value);
    }
    
    /**
     * Sets the event handler for the homeContentInsuranceButton.
     * 
     * @param value The event handler to set.
     */
    public void setHomeContentInsuranceButtonEventHandler(EventHandler<ActionEvent> value) {
        homeContentInsuranceButton.setOnAction(value);
    }

    /**
     * Sets the event handler for the holidayHomeInsuranceButton.
     * 
     * @param value The event handler to set.
     */
    public void setHolidayHomeInsuranceButtonEventHandler(EventHandler<ActionEvent> value) {
        holidayHomeInsuranceButton.setOnAction(value);
    }
    
    /**
     * Sets the event handler for the holidayHomeContentInsuranceButton.
     * 
     * @param value The event handler to set.
     */
    public void setHolidayHomeContentInsuranceButtonEventHandler(EventHandler<ActionEvent> value) {
        holidayHomeContentInsuranceButton.setOnAction(value);
    }

    /**
     * Sets the event handler for the travelInsuranceButton.
     * 
     * @param value The event handler to set.
     */
    public void setTravelInsuranceButtonEventHandler(EventHandler<ActionEvent> value) {
        travelInsuranceButton.setOnAction(value);
    }
}