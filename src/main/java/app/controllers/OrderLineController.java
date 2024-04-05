
package app.controllers;

import app.entities.Order;
import app.entities.OrderLine;
import app.entities.User;
import app.persistence.ConnectionPool;
import io.javalin.Javalin;
import io.javalin.http.Context;

public class OrderLineController {

    public static void addRoutes(Javalin app, ConnectionPool connectionPool)
    {

    }

    private OrderLine createOrderLine(Cart cart, Context ctx, ConnectionPool connectionPool){

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

        User user = ctx.sessionAttribute("currentUser");
        //Læg tingene i kurven, derefter render siden
    }

    private void deleteOrderLine(OrderLine orderLine){

    }

}

