package zonedtime;

import java.time.*;
import java.time.temporal.TemporalAdjusters;

public final class WeeklyAppointment {
    private final DayOfWeek dayOfWeek;
    private final LocalTime localTime;
    private final ZoneId zoneId;
    private final String description;

    public WeeklyAppointment(DayOfWeek weekday, LocalTime time, ZoneId timezone, String what) {
        dayOfWeek = weekday;
        localTime = time;
        zoneId = timezone;
        description = what;
    }
    public String toString() {
        return "Appointment [" + dayOfWeek + ", " + localTime + ", " + zoneId + ", \"" + description + "\"]";
    }
}
