package simplestock.controller;

import org.springframework.beans.factory.annotation.Autowired;
import simplestock.model.StockInformation;
import simplestock.model.Trade;
import simplestock.model.TradeType;
import simplestock.service.SimpleStockRepository;
import simplestock.service.SimpleStockService;

import java.math.BigDecimal;
import java.time.Instant;

public class BaseTest {

    @Autowired
    SimpleStockRepository simpleStockRepository;

    @Autowired
    SimpleStockService simpleStockService;

    protected void generateTradeInformation() {
        simpleStockService.addTradeTransaction(new Trade("TEA", Instant.now(), 102, new BigDecimal(1)), TradeType.SELL);
        simpleStockService.addTradeTransaction(new Trade("TEA", Instant.now(), 102, new BigDecimal(2)), TradeType.SELL);
        simpleStockService.addTradeTransaction(new Trade("TEA", Instant.now(), 102, new BigDecimal(3)), TradeType.SELL);

        simpleStockService.addTradeTransaction(new Trade("POP", Instant.now(), 102, new BigDecimal(4)), TradeType.BUY);
        simpleStockService.addTradeTransaction(new Trade("POP", Instant.now(), 102, new BigDecimal(5)), TradeType.BUY);
        simpleStockService.addTradeTransaction(new Trade("POP", Instant.now(), 102, new BigDecimal(6)), TradeType.BUY);
    }

    protected void generateStockInformation() {
        simpleStockRepository.getStockInformationChart().put("TEA", new StockInformation("TEA", "Common", new BigDecimal(0), new BigDecimal(0), new BigDecimal(100)));
        simpleStockRepository.getStockInformationChart().put("TEA", new StockInformation("TEA", "Common", new BigDecimal(0), new BigDecimal(0), new BigDecimal(100)));
        simpleStockRepository.getStockInformationChart().put("POP", new StockInformation("POP", "Common", new BigDecimal(8), new BigDecimal(0), new BigDecimal(100)));
        simpleStockRepository.getStockInformationChart().put("ALE", new StockInformation("ALE", "Common", new BigDecimal(23), new BigDecimal(0), new BigDecimal(100)));
        simpleStockRepository.getStockInformationChart().put("GIN", new StockInformation("GIN", "Preferred", new BigDecimal(8), new BigDecimal(2), new BigDecimal(100)));
        simpleStockRepository.getStockInformationChart().put("JOE", new StockInformation("JOE", "Common", new BigDecimal(13), new BigDecimal(0), new BigDecimal(250)));
    }

    protected void cleanUpTradeInformation() {
        simpleStockRepository.getMarketTradeList().clear();
    }

    protected void cleanUpStockInformation() {
        simpleStockRepository.getMarketTradeList().clear();
    }
}
