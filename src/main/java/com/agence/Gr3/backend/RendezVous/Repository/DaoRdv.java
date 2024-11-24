package com.agence.Gr3.backend.RendezVous.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.agence.Gr3.backend.RendezVous.Model.*;

@Repository
public class DaoRdv {

    private final Map<Integer, Rdv> rdvs = new HashMap<Integer, Rdv>();

    public Rdv inserer(int idRdv, Rdv rdv) {
        System.out.println("DaoRdv: inserer");
        this.rdvs.put(idRdv, rdv);
        return lire(idRdv);
    }

    public Rdv modifier(int idRdv, LocalDateTime localDateTime) {
        Rdv rdv = lire(idRdv);
        rdv.setStatut(Statut.MODIFIE);
        rdv.setDateHeureProposee(localDateTime);
        return rdv;
    }

    public Rdv annuler(int idRdv) {
        Rdv rdv = lire(idRdv);
        rdv.setStatut(Statut.ANNULE);
        return rdv;
    }

    public Rdv confirmer(int idRdv) {
        Rdv rdv = lire(idRdv);
        rdv.setStatut(Statut.CONFIRME);
        rdv.setDateHeure(rdv.getDateHeureProposee());
        return rdv;
    }

    public Rdv lire(int idRdv) {
        return this.rdvs.get(idRdv);
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
