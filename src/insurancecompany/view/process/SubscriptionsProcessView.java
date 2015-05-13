/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package insurancecompany.view.process;

import insurancecompany.view.statistics.*;
import insurancecompany.view.search.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

/**
 *
 * @author André
 */
public class SubscriptionsProcessView {
    // Decalaration of the Gridpane and Scene.
    private BorderPane mainPane;
    
    public SubscriptionsProcessView() {
        mainPane = new BorderPane();
    }
    
    /**
     * Returns the main Pane of this view.
     * @return 
     */
    public Pane getMainPane() {
        return mainPane;
    }
}
