package com.agence.Gr3.backend.Logements.Services;

import org.springframework.stereotype.Service;

import com.agence.Gr3.Model.Adresse;
import com.agence.Gr3.backend.Logements.Model.FabriqueLogement;
import com.agence.Gr3.backend.Logements.Repository.DaoLogement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import com.agence.Gr3.backend.Logements.Model.Logement;;

@Service
public class ServiceLogement {

    private static AtomicInteger atomicId = new AtomicInteger(0);
    private DaoLogement daoLogement;
    private FabriqueLogement fabriqueLogement;

    private ServiceLogement(DaoLogement daoLogement, FabriqueLogement fabriqueLogement) {
        this.daoLogement = daoLogement;
        this.fabriqueLogement = fabriqueLogement;
    }

    public Logement creer(String courriel, Map<String, Object> requestBody) {

        int id = atomicId.incrementAndGet();
        double nbrChambres = (Double) requestBody.get("nbrChambres");
        double nbrSallesDeBain = (Double) requestBody.get("nbrSallesDeBain");
        double superficie = (Double) requestBody.get("superficie");
        String description = requestBody.get("description").toString().trim();
        int dureeBail = (Integer) requestBody.get("dureeBail");
        Boolean proprieteConfirmee = true;
        Boolean disponible = true;

        int noCivique = (Integer) requestBody.get("noCivique");
        int suite = (Integer) requestBody.get("suite");
        String rue = requestBody.get("rue").toString().trim();
        String codePostal = requestBody.get("codePostal").toString().trim();
        String ville = requestBody.get("ville").toString().trim();
        String province = requestBody.get("province").toString().trim();
        Adresse adresse = new Adresse(noCivique, suite, rue, codePostal, ville, province);

        Logement logement = fabriqueLogement.creerLogement(id, courriel, nbrChambres, nbrSallesDeBain, superficie,
                description, dureeBail, adresse, proprieteConfirmee, disponible);

        System.out.println(logement);

        return daoLogement.inserer(id, logement); // daoLogement.inserer(id, logement);

    }

    public Logement modifier(int id, String courriel, Map<String, Object> requestBody) {

        System.out.println("methode: serviceLogement modifier"); // DEBUG

        double nbrChambres = (Double) requestBody.get("nbrChambres");
        double nbrSallesDeBain = (Double) requestBody.get("nbrSallesDeBain");
        double superficie = (Double) requestBody.get("superficie");
        String description = requestBody.get("description").toString().trim();
        int dureeBail = (Integer) requestBody.get("dureeBail");
        Boolean proprieteConfirmee = true;
        Boolean disponible = true;

        int noCivique = (Integer) requestBody.get("noCivique");
        int suite = (Integer) requestBody.get("suite");
        String rue = requestBody.get("rue").toString().trim();
        String codePostal = requestBody.get("codePostal").toString().trim();
        String ville = requestBody.get("ville").toString().trim();
        String province = requestBody.get("province").toString().trim();
        Adresse adresse = new Adresse(noCivique, suite, rue, codePostal, ville, province);

        Logement logement = fabriqueLogement.creerLogement(id, courriel, nbrChambres, nbrSallesDeBain, superficie,
                description, dureeBail, adresse, proprieteConfirmee, disponible);

        return daoLogement.modifier(id, logement);

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

}