package simplestock.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import simplestock.model.Trade;
import simplestock.service.SimpleStockService;

@RestController
public class SimpleStockController {

    SimpleStockService simpleStockService;

    @PostMapping(path = "/trade", consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public String processSingleTrade(@RequestBody Trade trade) {
        simpleStockService.addTradeList(trade);
        return "Result successed!";
    }

    @GetMapping(path = "/stockPrice", consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public String getStockPrice(@RequestParam String stockName, @RequestParam Double price) throws Exception {
        return simpleStockService.getPERatio(stockName, price).toString();
    }

    @GetMapping(path = "/peRatio", consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public String getPeRatio(@RequestParam String stockName, @RequestParam Double price) throws Exception {
        return simpleStockService.getPERatio(stockName, price).toString();
    }

    @GetMapping(path = "/marketAllShareIndex", consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public String getMarketAllShareIndex(@RequestParam Trade trade) {
        return simpleStockService.getMarketAllShareIndex().toString();
    }
}