package skeleton.easter;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;

/**
 * Calculate the date of Ash Wednesday for the next 10 years (starting with this year's Ash Wednesday).
 * Ash Wednesday is 46 days before Easter Sunday.  The date of Easter Sunday can be calculated
 * via the Gaussian Easter Algorithm.
 */
public class EasterCalculation {
    /*
        The Gaussian Easter Algorithm returns the date of Easter Sunday as number of days in March,
        i.e. the result "31" denotes "March 31" and the result "32" denotes "April 1".
     */
    private static int gaussianEasterAlgorithm(int year) {
        int secularNumber = year/100;
        int secularMoonShift   = 15+(3*secularNumber+3)/4 - (8*secularNumber+13)/25;
        int secularSunShift = 2 - (3*secularNumber+3)/4;
        int moonParameter = year % 19;
        int seedFor1stFullMoonInSpring = (19*moonParameter+secularMoonShift) % 30;
        int calendarianCorrectionQuantity = (seedFor1stFullMoonInSpring+moonParameter/11)/29;
        int easterLimit = 21+seedFor1stFullMoonInSpring-calendarianCorrectionQuantity;
        int firstSundayInMarch = 7-(year+year/4+secularSunShift)%7;
        int distanceOfEasterSundayFromEasterLimitInDays = 7-(easterLimit-firstSundayInMarch)%7;
        int easterSundayAsNumberOfDaysInMarch = easterLimit+distanceOfEasterSundayFromEasterLimitInDays;
        return easterSundayAsNumberOfDaysInMarch;
    }

    private static LocalDate getAshWednesday(int year) {   // Ash Wednesday is 46 days before Easter Sunday.
        ... to be done ...
    }
    public static void main(String... args) {
        // to check your results see http://www.maa.mhn.de/StarDate/publ_holidays.html
        System.out.println("Ash Wednesday the next 10 year:");
        int startYear = LocalDate.now().getYear();
        for (int year=startYear; year<startYear+10;year++) {
            System.out.println(getAshWednesday(year));
        }
    }
}
