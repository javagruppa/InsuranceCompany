/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package insurancecompany.view;


import insurancecompany.view.register.BoatInsuranceRegistration;
import insurancecompany.view.register.CustomerRegistration;
import insurancecompany.view.register.TravelInsuranceRegistration;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;


/**
 *
 * @author André
 */
public class RegisterView extends Application {
    
    private CustomerRegistration customerRegistration;
    private BoatInsuranceRegistration boatInsuranceRegistration;
    private TravelInsuranceRegistration travelInsuranceRegistration;
    
    private Pane customerRegistrationPane;
    private Pane boatInsuranceRegistrationPane;
    private Pane travelInsuranceRegistrationPane;
    
    private Scene scene;
    private BorderPane mainPane;
    private Button customerButton;
    private Button carInsuranceButton;
    private Button boatInsuranceButton;
    private Button homeInsuranceButton;
    private Button holidayHomeInsuranceButton;
    private Button travelInsuranceButton;
    private Button claimButton;
            
    //public static void main(String[] args) {
    //    launch(args);
    //}
    
    public void start(Stage stage) throws Exception {
        show(stage);
    }
    
    public void show(Stage stage) {
        stage.setTitle("Kunderegistrering");
        stage.setScene(getScene());
        stage.show();
    }
    
    public RegisterView() {
        mainPane = new BorderPane();        
        mainPane.setTop(createToolBar());
        scene = new Scene(getMainPane(), 800, 600);
        initializeViews();
        initializeEventHandlers();      
    }
    private void initializeViews() {
        customerRegistration = new CustomerRegistration();
        customerRegistrationPane = customerRegistration.getMainPane();
        boatInsuranceRegistration = new BoatInsuranceRegistration();
        boatInsuranceRegistrationPane = boatInsuranceRegistration.getMainPane();
        travelInsuranceRegistration = new TravelInsuranceRegistration();
        travelInsuranceRegistrationPane = travelInsuranceRegistration.getMainPane();
    }
    
    private void initializeEventHandlers() {
        customerButton.setOnAction((event) -> {
            mainPane.setCenter(customerRegistrationPane);
            selectedButtonStyleLower(customerButton);
        });
        
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
        
        claimButton.setOnAction((event) -> {
            //mainPane.setCenter();
            //selectedButtonStyleLower(claimButton);
        });       
    }
    
    // TODO: Change to setId, and make a custom style for selected, will not have hover etc
    private void selectedButtonStyleLower(Button button) {
        customerButton.setId("subToolbarButton");
        carInsuranceButton.setId("subToolbarButton");
        boatInsuranceButton.setId("subToolbarButton");
        homeInsuranceButton.setId("subToolbarButton");
        holidayHomeInsuranceButton.setId("subToolbarButton");
        travelInsuranceButton.setId("subToolbarButton");
        claimButton.setId("subToolbarButton");
        button.setId("subToolbarButtonSelected");
    }
    
    private HBox createToolBar() {
        HBox hbox = new HBox();
        hbox.getStyleClass().add("insurancecompany/resources/stylesheet.css");
        hbox.setStyle("-fx-background-color: #336699;");
        hbox.setPrefSize(640, 20);
        customerButton = new Button("Ny kunde");
        getCustomerButton().setId("subToolbarButton");
        carInsuranceButton = new Button("Bilforsikring");
        getCarInsuranceButton().setId("subToolbarButton");
        boatInsuranceButton = new Button("Båtforsikring");
        getBoatInsuranceButton().setId("subToolbarButton");
        homeInsuranceButton = new Button("Hus- og innboforsikring");
        getHomeInsuranceButton().setId("subToolbarButton");
        holidayHomeInsuranceButton = new Button("Fritidsboligforsikring");
        getHolidayHomeInsuranceButton().setId("subToolbarButton");
        travelInsuranceButton = new Button("Reiseforsikring");
        getTravelInsuranceButton().setId("subToolbarButton");
        claimButton = new Button("Skademelding");
        getClaimButton().setId("subToolbarButton");
                
        hbox.getChildren().addAll(getCustomerButton(), getCarInsuranceButton(), getBoatInsuranceButton(), getHomeInsuranceButton(), getHolidayHomeInsuranceButton(), getTravelInsuranceButton(), getClaimButton());
       
        return hbox;
    }

    /**
     * @return the scene
     */
    public Scene getScene() {
        return scene;
    }

    /**
     * @return the mainPane
     */
    public BorderPane getMainPane() {
        return mainPane;
    }

    /**
     * @return the customerButton
     */
    public Button getCustomerButton() {
        return customerButton;
    }

    /**
     * @return the carInsuranceButton
     */
    public Button getCarInsuranceButton() {
        return carInsuranceButton;
    }

    /**
     * @return the boatInsuranceButton
     */
    public Button getBoatInsuranceButton() {
        return boatInsuranceButton;
    }

    /**
     * @return the homeInsuranceButton
     */
    public Button getHomeInsuranceButton() {
        return homeInsuranceButton;
    }

    /**
     * @return the holidayHomeInsuranceButton
     */
    public Button getHolidayHomeInsuranceButton() {
        return holidayHomeInsuranceButton;
    }

    /**
     * @return the travelInsuranceButton
     */
    public Button getTravelInsuranceButton() {
        return travelInsuranceButton;
    }

    /**
     * @return the claimButton
     */
    public Button getClaimButton() {
        return claimButton;
    }
}
