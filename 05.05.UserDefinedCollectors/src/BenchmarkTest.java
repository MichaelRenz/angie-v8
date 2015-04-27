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

import java.util.ArrayList;
import java.util.List;

public class BenchmarkTest {
    private static class TestCase {
        private Runnable testCase;
        private String description;

        public TestCase(Runnable testCase, String description) {
            this.testCase = testCase;
            this.description = description;
        }
    }

    private static final List<TestCase> testCases = new ArrayList<>();


    private static void warmUp(TestCase tc) {
        System.out.print("start warm-up for " + tc.description + " ");

        for (int n = 0; n < 300; n++) {
            tc.testCase.run();
            if (n % 10 == 0) System.out.print(".");
        }

        System.out.println("finished");
    }

    private static void runTest(TestCase tc) {
        System.gc();

        long start = System.currentTimeMillis();
        for (int n = 0; n < 100; n++) {
            tc.testCase.run();
        }
        long duration = System.currentTimeMillis() - start;

        System.out.println(tc.description + ": " + ((double) duration / 100.0) + " ms");
    }

    public static void addTestCase(Runnable testCase, String description) {
        testCases.add(new TestCase(testCase, description));
    }



    public static void doTest() {
        testCases.forEach(BenchmarkTest::warmUp);
        testCases.forEach(BenchmarkTest::runTest);
    }
}