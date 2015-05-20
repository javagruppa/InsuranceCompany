package insurancecompany.misc;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.util.Callback;

/**
 * The DateUtility contains tools used to calculate between Calendar objects,
 * restrict DatePickers, set DateFormat and convert between various date type
 * Objects.
 * 
 * @author André
 * @author Carl
 * 
 * @since 17.05.2015
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
    /**
     * Sets up date restrictions to a DatePicker so that only dates at or older
     * than the current date are pickable.
     * 
     * @param datePicker 
     */
    public static void restrictDatePickerToOlder(DatePicker datePicker) {
        // Sets up a restricton for choosable dates:
        Callback<DatePicker, DateCell> dayCellFactory = dp -> new DateCell()
        {
            @Override
            public void updateItem(LocalDate item, boolean empty)
            {
                super.updateItem(item, empty);
                // Only allow dates that are older than current dates:
                if(item.isAfter(LocalDate.now()))
                {   // Sets the background color of the invalid dates to a pink/red color:
                    setStyle("-fx-background-color: #ffc0cb;");
                    // Disables them, so they can not be picked:
                    setDisable(true);
                }
            }
        };
        // Apply these restrictions to our DatePicker
        datePicker.setDayCellFactory(dayCellFactory);
        // Set prompt text:
        datePicker.setPromptText("dd/MM/yyyy");
    } // end of method restrictDatePickerToOlder
    
    public static int getMonthNumber(String monthName) {
        switch (monthName) {
            case "Januar": return 1;
            case "Februar": return 2;
            case "Mars": return 3;
            case "April": return 4;
            case "Mai": return 5;
            case "Juni": return 6;
            case "Juli": return 7;
            case "August": return 8;
            case "September": return 9;
            case "Oktober": return 10;
            case "November": return 11;
            case "Desember": return 12;
            default: return 0;
        }
    }
}
