package simplestock.model;

import java.math.BigDecimal;

public class StockInformation {

    String stockName;
    String type;
    BigDecimal lastDividend;
    BigDecimal fixedDividend;
    BigDecimal parValue;

    public StockInformation() {
    }

    public StockInformation(String stockName, String type, BigDecimal lastDividend, BigDecimal fixedDividend, BigDecimal parValue) {
        this.stockName = stockName;
        this.type = type;
        this.lastDividend = lastDividend;
        this.fixedDividend = fixedDividend;
        this.parValue = parValue;
    }

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public BigDecimal getLastDividend() {
        return lastDividend;
    }

    public void setLastDividend(BigDecimal lastDividend) {
        this.lastDividend = lastDividend;
    }

    public BigDecimal getFixedDividen() {
        return fixedDividend;
    }

    public void setFixedDividen(BigDecimal fixedDividen) {
        this.fixedDividend = fixedDividen;
    }

    public BigDecimal getParValue() {
        return parValue;
    }

    public void setParValue(BigDecimal parValue) {
        this.parValue = parValue;
    }
}
