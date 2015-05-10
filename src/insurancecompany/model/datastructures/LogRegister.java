/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package insurancecompany.model.datastructures;

import insurancecompany.misc.logs.Log;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author André
 */
public class LogRegister {
    
    /** List of logs from Exceptions etc. */
    private List<Log> logs;
    
    public LogRegister() {
        logs = new ArrayList<Log>();
    }
    
    public void add(Log log) {
        logs.add(log);
    }
    
    public void add(String stackTrace) {
        Log log = new Log(stackTrace);
        add(log);
    }
    
    public void add(String stackTrace, String customDescription) {
        Log log = new Log(stackTrace, customDescription);
        add(log);
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Logs:");
        for (Log log : logs) {
            sb.append(log.toString() + "\n");
        }
        return sb.toString();
    }
}
