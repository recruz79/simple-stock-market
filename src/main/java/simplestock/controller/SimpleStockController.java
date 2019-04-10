package simplestock.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
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

}