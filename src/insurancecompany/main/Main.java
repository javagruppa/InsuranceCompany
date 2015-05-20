
package insurancecompany.main;

import insurancecompany.controller.*;

import javafx.application.Application;
import javafx.stage.Stage;

/**Main class.
 * Contains the applications main-method
 *
 * @author André
 * @since 19.05.2015
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
    
    /**
     * Opens a new MainController
     * @param stage sets the stage
     */
    @Override
    public void start(Stage stage) {
        controller = new MainController();
        controller.show(stage);
    }
    
}
