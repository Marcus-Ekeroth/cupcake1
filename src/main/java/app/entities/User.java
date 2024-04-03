package app.entities;

public class User {

    private String username;

    private String password;

    private boolean admin;

    private int userId;

    private int balance;

    private String email;

    public User(String username, String password, boolean admin, int userId, int balance, String email) {
        this.username = username;
        this.password = password;
        this.admin = admin;
        this.userId = userId;
        this.balance = balance;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", admin=" + admin +
                ", userId=" + userId +
                ", balance=" + balance +
                ", email='" + email + '\'' +
                '}';
    }
}
