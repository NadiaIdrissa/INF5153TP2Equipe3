package com.agence.Gr3.backend.RendezVous.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.agence.Gr3.backend.Logements.Model.Logement;
import com.agence.Gr3.backend.RendezVous.Model.Rdv;

@Repository
public class DaoRdv {

    private final Map<Integer, Rdv> rdvs = new HashMap<Integer, Rdv>();

    public Rdv inserer(int id, Rdv rdv) {

        System.out.println("DaoRdv: inserer");
        this.rdvs.put(id, rdv);
        return lire(id);
    }

    public Rdv modifier(int id, Rdv rdv) {
        this.rdvs.put(id, rdv);
        return lire(id);
    }

    public Rdv lire(int id) {
        return this.rdvs.get(id);
    }

    public List<Rdv> rechercher(String courriel) {
        List<Rdv> rdvsTrouves = new ArrayList<>();
        for (Map.Entry<Integer, Rdv> entry : this.rdvs.entrySet()) {
            Rdv rdv = entry.getValue();
            if (rdv.getIdGerant().equals(courriel) || rdv.getIdLocataire().equals(courriel)) {
                rdvsTrouves.add(rdv);
            }
        }
        return rdvsTrouves;
    }

}
