
package app.controllers;

import app.entities.*;
import app.exceptions.DatabaseException;
import app.persistence.ConnectionPool;
import app.persistence.OrderLineMapper;
import app.persistence.OrderMapper;
import app.persistence.UserMapper;
import io.javalin.Javalin;
import io.javalin.http.Context;

import java.util.List;

public class OrderController {


    public static void addRoutes(Javalin app, ConnectionPool connectionPool) {
        app.post("/checkout", ctx -> checkout(ctx, connectionPool));
        app.post("/pay", ctx -> pay(ctx, connectionPool));
        //app.post("/deleteOrder", ctx -> deleteOrder(ctx, connectionPool));
    }

    private static void pay(Context ctx, ConnectionPool connectionPool) {
        Cart cart = ctx.sessionAttribute("cart");
        int price = cart.calculatePrice();
        User user = ctx.sessionAttribute("currentUser");
        int balance = user.getBalance();
        try {
            if (balance >= price) {
                Order order = OrderMapper.createOrder(ctx.sessionAttribute("currentUser"), price, true, connectionPool);
                for (OrderLine o : cart.getOrderLines()) {
                    OrderLineMapper.createOrderLine(order.getOrderId(), o.getBottomId(), o.getToppingId(), o.getPrice(), o.getAmount(), connectionPool);
                }
                updateBalance(ctx, connectionPool);

                ctx.sessionAttribute("cart", new Cart());
                ctx.render("receipt.html");
            } else {
                ctx.attribute("message", "Something went wrong, see if you have the necessary funds to complete the transaction");
                ctx.attribute("bottomList", ctx.sessionAttribute("bottomList"));
                ctx.attribute("toppingList", ctx.sessionAttribute("toppingList"));
                ctx.render("order.html");
            }

        } catch (DatabaseException e) {
            ctx.attribute("message", "Something went wrong. Try again.");
            ctx.render("paypage.html");
        }

    }

    private static void checkout(Context ctx, ConnectionPool connectionPool) {

        //Måske vis brugerens balance og pris for order

        ctx.render("paypage.html");
    }

    private static void updateBalance(Context ctx, ConnectionPool connectionPool) throws DatabaseException {
        Cart cart;
        cart = ctx.sessionAttribute("cart");
        User user;
        user = ctx.sessionAttribute("currentUser");
        if (user.getBalance() >= cart.calculatePrice()) {
            int newBalance = user.getBalance() - cart.calculatePrice();
            int userId = user.getUserId();
            UserMapper.updateBalance(userId, newBalance, connectionPool);
            user.setBalance(newBalance);
            ctx.sessionAttribute("currentUser",user);
        } else {
            ctx.attribute("message", "Something went wrong, kindly see if you have the necessary funds to complete the transaction");
            ctx.render("paypage.html");
        }
    }

    /*
        private static void deleteOrder(Context ctx, ConnectionPool connectionPool) {
        Order order = ctx.sessionAttribute("currentUser");
        try {
            int orderId = Integer.parseInt(ctx.formParam("orderId"));
            OrderMapper.deleteOrder(orderId, connectionPool);
            ctx.render("admin.html");
        } catch (DatabaseException | NumberFormatException e) {
            ctx.attribute("message", e.getMessage());
            ctx.render("admin.html");
        }
    }
     */

}

