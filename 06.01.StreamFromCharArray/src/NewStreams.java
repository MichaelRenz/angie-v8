import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.IntConsumer;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class NewStreams {

    // build streams from a char[] - five different solutions

    private static char[] testChars = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'};

    //-------------------- APPROACH # 1 ------------------------------------------------------------------------------------------
    /*
     *  Implement an Iterator<Character> and use the factory method Spliterators.spliterator()
     *  to create a spliterator from this iterator.  Use this spliterator to create a Stream<Character>
     *  backed by the char[] as underlying stream source.
     */
    public static Stream<Character> streamOfCharArray1(char[] array) {
        ... to be done ...
    }


    //-------------------- APPROACH # 2 ------------------------------------------------------------------------------------------
    /*
     *  Similar to the approach above: Implement an iterator of type PrimitiveIterator.OfInt and
     *  use it to create an IntStream backed by the char[] as underlying stream source.
     */
    public static IntStream intStreamOfCharArray2(char[] array) {
        ... to be done ...
    }


    //-------------------- APPROACH # 3 ------------------------------------------------------------------------------------------
    /*
     *  Implement a Spliterator.OfInt from scratch and use it to create an IntStream backed by the char[]
     *  as underlying stream source.
     *  Hint: Copy the source code of Spliterators.IntArraySpliterator and change the type of the
     *  underlying array from int[] to char[].
     */
    public static IntStream intStreamOfCharArray3(char[] array) {
        ... to be done ...
    }


    //-------------------- APPROACH # 4  ------------------------------------------------------------------------------------------
    /*
     *  Implement a Spliterator.OfInt by deriving from Spliterators. AbstractIntSpliterator and implement
     *  the abstract tryAdvance() method.  Use the newly defined spliterator type to create an IntStream
     *  backed by the char[] as underlying stream source.
     *  Hint: Use the same implementation technique as in approach #3, i.e., copy the source code of
     *  Spliterators.IntArraySpliterator and change the type of the underlying array from int[] to char[].
     */
    public static IntStream intStreamOfCharArray4(char[] array) {
        ... to be done ...
    }
    //-------------------- APPROACH # 5  ------------------------------------------------------------------------------------------
    /*
     *  Same as above, but this time do not inherit the spliterator's forEachRemaining() method,
     *  instead override it.
     */
    public static IntStream intStreamOfCharArray5(char[] array) {
        ... to be done ...
    }

    /////////////////////////////////////////////////  MAIN //////////////////////////////////////////////////////

    public static void main(String[] args) {
        /*
         *  Test functionality: do the new streams work properly?
         */
        streamOfCharArray1(testChars).forEach(c -> System.out.print(c + " "));
        System.out.println();

        intStreamOfCharArray2(testChars).forEach(i -> System.out.print((char) i + " "));
        System.out.println();

        intStreamOfCharArray3(testChars).forEach(i -> System.out.print((char) i + " "));
        System.out.println();

        intStreamOfCharArray4(testChars).forEach(i -> System.out.print((char)i + " "));
        System.out.println();

        intStreamOfCharArray5(testChars).forEach(i -> System.out.print((char)i + " "));
        System.out.println();


        /*
         *  Test for performance: how fast are the new streams?
         */
        System.out.println("----------------------------------------------");

        final int SIZE = 10_000_000;
        ThreadLocalRandom rand = ThreadLocalRandom.current();

        int[] ints = new int[SIZE];
        for (int i=0; i<SIZE; i++) ints[i] = rand.nextInt();

        final char[] chars = new char[SIZE];
        final Character[] characters = new Character[SIZE];
        for (int i=0; i<SIZE; i++) {
            chars[i] = (char) rand.nextInt();
            characters[i] = chars[i];
        }

        BenchmarkTest.addTestCase(() -> Arrays.stream(ints).count(),
                "int[]  - IntStream (JDK with Arrays.stream(),                          sequential");

        BenchmarkTest.addTestCase(() -> Arrays.stream(ints).parallel().count(),
                "int[]  - IntStream (JDK with Arrays.stream()),                         parallel  ");

        BenchmarkTest.addTestCase(() -> Arrays.stream(characters).count(),
                "Character[] - Stream<Character> (JDK with Arrays.stream()),            sequential");

        BenchmarkTest.addTestCase(() -> Arrays.stream(characters).parallel().count(),
                "Character[] - Stream<Character> (JDK with Arrays.stream()),            parallel");

        BenchmarkTest.addTestCase(() -> streamOfCharArray1(chars).count(),
                "char[] - Stream<Character>:#1 (spliterator from Iterator<Character>),  sequential");

        BenchmarkTest.addTestCase(() -> streamOfCharArray1(chars).parallel().count(),
                "char[] - Stream<Character>:#1 (spliterator from Iterator<Character>),  parallel");

        BenchmarkTest.addTestCase(() -> intStreamOfCharArray2(chars).count(),
                "char[] - IntStream:#2 (spliterator from PrimitiveIterator.OfInt),      sequential");

        BenchmarkTest.addTestCase(() -> intStreamOfCharArray2(chars).parallel().count(),
                "char[] - IntStream:#2 (spliterator from PrimitiveIterator.OfInt),      parallel");

        BenchmarkTest.addTestCase(() -> intStreamOfCharArray3(chars).count(),
                "char[] - IntStream:#3 (Spliterator.OfInt from scratch),                sequential");

        BenchmarkTest.addTestCase(() -> intStreamOfCharArray3(chars).parallel().count(),
                "char[] - IntStream:#3 (Spliterator.OfInt from scratch),                parallel");

        BenchmarkTest.addTestCase(() -> intStreamOfCharArray4(chars).count(),
                "char[] - IntStream:#4 (derived from AbstractIntSpliterator),           sequential");

        BenchmarkTest.addTestCase(() -> intStreamOfCharArray4(chars).parallel().count(),
                "char[] - IntStream:#4 (derived from AbstractIntSpliterator),           parallel");

        BenchmarkTest.addTestCase(() -> intStreamOfCharArray5(chars).count(),
                "char[] - IntStream:#5 (derived w/ forEachRemaining()),                 sequential");

        BenchmarkTest.addTestCase(() -> intStreamOfCharArray5(chars).parallel().count(),
                "char[] - IntStream:#5 (derived w/ forEachRemaining()),                 parallel");


        BenchmarkTest.doTest();
    }
}
