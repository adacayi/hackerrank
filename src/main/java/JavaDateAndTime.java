import java.util.Calendar;
import java.util.GregorianCalendar;

public class JavaDateAndTime {
    private String[] days = {"SUNDAY", "MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY", "FRIDAY", "SATURDAY"};

    public String getDay(String day, String month, String year) {
        int d = Integer.parseInt(day);
        int m = Integer.parseInt(month);
        int y = Integer.parseInt(year);

        GregorianCalendar calendar = new GregorianCalendar(y, m - 1, d);
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        return days[dayOfWeek];
    }

    public static String getDate(Calendar calendar) {
        return "" + calendar.get(Calendar.DATE) + "/" + (calendar.get(Calendar.MONTH) + 1) + "/"
                + calendar.get(Calendar.YEAR) + " " + calendar.get(Calendar.HOUR_OF_DAY) + ":"
                + calendar.get(Calendar.MINUTE) + ":" + calendar.get(Calendar.SECOND) + "."
                + calendar.get(Calendar.MILLISECOND);// Months begin with 0, so we added 1.
    }
}
