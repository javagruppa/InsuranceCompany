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
 * @author Sindre
 */
public class RegisterView {
    
    private Scene scene;
    private BorderPane mainPane;
    
    private Button personsButton;
    private Button insurancesButton;
    private Button claimsButton;
            
    public void show(Stage stage) {
        stage.setTitle("Kunderegistrering");
        stage.setScene(scene);
        stage.show();
    }
    
    public RegisterView() {
        mainPane = new BorderPane();
        mainPane.setTop(createToolBar());
        scene = new Scene(mainPane);
             
    }
    
    public void selectedButtonStyleLower(Button button) {
        personsButton.setId("subToolbarButton");
        insurancesButton.setId("subToolbarButton");
        claimsButton.setId("subToolbarButton");
        button.setId("subToolbarButtonSelected");
    }
    
    private HBox createToolBar() {
        HBox hbox = new HBox();
        hbox.getStyleClass().add("insurancecompany/resources/css/stylesheet.css");
        hbox.setStyle("-fx-background-color: #6577A1;");
        hbox.setPrefSize(640, 20);
        personsButton = new Button("Personer");
        personsButton.setId("subToolbarButton");
        insurancesButton = new Button("Forsikringer");
        insurancesButton.setId("subToolbarButton");
        claimsButton = new Button("Skademeldinger");
        claimsButton.setId("subToolbarButton");
        ObservableList<Button> buttons = FXCollections.observableArrayList ();
        buttons.addAll(personsButton, insurancesButton, claimsButton);        
        hbox.getChildren().addAll(buttons);
       
        return hbox;
    }
    
    // GET MAIN PANE
    
    public BorderPane getMainPane() {
        return mainPane;
    }
    
    // SET EVENT HANDLERS
    
    public void setPersonsButtonEventHandler(EventHandler<ActionEvent> value) {
        personsButton.setOnAction(value);
    }

    public void setInsurancesButtonEventHandler(EventHandler<ActionEvent> value) {
        insurancesButton.setOnAction(value);
    }
    
    public void setClaimsButtonEventHandler(EventHandler<ActionEvent> value) {
        claimsButton.setOnAction(value);
    }
}
