package app.entities;

public class OrderLine {
    private int amount;
    private int price;
    private int toppingId;
    private int bottomId;
    private int orderLineId;
    private int orderId;

    public OrderLine(int price, int orderLineId, int orderId, int bottomId, int toppingId, int amount) {
        this.amount = amount;
        this.price = price;
        this.toppingId = toppingId;
        this.bottomId = bottomId;
        this.orderLineId = orderLineId;
        this.orderId = orderId;
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

    public int getToppingId() {
        return toppingId;
    }

    public void setToppingId(int toppingId) {
        this.toppingId = toppingId;
    }

    public int getBottomId() {
        return bottomId;
    }

    public void setBottomId(int bottomId) {
        this.bottomId = bottomId;
    }

    public int getOrderLineId() {
        return orderLineId;
    }

    public void setOrderLineId(int orderLineId) {
        this.orderLineId = orderLineId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    @Override
    public String toString() {
        return "OrderLine{" +
                "amount=" + amount +
                ", price=" + price +
                ", topping='" + toppingId + '\'' +
                ", bottom='" + bottomId + '\'' +
                ", orderLineId=" + orderLineId +
                ", orderId=" + orderId +
                '}';
    }
}