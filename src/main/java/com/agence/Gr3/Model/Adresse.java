package com.agence.Gr3.Model;

public class Adresse {

    private int noCivique;
    private int suite;
    private String rue;
    private String codePostal;
    private String ville;
    private String province;

    public Adresse(int noCivique, int suite, String rue, String codePostal, String ville, String province) {
        this.noCivique = noCivique;
        this.suite = suite;
        this.rue = rue;
        this.codePostal = codePostal;
        this.ville = ville;
        this.province = province;
    }

    public int getNoCivique() {
        return noCivique;
    }

    public void setNoCivique(int noCivique) {
        this.noCivique = noCivique;
    }

    public int getSuite() {
        return suite;
    }

    public void setSuite(int suite) {
        this.suite = suite;
    }

    public String getRue() {
        return rue;
    }

    public void setRue(String rue) {
        this.rue = rue;
    }

    public String getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    @Override
    public String toString() {
        return "\n\tNo Civique: " + noCivique +
                "\n\tSuite: " + suite +
                "\n\tRue: " + rue +
                "\n\tCode Postal: " + codePostal +
                "\n\tVille: " + ville +
                "\n\tProvince: " + province;
    }
}
