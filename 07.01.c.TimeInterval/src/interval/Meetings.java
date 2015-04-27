package interval;

import java.time.LocalDateTime;
import java.util.Arrays;

/**
 * Implement a TimeInterval class that represents an interval of time, suitable for
 * calendar events (such as a meeting on a given date from 10:00 to 11:00).
 * Provide a method to check whether two intervals overlap.
 */
public final class Meetings {

    static TimeInterval[] meetings = new TimeInterval[]{
            new TimeInterval(LocalDateTime.of(2014, 7, 15, 10, 35), LocalDateTime.of(2014, 7, 15, 11, 00)),
            new TimeInterval(LocalDateTime.of(2014, 5, 25, 9, 30), LocalDateTime.of(2014, 5, 25, 11, 00)),
            new TimeInterval(LocalDateTime.of(2014, 8, 1, 0, 00), LocalDateTime.of(2014, 8, 22, 0, 00)),
            new TimeInterval(LocalDateTime.of(2014, 5, 25, 10, 30), LocalDateTime.of(2014, 5, 25, 11, 00)),
            new TimeInterval(LocalDateTime.of(2014, 8, 1, 8, 30), LocalDateTime.of(2014, 8, 1, 9, 30)),
            new TimeInterval(LocalDateTime.of(2014, 5, 25, 14, 30), LocalDateTime.of(2014, 5, 25, 15, 00)),
            new TimeInterval(LocalDateTime.of(2014, 7, 15, 9, 15), LocalDateTime.of(2014, 7, 15, 11, 00)),
            new TimeInterval(LocalDateTime.of(2014, 5, 25, 11, 00), LocalDateTime.of(2014, 5, 25, 12, 00))
    };
    private static void checkForOverlapIn(TimeInterval[] remaining) {
        ... to be done ...
    }
    public static void main(String... args) {
        checkForOverlapIn(meetings);
    }
}
