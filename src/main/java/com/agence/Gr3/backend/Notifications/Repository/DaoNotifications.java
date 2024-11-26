package com.agence.Gr3.backend.Notifications.Repository;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Repository;
import com.agence.Gr3.backend.Notifications.Model.*;

@Repository
public class DaoNotifications {

    private final Map<String, HistoriqueNotifications> historiqueNotifications = new HashMap<String, HistoriqueNotifications>();

    public HistoriqueNotifications inserer(String idUtilisateur, String notification) {
        HistoriqueNotifications historiqueUtilisateur = lire(idUtilisateur);
        historiqueUtilisateur.getNotifications().put(LocalDateTime.now(), notification);
        return lire(idUtilisateur);

    }

    public HistoriqueNotifications lire(String idUtilisateur) {
        return this.historiqueNotifications.get(idUtilisateur);

    }

}
