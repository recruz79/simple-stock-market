package simplestock.service;

import org.springframework.stereotype.Component;
import simplestock.model.StockInformation;
import simplestock.model.Trade;
import simplestock.model.TradeType;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Component
public class SimpleStockRepository {

    private Map<String, StockInformation> stockInformationChart;
    private Map<String, ArrayList<Trade>> marketTradeList;

    public  Map<String, ArrayList<Trade>> getMarketTradeList() {
        if(marketTradeList == null) {
            marketTradeList = new HashMap<>();
            ArrayList<Trade> list1 = new ArrayList();
            list1.add(new Trade("TEA", new Timestamp(System.currentTimeMillis()), 102, TradeType.SELL, 1D));
            list1.add(new Trade("TEA", new Timestamp(System.currentTimeMillis()), 102, TradeType.SELL, 2D));
            list1.add(new Trade("TEA", new Timestamp(System.currentTimeMillis()), 102, TradeType.SELL, 3D));

            ArrayList<Trade> list2 = new ArrayList();
            list2.add(new Trade("POP", new Timestamp(System.currentTimeMillis()), 102, TradeType.SELL, 4D));
            list2.add(new Trade("POP", new Timestamp(System.currentTimeMillis()), 102, TradeType.SELL, 5D));
            list2.add(new Trade("POP", new Timestamp(System.currentTimeMillis()), 102, TradeType.SELL, 6D));

            marketTradeList.put("TEA", list1);
            marketTradeList.put("POP", list2);
        }

        return marketTradeList;
    }

    public Map<String, StockInformation> getStockInformationChart() {
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
}
