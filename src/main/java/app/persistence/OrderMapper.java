package app.persistence;

import app.entities.OrderLine;
import app.exceptions.DatabaseException;
import app.entities.Order;
import app.entities.User;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderMapper {

    public List<OrderLine> getAllOrderLines(ConnectionPool connectionPool){
        String sql = "SELECT * FROM orderline WHERE user_id = ?";
        List<OrderLine> orderLineList = new ArrayList<>();
        try(
                Connection connection = connectionPool.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql);
        ) {
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                int price = rs.getInt("price");
                int orderLineId = rs.getInt("orderline_id)");
                int orderId = rs.getInt("order_id");
                int bottomId = rs.getInt("bottom_id");
                int toppingId = rs.getInt("topping_id");
                int amount = rs.getInt("amount");

                OrderLine orderLine = new OrderLine(price, orderLineId, orderId, bottomId, toppingId, amount);
                orderLineList.add(orderLine);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return orderLineList;
    }

    public Order createOrder(User user, int orderId, int price, boolean paid, ConnectionPool connectionPool) throws DatabaseException {
        Order newOrder = null;

        String sql = "insert into order (order_id, price, paid, user_id) values (?,?,?,?)";

        try(
                Connection connection = connectionPool.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)
        ) {
            ps.setInt(1, orderId);
            ps.setInt(2, price);
            ps.setBoolean(3, paid);
            ps.setInt(4,user.getUserId());
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected == 1)
            {
                ResultSet rs = ps.getGeneratedKeys();
                rs.next();
                int newId = rs.getInt(1);
                newOrder = new Order(newId, price, paid, user.getUserId());
            } else
            {
                throw new DatabaseException("Fejl under indsætning af task: " + orderId);
            }


        } catch (SQLException e) {
            throw new DatabaseException("Fejl under bestilling", e.getMessage());
        }
        return newOrder;
    }

    private void deleteOrder(int orderId, ConnectionPool connectionPool) throws DatabaseException{
        String sql = "delete from order where order_id = ?";

        try (
                Connection connection = connectionPool.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)
        )
        {
            ps.setInt(1, orderId);
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected != 1)
            {
                throw new DatabaseException("Fejl i opdatering af en order");
            }
        }
        catch (SQLException e)
        {
            throw new DatabaseException("Fejl ved sletning af en order", e.getMessage());
        }
    }
}