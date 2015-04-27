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

public class Lab_01_01 {
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
    Use the forEach-Method from interface Iterable<T> and a lambda expression to print all points
    in a collection of points.  Print them in one line and insert two blanks after each point.

    The output should look like this:
    java.awt.Point[x=1,y=1]  java.awt.Point[x=2,y=2]  java.awt.Point[x=3,y=3]  ...
     */
    private static void printPoints() {
        ... to be done ...
    }
    /*
     Print all points with the property: point.x % modVal == residue.
     For instance, filterPoints(2,0) would print all points with even x-coordinate.
     */
    private static void filterPoints(int modVal, int residue) {
        ... to be done ...
    }
    public static void main(String... args) {
        printPoints();
        filterPoints(2,0);
    }
}

