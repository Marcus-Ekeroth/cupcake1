package app.persistence;/* @auther: Frederik Dupont */

import app.entities.Bottom;
import app.entities.Topping;
import app.exceptions.DatabaseException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ToppingMapper {

    public static List<Topping> getAllTopping(ConnectionPool connectionPool) throws DatabaseException {
        List<Topping> toppingList = new ArrayList<>();
        String sql = "SELECT * FROM topping\n" +
                "ORDER BY topping_id ASC ";
        try (
                Connection connection = connectionPool.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)
        ) {

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int toppingId = rs.getInt("topping_id");
                String toppingName = rs.getString("topping_name");
                int price = rs.getInt("topping_price");
                toppingList.add(new Topping(toppingId,toppingName,price));
            }
        } catch (SQLException e) {
            throw new DatabaseException("Fejl!!!!", e.getMessage());
        }
        return toppingList;
    }

}
