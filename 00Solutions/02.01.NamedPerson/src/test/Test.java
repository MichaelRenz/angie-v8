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
    private static void test(Person p) {
        System.out.println(p);

        // shall yield output like this: Today (Feb 11, 2014) John F. Kennedy would be 96 years old.
        System.out.println(
                "Today ("+LocalDate.now().format(DateTimeFormatter.ofPattern("MMM dd, uuuu"))
                        +") "+p.getName()+" would be "+p.getAge()+" years old."
        );
    }
    public static void main(String... args) {
        Person[] people = new Person[] {
                new Person("Indira","Priyadarshini","Gandhi",31,Month.OCTOBER,1917),
                new Person("John","Fitzgerald","Kennedy",29,Month.MAY,1917),
                new Person("Angela","Dorothea","Merkel",17,Month.JULY,1954),
                new Person("Silvio",null,"Berlusconi",29,Month.SEPTEMBER,1936),
                new Person("Margaret","Hilda","Thatcher",13,Month.OCTOBER,1925),
                new Person("Willy",null,"Brandt",18,Month.DECEMBER,1913)
        };
        for (Person p : people)
            test(p);
    }
}
