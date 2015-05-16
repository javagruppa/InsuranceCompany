/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package insurancecompany.view.statistics;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

/**
 *
 * @author André
 */
public class StatisticsDisbursements {
    // Decalaration of the Gridpane and Scene.
    private GridPane mainPane;
    
    public StatisticsDisbursements() {
        mainPane = new GridPane();
    }
    
    /**
     * Returns the main Pane of this view.
     * @return 
     */
    public Pane getMainPane() {
        return mainPane;
    }
}
