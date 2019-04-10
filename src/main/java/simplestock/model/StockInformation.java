package simplestock.model;

public class StockInformation {

    String stockName;
    String type;
    Double lastDividend;
    Double fixedDividen;
    Double parValue;

    public StockInformation() {
    }

    public StockInformation(String stockName, String type, Double lastDividend, Double fixedDividen, Double parValue) {
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

    public Double getLastDividend() {
        return lastDividend;
    }

    public void setLastDividend(Double lastDividend) {
        this.lastDividend = lastDividend;
    }

    public Double getFixedDividen() {
        return fixedDividen;
    }

    public void setFixedDividen(Double fixedDividen) {
        this.fixedDividen = fixedDividen;
    }

    public Double getParValue() {
        return parValue;
    }

    public void setParValue(Double parValue) {
        this.parValue = parValue;
    }
}
