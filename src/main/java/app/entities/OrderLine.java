package app.entities;

public class OrderLine {
    private int userId;
    private int price;
    private String topping;
    private String bottom;
    private int orderLineId;
    private int orderId;

    public OrderLine(int userId, int price, String topping, String bottom, int orderLineId, int orderId) {
        this.userId = userId;
        this.price = price;
        this.topping = topping;
        this.bottom = bottom;
        this.orderLineId = orderLineId;
        this.orderId = orderId;
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

    public String getTopping() {
        return topping;
    }

    public void setTopping(String topping) {
        this.topping = topping;
    }

    public String getBottom() {
        return bottom;
    }

    public void setBottom(String bottom) {
        this.bottom = bottom;
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
                "userId=" + userId +
                ", price=" + price +
                ", topping='" + topping + '\'' +
                ", bottom='" + bottom + '\'' +
                ", orderLineId=" + orderLineId +
                ", orderId=" + orderId +
                '}';
    }
}
