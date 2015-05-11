/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package insurancecompany.view.register;

import insurancecompany.view.register.persons.CustomerRegistration;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
public class PersonsView {
    
    private CustomerRegistration customerRegistration;
    
    private Pane customerRegistrationPane;
    
    private Scene scene;
    private BorderPane mainPane;
    
    private Pane sideToolBarPane;
    
    private Button customerButton;
    private Button employeeButton;
            
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
    
    public PersonsView() {
        mainPane = new BorderPane();
        sideToolBarPane = createSideToolBar();
        //ScrollPane sp = new ScrollPane();
        //sp.setContent(sideToolBarPane);
        //sp.setFitToHeight(true);
        //sp.setFitToWidth(true);
        mainPane.setLeft(sideToolBarPane);
        scene = new Scene(mainPane, 800, 600);
        //initializeViews();
        //initializeEventHandlers();      
    }
    /*
    private void initializeViews() {
        //customerRegistration = new CustomerRegistration();
        //customerRegistrationPane = customerRegistration.getMainPane();
    }
    
    private void initializeEventHandlers() {
      
        customerButton.setOnAction((event) -> {
            mainPane.setCenter(customerRegistrationPane);
            selectedButtonStyleLower(customerButton);
        });
        
        employeeButton.setOnAction((event) -> {
            //mainPane.setCenter(boatInsuranceRegistrationPane);
            //selectedButtonStyleLower(employeeButton);
        });
    }
    */
    
    public void selectedButtonStyleLower(Button button) {
        customerButton.setId("sideToolbarButton");
        employeeButton.setId("sideToolbarButton");
        button.setId("sideToolbarButtonSelected");
    }
    
    private VBox createSideToolBar() {
        VBox vbox = new VBox();
        vbox.setPrefWidth(160);
        vbox.getStyleClass().add("insurancecompany/resources/css/stylesheet.css");
        vbox.setStyle("-fx-background-color: #6577A1;");
    
        customerButton = new Button("Ny kunde");       
        customerButton.setId("sideToolbarButton");
        Image customerImage = new Image("insurancecompany/resources/images/customer.png");
        customerButton.setGraphic(new ImageView(customerImage));
        //carInsuranceButton.setContentDisplay(ContentDisplay.LEFT);
        
        employeeButton = new Button("Ny ansatt");
        employeeButton.setId("sideToolbarButton");
        Image employeeImage = new Image("insurancecompany/resources/images/employee.png");
        employeeButton.setGraphic(new ImageView(employeeImage));
        
        ObservableList<Button> buttons = FXCollections.observableArrayList ();
        buttons.addAll(customerButton, employeeButton);
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

    
    public void setCustomerButtonEventHandler(EventHandler<ActionEvent> value) {
        customerButton.setOnAction(value);
    }
    
    public void setEmployeeButtonEventHandler(EventHandler<ActionEvent> value) {
        employeeButton.setOnAction(value);
    }
}
