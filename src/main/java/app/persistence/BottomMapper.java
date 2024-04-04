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
    public static List<Bottom> getAllBottom(int bottomId, ConnectionPool connectionPool) throws DatabaseException {
        List<Bottom> bottomList = new ArrayList<>();
        String sql = "SELECT * FROM public.bottom\n" +
                "ORDER BY bottom_id ASC ";

        try (
                Connection connection = connectionPool.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)
        ) {
            ps.setInt(1, bottomId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String bottomName = rs.getString("bottomName");
                int price = rs.getInt("price");
                bottomList.add(new Bottom(bottomName,price));
            }
        } catch (SQLException e) {
            throw new DatabaseException("Fejl!!!!", e.getMessage());
        }
        return bottomList;
    }

}
