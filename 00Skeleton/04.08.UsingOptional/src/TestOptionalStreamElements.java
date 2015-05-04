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


import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


public class TestOptionalStreamElements {

    private static void useMapWithNullValues() {
        Map<Integer, String> map = new HashMap<>();

        map.put(1, "one");
        map.put(2, "two");
        map.put(3, null);
        map.put(4, "four");
        map.put(6, null);
        map.put(8, "eight");

        for (int i=0; i<10; i++) {
            String s = map.get(i);

            if (s == null)
                if (map.containsKey(i))
                    System.out.println(i + " value is null");
                else
                    System.out.println(i + " has no entry");
            else
                System.out.println(i + " -> " + s);
        }
    }

    private static void useMapWithOptionalValues() {
        ... to be done ...
    }

    public static void main(String... args) {
        useMapWithNullValues();
        System.out.println();

        useMapWithOptionalValues();
        System.out.println();
    }
}
