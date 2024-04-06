package app.entities;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<OrderLine> orderLines = new ArrayList<>();


    public int calculatePrice() {
        int totalPrice = 0;
        for (OrderLine o : orderLines) {
            totalPrice += o.getPrice();
        }
        return totalPrice;
    }

    public void addToCart(OrderLine orderline) {
        orderLines.add(orderline);

    }

    public List<OrderLine> getOrderLines() {
        return orderLines;
    }
}
