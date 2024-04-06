package app.entities;

public class OrderLine {
    private int amount;
    private int price;
    private int toppingId;
    private int bottomId;
    private int orderLineId;
    private int orderId;
    private String bottomName;
    private String toppingName;



    public OrderLine(int price, int orderLineId, int orderId, int bottomId, int toppingId, int amount) {
        this.amount = amount;
        this.price = price;
        this.toppingId = toppingId;
        this.bottomId = bottomId;
        this.orderLineId = orderLineId;
        this.orderId = orderId;
    }
    public OrderLine(int price, int bottomId, int toppingId, int amount, String bottomName, String toppingName) {
        this.amount = amount;
        this.price = price;
        this.toppingId = toppingId;
        this.bottomId = bottomId;
        this.bottomName = bottomName;
        this.toppingName = toppingName;
    }


    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getOrderLineId() {
        return orderLineId;
    }

    public int getBottomId() {
        return bottomId;
    }

    public int getToppingId() {
        return toppingId;
    }

    public int getOrderId() {
        return orderId;
    }

    public String getBottomName() {
        return bottomName;
    }

    public String getToppingName() {
        return toppingName;
    }

    @Override
    public String toString() {
        return "OrderLine{" +
                "amount=" + amount +
                ", price=" + price +
                ", orderLineId='" + orderLineId + '\'' +
                ", orderId='" + orderId + '\'' +
                ", toppingId='" + toppingId + '\'' +
                ", bottomId='" + bottomId + '\'' +
                '}';
    }
}