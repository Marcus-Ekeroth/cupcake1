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
        //app.get("createuser", ctx -> ctx.render("createuser.html"));
        //app.post("createuser", ctx -> createUser(ctx,connectionPool));
    }

    private static void login(Context ctx, ConnectionPool connectionPool) {
        ctx.render("login.html");
    }

    private static void loggingon(Context ctx, ConnectionPool connectionPool){
        //Hent form parametre
        String email = ctx.formParam("email");
        String password = ctx.formParam("password");
        //Check om bruger findes i DB med de angivne username + password
        try {
            User user = UserMapper.login(email,password, connectionPool);
            ctx.sessionAttribute("currentUser",user);
            // Hvis ja, send videre til order siden

            //Skal laves så man får data fra order
            // List<Task> taskList = TaskMapper.getAllTasksPerUser(user.getUserId(),connectionPool);

            //data indsættes her
            // ctx.attribute("bottomList", bottomList);
            // ctx.attribute("toppingList", toppingList);

            ctx.render("order.html");
        } catch (DatabaseException e) {
            //Hvis nej, send tilbage til login side med fejl besked
            ctx.attribute("message",e.getMessage());
            ctx.render("index.html");
        }


    }


}
