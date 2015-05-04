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

import java.awt.Point;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.IntPredicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Lab_04_06 {

    private static final String workingDirectory = "D:\\Courses\\Curriculum\\Java\\Lambda-Seminar\\Labs\\Code\\Solution\\04.06.AuthorFeeRevisited\\";

    /*
    Count the space and non-space characters in one pass through the text in file text.txt and print the results.
     */

    private static void countSpaceAndNonSpaceCharacters_1a() {
        try (BufferedReader in = new BufferedReader(new FileReader(workingDirectory+"text.txt"))){
            Map<Boolean, List<Integer>> map = in
                    .lines()                           // Stream<String>
                    .flatMapToInt(String::chars)       // IntStream
                    .boxed()                           // Stream<Integer>
                    .collect(Collectors.partitioningBy(Character::isSpaceChar));

            int chars  = map.get(false).size();
            int spaces = map.get(true).size();

            System.out.println("spaces     = " + spaces);
            System.out.println("non-spaces = " + chars);
        } catch (IOException | UncheckedIOException e) {
            e.printStackTrace();
        }
    }
    // An alternative solution that uses a downstream collector for counting:
    private static void countSpaceAndNonSpaceCharacters_1b() {
        try (BufferedReader in = new BufferedReader(new FileReader(workingDirectory+"text.txt"))){
            Map<Boolean, Long> map = in
                    .lines()                           // Stream<String>
                    .flatMapToInt(String::chars)       // IntStream
                    .boxed()                           // Stream<Integer>
                    .collect(Collectors.partitioningBy(Character::isSpaceChar,Collectors.counting()));

            long chars  = map.get(false);
            long spaces = map.get(true);

            System.out.println("spaces     = " + spaces);
            System.out.println("non-spaces = " + chars);
        } catch (IOException | UncheckedIOException e) {
            e.printStackTrace();
        }
    }
    // An alternative solution that performs the boxing as part of the flat-mapping:
    private static void countSpaceAndNonSpaceCharacters_2() {
        try (BufferedReader in = new BufferedReader(new FileReader(workingDirectory+"text.txt"))){
            Function<? super String, ? extends Stream<? extends Character>> mapper =
                    s->s.chars()                                     // IntStream
                        .mapToObj(i -> Character.valueOf((char) i))  // Stream<Character>
                    ;
            Map<Boolean,List<Character>>  map =  in
                    .lines()                                      // Stream<String>
                    .flatMap(mapper)                              // Stream<Character>
                    .collect(Collectors.partitioningBy(Character::isSpaceChar));

            System.out.println("spaces     = "+map.get(true).stream().count());
            System.out.println("non-spaces = "+map.get(false).stream().count());
        } catch (IOException | UncheckedIOException e) {
            e.printStackTrace();
        }
    }
    // An alternative solution that uses the IntStream's collect operation, which takes
    // - a  supplier    that creates the map
    // - an accumulator that add a key-value pair to the map
    // - a  combiner    that combines two maps
    private static void countSpaceAndNonSpaceCharacters_3() {
        try (BufferedReader in = new BufferedReader(new FileReader(workingDirectory+"text.txt"))){
            Map<Boolean, List<Integer>> map = in
                    .lines()                           // Stream<String>
                    .flatMapToInt(String::chars)       // IntStream
                    .collect(// supplier
                             ()->   {   HashMap<Boolean, List<Integer>> tmpMap = new HashMap<>();
                                        tmpMap.put(true ,new ArrayList<Integer>());
                                        tmpMap.put(false,new ArrayList<Integer>());
                                        return tmpMap;
                                    }
                              // accumulator
                            ,(m,i)-> m.computeIfPresent(Character.isSpaceChar(i),(b,l)->{l.add(i); return l;})
                             // combiner
                            ,HashMap::putAll);

            int chars  = map.get(false).size();
            int spaces = map.get(true).size();

            System.out.println("spaces     = " + spaces);
            System.out.println("non-spaces = " + chars);
        } catch (IOException | UncheckedIOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String... args) {
        countSpaceAndNonSpaceCharacters_1a();
        countSpaceAndNonSpaceCharacters_1b();
        countSpaceAndNonSpaceCharacters_2();
        countSpaceAndNonSpaceCharacters_3();
    }
}
