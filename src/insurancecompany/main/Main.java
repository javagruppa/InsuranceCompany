/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package insurancecompany.main;

import insurancecompany.controller.*;

import javafx.application.Application;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 *
 * @author André
 */
public class Main extends Application {
    
    private MainController controller;
    
    /**
     * Main method. Launch the application.
     * @param args 
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    
    @Override
    public void start(Stage stage) {
        controller = new MainController();
        controller.show(stage);
    }
    
}
