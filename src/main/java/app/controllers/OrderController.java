package app.controllers;

import app.entities.Order;
import app.persistence.ConnectionPool;
import io.javalin.Javalin;
import io.javalin.http.Context;

public class OrderController {



    public static void addRoutes(Javalin app, ConnectionPool connectionPool)
    {

    }

    private Order createOrder(Context ctx, ConnectionPool connectionPool){
        User user =ctx.sessionAttribute("currentUser")
        //TODO: lav et context object som kan bruge userinputs til at indsætte dem som attributter til order objekt
        Order order = new Order()
    }


    private void deleteOrder(Order order){

    }




}
