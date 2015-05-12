/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package insurancecompany.view.register;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
    
    private ToggleButton carInsuranceClaimButton;
    private ToggleButton boatInsuranceClaimButton;
    private ToggleButton homeInsuranceClaimButton;
    private ToggleButton contentInsuranceClaimButton;
    private ToggleButton holidayHomeInsuranceClaimButton;
    private ToggleButton holidayHomeContentInsuranceClaimButton;
    private ToggleButton travelInsuranceClaimButton;
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
    
        carInsuranceClaimButton = new ToggleButton("Bilskade");       
        carInsuranceClaimButton.setId("sideToolbarButton");
        Image carImage = new Image("insurancecompany/resources/images/car.png");
        carInsuranceClaimButton.setGraphic(new ImageView(carImage));
        //carInsuranceButton.setContentDisplay(ContentDisplay.LEFT);
        
        boatInsuranceClaimButton = new ToggleButton("Båtskade");
        boatInsuranceClaimButton.setId("sideToolbarButton");
        Image boatImage = new Image("insurancecompany/resources/images/boat.png");
        boatInsuranceClaimButton.setGraphic(new ImageView(boatImage));
        
        homeInsuranceClaimButton = new ToggleButton("Husskade");
        homeInsuranceClaimButton.setId("sideToolbarButton");
        Image houseImage = new Image("insurancecompany/resources/images/house.png");
        homeInsuranceClaimButton.setGraphic(new ImageView(houseImage));
        
        contentInsuranceClaimButton = new ToggleButton("Innboskade");
        contentInsuranceClaimButton.setId("sideToolbarButton");
        contentInsuranceClaimButton.setGraphic(new ImageView(houseImage));
        
        holidayHomeInsuranceClaimButton = new ToggleButton("Fritidsbolig-\nskade");
        holidayHomeInsuranceClaimButton.setId("sideToolbarButton");
        Image cabinImage = new Image("insurancecompany/resources/images/cabin.png");
        holidayHomeInsuranceClaimButton.setGraphic(new ImageView(cabinImage));
        
        holidayHomeContentInsuranceClaimButton = new ToggleButton("Fritidsbolig-\ninnboskade");
        holidayHomeContentInsuranceClaimButton.setId("sideToolbarButton");
        holidayHomeContentInsuranceClaimButton.setGraphic(new ImageView(cabinImage));
        
        travelInsuranceClaimButton = new ToggleButton("Reiseskade");
        travelInsuranceClaimButton.setId("sideToolbarButton");
        Image airplaneImage = new Image("insurancecompany/resources/images/airplane.png");
        travelInsuranceClaimButton.setGraphic(new ImageView(airplaneImage));
        // Set up togglegroup and connect it to all togglebuttons:
        toggleGroup = new ToggleGroup();
        carInsuranceClaimButton.setToggleGroup(toggleGroup);
        boatInsuranceClaimButton.setToggleGroup(toggleGroup);
        homeInsuranceClaimButton.setToggleGroup(toggleGroup);
        contentInsuranceClaimButton.setToggleGroup(toggleGroup);
        holidayHomeInsuranceClaimButton.setToggleGroup(toggleGroup);
        holidayHomeContentInsuranceClaimButton.setToggleGroup(toggleGroup);
        travelInsuranceClaimButton.setToggleGroup(toggleGroup);
        ObservableList<ToggleButton> buttons = FXCollections.observableArrayList ();
        buttons.addAll(carInsuranceClaimButton, boatInsuranceClaimButton, homeInsuranceClaimButton, 
                contentInsuranceClaimButton, holidayHomeInsuranceClaimButton, 
                holidayHomeContentInsuranceClaimButton, travelInsuranceClaimButton);
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
}
