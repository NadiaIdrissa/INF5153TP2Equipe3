package com.agence.Gr3.frontend.Commandes.Utilisateurs;

import com.agence.Gr3.frontend.Commandes.Commande;
import com.agence.Gr3.frontend.Services.ServiceFrontendUtilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Deconnexion implements Commande<String, String> {

    private ServiceFrontendUtilisateur serviceFrontendUtilisateur;

    @Autowired
    public Deconnexion(ServiceFrontendUtilisateur serviceFrontendUtilisateur) {
        this.serviceFrontendUtilisateur = serviceFrontendUtilisateur;

    }

    @Override
    public String execute(String jwt) {

        String resultat = "fonctionne";

        return serviceFrontendUtilisateur.creerLocataire();

    }

}
