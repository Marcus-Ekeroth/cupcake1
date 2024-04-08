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


    public static List<Order> getAllOrders(ConnectionPool connectionPool){
        String sql = "SELECT * FROM public.\"order\" order by order_id";
        List<Order> ordersList = new ArrayList<>();
        try(
                Connection connection = connectionPool.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql);
        ) {
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                int orderId = rs.getInt("order_id");
                int price = rs.getInt("price");
                boolean paid = rs.getBoolean("paid");
                int userId = rs.getInt("user_id");


                Order order = new Order(orderId, price, paid, userId);
                ordersList.add(order);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return ordersList;
    }

    public static Order createOrder(User user, int price, boolean paid, ConnectionPool connectionPool) throws DatabaseException {
        Order newOrder = null;

        String sql = "insert into public.\"order\" (price, paid, user_id) values (?,?,?)";

        try(
                Connection connection = connectionPool.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)
        ) {
            ps.setInt(1, price);
            ps.setBoolean(2, paid);
            ps.setInt(3,user.getUserId());
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected == 1)
            {
                ResultSet rs = ps.getGeneratedKeys();
                rs.next();
                int newId = rs.getInt(1);
                newOrder = new Order(newId, price, paid, user.getUserId());
            } else
            {
                throw new DatabaseException("Fejl under indsætning af task: ");
            }


        } catch (SQLException e) {
            throw new DatabaseException("Fejl under bestilling", e.getMessage());
        }
        return newOrder;
    }

    public static void deleteOrder(int orderId, ConnectionPool connectionPool) throws DatabaseException{
        String sql = "delete from public.\"order\" where order_id = ?";

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

    public static void deleteSavedOrder(int userId, ConnectionPool connectionPool) throws DatabaseException {
        String sql = "DELETE FROM public.\"order\" WHERE user_id = ? AND paid = 'false'";
        try (
                Connection connection = connectionPool.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)
        ) {
            ps.setInt(1, userId);
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected != 1) {
                throw new DatabaseException("Error deleting orders");
            }
        } catch (SQLException e) {
            throw new DatabaseException("Error deleting orders", e.getMessage());
        }
    }



}