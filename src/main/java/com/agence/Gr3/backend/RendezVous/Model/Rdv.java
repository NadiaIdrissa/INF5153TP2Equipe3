package com.agence.Gr3.backend.RendezVous.Model;

import com.agence.Gr3.backend.Notifications.Model.Observateur;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Rdv implements Sujet {

    private List<Observateur> observateurs = new ArrayList<>();

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

        // Ne pas oublier de subscribe les observateurs

    }

    @Override
    public void ajouterObservateur(Observateur observateur) {
        observateurs.add(observateur);
    };

    @Override
    public void retirerObservateur(Observateur observateur) {
        observateurs.remove(observateur);
    };

    @Override
    public void notifierObservateurs(String notification) {
        for (Observateur observateur : observateurs) {
            observateur.mettreAJour(notification);
        }

    };

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

    /**
     * La méthode accepte les modifications, ou confir
     * 
     * 
     * @param idUtilisateur
     */
    public void accepter(String idUtilisateur) {

        if (this.statut == Statut.ATTENTE) {

            setStatut(statut.CONFIRME);

            notifierObservateurs(genererNotification(idUtilisateur, statut));
        } else if (this.statut == Statut.MODIFIE) {
            setStatut(statut.CONFIRME);
            setDateHeure(this.dateHeureProposee);

            notifierObservateurs(genererNotification(idUtilisateur, statut));
        }

    }

    public void annulerRdv(String idUtilisateur) {
        setStatut(statut.ANNULE);
        notifierObservateurs(genererNotification(idUtilisateur, this.statut));

    }

    public void modifierRdv(String idUtilisateur, LocalDateTime dateHeureProposee) {
        setStatut(statut.MODIFIE);

        this.dateHeureProposee = dateHeureProposee;
        notifierObservateurs(genererNotification(idUtilisateur, this.statut));

    }

    private String genererNotification(String idUtilisateur, Statut statut) {

        String notification = idUtilisateur + statut.getText() + ": Logement:" + this.idLogement;

        if (statut == Statut.MODIFIE) {

            notification = notification + "Date et heure suggérée:" + this.dateHeureProposee + "Date et heure actuelle:"
                    + this.dateHeure;

        }

        notification = notification + "Date et heure:" + this.dateHeure;

        return notification;

    }

}
