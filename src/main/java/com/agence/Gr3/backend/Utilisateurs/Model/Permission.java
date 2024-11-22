
package com.agence.Gr3.backend.Utilisateurs.Model;

import com.agence.Gr3.frontend.Commandes.Utilisateurs.*;
import com.agence.Gr3.frontend.Services.*;

public enum Permission {

    /**
     * Énumération de toutes les permissions existantes
     * 
     * 
     */

    CREER_UTILISATEUR("S'inscrire"),
    CONNEXION("Se connecter"),
    DECONNEXION("Se deconnecter"),
    MODIFIER_PROFIL("Modifier son profil"),
    QUITTER("Quitter l'application");

    private String description;

    Permission(String description) {
        this.description = description;

    }

    public String getDescription() {
        return description;
    }

}
