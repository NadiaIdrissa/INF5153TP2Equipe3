package com.agence.Gr3.backend.Utilisateurs.Model;

import com.agence.Gr3.Model.Adresse;

public class Profil {

    private int id;
    private String nom;
    private String prenom;
    private String telephone;
    private Adresse adresse;

    public Profil(int id, String nom, String prenom, String telephone, Adresse adresse) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.telephone = telephone;
        this.adresse = adresse;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String telephone) {
        this.telephone = telephone;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String prenom) {
        this.prenom = prenom;
    }

    public Adresse getAdresse() {
        return adresse;
    }

    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
    }

    /*
     * @Override
     * public String toString() {
     * return "Profil{" +
     * "id=" + id +
     * ", nom='" + nom + '\'' +
     * ", prenom='" + prenom + '\'' +
     * ", adresse=" + adresse +
     * '}';
     * }
     * 
     */
}
