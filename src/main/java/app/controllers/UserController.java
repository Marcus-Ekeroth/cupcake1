package app.controllers;

import app.persistence.ConnectionPool;
import app.persistence.UserMapper;
import io.javalin.Javalin;
import io.javalin.http.Context;


import java.util.List;

public class UserController {

    public static void addRoutes(Javalin app, ConnectionPool connectionPool) {
        app.post("/login", ctx -> login(ctx, connectionPool));
        app.get("/createuser", ctx -> ctx.render("createuser"));
        app.post("/createuser", ctx -> createUser(ctx, connectionPool));
        app.get("/logout", ctx -> logout(ctx));
    }

    public static void login(Context ctx, ConnectionPool connectionPool) {
        String name = ctx.formParam("username");
        String password = ctx.formParam("password");
        try {
            User user = UserMapper.login(name, password, connectionPool);
            ctx.sessionAttribute("currentUser", user);
            ctx.render("tasks"); // Directly render the tasks page without retrieving tasks
        } catch (DatabaseException e) {
            ctx.attribute("message", e.getMessage());
            ctx.render("index");
        }
    }
    public static void createUser(Context ctx, ConnectionPool connectionPool) {
        String name = ctx.formParam("username");
        String password1 = ctx.formParam("password1");
        String password2 = ctx.formParam("password2");

        // Validate passwords
        if (password1.equals(password2)) {
            try {
                UserMapper.createUser(name, password1, connectionPool);
                ctx.attribute("message", "User created successfully. Please log in to continue.");
                ctx.render("index");
            } catch (DatabaseException e) {
                ctx.attribute("message", e.getMessage());
                ctx.render("createuser");
            }
        } else {
            ctx.attribute("message", "Passwords do not match!");
            ctx.render("createuser");
        }
    }


    public static void logout(Context ctx) {
        // Invalidate session
        ctx.req().getSession().invalidate();
        ctx.redirect("/");
    }
}
