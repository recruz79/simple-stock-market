package simplestock.service;

import org.springframework.stereotype.Service;
import simplestock.model.StockInformation;
import simplestock.model.Trade;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Service
public class SimpleStockService {

    public static final long FIVE_MINUTES = 5 * 60 * 1000;

    Map<String, ArrayList<Trade>> marketTradeList = new HashMap<>();
    Map<String, StockInformation> stockInformationChart;

    private Map<String, StockInformation> getStockInformationChart() {
        if (stockInformationChart == null) {
            stockInformationChart = new HashMap<>();
            stockInformationChart.put("TEA", new StockInformation("TEA", "Common", 0L, 0L, 100L));
            stockInformationChart.put("POP", new StockInformation("POP", "Common", 8L, 0L, 100L));
            stockInformationChart.put("ALE", new StockInformation("ALE", "Common", 23L, 0L, 60L));
            stockInformationChart.put("GIN", new StockInformation("GIN", "Preferred", 8L, 2L, 100L));
            stockInformationChart.put("JOE", new StockInformation("JOE", "Common", 13L, 0L, 250L));
        }
        return stockInformationChart;
    }

    public Long getDividendYield(String stockName, Long price) throws Exception {
        StockInformation stockInformation = stockInformationChart.get(stockName);
        Long dividendYield = 0L;
        if (stockInformation == null) {
            throw new Exception("No stock found");
        }

        if ("Common".equals(stockInformation.getType())) {
            dividendYield = stockInformation.getLastDividend() / price;
        } else if ("Preferred".equals(stockInformation.getType())) {
            dividendYield = stockInformation.getFixedDividen() / price;
        }

        return dividendYield;
    }

    public Long getPERatio(String stockName, Long price) throws Exception {
        Long dividendYield = getDividendYield(stockName, price);
        if(dividendYield == 0) {
            throw new Exception("Could not calculate PE Ratio since dividendYield is zero");
        }

        Long peRatio = price / dividendYield;

        return peRatio;
    }

    public Long getStockPrice(String stockName) throws Exception {
        ArrayList<Trade> tradeList = marketTradeList.get(stockName);
        if (null == tradeList || tradeList.isEmpty()) {
            throw new Exception("Stock list is empty");
        }

        Long fiveMinutesAgo = System.currentTimeMillis() - FIVE_MINUTES;
        Long sumPricePerQuantity = tradeList.stream().filter(o -> o.getTimestamp().getTime() > fiveMinutesAgo)
                .mapToLong(o -> o.getQuantity() * o.getPrice())
                .sum();

        Long sumQuantity = tradeList.stream().filter(o -> o.getTimestamp().getTime() > fiveMinutesAgo)
                .mapToLong(o -> o.getQuantity())
                .sum();

        return sumPricePerQuantity / sumQuantity;
    }

    public Long getGBCEAllShareIndex() {

        marketTradeList.forEach((k, v) -> v.stream().map(o -> o.getPrice()).reduce(0L, (a, b) -> a * b));
        return 0L;
    }

    public void addTradeList(Trade trade) {
        ArrayList tradeList = marketTradeList.get(trade.getStockName());
        if(tradeList == null) {
            tradeList = new ArrayList();
        }

        tradeList.add(trade);
    }

}