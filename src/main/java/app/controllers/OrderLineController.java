
package app.controllers;

import app.entities.*;

import app.persistence.ConnectionPool;
import io.javalin.Javalin;
import io.javalin.http.Context;

public class OrderLineController {

    public static void addRoutes(Javalin app, ConnectionPool connectionPool) {
        app.post("addtocart", ctx -> addToCart(ctx, connectionPool));
    }

    private static void addToCart(Context ctx, ConnectionPool connectionPool) {
        Cart cart = ctx.sessionAttribute("cart");
        if(cart==null){
            ctx.sessionAttribute("cart",new Cart());
        }

        int bottomId = Integer.parseInt(ctx.formParam("selectedBottomId"));
        int toppingId = Integer.parseInt(ctx.formParam("selectedToppingId"));
        int amount = Integer.parseInt(ctx.formParam("amount"));

        System.out.println(amount);
        System.out.println(bottomId);
        System.out.println(toppingId);

        // cart.addToCart(new OrderLine(price, bottom, topping, amount));
        ctx.sessionAttribute("cart",cart);

        //Muligvis ikke nødvendigt.
        ctx.render("order.html");

    }

    private void createOrderLine(Cart cart, Context ctx, ConnectionPool connectionPool) {

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

    private void deleteOrderLine(OrderLine orderLine) {

    }

}

