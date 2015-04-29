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
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ThreadLocalRandom;

import math.Sine;

public class TestMax {

	public static void main(String[] args) {

		final int SIZE = 100000;
		ThreadLocalRandom rand = ThreadLocalRandom.current();
		/*
		 * test with primitive array
		 */
		int[] ints = new int[SIZE];
		for (int i = 0; i < SIZE; i++)
			ints[i] = rand.nextInt();

		BenchmarkTest.addTestCase(() -> {
			int[] a = ints;
			int m = Integer.MIN_VALUE;

			for (int i : a)
				if (i > m)
					m = i;

		}, "primitive array ohne Map, for-loop");

		BenchmarkTest.addTestCase(
				() -> Arrays.stream(ints)
						.reduce(Integer.MIN_VALUE, (i, j) -> Math.max(i, j)),
				"primitive array ohne Map, sequential");

		BenchmarkTest.addTestCase(
				() -> Arrays.stream(ints).parallel()
						.reduce(Integer.MIN_VALUE, (i, j) -> Math.max(i, j)),
				"primitive array ohne Map, parallel");

		BenchmarkTest.addTestCase(() -> {
			int[] a = ints;
			double m = Double.MIN_VALUE;
			double j;
			for (int i : a) {
				j = Sine.slowSin(i);
				if (j > m)
					m = j;
			}
				

		}, "primitive array MIT Map, for-loop");

		
		BenchmarkTest.addTestCase(
				() -> Arrays.stream(ints).mapToDouble(Sine::slowSin)
						.reduce(Double.MIN_VALUE, (i, j) -> Math.max(i, j)),
				"primitive array MIT Map, sequential");

		BenchmarkTest.addTestCase(
				() -> Arrays.stream(ints).parallel().mapToDouble(Sine::slowSin)
						.reduce(Double.MIN_VALUE, (i, j) -> Math.max(i, j)),
				"primitive array MIT Map, parallel");
		
		/*
		 * test with array of boxed Integers
		 */
		Integer[] integers = new Integer[SIZE];
		for (int i = 0; i < SIZE; i++)
			integers[i] = rand.nextInt();

		BenchmarkTest.addTestCase(() -> {
			Integer[] a = integers;
			Integer m = Integer.MIN_VALUE;

			for (Integer i : a)
				if (i > m)
					m = i;

		}, "Integer array, for-loop");

		BenchmarkTest.addTestCase(
				() -> Arrays.stream(integers).reduce(Integer.MIN_VALUE,
						(i, j) -> Math.max(i, j)), "Integer array, sequential");

		BenchmarkTest.addTestCase(() -> Arrays.stream(integers).parallel()
				.reduce(Integer.MIN_VALUE, (i, j) -> Math.max(i, j)),
				"Integer array, parallel");

		/*
		 * test with ArrayList
		 */
		List<Integer> alist = new ArrayList<>(SIZE);
		for (int i = 0; i < SIZE; i++)
			alist.add(rand.nextInt());

		BenchmarkTest.addTestCase(() -> {
			List<Integer> a = alist;
			Integer m = Integer.MIN_VALUE;

			for (Integer i : a)
				if (i > m)
					m = i;

		}, "List, for-loop");

		BenchmarkTest.addTestCase(
				() -> alist.stream().reduce(Integer.MIN_VALUE,
						(i, j) -> Math.max(i, j)), "List, sequential");

		BenchmarkTest.addTestCase(
				() -> alist.stream().parallel()
						.reduce(Integer.MIN_VALUE, (i, j) -> Math.max(i, j)),
				"List, parallel");

		/*
		 * test with LinkedList
		 */
		List<Integer> llist = new LinkedList<>();
		for (int i = 0; i < SIZE; i++)
			llist.add(rand.nextInt());

		BenchmarkTest.addTestCase(() -> {
			List<Integer> a = llist;
			Integer m = Integer.MIN_VALUE;

			for (Integer i : a)
				if (i > m)
					m = i;

		}, "Linked List, for-loop");

		BenchmarkTest.addTestCase(
				() -> llist.stream().reduce(Integer.MIN_VALUE,
						(i, j) -> Math.max(i, j)), "Linked List, sequential");

		BenchmarkTest.addTestCase(
				() -> llist.stream().parallel()
						.reduce(Integer.MIN_VALUE, (i, j) -> Math.max(i, j)),
				"Linked List, parallel");

		/*
		 * test with HashSet
		 */
		Set<Integer> hset = new HashSet<>(SIZE);
		for (int i = 0; i < SIZE; i++)
			hset.add(rand.nextInt());

		BenchmarkTest.addTestCase(() -> {
			Set<Integer> a = hset;
			Integer m = Integer.MIN_VALUE;

			for (Integer i : a)
				if (i > m)
					m = i;

		}, "Hashset, for-loop");

		BenchmarkTest.addTestCase(
				() -> hset.stream().reduce(Integer.MIN_VALUE,
						(i, j) -> Math.max(i, j)), "Hashset, sequential");

		BenchmarkTest.addTestCase(
				() -> hset.stream().parallel()
						.reduce(Integer.MIN_VALUE, (i, j) -> Math.max(i, j)),
				"Hashset, parallel");
		/*
		 * test with TreeSet
		 */
		Set<Integer> tset = new TreeSet<>();
		for (int i = 0; i < SIZE; i++)
			tset.add(rand.nextInt());

		BenchmarkTest.addTestCase(() -> {
			Set<Integer> a = tset;
			Integer m = Integer.MIN_VALUE;

			for (Integer i : a)
				if (i > m)
					m = i;

		}, "Treeset, for-loop");

		BenchmarkTest.addTestCase(
				() -> tset.stream().reduce(Integer.MIN_VALUE,
						(i, j) -> Math.max(i, j)), "Treeset, sequential");

		BenchmarkTest.addTestCase(
				() -> tset.stream().parallel()
						.reduce(Integer.MIN_VALUE, (i, j) -> Math.max(i, j)),
				"Treeset, parallel");

		// ///////////////////////////////////////////////////////////////////////
		// START BENCHMARK
		BenchmarkTest.doTest();
	}
}
