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
import java.util.Arrays;
import java.util.stream.Stream;

public class Lab_05_06 {
    private static String[] text = {"Hänschen ", "klein ", "ging ", "allein ", "in ", "die ", "weite ", "Welt ", "hinein "};

    private static String with3ParameterCollect(Stream<String> strings) {
        return strings
                .collect(StringBuilder::new,
                        StringBuilder::append,
                        StringBuilder::append)
                .toString();
    }
    private static String with3ParameterReduce(Stream<String> strings) {
        return strings
                .reduce(new StringBuilder(),
                        StringBuilder::append,
                        StringBuilder::append)
                .toString();
    }
    private static String with3ParameterReduceAndNewsStringBuilders(Stream<String> strings) {
        return strings
                .reduce(new StringBuilder(),
                        (sb,s)->new StringBuilder(sb).append(s),
                        (sb1,sb2)->new StringBuilder(sb1).append(sb2))
                .toString();
    }
    public static void main(String... args) {
        System.out.println("SEQUENTIAL");
        System.out.println("(collect) "+with3ParameterCollect(Arrays.stream(text)));
        System.out.println("(mutating reduce)  "+with3ParameterReduce(Arrays.stream(text)));
        System.out.println("(non-mutating reduce)  "+with3ParameterReduceAndNewsStringBuilders(Arrays.stream(text)));

        System.out.println("PARALLEL");
        System.out.println("(collect) "+with3ParameterCollect(Arrays.stream(text).parallel()));
        System.out.println("(mutating reduce)  "+with3ParameterReduce(Arrays.stream(text).parallel()));
        System.out.println("(non-mutating reduce)  "+with3ParameterReduceAndNewsStringBuilders(Arrays.stream(text).parallel()));
    }
}
