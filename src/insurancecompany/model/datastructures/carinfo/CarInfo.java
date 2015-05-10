/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package insurancecompany.model.datastructures.carinfo;

import java.util.List;
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
    private ModelInfoRegister modelRegister;

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
     * @return the modelRegister
     */
    public ModelInfoRegister getModelRegister() {
        return modelRegister;
    }
    
    /*
    public List<ModelInfo> getModels1() {
        if (modelRegister != null) {
            return modelRegister.getModels();
        } else {
            return null;
        }
    }
    */
    
    /**
     * @param models the modelRegister to set
     */
    @XmlElement(name="models")
    public void setModels(ModelInfoRegister models) {
        this.modelRegister = models;
    }
   /* 
    public void add(ModelInfo modelInfo) {
         if( this.modelRegister == null )
        {
            this.modelRegister = new ArrayList<ModelInfo>();
        }
        this.modelRegister.add(modelInfo);
    }
   */ 
    
    @Override
    public String toString() {
        return name;
    }
}
