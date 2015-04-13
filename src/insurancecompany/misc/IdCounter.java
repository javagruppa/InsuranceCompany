/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package insurancecompany.misc;

import java.io.*;


/**
 *
 * @author André
 */
public class IdCounter {
    
    private static int customerId = 1000000;
    private static int employeeId = 1000000;
    
    private static String fileName = "filename.dta";
    
    public static int nextEmployeeId() {
        return employeeId++;
    }
    
    public static int nextCustomerId() {
        return customerId++;
    }
    
    public static void setCustomerId(int id) {
        customerId = id;
    }
    
    public static void writeIdToFile() throws IOException {
        try (DataOutputStream dos = new DataOutputStream(
                new BufferedOutputStream(
                        new FileOutputStream(fileName) ))) {
           dos.writeInt(customerId);
           dos.writeInt(employeeId);
        }
    }
    
    public static void readIdFromFile() throws IOException {
        try (DataInputStream dis = new DataInputStream(
                new BufferedInputStream(
                        new FileInputStream(fileName)))) {
            customerId = dis.readInt();
            employeeId = dis.readInt();
        }
    }
    
}
