import math.Sine;
import transformer.AddTransformer;
import transformer.SineTransformer;
import transformer.Transformer;

import java.util.Arrays;

public class ForEachTest {

    public static void main(String[] args) {
        final int SIZE = 10000;
        final double offset = 0.0001;

        Transformer[] sts = new Transformer[SIZE];
        /*
            "cheap" transformation
            - sequential
            - parallel unordered
            - parallel ordered
         */
        for (int i=0; i<SIZE; i++) sts[i] = new AddTransformer(i*offset, i*offset);
        BenchmarkTest.addTestCase(() -> Arrays.stream(sts)
                                         .forEach(st -> st.transform()), "add - sequential");

        // parallel ordered: ... to be done ...

        // parallel unordered: ... to be done ...


        /*
            "expensive" transformation
            - sequential
            - parallel unordered
            - parallel ordered
         */
        for (int i=0; i<SIZE; i++) sts[i] = new SineTransformer(i*offset);
        BenchmarkTest.addTestCase(() -> Arrays.stream(sts)
                                         .forEach(st -> st.transform()), "sine - sequential");

        // parallel ordered: ... to be done ...

        // parallel unordered: ... to be done ...


        double[] doubles = new double[SIZE];
        for (int i=0; i<SIZE; i++) doubles[i] = i*offset;
        /*
            "cheap" mapping
            - sequential
            - parallel unordered
            - parallel ordered
         */
        BenchmarkTest.addTestCase(() -> Arrays.stream(doubles)
                                          .mapToObj(d -> new AddTransformer(d, d))
                                          .forEach(st -> st.transform()), "map with add - sequential");

        // parallel ordered: ... to be done ...

        // parallel unordered: ... to be done ...


        /*
            "expensive" mapping
            - sequential
            - parallel unordered
            - parallel ordered
         */
        BenchmarkTest.addTestCase(() -> Arrays.stream(doubles)
                                         .map(Sine::slowSin)
                                         .mapToObj(d -> new AddTransformer(d, d))
                                         .forEach(st -> st.transform()), "map with sine - sequential");

        // parallel ordered: ... to be done ...

        // parallel unordered: ... to be done ...

        /////////////////////////////////////////////////////////////////////////
        // START BENCHMARK
        BenchmarkTest.doTest();
    }
}
