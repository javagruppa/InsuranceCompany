/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package insurancecompany.view;

import insurancecompany.view.register.TravelInsuranceRegistration;
import insurancecompany.view.register.CustomerRegistration;
import insurancecompany.view.register.BoatInsuranceRegistration;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author André
 */
public class AdminView extends Application {
    
    private Stage primaryStage;
    
    private RegisterView registerView;
    private CustomerRegistration customerRegistration;
    private BoatInsuranceRegistration boatInsuranceRegistration;
    private TravelInsuranceRegistration travelInsuranceRegistration;
    
    private double xOffset = 0;
    private double yOffset = 0;
    //private double dx = 0;
    //private double dy = 0;
    //private boolean resizeBottom = false;
    
    private Scene scene;
    private BorderPane mainPane;
    
    private Pane toolBarPane;
    private Pane statusBarPane;
    private Pane centerPane;
    
    private Button registerTabButton;
    private Button searchTabButton;
    private Button statisticsTabButton;
    private Button logOutButton;
    private Button exitButton;
    private Text userStatusText;
    
    private BorderPane registerPane;
    private GridPane customerRegistrationPane;
    private GridPane travelInsurancePane;     
    
    public AdminView() {
        initializeViews();
        initializeEventHandlers();
        scene = new Scene(mainPane, 900, 600);
        scene.getStylesheets().add("insurancecompany/resources/stylesheet.css");
        
    }
    public void start(Stage stage) throws Exception {
        show(stage);
    }
    
    public void show(Stage stage) {
        primaryStage = stage;
        primaryStage.setFullScreen(true);
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setTitle("Kunderegistrering");
        primaryStage.setScene(getScene());
        primaryStage.show();
    }
    
    private void initializeViews() {
        mainPane = new BorderPane();
        VBox vbox = new VBox();
        toolBarPane = createToolBar();
        statusBarPane = createStatusBar();
        vbox.getChildren().addAll(toolBarPane, statusBarPane);
        
        registerView = new RegisterView();
        registerPane = registerView.getMainPane();
        
        mainPane.setTop(vbox);
        centerPane = registerPane;
        mainPane.setCenter(centerPane);
        
        
        
    }
    
    private void initializeEventHandlers() {
        registerTabButton.setOnAction((event) -> {
            mainPane.setCenter(registerPane);
            selectedButtonStyleUpper(registerTabButton);
        });
        
        exitButton.setOnAction((event) -> {
            Platform.exit();     
        });
        
        toolBarPane.setOnMouseClicked((event) -> {
            if(event.getButton().equals(MouseButton.PRIMARY)){
            if(event.getClickCount() == 2){
                if (primaryStage.isFullScreen())
                    primaryStage.setFullScreen(false);
                else if (!primaryStage.isFullScreen()) {
                    primaryStage.setFullScreen(true);
                }
            }
        }
            
        });
        
        toolBarPane.setOnMousePressed((event) -> {
            if (!primaryStage.isFullScreen()) {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            }
        });
        
        toolBarPane.setOnMouseDragged((event) -> {
            if (!primaryStage.isFullScreen()) {
                primaryStage.setX(event.getScreenX() - xOffset);
                primaryStage.setY(event.getScreenY() - yOffset);
            }
        });        
        
    }
    
    
    private HBox createToolBar() {
        HBox hbox = new HBox();
        hbox.setStyle("-fx-background-color: #008ED5;");
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
        hbox.setStyle("-fx-background-color: #6FA9A0;");
        
        userStatusText = new Text("Logget inn som Admin. Ansattnr: 10000000");
        hbox.getChildren().add(getUserStatusText());
        return hbox;
    }
    
    private void selectedButtonStyleUpper(Button button) {
        registerTabButton.setId("mainToolbarButton");
        searchTabButton.setId("mainToolbarButton");
        statisticsTabButton.setId("mainToolbarButton");
        button.setId("mainToolbarButtonSelected");
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
