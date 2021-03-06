
package insurancecompany.model.datastructures;

import insurancecompany.misc.logs.Log;
import insurancecompany.model.people.Person;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * This class creates the register for logs. It consists of a List with
 * all the logs registered since last login as well as several methods 
 * to manipulate and get information from the register.
 * 
 * @author André
 * 
 * @since 19.05.2015
 */
public class LogRegister {
    
    private static String logsFilePath = "src/insurancecompany/resources/logs/";
    
    /** List of logs from Exceptions etc. */
    private List<Log> logs;
    
    /** New arraylist of logs */
    public LogRegister() {
        logs = new ArrayList<>();
    }
    
    /** Adds a log
     * @param log the log to add
     */
    public void add(Log log) {
        logs.add(log);
    }
    
    /** Adds a log from string message
     * @param message the message to add
     */
    public void add(String message) {
        Log log = new Log(message);
        add(log);
    }
    
    /** Adds a log from sting message and user
     * @param message the message to add
     * @param user the user to add
     */
    public void add(String message, Person user) {
        Log log = new Log(message, user);
        add(log);
    }
    
    /** Adds a lot from stackTrace
     * @param stackTrace the stacktrace to add
     */
    public void add(StackTraceElement[] stackTrace) {
        Log log = new Log(stackTrace);
        add(log);
    }
    
    /**Adds a log from StackTrace and user
     * @param stackTrace the stacktrace to add
     * @param user the user to add
     */ 
    public void add(StackTraceElement[] stackTrace, Person user) {
        Log log = new Log(stackTrace, user);
        add(log);
    }
    
    /** Adds a log from stackTrace and string message
     * @param stackTrace the stacktrace to add
     * @param message the message to add
     */
    public void add(StackTraceElement[] stackTrace, String message) {
        Log log = new Log(stackTrace, message);
        add(log);
    }
    
    /** Adds a log from stackTrace, string message and user
     * @param stackTrace the stacktrace to add
     * @param message the message to add
     * @param user the user to add
     */
    public void add(StackTraceElement[] stackTrace, String message, Person user) {
        Log log = new Log(stackTrace, message, user);
        add(log);
    }
    
    /**
     * Returns a string representation for this set of logs.
     * @return a string representation for this set of logs.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Logs:");
        logs.stream().forEach((log) -> {
            sb.append(log.toString()).append("\n");
        });
        return sb.toString();
    }
    
    /**
     * Writes data to file
     * @throws IOException the exception to be thrown
     */
    public void writeToFile() throws IOException {
        // Will only write to file if this log register is not empty:
        if (!logs.isEmpty()) {
            // Create a String based on current time
            String timeLog = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
            // Create a file with the filepath decided in the private constant combined with the timelog and ending with .txt 
            File logFile = new File(logsFilePath + timeLog + ".txt");
            try (BufferedWriter writer = new BufferedWriter(
                    new FileWriter(logFile))){
                // Write this log registers toString to file:
                writer.write(toString());
            }
        }
    }

    /**
     * @return the logs
     */
    public List<Log> getLogs() {
        return logs;
    }
}
