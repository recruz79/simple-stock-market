package simplestock.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import simplestock.model.Trade;
import simplestock.service.SimpleStockService;

@RestController
public class SimpleStockController {

    @Autowired
    SimpleStockService simpleStockService;

    @PostMapping(path = "/trade")
    @ResponseStatus(HttpStatus.OK)
    public String processSingleTrade(@RequestBody Trade trade) {
        simpleStockService.addTradeList(trade);
        return "Result successed!";
    }

    @GetMapping(path = "/stockPrice")
    @ResponseStatus(HttpStatus.OK)
    public String getStockPrice(@RequestParam String stockName) throws Exception {
        return simpleStockService.getStockPrice(stockName).toString();
    }

    @GetMapping(path = "/peRatio")
    @ResponseStatus(HttpStatus.OK)
    public String getPeRatio(@RequestParam String stockName, @RequestParam Double price) throws Exception {
        return simpleStockService.getPERatio(stockName, price).toString();
    }

    @GetMapping(path = "/marketAllShareIndex")
    @ResponseStatus(HttpStatus.OK)
    public String getMarketAllShareIndex() throws Exception {
        return simpleStockService.getMarketAllShareIndex().toString();
    }
}