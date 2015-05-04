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

import java.math.BigInteger;
import java.util.Arrays;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;


public class Lab_04_01 {
    /*
    Step 1: Create a sequential and a parallel stream of strings that has an array as the underlying stream source.
            The stream should contain the days of the week.  Find at least two ways of doing it.
     */
    private static Stream<String> createStreamFromArray_1() {
        String[] weekdays = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
        Stream<String> sequentialStringStream = Arrays.stream(weekdays);
        Stream<String> parallelStringStream   = Arrays.stream(weekdays).parallel();
        return parallelStringStream;
    }
    private static Stream<String> createStreamFromArray_2() {
        Stream<String> sequentialStringStream
          = Stream.of("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday");
        return sequentialStringStream;
    }
    /*
    Step 2a: Create a stream of natural constants containing 2.997 924 58 (speed of light),
             3.1415 9265 359 (Pi) and 6.67384 (gravitational constant).
    */
    private static DoubleStream createDoubleStream() {
        return DoubleStream.of(
                 2.997_924_58               //speed of light
                ,3.1415_9265_359            // Pi
                ,6.67384                    // gravitational constant
        );
    }
    /*
    Step 2b: Create a stream of the integral numbers from 51 thru 100 (inclusive).
    */
    private static IntStream createIntStream() {
        return IntStream.rangeClosed(51,100);
    }
    /*
        Step 3: Create a stream that has a character sequence (say, a string containing your name)
                as the underlying stream source.
    */
    private static IntStream createCharacterStream() {
        return "Angelika Langer".chars();
    }
    /*
    Step 4: Create infinite streams:
            •	a stream of pseudo random numbers,
            •	the stream of the powers of 2, i.e., 2 4 8 16 32 64 …
            •	the stream of BigIntegers with all positive integral numbers
            Hint: Interface Stream has generate and iterate methods for this purpose.
     */
    private static Stream<Double> createStreamOfRandomNumbers() {
        return Stream.generate(Math::random);
    }
    private static Stream<Integer> createStreamOfPowersOfTwo() {
        return Stream.iterate(2, i -> i * 2);
    }
    private static Stream<BigInteger> createStreamBigIntegers() {
        return Stream.iterate(BigInteger.ZERO, i -> i.add(BigInteger.ONE));
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

        createCharacterStream().forEach(w->System.out.print(w+" "));
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
