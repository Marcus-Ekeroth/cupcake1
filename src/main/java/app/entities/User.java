package app.entities;

public class User {
    private String password;

    private String role;

    private int userId;

    private int balance;

    private String email;

    public User(String password, String role, int userId, int balance, String email) {
        this.password = password;
        this.role = role;
        this.userId = userId;
        this.balance = balance;
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
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
                "password='" + password + '\'' +
                ", role='" + role + '\'' +
                ", userId=" + userId +
                ", balance=" + balance +
                ", email='" + email + '\'' +
                '}';
    }
}
