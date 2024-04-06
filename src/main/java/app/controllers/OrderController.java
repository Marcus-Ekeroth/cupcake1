
package app.controllers;

import app.entities.Order;
import app.exceptions.DatabaseException;
import app.persistence.ConnectionPool;
import app.persistence.OrderMapper;
import io.javalin.Javalin;
import io.javalin.http.Context;
import app.entities.User;

import java.util.List;

public class OrderController {



    public static void addRoutes(Javalin app, ConnectionPool connectionPool)
    {
        app.post("/deleteOrder", ctx-> deleteOrder(ctx, connectionPool));
        app.post("/checkout", ctx-> checkout(ctx, connectionPool));
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

