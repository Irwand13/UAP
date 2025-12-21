package CekelDuit.utill;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtil {

    private static final DateTimeFormatter INPUT =
            DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    private static final DateTimeFormatter OUTPUT =
            DateTimeFormatter.ofPattern("dd MMM yyyy • HH:mm");

    public static String format(String dateStr) {
        if (dateStr == null || dateStr.isEmpty()) return "-";

        try {
            LocalDateTime dt = LocalDateTime.parse(dateStr, INPUT);
            return dt.format(OUTPUT);
        } catch (Exception e) {
            return "-";
        }
    }
}
