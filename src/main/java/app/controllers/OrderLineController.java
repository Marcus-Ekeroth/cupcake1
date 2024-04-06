
package app.controllers;

import app.entities.*;

import app.persistence.BottomMapper;
import app.persistence.ConnectionPool;
import app.persistence.ToppingMapper;
import io.javalin.Javalin;
import io.javalin.http.Context;

import java.util.List;

public class OrderLineController {

    public static void addRoutes(Javalin app, ConnectionPool connectionPool) {
        app.post("addtocart", ctx -> addToCart(ctx, connectionPool));
        app.post("gotocart", ctx -> goToCart(ctx, connectionPool));
    }

    private static void goToCart(Context ctx, ConnectionPool connectionPool) {
        Cart cart = ctx.sessionAttribute("cart");

        ctx.attribute("orderlineList",cart.getOrderLines());
        ctx.attribute("totalPrice", cart.calculatePrice());


        ctx.render("cart.html");
    }

    private static void addToCart(Context ctx, ConnectionPool connectionPool) {
        Cart cart = ctx.sessionAttribute("cart");
        if(cart==null){
            ctx.sessionAttribute("cart",new Cart());
            cart = ctx.sessionAttribute("cart");
        }

        int bottomId = Integer.parseInt(ctx.formParam("selectedBottomId"));
        int toppingId = Integer.parseInt(ctx.formParam("selectedToppingId"));
        int amount = Integer.parseInt(ctx.formParam("amount"));


        Bottom pickedBottom = bottomById(bottomId, ctx.sessionAttribute("bottomList"));
        Topping pickedTopping = toppingById(toppingId, ctx.sessionAttribute("toppingList"));

        int price = (pickedBottom.getBottomPrice()+pickedTopping.getToppingPrice())*amount;

        cart.addToCart(new OrderLine(price, bottomId, toppingId, amount, pickedBottom.getBottomName(), pickedTopping.getToppingName()));
        ctx.sessionAttribute("cart",cart);


        ctx.attribute("bottomList", ctx.sessionAttribute("bottomList"));
        ctx.attribute("toppingList", ctx.sessionAttribute("toppingList"));
        ctx.render("order.html");

    }

    private static Bottom bottomById(int id, List<Bottom> bottomList){
        Bottom bottom = null;
        for (Bottom b : bottomList) {
            if(b.getBottomId()==id){
                bottom = b;
            }
        }
        return bottom;
    }

    private static Topping toppingById(int id, List<Topping> toppingList){
        Topping topping = null;
        for (Topping t : toppingList) {
            if(t.getToppingId()==id){
                topping = t;
            }
        }
        return topping;
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

