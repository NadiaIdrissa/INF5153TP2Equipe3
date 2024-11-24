package com.agence.Gr3.backend.RendezVous.Model;

public enum Statut {

    ANNULE("a annulé/refusé le rendez-vous suivant: "),
    MODIFIE("propose une nouvelle date pour le rendez-vous suivant: "),
    CONFIRME("a confirmé le rendez-vous suivant: "),
    ATTENTE("est en attente de votre réponse pour le rendez-vous suivant: ");

    private final String text;

    Statut(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
