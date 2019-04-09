package simplestock.model;

public class Trade {


    String stock;
    String price;

    public Trade() {
    }

    public Trade(String stock, String price) {
        this.stock = stock;
        this.price = price;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
