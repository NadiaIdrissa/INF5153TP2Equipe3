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
public class ServicePermission {

    private final CreerUtilisateur creerUtilisateur;
    private final Connexion connexion;
    private final Deconnexion deconnexion;
    private final CreerLogement creerLogement;
    private final ModifierLogement modifierLogement;
    private final AfficherLogement afficherLogement;
    private final ModifierProfil modifierProfil;
    private final CreerVisite creerVisite;
    private final ConfirmerVisite confirmerVisite;
    private final CreerAnnonce creerAnnonce;
    private final ModifierAnnonce modifierAnnonce;

    @Autowired
    public ServicePermission(
            CreerUtilisateur creerUtilisateur,
            Connexion connexion,
            Deconnexion deconnexion,
            CreerLogement creerLogement,
            ModifierLogement modifierLogement,
            AfficherLogement afficherLogement,
            ModifierProfil modifierProfil,
            CreerVisite creerVisite,
            ConfirmerVisite confirmerVisite,
            CreerAnnonce creerAnnonce,
            ModifierAnnonce modifierAnnonce) {
        this.creerUtilisateur = creerUtilisateur;
        this.connexion = connexion;
        this.deconnexion = deconnexion;
        this.creerLogement = creerLogement;
        this.modifierLogement = modifierLogement;
        this.afficherLogement = afficherLogement;
        this.modifierProfil = modifierProfil;
        this.creerVisite = creerVisite;
        this.confirmerVisite = confirmerVisite;
        this.creerAnnonce = creerAnnonce;
        this.modifierAnnonce = modifierAnnonce;
    }

    public Commande<StringBuilder, String> getForPermission(Permission permission) {
        switch (permission) {
            case CREER_UTILISATEUR:
                return creerUtilisateur;
            case CONNEXION:
                return connexion;
            case DECONNEXION:
                return deconnexion;
            case CREER_LOGEMENT:
                return creerLogement;
            case MODIFIER_LOGEMENT:
                return modifierLogement;
            case AFFICHER_LOGEMENT:
                return afficherLogement;
            case MODIFIER_PROFIL:
                return modifierProfil;
            case CREER_VISITE:
                return creerVisite;
            case CONFIRMER_VISITE:
                return confirmerVisite;
            case CREER_ANNONCE:
                return creerAnnonce;
            case MODIFIER_ANNONCE:
                return modifierAnnonce;

            default:
                throw new IllegalArgumentException("Unknown permission: " + permission);
        }
    }
}
