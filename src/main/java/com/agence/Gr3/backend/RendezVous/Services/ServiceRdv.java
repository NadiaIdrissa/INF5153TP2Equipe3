package com.agence.Gr3.backend.RendezVous.Services;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import com.agence.Gr3.backend.Logements.Model.FabriqueLogementDeBase;
import com.agence.Gr3.backend.Logements.Model.Logement;
import com.agence.Gr3.backend.Logements.Repository.DaoLogement;
import com.agence.Gr3.backend.RendezVous.Model.Rdv;
import com.agence.Gr3.backend.RendezVous.Model.Statut;
import com.agence.Gr3.backend.RendezVous.Repository.DaoRdv;
import com.agence.Gr3.backend.Logements.Services.ServiceLogement;

@Service
public class ServiceRdv {

    private static AtomicInteger atomicId = new AtomicInteger(0);
    private DaoRdv daoRdv;
    private ServiceLogement serviceLogement;

    private ServiceRdv(DaoRdv daoRdv, ServiceLogement serviceLogement) {
        this.daoRdv = daoRdv;
        this.serviceLogement = serviceLogement;
    }

    public Rdv creer(String courriel, Map<String, Object> requestBody) {

        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        try {

            System.out.println(requestBody);
            System.out.println("ServiceRdv: creer");

            // Création d'un id
            int id = atomicId.incrementAndGet();

            // Extraction des information du corps de la requête HTTP
            int idLogement = (Integer) requestBody.get("idLogement");
            LocalDateTime dateHeurePropose = LocalDateTime.parse(requestBody.get("dateHeurePropose").toString(),
                    format);

            // Extraction du idGerant à partir du idLogement
            String idGerant = serviceLogement.rechercherUnique(idLogement).getIdGerant();

            // Creation de l'objet Rdv
            Rdv rdv = new Rdv(id, idLogement, courriel, idGerant, dateHeurePropose);

            return daoRdv.inserer(id, rdv);

        } catch (Exception e) {
            System.out.println("Exception while decorating: " + e.getMessage());
            e.printStackTrace(); // This will print the full stack trace to understand where it went wrong
        }

        return null;

    }

    public Rdv modifier(int id, String courriel, Map<String, Object> requestBody) {

        System.out.println("Rdv confirmé");
        Rdv rdv = null;

        // int idLogement, String idLocataire, String idGerant,LocalDateTime dateHeure

        // daoRdv.inserer(idLogement, null);

        return rdv;

    }

    public void confirmer(Rdv rdv, String idUtilisateur) {

        if (rdv.getStatut() == Statut.ATTENTE) {
            rdv.setStatut(Statut.CONFIRME);
            rdv.notifierObservateurs(genererNotification(rdv, idUtilisateur, Statut.CONFIRME));
        } else if (rdv.getStatut() == Statut.MODIFIE) {
            rdv.setStatut(Statut.CONFIRME);
            rdv.setDateHeure(rdv.getDateHeureProposee());
            rdv.notifierObservateurs(genererNotification(rdv, idUtilisateur, Statut.CONFIRME));
        }

    }

    public Rdv confirmer(int id, String idUtilisateur) {

        System.out.println("Rdv confirmé");

        return null;

    }

    public Rdv annuler(int id, String idUtilisateur) {

        return null;

    }

    /**
     * Récupère les logements associés à l'utilisateur
     * 
     * @return la liste de tous les logements associés à l'utilisateur
     */
    public List<Rdv> rechercher(String courriel) {

        return daoRdv.rechercher(courriel);

    }

    public Rdv rechercher(int id) {
        return daoRdv.lire(id);

    }

    // Get the rendezvous

    // rdv.setStatut(Statut.ANNULE);
    // rdv.notifierObservateurs(genererNotification(rdv, idUtilisateur,
    // Statut.ANNULE));

    public void modifier(Rdv rdv, String idUtilisateur, LocalDateTime dateHeureProposee) {
        rdv.setStatut(Statut.MODIFIE);
        rdv.setDateHeureProposee(dateHeureProposee);
        rdv.notifierObservateurs(genererNotification(rdv, idUtilisateur, Statut.MODIFIE));
    }

    private String genererNotification(Rdv rdv, String idUtilisateur, Statut statut) {
        String notification = idUtilisateur + " " + statut.getText() + ": Logement:" + rdv.getIdLogement();

        if (statut == Statut.MODIFIE) {
            notification = notification + " Date et heure suggérée:" + rdv.getDateHeureProposee() +
                    " Date et heure actuelle:" + rdv.getDateHeure();
        }

        notification = notification + " Date et heure:" + rdv.getDateHeure();
        return notification;
    }

}
