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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;


/**
 *
 * @author André
 */
public class SearchView {
    
    private Scene scene;
    private BorderPane mainPane;
    
    private Button customersButton;
    private Button employeesButton;
    private Button insurancesButton;
    private Button claimsButton;
            
    public void show(Stage stage) {
        stage.setTitle("Kunderegistrering");
        stage.setScene(scene);
        stage.show();
    }
    
    public SearchView() {
        mainPane = new BorderPane();
        mainPane.setTop(createToolBar());
        scene = new Scene(mainPane, 800, 600);
             
    }
    
    public void selectedButtonStyleLower(Button button) {
        customersButton.setId("subToolbarButton");
        insurancesButton.setId("subToolbarButton");
        claimsButton.setId("subToolbarButton");
        button.setId("subToolbarButtonSelected");
    }
    
    private HBox createToolBar() {
        HBox hbox = new HBox();
        hbox.getStyleClass().add("insurancecompany/resources/css/stylesheet.css");
        hbox.setStyle("-fx-background-color: #6577A1;");
        hbox.setPrefSize(640, 20);
        customersButton = new Button("Kunder");
        customersButton.setId("subToolbarButton");
        employeesButton = new Button("Ansatte");
        employeesButton.setId("subToolbarButton");
        insurancesButton = new Button("Forsikringer");
        insurancesButton.setId("subToolbarButton");
        claimsButton = new Button("Skademeldinger");
        claimsButton.setId("subToolbarButton");
        ObservableList<Button> buttons = FXCollections.observableArrayList ();
        buttons.addAll(customersButton, employeesButton, insurancesButton, claimsButton);        
        hbox.getChildren().addAll(buttons);
       
        return hbox;
    }
    
    // GET MAIN PANE
    
    public BorderPane getMainPane() {
        return mainPane;
    }
    
    // SET EVENT HANDLERS
    
    public void setCustomersButtonEventHandler(EventHandler<ActionEvent> value) {
        customersButton.setOnAction(value);
    }
    
    public void setEmployeesButtonEventHandler(EventHandler<ActionEvent> value) {
        employeesButton.setOnAction(value);
    }
    
    public void setInsurancesButtonEventHandler(EventHandler<ActionEvent> value) {
        insurancesButton.setOnAction(value);
    }
    
    public void setClaimsButtonEventHandler(EventHandler<ActionEvent> value) {
        claimsButton.setOnAction(value);
    }
}
