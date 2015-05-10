/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package insurancecompany.model.datastructures.carinfo;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author André
 */

@XmlRootElement(name="model")
public class ModelInfo {
    
    private String name;
    private int to;   
    private int since;

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
     * @return the to
     */
    public int getTo() {
        return to;
    }

    /**
     * @param to the to to set
     */
    @XmlAttribute(name="to")
    public void setTo(int to) {
        this.to = to;
    }

    /**
     * @return the since
     */
    @XmlAttribute(name="since")
    public int getSince() {
        return since;
    }

    /**
     * @param since the since to set
     */
    public void setSince(int since) {
        this.since = since;
    }
}
