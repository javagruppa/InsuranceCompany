/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package insurancecompany.misc;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author André
 */
public class DateUtility {

    /** The norwegian standard for showing dates */
    public static final java.util.Locale NORWAY = new java.util.Locale("no");
    /** The date format to be shown in toString */
    public static final SimpleDateFormat NORWEGIAN_DATE_FORMAT = new SimpleDateFormat("dd.MM.yyyy", NORWAY );
    
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