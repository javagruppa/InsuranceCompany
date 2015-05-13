package insurancecompany.misc;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * class DateUtility contains tools used to calculate and write dates with
 * specific formats
 * 
 * @author André
 */
public class DateUtility {

    /** The norwegian standard for showing dates */
    public static final java.util.Locale NORWAY = new java.util.Locale("no");
    /** The date format to be shown in toString */
    public static final SimpleDateFormat NORWEGIAN_DATE_FORMAT = new SimpleDateFormat("dd.MM.yyyy", NORWAY );
    
    /**
     * Calculates the difference in whole years between 2 dates.
     * @param first the first date
     * @param last the last date
     * @return the difference between first and last, in whole years
     */
    public static int getDifferenceInYears(Calendar first, Calendar last) {
        int diff = last.get(Calendar.YEAR) - first.get(Calendar.YEAR);
        if (first.get(Calendar.MONTH) > last.get(Calendar.MONTH) ||
                (first.get(Calendar.MONTH) == last.get(Calendar.MONTH) && 
                        first.get(Calendar.DATE) > last.get(Calendar.DATE))) {
            diff--;
        }
        return diff;
    }
    
    /**
     * Sets a Calendar object to a certain date
     * @param date the date to be set
     * @return the calendar object with the specified date
     */
    public static Calendar dateToCalendar(Date date){ 
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal;
    }

}
