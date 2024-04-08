package app.entities;

import java.util.LinkedList;
import java.util.List;

public class OrderLine {
    private int amount;
    private int price;
    private int toppingId;
    private int bottomId;
    private int orderLineId;
    private int orderId;
    public String bottomName;
    public String toppingName;



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

    public static List<OrderLine> convertOrderlineList(List<OrderLine> orderLineList,  List<Bottom> bottomList,  List<Topping> toppingList){
        List<OrderLine> orderLines = new LinkedList<>();

        for (OrderLine o: orderLineList) {
            int price = o.getPrice();
            int bottomId = o.getBottomId();
            int toppingId = o.getToppingId();
            int amount = o.getAmount();
            String bottomName = Bottom.bottomById(bottomId, bottomList).getBottomName();
            String toppingName = Topping.toppingById(toppingId, toppingList).getToppingName();

            orderLines.add(new OrderLine(price, bottomId, toppingId, amount, bottomName, toppingName));
        }

        return orderLines;
    }

    public int getAmount() {
        return amount;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getBottomId() {
        return bottomId;
    }

    public int getToppingId() {
        return toppingId;
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