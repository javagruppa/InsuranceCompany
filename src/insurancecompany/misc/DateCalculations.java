/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package insurancecompany.misc;

import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author André
 */
public class DateCalculations {
    
    
    public static int getDifferenceInYears(Calendar first, Calendar last) {
        int diff = last.get(Calendar.YEAR) - first.get(Calendar.YEAR);
        if (first.get(Calendar.MONTH) > last.get(Calendar.MONTH) ||
                (first.get(Calendar.MONTH) == last.get(Calendar.MONTH) && 
                        first.get(Calendar.DATE) > last.get(Calendar.DATE))) {
            diff--;
        }
        return diff;
    }
    
    public static Calendar dateToCalendar(Date date){ 
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal;
    }
}
