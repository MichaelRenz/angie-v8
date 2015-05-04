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
            "add" transformation
            - sequential
            - parallel unordered
            - parallel ordered
         */
        for (int i=0; i<SIZE; i++) sts[i] = new AddTransformer(i*offset, i*offset);
        BenchmarkTest.addTestCase(() -> Arrays.stream(sts)
                                         .forEach(st -> st.transform()), "add - sequential");

        for (int i=0; i<SIZE; i++) sts[i] = new AddTransformer(i*offset, i*offset);
        BenchmarkTest.addTestCase(() -> Arrays.stream(sts).parallel()
                                         .forEach(st -> st.transform()), "add - parallel unordered");

        for (int i=0; i<SIZE; i++) sts[i] = new AddTransformer(i*offset, i*offset);
        BenchmarkTest.addTestCase(() -> Arrays.stream(sts).parallel()
                                         .forEachOrdered(st -> st.transform()), "add - parallel ordered");
        /*
            "sine" transformation
            - sequential
            - parallel unordered
            - parallel ordered
         */
        for (int i=0; i<SIZE; i++) sts[i] = new SineTransformer(i*offset);
        BenchmarkTest.addTestCase(() -> Arrays.stream(sts)
                                         .forEach(st -> st.transform()), "sine - sequential");

        for (int i=0; i<SIZE; i++) sts[i] = new SineTransformer(i*offset);
        BenchmarkTest.addTestCase(() -> Arrays.stream(sts).parallel()
                                         .forEach(st -> st.transform()), "sine - parallel unordered");

        for (int i=0; i<SIZE; i++) sts[i] = new SineTransformer(i*offset);
        BenchmarkTest.addTestCase(() -> Arrays.stream(sts).parallel()
                                         .forEachOrdered(st -> st.transform()), "sine - parallel ordered");

        double[] doubles = new double[SIZE];
        for (int i=0; i<SIZE; i++) doubles[i] = i*offset;
        /*
            "add" mapping
            - sequential
            - parallel unordered
            - parallel ordered
         */
        BenchmarkTest.addTestCase(() -> Arrays.stream(doubles)
                                          .mapToObj(d -> new AddTransformer(d, d))
                                          .forEach(st -> st.transform()), "map with add - sequential");

        BenchmarkTest.addTestCase(() -> Arrays.stream(doubles).parallel()
                                         .mapToObj(d -> new AddTransformer(d, d))
                                         .forEach(st -> st.transform()), "map with add - parallel unordered");

        BenchmarkTest.addTestCase(() -> Arrays.stream(doubles).parallel()
                                         .mapToObj(d -> new AddTransformer(d, d))
                                         .forEachOrdered(st -> st.transform()), "map with add - parallel ordered");
        /*
            "sine" mapping
            - sequential
            - parallel unordered
            - parallel ordered
         */
        BenchmarkTest.addTestCase(() -> Arrays.stream(doubles)
                                         .map(Sine::slowSin)
                                         .mapToObj(d -> new AddTransformer(d, d))
                                         .forEach(st -> st.transform()), "map with sine - sequential");

        BenchmarkTest.addTestCase(() -> Arrays.stream(doubles)
                                         .map(Sine::slowSin).parallel()
                                         .mapToObj(d -> new AddTransformer(d, d))
                                         .forEach(st -> st.transform()), "map with sine - parallel unordered");

        BenchmarkTest.addTestCase(() -> Arrays.stream(doubles)
                                         .map(Sine::slowSin).parallel()
                                         .mapToObj(d -> new AddTransformer(d, d))
                                         .forEachOrdered(st -> st.transform()), "map with sine - parallel ordered");

        BenchmarkTest.doTest();
    }
}
