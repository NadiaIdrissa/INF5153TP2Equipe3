package com.agence.Gr3.backend.RendezVous.Model;

public enum Statut {

    ANNULE("a annulé le rendez-vous suivant: "),
    REFUSE("a refusé le rendez-vous suivant: "),
    MODIFIE("propose une nouvelle date pour le rendez-vous suivant: "),
    CONFIRME("a confirmé le rendez-vous suivant: "),
    ATTENTE("est en attente de votre réponse pour le rendez-vous suivant: ");

    // Field to store the associated text
    private final String text;

    // Constructor to assign the text to each enum constant
    Statut(String text) {
        this.text = text;
    }

    // Getter method to retrieve the associated text
    public String getText() {
        return text;
    }
}
