package app.controllers;

import app.entities.User;
import app.exceptions.DatabaseException;
import app.persistence.ConnectionPool;
import app.persistence.UserMapper;
import io.javalin.Javalin;
import io.javalin.http.Context;

import java.util.List;

public class UserController {

    public static void addRoutes(Javalin app, ConnectionPool connectionPool){
        app.post("login", ctx -> login(ctx, connectionPool));
        app.post("loggingon", ctx -> loggingon(ctx, connectionPool));
       // app.get("logout", ctx -> logout(ctx));
        app.get("createuser", ctx -> ctx.render("createuser.html"));
        app.post("createuser", ctx -> createUser(ctx,connectionPool));
    }

    private static void login(Context ctx, ConnectionPool connectionPool) {
        ctx.render("login.html");
    }

    private static void loggingon(Context ctx, ConnectionPool connectionPool){

        String email = ctx.formParam("email");
        String password = ctx.formParam("password");


        try {
            User user = UserMapper.login(email,password, connectionPool);
            ctx.sessionAttribute("currentUser",user);

if("admin".equals(user.getRole())) {
    ctx.render("admin.html");
}else{
    ctx.render("order.html");
}
        } catch (DatabaseException e) {

            ctx.attribute("message",e.getMessage());
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
                ctx.attribute("message", "Bruger oprettet succesfuldt. Venligst log ind for at fortsætte.");
                ctx.render("index.html");
            } catch (DatabaseException e) {
                ctx.attribute("message", e.getMessage());
                ctx.render("createuser.html");
            }
        } else {
            ctx.attribute("message", "Adgangskoderne matcher ikke!");
            ctx.render("createuser.html");
        }
    }
}

