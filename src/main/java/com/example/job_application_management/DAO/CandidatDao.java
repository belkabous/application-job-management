package com.example.job_application_management.DAO;

import com.example.job_application_management.Models.Candidat;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CandidatDao {
    private Connection connection;

    public CandidatDao() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Connecting to database...");
            this.connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/management_job", "root", "");

        } catch (ClassNotFoundException e) {
            System.err.println("MySQL JDBC Driver not found!");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Database connection error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void AjouterCandidat(Candidat candidat) {
        if (connection == null) {
            System.err.println("Database connection not established!");
            return;
        }
        String query = "INSERT INTO candidat (nom_candiat, email_candidat) VALUES (?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, candidat.getNom());
            preparedStatement.setString(2, candidat.getEmail());
            preparedStatement.executeUpdate(); // Cette ligne était manquante
        } catch (SQLException e) {
            System.err.println("Error inserting candidat: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public List<Candidat> AfficherCandidat() {
        List<Candidat> candidats = new ArrayList<>();
        String query = "SELECT id_candidat, nom_candiat, email_candidat FROM candidat";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id_candidat");
                String nom = resultSet.getString("nom_candiat"); // Correction du nom de colonne
                String email = resultSet.getString("email_candidat");
                candidats.add(new Candidat(id, nom, email));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return candidats;
    }

    public void ModiffierCandidat(Candidat candidat) {
        String query = "UPDATE candidat SET nom_candiat = ?, email_candidat = ? WHERE id_candidat = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, candidat.getNom());
            preparedStatement.setString(2, candidat.getEmail());
            preparedStatement.setInt(3, candidat.getId());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleterCandidat(Integer candidatId) {
        String query = "DELETE FROM candidat WHERE id_candidat = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, candidatId);
            preparedStatement.executeUpdate(); // Cette ligne était manquante
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Candidat selectCandidatById(int id) {
        String query = "SELECT id_candidat, nom_candiat, email_candidat FROM candidat WHERE id_candidat = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                return new Candidat(rs.getInt("id_candidat"), rs.getString("nom_candiat"), rs.getString("email_candidat"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}