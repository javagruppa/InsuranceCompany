/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package insurancecompany.misc;

/**
 *
 * @author André
 */
public enum User {
    ADMIN, CASE_WORKER, SERVICE_WORKER, CUSTOMER;
    
    @Override
    public String toString() {
        switch(this) {
            case ADMIN: return "Admin";
            case CASE_WORKER: return "Case worker";
            case SERVICE_WORKER: return "Service worker";
            case CUSTOMER: return "Customer";
            default: throw new IllegalArgumentException();
        }
    }
}
