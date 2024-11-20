package com.agence.Gr3.frontend.Commandes.Utilisateurs;

import com.agence.Gr3.backend.Utilisateurs.Model.Permission;
import com.agence.Gr3.frontend.Commandes.Commande;
import com.agence.Gr3.frontend.Services.FormulairesUtilisateur;

import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//TODO Associer a la bonne methode.

@Component
public class ReinitialiserMdp implements Commande<StringBuilder, String> {

    private FormulairesUtilisateur formulairesUtilisateur;

    @Autowired
    public ReinitialiserMdp(FormulairesUtilisateur formulairesUtilisateur) {
        this.formulairesUtilisateur = formulairesUtilisateur;

    }

    @Override
    public String execute(List<Permission> permissions, Scanner scanner, StringBuilder JWT) {
        return formulairesUtilisateur.creerLocataire(permissions, scanner, JWT);

    }

}
