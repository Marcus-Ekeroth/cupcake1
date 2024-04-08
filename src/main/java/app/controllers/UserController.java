package app.controllers;

import app.entities.*;
import app.exceptions.DatabaseException;
import app.persistence.*;
import io.javalin.Javalin;
import io.javalin.http.Context;

import java.util.List;


public class UserController {

    public static void addRoutes(Javalin app, ConnectionPool connectionPool) {
        app.post("login", ctx -> login(ctx, connectionPool));
        app.post("loggingon", ctx -> loggingon(ctx, connectionPool));
        app.post("logout", ctx -> logout(ctx, connectionPool));
        app.post("createuserpage", ctx -> ctx.render("createuser.html"));
        app.post("createuser", ctx -> createUser(ctx, connectionPool));
        app.post("updateBalance", ctx -> updatebalance(ctx, connectionPool));
        app.post("deleteOrder", ctx -> deleteOrder(ctx, connectionPool));
        app.post("details", ctx -> details(ctx, connectionPool));
        app.post("admin", ctx -> admin(ctx, connectionPool));

    }


    private static void login(Context ctx, ConnectionPool connectionPool) {
        ctx.render("login.html");
    }

    private static void loggingon(Context ctx, ConnectionPool connectionPool) {

        String email = ctx.formParam("email");
        String password = ctx.formParam("password");

        try {
            User user = UserMapper.login(email, password, connectionPool);
            ctx.sessionAttribute("currentUser", user);

            ctx.sessionAttribute("bottomList", BottomMapper.getAllBottom(connectionPool));
            ctx.sessionAttribute("toppingList", ToppingMapper.getAllTopping(connectionPool));

            ctx.attribute("bottomList", ctx.sessionAttribute("bottomList"));
            ctx.attribute("toppingList", ctx.sessionAttribute("toppingList"));

            if ("admin".equals(user.getRole())) {
                admin(ctx, connectionPool);
            } else {
                haiku(ctx);

                createCart(ctx, connectionPool);

                ctx.render("order.html");
            }

        } catch (DatabaseException e) {

            ctx.attribute("message", e.getMessage());
            ctx.render("index.html");
        }


    }

    private static void createUser(Context ctx, ConnectionPool connectionPool) {
        String password1 = ctx.formParam("password1");
        String password2 = ctx.formParam("password2");
        String email = ctx.formParam("email");

        // Validering af passwords - at de to matcher
        if (password1.equals(password2)) {
            try {
                UserMapper.createuser(password1, email, connectionPool);
                ctx.attribute("message", "User created successfully.");
                ctx.render("index.html");
            } catch (DatabaseException e) {
                ctx.attribute("message", e.getMessage());
                ctx.render("createuser.html");
            }
        } else {
            ctx.attribute("message", "Passwords doesn't match!");
            ctx.render("createuser.html");
        }
    }

    public static void logout(Context ctx, ConnectionPool connectionPool) {
        //saves the order contained in cart in the database
        saveOrder(ctx, connectionPool);

        // Invalidate session
        ctx.req().getSession().invalidate();
        ctx.redirect("/");
    }

    private static void createCart(Context ctx, ConnectionPool connectionPool) {
        Cart cart = ctx.sessionAttribute("cart");
        User user = ctx.sessionAttribute("currentUser");
        if (cart == null) {
            ctx.sessionAttribute("cart", new Cart());
            cart = ctx.sessionAttribute("cart");
        }

        List<OrderLine> orderLines = OrderLineMapper.getAllSavedOrderLines(user.getUserId(), connectionPool);
        orderLines = OrderLine.convertOrderlineList(orderLines, ctx.sessionAttribute("bottomList"), ctx.sessionAttribute("toppingList"));
        for (OrderLine o : orderLines) {
            cart.addToCart(o);
        }
        try {
            if (!cart.getOrderLines().isEmpty()) {
                OrderLineMapper.deleteSavedOrderLines(user.getUserId(), connectionPool);
                OrderMapper.deleteSavedOrder(user.getUserId(), connectionPool);
            }
        } catch (DatabaseException e) {
            ctx.render("index.html");
        }


    }

    private static void saveOrder(Context ctx, ConnectionPool connectionPool) {
        Cart cart = ctx.sessionAttribute("cart");
        if(!cart.getOrderLines().isEmpty()) {
            int price = cart.calculatePrice();
            User user = ctx.sessionAttribute("currentUser");

            try {
                Order order = OrderMapper.createOrder(user, price, false, connectionPool);
                for (OrderLine o : cart.getOrderLines()) {
                    OrderLineMapper.createOrderLine(order.getOrderId(), o.getBottomId(), o.getToppingId(), o.getPrice(), o.getAmount(), connectionPool);
                }
            } catch (DatabaseException e) {
                System.out.println("Could not save order");
            }
        }
    }

    private static void admin(Context ctx, ConnectionPool connectionPool) throws DatabaseException {

        ctx.attribute("ordersList", OrderMapper.getAllOrders(connectionPool));
        ctx.attribute("userList", UserMapper.getAllUsers(connectionPool));
        ctx.render("admin.html");
    }

    private static void updatebalance(Context ctx, ConnectionPool connectionPool) throws DatabaseException {
        int userId = Integer.parseInt(ctx.formParam("userId"));
        int balance = Integer.parseInt(ctx.formParam("balance"));

        UserMapper.updateBalance(userId, balance, connectionPool);
        ctx.attribute("ordersList", OrderMapper.getAllOrders(connectionPool));
        ctx.attribute("userList", UserMapper.getAllUsers(connectionPool));
        ctx.render("admin.html");

    }

    private static void details(Context ctx, ConnectionPool connectionPool) {
        int orderId = Integer.parseInt(ctx.formParam("orderId"));
        List<OrderLine> orderLineList = OrderLineMapper.getAllOrderLinesById(orderId, connectionPool);
        ctx.attribute("orderLineList", OrderLine.convertOrderlineList(orderLineList, ctx.sessionAttribute("bottomList"), ctx.sessionAttribute("toppingList")));

        ctx.render("orderlines.html");
    }

    private static void deleteOrder(Context ctx, ConnectionPool connectionPool) throws DatabaseException {
        int orderId = Integer.parseInt(ctx.formParam("orderId"));
        OrderMapper.deleteOrder(orderId, connectionPool);
        ctx.attribute("ordersList", OrderMapper.getAllOrders(connectionPool));
        ctx.attribute("userList", UserMapper.getAllUsers(connectionPool));
        ctx.render("admin.html");

    }

    private static void haiku(Context ctx) {
        Haiku haiku = new Haiku();
        haiku.fillHaikuList();
        ctx.attribute("haiku", haiku.pickRandomHaiku());
    }
}

