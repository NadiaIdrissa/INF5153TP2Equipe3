package com.agence.Gr3.backend.Utilisateurs.Repository;

import org.springframework.stereotype.Repository;

import com.agence.Gr3.backend.Utilisateurs.Model.Identifiant;
import com.agence.Gr3.backend.Utilisateurs.Model.Utilisateur;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

@Repository
public class DaoUtilisateurs {

    private final Map<String, Utilisateur> utilisateurs = new HashMap<String, Utilisateur>();

    /**
     * Insère un utilisateur dans la base de donnée (collection).
     * 
     * @param utilisateur L'utilisateur à insérer.
     */
    public void inserer(Utilisateur utilisateur) {
        this.utilisateurs.put(utilisateur.getIdentifiant().getCourriel(), utilisateur);

        System.out.println(utilisateurs);

    }

    /**
     * Récupère un utilisateur à partir de la base de donnée (collection).
     * 
     * @param identifiant L'identifiant de l'utilisateur à récupérer.
     * @return L'utilisateur correspondant à l'identifiant, ou null si aucune
     *         utilisateur n'est trouvé.
     */
    public Utilisateur lire(Identifiant identifiant) {
        System.out.println("On entre dans repository lire");

        return this.utilisateurs.get(identifiant.getCourriel());

    }

    /**
     * Récupère tous les utilisateurs.
     * 
     * @return Une liste contenant tous les utilisateurs dans la base de donnée
     *         (collection).
     */
    public List<Utilisateur> rechercher() {
        List<Utilisateur> utilisateurs = new ArrayList<>(this.utilisateurs.values());
        return utilisateurs;

    }

    /**
     * Récupère une liste d'utilisateurs en fonction de la liste d'identifiants
     * passé en argument.
     * 
     * @param identifiants Une liste d'identifiant.
     * @return Une liste d'utilisateur correspondant aux identifiants donnés.
     */
    public List<Utilisateur> rechercher(List<Identifiant> identifiants) {

        List<Utilisateur> utilisateurs = new ArrayList<>();

        for (Identifiant identifiant : identifiants) {
            utilisateurs.add(this.utilisateurs.get(identifiant.getCourriel()));

        }

        return utilisateurs;

    }

    /**
     * Supprime un utilisateur à partir de l'identifiant spécifié.
     * 
     * @param identifiant L'identifiant de l'utilisateur à supprimer.
     * @return true si la suppression a eu lieu, faux sinon
     */
    public boolean supprimer(Identifiant identifiant) {

        if (lire(identifiant) != null) {
            utilisateurs.remove(identifiant.getCourriel());
            return true;
        }

        return false;
    }

}
