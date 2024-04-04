package app.persistence;

import app.exceptions.DatabaseException;
import app.entities.Order;
import app.entities.User;

import java.sql.*;

public class OrderMapper {

    private void createOrder(User user, int orderId, int price, boolean paid, ConnectionPool connectionPool) throws DatabaseException {
        Order newOrder = null;

        String sql = "insert into order (order_id, price, paid, user_id) values (?,?,?,?,?)";

        try(
                Connection connection = connectionPool.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)
        ) {
            ps.setInt(1, orderId);
            ps.setInt(2, price);
            ps.setBoolean(3, paid);
            //in progress
            ps.setInt(4,user.getUserId());


        } catch (SQLException e) {
            throw new DatabaseException("Fejl under bestilling", e.getMessage());
        }

    }

    private void deleteOrder(){

    }
}