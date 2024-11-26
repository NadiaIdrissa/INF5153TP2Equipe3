package com.agence.Gr3.backend.Utilisateurs.Model;

public class Director {

    public Builder creerLocataire(Builder builder, Identifiant identifiant) {
        builder.setRole(Role.LOCATAIRE);
        builder.setStatut("actif");
        builder.setIdentifiant(identifiant);
        return builder;

    }

    public Builder creerRepresentant(Builder builder, Identifiant identifiant) {
        builder.setRole(Role.REPRESENTANT);
        builder.setStatut("actif");
        builder.setIdentifiant(identifiant);
        return builder;

    }

}
