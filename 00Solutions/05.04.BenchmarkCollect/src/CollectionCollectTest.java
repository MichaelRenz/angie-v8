import math.Sine;
import sun.reflect.generics.tree.Tree;

import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class CollectionCollectTest {
    public static void main(String[] args) {
        final int SIZE = 50000;
        int[] ints = new int[SIZE];
        for (int n=0; n<SIZE; n++) ints[n] = n;

         /*
            Use various types of collections as a sink:
            •	ArrayList
            •	LinkedList
            •	HashSet
            •	TreeSet
            •	Map
            •	ConcurrentMap
            •	more if you like
         */

        BenchmarkTest.addTestCase(() -> Arrays.stream(ints)
                                         .mapToObj(String::valueOf)
                                         .collect(Collectors.toCollection(ArrayList<String>::new)), "collect to ArrayList / sequential");

        BenchmarkTest.addTestCase(() -> Arrays.stream(ints).parallel()
                                         .mapToObj(String::valueOf)
                                         .collect(Collectors.toCollection(ArrayList<String>::new)), "collect to ArrayList / parallel");

        BenchmarkTest.addTestCase(() -> Arrays.stream(ints)
                                         .mapToObj(String::valueOf)
                                         .collect(Collectors.toCollection(LinkedList<String>::new)), "collect to LinkedList / sequential");

        BenchmarkTest.addTestCase(() -> Arrays.stream(ints).parallel()
                                         .mapToObj(String::valueOf)
                                         .collect(Collectors.toCollection(LinkedList<String>::new)), "collect to LinkedList / parallel");

        BenchmarkTest.addTestCase(() -> Arrays.stream(ints)
                                         .mapToObj(String::valueOf)
                                         .collect(Collectors.toCollection(HashSet<String>::new)), "collect to HashSet / sequential");

        BenchmarkTest.addTestCase(() -> Arrays.stream(ints).parallel()
                                         .mapToObj(String::valueOf)
                                         .collect(Collectors.toCollection(HashSet<String>::new)), "collect to HashSet / parallel");

        BenchmarkTest.addTestCase(() -> Arrays.stream(ints)
                                         .mapToObj(String::valueOf)
                                         .collect(Collectors.toCollection(TreeSet<String>::new)), "collect to TreeSet / sequential");

        BenchmarkTest.addTestCase(() -> Arrays.stream(ints).parallel()
                                         .mapToObj(String::valueOf)
                                         .collect(Collectors.toCollection(TreeSet<String>::new)), "collect to TreeSet / parallel");

        BenchmarkTest.addTestCase(() -> Arrays.stream(ints)
                                         .boxed()
                                         .collect(Collectors.toMap(i -> i, String::valueOf)), "collect to Map / sequential");

        BenchmarkTest.addTestCase(() -> Arrays.stream(ints).parallel()
                                         .boxed()
                                         .collect(Collectors.toMap(i -> i, String::valueOf)), "collect to Map / parallel");

        BenchmarkTest.addTestCase(() -> Arrays.stream(ints)
                                         .boxed()
                                         .collect(Collectors.toConcurrentMap(i -> i, String::valueOf)), "collect to ConcurrentMap / sequential");

        BenchmarkTest.addTestCase(() -> Arrays.stream(ints).parallel()
                                         .boxed()
                                         .collect(Collectors.toConcurrentMap(i -> i, String::valueOf)), "collect to ConcurrentMap / parallel");

        final int NEW_SIZE = SIZE/10;
        int[] newInts = new int[NEW_SIZE];

        for (int n=0; n<NEW_SIZE; n++) newInts[n] = n;

        BenchmarkTest.addTestCase(() -> Arrays.stream(newInts)
                                         .mapToObj(i -> String.valueOf(Sine.slowSin(i * 0.0001)))
                                         .collect(Collectors.toCollection(ArrayList<String>::new)), "with sine collect to ArrayList / sequential");

        BenchmarkTest.addTestCase(() -> Arrays.stream(newInts).parallel()
                                         .mapToObj(i -> String.valueOf(Sine.slowSin(i * 0.0001)))
                                         .collect(Collectors.toCollection(ArrayList<String>::new)), "with sine collect to ArrayList / parallel");

        BenchmarkTest.doTest();
    }
}
