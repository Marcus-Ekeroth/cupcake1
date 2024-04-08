package app.persistence;

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
    public void deleteOrderLine(int orderlineId, ConnectionPool connectionPool) throws DatabaseException {
        String sql = "delete from orderline where orderline_id = ?";

        try (
                Connection connection = connectionPool.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)
        ) {
            ps.setInt(1, orderlineId);
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected != 1) {
                throw new DatabaseException("Fejl i opdatering af en orderline");
            }
        } catch (SQLException e) {
            throw new DatabaseException("Fejl ved sletning af en task", e.getMessage());
        }
    }
}