package test;

import stock.Stock;
import stock.StockInfoFetcher;

import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.stream.Stream;

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
        final Function<String, String> mapper = s ->  {
            StockInfoFetcher infoFetcher = new StockInfoFetcher(s);
            try {
                ForkJoinPool.commonPool().managedBlock(infoFetcher);
            } catch (Throwable e) {
                return null;
            }
            return infoFetcher.getInfo();
        };
        Arrays.stream(stockSymbols)
                .parallel()
                .map(mapper)
                .peek(d -> System.out.println(Thread.currentThread().getName() + ": " + d))
                .map(s -> Stock.createStockData(s))
                .filter(y -> y != null)
                .reduce((s1, s2) -> s1.getChange() > s2.getChange() ? s1 : s2)
                .ifPresent(x -> System.out.println(x));
        System.out.println();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //  Yet another version that simply uses its own fork-join pool with a higher number of pool threads (instead of the common pool).
        ForkJoinPool myPool = new ForkJoinPool(stockSymbols.length-1);
        myPool.submit(()->Arrays.stream(stockSymbols)
                .parallel()
                .map(t -> Stock.getStockInfo(t))
                .peek(d -> System.out.println(Thread.currentThread().getName() + ": " + d))
                .map(s -> Stock.createStockData(s))
                .filter(y -> y != null)
                .reduce((s1, s2) -> s1.getChange() > s2.getChange() ? s1 : s2)
                .ifPresent(x -> System.out.println(x)));
        myPool.awaitQuiescence(30, TimeUnit.SECONDS);
    }
}
