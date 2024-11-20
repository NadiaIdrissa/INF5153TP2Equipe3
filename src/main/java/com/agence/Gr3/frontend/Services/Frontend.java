package com.agence.Gr3.frontend.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.agence.Gr3.backend.Utilisateurs.Model.Permission;
import com.agence.Gr3.backend.Utilisateurs.Model.Role;
import com.agence.Gr3.frontend.Commandes.*;
import com.agence.Gr3.backend.Utilisateurs.Services.ServicePermissions;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

/**
 * Cette classe est un service qui simule le frontend de l'application.
 * 
 */

@Service
public class Frontend {

    Scanner scanner = new Scanner(System.in);

    // Objets requis et injectés par Springboot
    FormulairesUtilisateur serviceFrontendUtilisateur;
    ServicePermissions servicePermissions;

    @Autowired
    public Frontend(FormulairesUtilisateur serviceFrontendUtilisateur, ServicePermissions servicePermissions) {
        this.serviceFrontendUtilisateur = serviceFrontendUtilisateur;
        this.servicePermissions = servicePermissions;
    }

    public void displayMenu() {

        // Création du menu de base.

        // Variables globales servant de menu et d'identifiant pour l'utilisateur
        List<Permission> permissions = new ArrayList<Permission>(Role.INVITE.getPermissions()); // Options disponibles
        StringBuilder JWT = new StringBuilder("JWT"); // Modifié à la connexion et à la déconnexion

        while (true) {

            // SI ON NE QUITTE PAS L'APPLICATION LA BOUCLE CONTINUE
            // LA BOUCLE EST COMPOSÉ DE DEUX ÉLÉMENTS
            // 1) AFFICHAGE DES FONCTIONNALITÉS DISPONNIBLES POUR L'UTILISATEUR
            // 2) LA GESTION DU CHOIX DE CES OPTIONS.

            System.out.println("\nBienvenue sur le site de l'Agence Immobibilère");
            System.out.println("\nChoisir l'option de votre choix: ");

            // Affichage des permissions (menu)
            for (int i = 0; i < permissions.size(); i++) {
                System.out.println("Saisir " + i + ": pour l'option " + permissions.get(i).getDescription());

            }

            // Saisie de l'utilisateur et vérification
            String saisie = scanner.nextLine().trim();
            int choix = -1;

            try {

                if (saisie.isEmpty()) {
                    System.out.println("Saisie invalide! Veuillez recommencer.");
                    continue; // La boucle recommence si la saisie est vide.
                }

                choix = Integer.parseInt(saisie);

                if (choix < 0 || choix >= permissions.size()) {
                    System.out.println(
                            "Saisie invalide! Choisir une option entre 0 et " + (permissions.size() - 1) + ".");
                    continue;// La boucle recommence si la saisie n'est pas dans l'intervalle.
                }

            } catch (NumberFormatException e) {
                System.out.println("Saisie invalide! Veuillez recommencer.");
                continue;// La boucle recommence s'il n'est pas possible de convertir la saisie en int.

            }

            // UTILISATION DU PATRON DE COMMANDE

            // 1) Obtention de la permission à partir de la liste, en fonction du choix
            Permission permission = permissions.get(choix);

            // 2) Création d'une commande correspondant à la permission sélectionnée
            Commande<StringBuilder, String> commande = servicePermissions.getForPermission(permission);

            // 3) Exécution de la commande et affichage du résultat.
            String resultat = commande.execute(permissions, scanner, JWT).toString();
            System.out.println(resultat);

            // DEBUG
            System.out.println("Le JSON Web Token correspond à :" + JWT);

        }
    }

}
