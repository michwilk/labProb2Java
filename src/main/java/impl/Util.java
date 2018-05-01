package impl;

import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class Util {
    public static final long MILLI_TO_HOUR = 3600 * 1000L;

    public static int hoursDifference(Date date1, Date date2) {
        return (int) ((date1.getTime() - date2.getTime()) / MILLI_TO_HOUR);
    }

    public static Date generateDateBetween(Date start, Date end) {
        long millis = ThreadLocalRandom.current().nextLong(0, end.getTime() - start.getTime());
        long hours = TimeUnit.MILLISECONDS.toHours(millis);
        return new Date(start.getTime() + hours * MILLI_TO_HOUR);
    }
}
