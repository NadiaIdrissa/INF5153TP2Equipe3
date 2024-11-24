package com.agence.Gr3.backend.Notifications.Model;

import java.util.List;

public class HistoriqueNotifications implements Observateur {

    private String idUtilisateur;
    private List<String> notifications;

    @Override
    public void mettreAJour(String modification) {
        notifications.add(modification);

    }

    public List<String> getNotifications() {
        return notifications;
    }

}
