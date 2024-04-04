package app.entities;


public class User {
    private int userId;
    private String userName;
    private String password;
    private String role;
    private String email;
    private int balance;

    public User(int userId, String userName, String password, String role, String email, int balance) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.role = role;
        this.email = email;
        this.balance = balance;
    }

    public int getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
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
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                ", email='" + email + '\'' +
                ", balance='" + balance + '\'' +
                '}';
    }
}
