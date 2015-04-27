package stock;

/**
* Created by Angelika Langer on 08.12.2014.
*/
public class StockData {
    private String symbol;
    private String price;
    private float increase;
    public StockData(String symbol, String price, float increase)    {
        this.symbol = symbol;
        this.price = price;
        this.increase = increase;
    }
    public float getChange() {
          return increase;
    }

    @Override
    public String toString() {
        return "StockData{" +
                "symbol='" + symbol + '\'' +
                ", price='" + price + '\'' +
                ", increase=" + increase +
                '}';
    }
}
