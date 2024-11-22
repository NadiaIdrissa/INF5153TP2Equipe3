package com.agence.Gr3.frontend.Commandes.Utilisateurs;

import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.agence.Gr3.backend.Utilisateurs.Model.Permission;
import com.agence.Gr3.frontend.Commandes.Commande;
import com.agence.Gr3.frontend.Services.FormulairesUtilisateur;

@Component
public class ConfirmerVisite implements Commande<StringBuilder, String> {

    private FormulairesUtilisateur formulairesUtilisateur;

    @Autowired
    public ConfirmerVisite(FormulairesUtilisateur formulairesUtilisateur) {
        this.formulairesUtilisateur = formulairesUtilisateur;

    }

    @Override
    public String execute(List<Permission> permissions, Scanner scanner, StringBuilder JWT) {
        return formulairesUtilisateur.confirmerVisite(permissions, scanner, JWT);

    }

}
