package com.agence.Gr3.backend.Notifications.Model;

import java.util.List;
import java.util.Map;
import java.time.LocalDateTime;

public class HistoriqueNotifications implements Observateur {

    private String idUtilisateur;
    private Map<LocalDateTime, String> notifications;

    @Override
    public void mettreAJour(String idUtilisateur, String notification) {
        // notifications.add(notification);

    }

    public Map<LocalDateTime, String> getNotifications() {
        return notifications;
    }

}
