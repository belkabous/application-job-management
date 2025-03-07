package com.example.job_application_management.Models;

import java.util.Date;

public class Candidature {
    private Candidat candidat;
    private Offre_emploi offre;
    private Date datePostulation;
    private String statut;

    public Candidature() {
        this.datePostulation = new Date();
        this.statut = "En attente";
    }

    public Candidature(Candidat candidat, Offre_emploi offre) {
        this.candidat = candidat;
        this.offre = offre;
        this.datePostulation = new Date();
        this.statut = "En attente";
    }

    public Candidature(Candidat candidat, Offre_emploi offre, Date datePostulation, String statut) {
        this.candidat = candidat;
        this.offre = offre;
        this.datePostulation = datePostulation;
        this.statut = statut;
    }

    public Candidat getCandidat() {
        return candidat;
    }

    public void setCandidat(Candidat candidat) {
        this.candidat = candidat;
    }

    public Offre_emploi getOffre() {
        return offre;
    }

    public void setOffre(Offre_emploi offre) {
        this.offre = offre;
    }

    public Date getDatePostulation() {
        return datePostulation;
    }

    public void setDatePostulation(Date datePostulation) {
        this.datePostulation = datePostulation;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }
}