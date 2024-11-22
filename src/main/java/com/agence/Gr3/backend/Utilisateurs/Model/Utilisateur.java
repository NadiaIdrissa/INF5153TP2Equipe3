package com.agence.Gr3.backend.Utilisateurs.Model;

import java.util.List;

public class Utilisateur {

    private Identifiant identifiant;
    private final Role role;
    private String statut;
    private Profil profil;
    private List<String> notifications;
    private List<String> calendrier;
    private List<String> annonces;
    private List<String> locateurs;

    // Constructeur acceptant une instance de type builder
    protected Utilisateur(BuilderUtilisateur builder) {
        this.identifiant = builder.getIdentifiant();
        this.role = builder.getRole();
        this.statut = builder.getStatut();
        this.profil = builder.getProfil();
        this.notifications = builder.getNotifications();
        this.calendrier = builder.getCalendrier();
        this.annonces = builder.getAnnonces();
        this.locateurs = builder.getLocateurs();
    }

    public Identifiant getIdentifiant() {
        return identifiant;
    }

    public Role getRole() {
        return role;
    }

    public String getStatut() {
        return statut;
    }

    public Profil getProfil() {
        return profil;
    }

    public List<String> getNotifications() {
        return notifications;
    }

    public List<String> getCalendrier() {
        return calendrier;
    }

    public List<String> getAnnonces() {
        return annonces;
    }

    public List<String> getLocateurs() {
        return locateurs;
    }

    public void setIdentifiant(Identifiant identifiant) {
        this.identifiant = identifiant;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public void setProfil(Profil profil) {
        this.profil = profil;
    }

    public void setNotifications(List<String> notifications) {
        this.notifications = notifications;
    }

    public void setCalendrier(List<String> calendrier) {
        this.calendrier = calendrier;
    }

    public void setAnnonces(List<String> annonces) {
        this.annonces = annonces;
    }

    public void setLocateurs(List<String> locateurs) {
        this.locateurs = locateurs;
    }

    @Override
    public String toString() {
        return "\nCompte Utilisateur:\n" +
                "\n   Role: " + role +
                "\n   Statut: " + statut + "\n" +
                "\n   Profil: " + profil + "\n" +
                "\n   Notifications: " + notifications +
                "\n   Calendrier: " + calendrier +
                "\n   Annonces: " + annonces +
                "\n   Locateurs: " + locateurs + "\n";
    }

}
