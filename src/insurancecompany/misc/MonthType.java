package insurancecompany.misc;

/**
 * This enum represents the different months in a year
 * 
 * @author André
 */
public enum MonthType {
    JANUARY, 
    FEBRUARY, 
    MARCH, 
    APRIL, 
    MAY, 
    JUNE, 
    JULY, 
    AUGUST, 
    SEPTEMBER, 
    OCTOBER, 
    NOVEMBER, 
    DECEMBER;
    
    /**
     * Returns a string representation of the user type of a person
     * @return a string representation of the user type of a person
     */
    @Override
    public String toString() {
        switch(this) {
            case JANUARY: return "Januar";
            case FEBRUARY: return "Februar";
            case MARCH: return "Mars";
            case APRIL: return "April";
            case MAY: return "Mai";
            case JUNE: return "Juni";
            case JULY: return "Juli";
            case AUGUST: return "August";
            case SEPTEMBER: return "September";
            case OCTOBER: return "Oktober";
            case NOVEMBER: return "November";
            case DECEMBER: return "Desember";
            default: throw new IllegalArgumentException();
        }
    }
}