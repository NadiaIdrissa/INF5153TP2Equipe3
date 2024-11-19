package com.agence.Gr3.backend.Utilisateurs.Model;

public class Identifiant {

    private String courriel;
    private String mdp;

    public Identifiant(String courriel, String mdp) {
        this.courriel = courriel;
        this.mdp = mdp;
    }

    public String getCourriel() {
        return courriel;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public String chiffrerMdp(String mdp) {
        return new String(java.util.Base64.getEncoder().encode(mdp.getBytes()));
    }
}
