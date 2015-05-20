package insurancecompany.view.search;

import insurancecompany.model.insurances.CarInsurance;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**the view of already registered CarClaimForms.
 * For caseworkers.
 * 
 * @author André
 * 
 * @since 19.05.2015
 */
public class ClaimFormProcess {
    
    private ScrollPane scrollPane;
    private StackPane stackPane;
    private StackPane canvasHolder;
    private GridPane gridPane;
    private Stage stage;
    private Scene scene;
    private Button guiltyButton;
    private Button notGuiltyButton;
    
    private CarInsurance carInsurance;
    
    public ClaimFormProcess(Image image, CarInsurance carInsurance) {
        this.carInsurance = carInsurance;
        stackPane = new StackPane();
        canvasHolder = new StackPane();
        canvasHolder.setId("opacityBackground");
        ImageView imageView = new ImageView(image);
        
        gridPane = new GridPane();
        gridPane.setAlignment(Pos.BOTTOM_CENTER);
        guiltyButton = new Button("Kunde er skyldig");
        guiltyButton.setId("guilty");
        notGuiltyButton = new Button("Kunde er uskyldig");
        notGuiltyButton.setId("notGuilty");
        gridPane.add(guiltyButton, 0, 0);
        gridPane.add(notGuiltyButton, 1, 0);
        
        stackPane.getChildren().addAll(imageView, gridPane);
        scrollPane = new ScrollPane();
        scrollPane.setContent(stackPane);
        
        scene = new Scene(scrollPane, 1150, 650);
        scene.getStylesheets().add("insurancecompany/resources/css/stylesheetCarClaimForm.css");
        
        // initialize local event handlers:
        setGuiltyButtonEventHandler();
        setNotGuiltyButtonEventHandler();
    }
    
    public void show(Stage stage) {
        this.stage = stage;
        stage.setTitle("Bilskademeldingsskjema");      
        stage.setScene(scene);
        stage.show();
    }
    
    private void setGuiltyButtonEventHandler() {
        guiltyButton.setOnAction((event) -> {
            // drop bonus if guilty:
            carInsurance.dropBonus();
            // then close:
            stage.close();
        });        
    }
    
    private void setNotGuiltyButtonEventHandler() {
        notGuiltyButton.setOnAction((event) -> {
            // just close:
            stage.close();
        });
        
    }
}
