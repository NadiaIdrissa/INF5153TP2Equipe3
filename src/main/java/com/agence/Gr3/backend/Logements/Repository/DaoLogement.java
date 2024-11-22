package com.agence.Gr3.backend.Logements.Repository;

import org.springframework.stereotype.Repository;
import java.util.concurrent.atomic.AtomicInteger;
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
        System.out.println(logements); // DEBUG

        return lire(id);

    }

    public Logement modifier(int id, Logement logement) {
        this.logements.put(id, logement);
        System.out.println(logements); // DEBUG
        return lire(id);
    }

    public Logement lire(int id) {
        System.out.println(logements); // DEBUG
        return this.logements.get(id);

    }

    /**
     * Récupère les logements associés à l'utilisateur
     * 
     * @return la liste de tous les logements
     */
    public List<Logement> rechercher(String courriel) {
        List<Logement> logements = new ArrayList<>(this.logements.values());
        System.out.println(logements); // DEBUG
        return logements;

    }

    public List<Logement> rechercher(Map<String, Object> criteres) {
        List<Logement> logements = new ArrayList<>(this.logements.values());
        return logements;

    }

}