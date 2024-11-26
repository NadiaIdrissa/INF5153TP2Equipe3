package com.agence.Gr3.backend.Notifications.Services;

import org.springframework.stereotype.Service;

import com.agence.Gr3.backend.Notifications.Model.*;
import com.agence.Gr3.backend.Notifications.Repository.DaoNotifications;
import com.agence.Gr3.backend.RendezVous.Services.ServiceRdv;

@Service
public class ServiceNotification implements Observateur {

    private DaoNotifications daoNotifications;

    private ServiceNotification(DaoNotifications daoNotifications) {
        this.daoNotifications = daoNotifications;
    }

    @Override
    public void mettreAJour(String idUtilisateur, String notification) {

        ajouterNotifications(idUtilisateur, notification);

    }

    public HistoriqueNotifications ajouterNotifications(String courriel, String notification) {
        return daoNotifications.inserer(courriel, notification);

    }

    public HistoriqueNotifications afficherNotifications(String courriel) {
        return daoNotifications.lire(courriel);

    }

}
