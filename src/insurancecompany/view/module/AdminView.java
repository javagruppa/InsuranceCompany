/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package insurancecompany.view.module;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.BorderPane;
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
 * @author Sindre
 */
public class AdminView extends Application {
    
    private Stage primaryStage;
    
    private double xOffset = 0;
    private double yOffset = 0;
    //private double dx = 0;
    //private double dy = 0;
    //private boolean resizeBottom = false;
    
    private Scene scene;
    private BorderPane mainPane;
    
    private Pane toolBarPane;
    private Pane statusBarPane;
    
    private Button registerTabButton;
    private Button searchTabButton;
    private Button statisticsTabButton;
    private Button logOutButton;
    private Button exitButton;
    private Text userStatusText;
    
    public AdminView() {
        initializeViews();
        initializeEventHandlers();
        scene = new Scene(mainPane, 1200, 700);
        scene.getStylesheets().add("insurancecompany/resources/css/stylesheet.css");
        
    }
    
    public void start(Stage stage) throws Exception {
        show(stage);
    }
    
    public void show(Stage stage) {
        primaryStage = stage;
        //primaryStage.setFullScreen(true);
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setTitle("Kunderegistrering");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    private void initializeViews() {
        mainPane = new BorderPane();
        VBox vbox = new VBox();
        toolBarPane = createToolBar();
        statusBarPane = createStatusBar();
        vbox.getChildren().addAll(toolBarPane, statusBarPane);
        mainPane.setTop(vbox);
    }
    
    private void initializeEventHandlers() {
        registerTabButton.setOnAction(event -> selectedButtonStyleUpper(registerTabButton));
        
        toolBarPane.setOnMouseClicked((event) -> {
            if(event.getButton().equals(MouseButton.PRIMARY)){
                if(event.getClickCount() == 2){
                    if (primaryStage.isFullScreen()) {
                        primaryStage.setFullScreen(false);
                    } else if (!primaryStage.isFullScreen()) {
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
        hbox.setStyle("-fx-background-color: #6577A1;");
        registerTabButton = new Button("Registrer");
        registerTabButton.setId("mainToolbarButton");
        searchTabButton = new Button("Søk");
        searchTabButton.setId("mainToolbarButton");
        statisticsTabButton = new Button("Statistikk");
        statisticsTabButton.setId("mainToolbarButton");
        
        logOutButton = new Button("Logg ut");
        logOutButton.setId("mainToolbarButton");
        exitButton = new Button("Avslutt");
        exitButton.setId("mainToolbarButton");
        
        HBox hbox1 = new HBox();
        hbox1.getChildren().addAll(logOutButton, exitButton);
        hbox1.setAlignment(Pos.CENTER_RIGHT);
        HBox.setHgrow(hbox1, Priority.ALWAYS);
        
        Image ifLogo = new Image("insurancecompany/resources/images/if.png");
        hbox.getChildren().addAll(new ImageView(ifLogo), registerTabButton, searchTabButton, statisticsTabButton);
        hbox.getChildren().add(hbox1);
        
        return hbox;
    }
    
    private HBox createStatusBar() {
        HBox hbox = new HBox();
        hbox.setPadding(new Insets(5, 0, 5, 0));
        hbox.setStyle("-fx-background-color: #E7E7FF;");
        
        userStatusText = new Text("Logget inn som Admin. Ansattnr: 10000000");
        hbox.getChildren().add(userStatusText);
        return hbox;
    }
    
    private void selectedButtonStyleUpper(Button button) {
        registerTabButton.setId("mainToolbarButton");
        searchTabButton.setId("mainToolbarButton");
        statisticsTabButton.setId("mainToolbarButton");
        button.setId("mainToolbarButtonSelected");
    }
    
    // GET MAIN PANE
    
    public BorderPane getMainPane() {
        return mainPane;
    }
    
    // SET EVENT HANDLERS

    public void setRegisterButtonEventHandler(EventHandler<ActionEvent> value) {
        registerTabButton.setOnAction(value);
    }

    public void setSearchButtonEventHandler(EventHandler<ActionEvent> value) {
        searchTabButton.setOnAction(value);
    }

    public void setStatisticsButtonEventHandler(EventHandler<ActionEvent> value) {
        statisticsTabButton.setOnAction(value);
    }

    public void setLogOutButtonEventHandler(EventHandler<ActionEvent> value) {
        logOutButton.setOnAction(value);
    }

    public void setExitButtonEventHandler(EventHandler<ActionEvent> value) {
        exitButton.setOnAction(value);
    }
}