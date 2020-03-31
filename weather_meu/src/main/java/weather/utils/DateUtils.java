package weather.utils;

import java.time.LocalDate;

public class DateUtils {
    public static int numDays(LocalDate from, LocalDate to) {
        return from.until(to).getDays()+1;
    }
}
