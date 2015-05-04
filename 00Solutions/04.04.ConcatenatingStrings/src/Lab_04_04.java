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

import java.awt.*;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class Lab_04_04 {
    private static final java.util.List<Point> points = generatePoints();

    private static java.util.List<Point> generatePoints() {
        java.util.List<Point> result = new ArrayList<>();
        result.add(new Point(1,1));
        result.add(new Point(2,2));
        result.add(new Point(3,3));
        result.add(new Point(1,-1));
        result.add(new Point(2,-2));
        result.add(new Point(3,-3));
        result.add(new Point(-1,-1));
        result.add(new Point(-2,-2));
        result.add(new Point(-3,-3));
        result.add(new Point(-1,1));
        result.add(new Point(-2,2));
        result.add(new Point(-3,3));
        return result;
    }
    /*
    Step 1: From a list of Point objects build a String object that represents all points.  Print the string.
            The output should look like this:
                 java.awt.Point[x=1,y=1]  java.awt.Point[x=2,y=2]  java.awt.Point[x=3,y=3]  ...
            In this first step use the collect() operation and the joining() collector from class Collectors.
            There are two versions available:
            •	without delimiter
            •	with delimiter
            Try out both
    */
    private static void createString_FromPointList_UsingJoiningCollector_1() {
        String result = points.stream()
                .map(p -> p + "  ")
                .collect(Collectors.joining());
        System.out.println(result.toString());
    }
    private static void createString_FromPointList_UsingJoiningCollector_2() {
        String result = points.stream()
                .map(Point::toString)
                .collect(Collectors.joining("  "));
        System.out.println(result.toString());
    }

    /*
    Step 2: Now, as an alternative, use the reduce() operation and string concatenation
            via the +-operator of class String.
            Note, how equally concise this approach looks.  However, consider the performance
            of both solutions.  Which one do you expect to run faster?
     */
    private static void createString_FromPointList_UsingString() {
        String result = points.stream()
                .map(p -> p + "  ")
                .reduce("",(s1, s2) -> s1 + s2);
        System.out.println(result);
    }

    public static void main(String... args) {
        createString_FromPointList_UsingJoiningCollector_1();
        createString_FromPointList_UsingJoiningCollector_2();
        createString_FromPointList_UsingString();
    }
}
