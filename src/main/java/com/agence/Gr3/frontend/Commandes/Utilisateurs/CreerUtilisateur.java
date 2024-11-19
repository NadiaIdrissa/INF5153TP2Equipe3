package com.agence.Gr3.frontend.Commandes.Utilisateurs;

import com.agence.Gr3.frontend.Commandes.Commande;
import com.agence.Gr3.frontend.Services.ServiceFrontendUtilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.Scanner;

@Component
public class CreerUtilisateur implements Commande<String, String> {

    private ServiceFrontendUtilisateur serviceFrontendUtilisateur;

    @Autowired
    public CreerUtilisateur(ServiceFrontendUtilisateur serviceFrontendUtilisateur) {
        this.serviceFrontendUtilisateur = serviceFrontendUtilisateur;

    }

    @Override
    public String execute(Scanner scanner, String JWT) {
        return serviceFrontendUtilisateur.creerLocataire(scanner, JWT);

    }

}
