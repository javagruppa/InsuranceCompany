/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package insurancecompany.view;

import insurancecompany.view.register.BoatInsuranceRegistration;
import insurancecompany.view.register.TravelInsuranceRegistration;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author André
 */
public class InsurancesView {
    
    private BoatInsuranceRegistration boatInsuranceRegistration;
    private TravelInsuranceRegistration travelInsuranceRegistration;
    
    private Pane customerRegistrationPane;
    private Pane boatInsuranceRegistrationPane;
    private Pane travelInsuranceRegistrationPane;
    
    private Scene scene;
    private BorderPane mainPane;
    
    private Pane sideToolBarPane;
    
    private Button carInsuranceButton;
    private Button boatInsuranceButton;
    private Button homeInsuranceButton;
    private Button holidayHomeInsuranceButton;
    private Button travelInsuranceButton;
            
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
    
    public InsurancesView() {
        mainPane = new BorderPane();
        sideToolBarPane = createSideToolBar();
        ScrollPane sp = new ScrollPane();
        sp.setContent(sideToolBarPane);
        sp.setFitToHeight(true);
        sp.setFitToWidth(true);
        mainPane.setLeft(sp);
        scene = new Scene(mainPane, 800, 600);
        initializeViews();
        initializeEventHandlers();      
    }
    private void initializeViews() {
        boatInsuranceRegistration = new BoatInsuranceRegistration();
        boatInsuranceRegistrationPane = boatInsuranceRegistration.getMainPane();
        travelInsuranceRegistration = new TravelInsuranceRegistration();
        travelInsuranceRegistrationPane = travelInsuranceRegistration.getMainPane();
    }
    
    private void initializeEventHandlers() {
      
        carInsuranceButton.setOnAction((event) -> {
            //mainPane.setCenter();
            //selectedButtonStyleLower(carInsuranceButton);
        });
        
        boatInsuranceButton.setOnAction((event) -> {
            mainPane.setCenter(boatInsuranceRegistrationPane);
            selectedButtonStyleLower(boatInsuranceButton);
        });
        
        homeInsuranceButton.setOnAction((event) -> {
            //mainPane.setCenter();
            //selectedButtonStyleLower(homeInsuranceButton);
        });
        
        holidayHomeInsuranceButton.setOnAction((event) -> {
            //mainPane.setCenter();
            //selectedButtonStyleLower(holidayHomeInsuranceButton);
        });
        
        travelInsuranceButton.setOnAction((event) -> {
            mainPane.setCenter(travelInsuranceRegistrationPane);
            selectedButtonStyleLower(travelInsuranceButton);
        });
    }
    
    // TODO: Change to setId, and make a custom style for selected, will not have hover etc
    private void selectedButtonStyleLower(Button button) {
        carInsuranceButton.setId("sideToolbarButton");
        boatInsuranceButton.setId("sideToolbarButton");
        homeInsuranceButton.setId("sideToolbarButton");
        holidayHomeInsuranceButton.setId("sideToolbarButton");
        travelInsuranceButton.setId("sideToolbarButton");
        button.setId("sideToolbarButtonSelected");
    }
    
    private VBox createSideToolBar() {
        VBox vbox = new VBox();
        vbox.setPrefWidth(160);
        vbox.getStyleClass().add("insurancecompany/resources/stylesheet.css");
        vbox.setStyle("-fx-background-color: #6577A1;");
    
        carInsuranceButton = new Button("Bilforsikring");       
        carInsuranceButton.setId("sideToolbarButton");
        Image carImage = new Image("insurancecompany/resources/images/car.png");
        carInsuranceButton.setGraphic(new ImageView(carImage));
        //carInsuranceButton.setContentDisplay(ContentDisplay.LEFT);
        
        boatInsuranceButton = new Button("Båtforsikring");
        boatInsuranceButton.setId("sideToolbarButton");
        Image boatImage = new Image("insurancecompany/resources/images/boat.png");
        boatInsuranceButton.setGraphic(new ImageView(boatImage));
        
        homeInsuranceButton = new Button("Husforsikring");
        homeInsuranceButton.setId("sideToolbarButton");
        Image houseImage = new Image("insurancecompany/resources/images/house.png");
        homeInsuranceButton.setGraphic(new ImageView(houseImage));
        
        holidayHomeInsuranceButton = new Button("Fritidsbolig-\nforsikring");
        holidayHomeInsuranceButton.setId("sideToolbarButton");
        Image cabinImage = new Image("insurancecompany/resources/images/cabin.png");
        holidayHomeInsuranceButton.setGraphic(new ImageView(cabinImage));
        
        travelInsuranceButton = new Button("Reiseforsikring");
        travelInsuranceButton.setId("sideToolbarButton");
        Image airplaneImage = new Image("insurancecompany/resources/images/airplane.png");
        travelInsuranceButton.setGraphic(new ImageView(airplaneImage));
        
        ObservableList<Button> buttons = FXCollections.observableArrayList ();
        buttons.addAll(carInsuranceButton, boatInsuranceButton, homeInsuranceButton, 
                holidayHomeInsuranceButton, travelInsuranceButton);
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
