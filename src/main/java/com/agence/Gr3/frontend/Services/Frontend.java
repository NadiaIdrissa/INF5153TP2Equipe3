package com.agence.Gr3.frontend.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.agence.Gr3.backend.Utilisateurs.Model.Permission;
import com.agence.Gr3.backend.Utilisateurs.Model.Role;
import com.agence.Gr3.frontend.Commandes.*;
import com.agence.Gr3.backend.Utilisateurs.Services.ServicePermissions;
import java.util.Scanner;
import java.util.List;

/**
 * Cette classe est un service qui simule le frontend de l'application.
 * 
 */

@Service
public class Frontend {

    Scanner scanner = new Scanner(System.in);

    ServiceFrontendUtilisateur serviceFrontendUtilisateur;
    ServicePermissions servicePermissions;

    @Autowired
    public Frontend(ServiceFrontendUtilisateur serviceFrontendUtilisateur, ServicePermissions servicePermissions) {
        this.serviceFrontendUtilisateur = serviceFrontendUtilisateur;
        this.servicePermissions = servicePermissions;
    }

    public void displayMenu() {

        // Création du menu de base.
        List<Permission> menu = Role.INVITE.getPermissions();
        String JWT = "JWT";

        while (true) {
            System.out.println("\nBienvenue,veuillez faire un choix");

            System.out.println(Role.INVITE);

            // Simply call nextLine and trim the result
            String selection = scanner.nextLine().trim(); // Waits for input
            int choix = menu.size() - 1;

            if (selection.isEmpty()) {
                System.out.println("Invalid input! Please choose a valid option.");
                continue;
            }

            try {
                choix = Integer.parseInt(selection);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a valid integer.");
            }

            Permission permission = menu.get(choix);

            Commande<String, String> commande = servicePermissions.getForPermission(permission);

            String resultat = commande.execute(scanner, JWT).toString();

        }
    }
}
