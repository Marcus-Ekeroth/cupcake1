
package app.controllers;

import app.entities.Cart;
import app.entities.Order;
import app.entities.OrderLine;
import app.entities.User;
import app.persistence.ConnectionPool;
import io.javalin.Javalin;
import io.javalin.http.Context;

import java.util.HashMap;

public class OrderLineController {

    public static void addRoutes(Javalin app, ConnectionPool connectionPool)
    {

    }

    private Cart createOrderLine(Cart cart, Context ctx, ConnectionPool connectionPool){

        int orderId = 0;
        ctx.attribute("orderid", orderId);
        int orderLineId = 0;
        ctx.attribute("orderlineid", orderLineId);
        int toppingId = 0;
        ctx.attribute("toppingid", toppingId);
        int bottomId = 0;
        ctx.attribute("bottomid", orderId);
        int price = 0;
        ctx.attribute("price", price);
        int amount = 0;
        ctx.attribute("amount", amount);

        User user = ctx.sessionAttribute("currentUser");

        OrderLine orderLine = new OrderLine(price, orderLineId, orderId, bottomId, toppingId, amount);

        cart.addToCart(orderLine);
        ctx.render("order.html");

        return cart;
    }

    private void deleteOrderLine(OrderLine orderLine){

    }

}

