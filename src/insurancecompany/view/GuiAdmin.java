/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package insurancecompany.view;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author André
 */
public class GuiAdmin extends Application {
    
    
    private RegisterPane registerPaneO;
    private CustomerRegistration customerRegistration;
    private BoatInsuranceRegistration boatInsuranceRegistration;
    private TravelInsuranceRegistration travelInsuranceRegistration;
    
    private Scene scene;
    private BorderPane mainPane;
    private Button registerTabButton;
    private Button searchTabButton;
    private Button statisticsTabButton;
    private Button logOutButton;
    private Button exitButton;
    private Text userStatusText;
    
    private BorderPane registerPane;
    private GridPane customerRegistrationPane;
    private GridPane travelInsurancePane;     
    
    public GuiAdmin() {
        mainPane = new BorderPane();
        VBox vbox = new VBox();
        vbox.getChildren().addAll(createToolBar(), createStatusBar());
        mainPane.setTop(vbox);
        scene = new Scene(getMainPane(), 900, 600);
        scene.getStylesheets().add("stylesheet.css");
    }
    public void start(Stage stage) throws Exception {
        show(stage);
    }
    
    public void show(Stage stage) {
        stage.setTitle("Kunderegistrering");
        stage.setScene(getScene());
        stage.show();
    }
    
    public void initialize() {
        registerTabButton.setOnAction((event) -> {
            mainPane.setCenter(registerPane);
        });
    }
    
    
    public HBox createToolBar() {
        HBox hbox = new HBox();
        hbox.setStyle("-fx-background-color: #336699;");
        registerTabButton = new Button("Registrer");
        getRegisterButton().setId("mainToolbarButton");
        searchTabButton = new Button("Søk");
        getSearchButton().setId("mainToolbarButton");
        statisticsTabButton = new Button("Statistikk");
        getStatisticsButton().setId("mainToolbarButton");
        
        logOutButton = new Button("Logg ut");
        getLogOutButton().setId("mainToolbarButton");
        exitButton = new Button("Avslutt");
        getExitButton().setId("mainToolbarButton");
        
        HBox hbox1 = new HBox();
        hbox1.getChildren().addAll(getLogOutButton(), getExitButton());
        hbox1.setAlignment(Pos.CENTER_RIGHT);
        HBox.setHgrow(hbox1, Priority.ALWAYS);
        
        hbox.getChildren().addAll(getRegisterButton(), getSearchButton(), getStatisticsButton());
        hbox.getChildren().add(hbox1);
        
        return hbox;
    }
    
    private HBox createStatusBar() {
        HBox hbox = new HBox();
        hbox.setPadding(new Insets(5, 0, 5, 0));
        hbox.setStyle("-fx-background-color: #FFFFFF;");
        
        userStatusText = new Text("Logget inn som Admin. Ansattnr: 10000000");
        hbox.getChildren().add(getUserStatusText());
        return hbox;
    }
    
    private void selectedButtonStyleUpper(Button button) {
        registerTabButton.setId("mainToolbarButton");
        searchTabButton.setId("mainToolbarButton");
        statisticsTabButton.setId("mainToolbarButton");
        button.setStyle("mainToolbarButtonSelected");
    }
    // TODO: Change to setId, and make a custom style for selected, will not have hover etc
    private void selectedButtonStyleLower(Button button) {
        registerPane.getCustomerButton().setStyle("-fx-background-color: linear-gradient(#395cab, #223768);");
        registerPane.getCarInsuranceButton().setStyle("-fx-background-color: linear-gradient(#395cab, #223768);");
        registerPane.getBoatInsuranceButton().setStyle("-fx-background-color: linear-gradient(#395cab, #223768);");
        registerPane.getHomeInsuranceButton().setStyle("-fx-background-color: linear-gradient(#395cab, #223768);");
        registerPane.getHolidayHomeInsurance().setStyle("-fx-background-color: linear-gradient(#395cab, #223768);");
        registerPane.getTravelInsuranceButton().setStyle("-fx-background-color: linear-gradient(#395cab, #223768);");
        registerPane.getClaimButton().setStyle("-fx-background-color: linear-gradient(#395cab, #223768);");
        button.setStyle("-fx-background-color: linear-gradient(#ffffff, #223768);");
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
     * @return the registerTabButton
     */
    public Button getRegisterButton() {
        return registerTabButton;
    }

    /**
     * @return the searchTabButton
     */
    public Button getSearchButton() {
        return searchTabButton;
    }

    /**
     * @return the statisticsTabButton
     */
    public Button getStatisticsButton() {
        return statisticsTabButton;
    }

    /**
     * @return the logOutButton
     */
    public Button getLogOutButton() {
        return logOutButton;
    }

    /**
     * @return the exitButton
     */
    public Button getExitButton() {
        return exitButton;
    }

    /**
     * @return the userStatusText
     */
    public Text getUserStatusText() {
        return userStatusText;
    }
    
}
