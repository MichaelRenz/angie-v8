package stock;

import java.util.concurrent.ForkJoinPool;

/**
* Created by Angelika Langer on 08.12.2014.
*/
public class StockInfoFetcher implements ForkJoinPool.ManagedBlocker {
    private final String symbol;
    private volatile String info = null;

    public StockInfoFetcher(String symbol) {
        this.symbol = symbol;
    }
    public boolean block() throws InterruptedException {
        if (info == null)
            info = Stock.getStockInfo(symbol);
        return true;
    }
    public boolean isReleasable() {
        return info != null;
    }
    public String getInfo() {
        return info;
    }
}
