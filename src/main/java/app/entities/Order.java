package app.entities;

public class Order {
    private int orderId;
    private boolean paid;
    private int userId;
    private int price;

    public Order(int orderId, int price, boolean paid, int userId) {
        this.orderId = orderId;
        this.paid = paid;
        this.userId = userId;
        this.price = price;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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
                ", paid=" + paid +
                ", userId=" + userId +
                ", price=" + price +
                '}';
    }
}