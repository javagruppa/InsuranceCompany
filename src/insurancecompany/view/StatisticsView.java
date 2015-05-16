/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package insurancecompany.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;


/**
 *
 * @author André
 */
public class StatisticsView {
    
    private Scene scene;
    private BorderPane mainPane;
    
    private ToggleButton claimsButton;
    private ToggleButton disbursementsButton;
    private ToggleButton incomeButton;
    private ToggleGroup toggleGroup;
            
    public void show(Stage stage) {
        stage.setTitle("Kunderegistrering");
        stage.setScene(scene);
        stage.show();
    }
    
    public StatisticsView() {
        mainPane = new BorderPane();
        mainPane.setTop(createToolBar());
        scene = new Scene(mainPane, 800, 600);
             
    }

    private HBox createToolBar() {
        HBox hbox = new HBox();
        hbox.getStyleClass().add("insurancecompany/resources/css/stylesheet.css");
        hbox.setStyle("-fx-background-color: #6577A1;");
        hbox.setPrefSize(640, 20);
        claimsButton = new ToggleButton("Skademeldinger");
        claimsButton.setId("subToolbarButton");
        disbursementsButton = new ToggleButton("Utbetalinger");
        disbursementsButton.setId("subToolbarButton");
        incomeButton = new ToggleButton("Inntekter");
        incomeButton.setId("subToolbarButton");
        toggleGroup = new ToggleGroup();
        claimsButton.setToggleGroup(toggleGroup);
        disbursementsButton.setToggleGroup(toggleGroup);
        incomeButton.setToggleGroup(toggleGroup);
        ObservableList<ToggleButton> buttons = FXCollections.observableArrayList ();
        buttons.addAll(claimsButton, disbursementsButton, incomeButton);        
        hbox.getChildren().addAll(buttons);
       
        return hbox;
    }
    
    // GET MAIN PANE
    
    public BorderPane getMainPane() {
        return mainPane;
    }
    
    // SET EVENT HANDLERS
    
    public void setClaimsButtonEventHandler(EventHandler<ActionEvent> value) {
        claimsButton.setOnAction(value);
    }
    
    public void setDisbursementsButtonEventHandler(EventHandler<ActionEvent> value) {
        disbursementsButton.setOnAction(value);
    }
    
    public void setIncomeButtonEventHandler(EventHandler<ActionEvent> value) {
        incomeButton.setOnAction(value);
    }
}
