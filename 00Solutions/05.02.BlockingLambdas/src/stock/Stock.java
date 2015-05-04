package stock;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

/**
 * Created by Angelika Langer on 08.12.2014.
 */
public class Stock {

    public static String getStockInfo(String s) {
        try {
            URL yahoofinance = new URL("http://finance.yahoo.com/d/quotes.csv?s="+ s +"&f=sl1c");

            URLConnection yc = yahoofinance.openConnection();

            try (BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()))) {

                String result = in.readLine();
                return result;
            }
        } catch (Exception e) { return null; }
    }
    public static StockData createStockData(String data) {
        try {
            String[] result = data.split(",", 0);

            return new StockData(
                    result[0].substring(1,result[0].length()-1),
                    result[1],
                    Float.parseFloat(result[2]
                            .substring(result[2].length()-7, result[2].length()-2)));
        } catch (Exception e) { return null; }
    }

}
