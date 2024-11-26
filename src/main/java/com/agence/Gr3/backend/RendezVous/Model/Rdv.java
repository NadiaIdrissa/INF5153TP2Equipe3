package com.agence.Gr3.backend.RendezVous.Model;

import com.agence.Gr3.backend.Notifications.Model.Observateur;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Rdv {

    private int id;
    private int idLogement;
    private String idLocataire;
    private String idGerant;
    private LocalDateTime dateHeure;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime dateHeureProposee;
    private Statut statut;
    private String modification;

    public Rdv(int id, int idLogement, String idLocataire, String idGerant, LocalDateTime dateHeureProposee) {
        this.id = id;
        this.idLogement = idLogement;
        this.idLocataire = idLocataire;
        this.idGerant = idGerant;
        this.dateHeureProposee = dateHeureProposee;
        this.statut = Statut.ATTENTE;
    }

    public int getId() {
        return id;
    }

    public int getIdLogement() {
        return idLogement;
    }

    public void setIdLogement(int idLogement) {
        this.idLogement = idLogement;
    }

    public String getIdLocataire() {
        return idLocataire;
    }

    public void setIdLocataire(String idLocataire) {
        this.idLocataire = idLocataire;
    }

    public String getIdGerant() {
        return idGerant;
    }

    public void setIdGerant(String idGerant) {
        this.idGerant = idGerant;
    }

    public LocalDateTime getDateHeure() {
        return dateHeure;
    }

    public void setDateHeure(LocalDateTime dateHeure) {
        this.dateHeure = dateHeure;
    }

    public LocalDateTime getDateHeureProposee() {
        return dateHeureProposee;
    }

    public void setDateHeureProposee(LocalDateTime dateHeureProposee) {
        this.dateHeureProposee = dateHeureProposee;
    }

    public Statut getStatut() {
        return statut;
    }

    public void setStatut(Statut statut) {
        this.statut = statut;
    }

    public String getModification() {
        return modification;
    }

    public void setModification(String modification) {
        this.modification = modification;
    }

}
