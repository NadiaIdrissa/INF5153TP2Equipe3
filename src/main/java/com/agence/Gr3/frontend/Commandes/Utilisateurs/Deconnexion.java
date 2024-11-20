package com.agence.Gr3.frontend.Commandes.Utilisateurs;

import com.agence.Gr3.backend.Utilisateurs.Model.Permission;
import com.agence.Gr3.frontend.Commandes.Commande;
import com.agence.Gr3.frontend.Services.FormulairesUtilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

//TODO Associer a la bonne methode.

@Component
public class Deconnexion implements Commande<StringBuilder, String> {

    private FormulairesUtilisateur formulairesUtilisateur;

    @Autowired
    public Deconnexion(FormulairesUtilisateur formulairesUtilisateur) {
        this.formulairesUtilisateur = formulairesUtilisateur;

    }

    @Override
    public String execute(List<Permission> permissions, Scanner scanner, StringBuilder JWT) {
        return formulairesUtilisateur.seDeconnecter(permissions, scanner, JWT);

    }

}
