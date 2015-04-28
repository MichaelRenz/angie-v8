/*
  Based on course material for "Lambdas & Streams", a seminar prepared
  and owned by Angelika Langer & Klaus Kreft.
  contact: http://www.AngelikaLanger.com/ or mailto: info@AngelikaLanger.com

  Â© Copyright 2013-2014 by Angelika Langer & Klaus Kreft. All rights reserved.

  Permission to use, copy, and modify this software for any non-profit
  purpose is hereby granted to attendants of the above mentioned seminar
  without fee, provided that the above copyright notice appears in all
  copies.  Angelika Langer and Klaus Kreft make no representations about
  the suitability of this software for any purpose.  It is provided
  "as is" without express or implied warranty.
*/

import java.awt.*;
import java.util.ArrayList;
import java.util.OptionalDouble;
import java.util.OptionalInt;

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
    	double sum = points.stream().mapToDouble(p -> p.getX())
    			//.peek(x -> System.out.println("Peekaboo: " + x))
    			.reduce(0, (p1,p2) -> (p1+p2));
    	System.out.println(sum);
    	//... to be done ...
    }
    /*
    Step 2: Use the reduce() operation without an initial value.
    */
    private static void printSumOfCoordinates_2() {
    	OptionalDouble sum = points.stream().mapToDouble(p -> p.getX()).reduce((p1,p2) -> (p1+p2));
    	System.out.println(sum);
    	
    	//... to be done ...
    }
    /*
    Step 3: Try to omit the mapping to the x-coordinate and reduce the points directly.
     */
    private static void printSumOfCoordinates_3() {
    	int sum = points.stream().reduce(0, (x, p) -> x + p.x, (x, p) -> (x+p)); //Integer::sum);
    	System.out.println(sum);
    	//... to be done ...
    	
    }

    public static void main(String... args) {
        printSumOfCoordinates_1();
        printSumOfCoordinates_2();
        printSumOfCoordinates_3();
    }
}
