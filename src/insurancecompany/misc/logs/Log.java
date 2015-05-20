
package insurancecompany.misc.logs;

import insurancecompany.model.people.*;
import java.util.Calendar;

/**
 *
 * @author André
 * 
 * @since 19.05.2015
 */
public class Log {
    private Calendar date;
    private StackTraceElement[] stackTrace;
    private String message;
    private String userType;
    private int userId;
    
    public Log(String message) {
        date = Calendar.getInstance();
        this.message = message;
    }
    
    public Log(String message, Person user) {
        date = Calendar.getInstance();
        this.message = message;
        populateUserInfo(user);
    }
    
    public Log(StackTraceElement[] stackTrace) {
        date = Calendar.getInstance();
        this.stackTrace = stackTrace;
    }
    
    public Log(StackTraceElement[] stackTrace, Person user) {
        date = Calendar.getInstance();
        this.stackTrace = stackTrace;
        populateUserInfo(user);
    }
    
    public Log(StackTraceElement[] stackTrace, String message) {
        date = Calendar.getInstance();
        this.stackTrace = stackTrace;
        this.message = message;
    }
    
    public Log(StackTraceElement[] stackTrace, String message, Person user) {
        date = Calendar.getInstance();
        this.stackTrace = stackTrace;
        this.message = message;
        populateUserInfo(user);
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Time: ").append(date.getTime());
        sb.append("\n");
        if (userType != null) {
            sb.append("Logged in user: ").append(userType);
            sb.append("\n");
        }
        if (userId != 0) {
            sb.append("User id: ").append(userId);
            sb.append("\n");
        }
        if (stackTrace != null) {
            sb.append("Stack Trace: ").append(stackTraceToString(stackTrace));
            sb.append("\n");
        }
        if (message != null) {
            sb.append("Description: ").append(message);
        }        
        return sb.toString();
    }
    
    private String stackTraceToString(StackTraceElement[] stack) {
        StringBuilder sb = new StringBuilder();
        for (StackTraceElement element : stack) {
            sb.append(element.toString());
            sb.append("\n");
        }
        return sb.toString();
    }
    
    private void populateUserInfo(Person user) {
        userId = user.getId();
        if (user instanceof ServiceWorker) {
            userType = "Service worker";
        } else if (user instanceof CaseWorker) {
            userType = "Case worker";
        } else if (user instanceof Admin) {
            userType = "Admin";
        } else if (user instanceof Customer) {
            userType = "Customer";
        } else {
            userType = "Unknown";
        }
    }
}
