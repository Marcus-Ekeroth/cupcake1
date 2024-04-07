package app.entities;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Cart {
    private List<OrderLine> orderLines = new LinkedList<>();


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
    public void removeFromCart(int cartIndex) {
        orderLines.remove(cartIndex);
    }

    public List<OrderLine> getOrderLines() {
        return orderLines;
    }
}
