package Utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Utils {
    public static String getCurrentTime() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("H:mm a");
        return sdf.format(cal.getTime()).toUpperCase();
    }
}
