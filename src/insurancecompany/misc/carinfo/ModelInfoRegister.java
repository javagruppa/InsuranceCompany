/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package insurancecompany.misc.carinfo;

import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This class acts as a receiver for unmarshalling our Car_makes_and_models.xml
 * This class contains a list of ModelInfo objects.
 * 
 * @author André
 * @since 19.05.2015
 */
@XmlRootElement(name="models")
public class ModelInfoRegister {
    
    private List<ModelInfo> models;

    /**
     * @return the cars
     */
    public List<ModelInfo> getModels() {
        return models;
    }

    
    @XmlElement(name="model")
    public void setModels(List<ModelInfo> models) {
        this.models = models;
    }
}
    