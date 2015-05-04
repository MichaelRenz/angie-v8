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
import java.util.*;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Lab_03_01 {
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
    Step 1: In a list of Point objects find all points with positive x-coordinate and print these points.
     */
    private static void printAllPositivePoints() {
        points.stream()
                .filter(p -> p.getX() > 0)
                .forEach(p -> System.out.print(p + "  "));
        System.out.println();
    }
    /*
    Step 2: In a list of Point objects find all points with positive x-coordinate, and print these coordinates.
     */
    private static void printAllPositiveCoordinates_1() {
        points.stream()
                .filter(p -> p.x > 0)
                .forEach(p -> System.out.print(p.x + "  "));
        System.out.println();
    }
    private static void printAllPositiveCoordinates_2() {
        points.stream()
                .filter(p -> p.getX() > 0)
                .map(Point::getX)
                .forEach(x -> System.out.print(x.intValue() + "  "));
        System.out.println();
    }
    private static void printAllPositiveCoordinates_3() {
        points.stream()
                .mapToInt(p->p.x)
                .filter(x -> x > 0)
                .forEach(x -> System.out.print(x + "  "));
        System.out.println();
    }
    /*
    Step 3: In a list of Point objects find all points with distinct positive x-coordinate, and print these coordinates.
     */
    private static void printDistinctPositiveCoordinates() {
        points.stream()
                .mapToInt(p -> p.x)
                .filter(x -> x > 0)
                .distinct()
                .forEach(x -> System.out.print(x + "  "));
        System.out.println();
    }
    /*
    Step 4 (optional): In a list of Point objects find all points with distinct positive x-coordinate, and print these points.
     */
    private static void printDistinctPositivePoints_1() {
        points.stream()
                .filter(p -> p.x > 0)
                .map(p -> new Point(p) {
                    public boolean equals(Object other) {
                        if (this == other) return true;
                        if (other == null) return false;
                        if (other.getClass() != this.getClass()) return false;
                        return this.x == ((Point)other).x;
                    }
                    public int hashCode() {
                        return this.x;
                    }
                })
                .distinct()
                .forEach(p -> System.out.print(p + "  "));
        System.out.println();
    }

    // Note: This solution is not order preserving,
    // i.e., points are printed in an order different from the order they had in the stream source.
    private static void printDistinctPositivePoints_2() {
        points.stream()
                .filter(p -> p.x > 0)
                .collect(Collectors.toCollection(() ->
                        new TreeSet<Point>((p1, p2) -> p1.x - p2.x)))
                .forEach(p -> System.out.print(p + "  "));
        System.out.println();
    }

    private static void printDistinctPositivePoints_3() {
        points.stream()
                .filter(p -> p.x > 0)
                .collect(Collectors.toMap(p->p.x, p->p, (pOld,pNew)->pOld, LinkedHashMap::new))
                .values()
                .forEach(p -> System.out.print(p + "  "));
        System.out.println();
    }

    private static void printDistinctPositivePoints_4() {
        final Map<Integer, Boolean> map = new ConcurrentHashMap<>();
        points.stream()
                .filter(p -> p.x > 0)
                .filter(p -> map.putIfAbsent(p.x, Boolean.TRUE) == null)
                .forEach(p -> System.out.print(p + "  "));
        System.out.println();
    }

    private static void printDistinctPositivePoints_5() {
        points.stream()
                .filter(p -> p.x > 0)
                .collect(Collectors.groupingBy(Point::getX))
                .values().forEach(l -> System.out.print(l.get(0) + "  "));
        System.out.println();
    }

    private static void printDistinctPositivePoints_6() {
        points.stream()
              .filter(p -> p.x > 0)
              .collect(Collectors.groupingBy(Point::getX,
                                             Collectors.collectingAndThen(Collectors.reducing((p1, p2) -> p1),
                                                                          op -> {
                                                                                   op.ifPresent(p -> System.out.print(p + "  "));
                                                                                   return (Void) null;
                                                                                }
                                                                         )
                                            )
                     );
        System.out.println();
    }

    private static void printDistinctPositivePoints_7() {
        points.stream()
              .filter(p -> p.x > 0)
              .collect(Collectors.collectingAndThen(Collectors.groupingBy(Point::getX),
                                                    map -> {
                                                            map.values().forEach(l -> System.out.print(l.get(0) + "  "));
                                                            return (Void) null;
                                                           }
                                                    )
                      );
        System.out.println();
    }
    public static void main(String... args) {
        printAllPositivePoints();
        printAllPositiveCoordinates_1();
        printAllPositiveCoordinates_2();
        printDistinctPositiveCoordinates();
        printDistinctPositivePoints_1();
        printDistinctPositivePoints_2();
        printDistinctPositivePoints_3();
        printDistinctPositivePoints_4();
        printDistinctPositivePoints_5();
        printDistinctPositivePoints_6();
        printDistinctPositivePoints_7();
    }
}
