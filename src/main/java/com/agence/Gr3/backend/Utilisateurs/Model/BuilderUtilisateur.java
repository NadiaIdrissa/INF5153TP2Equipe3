package com.agence.Gr3.backend.Utilisateurs.Model;

import java.util.List;

public class BuilderUtilisateur {

    private Identifiant identifiant;
    private Role role;
    private String statut;
    private Profil profil;
    private List<String> notifications;
    private List<String> calendrier;
    private List<String> annonces;
    private List<String> locateurs;

    // Attributs minimums requis pour un compte d'utilisateur
    public BuilderUtilisateur(Identifiant identifiant, Role role) {
        this.identifiant = identifiant;
        this.role = role;
        this.statut = (role == Role.LOCATAIRE || role == Role.INVITE) ? "actif" : "inactif";

    }

    public BuilderUtilisateur setNotifications(List<String> notifications) {
        this.notifications = notifications;
        return this;
    }

    public BuilderUtilisateur setIdentifiant(Identifiant identifiant) {
        this.identifiant = identifiant;
        return this;
    }

    public BuilderUtilisateur setCalendrier(List<String> calendrier) {
        this.calendrier = calendrier;
        return this;
    }

    public BuilderUtilisateur setAnnonces(List<String> annonces) {
        this.annonces = annonces;
        return this;
    }

    public BuilderUtilisateur setLocateurs(List<String> locateurs) {
        this.locateurs = locateurs;
        return this;
    }

    public String getStatut() {
        return statut;
    }

    public Profil getProfil() {
        return profil;
    }

    public Role getRole() {
        return role;
    }

    public List<String> getNotifications() {
        return notifications;
    }

    public Identifiant getIdentifiant() {
        return identifiant;
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

    public Utilisateur build() {
        return new Utilisateur(this);
    }

}
