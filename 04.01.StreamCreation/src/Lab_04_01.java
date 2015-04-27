import java.math.BigInteger;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/*
  Based on course material for "Lambdas & Streams", a seminar prepared
  and owned by Angelika Langer & Klaus Kreft.
  contact: http://www.AngelikaLanger.com/ or mailto: info@AngelikaLanger.com

  © Copyright 2013-2014 by Angelika Langer & Klaus Kreft. All rights reserved.

  Permission to use, copy, and modify this software for any non-profit
  purpose is hereby granted to attendants of the above mentioned seminar
  without fee, provided that the above copyright notice appears in all
  copies.  Angelika Langer and Klaus Kreft make no representations about
  the suitability of this software for any purpose.  It is provided
  "as is" without express or implied warranty.
*/
public class Lab_04_01 {
    /*
    Step 1: Create a sequential and a parallel stream of strings that has an array as the underlying stream source.
            The stream should contain the days of the week.  Find at least two ways of doing it.
     */
    private static Stream<String> createStreamFromArray_1() {
        // TBD ... to be done ...
        return Stream.empty();
    }
    private static Stream<String> createStreamFromArray_2() {
        // TBD ... to be done ...
        return Stream.empty();
    }
    /*
    Step 2a: Create a stream of natural constants containing 2.997 924 58 (speed of light),
             3.1415 9265 359 (Pi) and 6.67384 (gravitational constant).
    */
    private static DoubleStream createDoubleStream() {
        // TBD ... to be done ...
        return DoubleStream.empty();
    }
    /*
    Step 2b: Create a stream of the integral numbers from 51 thru 100 (inclusive).
    */
    private static IntStream createIntStream() {
        // TBD ... to be done ...
        return IntStream.empty();
    }
    /*
        Step 3: Create a stream that has a character sequence (say, a string containing your name)
                as the underlying stream source.
    */
    private static IntStream createCharacterStream() {
        // TBD ... to be done ...
        return IntStream.empty();
    }
    /*
    Step 4: Create infinite streams:
            •	a stream of pseudo random numbers,
            •	the stream of the powers of 2, i.e., 2 4 8 16 32 64 …
            •	the stream of BigIntegers with all positive integral numbers
            Hint: Interface Stream has generate and iterate methods for this purpose.
     */
    private static Stream<Double> createStreamOfRandomNumbers() {
        // TBD ... to be done ...
        return Stream.empty();
    }
    private static Stream<Integer> createStreamOfPowersOfTwo() {
        // TBD ... to be done ...
        return Stream.empty();
    }
    private static Stream<BigInteger> createStreamBigIntegers() {
        // TBD ... to be done ...
        return Stream.empty();
    }
    public static void main(String... args) {
        createStreamFromArray_1().forEach(w->System.out.print(w+" "));
        System.out.println();
        createStreamFromArray_2().forEach(w->System.out.print(w+" "));
        System.out.println();

        createDoubleStream().forEach(w->System.out.print(w+" "));
        System.out.println();
        createIntStream().forEach(w->System.out.print(w+" "));
        System.out.println();

        createCharacterStream().forEach(w->System.out.format("%c ",w));
        System.out.println();

        createStreamOfRandomNumbers().limit(10).forEach(w -> System.out.print(w + " "));
        System.out.println();
        createStreamOfPowersOfTwo().limit(10).forEach(w->System.out.print(w+" "));
        System.out.println();
        createStreamBigIntegers().limit(10).forEach(w->System.out.print(w+" "));
        System.out.println();
    }
}
