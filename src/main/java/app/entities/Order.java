package app.entities;

public class Order {
    public int orderId;
    public boolean paid;
    public int userId;
    public int price;

    public Order(int orderId, int price, boolean paid, int userId) {
        this.orderId = orderId;
        this.paid = paid;
        this.userId = userId;
        this.price = price;
    }

    public int getOrderId() {
        return orderId;
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