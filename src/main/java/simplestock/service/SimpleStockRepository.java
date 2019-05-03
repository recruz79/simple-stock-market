package simplestock.service;

import org.springframework.stereotype.Component;
import simplestock.model.StockInformation;
import simplestock.model.Trade;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class SimpleStockRepository {

    private Map<String, StockInformation> stockInformationChart;
    private Map<String, List<Trade>> marketTradeList;

    public Map<String, List<Trade>> getMarketTradeList() {
        if (marketTradeList == null) {
            marketTradeList = new ConcurrentHashMap<>();
        }
        return marketTradeList;
    }

    public Map<String, StockInformation> getStockInformationChart() {
        if (stockInformationChart == null) {
            stockInformationChart = new ConcurrentHashMap<>();
        }
        return stockInformationChart;
    }
}
