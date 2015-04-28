import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.function.IntPredicate;
import java.util.stream.Stream;

/*
  Based on course material for "Lambdas & Streams", a seminar prepared
  and owned by Angelika Langer & Klaus Kreft.
  contact: http://www.AngelikaLanger.com/ or mailto: info@AngelikaLanger.com

  Â© Copyright 2013-2014 by Angelika Langer & Klaus Kreft. All rights reserved.

  Permission to use, copy, and modify this software for any non-profit
  purpose is hereby granted to attendants of the above mentioned seminar
  without fee, provided that the above copyright notice appears in all
  copies.  Angelika Langer and Klaus Kreft make no representations about
  the suitability of this software for any purpose.  It is provided
  "as is" without express or implied warranty.
*/

public class Lab_04_03 {

    /*
    Step 1: Count all non-space characters in the text file and print the count.
            Hint: a space character can be identified via Character.isSpace().
     */
    private static void countNonSpaceCharacters() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\oose\\Desktop\\Skeleton\\Skeleton\\04.03.AuthorFee\\text.txt"));
        //int cci = br.lines().flatMap(l -> Stream.of(l.split(" "))).mapToInt(w-> w.length()).sum();
        //System.out.println(cci);
        
        long ccl = br.lines().flatMapToInt(String::chars).filter( ((IntPredicate) Character::isSpaceChar).negate()).count();
        System.out.println(ccl);
        br.close();
    }

    public static void main(String... args) throws IOException {
        countNonSpaceCharacters();
    }
}
