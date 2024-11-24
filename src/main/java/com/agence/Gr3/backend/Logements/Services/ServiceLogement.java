package com.agence.Gr3.backend.Logements.Services;

import org.springframework.stereotype.Service;

import com.agence.Gr3.Model.Adresse;
import com.agence.Gr3.backend.Logements.Model.DecorateurChauffage;
import com.agence.Gr3.backend.Logements.Model.DecorateurClimatisation;
import com.agence.Gr3.backend.Logements.Model.DecorateurElectromenagers;
import com.agence.Gr3.backend.Logements.Model.DecorateurMeuble;
import com.agence.Gr3.backend.Logements.Model.DecorateurSemiMeuble;
import com.agence.Gr3.backend.Logements.Model.DecorateurTaille2;
import com.agence.Gr3.backend.Logements.Model.DecorateurTaille3;
import com.agence.Gr3.backend.Logements.Model.DecorateurTaille4;
import com.agence.Gr3.backend.Logements.Model.DecorateurTaille5;
import com.agence.Gr3.backend.Logements.Model.DecorateurWifi;
import com.agence.Gr3.backend.Logements.Model.FabriqueLogementDeBase;
import com.agence.Gr3.backend.Logements.Repository.DaoLogement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import com.agence.Gr3.backend.Logements.Model.LogementDeBase;
import com.agence.Gr3.backend.Logements.Model.Logement;

@Service
public class ServiceLogement {

    private static AtomicInteger atomicId = new AtomicInteger(0);
    private DaoLogement daoLogement;
    private FabriqueLogementDeBase fabriqueLogementDeBase;

    private ServiceLogement(DaoLogement daoLogement, FabriqueLogementDeBase fabriqueLogementDeBase) {
        this.daoLogement = daoLogement;
        this.fabriqueLogementDeBase = fabriqueLogementDeBase;
    }

    public Logement creer(String courriel, Map<String, Object> requestBody) {

        int id = atomicId.incrementAndGet();

        Map<String, Object> decorateurs = (Map<String, Object>) requestBody.get("decorateurs");
        Map<String, Object> adresse = (Map<String, Object>) requestBody.get("adresse");

        int noCivique = (Integer) adresse.get("noCivique");
        int suite = (Integer) adresse.get("suite");
        String rue = adresse.get("rue").toString().trim();
        String codePostal = adresse.get("codePostal").toString().trim();
        String ville = adresse.get("ville").toString().trim();
        String province = adresse.get("province").toString().trim();

        // Création d'un logement de base
        Adresse adresseLogement = new Adresse(noCivique, suite, rue, codePostal, ville, province);
        LogementDeBase logementDeBase = fabriqueLogementDeBase.creerLogement(id, courriel, adresseLogement);

        // Décoration du logement de base
        Logement logement = decorer(decorateurs, logementDeBase);

        return daoLogement.inserer(id, logement);

    }

    public Logement modifier(int id, String courriel, Map<String, Object> requestBody) {

        Map<String, Object> decorateurs = (Map<String, Object>) requestBody.get("decorateurs");
        Map<String, Object> adresse = (Map<String, Object>) requestBody.get("adresse");

        int noCivique = (Integer) adresse.get("noCivique");
        int suite = (Integer) adresse.get("suite");
        String rue = adresse.get("rue").toString().trim();
        String codePostal = adresse.get("codePostal").toString().trim();
        String ville = adresse.get("ville").toString().trim();
        String province = adresse.get("province").toString().trim();

        // Création d'un logement de base
        Adresse adresseLogement = new Adresse(noCivique, suite, rue, codePostal, ville, province);
        LogementDeBase logementDeBase = fabriqueLogementDeBase.creerLogement(id, courriel, adresseLogement);

        System.out.println("Après la fabrique" + logementDeBase);

        // Décoration du logement de base
        Logement logement = decorer(decorateurs, logementDeBase);

        System.out.println(logement);

        return daoLogement.modifier(id, logement);

    }

    private Logement decorer(Map<String, Object> decorateurs, LogementDeBase logement) {

        Logement logementDecore = logement;

        System.out.println("Nos decorateurs" + decorateurs);

        try {

            for (Map.Entry<String, Object> entry : decorateurs.entrySet()) {
                String decoratorKey = entry.getKey();
                Boolean decoratorValue = (Boolean) entry.getValue();

                System.out.println("Notre logement decore au debut: " + logementDecore);

                if (decoratorValue != null && decoratorValue) {

                    System.out.println("Notre logement decore au pendant: " + logementDecore);
                    System.out.println("Son nouveau prix :" + logementDecore.getPrix());
                    System.out.println("decoratorValue" + decoratorValue);
                    System.out.println("decoratorKey" + decoratorKey);

                    switch (decoratorKey) {
                        case "chauffage":
                            logementDecore = new DecorateurChauffage(logementDecore);
                            break;
                        case "climatisation":
                            logementDecore = new DecorateurClimatisation(logementDecore);
                            break;
                        case "electromenagers":
                            logementDecore = new DecorateurElectromenagers(logementDecore);
                            break;
                        case "wifi":
                            logementDecore = new DecorateurWifi(logementDecore);
                            break;
                        case "semiMeuble":
                            logementDecore = new DecorateurSemiMeuble(logementDecore);
                            break;
                        case "meuble":
                            logementDecore = new DecorateurMeuble(logementDecore);
                            break;
                        case "taille2":
                            logementDecore = new DecorateurTaille2(logementDecore);
                            break;
                        case "taille3":
                            logementDecore = new DecorateurTaille3(logementDecore);
                            break;
                        case "taille4":
                            logementDecore = new DecorateurTaille4(logementDecore);
                            break;
                        case "taille5":
                            logementDecore = new DecorateurTaille5(logementDecore);
                            break;
                        default:
                            System.out.println("DécorateurInconnu: " + decoratorKey);
                    }
                }
            }

            System.out.println("Notre logement decore a la fin: " + logementDecore);

        } catch (Exception e) {
            System.out.println("Exception while decorating: " + e.getMessage());
            e.printStackTrace();
        }

        return logementDecore;

    }

    /**
     * Récupère les logements associés à l'utilisateur
     * 
     * @return la liste de tous les logements associés à l'utilisateur
     */
    public List<Logement> rechercher(String courriel) {

        return daoLogement.rechercher(courriel);

    }

    /**
     * Récupère les logements.
     * 
     * @return
     */
    public List<Logement> rechercherTous() {

        return daoLogement.rechercherTous();

    }

    /**
     * Récupère les logements.
     * 
     * @return
     */
    public Logement rechercherUnique(int id) {

        return daoLogement.lire(id);

    }

}