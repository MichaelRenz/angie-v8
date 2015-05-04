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

import java.awt.Point;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Lab_04_07 {
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
    private static double distanceFromOrigin(Point p) {
        return p.distance(0,0);
    }
    /*
    Partition a list of Point objects into the points with positive (>=0) and the points with negative (<0) x-coordinate.
    */
    /*
    Step 1: For each partition find a point with maximum distance from the origin.
    */
    private static void splitAndFindAPointWithMaximumDistance() {
        Map<Boolean, Optional<Point>> result
        = points.stream()
                .collect(Collectors.partitioningBy(p -> p.x >= 0,
                         Collectors.maxBy((p1, p2) -> Double.compare(distanceFromOrigin(p1), distanceFromOrigin(p2)))));
        System.out.println(result);
    }
    /*
    Step 2: For each partition find the maximum y-coordinate.
    */
    private static void splitAndFindMaximumYCoordinate() {
        Map<Boolean, Optional<Integer>> result
                = points.stream().collect(Collectors.partitioningBy(p -> p.x >= 0,
                Collectors.mapping(p -> p.y, Collectors.maxBy(Integer::compareTo))));
        System.out.println(result);
    }


    /*
    Step 3 (optional): For each partition find all points with maximum distance from the origin (similar to step 1).
    */
    private static void splitAndFindAllPointsWithMaximumDistance() {
        points.stream().collect(
                Collectors.collectingAndThen(
                        Collectors.partitioningBy(p -> p.x >= 0),
                        groupingMap -> {
                            groupingMap.keySet().stream().forEach(booleanKey ->
                                groupingMap.get(booleanKey).stream()
                                        .mapToDouble(Lab_04_07::distanceFromOrigin)
                                        .max()
                                        .ifPresent(d->groupingMap.get(booleanKey).stream()
                                                    .filter(p->distanceFromOrigin(p)==d)
                                                    .forEach(System.out::println)
                                        )
                            );
                            return (Void)null;
                        }
                )
        );
    }


    public static void main(String... args) {
        splitAndFindAPointWithMaximumDistance();
        splitAndFindMaximumYCoordinate();
        splitAndFindAllPointsWithMaximumDistance();

    }
}
