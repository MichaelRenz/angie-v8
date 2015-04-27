package test;

import user.NumberRange;

import java.util.Arrays;

public final class
        Test {
    public static void main(String[] args) throws InterruptedException {
        test(new NumberRange());
        testEH();
    }
    private static void test(final NumberRange range) throws InterruptedException {
        System.out.println("+++++++++++++++++++++++++++++++++++++");
        System.out.println("++++++++++++ Testing: "+range.getClass());
        System.out.println("++++++++++++ started ...");
        System.out.println("+++++++++++++++++++++++++++++++++++++");

        range.setUpper(10);
        range.setLower(5);


        Runnable setterLower = new Runnable() {
            public void run() {
                int i = 8;
                for(;;) {
                    try {
                        if (Thread.currentThread().isInterrupted()) return;
//                        System.out.println(Thread.currentThread().getName()+": bef "+range);
                        range.setLower(i);
//                        System.out.println(Thread.currentThread().getName()+": aft "+range);
                    } catch (IllegalArgumentException e) {
//                        System.out.println(Thread.currentThread().getName()+": "+e.getMessage());
                    }
                }
            }
        };

        Runnable setterUpper = new Runnable() {
            public void run() {
                int i = 7;
                for(;;) {
                    if (Thread.currentThread().isInterrupted()) return;
                    try {
//                        System.out.println(Thread.currentThread().getName()+": bef "+range);
                        range.setUpper(i);
//                        System.out.println(Thread.currentThread().getName()+": aft "+range);
                    } catch (IllegalArgumentException e) {
//                        System.out.println(Thread.currentThread().getName()+": "+e.getMessage());
                    }
                }
            }
        };

        Runnable watchDog = new Runnable() {
            public void run() {
                int i = 7;
                for(;;) {
                    if (Thread.currentThread().isInterrupted()) return;
                    int[] rng = range.getRange();
                    if (rng[0]>rng[1])
                        System.err.println(Thread.currentThread().getName()+": "
                                +"broken range "+ Arrays.toString(rng));
                    System.out.println(Thread.currentThread().getName()+": "
                            +i+" is in range "+Arrays.toString(rng)+": "+(rng[0]<=i&&i<=rng[1]));
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        return;
                    }
                }
            }
        };

        Thread wd = new Thread(watchDog,"watchdog");
        Thread sl = new Thread(setterLower,"set lower");
        Thread su = new Thread(setterUpper,"set upper");
        wd.start();
        sl.start();
        su.start();
        Thread.sleep(50);
        su.interrupt(); sl.interrupt();wd.interrupt();
    }
    private static void testEH()  {
        try {
            NumberRange invalidRange = new NumberRange(1,-1);
            System.out.println(Thread.currentThread().getName()+": "
                    +"range "+invalidRange+" is valid: "+invalidRange.isValid());
        } catch (NumberRange.BrokenRangeException e) {
            e.printStackTrace(System.out);
        } catch (Exception e) {
            System.err.println(Thread.currentThread().getName()+": "
                    +"Oops, we are not supposed to end up here ...");
            e.printStackTrace();
        }
    }
}
