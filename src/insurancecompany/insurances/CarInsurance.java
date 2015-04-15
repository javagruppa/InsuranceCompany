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
public class CarInsurance extends VehicleInsurance {
    private int mileage;
    private int pricePerKilometer;
    private int bonus;
    
    public CarInsurance(int insurancePremium, int insuranceAmount,
            String insuranceConditions, VehicleOwner owner,
            String registrationNumber, String type, String model, int mileage,
            int pricePerKilometer, int bonus) {
        super(insurancePremium, insuranceAmount, insuranceConditions,
                owner, registrationNumber, type, model);
        this.mileage = mileage;
        this.pricePerKilometer = pricePerKilometer;
        this.bonus = bonus;
    }
}