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

import java.io.*;
import java.util.stream.Stream;

public class Lab_04_08 {
    private static final String workingDirectory = "D:\\Courses\\Curriculum\\Java\\Lambda-Seminar\\Labs\\Code\\Skeleton\\04.08.ClosingStreams\\";
    private static BufferedReader testBR = null;      // for test purposes only

    private static void testClosedReader(){
        // for test purposes: the read attempt must fail, since the file should already be closed
        try {
            testBR.read();
            System.out.println("Oops! We should not get here ...");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static Stream<String> createStreamFromFile (String filename) {
        try {
            final BufferedReader in = new BufferedReader(new FileReader(filename));
            testBR = in;   // for test purposes only
            Stream<String> myStream = in.lines();
            return myStream;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return Stream.empty();
        }
    }
    private static void countCharacters(String filename) {
        try (Stream<String> myStream = createStreamFromFile(filename)) {
            long cnt = myStream.flatMapToInt(String::chars).count();
            System.out.println("number of chars: " + cnt);
        }
    }

    public static void main(String... args) {
        countCharacters(workingDirectory+"text.txt");
        testClosedReader();
    }
}