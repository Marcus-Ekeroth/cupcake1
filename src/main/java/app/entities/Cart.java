package app.entities;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<OrderLine> orderLines = new ArrayList<>();


    private int calculatePrice() {
        int totalPrice = 0;
        for (OrderLine o: orderLines) {
            totalPrice+= o.getPrice();
        }
return totalPrice;
    }
private void addToCart(OrderLine orderline){
    orderLines.add(orderline);

}

}
