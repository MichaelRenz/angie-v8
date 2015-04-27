package zonedtime;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * Say you have a conference call at 10:00 in New York, but happen to be in Berlin.
 * Implement a utility that alerts you at the correct local time.
 *
 * Specifically:
 * Read a list of appointments in different time zones and alert the user which ones are due in local time
 * within the next hour.
 */
public class Appointments {
    private static List<WeeklyAppointment> appointments = Arrays.asList(
            new WeeklyAppointment(DayOfWeek.WEDNESDAY,LocalTime.of(9,0),ZoneId.of("Europe/Berlin"),"Project Dino, Dev-Team"),
            new WeeklyAppointment(DayOfWeek.WEDNESDAY,LocalTime.of(9,0),ZoneId.of("Europe/Lisbon"),"Project Dino, Test-Team"),
            new WeeklyAppointment(DayOfWeek.WEDNESDAY,LocalTime.of(9,0),ZoneId.of("Europe/Athens"),"Project Dino, Test-Team"),
            new WeeklyAppointment(DayOfWeek.TUESDAY,LocalTime.of(8,30),ZoneId.of("US/Eastern"),"Project Bali, Design-Team"),
            new WeeklyAppointment(DayOfWeek.FRIDAY,LocalTime.of(15,0),ZoneId.of("US/Pacific"), "Project Bali, Dev-Team, Santa Clara"),
            new WeeklyAppointment(DayOfWeek.FRIDAY,LocalTime.of(15,0),ZoneId.of("Europe/Stockholm"), "Project Bali, Dev-Team, Malmö"),
            new WeeklyAppointment(DayOfWeek.WEDNESDAY,LocalTime.of(11,45),ZoneId.of("Europe/Simferopol"),"Project Bali, Test-Team"),
            new WeeklyAppointment(DayOfWeek.TUESDAY,LocalTime.of(9,0),ZoneId.of("Europe/Berlin"),"Budget, Frankfurt"),
            new WeeklyAppointment(DayOfWeek.MONDAY,LocalTime.of(15,0),ZoneId.of("Asia/Dubai"),"Al Ghurair Group, Finance"),
            new WeeklyAppointment(DayOfWeek.TUESDAY,LocalTime.of(15,0),ZoneId.of("Europe/Moscow"),"Dev-Meeting, JetBrains, St. Petersburg"),
            new WeeklyAppointment(DayOfWeek.WEDNESDAY,LocalTime.of(7,15),ZoneId.of("Asia/Singapore"),"ASA, Supply"),
            new WeeklyAppointment(DayOfWeek.THURSDAY,LocalTime.of(10,0),ZoneId.of("US/Eastern"),"HR, DC"),
            new WeeklyAppointment(DayOfWeek.THURSDAY,LocalTime.of(10,0),ZoneId.of("US/Central"),"Board-Meeting, Chicago"),
            new WeeklyAppointment(DayOfWeek.WEDNESDAY,LocalTime.of(10,0),ZoneId.of("Australia/Brisbane"), "Sales"),
            new WeeklyAppointment(DayOfWeek.MONDAY,LocalTime.of(10,30),ZoneId.of("GB-Eire"), "Sales"),
            new WeeklyAppointment(DayOfWeek.WEDNESDAY,LocalTime.of(14,0),ZoneId.of("US/Mountain"), "Sales"),
            new WeeklyAppointment(DayOfWeek.WEDNESDAY,LocalTime.of(10,0),ZoneId.of("Asia/Tokyo"), "Sales")
    );
    private static void alert(Duration duration) {
        System.err.println("appointments due within the next "+duration.toHours()+" hours:");
        ... to be done ...
    }
    public static void main(String... args) {
        alert(Duration.of(8,ChronoUnit.HOURS));
    }
}
