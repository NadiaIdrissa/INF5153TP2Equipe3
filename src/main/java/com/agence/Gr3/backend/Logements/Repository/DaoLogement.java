package com.agence.Gr3.backend.Logements.Repository;

import org.springframework.stereotype.Repository;
import com.agence.Gr3.backend.Logements.Model.Logement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class DaoLogement {

    private final Map<Integer, Logement> logements = new HashMap<Integer, Logement>();

    /**
     * 
     * @param logement
     * @return
     */
    public Logement inserer(int id, Logement logement) {
        this.logements.put(id, logement);
        return lire(id);

    }

    public Logement modifier(int id, Logement logement) {
        this.logements.put(id, logement);
        return lire(id);
    }

    public Logement lire(int id) {
        return this.logements.get(id);

    }

    /**
     * Récupère les logements associés à l'utilisateur
     * 
     * @return la liste de tous les logements
     */
    public List<Logement> rechercher(String courriel) {
        System.out.println("Afficher mes Logements");

        List<Logement> logementsTrouves = new ArrayList<>();

        for (Map.Entry<Integer, Logement> entry : this.logements.entrySet()) {
            Logement logement = entry.getValue();
            if (logement.getIdRepresentant().equals(courriel)) {
                logementsTrouves.add(logement);
            }
        }

        return logementsTrouves;

    }

    /**
     * Récupère tous les logements.
     * 
     * @return
     */
    public List<Logement> rechercherTous() {

        System.out.println("Afficher tous les Logements");
        List<Logement> logements = new ArrayList<>(this.logements.values());
        return logements;

    }

    public List<Logement> rechercher(Map<String, Object> criteres) {
        List<Logement> logements = new ArrayList<>(this.logements.values());
        return logements;

    }

}