/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package insurancecompany.view.register;

import insurancecompany.view.register.insurances.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author André
 * @author Sindre
 */
public class InsurancesView {
    
    private Scene scene;
    private BorderPane mainPane;
    private Pane sideToolBarPane;
    
    private Button boatInsuranceButton;
    private Button carInsuranceButton;
    private Button homeInsuranceButton;
    private Button holidayHomeInsuranceButton;
    private Button travelInsuranceButton;
            
    public void show(Stage stage) {
        stage.setTitle("Forsikringsregistrering");
        stage.setScene(scene);
        stage.show();
    }
    
    public InsurancesView() {
        mainPane = new BorderPane();
        sideToolBarPane = createSideToolBar();
        //ScrollPane sp = new ScrollPane();
        //sp.setContent(sideToolBarPane);
        //sp.setFitToHeight(true);
        //sp.setFitToWidth(true);
        mainPane.setLeft(sideToolBarPane);
        scene = new Scene(mainPane, 800, 600);
        
        initializeEventHandlers();      
    }
    
    private void initializeEventHandlers() {
        carInsuranceButton.setOnAction(event -> 
            selectedButtonStyleLower(carInsuranceButton));
        
        boatInsuranceButton.setOnAction(event -> 
            selectedButtonStyleLower(boatInsuranceButton));
        
        homeInsuranceButton.setOnAction(event -> 
            selectedButtonStyleLower(homeInsuranceButton));
        
        holidayHomeInsuranceButton.setOnAction(event -> 
            selectedButtonStyleLower(holidayHomeInsuranceButton));
        
        travelInsuranceButton.setOnAction(event -> 
            selectedButtonStyleLower(travelInsuranceButton));
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
        vbox.getStyleClass().add("insurancecompany/resources/css/stylesheet.css");
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
    
    // SET EVENT HANDLERS

    public void setBoatInsuranceButtonEventHandler(EventHandler<ActionEvent> value) {
        boatInsuranceButton.setOnAction(value);
    }

    public void setCarInsuranceButtonEventHandler(EventHandler<ActionEvent> value) {
        carInsuranceButton.setOnAction(value);
    }

    public void setHomeInsuranceButtonEventHandler(EventHandler<ActionEvent> value) {
        homeInsuranceButton.setOnAction(value);
    }

    public void setHolidayHomeInsuranceButtonEventHandler(EventHandler<ActionEvent> value) {
        holidayHomeInsuranceButton.setOnAction(value);
    }

    public void setTravelInsuranceButtonEventHandler(EventHandler<ActionEvent> value) {
        travelInsuranceButton.setOnAction(value);
    }
}