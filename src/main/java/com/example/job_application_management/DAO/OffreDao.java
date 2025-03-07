package com.example.job_application_management.DAO;

import com.example.job_application_management.Models.Offre_emploi;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OffreDao {
    private Connection connection;

    public OffreDao() {
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

    public void AjouterOffer(Offre_emploi offreEmploi) {
        if (connection == null) {
            System.err.println("Database connection not established!");
            return;
        }
        String query = "INSERT INTO offre_emploi (titre_offre, description, date) VALUES (?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, offreEmploi.getTitre());
            preparedStatement.setString(2, offreEmploi.getDescription());
            preparedStatement.setDate(3, new java.sql.Date(offreEmploi.getDate().getTime()));
            preparedStatement.executeUpdate(); // Cette ligne était manquante
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Offre_emploi> AfficherOffer() {
        List<Offre_emploi> offreEmploi = new ArrayList<>();
        String query = "SELECT id_offre, titre_offre, description, date FROM offre_emploi";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Offre_emploi emploi = new Offre_emploi();
                emploi.setId(resultSet.getInt("id_offre"));
                emploi.setTitre(resultSet.getString("titre_offre"));
                emploi.setDescription(resultSet.getString("description"));
                emploi.setDate(resultSet.getDate("date"));
                offreEmploi.add(emploi);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return offreEmploi;
    }

    public void ModiffierOffer(Offre_emploi offreEmploi) {
        String query = "UPDATE offre_emploi SET titre_offre = ?, description = ?, date = ? WHERE id_offre = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, offreEmploi.getTitre());
            preparedStatement.setString(2, offreEmploi.getDescription());
            preparedStatement.setDate(3, new java.sql.Date(offreEmploi.getDate().getTime()));
            preparedStatement.setInt(4, offreEmploi.getId());
            preparedStatement.executeUpdate(); // Cette ligne était manquante
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void DeleteOffre(int offreId) {
        String query = "DELETE FROM offre_emploi WHERE id_offre = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, offreId);
            preparedStatement.executeUpdate(); // Cette ligne était manquante
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Offre_emploi SelectByIdOffre(int id) {
        String query = "SELECT id_offre, titre_offre, description, date FROM offre_emploi WHERE id_offre = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return new Offre_emploi(
                        resultSet.getInt("id_offre"),
                        resultSet.getString("titre_offre"),
                        resultSet.getString("description"),
                        resultSet.getDate("date")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}