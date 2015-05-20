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
 * claims. It creates a pane which is sent to the controller and 
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
public class RegisterClaims {
    
    // Declaration of the main pane which is sent to the controller.
    private BorderPane mainPane;
    
    // Declaration of all the nodes in the side toolbar.
    private Pane sideToolBarPane;
    private ToggleButton carClaimButton;
    private ToggleButton boatClaimButton;
    private ToggleButton homeClaimButton;
    private ToggleButton homeContentClaimButton;
    private ToggleButton holidayHomeClaimButton;
    private ToggleButton holidayHomeContentClaimButton;
    private ToggleButton travelClaimButton;
    private ToggleGroup toggleGroup;
            
    /**
     * Default constructor. Initializes all field and sets up the view.
     */
    public RegisterClaims() {
        
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
    
        carClaimButton = new ToggleButton("Bilskade");       
        carClaimButton.setId("sideToolbarButton");
        Image carImage = new Image("insurancecompany/resources/images/car.png");
        carClaimButton.setGraphic(new ImageView(carImage));
        
        boatClaimButton = new ToggleButton("Båtskade");
        boatClaimButton.setId("sideToolbarButton");
        Image boatImage = new Image("insurancecompany/resources/images/boat.png");
        boatClaimButton.setGraphic(new ImageView(boatImage));
        
        homeClaimButton = new ToggleButton("Husskade");
        homeClaimButton.setId("sideToolbarButton");
        Image houseImage = new Image("insurancecompany/resources/images/house.png");
        homeClaimButton.setGraphic(new ImageView(houseImage));
        
        homeContentClaimButton = new ToggleButton("Innboskade");
        homeContentClaimButton.setId("sideToolbarButton");
        homeContentClaimButton.setGraphic(new ImageView(houseImage));
        
        holidayHomeClaimButton = new ToggleButton("Fritidsbolig-\nskade");
        holidayHomeClaimButton.setId("sideToolbarButton");
        Image cabinImage = new Image("insurancecompany/resources/images/cabin.png");
        holidayHomeClaimButton.setGraphic(new ImageView(cabinImage));
        
        holidayHomeContentClaimButton = new ToggleButton("Fritidsbolig-\ninnboskade");
        holidayHomeContentClaimButton.setId("sideToolbarButton");
        holidayHomeContentClaimButton.setGraphic(new ImageView(cabinImage));
        
        travelClaimButton = new ToggleButton("Reiseskade");
        travelClaimButton.setId("sideToolbarButton");
        Image airplaneImage = new Image("insurancecompany/resources/images/airplane.png");
        travelClaimButton.setGraphic(new ImageView(airplaneImage));
        
        // Sets up the ToggleGroup and connects all ToggleButtons.
        toggleGroup = new ToggleGroup();
        carClaimButton.setToggleGroup(toggleGroup);
        boatClaimButton.setToggleGroup(toggleGroup);
        homeClaimButton.setToggleGroup(toggleGroup);
        homeContentClaimButton.setToggleGroup(toggleGroup);
        holidayHomeClaimButton.setToggleGroup(toggleGroup);
        holidayHomeContentClaimButton.setToggleGroup(toggleGroup);
        travelClaimButton.setToggleGroup(toggleGroup);
        ObservableList<ToggleButton> buttons = FXCollections.observableArrayList ();
        buttons.addAll(carClaimButton, boatClaimButton, homeClaimButton, 
                homeContentClaimButton, holidayHomeClaimButton, 
                holidayHomeContentClaimButton, travelClaimButton);
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
     * Sets the event handler for the boatClaimButton.
     * 
     * @param value The event handler to set.
     */
    public void setBoatClaimButtonEventHandler(EventHandler<ActionEvent> value) {
        boatClaimButton.setOnAction(value);
    }

    /**
     * Sets the event handler for the carClaimButton.
     * 
     * @param value The event handler to set.
     */
    public void setCarClaimButtonEventHandler(EventHandler<ActionEvent> value) {
        carClaimButton.setOnAction(value);
    }

    /**
     * Sets the event handler for the homeClaimButton.
     * 
     * @param value The event handler to set.
     */
    public void setHomeClaimButtonEventHandler(EventHandler<ActionEvent> value) {
        homeClaimButton.setOnAction(value);
    }
    
    /**
     * Sets the event handler for the homeContentClaimButton.
     * 
     * @param value The event handler to set.
     */
    public void setHomeContentClaimButtonEventHandler(EventHandler<ActionEvent> value) {
        homeContentClaimButton.setOnAction(value);
    }

    /**
     * Sets the event handler for the holidayHomeClaimButton.
     * 
     * @param value The event handler to set.
     */
    public void setHolidayHomeClaimButtonEventHandler(EventHandler<ActionEvent> value) {
        holidayHomeClaimButton.setOnAction(value);
    }
    
    /**
     * Sets the event handler for the holidayHomeContentClaimButton.
     * 
     * @param value The event handler to set.
     */
    public void setHolidayHomeContentClaimButtonEventHandler(EventHandler<ActionEvent> value) {
        holidayHomeContentClaimButton.setOnAction(value);
    }

    /**
     * Sets the event handler for the travelClaimButton.
     * 
     * @param value The event handler to set.
     */
    public void setTravelClaimButtonEventHandler(EventHandler<ActionEvent> value) {
        travelClaimButton.setOnAction(value);
    }
}