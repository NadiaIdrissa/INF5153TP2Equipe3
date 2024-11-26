package com.agence.Gr3.backend.RendezVous.Model;

import com.agence.Gr3.backend.Notifications.Model.Observateur;

public interface Sujet {

    void ajouterObservateur(Observateur observateur);

    void retirerObservateur(Observateur observateur);

    void notifierObservateurs(String idUtilisateur, String notification);

}
