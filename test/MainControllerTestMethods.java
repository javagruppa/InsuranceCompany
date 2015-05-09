/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Sindre
 */
public class MainControllerTestMethods {
    private static final String customerFileName = "src/customerRegister.dta";
    
    private void loginBtnEventHandler(ActionEvent e) {
        
        String userName = loginView.getUserTextField().getText();
        int employeeId = Integer.parseInt(userName);
        Employee employee = employees.getEmployeeById(employeeId);
        if (employee instanceof Admin) {
            System.out.println("Admin login");
        } else  if (employee instanceof CaseWorker) {
            System.out.println("case worker login");
        } else if (employee instanceof ServiceWorker) {
            System.out.println("Service work login");
        }
    }
    
    public void readCustomersFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream(customerFileName))) {
            customers = (CustomerRegister) ois.readObject();
        } catch (ClassNotFoundException cnfe) {
            
        } catch (FileNotFoundException fnfe) {
            
        } catch (IOException ioe) {
            
        }
    }
    
    public void writeCustomersToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(customerFileName))) {
            oos.writeObject(customers);
        } catch ( NotSerializableException nse ) {
            
        } catch (IOException ioe) {
            
        }
    }
}