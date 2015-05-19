/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package insurancecompany.view;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
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
public class MainView extends Application {
    
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
    
    private ToggleButton registerTabButton;
    private ToggleButton searchTabButton;
    private ToggleButton statisticsTabButton;
    private ToggleGroup toggleGroup;
    private Button saveDataButton;
    private Button logOutButton;
    private Button exitButton;
    private Text userStatusText;
    
    public MainView() {
        initializeViews();
        scene = new Scene(mainPane, 1280, 700);
        scene.getStylesheets().add("insurancecompany/resources/css/stylesheet.css");       
    }
    
    @Override
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
    
    private HBox createToolBar() {
        HBox hbox = new HBox();
        hbox.setStyle("-fx-background-color: #6577A1;");
        registerTabButton = new ToggleButton("Registrer");
        registerTabButton.setId("mainToolbarButton");
        searchTabButton = new ToggleButton("Søk");
        searchTabButton.setId("mainToolbarButton");
        statisticsTabButton = new ToggleButton("Statistikk");
        statisticsTabButton.setId("mainToolbarButton");
        toggleGroup = new ToggleGroup();
        registerTabButton.setToggleGroup(toggleGroup);
        searchTabButton.setToggleGroup(toggleGroup);
        statisticsTabButton.setToggleGroup(toggleGroup);
        
        saveDataButton = new Button("Lagre datastrukturer");
        saveDataButton.setId("mainToolbarButton");
        logOutButton = new Button("Logg ut");
        logOutButton.setId("mainToolbarButton");
        exitButton = new Button("Avslutt");
        exitButton.setId("mainToolbarButton");
        
        HBox hbox1 = new HBox();
        hbox1.getChildren().addAll(saveDataButton, logOutButton, exitButton);
        hbox1.setAlignment(Pos.CENTER_RIGHT);
        HBox.setHgrow(hbox1, Priority.ALWAYS);
        
        Image ifLogo = new Image("insurancecompany/resources/images/asiflogo.png");
        hbox.getChildren().addAll(new ImageView(ifLogo), registerTabButton, 
                searchTabButton, statisticsTabButton);
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
    
    public void setSaveDataButtonEventHandler(EventHandler<ActionEvent> value) {
        saveDataButton.setOnAction(value);
    }

    public void setLogOutButtonEventHandler(EventHandler<ActionEvent> value) {
        logOutButton.setOnAction(value);
    }

    public void setExitButtonEventHandler(EventHandler<ActionEvent> value) {
        exitButton.setOnAction(value);
    }
    
    public void setToolbarOnMouseClickedEventHandler(EventHandler<MouseEvent> value) {
        toolBarPane.setOnMouseClicked(value);
    }
    
    public void setToolbarOnMouseDraggedEventHandler(EventHandler<MouseEvent> value) {
        toolBarPane.setOnMouseDragged(value);
    }
    
    public void setToolbarOnMousePressedEventHandler(EventHandler<MouseEvent> value) {
        toolBarPane.setOnMousePressed(value);
    }

    /**
     * @return the xOffset
     */
    public double getxOffset() {
        return xOffset;
    }

    /**
     * @param xOffset the xOffset to set
     */
    public void setxOffset(double xOffset) {
        this.xOffset = xOffset;
    }

    /**
     * @return the yOffset
     */
    public double getyOffset() {
        return yOffset;
    }

    /**
     * @param yOffset the yOffset to set
     */
    public void setyOffset(double yOffset) {
        this.yOffset = yOffset;
    }
}