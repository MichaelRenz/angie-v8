import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collector;


public class TestAlternativeMaxCollector {

    private static final char[] cs = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o',
                                       'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', ' '};

    private static final int CS_LEN = cs.length;
    private static final int LEN = 1000000;
    private static final int MAX_STRING_LEN = 10;

    private static List<String> stringList = new ArrayList<>();

    private static void populateList() {
        ThreadLocalRandom rand = ThreadLocalRandom.current();

        for (int i=0; i<LEN; i++) {
            StringBuilder sb = new StringBuilder(MAX_STRING_LEN);

            int nextStringLen = rand.nextInt(MAX_STRING_LEN);
            if (nextStringLen == 0) {
                i--;
                continue;
            }

            for(int j=0; j<nextStringLen; j++)
                sb.append(cs[rand.nextInt(CS_LEN)]);

            stringList.add(sb.toString());
        }

        System.out.println("stringList length: " + stringList.size());
    }


    private static void test() {
        // max operation aus dem JDK
        stringList.stream().parallel().max(String::compareTo).ifPresent(s -> System.out.println("max: " + s));

        // your collector
        Collector<String,?,String> collector = ... to be done ...
        String m = stringList.stream().parallel().collect(collector);
        System.out.println("max: " + m);
    }

    private static void benchmark() {
        BenchmarkTest.addTestCase(() -> stringList.stream()
                                             .max(String::compareTo), "max() / sequential");

        BenchmarkTest.addTestCase(() -> stringList.stream().parallel()
                                             .max(String::compareTo), "max() / parallel");

        ///////////////////////

        Collector<String,?,String> collector = ... to be done ...

        BenchmarkTest.addTestCase(() -> stringList.stream()
                                             .collect(collector), "collect() /  sequential");

        BenchmarkTest.addTestCase(() -> stringList.stream().parallel()
                                             .collect(collector), "collect() / parallel");

        /////////////////////////////////////////////////////////////////////////
        // START BENCHMARK
        BenchmarkTest.doTest();

    }

    public static void main(String[] args) {
        populateList();
        test();
        benchmark();
    }
}
