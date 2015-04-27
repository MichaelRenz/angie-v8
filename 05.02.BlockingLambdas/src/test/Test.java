package test;

import stock.Stock;
import stock.StockInfoFetcher;

import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

/**
 * Created by Angelika Langer on 08.12.2014.
 */
public class Test {
    private static String[] stockSymbols = {"GOOG", "AAPL", "MSFT", "YHOO", "TWTR", "FB", "AMZN", "ORCL"};

    public static void main(String... args) {

        //  Initial version that clogs the fork-join pool.
        Arrays.stream(stockSymbols)
                .parallel()
                .map(s -> Stock.getStockInfo(s))
                .peek(s -> System.out.println(Thread.currentThread().getName() + ": " + s))
                .map(s -> Stock.createStockData(s))
                .filter(s -> s != null)
                .reduce((s1, s2) -> s1.getChange() > s2.getChange() ? s1 : s2)
                .ifPresent(s -> System.out.println(s));
        System.out.println();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //  Alternative version with a managed blocker.

        // ... to be done ...
    }
}
