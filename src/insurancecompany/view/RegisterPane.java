/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package insurancecompany.view;

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
public class RegisterPane extends Application {
    
    
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
    

    @Override
    public void start(Stage stage) throws Exception {
        show(stage);
    }
    
    public void show(Stage stage) {
        stage.setTitle("Kunderegistrering");
        stage.setScene(getScene());
        stage.show();
    }
    
    public RegisterPane() {
        mainPane = new BorderPane();
        
        mainPane.setTop(createToolBar());
        scene = new Scene(getMainPane(), 800, 600);
    }
    
    public HBox createToolBar() {
        HBox hbox = new HBox();
        hbox.getStyleClass().add("stylesheet.css");
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
    
    public Pane getPane() {
        return getMainPane();
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
