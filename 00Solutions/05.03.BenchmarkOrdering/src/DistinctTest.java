import math.Sine;

import java.util.Arrays;

public class DistinctTest {

    public static void main(String[] args) {
        final int SIZE = 100000;
        final int DIVIDER = 2;

        int[] ints = new int[SIZE];
        for (int i=0; i<SIZE; i++) ints[i] = i%(SIZE/DIVIDER);

        long cnt = Arrays.stream(ints)
                           .distinct()
                           .count();

        System.out.println(cnt + " elements (out of " + SIZE+ ") are distinct");
        /*
            distinct, no mapping
            - sequential
            - parallel ordered
            - parallel unordered
         */
        BenchmarkTest.addTestCase(() -> Arrays.stream(ints)
                                         .distinct()
                                         .count(), "sequential - int");

        BenchmarkTest.addTestCase(() -> Arrays.stream(ints).parallel()
                                         .distinct()
                                         .count(), "parallel ordered - int");

        BenchmarkTest.addTestCase(() -> Arrays.stream(ints).parallel().unordered()
                                         .distinct()
                                         .count(), "parallel unordered - int");
        /*
            distinct, "cheap" mapping
            - sequential
            - parallel ordered
            - parallel unordered
         */
        BenchmarkTest.addTestCase(() -> Arrays.stream(ints)
                                         .mapToObj(String::valueOf)
                                         .distinct()
                                         .count(), "sequential - String");

        BenchmarkTest.addTestCase(() -> Arrays.stream(ints).parallel()
                                         .mapToObj(String::valueOf)
                                         .distinct()
                                         .count(), "parallel ordered - String");

        BenchmarkTest.addTestCase(() -> Arrays.stream(ints).parallel().unordered()
                                         .mapToObj(String::valueOf)
                                         .distinct()
                                         .count(), "parallel unordered - String");

        final int REDUCED_SIZE = SIZE/10;
        int[] rints = new int[REDUCED_SIZE];
        for (int i=0; i<REDUCED_SIZE; i++) rints[i] = i%(REDUCED_SIZE/DIVIDER);

        final double offset = 0.0001;

        cnt =  Arrays.stream(rints)
                     .map(i -> new Double(REDUCED_SIZE / DIVIDER * Sine.slowSin(i * offset)).intValue())
                     .distinct()
                     .count();

        System.out.println(cnt + " elements (out of " + REDUCED_SIZE+ ") are distinct");
        /*
            distinct, "expensive" mapping
            - sequential
            - parallel ordered
            - parallel unordered
         */
        BenchmarkTest.addTestCase(() -> Arrays.stream(rints)
                                         .map(i -> new Double(SIZE/DIVIDER*Sine.slowSin(i*offset)).intValue())
                                         .distinct()
                                         .count(), "sequential - sine");

        BenchmarkTest.addTestCase(() -> Arrays.stream(rints).parallel()
                                         .map(i -> new Double(SIZE / DIVIDER * Sine.slowSin(i * offset)).intValue())
                                         .distinct()
                                         .count(), "parallel ordered - sine");

        BenchmarkTest.addTestCase(() -> Arrays.stream(rints).parallel().unordered()
                                         .map(i -> new Double(SIZE / DIVIDER * Sine.slowSin(i * offset)).intValue())
                                         .distinct()
                                         .count(), "parallel unordered - sine");

        BenchmarkTest.doTest();
    }
}
