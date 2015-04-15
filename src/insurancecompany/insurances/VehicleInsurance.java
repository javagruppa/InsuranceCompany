/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package insurancecompany.insurances;

import insurancecompany.people.VehicleOwner;

/**
 *
 * @author Sindre
 */
public class VehicleInsurance extends Insurance {
    private VehicleOwner owner;
    private String registrationNumber;
    private String type;
    private String model;
    
    public VehicleInsurance(VehicleOwner owner, String registrationNumber,
            String type, String model) {
        super();
        this.owner = owner;
        this.registrationNumber = registrationNumber;
        this.type = type;
        this.model = model;
    }
}