/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package insurancecompany.datastructures;
import insurancecompany.vehicles.*;
import java.util.ArrayList;

/**
 *
 * @author André
 */
public class VehicleRegister {
    /** List of all vehicles that are registered to customers. */
    private ArrayList<Vehicle> vehicles;
    
    public VehicleRegister() {
        vehicles = new ArrayList<Vehicle>();
    }
    
    /**
     * Adds a new vehicle to this register if it does not already exist.
     * @param vehicle
     * @return 
     */
    public boolean addVehicle(Vehicle vehicle) {
        if (vehicles.contains(vehicle)) {
            return false;
        } else {
            vehicles.add(vehicle);
            return true;
        }
    }
    
    /**
     * Finds and returns a list of vehicles matching the person with a given personal number.
     * @param personalNumber
     * @return 
     */
    public ArrayList<Vehicle> findVehiclesByPersonalNumber(int personalNumber) {
        ArrayList<Vehicle> v = new ArrayList<Vehicle>();
        for (Vehicle vehicle : vehicles) {
            if (vehicle.getPersonalNumber() == personalNumber) {
                v.add(vehicle);
            }
        }
        // Returns null if no matches are found:
        if (v.isEmpty()) {
            return null;
        } else {
            // Returns the newly created list otherwise:
            return v;
        }
    }
    
    
}