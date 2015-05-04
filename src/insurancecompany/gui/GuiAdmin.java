/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package insurancecompany.gui;

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
    
    
    private Scene scene;
    private BorderPane mainPane;
    private Button registerButton;
    private Button searchButton;
    private Button statisticsButton;
    private Button logOutButton;
    private Button exitButton;
    private Text userStatusText;
            
    public static void main(String[] args) {
        launch(args);
    }
    

    @Override
    public void start(Stage stage) throws Exception {
        show(stage);
    }
    
    public void show(Stage stage) {
        stage.setTitle("Kunderegistrering");
        stage.setScene(getScene());
        stage.show();
    }
    
    public GuiAdmin() {
        mainPane = new BorderPane();
        VBox vbox = new VBox();
        vbox.getChildren().addAll(createToolBar(), createStatusBar());
        mainPane.setTop(vbox);
        scene = new Scene(getMainPane(), 900, 600);
        scene.getStylesheets().add("stylesheet.css");
    }
    
    public HBox createToolBar() {
        HBox hbox = new HBox();
        hbox.setStyle("-fx-background-color: #336699;");
        registerButton = new Button("Registrer");
        getRegisterButton().setId("mainToolbarButton");
        searchButton = new Button("Søk");
        getSearchButton().setId("mainToolbarButton");
        statisticsButton = new Button("Statistikk");
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
    
    public HBox createStatusBar() {
        HBox hbox = new HBox();
        hbox.setPadding(new Insets(5, 0, 5, 0));
        hbox.setStyle("-fx-background-color: #FFFFFF;");
        
        userStatusText = new Text("Logget inn som Admin. Ansattnr: 10000000");
        hbox.getChildren().add(getUserStatusText());
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

    /**
     * @return the registerButton
     */
    public Button getRegisterButton() {
        return registerButton;
    }

    /**
     * @return the searchButton
     */
    public Button getSearchButton() {
        return searchButton;
    }

    /**
     * @return the statisticsButton
     */
    public Button getStatisticsButton() {
        return statisticsButton;
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
