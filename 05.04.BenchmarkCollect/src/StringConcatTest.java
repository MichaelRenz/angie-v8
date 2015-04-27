import math.Sine;

import java.util.Arrays;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;

public class StringConcatTest {
    public static void main(String[] args) {
        final int SIZE = 2000;
        int[] ints = new int[SIZE];
        for (int n=0; n<SIZE; n++) ints[n] = n;
        ///////////////////////////////////////////////////////////////////
        /*
            string concatenation via reduce() and "+"-operator of string
            - sequential
            - parallel

            convert int => string
            - in reduce()
            - before reduce()

            string concatenation via collect() and joining()
            - sequential
            - parallel
         */
        BenchmarkTest.addTestCase(() -> Arrays.stream(ints)
                                         .boxed()
                                         .reduce("", (i, s) -> String.valueOf(i) + " " + s, (s1, s2) -> s1 + s2), "'+' with boxed / sequential");
        // parallel: ... to be done ...

        BenchmarkTest.addTestCase(() -> Arrays.stream(ints)
                                         .mapToObj(i -> String.valueOf(i) + " ")
                                         .reduce("", (s1, s2) -> s1 + s2), "'+' with map / sequential");
        // parallel: ... to be done ...

        BenchmarkTest.addTestCase(() -> Arrays.stream(ints)
                                         .mapToObj(i -> String.valueOf(i))
                                         .collect(Collectors.joining(" ")), "joining() / sequential");
        // parallel: ... to be done ...

        ///////////////////////////////////////////////////////////////////

        final int NEW_SIZE = 10*SIZE;
        int[] newInts = new int[NEW_SIZE];
        for (int n=0; n<NEW_SIZE; n++) newInts[n] = n;
        ///////////////////////////////////////////////////////////////////
        /*
            string concatenation via forEach() and append() of StringBuilder/StringBuffer
            - sequential via StringBuilder
            - parallel   via StringBuffer
                         - ordered
                         - unordered

            string concatenation via collect() and joining()
            - sequential
            - parallel
         */
        StringBuilder sBuilder = new StringBuilder();
        BenchmarkTest.addTestCase(() -> Arrays.stream(newInts)
                .mapToObj(i -> String.valueOf(i))
                .forEach(s -> { sBuilder.append(s); sBuilder.append(" "); } ), "StringBuilder / sequential");
        sBuilder.delete(0, sBuilder.length());    // release resources in order to prevent OutOfMemoryError
        sBuilder.trimToSize();

        // parallel unordered: ... to be done ...

        // parallel ordered: ... to be done ...

        // joining sequential: ... to be done ...

        // joining parallel: ... to be done ...

        ///////////////////////////////////////////////////////////////////


        ///////////////////////////////////////////////////////////////////
        /*
            string concatenation via collect() and joining()
            - sequential
            - parallel

            - with cheap mapping
            - with expensive mapping
         */
        BenchmarkTest.addTestCase(() -> Arrays.stream(newInts)
                .mapToObj(i -> String.valueOf(i))
                .collect(Collectors.joining(" ")), "joining() / sequential");

        // parallel: ... to be done ...

        BenchmarkTest.addTestCase(() -> Arrays.stream(ints)
                                         .mapToObj(i -> String.valueOf(Sine.slowSin(i * 0.001)))
                                         .collect(Collectors.joining(" ")), "joining() with sine / sequential");

        // parallel: ... to be done ...


        ///////////////////////////////////////////////////////////////////


        /////////////////////////////////////////////////////////////////////////
        // START BENCHMARK
        BenchmarkTest.doTest();
    }
}
