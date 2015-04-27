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
import java.util.List;

public class Lab_01_02 {
    private static final List<Point> points = generatePoints();

    private static List<Point> generatePoints() {
        List<Point> result = new ArrayList<>();
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
    This exercise is similar to the previous ones:
    use the forEach method from interface Iterable<T> to print all points in a collection of points.
    This time, use a method reference instead of a lambda expression.
    Use a reference to an existing method from the JDK libraries.
     */
    private static void printPoints_1() {
        ... to be done ...
    }
    /*
    Define a helper method that formats the output, e.g. insert two blanks after each point.
    Print all points using a reference to the helper method.
     */
    private static void printPoints_2() {
        .. to be done ...
    }
    public static void main(String... args) {
        printPoints_1();
        printPoints_2();
    }
}
