package com.agence.Gr3.backend.Logements.Model;

import com.agence.Gr3.Model.Adresse;

public class DecorateurTaille4 implements Logement {

    private Logement logementDeBase;
    private double supplement = 750.00;
    private double taille = 4.5;

    public DecorateurTaille4(Logement logementDeBase) {
        this.logementDeBase = logementDeBase;
    }

    @Override
    public int getId() {
        return this.logementDeBase.getId();
    }

    @Override
    public void setId(int id) {
        this.logementDeBase.setId(id);
    }

    @Override
    public String getIdGerant() {
        return this.logementDeBase.getIdGerant();
    }

    @Override
    public void setIdGerant(String idGerant) {
        this.logementDeBase.setIdGerant(idGerant);
    }

    @Override
    public Boolean getChauffage() {
        return this.logementDeBase.getChauffage();
    }

    @Override
    public void setChauffage(Boolean chauffage) {
        this.logementDeBase.setChauffage(chauffage);
    }

    @Override
    public Boolean getClimatisation() {
        return this.logementDeBase.getClimatisation();
    }

    @Override
    public void setClimatisation(Boolean climatisation) {
        this.logementDeBase.setClimatisation(climatisation);
    }

    @Override
    public Boolean getElectromenagers() {
        return this.logementDeBase.getElectromenagers();
    }

    @Override
    public void setElectromenagers(Boolean electromenagers) {
        this.logementDeBase.setElectromenagers(electromenagers);
    }

    @Override
    public Boolean getWifi() {
        return this.logementDeBase.getWifi();
    }

    @Override
    public void setWifi(Boolean wifi) {
        this.logementDeBase.setWifi(wifi);
    }

    @Override
    public Boolean getSemiMeuble() {
        return this.logementDeBase.getSemiMeuble();
    }

    @Override
    public void setSemiMeuble(Boolean semiMeuble) {
        this.logementDeBase.setSemiMeuble(semiMeuble);
    }

    @Override
    public Boolean getMeuble() {
        return this.logementDeBase.getMeuble();
    }

    @Override
    public void setMeuble(Boolean meuble) {
        this.logementDeBase.setMeuble(meuble);
    }

    // Modification
    @Override
    public Double getTaille() {
        return this.taille;
    }

    @Override
    public void setTaille(Double taille) {
        // Ne rien faire
    }

    // Modification
    @Override
    public Double getPrix() {
        return this.logementDeBase.getPrix() + supplement;
    }

    @Override
    public void setPrix(Double prix) {
        // Ne rien faire
    }

    @Override
    public Adresse getAdresse() {
        return this.logementDeBase.getAdresse();
    }

    @Override
    public void setAdresse(Adresse adresse) {
        this.logementDeBase.setAdresse(adresse);
    }

}
