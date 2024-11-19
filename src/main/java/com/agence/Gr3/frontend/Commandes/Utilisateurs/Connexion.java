package com.agence.Gr3.frontend.Commandes.Utilisateurs;

import com.agence.Gr3.frontend.Commandes.Commande;
import com.agence.Gr3.frontend.Services.FormulairesUtilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.agence.Gr3.backend.Utilisateurs.Model.Permission;
import java.util.Scanner;
import java.util.List;

@Component
public class Connexion implements Commande<String, String> {

    private FormulairesUtilisateur formulairesUtilisateur;

    @Autowired
    public Connexion(FormulairesUtilisateur formulairesUtilisateur) {
        this.formulairesUtilisateur = formulairesUtilisateur;

    }

    @Override
    public String execute(List<Permission> permissions, Scanner scanner, String JWT) {
        return formulairesUtilisateur.seConnecter(permissions, scanner, JWT);

    }

}
