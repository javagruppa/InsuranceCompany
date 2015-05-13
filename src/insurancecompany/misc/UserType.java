package insurancecompany.misc;

/**
 * class UserType. Enums showing what kind of user a person object is
 * @author André
 */
public enum UserType {
    CUSTOMER, ADMIN, CASE_WORKER, SERVICE_WORKER;
    
    /**
     * Returns a string representation of the user type of a person
     * @return a string representation of the user type of a person
     */
    @Override
    public String toString() {
        switch(this) {
            case CUSTOMER: return "Kunde";
            case ADMIN: return "Administrator";
            case CASE_WORKER: return "Saksbehandler";
            case SERVICE_WORKER: return "Kundebehandler";
            default: throw new IllegalArgumentException();
        }
    }
}