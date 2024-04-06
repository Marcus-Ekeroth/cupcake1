package app.entities;


public class User {
    private int userId;
    private String password;
    private String role;
    private String email;
    private int balance;

    public User(int userId, String email, String password, String role, int balance) {
        this.userId = userId;
        this.password = password;
        this.role = role;
        this.email = email;
        this.balance = balance;
    }

    public int getUserId() {
        return userId;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public String getEmail() {
        return email;
    }

    public int getBalance() {
        return balance;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                ", email='" + email + '\'' +
                ", balance='" + balance + '\'' +
                '}';
    }
}
