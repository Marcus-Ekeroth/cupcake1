package app.persistence;

import app.exceptions.DatabaseException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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


    private void deleteOrderLine(int orderlineId, ConnectionPool connectionPool) throws DatabaseException {
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