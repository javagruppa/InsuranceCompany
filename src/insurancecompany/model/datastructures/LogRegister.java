/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
 *
 * @author André
 */
public class LogRegister {
    
    private static String logsFilePath = "src/insurancecompany/resources/logs/";
    
    /** List of logs from Exceptions etc. */
    private List<Log> logs;
    
    public LogRegister() {
        logs = new ArrayList<>();
    }
    
    public void add(Log log) {
        logs.add(log);
    }
    
    public void add(String message) {
        Log log = new Log(message);
        add(log);
    }
    
    public void add(String message, Person user) {
        Log log = new Log(message, user);
        add(log);
    }
    
    public void add(StackTraceElement[] stackTrace) {
        Log log = new Log(stackTrace);
        add(log);
    }
    
    public void add(StackTraceElement[] stackTrace, Person user) {
        Log log = new Log(stackTrace, user);
        add(log);
    }
    
    public void add(StackTraceElement[] stackTrace, String message) {
        Log log = new Log(stackTrace, message);
        add(log);
    }
    
    public void add(StackTraceElement[] stackTrace, String message, Person user) {
        Log log = new Log(stackTrace, message, user);
        add(log);
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Logs:");
        logs.stream().forEach((log) -> {
            sb.append(log.toString()).append("\n");
        });
        return sb.toString();
    }
    
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
