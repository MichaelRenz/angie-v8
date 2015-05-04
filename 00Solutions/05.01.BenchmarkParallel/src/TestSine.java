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
import math.Sine;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class TestSine {

    public static void main(String[] args) {

        final int SIZE = 10000;
        ThreadLocalRandom rand = ThreadLocalRandom.current();
        ///////////////////////////////////////////////////////////////////////////////////////////
        /*
            test with primitive array
         */
        int[] ints = new int[SIZE];
        for (int i=0; i<SIZE; i++) ints[i] = rand.nextInt();

        BenchmarkTest.addTestCase(() -> {
            int[] a = ints;
            int e = a.length;
            double m = Double.MIN_VALUE;

            for (int i = 0; i < e; i++) {
                double d = Sine.slowSin(a[i]);
                if (d > m) m = d;
            }
        }, "primitive array, for-loop");

        BenchmarkTest.addTestCase(() -> Arrays.stream(ints)
                                         .mapToDouble(Sine::slowSin)
                                         .reduce(Double.MIN_VALUE, (i, j) -> Math.max(i, j)),
                             "primitive array, sequential");

        BenchmarkTest.addTestCase(() -> Arrays.stream(ints).parallel()
                                         .mapToDouble(Sine::slowSin)
                                         .reduce(Double.MIN_VALUE,  (i, j) -> Math.max(i, j)),
                             "primitive array, parallel");
        ///////////////////////////////////////////////////////////////////////////////////////////
        /*
            test with array of boxed Integers
         */
        Integer[] integers = new Integer[SIZE];
        for (int i=0; i<SIZE; i++) integers[i] = rand.nextInt();

        BenchmarkTest.addTestCase(() -> {
            Integer[] a = integers;
            int e = a.length;
            double m = Double.MIN_VALUE;

            for (int i = 0; i < e; i++) {
                double d = Sine.slowSin(a[i]);
                if (d > m) m = d;
            }
        }, "boxed array, for-loop");

        BenchmarkTest.addTestCase(() -> Arrays.stream(integers)
                .mapToDouble(Sine::slowSin)
                .reduce(Double.MIN_VALUE, (i, j) -> Math.max(i, j)),
                "boxed array, sequential");

        BenchmarkTest.addTestCase(() -> Arrays.stream(integers).parallel()
                .mapToDouble(Sine::slowSin)
                .reduce(Double.MIN_VALUE, (i, j) -> Math.max(i, j)),
                "boxed array, parallel");
        ///////////////////////////////////////////////////////////////////////////////////////////
        /*
            test with ArrayList
         */
        List<Integer> alist = new ArrayList<>(SIZE);
        for (int i=0; i<SIZE; i++) alist.add(rand.nextInt());

        BenchmarkTest.addTestCase(() -> {
            Collection<Integer> a = alist;
            double m = Double.MIN_VALUE;

            for (int i : a) {
                double d = Sine.slowSin(i);
                if (d > m) m = d;
            }
        }, "array list, for-loop");

        BenchmarkTest.addTestCase(() -> alist.stream()
                                        .mapToDouble(Sine::slowSin)
                                        .reduce(Double.MIN_VALUE, (i, j) -> Math.max(i, j)),
                             "array list, sequential");

        BenchmarkTest.addTestCase(() -> alist.parallelStream()
                                        .mapToDouble(Sine::slowSin)
                                        .reduce(Double.MIN_VALUE, (i, j) -> Math.max(i, j)),
                             "array list, parallel");
        ///////////////////////////////////////////////////////////////////////////////////////////
        /*
            test with LinkedList
         */
        List<Integer> llist = new LinkedList<>();
        for (int i=0; i<SIZE; i++) llist.add(rand.nextInt());

        BenchmarkTest.addTestCase(() -> {
            Collection<Integer> a = llist;
            double m = Double.MIN_VALUE;

            for (int i : a) {
                double d = Sine.slowSin(i);
                if (d > m) m = d;
            }
        }, "linked list, for-loop");

        BenchmarkTest.addTestCase(() -> llist.stream()
                                        .mapToDouble(Sine::slowSin)
                                        .reduce(Double.MIN_VALUE, (i, j) -> Math.max(i, j)),
                "linked list, sequential");

        BenchmarkTest.addTestCase(() -> llist.parallelStream()
                                        .mapToDouble(Sine::slowSin)
                                        .reduce(Double.MIN_VALUE, (i, j) -> Math.max(i, j)),
                "linked list, parallel");
        ///////////////////////////////////////////////////////////////////////////////////////////
        /*
            test with HashSet
         */
        Set<Integer> hset = new HashSet<>(SIZE);
        for (int i=0; i<SIZE; i++) hset.add(rand.nextInt());

        BenchmarkTest.addTestCase(() -> {
            Collection<Integer> a = hset;
            double m = Double.MIN_VALUE;

            for (int i : a) {
                double d = Sine.slowSin(i);
                if (d > m) m = d;
            }
        }, "hash set, for-loop");

        BenchmarkTest.addTestCase(() -> hset.stream()
                                       .mapToDouble(Sine::slowSin)
                                       .reduce(Double.MIN_VALUE, (i, j) -> Math.max(i, j)),
                             "hash set, sequential");

        BenchmarkTest.addTestCase(() -> hset.parallelStream()
                                       .mapToDouble(Sine::slowSin)
                                       .reduce(Double.MIN_VALUE, (i, j) -> Math.max(i, j)),
                             "hash set, parallel");
        ///////////////////////////////////////////////////////////////////////////////////////////
        /*
            test with TreeSet
         */
        Set<Integer> tset = new TreeSet<>();
        for (int i=0; i<SIZE; i++) tset.add(rand.nextInt());

        BenchmarkTest.addTestCase(() -> {
            Collection<Integer> a = tset;
            double m = Double.MIN_VALUE;

            for (int i : a) {
                double d = Sine.slowSin(i);
                if (d > m) m = d;
            }
        }, "tree set, for-loop");

        BenchmarkTest.addTestCase(() -> tset.stream()
                                       .mapToDouble(Sine::slowSin)
                                       .reduce(Double.MIN_VALUE, (i, j) -> Math.max(i, j)),
                             "tree set, sequential");

        BenchmarkTest.addTestCase(() -> tset.parallelStream()
                                       .mapToDouble(Sine::slowSin)
                                       .reduce(Double.MIN_VALUE, (i, j) -> Math.max(i, j)),
                              "tree set, parallel");
        ///////////////////////////////////////////////////////////////////////////////////////////

        /*
            test with CopyOnWriteArrayList
         */
        // nicht für die Übung; nur zum Spaß
        /*
        List<Integer> aconclist = new CopyOnWriteArrayList<>(integers);
        for (int i=0; i<SIZE; i++) aconclist.add(rand.nextInt());

        BenchmarkTest.addTestCase(() -> {
            Collection<Integer> a = aconclist;
            double m = Double.MIN_VALUE;

            for (int i : a) {
                double d = Sine.slowSin(i);
                if (d > m) m = d;
            }
        }, "copy-on-write array list, for-loop");

        BenchmarkTest.addTestCase(() -> aconclist.stream()
                .mapToDouble(Sine::slowSin)
                .reduce(Double.MIN_VALUE, (i, j) -> Math.max(i, j)),
                "copy-on-write array list, sequential");

        BenchmarkTest.addTestCase(() -> aconclist.parallelStream()
                .mapToDouble(Sine::slowSin)
                .reduce(Double.MIN_VALUE, (i, j) -> Math.max(i, j)),
                "copy-on-write array list, parallel");
        */
        ///////////////////////////////////////////////////////////////////////////////////////////

        BenchmarkTest.doTest();
    }
}
