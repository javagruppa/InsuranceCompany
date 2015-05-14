/*
 * Here comes the text of your license
 * Each line should be prefixed with  * 
 */
package insurancecompany.view.register.claims;

import insurancecompany.model.people.Customer;
import insurancecompany.model.people.VehicleOwner;
import insurancecompany.model.properties.Address;
import insurancecompany.model.vehicles.Car;
import java.util.Calendar;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author André
 */
public class CarClaimFormView {
    
    private ScrollPane scrollPane;
    private StackPane stackPane;
    private GridPane gridPane;
    private Scene scene;
    private Image image;
    
    /** The date of the accident */
    private DatePicker date;
    /** The location of the accident */
    private TextField location;
    /** if there is any witnesses to the accident */
    private TextArea witnesses;
    
    /**the last name of the person*/
    private TextField lastNameA;
    /**the first name of the person*/
    private TextField firstNameA;
    /**the personal number of the person*/
    private TextField personalNumberA;
    private TextField streetA; // Includes street name, number and letter.
    private TextField zipCodeA;
    private TextField cityA;
    /**the phone number of the person*/
    private TextField phoneA;
    /**the e-mail address for the person*/
    private TextField emailA;
    private TextField registrationNumberA;
    private TextField brandA;
    
    
    /**the last name of the person*/
    private TextField lastNameB;
    /**the first name of the person*/
    private TextField firstNameB;
    /**the personal number of the person*/
    private TextField personalNumberB;
    private TextField streetB; // Includes street name, number and letter.
    private TextField zipCodeB;
    private TextField cityB;
    /**the phone number of the person*/
    private TextField phoneB;
    /**the e-mail address for the person*/
    private TextField emailB;
    private TextField registrationNumberB;
    private TextField brandB;
   
    /** The course of events */
    private TextArea courseOfEvents;
    
    private Car carA;
    /** The customer who owns the insurance */
    private Customer ownerA;
    /** The owner of another vehicle involved in the accident */
    private VehicleOwner otherPerson;
    /** Another car involved in the accident */
    private Car carB;
    /** The name of the insurance company of the other part involved */
    private String insuranceCompanyB;

    
    /** The Id for the insurance covering this damage */
    private int insuranceId;

    
    public CarClaimFormView() {
        stackPane = new StackPane();
        image = new Image("insurancecompany/resources/images/carclaimform.jpg");
        ImageView imageView = new ImageView(image);
        

        gridPane = new GridPane();
        gridPane.setAlignment(Pos.TOP_LEFT);
        gridPane.setHgap(10);
        gridPane.setVgap(6);
        ColumnConstraints col0 = new ColumnConstraints(60); // gap
        ColumnConstraints col1 = new ColumnConstraints(100); // first field
        ColumnConstraints col2 = new ColumnConstraints(45); // gap
        ColumnConstraints col3 = new ColumnConstraints(5); // gap
        ColumnConstraints col4 = new ColumnConstraints(160); // second field
        ColumnConstraints col5 = new ColumnConstraints(10); // gap
        ColumnConstraints col6 = new ColumnConstraints(280); // third field
        ColumnConstraints col7 = new ColumnConstraints(10); // gap
        ColumnConstraints col8 = new ColumnConstraints(150); // fourth field
        ColumnConstraints col9 = new ColumnConstraints(5); // gap
        ColumnConstraints col10 = new ColumnConstraints(150); // fifth field
        // Add these constraints:
        gridPane.getColumnConstraints().addAll(col0, col1, col2, col3, col4, col5, col6, col7, col8, col9, col10);
        
        RowConstraints row0 = new RowConstraints(65); // gap
        RowConstraints row1 = new RowConstraints(50); // first field
        RowConstraints row2 = new RowConstraints(17); // gap
        RowConstraints row3 = new RowConstraints(55); // second field
        RowConstraints row4 = new RowConstraints(27); // gap
        RowConstraints row5 = new RowConstraints(50); // third field
        RowConstraints row6 = new RowConstraints(30); // fourth field
        RowConstraints row7 = new RowConstraints(50);
        RowConstraints row8 = new RowConstraints(50);
        RowConstraints row9 = new RowConstraints(50);
        // Add these constraints:
        gridPane.getRowConstraints().addAll(row0, row1, row2, row3, row4, row5, row6, row7, row8, row9);
 
        stackPane.getChildren().addAll(imageView, gridPane);
        scrollPane = new ScrollPane();
        scrollPane.setContent(stackPane);
        
        
        date = new DatePicker();
        location = new TextField();
        witnesses = new TextArea();
        witnesses.setWrapText(true);
               
        lastNameA = new TextField();
        firstNameA = new TextField();
        personalNumberA = new TextField();
        streetA = new TextField();
        zipCodeA = new TextField();
        cityA = new TextField();
        phoneA = new TextField();
        emailA = new TextField();
        registrationNumberA = new TextField();
        brandA = new TextField();


        lastNameB = new TextField();
        firstNameB = new TextField();
        personalNumberB = new TextField();
        streetB = new TextField();
        zipCodeB = new TextField();
        cityB = new TextField();
        phoneB = new TextField();
        emailB = new TextField();
        registrationNumberB = new TextField();
        brandB = new TextField();

        courseOfEvents = new TextArea();
        
        // ADD TO THE GRIDPANE:
        
        gridPane.add(date, 1, 1);

        gridPane.add(location, 4, 1, 4, 1);
        gridPane.add(witnesses, 6, 3, 5, 1);              
        gridPane.add(lastNameA, 2, 5, 3, 1);
        gridPane.add(firstNameA, 1, 6, 2, 1);
        
        gridPane.add(personalNumberA, 4, 6);
        /*
        gridPane.add(streetA
        gridPane.add(zipCodeA
        gridPane.add(cityA
        gridPane.add(phoneA
        gridPane.add(emailA
        gridPane.add(registrationNumberA
        gridPane.add(brandA


        gridPane.add(lastNameB
        gridPane.add(firstNameB
        gridPane.add(personalNumberB
        gridPane.add(streetB
        gridPane.add(zipCodeB
        gridPane.add(cityB
        gridPane.add(phoneB
        gridPane.add(emailB
        gridPane.add(registrationNumberB
        gridPane.add(brandB
        
        
        gridPane.add(courseOfEvents
        */

        scene = new Scene(scrollPane, 1150, 650);
        scene.getStylesheets().add("insurancecompany/resources/css/stylesheetCarClaimForm.css");
    }
    
    public void show(Stage stage) {
        stage.setTitle("Innlogging");
        
        stage.setScene(scene);
        stage.show();
    }
}
