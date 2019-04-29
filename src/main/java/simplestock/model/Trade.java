package simplestock.model;

import java.math.BigDecimal;
import java.time.Instant;

public class Trade {

    String stockName;
    Instant timestamp;
    Integer quantity;
    TradeType tradeType;
    BigDecimal price;
    BigDecimal settledAmount;

    public Trade() {
    }

    public Trade(String stockName, Instant timestamp, Integer quantity, BigDecimal price) {
        this.stockName = stockName;
        this.timestamp = timestamp;
        this.quantity = quantity;
        this.price = price;
        this.settledAmount = price.multiply(new BigDecimal(quantity));
    }

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getSettledAmount() {
        return settledAmount;
    }

    public void setSettledAmount(BigDecimal settledAmount) {
        this.settledAmount = settledAmount;
    }
}
