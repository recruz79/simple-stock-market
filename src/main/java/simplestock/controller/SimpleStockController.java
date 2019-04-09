package simplestock.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import simplestock.model.Trade;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class SimpleStockController {

    Map<String, ArrayList<Trade>> marketTradeList = new HashMap<String, ArrayList<Trade>>();

    @PostMapping(path = "/trade", consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public String processSingleTrade(@RequestBody Trade trade) {
        ArrayList tradeList = marketTradeList.get(trade.getStockName());
        addTradeList(tradeList, trade);

        return "Result successed!";
    }

    private void addTradeList(List tradeList, Trade trade) {
        if(tradeList == null) {
            tradeList = new ArrayList();
        }

        tradeList.add(trade);
    }

}