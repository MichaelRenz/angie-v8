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

import java.time.*;

public class Person implements Name, Age {
    private String firstName;
    private String middleName;
    private String lastName;
    private LocalDate dateOfBirth;

    public Person(String first, String middle, String last, int day, Month month, int year) {
        firstName  = first;
        middleName = middle;
        lastName   = last;
        dateOfBirth= LocalDate.of(year,month,day);
    }
    public String getFirstName() {
        return firstName;
    }
    public String getMiddleName() {
        return middleName;
    }
    public String getLastName() {
        return lastName;
    }
    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }
    public String toString() {
        return String.format("%-12s= %s\n%-12s= %s\n%-12s= %s\n%-12s= %s"
                ,"firstName"  ,firstName
                ,"middleName" ,middleName
                ,"lastName"   ,lastName
                ,"dateOfBirth",dateOfBirth
        );
    }
}
