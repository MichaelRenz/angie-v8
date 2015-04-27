/*
  Based on course material for "Lambdas & Streams", a seminar prepared
  and owned by Angelika Langer & Klaus Kreft.
  contact: http://www.AngelikaLanger.com/ or mailto: info@AngelikaLanger.com

  © Copyright 2013-2014 by Angelika Langer & Klaus Kreft. All rights reserved.

  Permission to use, copy, and modify this software for any non-profit
  purpose is hereby granted to attendants of the above mentioned seminar
  without fee, provided that the above copyright notice appears in all
  copies.  Angelika Langer and Klaus Kreft make no representations about
  the suitability of this software for any purpose.  It is provided
  "as is" without express or implied warranty.
*/
package name;

import java.time.LocalDate;
import java.time.MonthDay;
import java.time.temporal.ChronoUnit;

/*
 * Here is the result that the various methods shall yield:
    getDateOfBirth() :	1954-07-17
    getBirthday() :		July 17
    getAge() :		    current age

   Hints:
    The combination of a year and month can be obtained via the from() method of class java.time.MonthDay.
    The years between two points in time can be obtained via the between() method of java.time.temporal.ChronoUnit.YEARS.
 */
public interface Age {
    LocalDate getDateOfBirth();
    long getAge();
    MonthDay getBirthday();
}
