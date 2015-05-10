/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package insurancecompany.misc.logs;

import insurancecompany.misc.User;
import java.util.Calendar;

/**
 *
 * @author André
 */
public class Log {
    private Calendar date;
    private String stackTrace;
    private String customDescription;
    private User user;
    
    public Log(String stackTrace, User user) {
        date = Calendar.getInstance();
        this.stackTrace = stackTrace;
    }
    
    public Log(String stackTrace, String customDescription, User user) {
        date = Calendar.getInstance();
        this.stackTrace = stackTrace;
        this.customDescription = customDescription;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Time: " + date.getTime() + "\n");
        sb.append("Logged in user: " + user.toString() + "\n");
        sb.append("Stack Trace: "+ stackTrace + "\n");
        if (customDescription != null) {
            sb.append("Description: " + customDescription);
        }        
        return sb.toString();
    }
    
    // TODO: read and write from textfile
}
