package com.agence.Gr3.backend.Utilisateurs.Model;

import java.util.List;

public interface Builder {

    Builder setIdentifiant(Identifiant identifiant);

    Builder setRole(Role role);

    Builder setStatut(String statut);

    Builder setProfil(Profil profil);

    Builder setNotifications(List<String> notifications);

    Builder setCalendrier(List<String> calendrier);

    Builder setAnnonces(List<String> annonces);

    Builder setLocateurs(List<String> locateurs);

    Utilisateur build();

}
