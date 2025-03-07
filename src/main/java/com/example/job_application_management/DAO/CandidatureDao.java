package com.example.job_application_management.DAO;

import com.example.job_application_management.Models.Candidat;
import com.example.job_application_management.Models.Candidature;
import com.example.job_application_management.Models.Offre_emploi;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CandidatureDao {
    private Connection connection;
    private CandidatDao candidatDao;
    private OffreDao offreDao;

    public CandidatureDao() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Connecting to database...");
            this.connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/management_job", "root", "");
            candidatDao = new CandidatDao();
            offreDao = new OffreDao();
        } catch (ClassNotFoundException e) {
            System.err.println("MySQL JDBC Driver not found!");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Database connection error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public boolean ajouterCandidature(int idCandidat, int idOffre) {
        if (connection == null) {
            System.err.println("Database connection not established!");
            return false;
        }
        String query = "INSERT INTO candidature (id_candidat, id_offre) VALUES (?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, idCandidat);
            preparedStatement.setInt(2, idOffre);
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("Error inserting candidature: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public List<Candidature> getCandidaturesForOffre(int idOffre) {
        List<Candidature> candidatures = new ArrayList<>();
        String query = "SELECT c.id_candidat, c.nom_candiat, c.email_candidat, o.id_offre, o.titre_offre " +
                "FROM candidature ca " +
                "JOIN candidat c ON ca.id_candidat = c.id_candidat " +
                "JOIN offre_emploi o ON ca.id_offre = o.id_offre " +
                "WHERE ca.id_offre = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, idOffre);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Candidat candidat = new Candidat(
                        resultSet.getInt("id_candidat"),
                        resultSet.getString("nom_candiat"),
                        resultSet.getString("email_candidat")
                );

                Offre_emploi offre = new Offre_emploi();
                offre.setId(resultSet.getInt("id_offre"));
                offre.setTitre(resultSet.getString("titre_offre"));

                Candidature candidature = new Candidature(candidat, offre);
                candidatures.add(candidature);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return candidatures;
    }

    public List<Candidature> getCandidaturesForCandidat(int idCandidat) {
        List<Candidature> candidatures = new ArrayList<>();
        String query = "SELECT c.id_candidat, c.nom_candiat, c.email_candidat, o.id_offre, o.titre_offre, o.description, o.date " +
                "FROM candidature ca " +
                "JOIN candidat c ON ca.id_candidat = c.id_candidat " +
                "JOIN offre_emploi o ON ca.id_offre = o.id_offre " +
                "WHERE ca.id_candidat = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, idCandidat);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Candidat candidat = new Candidat(
                        resultSet.getInt("id_candidat"),
                        resultSet.getString("nom_candiat"),
                        resultSet.getString("email_candidat")
                );

                Offre_emploi offre = new Offre_emploi(
                        resultSet.getInt("id_offre"),
                        resultSet.getString("titre_offre"),
                        resultSet.getString("description"),
                        resultSet.getDate("date")
                );

                Candidature candidature = new Candidature(candidat, offre);
                candidatures.add(candidature);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return candidatures;
    }

    public boolean deleteCandidature(int idCandidat, int idOffre) {
        String query = "DELETE FROM candidature WHERE id_candidat = ? AND id_offre = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, idCandidat);
            preparedStatement.setInt(2, idOffre);
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}