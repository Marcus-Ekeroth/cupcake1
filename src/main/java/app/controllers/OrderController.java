
package app.controllers;

import app.entities.*;
import app.exceptions.DatabaseException;
import app.persistence.ConnectionPool;
import app.persistence.OrderLineMapper;
import app.persistence.OrderMapper;
import io.javalin.Javalin;
import io.javalin.http.Context;

import java.util.List;

public class OrderController {



    public static void addRoutes(Javalin app, ConnectionPool connectionPool)
    {
        app.post("/deleteOrder", ctx-> deleteOrder(ctx, connectionPool));
        app.post("/checkout", ctx-> checkout(ctx, connectionPool));
        app.post("/pay", ctx-> pay(ctx, connectionPool));
    }

    private static void pay(Context ctx, ConnectionPool connectionPool) {
        Cart cart = ctx.sessionAttribute("cart");
        int price = cart.calculatePrice();
        try {
            Order order = OrderMapper.createOrder(ctx.sessionAttribute("currentUser"), price, true, connectionPool);
            for (OrderLine o: cart.getOrderLines()) {
                OrderLineMapper.createOrderLine(order.getOrderId(), o.getBottomId(), o.getToppingId(), o.getPrice(), o.getAmount(), connectionPool);
            }
            ctx.render("receipt.html");
        } catch (DatabaseException e) {
            ctx.attribute("message", "Noget gik galt. Prøv evt. igen.");
            ctx.render("paypage.html");
        }

    }

    private static void checkout(Context ctx, ConnectionPool connectionPool) {
        ctx.render("paypage.html");
    }

    private static void deleteOrder(Context ctx, ConnectionPool connectionPool)
    {
        Order order = ctx.sessionAttribute("currentUser");
        try
        {
            int orderId = Integer.parseInt(ctx.formParam("orderId"));
            OrderMapper.deleteOrder(orderId, connectionPool);
            ctx.render("admin.html");
        }
        catch (DatabaseException | NumberFormatException e)
        {
            ctx.attribute("message", e.getMessage());
            ctx.render("admin.html");
        }
    }
    private void createOrder(Context ctx, ConnectionPool connectionPool){
        User user =ctx.sessionAttribute("currentUser");
    }


}

