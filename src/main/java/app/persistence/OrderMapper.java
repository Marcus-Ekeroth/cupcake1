package app.persistence;

import app.exceptions.DatabaseException;
import app.entities.Order;
import app.entities.User;

import java.sql.*;

public class OrderMapper {

    private void createOrder(User user, ConnectionPool connectionPool) throws DatabaseException {
        Order newOrder = null;

        String sql = "insert into task (order_id, bottom_id, topping_id, amount, price) values (?,?,?,?,?)";

        try(
                Connection connection = connectionPool.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)
        ) {
        //TODO: fyld resten af metoden ud


    } catch (SQLException e) {
            throw new DatabaseException("Fejl under bestilling", e.getMessage());
        }

    }

    private void deleteOrder(){

    }
}
