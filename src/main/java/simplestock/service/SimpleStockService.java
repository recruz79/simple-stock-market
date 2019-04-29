package simplestock.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import simplestock.exception.MarketException;
import simplestock.model.StockInformation;
import simplestock.model.Trade;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class SimpleStockService {

    public static final long FIVE_MINUTES = 5 * 60 * 1000;
    public static final String COMMON_TYPE = "Common";
    public static final String PREFERRED_TYPE = "Preferred";

    @Autowired
    SimpleStockRepository simpleStockRepository;

    public BigDecimal getDividendYield(String stockName, BigDecimal price) throws MarketException {
        StockInformation stockInformation = simpleStockRepository.getStockInformationChart().get(stockName);
        BigDecimal dividendYield = BigDecimal.ZERO;
        if (stockInformation == null) {
            throw new MarketException("No stock found");
        }

        if (COMMON_TYPE.equals(stockInformation.getType())) {
            dividendYield = stockInformation.getLastDividend().divide(price);
        } else if (PREFERRED_TYPE.equals(stockInformation.getType())) {
            dividendYield = stockInformation.getFixedDividend().divide(price);
        }

        return dividendYield;
    }

    public BigDecimal getPERatio(String stockName, BigDecimal price) throws MarketException {
        BigDecimal dividendYield = getDividendYield(stockName, price);
        if (dividendYield.compareTo(BigDecimal.ZERO) == 0) {
            throw new MarketException("Could not calculate PE Ratio since dividendYield is zero");
        }

        return price.divide(dividendYield);
    }

    public BigDecimal getStockPrice(String stockName) throws MarketException {
        List<Trade> tradeList = simpleStockRepository.getMarketTradeList().get(stockName);
        if (null == tradeList || tradeList.isEmpty()) {
            throw new MarketException("Stock list is empty");
        }

        Long fiveMinutesAgo = System.currentTimeMillis() - FIVE_MINUTES;
        BigDecimal sumPricePerQuantity = tradeList.stream().filter(o -> o.getTimestamp().getTime() > fiveMinutesAgo)
                .map(o -> o.getPrice().multiply(new BigDecimal(o.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        Integer sumQuantity = tradeList.stream().filter(o -> o.getTimestamp().getTime() > fiveMinutesAgo)
                .mapToInt(o -> o.getQuantity())
                .sum();

        return sumPricePerQuantity.divide(new BigDecimal(sumQuantity));
    }

    public BigDecimal getMarketAllShareIndex() {
        AtomicInteger counter = new AtomicInteger(0);
        Integer result = simpleStockRepository.getMarketTradeList().keySet()
                .stream()
                .mapToInt(o -> simpleStockRepository.getMarketTradeList().get(o).stream()
                .mapToInt(x -> {
                    counter.addAndGet(1);
                    return x.getPrice().intValue();
                }).reduce(1, (a, b) -> a * b)).reduce(1, (a, b) -> a * b);
        return new BigDecimal(Math.pow(result, (1 / counter.doubleValue())));
    }

    public void addTradeList(Trade trade) {
        List<Trade> tradeList = simpleStockRepository.getMarketTradeList().get(trade.getStockName());
        if (tradeList == null) {
            tradeList = Collections.synchronizedList(new LinkedList());
            List<Trade> currentTradeList = simpleStockRepository.getMarketTradeList().putIfAbsent(trade.getStockName(), tradeList);
            if(currentTradeList != null) {
                tradeList  = currentTradeList;
            }
        }
        tradeList.add(trade);
    }

}