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

public class Lab_03_02 {
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
    In a list of Point objects sum up the x-coordinates of all points and print the resulting sum.
    For tracing purposes, print all x-coordinates before printing the sum.
    */
    /*
    Step 1: Use the reduce() operation with an initial value.
    */
    private static void printSumOfCoordinates_1() {
        int result = points.stream()
                .mapToInt(p -> p.x)
                .peek(x -> System.out.print(x + " "))
                .reduce(0, (x1, x2) -> x1 + x2);
        System.out.println("sum is: " + result);
    }
    /*
    Step 2: Use the reduce() operation without an initial value.
    */
    private static void printSumOfCoordinates_2a() {
        points.stream()
                .mapToInt(p -> p.x)
                .peek(x -> System.out.print(x + " "))
                .reduce((x1,x2) -> x1+x2)
                .ifPresent(s -> System.out.println("sum is: " + s));
    }
    private static void printSumOfCoordinates_2b() {
        int result = points.stream()
                .mapToInt(p -> p.x)
                .peek(x -> System.out.print(x + " "))
                .sum();
        System.out.println("sum is: " + result);
    }
    /*
    Step 3: Try to omit the mapping to the x-coordinate and reduce the points directly.
     */
    private static void printSumOfCoordinates_3() {
        int result = points.stream()
                .peek(p -> System.out.print(p.x + " "))
                .reduce(0, (x, p2) -> x + p2.x, Integer::sum);
        System.out.println("sum is: " + result);
    }

    public static void main(String... args) {
        printSumOfCoordinates_1();
        printSumOfCoordinates_2a();
        printSumOfCoordinates_2b();
        printSumOfCoordinates_3();
    }
}
