package app.persistence;

import app.entities.User;
import app.exceptions.DatabaseException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserMapper {

    public static User login(String email, String password, ConnectionPool connectionPool) throws DatabaseException {
        String sql = "select * from users where email=? and password=?";

        try (
                Connection connection = connectionPool.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)
        ) {
            ps.setString(1, email);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("user_id");
                String role = rs.getString("role");
                int balance = rs.getInt("balance");
                return new User(id, email, password, role, balance);
            } else {
                throw new DatabaseException("Fejl i login. Prøv igen");
            }
        } catch (SQLException e) {
            throw new DatabaseException("DB fejl", e.getMessage());
        }
    }

    public static void createuser(String password, String email, ConnectionPool connectionPool) throws DatabaseException {
        String sql = "insert into users (password, email,balance, role) values (?,?,?,?)";

        try (
                Connection connection = connectionPool.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)
        ) {
            ps.setString(1, password);
            ps.setString(2, email);
            ps.setInt(3, 0);
            ps.setString(4, "user");

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected != 1) {
                throw new DatabaseException("Fejl ved oprettelse af ny bruger");
            }
        } catch (SQLException e) {
            String msg = "Der er sket en fejl. Prøv igen";
            if (e.getMessage().startsWith("ERROR: duplicate key value ")) {
                msg = "Denne email er allerede brugt.";
            }
            throw new DatabaseException(msg, e.getMessage());
        }
    }

    public static void updateBalance(int userId, int balance, ConnectionPool connectionPool) throws DatabaseException {
        String sql = "update users set balance = ? where user_id = ?";

        try (
                Connection connection = connectionPool.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)
        ) {
            ps.setInt(1, balance);
            ps.setInt(2, userId);
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected != 1) {
                throw new DatabaseException("Fejl i opdatering af en balance");
            }
        } catch (SQLException e) {
            throw new DatabaseException("Fejl i opdatering af en balance", e.getMessage());
        }

    }
    public static List<User> getAllUsers(ConnectionPool connectionPool) throws DatabaseException
    {
        List<User> userList = new ArrayList<>();
        String sql = "select * from users order by user_id";

        try (
                Connection connection = connectionPool.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)
        )
        {
            ResultSet rs = ps.executeQuery();
            while (rs.next())
            {
                int id = rs.getInt("user_id");
                String email = rs.getString("email");
                String role = rs.getString("role");
                int balance = rs.getInt("balance");
                userList.add(new User(id, email, role, balance));
            }
        }
        catch (SQLException e)
        {
            throw new DatabaseException("Fejl!!!!", e.getMessage());
        }
        return userList;
    }
}
