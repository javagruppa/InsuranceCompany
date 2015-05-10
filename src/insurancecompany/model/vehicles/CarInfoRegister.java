/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package insurancecompany.model.vehicles;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author André
 */
@XmlRootElement(name="items")
public class CarInfoRegister {
    
    private List<CarInfo> cars;

    /**
     * @return the cars
     */
    public List<CarInfo> getCars() {
        return cars;
    }

    
    @XmlElement(name="item")
    public void setCars(List<CarInfo> cars) {
        this.cars = cars;
    }
    
    public void add(CarInfo carInfo) {
         if( this.cars == null )
        {
            this.cars = new ArrayList<CarInfo>();
        }
        this.cars.add(carInfo);
    }
    
    @Override
    public String toString()
    {
        StringBuffer str = new StringBuffer();
        for( CarInfo carInfo : this.cars )
        {
            str.append( carInfo.toString() );
        }
        return str.toString();
    }
}
