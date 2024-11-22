package com.agence.Gr3.backend.Logements.Model;

import com.agence.Gr3.Model.Adresse;

public class Logement {
    private int id;
    private String idRepresentant;
    private double nbrChambres;
    private double nbrSallesDeBain;
    private double superficie;
    private String description;
    private int dureeBail;
    private Adresse adresse;
    private boolean proprieteConfirmee;
    private boolean disponible;

    // Private constructor to ensure Logement can only be created via the factory
    protected Logement(int id, String idRepresentant, double nbrChambres, double nbrSallesDeBain,
            double superficie, String description, int dureeBail, Adresse adresse,
            boolean proprieteConfirmee, boolean disponible) {
        this.id = id;
        this.idRepresentant = idRepresentant;
        this.nbrChambres = nbrChambres;
        this.nbrSallesDeBain = nbrSallesDeBain;
        this.superficie = superficie;
        this.description = description;
        this.dureeBail = dureeBail;
        this.adresse = adresse;
        this.proprieteConfirmee = proprieteConfirmee;
        this.disponible = disponible;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIdRepresentant() {
        return idRepresentant;
    }

    public void setIdRepresentant(String idRepresentant) {
        this.idRepresentant = idRepresentant;
    }

    public double getNbrChambres() {
        return nbrChambres;
    }

    public void setNbrChambres(double nbrChambres) {
        this.nbrChambres = nbrChambres;
    }

    public double getNbrSallesDeBain() {
        return nbrSallesDeBain;
    }

    public void setNbrSallesDeBain(double nbrSallesDeBain) {
        this.nbrSallesDeBain = nbrSallesDeBain;
    }

    public double getSuperficie() {
        return superficie;
    }

    public void setSuperficie(double superficie) {
        this.superficie = superficie;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDureeBail() {
        return dureeBail;
    }

    public void setDureeBail(int dureeBail) {
        this.dureeBail = dureeBail;
    }

    public Adresse getAdresse() {
        return adresse;
    }

    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
    }

    public boolean isProprieteConfirmee() {
        return proprieteConfirmee;
    }

    public void setProprieteConfirmee(boolean proprieteConfirmee) {
        this.proprieteConfirmee = proprieteConfirmee;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    @Override
    public String toString() {
        return "Logement \n" +
                "id: " + id +
                "\n idRepresentant: " + idRepresentant +
                "\n nbrChambres: " + nbrChambres +
                "\n nbrSallesDeBain: " + nbrSallesDeBain +
                "\n superficie: " + superficie +
                "\n description: " + description +
                "\n dureeBail: " + dureeBail +
                "\n adresse:" + adresse +
                "\n proprieteConfirmee: " + proprieteConfirmee +
                "\n disponible: " + disponible;
    }
}