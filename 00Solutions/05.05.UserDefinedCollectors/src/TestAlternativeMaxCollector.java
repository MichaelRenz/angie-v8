import sun.net.www.content.audio.x_aiff;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import static java.util.stream.Collector.Characteristics.*;

public class TestAlternativeMaxCollector {
    private static final char[] cs = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o',
                                       'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', ' '};

    private static final int CS_LEN = cs.length;
    private static final int LEN = 1_000_000;
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

    // solution 1
    private static class ConcurrentMaxFinder {
        private AtomicReference<String> max =  new AtomicReference<>("");

        public void accept(String s) {
            String old = null;
            do {
                old = max.get();
                if (s.compareTo(old) <= 0)
                    break;
            } while(!max.compareAndSet(old, s));
        }

        public ConcurrentMaxFinder combine(ConcurrentMaxFinder cmf) {
            return (max.get().compareTo(cmf.max.get()) >= 0 ? this : cmf);
        }

        String getMax() {
            return max.get();
        }
    }

    // solution 2 (alternative implementation with explicit derivation)
    private static class MyCollector implements Collector<String, AtomicReference<String>, String>  {

        @Override
        public Supplier<AtomicReference<String>> supplier() {
            return (() -> new AtomicReference<String>(""));
        }

        @Override
        public BiConsumer<AtomicReference<String>, String> accumulator() {
            return ((max, s) -> {
                                   String old = null;
                                   do {
                                      old = max.get();
                                      if (s.compareTo(old) <= 0)
                                      break;
                                   } while(!max.compareAndSet(old, s));
                                });
        }

        @Override
        public BinaryOperator<AtomicReference<String>> combiner() {
            return ((m1, m2) -> m1.get().compareTo(m2.get()) >= 0 ? m1 : m2);
        }

        @Override
        public Function<AtomicReference<String>, String> finisher() {
            return (m -> m.get());
        }

        @Override
        public Set<Characteristics> characteristics() {
            return Collections.unmodifiableSet(EnumSet.of(CONCURRENT, UNORDERED));
        }
    }

    private static void test() {
        // max operation from JDK
        stringList.stream().parallel().max(String::compareTo).ifPresent(s -> System.out.println("max: " + s));

        // 1st approach with of()-factory from Collector interface
        Collector<String,ConcurrentMaxFinder,String> collector = Collector.of(ConcurrentMaxFinder::new,
                                                                                ConcurrentMaxFinder::accept,
                                                                                ConcurrentMaxFinder::combine,
                                                                                ConcurrentMaxFinder::getMax,
                                                                                CONCURRENT, UNORDERED);

        String m = stringList.stream().parallel().collect(collector);
        System.out.println("max: " + m);

        // 2nd approach with derivation from Collector interface
        Collector<String,AtomicReference<String>,String> myCollector = new MyCollector();
        m = stringList.stream().parallel().collect(myCollector);
        System.out.println("max: " + m);

        // 3rd incorrect (!) approach with collect()
        m = stringList.stream().parallel().collect(ConcurrentMaxFinder::new,
                                                   ConcurrentMaxFinder::accept,
                                                   ConcurrentMaxFinder::combine)
                                          .getMax();
        System.out.println("max: " + m);
    }

    private static void benchmark() {
        BenchmarkTest.addTestCase(() -> stringList.stream()
                                             .max(String::compareTo), "max() / sequential");

        BenchmarkTest.addTestCase(() -> stringList.stream().parallel()
                                             .max(String::compareTo), "max() / parallel");

        ///////////////////////

        Collector<String,ConcurrentMaxFinder,String> collector = Collector.of(ConcurrentMaxFinder::new,
                                                                              ConcurrentMaxFinder::accept,
                                                                              ConcurrentMaxFinder::combine,
                                                                              ConcurrentMaxFinder::getMax,
                                                                              CONCURRENT, UNORDERED);

        BenchmarkTest.addTestCase(() -> stringList.stream()
                                             .collect(collector), "collect() (Collector.of) /  sequential");

        BenchmarkTest.addTestCase(() -> stringList.stream().parallel()
                                             .collect(collector), "collect() (Collector.of) / parallel");

        ///////////////////////

        Collector<String,AtomicReference<String>,String> myCollector = new MyCollector();

        BenchmarkTest.addTestCase(() -> stringList.stream()
                                             .collect(myCollector), "collect() (MyCollector) /  sequential");

        BenchmarkTest.addTestCase(() -> stringList.stream().parallel()
                                             .collect(myCollector), "collect() (MyCollector) / parallel");

        ///////////////////////

        BenchmarkTest.addTestCase(() -> stringList.stream()
                .collect(ConcurrentMaxFinder::new,
                        ConcurrentMaxFinder::accept,
                        ConcurrentMaxFinder::combine)
                .getMax(), "collect() (incorrect) /  sequential");

        BenchmarkTest.addTestCase(() -> stringList.stream().parallel()
                .collect(ConcurrentMaxFinder::new,
                        ConcurrentMaxFinder::accept,
                        ConcurrentMaxFinder::combine)
                .getMax(), "collect() (incorrect) / parallel");

        BenchmarkTest.doTest();
    }

    public static void main(String[] args) {
        populateList();
        test();
        benchmark();
    }
}

