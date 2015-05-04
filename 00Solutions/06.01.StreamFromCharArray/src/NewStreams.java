import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Consumer;
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
    private static class CharArrayIterator1 implements Iterator<Character> {
        private int i = 0;
        private final char[] array;


        public CharArrayIterator1(char[] array) {
            this.array = array;
        }

        @Override
        public boolean hasNext() {
            return (i < array.length ? true : false);
        }

        @Override
        public Character next() {
            if (i < array.length)
                return array[i++];
            else
                throw new NoSuchElementException();
        }

        @Override
        public void forEachRemaining(Consumer<? super Character> action) {
            Objects.requireNonNull(action);
            char[] a = array;
            int j = i;
            int l = array.length;
            i = l;

            if (j >= 0 && j < l) {
                do { action.accept(a[j]); } while (++j < l);
            }
        }
    }

    public static Stream<Character> streamOfCharArray1(char[] array) {
        Iterator<Character> iter = new CharArrayIterator1(array);

        Spliterator<Character> spliter =
                Spliterators.spliterator(iter, array.length, Spliterator.ORDERED | Spliterator.IMMUTABLE | Spliterator.NONNULL);

        return StreamSupport.stream(spliter, false);
    }


    //-------------------- APPROACH # 2 ------------------------------------------------------------------------------------------
    /*
     *  Similar to the approach above: Implement an iterator of type PrimitiveIterator.OfInt and
     *  use it to create an IntStream backed by the char[] as underlying stream source.
     */
    private static class CharArrayIterator2 implements PrimitiveIterator.OfInt {
        private int i = 0;
        private final char[] array;


        public CharArrayIterator2(char[] array) {
            this.array = array;
        }

        @Override
        public boolean hasNext() {
            return (i < array.length ? true : false);
        }

        @Override
        public int nextInt() {
            if (i < array.length)
                return array[i++];
            else
                throw new NoSuchElementException();
        }

        @Override
        public void forEachRemaining(IntConsumer action) {
            Objects.requireNonNull(action);
            char[] a = array;
            int j = i;
            int l = array.length;
            i = l;

            if (j >= 0 && j < l) {
                do { action.accept(a[j]); } while (++j < l);
            }
        }
    }

    public static IntStream intStreamOfCharArray2(char[] array) {
        PrimitiveIterator.OfInt iter = new CharArrayIterator2(array);

        Spliterator.OfInt spliter = Spliterators.spliterator(iter, array.length, Spliterator.ORDERED | Spliterator.IMMUTABLE);

        return StreamSupport.intStream(spliter, false);
    }


    //-------------------- APPROACH # 3 ------------------------------------------------------------------------------------------
    /*
     *  Implement a Spliterator.OfInt from scratch and use it to create an IntStream backed by the char[]
     *  as underlying stream source.
     *  Hint: Copy the source code of Spliterators.IntArraySpliterator and change the type of the
     *  underlying array from int[] to char[].
     */
    static final class CharArraySpliterator implements Spliterator.OfInt {
        private final char[] array;
        private int index;        // current index, modified on advance/split
        private final int fence;  // one past last index
        private final int characteristics;

        /**
         * Creates a spliterator covering all of the given array.
         * @param array the array, assumed to be unmodified during use
         * @param additionalCharacteristics Additional spliterator characteristics
         *        of this spliterator's source or elements beyond {@code SIZED} and
         *        {@code SUBSIZED} which are are always reported
         */
        public CharArraySpliterator(char[] array, int additionalCharacteristics) {
            this(array, 0, array.length, additionalCharacteristics);
        }

        /**
         * Creates a spliterator covering the given array and range
         * @param array the array, assumed to be unmodified during use
         * @param origin the least index (inclusive) to cover
         * @param fence one past the greatest index to cover
         * @param additionalCharacteristics Additional spliterator characteristics
         *        of this spliterator's source or elements beyond {@code SIZED} and
         *        {@code SUBSIZED} which are are always reported
         */
        public CharArraySpliterator(char[] array, int origin, int fence, int additionalCharacteristics) {
            this.array = array;
            this.index = origin;
            this.fence = fence;
            this.characteristics = additionalCharacteristics | Spliterator.SIZED | Spliterator.SUBSIZED;
        }

        @Override
        public OfInt trySplit() {
            int lo = index, mid = (lo + fence) >>> 1;
            return (lo >= mid)
                    ? null
                    : new CharArraySpliterator(array, lo, index = mid, characteristics);
        }

        @Override
        public void forEachRemaining(IntConsumer action) {
            char[] a; int i, hi; // hoist accesses and checks from loop
            if (action == null)
                throw new NullPointerException();
            if ((a = array).length >= (hi = fence) &&
                    (i = index) >= 0 && i < (index = hi)) {
                do { action.accept(a[i]); } while (++i < hi);
            }
        }

        @Override
        public boolean tryAdvance(IntConsumer action) {
            if (action == null)
                throw new NullPointerException();
            if (index >= 0 && index < fence) {
                action.accept(array[index++]);
                return true;
            }
            return false;
        }

        @Override
        public long estimateSize() { return (long)(fence - index); }

        @Override
        public int characteristics() {
            return characteristics;
        }

        @Override
        public Comparator<? super Integer> getComparator() {
            if (hasCharacteristics(Spliterator.SORTED))
                return null;
            throw new IllegalStateException();
        }
    }

    public static IntStream intStreamOfCharArray3(char[] array) {
        Spliterator.OfInt spliter = new CharArraySpliterator(array, Spliterator.ORDERED |
                                                                    Spliterator.IMMUTABLE |
                                                                    Spliterator.SIZED |
                                                                    Spliterator.SUBSIZED);

        return StreamSupport.intStream(spliter, false);
    }


    //-------------------- APPROACH # 4 and # 5 ------------------------------------------------------------------------------------------
    /*
     *  #4
     *  Implement a Spliterator.OfInt by deriving from Spliterators. AbstractIntSpliterator and implement
     *  the abstract tryAdvance() method.  Use the newly defined spliterator type to create an IntStream
     *  backed by the char[] as underlying stream source.
     *  Hint: Use the same implementation technique as in approach #3, i.e., copy the source code of
     *  Spliterators.IntArraySpliterator and change the type of the underlying array from int[] to char[].
     *
     *  # 5
     *  Same as above, but this time do not inherit the spliterator's forEachRemaining() method,
     *  instead override it.
     */
    static final class CharArrayDerivedSpliterator extends Spliterators.AbstractIntSpliterator implements Spliterator.OfInt {
        private final char[] array;
        private int index;        // current index, modified on advance/split
        private final int fence;  // one past last index
        private final boolean overrideForEachRemaining;  // should forEachRemaining() be overridden ?

        public CharArrayDerivedSpliterator(char[] array, int additionalCharacteristics, boolean overrideForEachRemaining) {
            super(array.length, additionalCharacteristics);
            this.array = array;
            this.index = 0;
            this.fence = array.length;
            this.overrideForEachRemaining = overrideForEachRemaining;
        }

        @Override
        public boolean tryAdvance(IntConsumer action) {
            if (action == null)
                throw new NullPointerException();
            if (index >= 0 && index < fence) {
                action.accept(array[index++]);
                return true;
            }
            return false;
        }

        @Override
        public void forEachRemaining(IntConsumer action) {
            if (!overrideForEachRemaining) {
                super.forEachRemaining(action);
            }
            else {
                char[] a;
                int i, hi; // hoist accesses and checks from loop
                if (action == null)
                    throw new NullPointerException();
                if ((a = array).length >= (hi = fence) &&
                        (i = index) >= 0 && i < (index = hi)) {
                    do {
                        action.accept(a[i]);
                    } while (++i < hi);
                }
            }
        }
    }

    private static IntStream intStreamOfCharArrayInternal(char[] array, boolean overrideForEachRemaining) {
        Spliterator.OfInt spliter = new CharArrayDerivedSpliterator(array, Spliterator.ORDERED |
                                                                           Spliterator.IMMUTABLE |
                                                                           Spliterator.SIZED |
                                                                           Spliterator.SUBSIZED,
                                                                    overrideForEachRemaining);

        return StreamSupport.intStream(spliter, false);
    }

    public static IntStream intStreamOfCharArray4(char[] array) {
        return intStreamOfCharArrayInternal(array, false);
    }

    public static IntStream intStreamOfCharArray5(char[] array) {
        return intStreamOfCharArrayInternal(array, true);
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
