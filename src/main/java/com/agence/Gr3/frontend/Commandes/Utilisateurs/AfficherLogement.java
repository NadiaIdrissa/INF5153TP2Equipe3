package com.agence.Gr3.frontend.Commandes.Utilisateurs;

import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.agence.Gr3.backend.Utilisateurs.Model.Permission;
import com.agence.Gr3.frontend.Commandes.Commande;
import com.agence.Gr3.frontend.Services.FormulairesLogement;

@Component
public class AfficherLogement implements Commande <StringBuilder ,String> {

    private FormulairesLogement formulairesLogement;

    @Autowired
    public AfficherLogement(FormulairesLogement formulairesLogement) {
        this.formulairesLogement = formulairesLogement;

    }

    @Override
    public String execute(List<Permission> permissions, Scanner scanner, StringBuilder JWT) {
        return formulairesLogement.afficherLogement(permissions, scanner, JWT);

    }

}
