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
import java.util.Optional;

public class TestLegacyWrapper {
    private static Optional<String> wrappedGetProperty(String key, Optional<String> def)  {
        return Optional.ofNullable(System.getProperty(key,def.orElse(null)));
    }
    public static void main(String... arg) {
        // legacy usage
        String value1 = System.getProperty("none",null);
        System.out.println(value1);
        String value2 = System.getProperty("none","default");
        System.out.println(value2);

        // usage in an "Optional" world
        Optional<String> optional1 = wrappedGetProperty("none",Optional.empty());
        System.out.println(optional1);
        Optional<String> optional2 = wrappedGetProperty("none",Optional.of("default"));
        System.out.println(optional2);
    }
}
