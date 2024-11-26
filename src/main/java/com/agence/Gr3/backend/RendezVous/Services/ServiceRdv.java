package com.agence.Gr3.backend.RendezVous.Services;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import com.agence.Gr3.backend.RendezVous.Model.Rdv;
import com.agence.Gr3.backend.RendezVous.Model.Statut;
import com.agence.Gr3.backend.RendezVous.Repository.DaoRdv;
import com.agence.Gr3.backend.Logements.Services.ServiceLogement;
import com.agence.Gr3.backend.Notifications.Model.Observateur;
import com.agence.Gr3.backend.Notifications.Services.ServiceNotification;
import com.agence.Gr3.backend.RendezVous.Model.Sujet;

@Service
public class ServiceRdv implements Sujet {

    private static AtomicInteger atomicId = new AtomicInteger(0);
    private DaoRdv daoRdv;
    private ServiceLogement serviceLogement;
    private ServiceNotification serviceNotification;

    private List<Observateur> observateurs = new ArrayList<>();

    @Override
    public void ajouterObservateur(Observateur observateur) {
        observateurs.add(observateur);
    };

    @Override
    public void retirerObservateur(Observateur observateur) {
        observateurs.remove(observateur);
    };

    @Override
    public void notifierObservateurs(String idUtilisateur, String notification) {
        for (Observateur observateur : observateurs) {
            observateur.mettreAJour(idUtilisateur, notification);
        }

    };

    private ServiceRdv(DaoRdv daoRdv, ServiceLogement serviceLogement, ServiceNotification serviceNotification) {
        this.daoRdv = daoRdv;
        this.serviceLogement = serviceLogement;
        this.serviceNotification = serviceNotification;
    }

    /**
     * Crée un nouvel objet rendez-vous (Rdv) à partir des informations fournies
     * dans le corps de la requête.
     * 
     * 
     * 
     * 
     * @param courriel    Le courriel de l'utilisateur qui demande un rdv
     * @param requestBody Le corps de la requête HTTP
     * @return Le rdv inséré dans la
     */
    public Rdv creer(String courriel, Map<String, Object> requestBody) {

        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

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

    }

    public Rdv modifier(int idRdv, String idUtilisateur, Map<String, Object> requestBody) {

        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        LocalDateTime dateHeurePropose = LocalDateTime.parse(requestBody.get("dateHeurePropose").toString(),
                format);
        // Le id de l'utilisateur est pour la notification

        return daoRdv.modifier(idRdv, dateHeurePropose);

    }

    // LOGIQUE DE CRÉATION DES NOTIFICATION ET PERSISTANCE DES NOTIFICATIONS

    /*
     * public void confirmer(Rdv rdv, String idUtilisateur) {
     * 
     * if (rdv.getStatut() == Statut.ATTENTE) {
     * rdv.setStatut(Statut.CONFIRME);
     * rdv.notifierObservateurs(genererNotification(rdv, idUtilisateur,
     * Statut.CONFIRME));
     * } else if (rdv.getStatut() == Statut.MODIFIE) {
     * rdv.setStatut(Statut.CONFIRME);
     * rdv.setDateHeure(rdv.getDateHeureProposee());
     * rdv.notifierObservateurs(genererNotification(rdv, idUtilisateur,
     * Statut.CONFIRME));
     * }
     * 
     * }
     */
    public Rdv confirmer(int idRdv, String idUtilisateur) {

        // Le id de l'utilisateur est pour la notification
        String destinataire;

        Rdv rdv = rechercher(idRdv);

        if (rdv.getIdLocataire().equals(idUtilisateur)) {

            destinataire = rdv.getIdGerant();

        } else {
            destinataire = rdv.getIdLocataire();

        }

        notifierObservateurs(destinataire, idUtilisateur + "a confirmé le rendez-vous suivant:" + idRdv);

        return daoRdv.confirmer(idRdv);

    }

    public Rdv annuler(int idRdv, String idUtilisateur) {

        // Le id de l'utilisateur est pour la notification
        String destinataire;

        Rdv rdv = rechercher(idRdv);

        if (rdv.getIdLocataire().equals(idUtilisateur)) {

            destinataire = rdv.getIdGerant();

        } else {
            destinataire = rdv.getIdLocataire();

        }

        notifierObservateurs(destinataire, idUtilisateur + "a confirmé le rendez-vous suivant:" + idRdv);

        return daoRdv.confirmer(idRdv);

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

}
