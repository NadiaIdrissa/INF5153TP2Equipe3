package com.agence.Gr3.backend.Utilisateurs.Services;

import org.springframework.stereotype.Service;
import com.agence.Gr3.frontend.Commandes.Utilisateurs.*;
import com.agence.Gr3.backend.Utilisateurs.Model.Permission;
import org.springframework.beans.factory.annotation.Autowired;
import com.agence.Gr3.frontend.Commandes.Commande;

/**
 * Ce service sert à faire la correspondance avec
 * 
 * 
 * 
 * 
 */

@Service
public class ServicePermissions {

    private final CreerUtilisateur creerUtilisateur;
    private final Connexion connexion;
    private final Deconnexion deconnexion;
    private final Quitter reinitialiserMdp;
    private final ModifierProfil modifierProfil;

    @Autowired
    public ServicePermissions(CreerUtilisateur creerUtilisateur,
            Connexion connexion,
            Deconnexion deconnexion,
            Quitter reinitialiserMdp,
            ModifierProfil modifierProfil) {
        this.creerUtilisateur = creerUtilisateur;
        this.connexion = connexion;
        this.deconnexion = deconnexion;
        this.reinitialiserMdp = reinitialiserMdp;
        this.modifierProfil = modifierProfil;
    }

    public Commande<StringBuilder, String> getForPermission(Permission permission) {
        switch (permission) {
            case CREER_UTILISATEUR:
                return creerUtilisateur;
            case CONNEXION:
                return connexion;
            case DECONNEXION:
                return deconnexion;
            case REINITIALISER_MDP:
                return reinitialiserMdp;
            case MODIFIER_PROFIL:
                return modifierProfil;
            default:
                throw new IllegalArgumentException("Unknown permission: " + permission);
        }
    }
}
