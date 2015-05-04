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

        import java.io.BufferedReader;
        import java.io.FileReader;
        import java.io.IOException;
        import java.io.UncheckedIOException;
        import java.util.function.IntPredicate;

public class Lab_04_03 {

    /*
    Count all non-space characters in the text file and print the count.
    Hint: a space character can be identified via Character.isSpace().
     */
    private static void countNonSpaceCharacters() {
        try (BufferedReader in = new BufferedReader(new FileReader("text.txt"))){
            long cnt = in.lines()
                    .flatMapToInt(String::chars)
                    .filter(((IntPredicate)Character::isSpaceChar).negate())
                    .count();

            System.out.println("number of non-white-space chars: " + cnt);
        } catch (IOException | UncheckedIOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String... args) {
        countNonSpaceCharacters();
    }
}
