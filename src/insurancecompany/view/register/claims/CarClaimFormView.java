/*
 * Here comes the text of your license
 * Each line should be prefixed with  * 
 */
package insurancecompany.view.register.claims;

import insurancecompany.misc.DateUtility;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * Only the fields related to person B will be registered. The other fields are pulled
 * from the car insurance owner.
 * @author André
 */
public class CarClaimFormView {
    
    /** Scroll pane used to show the whole car claim form. */
    private ScrollPane scrollPane;
    /** Stack pane used to stack input nodes on top of the car claim form image. */
    private StackPane stackPane;
    /** Stack pane used to hold the canvas, which opens up for setting a background color to the canvas. */
    private StackPane canvasHolder;
    /** Grid pane used to lay out the user action nodes. */
    private GridPane gridPane;
    /** Stage used to display this view. */
    private Stage stage;
    /** Scene used to display this view. */
    private Scene scene;
    /** Car claim form background image. */
    private Image image;
    /** Canvas used to draw an image of the accident. */
    private Canvas canvas;
    
    /** The date of the accident. */
    private DatePicker date;
    /** The location of the accident. */
    private TextField location;
    /** if there is any witnesses to the accident */
    private TextArea witnesses;
    
    /**the last name of the person.*/
    private TextField lastNameA;
    /**the first name of the person.*/
    private TextField firstNameA;
    /**the personal number of the person.*/
    private TextField personalNumberA;
    private TextField streetA; // Includes street name, number and letter.
    private TextField zipCodeA;
    /**the phone number of the person.*/
    private TextField phoneA;
    /**the e-mail address for the person.*/
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
    /**The phone number of the person. */
    private TextField phoneB;
    /**The e-mail address for the person.*/
    private TextField emailB;
    private TextField registrationNumberB;
    private TextField brandB;
    /** The name of the insurance company of the other part involved */
    private TextField insuranceCompanyB;
    
    // TODO: Remember to onAction close window when button is clicked. 
    // If not all fields are filled in correctly, show a dialog window:
    private Button registerButton;
    private Button clearCanvasButton;
    
    /** Reference to the car claim view that owns this class.*/
    private CarClaimRegistration carClaimRegistration;

    /**
     * Sole constructor. Receives a reference to its car claim.
     */
    public CarClaimFormView(CarClaimRegistration carClaimRegistration) {
        this.carClaimRegistration = carClaimRegistration;
        stackPane = new StackPane();
        canvasHolder = new StackPane();
        canvasHolder.setId("opacityBackground");
        image = new Image("insurancecompany/resources/images/carclaimform.jpg");
        ImageView imageView = new ImageView(image);
        

        gridPane = new GridPane();
        gridPane.setAlignment(Pos.TOP_LEFT);
        gridPane.setHgap(10);
        gridPane.setVgap(6);
        // Column constraints for the gridpane
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
        // Row constraints for the gridpane
        RowConstraints row0 = new RowConstraints(65); // gap
        RowConstraints row1 = new RowConstraints(50); // Skadedato, skadested
        RowConstraints row2 = new RowConstraints(17); // gap
        RowConstraints row3 = new RowConstraints(55); // Vitner
        RowConstraints row4 = new RowConstraints(27); // gap
        RowConstraints row5 = new RowConstraints(50); // Etternavn
        RowConstraints row6 = new RowConstraints(30); // Fornavn, fødselsnummer
        RowConstraints row7 = new RowConstraints(35); // Adresse
        RowConstraints row8 = new RowConstraints(38); // Postnr
        RowConstraints row9 = new RowConstraints(38); // Telefon
        RowConstraints row10 = new RowConstraints(36); // Epost
        RowConstraints row11 = new RowConstraints(42); // Regnr
        RowConstraints row12 = new RowConstraints(38); // Merke
        RowConstraints row13 = new RowConstraints(38); // empty
        RowConstraints row14 = new RowConstraints(38); // Forsikringsselskap
        RowConstraints row15 = new RowConstraints(38); // empty
        RowConstraints row16 = new RowConstraints(295); // HUGE gap
        RowConstraints row17 = new RowConstraints(20); // Beskrivelse Label
        RowConstraints row18 = new RowConstraints(400); // Beskrivelse
        RowConstraints row19 = new RowConstraints(100); // Registrer knapp
        // Add these constraints:
        gridPane.getRowConstraints().addAll(row0, row1, row2, row3, row4, row5, 
                row6, row7, row8, row9, row10, row11, row12, row13, row14, row15,
                row16, row17, row18, row19);
 
        stackPane.getChildren().addAll(imageView, gridPane);
        scrollPane = new ScrollPane();
        scrollPane.setContent(stackPane);
        
        
        date = new DatePicker();
        restrictDatePicker(date);
        location = new TextField();
        witnesses = new TextArea();
        witnesses.setWrapText(true);
               
        lastNameA = new TextField();
        firstNameA = new TextField();
        personalNumberA = new TextField();
        streetA = new TextField();
        zipCodeA = new TextField();
        phoneA = new TextField();
        emailA = new TextField();
        registrationNumberA = new TextField();
        brandA = new TextField();


        lastNameB = new TextField();
        firstNameB = new TextField();
        personalNumberB = new TextField();
        streetB = new TextField();
        zipCodeB = new TextField();
        phoneB = new TextField();
        emailB = new TextField();
        registrationNumberB = new TextField();
        brandB = new TextField();
        insuranceCompanyB = new TextField();
        
        registerButton = new Button("Registrer");
        registerButton.setId("custom");
        clearCanvasButton = new Button("Fjern tegning");
        clearCanvasButton.setId("custom1");
        canvas = new Canvas(680, 390);
        canvas.setCursor(Cursor.CROSSHAIR);
        final GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
        initDraw(graphicsContext);
         
        canvas.addEventHandler(MouseEvent.MOUSE_PRESSED, (MouseEvent event) -> {
            graphicsContext.beginPath();
            graphicsContext.moveTo(event.getX(), event.getY());
            graphicsContext.stroke();
        });
        canvas.addEventHandler(MouseEvent.MOUSE_DRAGGED, (MouseEvent event) -> {
            graphicsContext.lineTo(event.getX(), event.getY());
            graphicsContext.stroke();
        });
        canvas.addEventHandler(MouseEvent.MOUSE_RELEASED, (MouseEvent event) -> {
        });
        canvasHolder.getChildren().add(canvas);

        // ADD TO THE GRIDPANE:
        
        gridPane.add(date, 1, 1);

        gridPane.add(location, 4, 1, 4, 1);
        gridPane.add(witnesses, 6, 3, 5, 1);
        
        gridPane.add(lastNameA, 2, 5, 3, 1);
        gridPane.add(firstNameA, 1, 6, 2, 1);
        gridPane.add(personalNumberA, 4, 6);
        gridPane.add(streetA, 1, 7, 4, 1);
        gridPane.add(zipCodeA, 1, 8, 3, 1);
        gridPane.add(phoneA, 1, 9, 2, 1);
        gridPane.add(emailA, 3, 10, 2, 1);
        gridPane.add(registrationNumberA, 2, 11, 3, 1);
        gridPane.add(brandA, 1, 12, 2, 1);

        gridPane.add(lastNameB, 9, 5, 2, 1);
        gridPane.add(firstNameB, 7, 6, 2, 1);    
        gridPane.add(personalNumberB, 10, 6);
        gridPane.add(streetB, 7, 7, 4, 1);
        gridPane.add(zipCodeB, 7, 8, 3, 1);
        gridPane.add(phoneB, 7, 9, 2, 1);
        gridPane.add(emailB, 10, 10, 2, 1);
        gridPane.add(registrationNumberB, 9, 11, 3, 1);
        gridPane.add(brandB, 7, 12, 2, 1);
        gridPane.add(insuranceCompanyB, 9, 14, 2, 1);
        
        //gridPane.add(courseOfEvents, 3, 18, 6, 1);
        gridPane.add(canvasHolder, 3, 18, 6, 1);
        gridPane.add(clearCanvasButton, 9, 18, 2, 1);
        gridPane.add(registerButton, 6, 19, 5, 1);

        scene = new Scene(scrollPane, 1150, 650);
        scene.getStylesheets().add("insurancecompany/resources/css/stylesheetCarClaimForm.css");
        initializeEventHandlers();
    }
    
    public void show(Stage stage) {
        this.stage = stage;
        stage.setTitle("Bilskademeldingsskjema");      
        stage.setScene(scene);
        stage.show();
    }
    
    public void initializeEventHandlers() {
        setclearCanvasButtonEventHandler();
        setRegisterButtonEventHandler();
    }

     
    private void initDraw(GraphicsContext gc){
        double canvasWidth = gc.getCanvas().getWidth();
        double canvasHeight = gc.getCanvas().getHeight();
         
        gc.setFill(Color.LIGHTGRAY);
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(5);
 
        gc.fill();
        gc.strokeRect(
                0,              //x of the upper left corner
                0,              //y of the upper left corner
                canvasWidth,    //width of the rectangle
                canvasHeight);  //height of the rectangle
         
        gc.setFill(Color.RED);
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(2);
         
    }
     /**
     * Sets up date restrictions to the DatePicker in the parameter.
     * @param date 
     */
    private void restrictDatePicker(DatePicker date) {
        date.setValue(LocalDate.now());
        // Sets up a restricton for choosable dates:
        Callback<DatePicker, DateCell> dayCellFactory = dp -> new DateCell()
        {
            @Override
            public void updateItem(LocalDate item, boolean empty)
            {
                super.updateItem(item, empty);
                // Only allow dates that are up to 2 months old, and not newer than current date:
                if(item.isBefore(LocalDate.now().minusMonths(2)) || item.isAfter(LocalDate.now()))
                {   // Sets the background color of the invalid dates to a pink/red color:
                    setStyle("-fx-background-color: #ffc0cb;");
                    // Disables them, so they can not be picked:
                    setDisable(true);
                }
            }
        };
        // Apply these restrictions to our DatePicker
        date.setDayCellFactory(dayCellFactory);
        date.setPromptText("dd/MM/yyyy");
    } // end of method restrictDatePicker
    
    /**
     * Returns a WriteableImage of a snapshot of the complete car claim
     * form.
     * @return 
     */
    public WritableImage getSnapshotOfCarLcaimForm() {
        return stackPane.snapshot(new SnapshotParameters(), null);
    }
    
    /**
     * Returns a WriteableImage of the drawn image in the car claim form.
     * @return 
     */
    public WritableImage getDrawnImage() {
        return canvas.snapshot(null, null);
    }
    
    /**
     * Returns the selected date as a Calendar object.
     * @return the date
     */
    public Calendar getDate() {
        // Get selected value:
        if (date.getValue() != null) {
            LocalDate localDate = date.getValue();
            // Convert to date:
            Date d = DateUtility.localDateToDate(localDate);
            // Conert to Calendar:
            Calendar c = DateUtility.dateToCalendar(d);
            return c;
        } else {
            return null;
        }
    }

    /**
     * @return the location
     */
    public String getLocation() {
        return location.getText();
    }

    /**
     * @return the witnesses
     */
    public String getWitnesses() {
        return witnesses.getText();
    }

    /**
     * @param lastNameA the lastNameA to set
     */
    public void setLastNameA(String lastNameA) {
        this.lastNameA.setText(lastNameA);
    }

    /**
     * @param firstNameA the firstNameA to set
     */
    public void setFirstNameA(String firstNameA) {
        this.firstNameA.setText(firstNameA);
    }

    /**
     * @param personalNumberA the personalNumberA to set
     */
    public void setPersonalNumberA(String personalNumberA) {
        this.personalNumberA.setText(personalNumberA);
    }

    /**
     * @param streetA the streetA to set
     */
    public void setStreetA(String streetA) {
        this.streetA.setText(streetA);
    }

    /**
     * @param zipCodeA the zipCodeA to set
     */
    public void setZipCodeA(String zipCodeA) {
        this.zipCodeA.setText(zipCodeA);
    }

    /**
     * @param phoneA the phoneA to set
     */
    public void setPhoneA(String phoneA) {
        this.phoneA.setText(phoneA);
    }

    /**
     * @param emailA the emailA to set
     */
    public void setEmailA(String emailA) {
        this.emailA.setText(emailA);
    }

    /**
     * @param registrationNumberA the registrationNumberA to set
     */
    public void setRegistrationNumberA(String registrationNumberA) {
        this.registrationNumberA.setText(registrationNumberA);
    }

    /**
     * @param brandA the brandA to set
     */
    public void setBrandA(String brandA) {
        this.brandA.setText(brandA);
    }

    /**
     * @return the lastNameB
     */
    public String getLastNameB() {
        return lastNameB.getText();
    }

    /**
     * @return the firstNameB
     */
    public String getFirstNameB() {
        return firstNameB.getText();
    }

    /**
     * @return the personalNumberB
     */
    public String getPersonalNumberB() {
        return personalNumberB.getText();
    }

    /**
     * @return the streetB
     */
    public String getStreetB() {
        return streetB.getText();
    }

    /**
     * @return the zipCodeB
     */
    public String getZipCodeB() {
        return zipCodeB.getText();
    }

    /**
     * @return the phoneB
     */
    public String getPhoneB() {
        return phoneB.getText();
    }

    /**
     * @return the emailB
     */
    public String getEmailB() {
        return emailB.getText();
    }

    /**
     * @return the registrationNumberB
     */
    public String getRegistrationNumberB() {
        return registrationNumberB.getText();
    }

    /**
     * @return the brandB
     */
    public String getBrandB() {
        return brandB.getText();
    }

    /**
     * @return the insuranceCompanyB
     */
    public String getInsuranceCompanyB() {
        return insuranceCompanyB.getText();
    }


    /**
     * Sets the event handler for the register button of this view.
     */
    public void setRegisterButtonEventHandler() {
        registerButton.setOnAction((event) -> {
            carClaimRegistration.setCarClaimFormView(this);
            carClaimRegistration.setClaimFormMessage("Skademeldingsskjema er fyllt inn.");
            // Hide the stage:
            stage.hide(); 
        });
    }
    
    /**
     * Sets the event handler for the clear canvas button of this view
     * to clear the canvas when pressed.
     */
    public void setclearCanvasButtonEventHandler() {
        clearCanvasButton.setOnAction((event) -> {
            GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
            // Clear everything but the border:
            graphicsContext.clearRect(3, 3, 674, 384);
        });
    }
    

    /**
     * Returns the car claim registration object to this view.
     * @return the carClaimRegistration
     */
    public CarClaimRegistration getCarClaimRegistration() {
        return carClaimRegistration;
    }

    /**
     * @return the lastNameA
     */
    public String getLastNameA() {
        return lastNameA.getText();
    }

    /**
     * @return the firstNameA
     */
    public String getFirstNameA() {
        return firstNameA.getText();
    }

    /**
     * @return the personalNumberA
     */
    public String getPersonalNumberA() {
        return personalNumberA.getText();
    }

    /**
     * @return the streetA
     */
    public String getStreetA() {
        return streetA.getText();
    }

    /**
     * @return the zipCodeA
     */
    public String getZipCodeA() {
        return zipCodeA.getText();
    }

    /**
     * @return the phoneA
     */
    public String getPhoneA() {
        return phoneA.getText();
    }

    /**
     * @return the emailA
     */
    public String getEmailA() {
        return emailA.getText();
    }

    /**
     * @return the registrationNumberA
     */
    public String getRegistrationNumberA() {
        return registrationNumberA.getText();
    }

    /**
     * @return the brandA
     */
    public String getBrandA() {
        return brandA.getText();
    }
}
