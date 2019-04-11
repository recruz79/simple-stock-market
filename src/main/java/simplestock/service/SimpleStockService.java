package simplestock.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import simplestock.model.StockInformation;
import simplestock.model.Trade;

import java.util.ArrayList;

@Service
public class SimpleStockService {

    public static final long FIVE_MINUTES = 5 * 60 * 1000;

    @Autowired
    SimpleStockRepository simpleStockRepository;

    public Double getDividendYield(String stockName, Double price) throws Exception {
        StockInformation stockInformation = simpleStockRepository.getStockInformationChart().get(stockName);
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

    public Double getPERatio(String stockName, Double price) throws Exception {
        Double dividendYield = getDividendYield(stockName, price);
        if (dividendYield == 0) {
            throw new Exception("Could not calculate PE Ratio since dividendYield is zero");
        }

        Double peRatio = price / dividendYield;

        return peRatio;
    }

    public Double getStockPrice(String stockName) throws Exception {
        ArrayList<Trade> tradeList = simpleStockRepository.getMarketTradeList().get(stockName);
        if (null == tradeList || tradeList.isEmpty()) {
            throw new Exception("Stock list is empty");
        }

        Long fiveMinutesAgo = System.currentTimeMillis() - FIVE_MINUTES;
        Double sumPricePerQuantity = tradeList.stream().filter(o -> o.getTimestamp().getTime() > fiveMinutesAgo)
                .mapToDouble(o -> o.getQuantity() * o.getPrice())
                .sum();

        Double sumQuantity = tradeList.stream().filter(o -> o.getTimestamp().getTime() > fiveMinutesAgo)
                .mapToDouble(o -> o.getQuantity())
                .sum();

        return sumPricePerQuantity / sumQuantity;
    }

    public Double getMarketAllShareIndex() {
        Double marketAllShareIndex = 1D;
        Double count = 0D;
        for (String key : simpleStockRepository.getMarketTradeList().keySet()) {
            ArrayList<Trade> stockTradelist = simpleStockRepository.getMarketTradeList().get(key);
            for (Trade trade : stockTradelist) {
                marketAllShareIndex *= trade.getPrice();
                count++;
            }
        }

        return Math.pow(marketAllShareIndex, (1 / count));
    }

    public void addTradeList(Trade trade) {
        ArrayList tradeList = simpleStockRepository.getMarketTradeList().get(trade.getStockName());
        if (tradeList == null) {
            tradeList = new ArrayList();
        }

        tradeList.add(trade);
    }

}