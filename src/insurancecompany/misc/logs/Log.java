/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package insurancecompany.misc.logs;

import insurancecompany.misc.User;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Calendar;

/**
 *
 * @author André
 */
public class Log {
    private Calendar date;
    private StackTraceElement[] stackTrace;
    private String customDescription;
    private User user;
    
    public Log(String customDescription) {
        date = Calendar.getInstance();
        this.customDescription = customDescription;
    }
    
    public Log(StackTraceElement[] stackTrace) {
        date = Calendar.getInstance();
        this.stackTrace = stackTrace;
    }
    
    public Log(StackTraceElement[] stackTrace, User user) {
        date = Calendar.getInstance();
        this.stackTrace = stackTrace;
        this.user = user;
    }
    
    public Log(StackTraceElement[] stackTrace, String customDescription) {
        date = Calendar.getInstance();
        this.stackTrace = stackTrace;
        this.customDescription = customDescription;
    }
    
    public Log(StackTraceElement[] stackTrace, String customDescription, User user) {
        date = Calendar.getInstance();
        this.stackTrace = stackTrace;
        this.customDescription = customDescription;
        this.user = user;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Time: " + date.getTime());
        sb.append("\n");
        if (user != null) {
            sb.append("Logged in user: " + user.toString());
            sb.append("\n");
        }
        if (stackTrace != null) {
            sb.append("Stack Trace: "+ stackTraceToString(stackTrace));
            sb.append("\n");
        }
        if (customDescription != null) {
            sb.append("Description: " + customDescription);
        }        
        return sb.toString();
    }
    
    public String stackTraceToString(StackTraceElement[] stack) {
        StringBuilder sb = new StringBuilder();
        for (StackTraceElement element : stack) {
            sb.append(element.toString());
            sb.append("\n");
        }
        return sb.toString();
    }
    
    // TODO: read and write from textfile
}
