package Utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Utils {
    public static String getCurrentTime() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        return sdf.format(cal.getTime()).toUpperCase();
    }

    public static void sleep(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            System.out.println("Failed to sleep thread");
        }
    }
}
