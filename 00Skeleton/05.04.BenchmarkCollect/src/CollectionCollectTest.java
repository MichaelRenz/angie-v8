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
        // parallel: ... to be done ...

        // all the other types of collections:  ... to be done ...

        BenchmarkTest.addTestCase(() -> Arrays.stream(ints)
                                         .boxed()
                                         .collect(Collectors.toMap(i -> i, String::valueOf)), "collect to Map / sequential");

        // parallel: ... to be done ...

        // concurrent map:  ... to be done ...

        /////////////////////////////////////////////////////////////////////////
        // START BENCHMARK
        BenchmarkTest.doTest();
    }
}
