package com.agence.Gr3.backend.Utilisateurs;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.http.HttpHeaders;
import io.jsonwebtoken.Claims;
import com.agence.Gr3.backend.Utilisateurs.Model.*;
import com.agence.Gr3.backend.Utilisateurs.Repository.DaoUtilisateurs;
import com.agence.Gr3.backend.Utilisateurs.Services.ServiceJwt;
import com.agence.Gr3.Model.Adresse;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Base64;
import java.util.HashMap;

@RestController
@RequestMapping("/utilisateur")
public class ControleurUtilisateur {

    private DaoUtilisateurs daoUtilisateurs; // BD
    private ServiceJwt serviceJwt; // Service pour gérer les JWT

    // Constructeur
    public ControleurUtilisateur(DaoUtilisateurs daoUtilisateurs, ServiceJwt serviceJwt) {
        this.daoUtilisateurs = daoUtilisateurs;
        this.serviceJwt = serviceJwt;

    }

    @PostMapping("/creer")
    public ResponseEntity<String> creerUtilisateur(@RequestBody HashMap<String, String> requestBody) {

        System.out.println("CONTROLEUR UTILISATEUR"); // DEBUGG

        String courriel = requestBody.get("courriel").toString().trim();
        String mdp = requestBody.get("mdp").toString().trim();
        String role = requestBody.get("role").toString().trim();

        System.out.println("courriel recu: " + courriel);// DEBUGG
        System.out.println("mot de passe recu: " + mdp);// DEBUGG

        Role roleUtilisateur;

        switch (role) {

            case "representant":
                roleUtilisateur = Role.REPRESENTANT;
                break;

            case "agent":
                roleUtilisateur = Role.AGENT;
                break;

            default:
                roleUtilisateur = Role.LOCATAIRE;

        }

        // Création et insertion d'un utilisateur
        Identifiant identifant = new Identifiant(courriel, mdp);

        System.out.println("Role recu: " + role);
        System.out.println("ROLE DE L'UTILISATEUR : " + roleUtilisateur);

        // Chercher
        if (daoUtilisateurs.lire(identifant.getCourriel()) != null) {
            return new ResponseEntity<String>("Erreur: l'utilisateur existe déjà!", HttpStatus.INTERNAL_SERVER_ERROR);

        }

        BuilderUtilisateur builder = new BuilderUtilisateur(identifant, roleUtilisateur);
        Utilisateur utilisateur = builder.build();
        // Comme la BD est une MAP qui contient des paires clé valeur
        // la clé est le courriel et la valeur l'utilisateur.
        daoUtilisateurs.inserer(courriel, utilisateur);

        return new ResponseEntity<String>("Utilisateur enregitstré", HttpStatus.OK);

    }

    @PostMapping("/profil")
    public ResponseEntity<String> modifierProfil(@RequestHeader("Authorization") String authorizationHeader,
            @RequestBody HashMap<String, Object> requestBody) {

        // Extraction et validation du JWT
        String jwt = authorizationHeader.replace("Bearer ", "");

        try {

            // Validation du JWT soumis
            Claims claims = serviceJwt.validerJwt(jwt);
            Utilisateur utilisateur = daoUtilisateurs.lire(claims.getSubject());

            if (utilisateur != null) {

                // Création de l'adresse
                int noCivique = (Integer) requestBody.get("noCivique");
                int suite = (Integer) requestBody.get("suite");
                String rue = requestBody.get("rue").toString().trim();
                String codePostal = requestBody.get("codePostal").toString().trim();
                String ville = requestBody.get("ville").toString().trim();
                String province = requestBody.get("province").toString().trim();
                Adresse adresse = new Adresse(noCivique, suite, rue, codePostal, ville, province);

                // Création du profil
                String nom = requestBody.get("nom").toString().trim();
                String prenom = requestBody.get("prenom").toString().trim();
                String telephone = requestBody.get("telephone").toString().trim();
                Profil profil = new Profil(nom, prenom, telephone, adresse);

                // modification du profil de l'utilisateur
                utilisateur.setProfil(profil);

                System.out.println(utilisateur);

                return new ResponseEntity<String>("Profil modifié!", HttpStatus.OK);

            }

            return new ResponseEntity<String>("Accès interdit!", HttpStatus.UNAUTHORIZED);

        } catch (Exception e) {

            return new ResponseEntity<String>("Accès interdit!", HttpStatus.UNAUTHORIZED);

        }

    }

    @PostMapping("/connexion")
    public ResponseEntity<List<Permission>> connecterUtilisateur(
            @RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader) {

        System.out.println("CONNEXION");

        if (authorizationHeader == null || !authorizationHeader.startsWith("Basic ")) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build(); // Invalid or missing Authorization header
        }

        String token = authorizationHeader.substring(6); // Extract token part (after "Basic ")
        String decodedCredentials = decodeBase64(token);

        if (decodedCredentials == null) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build(); // Invalid Base64 encoding
        }

        String[] identifiants = decodedCredentials.split(":", 2);
        if (identifiants.length != 2) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build(); // Invalid credentials format
        }

        // Extraction du courriel et mdp du tableau identifiants
        String courriel = identifiants[0].trim();
        String mdp = identifiants[1].trim();

        System.out.println("courriel recu: " + courriel);
        System.out.println("mot de passe recu: " + mdp);

        Utilisateur utilisateur = daoUtilisateurs.lire(courriel);

        if (utilisateur == null) {
            System.out.println("L'utilisateur est null");
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        } else if (!utilisateur.getIdentifiant().getMdp().equals(mdp)) {

            String mdpBD = utilisateur.getIdentifiant().getMdp();

            System.out.println(mdpBD + " de taille " + mdpBD.length());
            System.out.println(mdp + " de taille " + mdp.length());

            System.out.println("mot de passe BD: " + utilisateur.getIdentifiant().getMdp());
            Identifiant identifiant = utilisateur.getIdentifiant();
            String courriel2 = identifiant.getCourriel();
            String statut = utilisateur.getStatut();
            System.out.println("statut :" + statut);
            System.out.println("Courriel2 :" + courriel2);
            System.out.println("le mot de passe de correspond pas");
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        List<Permission> permissions = utilisateur.getRole().getPermissions();
        System.out.println("Nouvelles permissions:" + permissions);
        String jwt = serviceJwt.creerJwt(utilisateur.getIdentifiant());

        return ResponseEntity.ok()
                .header("Authorization", "Bearer " + jwt)
                .body(permissions);

    }

    private String decodeBase64(String encodedString) {
        try {
            return new String(Base64.getDecoder().decode(encodedString), StandardCharsets.UTF_8);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

}
