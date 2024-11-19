package com.agence.Gr3.backend.Utilisateurs;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;

import com.agence.Gr3.backend.Utilisateurs.Model.*;
import com.agence.Gr3.backend.Utilisateurs.Repository.DaoUtilisateurs;

@RestController
@RequestMapping("/utilisateur")
public class ControleurUtilisateur {

    private DaoUtilisateurs daoUtilisateurs;

    public ControleurUtilisateur(DaoUtilisateurs daoUtilisateurs) {
        this.daoUtilisateurs = daoUtilisateurs;
    }

    @PostMapping("/creer")
    public ResponseEntity<String> creerUtilisateur(@RequestBody MultiValueMap<String, String> requestBody) {

        System.out.println("CONTROLEUR UTILISATEUR");

        String courriel = requestBody.get("courriel").toString();
        String mdp = requestBody.get("mdp").toString();
        String role = requestBody.get("role").toString();
        Role roleUtilisateur;

        switch (role) {

            case "representant":
                roleUtilisateur = Role.REPRESENTANT;

            case "agent":
                roleUtilisateur = Role.AGENT;

            default:
                roleUtilisateur = Role.LOCATAIRE;

        }

        // Création et insertion d'un utilisateur
        Identifiant identifant = new Identifiant(courriel, mdp);

        // Chercher
        if (daoUtilisateurs.lire(identifant) != null) {
            return new ResponseEntity<String>("Erreur: l'utilisateur existe déjà!", HttpStatus.INTERNAL_SERVER_ERROR);

        }

        BuilderUtilisateur builder = new BuilderUtilisateur(identifant, roleUtilisateur);
        Utilisateur utilisateur = builder.build();
        daoUtilisateurs.inserer(utilisateur);

        return new ResponseEntity<String>("Utilisateur enregitstré", HttpStatus.OK);

    }

    // @GetMapping("/connexion")

}
