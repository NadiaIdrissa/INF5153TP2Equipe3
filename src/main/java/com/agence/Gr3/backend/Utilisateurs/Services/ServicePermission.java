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
    private final AfficherTousLogements afficherTousLogements;
    private final ModifierProfil modifierProfil;
    private final CreerVisite creerVisite;
    private final ConfirmerVisite confirmerVisite;
    private final AnnulerVisite annulerVisite;
    private final ModifierVisite modifierVisite;
    private final AfficherVisites afficherVisites;
    private final AfficherNotifications afficherNotifications;

    @Autowired
    public ServicePermission(
            CreerUtilisateur creerUtilisateur,
            Connexion connexion,
            Deconnexion deconnexion,
            CreerLogement creerLogement,
            ModifierLogement modifierLogement,
            AfficherLogement afficherLogement,
            AfficherTousLogements afficherTousLogements,
            ModifierProfil modifierProfil,
            CreerVisite creerVisite,
            ConfirmerVisite confirmerVisite,
            AnnulerVisite annulerVisite,
            ModifierVisite modifierVisite,
            AfficherVisites afficherVisites,
            AfficherNotifications afficherNotifications) {
        this.creerUtilisateur = creerUtilisateur;
        this.connexion = connexion;
        this.deconnexion = deconnexion;
        this.creerLogement = creerLogement;
        this.modifierLogement = modifierLogement;
        this.afficherLogement = afficherLogement;
        this.afficherTousLogements = afficherTousLogements;
        this.modifierProfil = modifierProfil;
        this.creerVisite = creerVisite;
        this.confirmerVisite = confirmerVisite;
        this.annulerVisite = annulerVisite;
        this.modifierVisite = modifierVisite;
        this.afficherVisites = afficherVisites;
        this.afficherNotifications = afficherNotifications;

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
            case AFFICHER_TOUS_LOGEMENTS:
                return afficherTousLogements;
            case MODIFIER_PROFIL:
                return modifierProfil;
            case CREER_VISITE:
                return creerVisite;
            case ANNULER_VISITE:
                return annulerVisite;
            case MODIFIER_VISITE:
                return modifierVisite;
            case CONFIRMER_VISITE:
                return confirmerVisite;
            case AFFICHER_VISITES:
                return afficherVisites;
            case AFFICHER_NOTIFICATIONS:
                return afficherNotifications;

            default:
                throw new IllegalArgumentException("Unknown permission: " + permission);
        }
    }
}
