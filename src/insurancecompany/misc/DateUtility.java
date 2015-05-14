package insurancecompany.misc;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

/**
 * class DateUtility contains tools used to calculate and write dates with
 * specific formats
 * 
 * @author André
 */
public class DateUtility {

    /** The Norwegian standard for showing dates. */
    public static final java.util.Locale NORWAY_LOCALE = new java.util.Locale("no");
    /** Date format following the Norwegian date format. */
    public static final SimpleDateFormat NORWEGIAN_DATE_FORMAT = new SimpleDateFormat("dd.MM.yyyy", NORWAY_LOCALE );
    
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
     * Returns a Calendar object from a Date object.
     * @param date the date to be converted
     * @return the calendar object with the specified date
     */
    public static Calendar dateToCalendar(Date date){ 
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal;
    }
    
    /**
     * Returns a Date object from a LocalDate object.
     * @param localDate the LocalDate to be converted
     * @return
     */
    public static Date localDateToDate(LocalDate localDate) {
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }
    
    /**
     * Returns a Calendar object from a LocalDate object.
     * @param localDate
     * @return 
     */
    public static Calendar LocalDateToCalendar(LocalDate localDate) {
        Date date = localDateToDate(localDate);
        return dateToCalendar(date);
    }

}
