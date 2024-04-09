package app.persistence;

import app.entities.*;
import app.entities.OrderLine;
import app.exceptions.DatabaseException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderLineMapper {

    public static void createOrderLine(int orderId, int botttomId, int toppingId, int price, int amount, ConnectionPool connectionPool) throws DatabaseException {
        String sql = "insert into public.orderline (order_id, bottom_id, topping_id, price, amount) values (?,?,?,?,?)";

        try (
                Connection connection = connectionPool.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)
        ) {
            ps.setInt(1, orderId);
            ps.setInt(2, botttomId);
            ps.setInt(3, toppingId);
            ps.setInt(4, price);
            ps.setInt(5, amount);

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected != 1) {
                throw new DatabaseException("Fejl ved oprettelse af ny bruger");
            }
        } catch (SQLException e) {
            String msg = "Der er sket en fejl. Prøv igen";
            if (e.getMessage().startsWith("ERROR: duplicate key value ")) {
                msg = "Brugernavnet findes allerede. Vælg et andet";
            }
            throw new DatabaseException(msg, e.getMessage());
        } catch (DatabaseException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<OrderLine> getAllSavedOrderLines(int userId, ConnectionPool connectionPool){
        String sql = "SELECT ol.price AS price, ol.orderline_id AS orderline_id, ol.order_id AS order_id, ol.bottom_id AS bottom_id, ol.topping_id AS topping_id, ol.amount AS amount FROM orderline ol INNER JOIN public.\"order\" o ON ol.order_id = o.order_id WHERE o.user_id = ? AND o.paid = 'false';";
        List<OrderLine> orderLineList = new ArrayList<>();
        try (
                Connection connection = connectionPool.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql);
        ) {
            ps.setInt(1, userId);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int price = rs.getInt("price");
                int orderLineId = rs.getInt("orderline_id");
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

    public static void deleteSavedOrderLines(int userId, ConnectionPool connectionPool) throws DatabaseException {
        String sql = "DELETE FROM orderline WHERE EXISTS (SELECT 1 FROM public.\"order\" o WHERE o.order_id = orderline.order_id AND o.user_id = ? AND o.paid = 'false')";

        try (
                Connection connection = connectionPool.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)
        ) {
            ps.setInt(1, userId);
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected < 1) {
                throw new DatabaseException("Error deleting orderlines");
            }
        } catch (SQLException e) {
            throw new DatabaseException("Error deleting orderlines", e.getMessage());
        }
    }

    public static List<OrderLine> getAllOrderLinesById(int orderId, ConnectionPool connectionPool){
        String sql = "SELECT * FROM orderline WHERE order_id = ?";
        List<OrderLine> orderLineList = new ArrayList<>();
        try(
                Connection connection = connectionPool.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql);
        ) {
            ps.setInt(1, orderId);

            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                int price = rs.getInt("price");
                int orderLineId = rs.getInt("orderline_id");
                int id = rs.getInt("order_id");
                int bottomId = rs.getInt("bottom_id");
                int toppingId = rs.getInt("topping_id");
                int amount = rs.getInt("amount");

                OrderLine orderLine = new OrderLine(price, orderLineId, id, bottomId, toppingId, amount);
                orderLineList.add(orderLine);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return orderLineList;
    }
    public static void deleteOrderLine(int orderId, ConnectionPool connectionPool) throws DatabaseException {
        String sql = "delete from orderline where order_id = ?";

        try (
                Connection connection = connectionPool.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)
        ) {
            ps.setInt(1, orderId);
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected < 0) {
                throw new DatabaseException("Fejl i opdatering af en orderline");
            }
        } catch (SQLException e) {
            throw new DatabaseException("Fejl ved sletning af en task", e.getMessage());
        }
    }
}