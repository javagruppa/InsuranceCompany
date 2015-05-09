/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
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
    
    private Button carInsuranceClaimButton;
    private Button boatInsuranceClaimButton;
    private Button homeInsuranceClaimButton;
    private Button holidayHomeInsuranceClaimButton;
    private Button travelInsuranceClaimButton;
            
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
        initializeEventHandlers();      
    }
    private void initializeViews() {
        //boatInsuranceRegistration = new BoatInsuranceRegistration();
        //boatInsuranceRegistrationPane = boatInsuranceRegistration.getMainPane();
        //travelInsuranceRegistration = new TravelInsuranceRegistration();
        //travelInsuranceRegistrationPane = travelInsuranceRegistration.getMainPane();
    }
    
    private void initializeEventHandlers() {
      
        carInsuranceClaimButton.setOnAction((event) -> {
            //mainPane.setCenter();
            //selectedButtonStyleLower(carInsuranceClaimButton);
        });
        
        boatInsuranceClaimButton.setOnAction((event) -> {
            //mainPane.setCenter(boatInsuranceRegistrationPane);
            //selectedButtonStyleLower(boatInsuranceClaimButton);
        });
        
        homeInsuranceClaimButton.setOnAction((event) -> {
            //mainPane.setCenter();
            //selectedButtonStyleLower(homeInsuranceClaimButton);
        });
        
        holidayHomeInsuranceClaimButton.setOnAction((event) -> {
            //mainPane.setCenter();
            //selectedButtonStyleLower(holidayHomeInsuranceClaimButton);
        });
        
        travelInsuranceClaimButton.setOnAction((event) -> {
            mainPane.setCenter(travelInsuranceRegistrationPane);
            selectedButtonStyleLower(travelInsuranceClaimButton);
        });
    }
    
    // TODO: Change to setId, and make a custom style for selected, will not have hover etc
    private void selectedButtonStyleLower(Button button) {
        carInsuranceClaimButton.setId("sideToolbarButton");
        boatInsuranceClaimButton.setId("sideToolbarButton");
        homeInsuranceClaimButton.setId("sideToolbarButton");
        holidayHomeInsuranceClaimButton.setId("sideToolbarButton");
        travelInsuranceClaimButton.setId("sideToolbarButton");
        button.setId("sideToolbarButtonSelected");
    }
    
    private VBox createSideToolBar() {
        VBox vbox = new VBox();
        vbox.setPrefWidth(160);
        vbox.getStyleClass().add("insurancecompany/resources/css/stylesheet.css");
        vbox.setStyle("-fx-background-color: #6577A1;");
    
        carInsuranceClaimButton = new Button("Bilskade");       
        carInsuranceClaimButton.setId("sideToolbarButton");
        Image carImage = new Image("insurancecompany/resources/images/car.png");
        carInsuranceClaimButton.setGraphic(new ImageView(carImage));
        //carInsuranceButton.setContentDisplay(ContentDisplay.LEFT);
        
        boatInsuranceClaimButton = new Button("Båtskade");
        boatInsuranceClaimButton.setId("sideToolbarButton");
        Image boatImage = new Image("insurancecompany/resources/images/boat.png");
        boatInsuranceClaimButton.setGraphic(new ImageView(boatImage));
        
        homeInsuranceClaimButton = new Button("Husskade");
        homeInsuranceClaimButton.setId("sideToolbarButton");
        Image houseImage = new Image("insurancecompany/resources/images/house.png");
        homeInsuranceClaimButton.setGraphic(new ImageView(houseImage));
        
        holidayHomeInsuranceClaimButton = new Button("Fritidsbolig-\nskade");
        holidayHomeInsuranceClaimButton.setId("sideToolbarButton");
        Image cabinImage = new Image("insurancecompany/resources/images/cabin.png");
        holidayHomeInsuranceClaimButton.setGraphic(new ImageView(cabinImage));
        
        travelInsuranceClaimButton = new Button("Reiseskade");
        travelInsuranceClaimButton.setId("sideToolbarButton");
        Image airplaneImage = new Image("insurancecompany/resources/images/airplane.png");
        travelInsuranceClaimButton.setGraphic(new ImageView(airplaneImage));
        
        ObservableList<Button> buttons = FXCollections.observableArrayList ();
        buttons.addAll(carInsuranceClaimButton, boatInsuranceClaimButton, homeInsuranceClaimButton, 
                holidayHomeInsuranceClaimButton, travelInsuranceClaimButton);
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
