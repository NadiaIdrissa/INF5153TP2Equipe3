package com.agence.Gr3.frontend.Commandes.Utilisateurs;

import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.agence.Gr3.backend.Utilisateurs.Model.Permission;
import com.agence.Gr3.frontend.Commandes.Commande;
import com.agence.Gr3.frontend.Services.FormulairesRdv;

@Component
public class AfficherVisites implements Commande<StringBuilder, String> {

    private FormulairesRdv formulairesRdv;

    @Autowired
    public AfficherVisites(FormulairesRdv formulairesRdv) {
        this.formulairesRdv = formulairesRdv;

    }

    @Override
    public String execute(List<Permission> permissions, Scanner scanner, StringBuilder JWT) {
        return formulairesRdv.afficherVisites(permissions, scanner, JWT);

    }

}
