package simplestock.model;

import java.sql.Timestamp;

public class Trade {

    String stockName;
    Timestamp timestamp;
    Integer quantity;
    TradeType tradeType;
    Long price;
    Long settledAmount;

    public Trade() {
    }

    public Trade(String stockName, Timestamp timestamp, Integer quantity, TradeType tradeType, Long price) {
        this.stockName = stockName;
        this.timestamp = timestamp;
        this.quantity = quantity;
        this.tradeType = tradeType;
        this.price = price;
        this.settledAmount = price * quantity;
    }

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public TradeType getTradeType() {
        return tradeType;
    }

    public void setTradeType(TradeType tradeType) {
        this.tradeType = tradeType;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Long getSettledAmount() {
        return settledAmount;
    }

    public void setSettledAmount(Long settledAmount) {
        this.settledAmount = settledAmount;
    }
}
