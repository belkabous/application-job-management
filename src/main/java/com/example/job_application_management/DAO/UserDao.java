package com.example.job_application_management.DAO;


import com.example.job_application_management.Models.User;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao {


    private static final String URL = "jdbc:mysql://localhost:3306/management_job";
    private static final String USER = "root";
    private static final String PASSWORD = "";


    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Erreur de chargement du driver JDBC", e);
        }
    }

    // Méthode pour obtenir une connexion
    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    // Méthode pour récupérer tous les utilisateurs
    public List<User> getAll() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM users";

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setRole(rs.getString("role"));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }
}

