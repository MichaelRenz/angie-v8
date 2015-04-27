/*
  Based on course material for "Concurrent Java", a seminar
  prepared and owned by Angelika Langer & Klaus Kreft, January 2014
  contact: http://www.AngelikaLanger.com

  © Copyright 1995 - 2014 by Angelika Langer & Klaus Kreft.
  Permission to use, copy, and modify this software for any non-profit
  purpose is hereby granted to attendants of the above mentioned seminar
  without fee, provided that the above copyright notice appears in all
  copies.  Angelika Langer and Klaus Kreft make no representations about
  the suitability of this software for any purpose.  It is provided
  "as is" without express or implied warranty.
*/
package stock;

import java.util.concurrent.*;

public class StockInfo {
    public interface Callback<T> {
        void use(T arg);
    }
    private static ExecutorService pool;

    public static void setUp() {
        pool = Executors.newFixedThreadPool(4);
    }
    public static void get(String stockSymbol, Callback<String> callback)  {
        pool.execute(new Runnable() {
            public void run() {
                callback.use(
                        StockInfoHelper.getStockInfo(stockSymbol)
                        /* if your internet connection does not work,
                         * i.e., the socket connect fails
                         * then use the fake below:
                         */
                        // StockInfoHelper.fakeStockInfo(stockSymbol)
                );
            }
        });
    }
    public static CompletionStage<String> adaptedGet(String stockSymbol)  {
        ... to be done ...
    }
    public static void printStockInfoWithMaximumIncreaseRate(String[] stockSymbols) {
        ... to be done ...
    }
    public static void shutDown() {
        pool.shutdown();
    }
}
