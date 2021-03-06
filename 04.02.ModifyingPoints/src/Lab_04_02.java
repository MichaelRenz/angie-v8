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

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Lab_04_02 {
    private static java.util.List<Point> points = generatePoints();

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
    Step 1: In a list of Point objects set the y-coordinate to 0 for all points where the x-coordinate is positive.
            Then print the modified points.
     */
    private static void modifyPoints() {
    	points.stream().parallel().filter(p -> p.getX() > 0.0).forEach(p -> {p.y = 0; System.out.println(p);});
     	points = generatePoints();
    }
    /*
    Step 2: Mutation of sequence elements is not always a good idea.
            Can you produce the same output (as in step 1) without mutating the points?
            Idea: Generate a stream that contains the respective modified points and print them.
            Verify that the originals are unchanged by printing them, too.
            Don't forget to reset the list of points to the original values; after all we modified them in step 1.
     */
    private static void produceNewPoints() {
    	Stream<Point> modifiedPointStream = (Stream<Point>) points.stream().parallel().filter(p -> p.getX() > 0.0).map(p -> new Point(p.x, 0));
    	modifiedPointStream.forEach(mf -> System.out.print(mf + ", "));
    	System.out.println("\n-------------------------------------------------------------------");
    	points.forEach(p -> System.out.print(p + ", "));
    	//Stream<Points> sP = st
        //... to be done ...
    }
    /*
    Step 3: Generate new points (from the original points) with 10-times larger coordinate, i.e., calculate as follows:
            Point np = new Point(10 * p.x, 10 * p.y);
            Add these new points to the original list of points or
            produce a stream that contains the original points followed by the newly generated points.
     */
    private static void addNewPoints() {
//    	points.stream().parallel().filter(p -> p.getX() > 0.0).map(p -> new Point(p.x*10, p.y*10)).forEach(p -> points.add(p));
    	Stream<Point> newPointStream = (Stream<Point>) points.stream().parallel().map(p -> new Point(p.x*10, p.y*10));
    	List<Point> lp = newPointStream.collect(Collectors.toCollection(ArrayList::new));
    	points.addAll(lp);
    	points.forEach(p -> System.out.println
    			(p));
    	//... to be done ...
    }

    public static void main(String... args) {
        modifyPoints();
        produceNewPoints();
        addNewPoints();
    }
}
