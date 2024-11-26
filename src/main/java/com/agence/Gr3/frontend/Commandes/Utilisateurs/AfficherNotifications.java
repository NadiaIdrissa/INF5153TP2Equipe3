package com.agence.Gr3.frontend.Commandes.Utilisateurs;

import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.agence.Gr3.frontend.Services.FormulairesNotification;
import com.agence.Gr3.backend.Utilisateurs.Model.Permission;
import com.agence.Gr3.frontend.Commandes.Commande;

@Component
public class AfficherNotifications implements Commande<StringBuilder, String> {

    private FormulairesNotification formulairesNotification;

    @Autowired
    public AfficherNotifications(FormulairesNotification formulairesNotification) {
        this.formulairesNotification = formulairesNotification;

    }

    @Override
    public String execute(List<Permission> permissions, Scanner scanner, StringBuilder JWT) {
        return formulairesNotification.afficherNotifications(permissions, scanner, JWT);

    }
}
