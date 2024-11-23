package com.agence.Gr3.backend.Logements.Model;

import com.agence.Gr3.Model.Adresse;

public interface Logement {

    public int getId();

    public void setId(int id);

    public String getIdGerant();

    public void setIdGerant(String idGerant);

    public Boolean getChauffage();

    public void setChauffage(Boolean chauffage);

    public Boolean getClimatisation();

    public void setClimatisation(Boolean climatisation);

    public Boolean getElectromenagers();

    public void setElectromenagers(Boolean electromenagers);

    public Boolean getWifi();

    public void setWifi(Boolean wifi);

    public Boolean getSemiMeuble();

    public void setSemiMeuble(Boolean semiMeuble);

    public Boolean getMeuble();

    public void setMeuble(Boolean meuble);

    public Double getTaille();

    public void setTaille(Double taille);

    public Double getPrix();

    public void setPrix(Double prix);

    public Adresse getAdresse();

    public void setAdresse(Adresse adresse);

}
