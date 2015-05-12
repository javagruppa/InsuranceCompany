/*
 * Here comes the text of your license
 * Each line should be prefixed with  * 
 */
package insurancecompany.misc;

/**
 *
 * @author André
 */
public enum EmployeeType {
    ADMIN, CASE_WORKER, SERVICE_WORKER;
    
    @Override
    public String toString() {
        switch(this) {
            case ADMIN: return "Administrator";
            case CASE_WORKER: return "Saksbehandler";
            case SERVICE_WORKER: return "Kundebehandler";
            default: throw new IllegalArgumentException();
        }
    }
}