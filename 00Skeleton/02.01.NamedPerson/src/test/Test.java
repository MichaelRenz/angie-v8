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
package test;

import name.Person;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;

public class Test {
    public static void main(String... args) {
        Person p = new Person("John","Fitzgerald","Kennedy",29,Month.MAY,1917);
        System.out.println(p);

        // shall yield output like this: Today (Feb 11, 2014) John F. Kennedy would be 96 years old.
        System.out.println(
                "Today ("+LocalDate.now().format(DateTimeFormatter.ofPattern("MMM dd, uuuu"))
                +") "+p.getName()+" would be "+p.getAge()+" years old.");
    }
}
