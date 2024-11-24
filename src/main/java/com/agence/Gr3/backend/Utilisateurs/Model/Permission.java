
package com.agence.Gr3.backend.Utilisateurs.Model;

public enum Permission {

    /**
     * Énumération de toutes les permissions existantes
     * 
     * 
     */

    CREER_UTILISATEUR("S'inscrire"),
    CONNEXION("Se connecter"),
    DECONNEXION("Se deconnecter"),
    CREER_LOGEMENT("Ajouter un logement"),
    MODIFIER_LOGEMENT("Modifier un logement"),
    AFFICHER_LOGEMENT("Afficher mes logements"),
    AFFICHER_TOUS_LOGEMENTS("Afficher les logements"),
    MODIFIER_PROFIL("Modifier son profil"),
    CREER_VISITE("Demander une visite"),
    CONFIRMER_VISITE("Confirmer une visite"),
    ANNULER_VISITE("Annuler une visite"),
    MODIFIER_VISITE("Modifier une visite"),
    QUITTER("Quitter l'application");

    private String description;

    Permission(String description) {
        this.description = description;

    }

    public String getDescription() {
        return description;
    }

}
