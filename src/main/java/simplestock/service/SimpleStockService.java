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
            stockInformationChart.put("TEA", new StockInformation("TEA", "Common", 0D, 0D, 100D));
            stockInformationChart.put("POP", new StockInformation("POP", "Common", 8D, 0D, 100D));
            stockInformationChart.put("ALE", new StockInformation("ALE", "Common", 23D, 0D, 60D));
            stockInformationChart.put("GIN", new StockInformation("GIN", "Preferred", 8D, 2D, 100D));
            stockInformationChart.put("JOE", new StockInformation("JOE", "Common", 13D, 0D, 250D));
        }
        return stockInformationChart;
    }

    public Double getDividendYield(String stockName, Long price) throws Exception {
        StockInformation stockInformation = stockInformationChart.get(stockName);
        Double dividendYield = 0D;
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

    public Double getPERatio(String stockName, Long price) throws Exception {
        Double dividendYield = getDividendYield(stockName, price);
        if(dividendYield == 0) {
            throw new Exception("Could not calculate PE Ratio since dividendYield is zero");
        }

        Double peRatio = price / dividendYield;

        return peRatio;
    }

    public Double getStockPrice(String stockName) throws Exception {
        ArrayList<Trade> tradeList = marketTradeList.get(stockName);
        if (null == tradeList || tradeList.isEmpty()) {
            throw new Exception("Stock list is empty");
        }

        Long fiveMinutesAgo = System.currentTimeMillis() - FIVE_MINUTES;
        Double sumPricePerQuantity = tradeList.stream().filter(o -> o.getTimestamp().getTime() > fiveMinutesAgo)
                .mapToDouble(o -> o.getQuantity() * o.getPrice())
                .sum();

        Long sumQuantity = tradeList.stream().filter(o -> o.getTimestamp().getTime() > fiveMinutesAgo)
                .mapToLong(o -> o.getQuantity())
                .sum();

        return sumPricePerQuantity / sumQuantity;
    }

    public Double getGBCEAllShareIndex() {Double BGCBEallShareIndex = 1D;
        Double count = 0D;
        for(String key : marketTradeList.keySet()) {
            ArrayList<Trade> stockTradelist = marketTradeList.get(key);
            for(Trade trade : stockTradelist) {
                BGCBEallShareIndex *= trade.getPrice();
                count++;
            }
        }

        return Math.pow(BGCBEallShareIndex.doubleValue(), (1/count));
    }

    public void addTradeList(Trade trade) {
        ArrayList tradeList = marketTradeList.get(trade.getStockName());
        if(tradeList == null) {
            tradeList = new ArrayList();
        }

        tradeList.add(trade);
    }

}