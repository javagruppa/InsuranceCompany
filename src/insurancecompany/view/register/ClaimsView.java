/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package insurancecompany.view.register;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author André
 */
public class ClaimsView {
   
    //private BoatInsuranceRegistration boatInsuranceRegistration;
    //private TravelInsuranceRegistration travelInsuranceRegistration;
    
    private Pane customerRegistrationPane;
    private Pane boatInsuranceRegistrationPane;
    private Pane travelInsuranceRegistrationPane;
    
    private Scene scene;
    private BorderPane mainPane;
    
    private Pane sideToolBarPane;
    
    private ToggleButton carClaimButton;
    private ToggleButton boatClaimButton;
    private ToggleButton homeClaimButton;
    private ToggleButton contentClaimButton;
    private ToggleButton holidayHomeClaimButton;
    private ToggleButton holidayHomeContentClaimButton;
    private ToggleButton travelClaimButton;
    private ToggleGroup toggleGroup;
            
    //public static void main(String[] args) {
    //    launch(args);
    //}
    
    public void start(Stage stage) throws Exception {
        show(stage);
    }
    
    public void show(Stage stage) {
        stage.setTitle("Kunderegistrering");
        stage.setScene(scene);
        stage.show();
    }
    
    public ClaimsView() {
        mainPane = new BorderPane();
        sideToolBarPane = createSideToolBar();
        //ScrollPane sp = new ScrollPane();
        //sp.setContent(sideToolBarPane);
        //sp.setFitToHeight(true);
        //sp.setFitToWidth(true);
        mainPane.setLeft(sideToolBarPane);
        scene = new Scene(mainPane, 800, 600);
        initializeViews();    
    }
    private void initializeViews() {
        //boatInsuranceRegistration = new BoatInsuranceRegistration();
        //boatInsuranceRegistrationPane = boatInsuranceRegistration.getMainPane();
        //travelInsuranceRegistration = new TravelInsuranceRegistration();
        //travelInsuranceRegistrationPane = travelInsuranceRegistration.getMainPane();
    }
    
    
    private VBox createSideToolBar() {
        VBox vbox = new VBox();
        vbox.setPrefWidth(160);
        vbox.getStyleClass().add("insurancecompany/resources/css/stylesheet.css");
        vbox.setStyle("-fx-background-color: #6577A1;");
    
        carClaimButton = new ToggleButton("Bilskade");       
        carClaimButton.setId("sideToolbarButton");
        Image carImage = new Image("insurancecompany/resources/images/car.png");
        carClaimButton.setGraphic(new ImageView(carImage));
        //carInsuranceButton.setContentDisplay(ContentDisplay.LEFT);
        
        boatClaimButton = new ToggleButton("Båtskade");
        boatClaimButton.setId("sideToolbarButton");
        Image boatImage = new Image("insurancecompany/resources/images/boat.png");
        boatClaimButton.setGraphic(new ImageView(boatImage));
        
        homeClaimButton = new ToggleButton("Husskade");
        homeClaimButton.setId("sideToolbarButton");
        Image houseImage = new Image("insurancecompany/resources/images/house.png");
        homeClaimButton.setGraphic(new ImageView(houseImage));
        
        contentClaimButton = new ToggleButton("Innboskade");
        contentClaimButton.setId("sideToolbarButton");
        contentClaimButton.setGraphic(new ImageView(houseImage));
        
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
        // Set up togglegroup and connect it to all togglebuttons:
        toggleGroup = new ToggleGroup();
        carClaimButton.setToggleGroup(toggleGroup);
        boatClaimButton.setToggleGroup(toggleGroup);
        homeClaimButton.setToggleGroup(toggleGroup);
        contentClaimButton.setToggleGroup(toggleGroup);
        holidayHomeClaimButton.setToggleGroup(toggleGroup);
        holidayHomeContentClaimButton.setToggleGroup(toggleGroup);
        travelClaimButton.setToggleGroup(toggleGroup);
        ObservableList<ToggleButton> buttons = FXCollections.observableArrayList ();
        buttons.addAll(carClaimButton, boatClaimButton, homeClaimButton, 
                contentClaimButton, holidayHomeClaimButton, 
                holidayHomeContentClaimButton, travelClaimButton);
        buttons.forEach(b -> {
            b.setMinWidth(Button.USE_PREF_SIZE);
            b.setMaxWidth(Double.MAX_VALUE);
        });
        
                
        vbox.getChildren().addAll(buttons);
       
        return vbox;
    }
    
    public BorderPane getMainPane() {
        return mainPane;
    }
    
    // SET EVENT HANDLERS

    public void setBoatClaimButtonEventHandler(EventHandler<ActionEvent> value) {
        boatClaimButton.setOnAction(value);
    }

    public void setCarClaimButtonEventHandler(EventHandler<ActionEvent> value) {
        carClaimButton.setOnAction(value);
    }

    public void setHomeClaimButtonEventHandler(EventHandler<ActionEvent> value) {
        homeClaimButton.setOnAction(value);
    }

    public void setHolidayHomeClaimButtonEventHandler(EventHandler<ActionEvent> value) {
        holidayHomeClaimButton.setOnAction(value);
    }

    public void setTravelClaimButtonEventHandler(EventHandler<ActionEvent> value) {
        travelClaimButton.setOnAction(value);
    }
}
