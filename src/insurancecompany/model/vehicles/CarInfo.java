/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package insurancecompany.model.vehicles;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author André
 */
@XmlRootElement(name="item")
public class CarInfo {
    private String name;
    private ModelInfoRegister models;

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    @XmlAttribute(name="name")
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the models
     */
    public ModelInfoRegister getModels() {
        return models;
    }

    /**
     * @param models the models to set
     */
    @XmlElement(name="models")
    public void setModels(ModelInfoRegister models) {
        this.models = models;
    }
   /* 
    public void add(ModelInfo modelInfo) {
         if( this.models == null )
        {
            this.models = new ArrayList<ModelInfo>();
        }
        this.models.add(modelInfo);
    }
   */ 
}
