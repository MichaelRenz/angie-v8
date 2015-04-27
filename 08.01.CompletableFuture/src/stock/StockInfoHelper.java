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

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class StockInfoHelper {

    public static String getStockInfo(String s) {
        try {
            URL yahoofinance = new URL("http://finance.yahoo.com/d/quotes.csv?s="+ s +"&f=nl1c");
            URLConnection yc = yahoofinance.openConnection();
            try (BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()))) {
                String result = in.readLine();
                return result;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    private static double increaseRate(String stockInfo) {
        double increaseRate = 0.0;
        int strlen = stockInfo.length();
        char sign = stockInfo.charAt(strlen-7);
        if (sign != '+' && sign != '-' && sign != ' ')
            System.out.println(">>>>>>>>> PARSING ERROR in util info: "+stockInfo);
        increaseRate = Double.parseDouble(stockInfo.substring(strlen-7,strlen-2));
        return increaseRate;
    }
    public static String maxIncreaseRate(String stockInfo1, String stockInfo2) {
        if (stockInfo1==null && stockInfo2!=null)
            return stockInfo2;
        if (stockInfo2==null && stockInfo1!=null)
            return stockInfo1;
        if (stockInfo1!=null && stockInfo2!=null)
            return increaseRate(stockInfo1)>increaseRate(stockInfo2) ? stockInfo1 : stockInfo2;
        return null;
    }
    private enum MockStockInfo {
        AAPL("\"Apple Inc.\",545.70,\"+4.72 - +0.87%\""),
        ADBE("\"Adobe Systems Inc\",58.455,\"-0.705 - -1.19%\""),
        ALTR("\"Altera Corporatio\",32.08,\"-0.09 - -0.28%\""),
        AMZN("\"Amazon.com, Inc.\",393.60,\"-2.84 - -0.72%\""),
        APCC("\"ASIA PACIFIC ENER\",0.00,\"0.00 - 0.00%\""),
        BIIB("\"Biogen Idec Inc.\",274.295,\"-3.105 - -1.12%\""),
        CSCO("\"Cisco Systems, In\",22.0801,\"+0.1001 - +0.46%\""),
        CTSH("\"Cognizant Technol\",98.19,\"-0.13 - -0.13%\""),
        CTXS("\"Citrix Systems, I\",61.57,\"-0.91 - -1.46%\""),
        DELL("\"Dell Inc.\",13.86,\"0.00 - 0.00%\""),
        EBAY("\"eBay Inc.\",51.85,\"-1.41 - -2.65%\""),
        GOOG("\"Google Inc.\",1116.14,\"+11.14 - +1.01%\""),
        INTC("\"Intel Corporation\",25.52,\"-0.26 - -1.01%\""),
        LBTYA("\"Liberty Global pl\",90.00,\"+1.40 - +1.58%\""),
        MXIM("\"Maxim Integrated\",28.84,\"+0.11 - +0.38%\""),
        MSFT("\"Microsoft Corpora\",36.215,\"-0.695 - -1.88%\""),
        MCHP("\"Microchip Technol\",44.64,\"+0.10 - +0.22%\""),
        ORCL("\"Oracle Corporatio\",37.70,\"+0.08 - +0.21%\""),
        TRMK("\"Trustmark Corpora\",26.58,\"-0.21 - -0.78%\""),
        YHOO("\"Yahoo Inc.\",40.215,\"+0.095 - +0.24%\"");
        private String info;
        private MockStockInfo(String info)  { this.info = info; }
        public String getInfo() {
            try { Thread.sleep(1000); }
            catch (InterruptedException e) { e.printStackTrace(); }
            return info;
        }
    }
    public static String fakeStockInfo(String s) {
        return MockStockInfo.valueOf(s).getInfo();
    }
}
