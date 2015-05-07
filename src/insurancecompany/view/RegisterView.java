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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
public class RegisterView {
    
    //private CustomerRegistration customerRegistration;
    //private BoatInsuranceRegistration boatInsuranceRegistration;
    //private TravelInsuranceRegistration travelInsuranceRegistration;
    private InsurancesView insurancesView;
    private PersonsView personsView;
    
    private Pane personsPane;
    private Pane insurancesPane;
    private Pane claimsPane;
    
    private Scene scene;
    private BorderPane mainPane;
    private Button personsButton;
    private Button insurancesButton;
    private Button claimsButton;
            
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
    
    public RegisterView() {
        mainPane = new BorderPane();
        mainPane.setTop(createToolBar());
        initializeViews();
        initializeEventHandlers(); 
        mainPane.setCenter(insurancesPane);
        scene = new Scene(mainPane, 800, 600);
             
    }
    private void initializeViews() {
        insurancesView = new InsurancesView();
        insurancesPane = insurancesView.getMainPane();
        
        personsView = new PersonsView();
        personsPane = personsView.getMainPane();
        
        /*
        customerRegistration = new CustomerRegistration();
        customerRegistrationPane = customerRegistration.getMainPane();
        boatInsuranceRegistration = new BoatInsuranceRegistration();
        //boatInsuranceRegistrationPane = boatInsuranceRegistration.getMainPane();
        travelInsuranceRegistration = new TravelInsuranceRegistration();
        //travelInsuranceRegistrationPane = travelInsuranceRegistration.getMainPane();
        */
    }
    
    private void initializeEventHandlers() {
        personsButton.setOnAction((event) -> {
            mainPane.setCenter(personsPane);
            selectedButtonStyleLower(personsButton);
        });
        
        insurancesButton.setOnAction((event) -> {
            mainPane.setCenter(insurancesPane);
            selectedButtonStyleLower(insurancesButton);
        });
        
        claimsButton.setOnAction((event) -> {
            //mainPane.setCenter(claimsPane);
            selectedButtonStyleLower(claimsButton);
        });      
    }
    
    // TODO: Change to setId, and make a custom style for selected, will not have hover etc
    private void selectedButtonStyleLower(Button button) {
        personsButton.setId("subToolbarButton");
        insurancesButton.setId("subToolbarButton");
        claimsButton.setId("subToolbarButton");
        button.setId("subToolbarButtonSelected");
    }
    
    private HBox createToolBar() {
        HBox hbox = new HBox();
        hbox.getStyleClass().add("insurancecompany/resources/stylesheet.css");
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
}
