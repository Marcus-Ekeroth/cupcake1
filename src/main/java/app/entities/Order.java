package app.entities;

public class Order {
    private int orderId;

    private int bottomId;
    private int toppingId;
    private int amount;
    private int price;

    public Order(int orderId, int bottomId, int toppingId, int amount, int price) {
        this.orderId = orderId;
        this.bottomId = bottomId;
        this.toppingId = toppingId;
        this.amount = amount;
        this.price = price;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getBottomId() {
        return bottomId;
    }

    public void setBottomId(int bottomId) {
        this.bottomId = bottomId;
    }

    public int getToppingId() {
        return toppingId;
    }

    public void setToppingId(int toppingId) {
        this.toppingId = toppingId;
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

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", bottomId=" + bottomId +
                ", toppingId=" + toppingId +
                ", amount=" + amount +
                ", price=" + price +
                '}';
    }
}
