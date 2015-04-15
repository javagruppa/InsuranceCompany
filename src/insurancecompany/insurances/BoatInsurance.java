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
public class BoatInsurance extends VehicleInsurance {
    private int length;
    private int modelYear;
    private String engineType;
    private int horsepower;
    
    public BoatInsurance(VehicleOwner owner, String registrationNumber,
            String type, String model, int length, int modelYear,
            String engineType, int horsepower) {
        super(owner, registrationNumber, type, model);
        this.length = length;
        this.modelYear = modelYear;
        this.engineType = engineType;
        this.horsepower = horsepower;
    }
}