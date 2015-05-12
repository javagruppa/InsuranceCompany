/*
 * Here comes the text of your license
 * Each line should be prefixed with  * 
 */
package insurancecompany.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 *
 * @author André
 */
public class ProcessView {
    
    private Scene scene;
    private BorderPane mainPane;
    
    private ToggleButton claimsButton;
    private ToggleButton billsButton;
    private ToggleButton subscriptionsButton;
    private ToggleGroup toggleGroup;
            
    public void show(Stage stage) {

    }
    
    public ProcessView() {
        mainPane = new BorderPane();
        mainPane.setTop(createToolBar());
        scene = new Scene(mainPane);
             
    }
    
    private HBox createToolBar() {
        HBox hbox = new HBox();
        hbox.getStyleClass().add("insurancecompany/resources/css/stylesheet.css");
        hbox.setStyle("-fx-background-color: #6577A1;");
        hbox.setPrefSize(640, 20);
        claimsButton = new ToggleButton("Skademeldinger");
        claimsButton.setId("subToolbarButton");
        billsButton = new ToggleButton("Regninger");
        billsButton.setId("subToolbarButton");
        subscriptionsButton = new ToggleButton("Abonnementer");
        subscriptionsButton.setId("subToolbarButton");
        toggleGroup = new ToggleGroup();
        claimsButton.setToggleGroup(toggleGroup);
        billsButton.setToggleGroup(toggleGroup);
        subscriptionsButton.setToggleGroup(toggleGroup);
        ObservableList<ToggleButton> buttons = FXCollections.observableArrayList();
        buttons.addAll(claimsButton, billsButton, subscriptionsButton);        
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

    public void setBillsButtonEventHandler(EventHandler<ActionEvent> value) {
        billsButton.setOnAction(value);
    }
    
    public void setSubscriptionsButtonEventHandler(EventHandler<ActionEvent> value) {
        subscriptionsButton.setOnAction(value);
    }
}

