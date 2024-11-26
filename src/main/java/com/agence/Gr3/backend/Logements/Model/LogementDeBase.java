package com.agence.Gr3.backend.Logements.Model;

import com.agence.Gr3.Model.Adresse;

public class LogementDeBase implements Logement {

    private int id;
    private String idGerant;
    private Boolean chauffage;
    private Boolean climatisation;
    private Boolean electromenagers;
    private Boolean wifi;
    private Boolean semiMeuble;
    private Boolean meuble;
    private Double taille;
    private Double prix;
    private Adresse adresse;

    protected LogementDeBase(int id, String idGerant, Adresse adresse) {
        this.id = id;
        this.idGerant = idGerant;
        this.adresse = adresse;

        // Valeurs par défaut
        this.chauffage = false;
        this.climatisation = false;
        this.electromenagers = false;
        this.wifi = false;
        this.semiMeuble = false;
        this.meuble = false;
        this.taille = 1.0;
        this.prix = 1000.0;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String getIdGerant() {
        return idGerant;
    }

    @Override
    public void setIdGerant(String idGerant) {
        this.idGerant = idGerant;
    }

    @Override
    public Boolean getChauffage() {
        return chauffage;
    }

    @Override
    public void setChauffage(Boolean chauffage) {
        this.chauffage = chauffage;
    }

    @Override
    public Boolean getClimatisation() {
        return climatisation;
    }

    @Override
    public void setClimatisation(Boolean climatisation) {
        this.climatisation = climatisation;
    }

    @Override
    public Boolean getElectromenagers() {
        return electromenagers;
    }

    @Override
    public void setElectromenagers(Boolean electromenagers) {
        this.electromenagers = electromenagers;
    }

    @Override
    public Boolean getWifi() {
        return wifi;
    }

    @Override
    public void setWifi(Boolean wifi) {
        this.wifi = wifi;
    }

    @Override
    public Boolean getSemiMeuble() {
        return semiMeuble;
    }

    @Override
    public void setSemiMeuble(Boolean semiMeuble) {
        this.semiMeuble = semiMeuble;
    }

    @Override
    public Boolean getMeuble() {
        return meuble;
    }

    @Override
    public void setMeuble(Boolean meuble) {
        this.meuble = meuble;
    }

    @Override
    public Double getTaille() {
        return taille;
    }

    @Override
    public void setTaille(Double taille) {
        this.taille = taille;
    }

    @Override
    public Double getPrix() {
        return prix;
    }

    @Override
    public void setPrix(Double prix) {
        this.prix = prix;
    }

    @Override
    public Adresse getAdresse() {
        return adresse;
    }

    @Override
    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
    }

    @Override
    public String toString() {
        return "Logement {" +
                "id=" + id +
                ", idGerant='" + idGerant + '\'' +
                ", chauffage=" + chauffage +
                ", climatisation=" + climatisation +
                ", electromenagers=" + electromenagers +
                ", wifi=" + wifi +
                ", semiMeuble=" + semiMeuble +
                ", meuble=" + meuble +
                ", taille=" + taille +
                ", prix=" + prix +
                ", adresse=" + adresse +
                '}';
    }

}
