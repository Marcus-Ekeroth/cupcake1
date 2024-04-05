package app.persistence;/* @auther: Frederik Dupont */

import app.entities.Bottom;
import app.exceptions.DatabaseException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BottomMapper {

    public static List<Bottom> getAllBottom(ConnectionPool connectionPool) throws DatabaseException {
        List<Bottom> bottomList = new ArrayList<>();
        String sql = "SELECT * FROM bottom\n" +
                "ORDER BY bottom_id ASC ";
        try (
                Connection connection = connectionPool.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)
        ) {

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int bottomId = rs.getInt("bottom_id");
                String bottomName = rs.getString("bottom_name");
                int price = rs.getInt("bottom_price");
                bottomList.add(new Bottom(bottomId,bottomName,price));
            }
        } catch (SQLException e) {
            throw new DatabaseException("Fejl!!!!", e.getMessage());
        }
        return bottomList;
    }

}
