package insurancecompany.misc;

/**
 * This enum represents the types of employees that can be registered.
 * 
 * @author André
 */
public enum EmployeeType {
    ADMIN, CASE_WORKER, SERVICE_WORKER;
    
    /**
     * Returns a string representation of the type of employee
     * @return a string representation of the type of employee
     */
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