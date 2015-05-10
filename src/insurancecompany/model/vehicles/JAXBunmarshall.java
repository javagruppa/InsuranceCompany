/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package insurancecompany.model.vehicles;

import java.io.File;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

/**
 *
 * @author André
 */
public class JAXBunmarshall {
    public static void main(String[] args) {
        try {
		File file = new File("src/insurancecompany/resources/xml/Car_makes_and_models.xml");
                JAXBContext jaxbContext = JAXBContext.newInstance(CarInfoRegister.class);
		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
                
		CarInfoRegister carInfoRegister = (CarInfoRegister) jaxbUnmarshaller.unmarshal(file);
                
                String name = carInfoRegister.getCars().get(1).getName();
                int to = carInfoRegister.getCars().get(0).getModels().getModels().get(2).getTo();
		System.out.println(to);
 
	  } catch (JAXBException e) {
		e.printStackTrace();
	  }
    }
}
