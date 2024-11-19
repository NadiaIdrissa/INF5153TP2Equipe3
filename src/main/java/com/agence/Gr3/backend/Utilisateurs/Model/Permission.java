
package com.agence.Gr3.backend.Utilisateurs.Model;

import com.agence.Gr3.frontend.Commandes.Utilisateurs.*;
import com.agence.Gr3.frontend.Services.*;

public enum Permission {

    /**
     * Énumération de toutes les permissions existantes
     * 
     * 
     */

    CREER_UTILISATEUR("Cree un utilisateur"),
    CONNEXION("Connecte l'utilisateur"),
    DECONNEXION("Deconnecte l'utilisateur"),
    REINITIALISER_MDP("Reinitialise le mot de passe"),
    MODIFIER_PROFIL("Crée un utilisateur"),
    QUITTER("Quitter l'application");

    private String description;

    Permission(String description) {
        this.description = description;

    }

    public String getDescription() {
        return description;
    }

}
