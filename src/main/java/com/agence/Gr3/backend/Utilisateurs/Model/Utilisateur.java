package com.agence.Gr3.backend.Utilisateurs.Model;

import java.util.List;

public class Utilisateur {

    private final Identifiant identifiant;
    private final Role role;
    private final String statut;
    private final Profil profil;
    private final List<String> notifications;
    private final List<String> calendrier;
    private final List<String> annonces;
    private final List<String> locateurs;

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

    @Override
    public String toString() {
        return "CompteUtilisateur{" +
                "identifiant=" + identifiant +
                ", role=" + role +
                ", statut='" + statut + '\'' +
                ", profil=" + profil +
                ", notifications=" + notifications +
                ", calendrier=" + calendrier +
                ", annonces=" + annonces +
                ", locateurs=" + locateurs +
                '}';
    }

}
