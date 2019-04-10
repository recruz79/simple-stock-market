package simplestock.model;

public class StockInformation {

    String stockName;
    String type;
    Long lastDividend;
    Long fixedDividen;
    Long parValue;

    public StockInformation() {
    }

    public StockInformation(String stockName, String type, Long lastDividend, Long fixedDividen, Long parValue) {
        this.stockName = stockName;
        this.type = type;
        this.lastDividend = lastDividend;
        this.fixedDividen = fixedDividen;
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

    public Long getLastDividend() {
        return lastDividend;
    }

    public void setLastDividend(Long lastDividend) {
        this.lastDividend = lastDividend;
    }

    public Long getFixedDividen() {
        return fixedDividen;
    }

    public void setFixedDividen(Long fixedDividen) {
        this.fixedDividen = fixedDividen;
    }

    public Long getParValue() {
        return parValue;
    }

    public void setParValue(Long parValue) {
        this.parValue = parValue;
    }
}
