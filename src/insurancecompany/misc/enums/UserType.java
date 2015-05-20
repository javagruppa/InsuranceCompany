package insurancecompany.misc.enums;

/**
 * This enum represents the types of users that can log in to the program.
 * 
 * @author André
 * @since 19.05.2015
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