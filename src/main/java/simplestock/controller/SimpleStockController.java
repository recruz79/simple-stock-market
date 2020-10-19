package simplestock.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import simplestock.model.Trade;
import simplestock.model.TradeType;
import simplestock.service.SimpleStockService;

import java.math.BigDecimal;
import java.math.RoundingMode;

@RestController
public class SimpleStockController {

    @Autowired
    SimpleStockService simpleStockService;

    @PostMapping(path = "/trade/buy")
    @ResponseStatus(HttpStatus.OK)
    public String processBuyTrade(@RequestBody Trade trade) {
        simpleStockService.addTradeTransaction(trade, TradeType.BUY);
        return "Result successed!";
    }

    @PostMapping(path = "/trade/sell")
    @ResponseStatus(HttpStatus.OK)
    public String processSellTrade(@RequestBody Trade trade) {
        simpleStockService.addTradeTransaction(trade, TradeType.SELL);
        return "Result successed!";
    }

    @GetMapping(path = "/stockPrice")
    @ResponseStatus(HttpStatus.OK)
    public String getStockPrice(@RequestParam String stockName) throws Exception {
        return simpleStockService.getStockPrice(stockName).setScale(2, RoundingMode.HALF_UP).toString();
    }

    @GetMapping(path = "/peRatio")
    @ResponseStatus(HttpStatus.OK)
    public String getPeRatio(@RequestParam String stockName, @RequestParam BigDecimal price) throws Exception {
        return simpleStockService.getPERatio(stockName, price).setScale(4, RoundingMode.HALF_UP).toString();
    }

    @GetMapping(path = "/marketAllShareIndex")
    @ResponseStatus(HttpStatus.OK)
    public String getMarketAllShareIndex() throws Exception {
        return simpleStockService.getMarketAllShareIndex().setScale(4, RoundingMode.HALF_UP).toString();
    }

    @GetMapping(path = "/test")
    @ResponseStatus(HttpStatus.OK)
    public String getTestAccess() throws Exception {
        return "Test is ok";
    }

}