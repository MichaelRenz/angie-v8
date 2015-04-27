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
package test;

import stock.StockInfo;

public class Test {
    private static String[] stockSymbols =
          { "AAPL", "ADBE", "ALTR", "AMZN", "APCC",
            "BIIB", "CSCO", "CTSH", "CTXS", "DELL",
            "EBAY", "GOOG", "INTC", "LBTYA", "MXIM",
            "MSFT", "MCHP", "ORCL", "TRMK", "YHOO" };

    public static void main(String... args) throws Exception {
        StockInfo.setUp();
        try {
          System.out.println("\n************** CALLBACK-BASED SOLUTION ******************\n");
          for (String symbol:stockSymbols) {
               StockInfo.get(symbol, info -> System.out.println(symbol + ": " + info));
          }
        } catch (Throwable e) {
            e.printStackTrace();
        }
        System.out.println("\n************** RE-WRITTEN SOLUTION ******************\n");
        for (String symbol:stockSymbols) {
            StockInfo.adaptedGet(symbol)
                    .thenAccept(info -> System.out.println(symbol + ": " + info));
        }

        StockInfo.printStockInfoWithMaximumIncreaseRate(stockSymbols);
        StockInfo.shutDown();
    }
}
